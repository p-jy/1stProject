package Library;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class LibraryProgram implements ConsoleProgram {
	//프로그램
	
	private Scanner scan = new Scanner(System.in);
	
	private BookManager bm = new BookManager();
	
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
				System.out.println("[올바른 입력이 아닙니다.]");
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
		
		bm.registBook(book);
		
	}

	private Book inputBook() {
		System.out.print("카테고리 : ");
		String category = scan.next();
		scan.nextLine();
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("작가명 : ");
		String author = scan.nextLine();
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		
		String codePrefix = Book.getCodePrefix(category);
		int count = bm.getLastNum(codePrefix);
		
		return new Book(count, category, title, author, publisher);
	}

	private void updateBook() {
		int menu;
		do {
			
			printSearchMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runUpdateMenu(menu);
			
			
		} while(menu != 5);
	}

	private void runUpdateMenu(int menu) {
		
		List<Book> tmpList;
		int index;
		
		switch(menu) {
		case 1:
			System.out.print("도서명 : ");
			String title = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", title, "", ""));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			update(index, tmpList);
			
			break;
		case 2:
			System.out.print("작가명 : ");
			String author = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", author, ""));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			update(index, tmpList);
			
			break;
		case 3:
			System.out.print("출판사 : ");
			String publisher = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", "", publisher));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			update(index, tmpList);
			
			break;
		case 4:
			System.out.print("도서코드 : ");
			String bookCode = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book(bookCode, "", "", ""));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			update(index, tmpList);
			
			break;
		case 5:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
			System.out.println("[잘못된 입력입니다.]");
		}
		
	}

	private void update(int index, List<Book> tmpList) {
		if(index < 0 || index >= tmpList.size()) {
			System.out.println("[잘못 선택했습니다.]");
			return;
		}
		
		Book bookObj = inputBook();
		
		if(bm.update(tmpList.get(index), bookObj)) {
			System.out.println("[도서를 수정했습니다.]");
		} else {
			System.out.println("[도서를 수정하지 못했습니다.]");
		}
	}

	private void deleteBook() {
		int menu;
		do {
			
			printSearchMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runDeleteMenu(menu);
			
			
		} while(menu != 5);
	}

	private void runDeleteMenu(int menu) {
		
		List<Book> tmpList;
		int index;
		
		switch(menu) {
		case 1:
			System.out.print("도서명 : ");
			String title = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", title, "", ""));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			delete(index, tmpList);
			
			break;
		case 2:
			System.out.print("작가명 : ");
			String author = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", author, ""));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			delete(index, tmpList);
			
			break;
		case 3:
			System.out.print("출판사 : ");
			String publisher = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", "", publisher));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			delete(index, tmpList);
			
			break;
		case 4:
			System.out.print("도서코드 : ");
			String bookCode = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book(bookCode, "", "", ""));
			
			search(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			delete(index, tmpList);
			
			break;
		case 5:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
			System.out.println("[잘못된 입력입니다.]");
		}
		
	}

	private void delete(int index, List<Book> tmpList) {
		if(bm.delete(tmpList.get(index))) {
			System.out.println("[도서를 삭제했습니다.]");
		} else {
			System.out.println("[도서를 삭제하지 못했습니다.]");
		}
	}

	private void searchBook() {
		int menu;
		do {
			
			printSearchMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runSearchMenu(menu);
			
			
		} while(menu != 5);
		
	}

	private void printSearchMenu() {
		System.out.println("-------------------");
		System.out.println("도서 검색");
		System.out.println("1. 도서명으로 검색");
		System.out.println("2. 작가명으로 검색");
		System.out.println("3. 출판사로 검색");
		System.out.println("4. 도서 번호로 검색");
		System.out.println("5. 이전으로");
		System.out.println("-------------------");
		System.out.println("메뉴 입력 : ");
	}

	private void runSearchMenu(int menu) {
		List<Book> tmpList;
		
		switch(menu) {
		case 1:
			System.out.print("도서명 : ");
			String title = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", title, "", ""));
			
			search(tmpList, false);
			break;
		case 2:
			System.out.print("작가명 : ");
			String author = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", author, ""));
			
			search(tmpList, false);
			break;
		case 3:
			System.out.print("출판사 : ");
			String publisher = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", "", publisher));
			
			search(tmpList, false);
			break;
		case 4:
			System.out.print("도서코드 : ");
			String bookCode = scan.next();
			scan.nextLine();
			
			tmpList = bm.getBookList(new Book(bookCode, "", "", ""));
			
			search(tmpList, false);
			break;
		case 5:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
			System.out.println("[잘못된 입력입니다.]");
		}
	}

	private void search(List<Book> tmpList, boolean isBook) {
		
		
		if(tmpList.isEmpty()) {
			System.out.println("[일치하는 도서가 없습니다.]");
			return;
		}
		
		bm.print(tmpList, isBook);
		
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
