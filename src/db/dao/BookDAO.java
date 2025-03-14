package db.dao;

import org.apache.ibatis.annotations.Param;

import db.model.vo.Book;

public interface BookDAO {

	Book selectBook(@Param("book")Book book);

}
