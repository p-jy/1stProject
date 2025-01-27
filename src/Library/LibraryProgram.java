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
			signUp();
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
		
		//일치하는 회원X
		System.out.println("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
		
	}

	private void runAdmin() {
		int menu;
		
		do {
		//어드민 메뉴 노출
		printAdminMenu();
		menu = scan.nextInt();
		
		switch(menu) {
		case 1:
			manageMember();
			break;
		case 2:
			manageBook();
			break;
		case 3:
			System.out.println("로그아웃되었습니다.");
			System.out.println("메인 메뉴로 돌아갑니다.");
			break;
		default:
		}
		
		
		
		} while(menu != 3);
		
	}

	private void printAdminMenu() {
		System.out.println("1. 회원 관리");
		System.out.println("2. 도서 관리");
		System.out.println("3. 로그아웃");
		System.out.print("메뉴 입력 : ");
	}

	private void manageMember() {
		//회원 관리
		System.out.println("1. 회원 수정");
		System.out.println("2. 회원 삭제");
		System.out.println("3. 회원 조회");
		System.out.println("4. 이전 메뉴");
		System.out.print("메뉴 입력 : ");
		
		int menu = scan.nextInt();
		
		switch(menu) {
		case 1:
			updateMember();
			break;
		case 2:
			deleteMember();
			break;
		case 3:
			searchMember();
			break;
		case 4:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
		}
		
	}

	private void updateMember() {
		
	}

	private void deleteMember() {
		
	}

	private void searchMember() {
		
	}

	private void manageBook() {
		//도서 관리
		System.out.println("1. 도서 등록");
		System.out.println("2. 도서 수정");
		System.out.println("3. 도서 삭제");
		System.out.println("4. 도서 조회");
		System.out.println("5. 이전 메뉴");
		System.out.print("메뉴 입력 : ");
		
		int menu = scan.nextInt();
		
		switch(menu) {
		case 1:
			registBook();
			break;
		case 2:
			updateBook();
			break;
		case 3:
			deleteBook();
			break;
		case 4:
			searchBook();
			break;
		case 5:
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		default:
		}
		
		
	}

	private void registBook() {
		Book book = inputBook();
	}

	private Book inputBook() {
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("작가명 : ");
		String author = scan.nextLine();
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		
		return new Book(title, author, publisher);
	}

	private void updateBook() {
		
	}

	private void deleteBook() {
		
	}

	private void searchBook() {
		
	}

	private void runMember() {
		
		//회원 메뉴 노출
		printMemberMenu();
		int menu = scan.nextInt();
		
		runMemberMenu(menu);
		
	}

	private void runMemberMenu(int menu) {
		switch(menu) {
		case 1:
			deleteID();
			break;
		case 2:
			searchBook();
			break;
		case 3:
			rentBook();
			break;
		case 4:
			returnBook();
			break;
		case 5:
			System.out.println("로그아웃되었습니다.");
			System.out.println("메인 메뉴로 돌아갑니다.");
			break;
		default:
		}
	}

	private void deleteID() {
		
	}

	private void rentBook() {
		
	}

	private void returnBook() {
		
	}

	private void printMemberMenu() {
		System.out.println("1. 회원 탈퇴");
		System.out.println("2. 도서 검색");
		System.out.println("3. 도서 대여");
		System.out.println("4. 도서 반납");
		System.out.println("5. 로그아웃");
		System.out.print("메뉴 입력 : ");
		
	}

	private void signUp() {
		
		//아이디, 비밀번호, 이름, 번호 입력
		//회원 매니저에 중복 여부 확인 요청 (아이디) 및 등록
		//확인 결과에 따른 알림
		
	}

}
