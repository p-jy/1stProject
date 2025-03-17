package db3.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db3.dao.BookDAO;
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
	
//	public void addSampleBookData() {
//		registBook(new Book(getLastNum("000"), "총류", "한국의 꽃살·기둥·누각", "임석재", "이화여자대학교출판부"));
//		registBook(new Book(getLastNum("100"), "철학", "장자의 철학", "강신주", "태학사"));
//		registBook(new Book(getLastNum("200"), "종교", "저항하는 그리스도인", "강성호", "복 있는사람"));
//		registBook(new Book(getLastNum("300"), "사회과학", "수익 분배의 경제학", "오정석", "삼성경제연구소"));
//		registBook(new Book(getLastNum("400"), "자연과학", "역발상의 과학 : 더하고 빼고 뒤집으면 답이 보인다", "김준래", "오엘북스"));
//		registBook(new Book(getLastNum("500"), "기술과학", "이유석의 이유식", "이유석", "BR미디어"));
//		registBook(new Book(getLastNum("600"), "예술", "프로처럼 사진 찍기", "조승범", "청년정신"));
//		registBook(new Book(getLastNum("700"), "언어", "프랑스어동사활용사전", "서영하", "청록출판사"));
//		registBook(new Book(getLastNum("800"), "문학", "위로가 필요한 시간", "김경집", "조화로운삶"));
//		registBook(new Book(getLastNum("900"), "역사", "미술로 보는 우리 역사", "전국역사교사모임", "푸른나무"));
//		registBook(new Book(getLastNum("900"), "역사", "유배인과 배수첩들의 뒤안길", "전웅", "소나기"));
//	}
//	
	
}