package emaillist.repository;

import emaillist.vo.EmailListVo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmailListRepository {

    public Boolean deleteByEmail(String email) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            // 3. Statement 생성하기
            pstmt = conn.prepareStatement("delete from emaillist where email = ?;");

            // 4. Parameter Binding
            pstmt.setString(1, email);

            // 5. SQL 실행
            result = pstmt.executeUpdate() >= 1;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }

        return result;
    }

    public Boolean insert(EmailListVo vo) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            // 3. Statement 생성하기
            pstmt = conn.prepareStatement("insert into emaillist values (null, ?, ?, ?)");

            // 4. Parameter Binding
            pstmt.setString(1, vo.getFirstName());
            pstmt.setString(2, vo.getLastName());
            pstmt.setString(3, vo.getEmail());

            // 5. SQL 실행
            result = pstmt.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }

        return result;
    }

    public Long count() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            // 3. Statement 생성하기
            pstmt = conn.prepareStatement("select count(*) from emaillist;");

            // 5. SQL 실행
            rs = pstmt.executeQuery();

            while (rs.next()) {
                return rs.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
        return 0L;
    }

    public List<EmailListVo> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<EmailListVo> result = new ArrayList<>();

        try {
            conn = getConnection();

            // 3. Statement 생성하기
            pstmt = conn.prepareStatement("select id, first_name, last_name, email from emaillist;");

            // 5. SQL 실행
            rs = pstmt.executeQuery();

            while (rs.next()) {
                EmailListVo vo = new EmailListVo();
                vo.setId(rs.getLong(1));
                vo.setFirstName(rs.getString(2));
                vo.setLastName(rs.getString(3));
                vo.setEmail(rs.getString(4));
                result.add(vo);
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            // 1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mariadb://192.168.0.120:3306/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e.getMessage());
        }
        return conn;
    }
}
