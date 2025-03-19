package db2.ex1.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db2.ex1.dao.BookDAO;
import db2.ex1.dao.MemberDAO;
import db2.ex1.dao.RentDAO;
import db2.ex1.model.vo.Book;
import db2.ex1.model.vo.Member;
import db2.ex1.model.vo.Rent;
import lombok.Data;

@Data
public class MemberManager {
	
	private List<Member> members;
	
	private MemberDAO memberDao;
	private BookDAO bookDao;
	private RentDAO rentDao;
	
	public MemberManager() {
		String resource = "db2/ex1/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			memberDao = session.getMapper(MemberDAO.class);
			bookDao = session.getMapper(BookDAO.class);
			rentDao = session.getMapper(RentDAO.class);
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
	
	public void print() {
		
		List<Member> list = memberDao.selectMemberList();
		
		if(list == null || list.size() == 0) {
			System.out.println("[등록된 회원이 없습니다.]");
			return;
		}
		for(Member member : list) {
			System.out.println(member);
		}
		
	}

	public boolean insertMember(Member member) {
		if(member == null) {
			return false;
		}
		
		//중복 확인
		if(contains(member)) {
			return false;
		}
		
		//중복x -> 추가
		return memberDao.insertMember(member);
	}

	public boolean update(Member selMem, Member newMem) {
		if(selMem == null || newMem == null) {
			return false;
		}
		
		//id가 같은 경우 이름, 번호 수정
		if(selMem.equals(newMem)) {
			return memberDao.updateMember(newMem, newMem);
		}
		//id가 다른 경우
		if(contains(newMem)) {
			return false;
		}
		return memberDao.updateMember(selMem, newMem);
		
	}

	public boolean delete(Member member) {
		if(member == null) {
			return false;
		}
		
		member.setDel("Y");
		member.setCanRent("N");
		if(!memberDao.deleteMember(member)) {
			member.setDel("N");
			member.setCanRent("Y");
			return false;
		}
		
		return true;
	}

	public boolean deleteByAdmin(Member member) {
		if(member == null) {
			return false;
		}
		
		member.setDel("Y");
		member.setCanRent("N");
		if(!memberDao.deleteMemberByAdmin(member)) {
			member.setDel("N");
			member.setCanRent("Y");
			return false;
		}
		
		return true;
	}

	public Member getMember(String id, String pw) {
		Member member = new Member(id, pw, "", "");
		
		Member user = getMember(member);
		
		if(user == null) {
			return null;
		}
		
		if(!user.getPw().equals(pw)) {
			return null;
		}
		
		return user;
	}

	public Member getMember(Member member) {
		if(member == null) {
			return null;
		}
		
		if(!contains(member)) {
			return null;
		}
		
		Member user = member;
		
		return user;
	}
	
	public boolean checkAdmin(Member user) {
		return user != null && "admin".equals(user.getId());
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

	private int getRentNum(Member member, Rent rent) {
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

	public int countRent(Member member) {
		if(member == null) {
			return 0;
		}
		
		List<Rent> list = rentDao.selectRentList(member.getId());
		
		return list.size();
	}

	public void setCantRent(Member member) {
		if(member == null) {
			return;
		}
		
		member.setCanRent("N");
		
	}
	
	public void setCanRent(Member member) {
		if(member == null) {
			return;
		}
		
		member.setCanRent("Y");
	}
	
	
	//반납 예정일을 확인하여 지난 경우 false, 지나지않은 경우 true를 반환
	//지난 경우 대여불가기간을 합산하여 me_no_rent에 저장
	public boolean checkDueDate(Member member) {
		
		if(member == null) {
			return false;
		}
		
		long diff = 0L;
		long re = 0L;
		int noRent = 0;
		
		Date now = new Date();
		
		List<Rent> list = rentDao.selectRentList(member.getId());
		
		for(Rent rent : list) {
			if(rent.getDueDate() == null) {
				return false;
			}
			diff = rent.getDueDate().getTime() - now.getTime();
			if(diff < 0) {
				member.setCanRent("N");
				re = diff / (3600 * 24 * 1000L);
				noRent = (int) (member.getNoRent() - re);
			}
		}
		member.setNoRent(noRent);
		memberDao.updateNoRent(member);
		
		return member.getCanRent().equals("N") ? false : true;
	}

	public void printRentList(Member member) {
		
		if(member == null) {
			return;
		}
		
		List<Rent> list = rentDao.selectRentList(member.getId());
		System.out.println("==========================================================================");
		System.out.println(" 도서코드" + "\t\t도서명\t 저자\t출판사" + "\t대여\t  대여일\t      반납예정일");
		System.out.println("--------------------------------------------------------------------------");
		for(Rent rt : list) {
			System.out.println(rt);
		}
		System.out.println("==========================================================================");
		
	}

	public String getPw(String id) {
		
		Member member = memberDao.selectMemberByID(id);
		String pw = member.getPw();
		return pw;
	}

	
}