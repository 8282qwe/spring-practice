package guestbook.repository.template;

import org.springframework.jdbc.core.RowMapper;

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

    public <E> List<E> queryForList(String sql, RowMapper<E> rowMapper) {
        return queryForListWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection connection) throws SQLException {
                return connection.prepareStatement(sql);
            }
        }, rowMapper);
    }

    public int excuteUpdate(String sql, Object... parameters) {
        return excuteUpdateWithStatementStrategy(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i + 1, parameters[i]);
            }
            return pstmt;
        });
    }

    private <E> List<E> queryForListWithStatementStrategy(StatementStrategy statementStrategy,RowMapper<E> rowMapper) {
        List<E> result = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = statementStrategy.makeStatement(connection);
                ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs, rs.getRow()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private int excuteUpdateWithStatementStrategy(StatementStrategy statementStrategy) {
        int result = 0;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = statementStrategy.makeStatement(connection)
        ) {
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
