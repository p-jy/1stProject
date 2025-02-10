package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberManager {
	
	/* 
	 * 1. 로그인
	   ㄴ 아이디, 비밀번호 입력하여 일치하면 로그인
		- 어드민 계정인 경우 어드민 화면으로 이동
		- 일반 회원 계정인 경우 회원 화면으로 이동 */
		
	/*
	   2. 회원가입 (regex 참고)
	   ㄴ 아이디, 비밀번호, 이름, 번호 입력받아 회원 등록
		- 아이디
		    - 영어 + 숫자 + 특수문자(`_`) 6~12 ⇒ 첫 글자 무조건 영어
		    - 중복 불가
		- 비밀번호
		    - 영어 + 숫자 + 특수문자 (`!` `@` `#`) 8~16 ⇒ 첫 글자 제한 없음
		    - 중복 가능
		- 이름
		    - 중복 가능
		- 번호
		    - 010-nnnn-nnnn 형식만 가능.
		    - 중복 가능
	 */
	
	//회원관리
	
	private List<Member> members;
	
	public boolean insertMember(Member member) {
		if(member == null || members == null) {
			return false;
		}
		
		if(members.contains(member)) {
			return false;
		}
		
		return members.add(member);
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

	public boolean checkAdmin(Member user) {
		if("admin".equals(user.getId())) {
			return true;
		}
		
		return false;
	}

	public void addAdmin() {
		members.add(new Member("admin", "admin", "관리자", "관리자"));
	}
}
