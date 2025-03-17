package db3.dao;

import java.sql.*;
import java.time.LocalDateTime;

import db3.model.vo.Rent;

public class RentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // 1. 회원의 현재 대여 중인 도서 수 조회 (RE_STATE가 '대여'인 레코드 개수)
    public int getCurrentRentCount(String memberId) {
        String sql = "SELECT COUNT(*) FROM RENT WHERE RE_ME_ID = ? AND RE_STATE = '대여'";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 2. 도서 대여 기록 삽입 (대여 처리)
    public boolean insertRent(Rent rent) {
        // 대여 시작일, 반납 예정일, 회원ID, 도서코드, 상태 ('대여')를 INSERT
        String sql = "INSERT INTO RENT (RE_RENT_DATE, RE_DUE_DATE, RE_ME_ID, RE_BO_CODE, RE_STATE) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(rent.getReRentDate())); // 대여 시작일
            pstmt.setTimestamp(2, Timestamp.valueOf(rent.getReDueDate()));  // 반납 예정일 (예: 오늘 + 7일)
            pstmt.setString(3, rent.getReMeId());  // 회원 ID
            pstmt.setString(4, rent.getReBoCode());  // 도서 코드
            pstmt.setString(5, rent.getReState());   // 상태 ("대여")

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. 활성 대여 기록 조회 (회원이 대여 중인 특정 도서 조회)
    public Rent findActiveRent(String memberId, String bookCode) {
        String sql = "SELECT * FROM RENT WHERE RE_ME_ID = ? AND RE_BO_CODE = ? AND RE_STATE = '대여'";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);
            pstmt.setString(2, bookCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Rent rent = new Rent();
                rent.setReNum(rs.getInt("RE_NUM"));
                rent.setReRentDate(rs.getTimestamp("RE_RENT_DATE").toLocalDateTime());
                rent.setReDueDate(rs.getTimestamp("RE_DUE_DATE").toLocalDateTime());
                Timestamp returnTs = rs.getTimestamp("RE_RETURN_DATE");
                if (returnTs != null) {
                    rent.setReReturnDate(returnTs.toLocalDateTime());
                }
                rent.setReState(rs.getString("RE_STATE"));
                rent.setReMeId(rs.getString("RE_ME_ID"));
                rent.setReBoCode(rs.getString("RE_BO_CODE"));
                return rent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. 대여 기록 업데이트 (반납 처리)
    public boolean updateRentReturn(Rent rent) {
        // 반납 시 RE_RETURN_DATE와 RE_STATE를 업데이트 (예: 상태를 '반납'으로 변경)
        String sql = "UPDATE RENT SET RE_RETURN_DATE = ?, RE_STATE = ? WHERE RE_NUM = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(rent.getReReturnDate()));
            pstmt.setString(2, rent.getReState());
            pstmt.setInt(3, rent.getReNum());

            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
