package guestbook.repository;

import guestbook.repository.template.JdbcContext;
import guestbook.vo.GuestbookVo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GuestbookRepository {
    private final JdbcContext jdbcContext;

    public GuestbookRepository(JdbcContext jdbcContext, DataSource dataSource) {
        this.jdbcContext = jdbcContext;
    }

    public List<GuestbookVo> findAll() {
        return jdbcContext.query("select id, name, date_format(reg_date,'%Y-%m-%d %h:%i:%s') as regDate, contents from guestbook order by reg_date desc;"
                , new BeanPropertyRowMapper<>(GuestbookVo.class)
        );
    }

    public int insert(GuestbookVo vo) {
        return jdbcContext.update("insert into guestbook values (null,?,?,?,now());", vo.getName(), vo.getPassword(), vo.getContents());
    }

    public int deleteByIdAndPassword(long id, String password) {
        return jdbcContext.update("delete from guestbook where id = ? and password = ?;", id, password);
    }

    public GuestbookVo findById(long id) {
        return jdbcContext.queryForObject("select id, name, date_format(reg_date,'%Y-%m-%d %h:%i:%s') as regDate, contents from guestbook where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(GuestbookVo.class));
    }
}
