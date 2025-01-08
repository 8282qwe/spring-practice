package guestbook.repository.template;

import guestbook.vo.GuestbookVo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <E> List<E> query(String sql, RowMapper<E> rowMapper) {
        return queryForListWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                return connection.prepareStatement(sql);
            }
        }, rowMapper);
    }

    public <E> E queryForObject(String sql, Object[] objects, BeanPropertyRowMapper<GuestbookVo> rowMapper) {
        return queryForObjectWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                for (int i = 0; i < objects.length; i++) {
                    pstmt.setObject(i + 1, objects[i]);
                }
                return pstmt;
            }
        }, rowMapper);
    }

    public int update(String sql, Object... parameters) {
        return UpdateWithStatementStrategy(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
            return pstmt;
        });
    }

    private <E> List<E> queryForListWithStatementStrategy(StatementStrategy statementStrategy, RowMapper<E> rowMapper) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<E> result = new ArrayList<>();

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            pstmt = statementStrategy.makeStatement(connection);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(rowMapper.mapRow(rs, rs.getRow()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException ignore) {

            }
        }
        return result;
    }

    private int UpdateWithStatementStrategy(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            pstmt = statementStrategy.makeStatement(connection);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException ignore) {
            }
        }
    }

    private <E> E queryForObjectWithStatementStrategy(StatementStrategy statementStrategy, BeanPropertyRowMapper<GuestbookVo> rowMapper) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            pstmt = statementStrategy.makeStatement(connection);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return (E) rowMapper.mapRow(rs, rs.getRow());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException ignore) {

            }

        }
        return null;
    }
}
