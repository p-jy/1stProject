package Library;

import java.util.Map;
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
	
	/* 해야 될 것
	 * 회원 탈퇴 메뉴 admin 입력시 관리자는 탈퇴할 수 없습니다. 출력
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
		
		//존재하는 아이디인지 확인
		Member member = MemberManager.getId(id);
		if (member == null) {
			System.out.println("[아이디가 존재하지 않습니다.]");
			return;
		}
				
		//비밀번호 입력 받기
		System.out.println("---------------");
		System.out.println("비밀번호를 입력하세요.");
		System.out.println("---------------");
		String pw = scan.nextLine();
		
		//비밀번호 확인
		if (!member.getPw().equals(pw)) {
	        System.out.println("[비밀번호가 일치하지 않습니다.]");
	        return;
	    }
		
		//id가 admin -> 어드민 화면
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
			
			if(menu == 4) {
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
		System.out.println("---------------");
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
			bookRental();
			break;
		case 4:
			System.out.println("[이전으로 돌아갑니다.]");
			return;
		default:
			System.out.println("[잘못된 메뉴입니다.]");
		}
		
	}

	private void deleteUser() {
		System.out.println("[삭제할 아이디를 입력하세요: ]");
		String id = scan.nextLine();
		
		//존재하는 아이디인지 확인
		Member member = MemberManager.getId(id);
		if (member == null) {
	        System.out.println("[존재하지 않는 아이디입니다.]");
	        return;
		}
		
		System.out.println("[비밀번호를 입력하세요: ]");
		String pw = scan.nextLine();
		
		//비밀번호 일치 확인
		if (!member.getPw().equals(pw)) {
	        System.out.println("[비밀번호가 일치하지 않습니다.]");
	        return;
	    }
		
		System.out.println("[회원 탈퇴를 하시겠습니까?] y 입력시 탈퇴");
		String confirm = scan.nextLine();
		
		if(confirm.equalsIgnoreCase("y")) {
			MemberManager.removerMember(id);
			System.out.println("[회원 탈퇴가 완료되었습니다.]");
			run();
		} else {
			System.out.println("[회원 탈퇴가 취소되었습니다.]");
			return;
		}
	}

	private void bookSearch() {
		// TODO Auto-generated method stub
		
	}

	private void bookRental() {
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
			System.out.println("[이전으로 돌아갑니다.]");
			return;
		default:
			System.out.println("[잘못된 메뉴입니다.]");
		}
		
	}

	private void userManager() {
		System.out.println("----------------");
		int index = 1;
		
		for (Map.Entry<String, Member> entry : MemberManager.getMemberList().entrySet()) {
		    System.out.println(index + ". 아이디: " + entry.getKey() + ", 이름: " + entry.getValue().getName() + ", 핸드폰 번호: " + entry.getValue().getNum());
		    index++;
		}
		
	    //탈퇴할 아이디 입력 받기
	    System.out.println("[탈퇴할 회원의 아이디를 입력하세요.]");
	    String targetId = scan.nextLine();
	    
	    //존재하는 회원인지 확인
	    Member member = MemberManager.getId(targetId);
	    if (member == null) {
	        System.out.println("[존재하지 않는 아이디입니다.]");
	        return;
	    }
	    
	    //관리자 확인
	    if ("admin".equals(targetId)) {
	        System.out.println("[관리자는 탈퇴할 수 없습니다.]");
	        return;
	    }
	    
	    //탈퇴 확인
	    System.out.println("[정말 탈퇴를 시키겠습니까? y 입력시 탈퇴]");
	    String confirm = scan.nextLine();
	    
	    if (confirm.equalsIgnoreCase("y")) {
	        //회원 탈퇴 처리
	        MemberManager.removerMember(targetId);
	        System.out.println("[회원 탈퇴가 완료되었습니다.]");
	    } else {
	        System.out.println("[회원 탈퇴가 취소되었습니다.]");
		}
	}

	private void bookManager() {
		// TODO Auto-generated method stub
		
	}

	private void join() {
		String idPattern ="^[a-zA-Z][a-zA-Z0-9]{4,11}$"; //첫 글자 영어, 6~12자 //admin 5글자라 임시로 5글자로 변경 
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
		
		//전화번호 (010-nnnn-nnnn) =>010을 굳이 입력할 필요없이 nnnn-nnnn으로 입력하게끔 하기 고민중
		//String numPattern = "^010-[0-9]{4}-[0-9]{4}$";
		String numPattern = "[0-9]{4}-[0-9]{4}$";
	    System.out.println("-------------");
		System.out.println("번호를 입력하세요.(010-xxxx-xxxx) xxxx-xxxx만");
		System.out.println("-------------");
	    String num = scan.nextLine();
	    
	    if(!num.matches(numPattern)) {
	    	System.out.println("010-xxxx-xxxx 형식으로 입력해주세요.");
	    	return;
	    }
	    MemberManager.addMember(id, pw, name, num);
	}

}