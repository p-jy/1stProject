package db3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db3.model.vo.Member;

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
    
    
    //로그인
    public Member login(String id, String pw) {
    	
    	boolean idExists = isIdExists(id);
    	
    	 if (!isIdExists(id)) {
    	        System.out.println("존재하지 않는 아이디입니다.");
    	        return null;
	    }
    	 
    	// 비밀번호 검증
        String sql = "SELECT * FROM member WHERE ME_ID = ? AND ME_PW = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Member(
                    rs.getString("ME_ID"),
                    rs.getString("ME_PW"),
                    rs.getString("ME_NAME"),
                    rs.getString("ME_NUM"),
                    rs.getString("ME_AUTHORITY"),
                    rs.getString("ME_CAN_RENT")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 아이디 중복 체크
    public boolean isIdExists(String id) {
        String sql = "SELECT COUNT(*) FROM member WHERE ME_ID = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // ID가 존재하면 true 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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


    public Member findById(String id) {
        String sql = "SELECT * FROM member WHERE ME_ID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Member(
                    rs.getString("ME_ID"),
                    rs.getString("ME_PW"),
                    rs.getString("ME_NAME"),
                    rs.getString("ME_NUM"),
                    rs.getString("ME_AUTHORITY"),
                    rs.getString("ME_CAN_RENT")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setRentBan(String memberId, long overdueDays) {
        // 연체된 일수만큼 대여 금지 기간을 설정 (현재 날짜 + overdueDays)
        String sql = "UPDATE member SET ME_CAN_RENT = 'N', ME_CAN_RENT_DATE = DATE_ADD(CURDATE(), INTERVAL ? DAY) WHERE ME_ID = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, overdueDays);
            pstmt.setString(2, memberId);
            
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("회원 " + memberId + "의 대여 금지 기간이 " + overdueDays + "일로 설정되었습니다.");
            } else {
                System.out.println("회원 " + memberId + "의 대여 금지 기간 설정에 실패했습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
