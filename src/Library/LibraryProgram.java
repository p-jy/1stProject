package Library;

public class LibraryProgram implements ConsoleProgram {
	//프로그램
	/* 1. 로그인
	   ㄴ 아이디, 비밀번호 입력하여 일치하면 로그인
		- 어드민 계정인 경우 어드민 화면으로 이동
		- 일반 회원 계정인 경우 회원 화면으로 이동 */
		
	/* 2. 회원가입 (regex 참고)
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
		    - 중복 가능 */
	
	@Override
	public void run() {
		
	}

	@Override
	public void printMenu() {
		System.out.println("-----------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("-----------");
	}

	@Override
	public void runMenu(int menu) {
		System.out.println("-----------");
		switch(menu) {
		case 1:
			login();
			break;
		case 2:
			join();
			break;
		default:
			System.out.println("[올바른 메뉴가 아닙니다.]");
		}
	}

	private void login() {
		/* ㅡ 로그인
	   		ㄴ 아이디, 비밀번호 입력하여 일치하면 로그인
			- 어드민 계정인 경우 어드민 화면으로 이동
		 	- 일반 회원 계정인 경우 회원 화면으로 이동 */
		//아이디 입력 받기
		System.out.println("--------------");
		System.out.println("아이디를 입력하세요.");
		System.out.println("--------------");
		
		//비밀번호 입력 받기
		System.out.println("---------------");
		System.out.println("비밀번호를 입력하세요.");
		System.out.println("---------------");
		
		//Id가 admin -> 어드민 화면
		//나머지 -> 회원 화면
	}

	private void join() {
	 /* - 아이디
	    - 영어 + 숫자 + 특수문자(`_`) 6~12 ⇒ 첫 글자 무조건 영어
	    - 중복 불가 */
		System.out.println("--------------");
		System.out.println("아이디를 입력하세요.");
		System.out.println("--------------");
		Id id = 
				
		//리스트에 있는지 확인해서 없으면 알림 후 종료 => indexOf
		if(MemberManager.getId(id) == null ){
			System.out.println("사용 가능한 아이디 입니다.");
			return;
		}
		//중복
			System.out.println("이미 사용중인 아이디 입니다.");
		
	 /* - 비밀번호 (regex101 참고)
	    - 영어 + 숫자 + 특수문자 (`!` `@` `#`) 8~16 ⇒ 첫 글자 제한 없음
	    - 중복 가능 */
		System.out.println("---------------");
		System.out.println("비밀번호를 입력하세요.");
		System.out.println("---------------");
		Pw pw = 
				
		//이름(중복 가능)
		System.out.println("-------------");
		System.out.println("이름을 입력하세요.");
		System.out.println("-------------");
		Name name =
				
	/*- 번호 (regex101 참고)
	    - 010-nnnn-nnnn 형식만 가능.
	    - 중복 가능 */
				
	    System.out.println("-------------");
		System.out.println("번호를 입력하세요.");
		System.out.println("-------------");
	    Num num =
		 
	}

}











