package db2.ex1.service;

import java.util.List;

import db2.ex1.dao.BookDAO;
import db2.ex1.model.vo.Book;

public class BookDAOImpl implements BookDAO {

	@Override
	public Book selectBook(Book book) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public boolean insertBook(Book book) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

	@Override
	public boolean updateBook(Book book, Book newBook) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

	@Override
	public List<Book> selectBookList() {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public Book selectBookByCode(String code) {
		// TODO 자동 생성된 메소드 스텁
		return null;
	}

	@Override
	public boolean updateBookRentStatus(Book book) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

}
