package db2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db2.model.vo.Book;

public interface BookDAO {

	// 1. 도서 등록
    boolean registBook(@Param("book") Book book);

    // 2. 도서 수정
    boolean updateBook(@Param("book") Book book);

    // 3. 도서 삭제 (도서코드 기준)
    boolean deleteBook(@Param("bookCode") String bookCode);

    // 4. 도서 조회 (도서코드 기준)
    Book selectBookByCode(@Param("bookCode") String bookCode);

    // 5. 도서 목록 (전체 도서 가져오기)
    List<Book> selectAllBooks();

    // 검색 기능
    // 1. 도서명으로 검색
    List<Book> searchBooksByTitle(@Param("title") String title);

    // 2. 작가명으로 검색
    List<Book> searchBooksByAuthor(@Param("author") String author);

    // 3. 출판사로 검색
    List<Book> searchBooksByPublisher(@Param("publisher") String publisher);

    // 4. 도서 번호(코드)로 검색
    List<Book> searchBooksByCode(@Param("bookCode") String bookCode);

	/**
	 * 책 등록 메서드
	 * 
	 * @param book 등록할 도서 객체
	 */
	void insertBook(Book book);

		
}