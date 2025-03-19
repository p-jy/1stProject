package db2.ex1.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db2.ex1.dao.BookDAO;
import db2.ex1.model.vo.Book;

public class BookManager {
	//도서관리
	
	private static List<Book> list;
	
	private BookDAO bookDao;
	
	public BookManager() {
		String resource = "db2/ex1/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			bookDao = session.getMapper(BookDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean contains(Book book) {
		Book dbBook = bookDao.selectBook(book);
		
		return dbBook != null;
	}
	
	public boolean registBook(Book book) {
		
		if(book == null) {
			return false;
		}
		
		if(contains(book)) {
			return false;
		}
		
		return bookDao.insertBook(book);
	}

	public void print(String title) {
		
		if(list == null) {
			System.out.println("");
		}
		
	}

	public void print() {
		
		List<Book> list = bookDao.selectBookList();
		System.out.println("===============================================");
		if(list == null || list.size() == 0) {
			System.out.println("[등록된 도서가 없습니다.]");
			return;
		}
		System.out.println(" 도서코드" + "\t" + "   도서명" + "\t\t" + "저자" + "\t" + "출판사" + "\t" + "대여");
		System.out.println("-----------------------------------------------");
		for(Book book : list) {
			System.out.println(book);
		}
		System.out.println("===============================================");
	}

	public int getLastNum(String codePrefix) {

		List<Book> list = bookDao.selectBookList();
		
		if(list == null || list.size() == 0) {
			return 0;
		}
		
		int max = 0;
		
		for(Book book : list) {
			String bookCodePrefix = book.getCode().substring(0, 3);
			String bookCodeSuffix = book.getCode().substring(3);
			if(bookCodePrefix.equals(codePrefix)) {
				int num = Integer.parseInt(bookCodeSuffix);
				if(max < num) {
					max = num;
				}
			}
		}
		return max;
	}

	public boolean update(Book book, Book newBook) {
		
		if(book == null || newBook == null) {
			return false;
		}
		
		if(!contains(book)) {
			return false;
		}
		
		if(!contains(newBook)) {
			return bookDao.updateBook(book, newBook);
		}
		
		return false;
	}

	public boolean delete(Book book) {
		if(book == null) {
			return false;
		}
		
		book.setDel("Y");
		
		if(!bookDao.deleteBook(book)) {
			book.setDel("N");
			return false;
		}
		
		return true;
	}
	

	public boolean rentBook(Book book) {
		if(book == null) {
			return false;
		}
		if(!bookDao.updateStateY(book)) {
			return false;
		}
		
		return true;
	}
	
	public boolean returnBook(Book book) {
		if(book == null) {
			return false;
		}
		if(!bookDao.updateStateN(book)) {
			return false;
		}
		
		return true;
	}

	public Book getBook(String code) {
		Book book = new Book(code, "", "", "");
		
		return book;
	}

	public void addBookSample() {
		
		List<Book> list = bookDao.selectBookList();
		
		if(list == null || list.size() == 0) {
			addBookSample();
		}
		
	}
	
	
}