package db2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db2.config.DBConnection;
import db2.model.vo.Book;

public class BookDAOImpl implements BookDAO {
    
    private Connection conn;

    // 데이터베이스 연결을 위한 메서드
    public BookDAOImpl() {
        this.conn = DBConnection.getConnection();  // DBConnection 클래스의 getConnection()을 사용
    }

    /**
     * 도서 코드로 책을 조회하는 메서드
     * 
     * @param bookCode 조회할 도서의 코드
     * @return 해당 도서 코드에 맞는 도서 객체, 없으면 null
     */
    @Override
    public Book selectBookByCode(String bookCode) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE bo_code = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookCode);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                book = new Book(
                        rs.getString("bo_code"),
                        rs.getString("bo_title"),
                        rs.getString("bo_author"),
                        rs.getString("bo_publisher")
                );
                book.setRentReturn(rs.getBoolean("bo_rent"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    /**
     * 모든 도서를 조회하는 메서드
     * 
     * @return 모든 도서 객체 리스트
     */
    @Override
    public List<Book> selectAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("bo_code"),
                        rs.getString("bo_title"),
                        rs.getString("bo_author"),
                        rs.getString("bo_publisher")
                );
                book.setRentReturn(rs.getBoolean("bo_rent"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    /**
     * 책 등록 메서드
     * 
     * @param book 등록할 도서 객체
     */
    @Override
    public void insertBook(Book book) {
        String sql = "INSERT INTO books (bo_code, bo_title, bo_author, bo_publisher, bo_rent, bo_ca_num) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getBookCode());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setBoolean(5, book.isRentReturn());
            pstmt.setString(6, book.getCategory());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 책 수정 메서드
     * 
     * @param book 수정할 도서 객체
     */
    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET bo_title = ?, bo_author = ?, bo_publisher = ?, bo_rent = ?, bo_ca_num = ? WHERE bo_code = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setBoolean(4, book.isRentReturn());
            pstmt.setString(5, book.getCategory());
            pstmt.setString(6, book.getBookCode());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 책 삭제 메서드
     * 
     * @param bookCode 삭제할 도서의 코드
     */
    @Override
    public void deleteBook(String bookCode) {
        String sql = "DELETE FROM books WHERE bo_code = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public boolean registBook(Book book) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

	@Override
	public List<Book> searchBooksByTitle(String title) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public List<Book> searchBooksByAuthor(String author) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public List<Book> searchBooksByPublisher(String publisher) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public List<Book> searchBooksByCode(String bookCode) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}
}
