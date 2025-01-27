package Library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryProgram implements ConsoleProgram {
	//프로그램
	
	static Scanner scan = new Scanner(System.in);
	
	@Override
	public void run() {
		int menu = 0;
		do {
			
			printMenu();
			
			try {
				
				menu = scan.nextInt();
				scan.nextLine();
				
				runMenu(menu);
				
			} catch (InputMismatchException e) {
				System.out.println("올바른 입력이 아닙니다.");
				scan.nextLine();
			}
			
		} while(menu != 3);
	}

	@Override
	public void printMenu() {
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		System.out.print("메뉴 입력 : ");
	}

	@Override
	public void runMenu(int menu) {
		switch(menu) {
		case 1:
			login();
			break;
		case 2:
			join();
			break;
		case 3:
			System.out.println("[프로그램을 종료합니다.]");
			break;
		default:
		}
	}

	private void login() {
		
		//아이디 비밀번호 입력
		//회원매니저에 일치하는 회원 정보가 있는지 확인
		//일치하는 회원O && 어드민계정
		runAdmin();
		
		//일치하는 회원O && 어드민계정X
		runMember();
		
	}

	private void runAdmin() {
		
		//어드민 메뉴 노출
		//회원 관리
		//도서 관리
		//로그아웃
	}

	private void runMember() {
		
		//회원 메뉴 노출
		//회원 탈퇴
		//도서 검색
		//도서 대여
		//도서 반납
		//로그아웃
	}

	private void join() {
		
		//아이디, 비밀번호, 이름, 번호 입력
		//회원 매니저에 중복 여부 확인 요청 (아이디) 및 등록
		//확인 결과에 따른 알림
	}

}
