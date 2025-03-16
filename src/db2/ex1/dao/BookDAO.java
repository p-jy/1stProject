package db2.ex1.dao;

import org.apache.ibatis.annotations.Param;

import db2.ex1.model.vo.Book;

public interface BookDAO {

	Book selectBook(@Param("book")Book book);

	boolean insertBook(@Param("book")Book book);



}

