package db.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db.dao.BookDAO;
import db.dao.MemberDAO;
import db.dao.RentDAO;
import db.model.vo.Book;
import db.model.vo.Member;
import db.model.vo.Rent;
import lombok.Data;

@Data
public class MemberManager {
	private List<Member> list;
	
	private BookDAO bookDao;
	private MemberDAO memberDao;
	private RentDAO rentDao;
	
	public MemberManager() {
		String resource = "db/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			bookDao = session.getMapper(BookDAO.class);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contains(Member member) {
		Member dbMem = memberDao.selectMember(member);
		
		if(dbMem != null) {
			return true;
		}
		
		return false;
	}
	
	public boolean insertMember(Member member) {
		if(member == null) {
			return false;
		}
		
		if(contains(member)) {
			return false;
		}
		
		return memberDao.insertStudent(member);
	}
	
	public Member getMember(Member member) {
		return contains(member) ? member : null;
	}
	
	public boolean updateMember(Member selMember, Member newMember) {
		if(selMember == null || newMember == null) {
			return false;
		}
		
		if(selMember.equals(newMember)) {
			return memberDao.updateMember(newMember, newMember);
		}
		
		if(contains(newMember)) {
			return false;
		}
		
		return memberDao.updateMember(selMember, newMember);
	}
	
	public boolean deleteMember(Member member) {
		if(member == null) {
			return false;
		}
		
		return memberDao.deleteMember(member);
	}
	
	public void printMember(Member member) {
		if(member == null) {
			System.out.println("회원 정보가 없습니다.");
			return;
		}
		
		Member tmp = memberDao.selectMember(member);
		if(tmp == null) {
			System.out.println("일치하는 회원 정보가 없습니다.");
			return;
		}
		
		tmp.print();
	}
	
	public int getRentNum(Member member, Rent rent) {
		if(member == null || rent == null || rent.getBook() == null) {
			return -1;
		}
		
		Member dbMem = memberDao.selectMember(member);
		if(dbMem == null) {
			return -1;
		}
		
		Book dbBook = bookDao.selectBook(rent.getBook());
		if(dbBook == null) {
			return -1;
		}
		
		rent.setId(dbMem.getId());
		rent.getBook().setCode(dbBook.getCode());
		Rent dbRent = rentDao.selectRent(rent);
		
		return dbRent != null ? dbRent.getNum() : -1;
	}
	
	public boolean rentBook(Member member, Rent rent) {
		if(member == null || rent == null) {
			return false;
		}
		
		if(getRentNum(member, rent) != -1) {
			return false;
		}
		
		return rentDao.rentBook(rent);
	}
	
	public boolean returnBook(Member member, Book book) {
		if(member == null || book == null) {
			return false;
		}
		
		Member dbMem = memberDao.selectMember(member);
		if(dbMem == null) {
			return false;
		}
		
		Book dbBook = bookDao.selectBook(book);
		if(dbBook == null) {
			return false;
		}
		
		return rentDao.returnBook(dbMem.getId(), dbBook.getCode());
	}
	
	public void printRent(Member member, Book book) {
		if(member == null || book == null) {
			System.out.println("출력할 수 없습니다.");
			return;
		}
		member = memberDao.selectMember(member);
		if(member == null) {
			System.out.println("일치하는 회원 정보가 없습니다.");
			return;
		}
		book = bookDao.selectBook(book);
		if(book == null) {
			System.out.println("일치하는 도서 정보가 없습니다.");
			return;
		}
		Rent tmp = new Rent(new Book("", "", "", ""), "");
		tmp.setId(member.getId());
		tmp.getBook().setCode(book.getCode());
		
		Rent rent = rentDao.selectRent(tmp);
		if(rent == null) {
			System.out.println("대여중인 도서가 없습니다.");
			return;
		}
		
		System.out.println(member.getId() + " " + rent);
	}

	public boolean checkAdmin(Member user) {
		return user != null && "admin".equals(user.getId());
	}
	
	
}
