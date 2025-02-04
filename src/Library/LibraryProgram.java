package Library;

import java.util.Scanner;

public class LibraryProgram implements ConsoleProgram {
	Scanner scan = new Scanner(System.in);
	//프로그램
	/* 1. 로그인
	   ㄴ 아이디, 비밀번호 입력하여 일치하면 로그인
		- 어드민 계정인 경우 어드민 화면으로 이동
		- 일반 회원 계정인 경우 회원 화면으로 이동 */
		
	/* 2. 회원가입
	   ㄴ 아이디, 비밀번호, 이름, 번호 입력받아 회원 등록
		- 아이디
		    - 영어 + 숫자 ⇒ 첫 글자 무조건 영어
		    - 중복 불가
		- 비밀번호
		    - 영어 + 숫자 + 특수문자 (`!` `@` `#`) 8~16 ⇒ 첫 글자 제한 없음
		    - 중복 가능
		- 이름
		    - 중복 가능
		- 번호
		    - 010-nnnn-nnnn 형식만 가능.
		    - 중복 가능 */
	
	/* 구현해야 할 것
	 * 1. 일반 회원 접속시 1.회원 탈퇴
	 * 				  2.도서 검색
	 * 				  3.도서 대여
	 * 				  4.로그아웃
	 * 
	 * 2. admin 접속시  1.회원 관리
	 * 				  2.도서 관리
	 * 				  3.로그아웃  
	 */
	
	@Override
	public void run() {
		while(true) {
			
			printMenu();
		
			int menu = scan.nextInt();
			scan.nextLine();
			
			runMenu(menu);
			
			if(menu == 3) {
				break;
			}
		}
	}

	@Override
	public void printMenu() {
		System.out.println("-----------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
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
		case 3:
			System.out.println("프로그램 종료.");
			break;
		default:
			System.out.println("[올바른 메뉴가 아닙니다.]");
		}
	}

	private void login() {
		/*  - 아이디, 비밀번호 입력하여 일치하면 로그인
			- 어드민 계정인 경우 어드민 화면으로 이동
		 	- 일반 회원 계정인 경우 회원 화면으로 이동 */
		//아이디 입력 받기
		System.out.println("--------------");
		System.out.println("아이디를 입력하세요.");
		System.out.println("--------------");
		String id = scan.nextLine();
		
		//비밀번호 입력 받기
		System.out.println("---------------");
		System.out.println("비밀번호를 입력하세요.");
		System.out.println("---------------");
		String pw = scan.nextLine();
		
		//회원 정보 확인
	    Member member = MemberManager.getId(id);
	    
		//Id가 admin -> 어드민 화면
	    if (member != null && member.getPw().equals(pw)) {
	    	if(id.equals("admin")) {
	    		System.out.println("관리자 메뉴로 이동합니다.");
	    		runAdmin();
	    	}else {
	    		System.out.println("사용자 메뉴로 이동합니다.");
	    		runUser();
	    	}
	    }else {
	    	System.out.println("아이디나 비밀번호가 일치하지 않습니다.");
	    }
	}

	private void runAdmin() {
		while(true) {
			
			printAdminMenu();
			int menu = scan.nextInt();
			scan.nextLine();
			
			runAdminMenu(menu);
			
			if(menu == 3) {
				break;
			}
		}
	}

	private void runUser() {
		while(true) {
			
			printUserMenu();
			int menu = scan.nextInt();
			scan.nextLine();
			
			runUserMenu(menu);
			
			if(menu == 3) {
				break;
			}
		}
	}

	private void printAdminMenu() {
		System.out.println("---------------");
		System.out.println("1. 회원 관리");
		System.out.println("2. 도서 관리");
		System.out.println("3. 로그아웃 ");
		System.out.println("---------------");
	}

	private void printUserMenu() {
		System.out.println("---------------");
		System.out.println("1. 회원 탈퇴");
		System.out.println("2. 도서 검색");
		System.out.println("3. 도서 대여");
		System.out.println("4. 로그아웃 ");
	}

	private void runUserMenu(int menu) {
		System.out.println("---------------");
		switch(menu) {
		case 1:
			deleteUser();
			break;
		case 2:
			bookSearch();
			break;
		case 3:
			//????(); 로그아웃 구현 필요
			break;
		default:
			System.out.println("[잘못된 메뉴입니다.]");
		}
		
	}

	private void deleteUser() {
		// TODO Auto-generated method stub
		
	}

	private void bookSearch() {
		// TODO Auto-generated method stub
		
	}

	private void runAdminMenu(int menu) {
		System.out.println("------------------");
		switch(menu) {
		case 1:
			userManager();
			break;
		case 2:
			bookManager();
			break;
		case 3:
			//????(); 로그아웃 구현 필요
			break;
		default:
			System.out.println("[잘못된 메뉴입니다.]");
		}
		
	}

	private void userManager() {
		//회원 탈퇴 구현 필요
		
	}

	private void bookManager() {
		// TODO Auto-generated method stub
		
	}

	private void join() {
		String idPattern ="^[a-zA-Z][a-zA-Z0-9]{5,11}$"; //첫 글자 영어, 6~12자 
		System.out.println("--------------");
		System.out.println("아이디를 입력하세요.(6~12자)");
		System.out.println("--------------");
		String id = scan.nextLine(); 
				
		//리스트에 있는지 확인해서 없으면 알림 후 종료 => indexOf
		if(MemberManager.getId(id) != null ){
			System.out.println("이미 사용중인 아이디 입니다.");
			return;
		}
		
		if(!id.matches(idPattern)) {
			System.out.println("아이디의 첫 글자는 영어, 6~12글자로 작성해주세요.");
			return;
		}
		//"사용 가능한 아이디입니다."(넣을지 말지 고민)
			
		//8~16자, 영어, 숫자, 특수문자 조합
		String pwPattern = "^[a-zA-Z0-9!@#]{8,16}$";
		System.out.println("---------------");
		System.out.println("비밀번호를 입력하세요.(8~16자) 영어,숫자,!@# 가능");
		System.out.println("---------------");
		String pw = scan.nextLine();
		
		if(!pw.matches(pwPattern)) {
			System.out.println("비밀번호는 영어, 숫자, !@#만 사용 가능합니다. (8~16자)");
		}
				
		//이름(중복 가능)
		System.out.println("-------------");
		System.out.println("이름을 입력하세요.");
		System.out.println("-------------");
		String name = scan.nextLine();
		
		//전화번호 (010-nnnn-nnnn)
		 String numPattern = "^010-[0-9]{4}-[0-9]{4}$";
	    System.out.println("-------------");
		System.out.println("번호를 입력하세요.(010-xxxx-xxxx)");
		System.out.println("-------------");
	    String num = scan.nextLine();
	    
	    if(!num.matches(numPattern)) {
	    	System.out.println("010-xxxx-xxxx 형식으로 입력해주세요.");
	    	return;
	    }
	    MemberManager.addMember(id, pw, name, num);
	}

}