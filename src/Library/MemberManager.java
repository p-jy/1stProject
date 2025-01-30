package Library;

import java.util.HashMap;
import java.util.Map;

public class MemberManager {

    private static Map<String, Member> memberList = new HashMap<>();

	public static void addMember(String id, String pw, String name, String num) {
		//아이디 중복 확인
		if(memberList.containsKey(id)) {
			System.out.println("이미 사용중인 아이디입니다.");
		} else {
			// 회원가입
			Member newMember = new Member(id, pw, name ,num);
			memberList.put(id, newMember);
			System.out.println("회원가입이 완료되었습니다");
		}
	}

	//아이디로 회원 찾기
	public static Member getId(String id) {
		return memberList.get(id);
	}


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
}
