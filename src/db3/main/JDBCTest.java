package db3.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "root";

        try {
            // 1. 데이터베이스 연결
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("DB 연결 성공!");

            // 2. SQL 실행 (users → member 테이블 사용)
            String sql = "SELECT ME_ID, ME_NAME, ME_AUTHORITY FROM member";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // 3. 결과 출력 (컬럼명 수정)
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("ME_ID") + 
                                   ", 이름: " + rs.getString("ME_NAME") + 
                                   ", 권한: " + rs.getString("ME_AUTHORITY"));
            }

            // 4. 리소스 정리
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
