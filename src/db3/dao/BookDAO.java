package db3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db3.model.vo.Book;

public class BookDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

	public Book findByCode(String bookCode) {
	    String sql = "SELECT * FROM book WHERE BO_CODE = ?";
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	         
	        pstmt.setString(1, bookCode);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            return new Book(
	                rs.getString("BO_CODE"),
	                rs.getString("BO_TITLE"),
	                rs.getString("BO_AUTHOR"),
	                rs.getString("BO_PUBLISHER"),
	                rs.getString("BO_DEL"),
	                rs.getString("BO_RENT"),
	                rs.getInt("BO_CA_NUM")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public boolean updateBookRentStatus(String bookCode, String newStatus) {
	    String sql = "UPDATE book SET BO_RENT = ? WHERE BO_CODE = ?";
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, newStatus);
	        pstmt.setString(2, bookCode);

	        int result = pstmt.executeUpdate();
	        return result > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}



}
