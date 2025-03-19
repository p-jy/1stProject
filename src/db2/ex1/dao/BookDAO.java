package db2.ex1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db2.ex1.model.vo.Book;
import lombok.NonNull;

public interface BookDAO {

	Book selectBook(@Param("book")Book book);

	boolean insertBook(@Param("book")Book book);

	boolean updateBook(@Param("old")Book book, @Param("new")Book newBook);

	boolean deleteBook(@Param("book")Book book);

	List<Book> selectBookList();

	boolean updateState(@Param("book")Book book);

	boolean returnBookState(@Param("book") Book book);
}