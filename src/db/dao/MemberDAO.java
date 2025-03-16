package db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.model.vo.Member;

public class MemberDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // 회원 목록 불러오기
    public List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                memberList.add(new Member(
                    rs.getString("ME_ID"),
                    rs.getString("ME_PW"),
                    rs.getString("ME_NAME"),
                    rs.getString("ME_NUM"),
                    rs.getString("ME_AUTHORITY"),
                    rs.getString("ME_CAN_RENT")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberList;
    }
    
    // 회원 가입
    public boolean addMember(Member member) {
    	String sql = "INSERT INTO member (ME_ID, ME_PW, ME_NAME, ME_NUM, ME_AUTHORITY, ME_CAN_RENT, ME_CAN_RENT_DATE, ME_NO_RENT, ME_DEL) " +
                		"VALUES (?, ?, ?, ?, 'USER', 'Y', CURDATE(), 0, 'N')";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPw());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getNum());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // 회원 조회 (나중에)
    
    
    // 회원 수정
    public boolean updateMember(String id, String newName, String newAuthority) {
        String sql = "UPDATE member SET ME_NAME = ?, ME_AUTHORITY = ? WHERE ME_ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newAuthority);
            pstmt.setString(3, id);

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 회원 삭제
    public boolean deleteMember(String id) {
        String sql = "DELETE FROM member WHERE ME_ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
