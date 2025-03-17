package db2.ex1.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db2.ex1.dao.MemberDAO;
import db2.ex1.model.vo.Book;
import db2.ex1.model.vo.Member;
import lombok.NonNull;


public class MemberManager {
	
	private List<Member> members;
	
	private MemberDAO memberDao;
	
	public MemberManager() {
		String resource = "db2/ex1/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
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
		
		//중복 확인
		if(contains(member)) {
			return false;
		}
		
		//중복x -> 추가
		return memberDao.insertMember(member);
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

	public Member getMember(String id, String pw) {
		Member member = new Member(id, pw, "", "");
		
		Member user = getMember(member);
		
		return user;
	}

	private Member getMember(Member member) {
		if(member == null) {
			return null;
		}
		
		if(!contains(member)) {
			return null;
		}
		
		Member user = member;
		
		if(user.getPw().equals(member.getPw())) {
			return user;
		}
		
		return null;
	}
	public List<Member> getMembers() {
	    return members;
	}

	public boolean checkAdmin(Member user) {
		return user != null && "admin".equals(user.getId());
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
	
}