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
	
	public boolean insertMember(Member member) {
		if(member == null) {
			return false;
		}
		//중복 확인
		//DB에서 member를 이용하여 회원 정보를 가져옴
		Member dbMem = memberDao.selectMember(member);
		System.out.println("DB에서 가져온 정보 : " + dbMem);
		
		//DB에서 가져온 회원 정보가 있으면 중복 -> false 반환
		if(dbMem != null) {
			return false;
		}
		//중복x -> 추가
		return memberDao.insertMember(member);
	}
	
	

	public boolean checkId(String id, String pw) {
		Member member = new Member(id, pw, "", "");
		
		int index = members.indexOf(member);
		
		if(index < 0 || index >= members.size()) {
			return false;
		}
		
		if(!members.get(index).getPw().equals(pw)) {
			return false;
		}
		
		return true;
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

	public boolean update(Member member, Member memberObj) {
		if(member == null || memberObj == null) {
			return false;
		}
		
		member.update(memberObj);
		
		return true;
	}

	public boolean delete(Member member) {
		
		return members.remove(member);
	}

	public void cancelMembership(String id, String pw) {
		Member member = new Member(id, pw, "", "");
		
		
	}

	public Member getMember(String id, String pw) {
		Member member = new Member(id, pw, "", "");
		
		Member user = getMember(members, member);
		
		return user;
	}

	private Member getMember(List<Member> members2, Member member) {
		if(members == null || members.isEmpty()) {
			return null;
		} if(member == null) {
			return null;
		}
		int index = members.indexOf(member);
		
		if(index < 0) {
			return null;
		}
		
		Member user = members.get(index);
		
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