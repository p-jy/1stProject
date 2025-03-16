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
import db2.ex1.model.vo.Member;


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
	
	public List<Member> getMemberList(Member member) {
		if(member.getName() != null) {
			String name = member.getName();
			
			return members.stream()
					.filter(m-> m.getName().contains(name))
					.collect(Collectors.toList());
			
		} else if(member.getNum() != null) {
			String num = member.getNum();
			
			return members.stream()
					.filter(m-> m.getNum().contains(num))
					.collect(Collectors.toList());
			
		} else if(member.getId() != null) {
			String publisher = member.getId();
			
			return members.stream()
					.filter(m-> m.getId().contains(publisher))
					.collect(Collectors.toList());
		}
		
		System.out.println("[일치하는 회원 정보가 없습니다.]");
		return null;
	}

	public void print(List<Member> tmpList, boolean isMember) {
		
		if(tmpList == null || tmpList.isEmpty()) {
			System.out.println("[일치하는 회원 정보가 없습니다.]");
			return;
		}
		
		for(int i = 0; i < tmpList.size(); i++) {
			if(isMember == true) {
				System.out.print(i+1 + ". ");
			}
			System.out.println(tmpList.get(i));
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
		return memberDao.deleteMember(member);
	}

	public void cancelMembership(String id, String pw) {
		Member member = new Member(id, pw, "", "");
		
		
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
		
	
	

	public void addAdmin() {
		boolean exists = members.stream().anyMatch(m -> "admin".equals(m.getId()));
		if(!exists) {
			members.add(new Member("admin", "admin", "관리자", "관리자"));
		}
	}

	
}