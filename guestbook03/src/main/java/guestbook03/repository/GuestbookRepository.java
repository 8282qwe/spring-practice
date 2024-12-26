package guestbook03.repository;

import guestbook03.vo.GuestbookVo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestbookRepository {

    public List<GuestbookVo> findAll() {
        List<GuestbookVo> result = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select id, name, date_format(reg_date,'%Y-%m-%d %h:%i:%s') as reg_date_format, contents from guestbook order by reg_date desc;");
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                GuestbookVo vo = new GuestbookVo();
                vo.setId(rs.getLong("id"));
                vo.setName(rs.getString("name"));
                vo.setRegDate(rs.getString("reg_date_format"));
                vo.setContents(rs.getString("contents"));
                result.add(vo);
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return result;
    }

    public int insert(GuestbookVo vo) {
        int result = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("insert into guestbook values (null,?,?,?,now());");
        ) {
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getContents());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return result;
    }

    public int deleteByIdAndPassword(long id, String password) {
        int result = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where id = ? and password = ?;");
        ) {
            pstmt.setLong(1, id);
            pstmt.setString(2, password);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
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
