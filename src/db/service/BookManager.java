package db.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.dao.BookDAO;
import lombok.Data;

@Data
public class BookManager {
	
	private BookDAO bookDao;
	
	public BookManager() {
		String resource = "db/config/mybatis-config.xml";
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
	
	
}