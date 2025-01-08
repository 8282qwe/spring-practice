package guestbook.service;

import guestbook.repository.GuestbookLogRepository;
import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class GuestbookService {
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private DataSource dataSource;

    private final GuestbookRepository guestbookRepository;
    private final GuestbookLogRepository guestbookLogRepository;

    public GuestbookService(GuestbookRepository guestbookRepository, GuestbookLogRepository guestbookLogRepository) {
        this.guestbookRepository = guestbookRepository;
        this.guestbookLogRepository = guestbookLogRepository;
    }

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    public void deleteContents(long id, String password) {
        // TX:BEGIN
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            GuestbookVo vo = guestbookRepository.findById(id);
            if (vo == null) {
                return;
            }

            if (guestbookRepository.deleteByIdAndPassword(id, password) == 1) {
                guestbookLogRepository.update(vo.getRegDate());
            }

            // TX:END(SUCCESS)
            transactionManager.commit(txStatus);
        } catch (Throwable e) {
            // TX:END(FAILED)
            transactionManager.rollback(txStatus);
            throw new RuntimeException(e);
        }
    }

    public void addContents(GuestbookVo guestbookVo) {
        // 트랜잭션 동기(Connection) 초기화
        TransactionSynchronizationManager.initSynchronization();

        // DataSourceUtils는 DataSource에서 Connection을 가져와 위의 TransactionSynchronizationManager(ThreadLocal)에 Connection을 저장
        Connection connection = DataSourceUtils.getConnection(dataSource);

        try {
            //TX: BEGIN
            connection.setAutoCommit(false);

            if (guestbookLogRepository.update() == 0) {
                guestbookLogRepository.insert();
            }

            guestbookRepository.insert(guestbookVo);

            //TX:END(SUCCESS)
            connection.commit();
        } catch (SQLException e) {
            try {
                //TX:END(FAILED)
                connection.rollback();
                throw new RuntimeException(e);
            } catch (SQLException ignored) {

            }
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }
}
