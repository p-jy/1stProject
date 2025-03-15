package db2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library"; // DB URL
    private static final String USER = "root"; // MySQL 계정 (본인 설정 확인)
    private static final String PASSWORD = "root"; // MySQL 비밀번호

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 데이터베이스 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("MySQL 연결 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 실패: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("MySQL 연결 실패: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("MySQL 연결 종료!");
            }
        } catch (SQLException e) {
            System.out.println("MySQL 연결 종료 실패: " + e.getMessage());
        }
    }
}
