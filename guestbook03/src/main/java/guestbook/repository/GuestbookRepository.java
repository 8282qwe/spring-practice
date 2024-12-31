package guestbook.repository;

import guestbook.repository.template.JdbcContext;
import guestbook.vo.GuestbookVo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GuestbookRepository {
    private final JdbcContext jdbcContext;
    private final DataSource dataSource;

    public GuestbookRepository(JdbcContext jdbcContext, DataSource dataSource) {
        this.jdbcContext = jdbcContext;
        this.dataSource = dataSource;
    }

    public List<GuestbookVo> findAll() {
        return jdbcContext.queryForList("select id, name, date_format(reg_date,'%Y-%m-%d %h:%i:%s') as reg_date_format, contents from guestbook order by reg_date desc;"
                , new RowMapper<GuestbookVo>() {
                    @Override
                    public GuestbookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        GuestbookVo guestbookVo = new GuestbookVo();
                        guestbookVo.setId(rs.getLong("id"));
                        guestbookVo.setName(rs.getString("name"));
                        guestbookVo.setRegDate(rs.getString("reg_date_format"));
                        guestbookVo.setContents(rs.getString("contents"));
                        return guestbookVo;
                    }
                });
    }

    public int insert(GuestbookVo vo) {
        return jdbcContext.excuteUpdate("insert into guestbook values (null,?,?,?,now());", vo.getName(), vo.getPassword(), vo.getContents());
    }

    public int deleteByIdAndPassword(long id, String password) {
        return jdbcContext.excuteUpdate("delete from guestbook where id = ? and password = ?;", id, password);
    }
}
