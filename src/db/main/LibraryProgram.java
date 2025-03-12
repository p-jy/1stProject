package db.main;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Library.Book;
import Library.BookManager;
import Library.ConsoleProgram;
import Library.Member;
import Library.MemberManager;
import Library.RentReturn;

public class LibraryProgram implements ConsoleProgram {
	
	
	private Scanner scan = new Scanner(System.in);
	
	private BookManager bm = new BookManager();
	private MemberManager mm = new MemberManager();
	private Member user = null;
	Map<String, List<Book>> rentList = new HashMap<String, List<Book>>();
	private RentReturn rr = new RentReturn(rentList);
	private List<Member> members;
	private List<Book> books;
	private Map<String, List<Book>> rentals;
	
	@Override
	public void run() {
		String membersFileName = "src/db/Member.txt";
        String booksFileName = "src/db/Book.txt";
        String rentalsFileName = "src/db/RentReturn.txt";
        
       
        members = (List<Member>) load(membersFileName);
        books = (List<Book>) load(booksFileName);
        rentals = (Map<String, List<Book>>) load(rentalsFileName);
        
       
        if (members == null) {     
            mm.addAdmin();
        } 
        else {
        	mm.setMembers(members);
        }
        
        if (books == null) {
            bm.addSampleBookData();
        } 
        else {
        	bm.setBooks(books);
        }
        
        if (rentals != null) {
        	rentList = rentals;
        	rr = new RentReturn(rentList);
        } 
       
        
       
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
        
        
        save(membersFileName,mm.getMembers());
        save(booksFileName, bm.getBooks());
        save(rentalsFileName, rr.getRentReturnMap());
       
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
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		
		user = mm.getMember(id, pw);
		
		//회원매니저에 일치하는 회원 정보가 있는지 확인
		if(user == null) { //일치X
			System.out.println("[아이디 또는 비밀번호를 잘못 입력했습니다.]");
		} else if(mm.checkAdmin(user)) {
			//일치하는 회원O && 어드민계정
			runAdmin();
		} else {
			//일치하는 회원O && 어드민계정X
			runMember();
		}		
		
	}

	private void signUp() {
		
		//아이디, 비밀번호, 이름, 번호 입력
		Member member = inputMember();
		//회원 매니저에 중복 여부 확인 요청 (아이디) 및 등록
		//확인 결과에 따른 알림
		if(!mm.insertMember(member)) {
			System.out.println("[사용 불가능한 ID 입니다.]");
			return;
		}
		System.out.println("[회원 가입 완료]");
		
	}

	private void runAdmin() {
		int menu;
		
		do {
		//어드민 메뉴 노출
		printAdminMenu();
		menu = scan.nextInt();
		scan.nextLine();
		
		switch(menu) {
		case 1:
			manageMember();
			break;
		case 2:
			manageBook();
			break;
		case 3:
			System.out.println("[로그아웃]");
			System.out.println("[메인 메뉴로 돌아갑니다.]");
			break;
		default:
		}
		
		
		
		} while(menu != 3);
		
	}

	private void runMember() {
		int menu;
		
		do {
			if(user == null) {
				break;
			}
			//회원 메뉴 노출
			printMemberMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runMemberMenu(menu);
		} while(menu != 4);
		
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
			rentReturnBook();
			break;
		case 4:
			System.out.println("[로그아웃되었습니다.]");
			System.out.println("[메인 메뉴로 돌아갑니다.]");
			break;
		default:
		}
	}

	private void printAdminMenu() {
		System.out.println("1. 회원 관리");
		System.out.println("2. 도서 관리");
		System.out.println("3. 로그아웃");
		System.out.print("메뉴 입력 : ");
	}

	private void printMemberMenu() {
		System.out.println("1. 회원 탈퇴");
		System.out.println("2. 도서 검색");
		System.out.println("3. 대여 반납");
		System.out.println("4. 로그아웃");
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
		scan.nextLine();
		
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

	private void manageBook() {
		//도서 관리
		System.out.println("1. 도서 등록");
		System.out.println("2. 도서 수정");
		System.out.println("3. 도서 삭제");
		System.out.println("4. 도서 조회");
		System.out.println("5. 도서 목록");
		System.out.println("6. 이전 메뉴");
		System.out.print("메뉴 입력 : ");
		
		int menu = scan.nextInt();
		scan.nextLine();
		
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
			BookManager.listBook();
			break;
		case 6:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
		}
		
		
	}

	private void updateMember() {
		int menu;
		do {
			
			printSearchMemberMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runUpdateMemberMenu(menu);
			
			
		} while(menu != 4);
	}

	private void updateBook() {
		int menu;
		do {
			
			printSearchBookMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runUpdateBookMenu(menu);
			
			
		} while(menu != 5);
	}

	private void runUpdateMemberMenu(int menu) {
		List<Member> tmpList;
		int index;
		
		switch(menu) {
		case 1:
			System.out.print("이름 : ");
			String name = scan.next();
			scan.nextLine();
			
			tmpList = mm.getMemberList(new Member("", "", name, ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			updateMember(index, tmpList);
			break;
		case 2:
			System.out.print("번호 : ");
			String num = scan.next();
			scan.nextLine();
			
			tmpList = mm.getMemberList(new Member("", "", "", num));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			updateMember(index, tmpList);
			break;
		case 3:
			System.out.print("아이디 : ");
			String id = scan.next();
			scan.nextLine();
			
			tmpList = mm.getMemberList(new Member(id, "", "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			updateMember(index, tmpList);
			break;
		case 4:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
		}
	}

	private void runUpdateBookMenu(int menu) {
		
		List<Book> tmpList;
		int index;
		
		switch(menu) {
		case 1:
			System.out.print("도서명 : ");
			String title = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", title, "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			updateBook(index, tmpList);
			
			break;
		case 2:
			System.out.print("작가명 : ");
			String author = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", author, ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			updateBook(index, tmpList);
			
			break;
		case 3:
			System.out.print("출판사 : ");
			String publisher = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", "", publisher));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			updateBook(index, tmpList);
			
			break;
		case 4:
			System.out.print("도서코드 : ");
			String bookCode = scan.nextLine();
			
			tmpList = bm.getBookList(new Book(bookCode, "", "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			updateBook(index, tmpList);
			
			break;
		case 5:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
			System.out.println("[잘못된 입력입니다.]");
		}
		
	}

	private void updateMember(int index, List<Member> tmpList) {
		if(index < 0 || index >= tmpList.size()) {
			System.out.println("[잘못 선택했습니다.]");
			return;
		}
		
		Member memberObj = inputMemberBase();
		
		if(mm.update(tmpList.get(index), memberObj)) {
			System.out.println("[회원 정보를 수정했습니다.]");
		} else {
			System.out.println("[회원 정보를 수정하지 못했습니다.]");
		}
		
	}

	private void updateBook(int index, List<Book> tmpList) {
		if(index < 0 || index >= tmpList.size()) {
			System.out.println("[잘못 선택했습니다.]");
			return;
		}
		
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("작가명 : ");
		String author = scan.nextLine();
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		
		Book bookObj = new Book("", title, author, publisher);
		
		if(bm.update(tmpList.get(index), bookObj)) {
			System.out.println("[도서를 수정했습니다.]");
		} else {
			System.out.println("[도서를 수정하지 못했습니다.]");
		}
	}

	private Member inputMemberBase() {
		System.out.print("이름 : ");
		String name = scan.next();
		scan.nextLine();
		
		String numPattern = "[0-9]{4}-[0-9]{4}$"; //010-nnnn-nnnn
		String num = "";
		while (true) {
	        System.out.print("번호(010(생략) xxxx-xxxx) : ");
	        num = scan.nextLine();
	        if (num.matches(numPattern)) {
	            break;
	        } else {
	            System.out.println("[xxxx-xxxx(010제외) 형식으로 입력해주세요.]");
	        }
	    }
		
		Member member = new Member("", "", name, num);
		return member;
	}

	private Member inputMember() {
		String idPattern ="^[a-zA-Z][a-zA-Z0-9]{5,11}$"; //첫 글자 영어, 6~12자
		String id = "";
	    while (true) {
	        System.out.print("아이디 : ");
	        id = scan.nextLine();
	        if (id.matches(idPattern)) {
	            break;
	        } else {
	            System.out.println("[아이디는 첫 글자가 영어이고, 6~12자이어야 합니다.]");
	        }
	    }
		
		String pwPattern = "^[a-zA-Z0-9!@#]{8,16}$"; //영어, 숫자, !@#, 8~16자
		String pw = "";
	    while (true) {
	        System.out.print("비밀번호 : ");
	        pw = scan.nextLine();
	        if (pw.matches(pwPattern)) {
	            break;
	        } else {
	            System.out.println("[비밀번호는 영어, 숫자, !@#만, 8~16자여야 합니다.]");
	        }
	    }
		
		Member member = inputMemberBase();
		return new Member(id, pw, member.getName(), member.getNum());
	}

	private Book inputBookBase() {
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("작가명 : ");
		String author = scan.nextLine();
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		
		return new Book("", title, author, publisher);
	}

	private Book inputBook() {
		System.out.print("카테고리 : ");
		String category = scan.next();
		scan.nextLine();
		Book book = inputBookBase();
		
		String codePrefix;
		try {
			codePrefix = Book.getCodePrefix(category);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		int count = bm.getLastNum(codePrefix);
		
		return new Book(count, category, book.getTitle(), book.getAuthor(), book.getPublisher());
	}

	private void registBook() {
		Book book = inputBook();
		
		bm.registBook(book);
		
	}

	private void deleteMember() {
		int menu;
		
		do {
			printSearchMemberMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runDeleteMemberMenu(menu);
			
		} while (menu != 4);
	}

	private void deleteID() {
		System.out.print("아이디 : ");
		String id = scan.next();
		scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		
		Member member = mm.getMember(id, pw);
		
		if(mm.delete(member)) {
			System.out.println("[회원 탈퇴 완료]");
			user = null;
		} else {
			System.out.println("[회원 탈퇴 실패]");
		}
		
		
	}

	private void deleteBook() {
		int menu;
		do {
			
			printSearchBookMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runDeleteBookMenu(menu);
			
			
		} while(menu != 5);
	}

	private void runDeleteMemberMenu(int menu) {
		List<Member> tmpList;
		int index;
		
		switch(menu) {
		case 1:
			System.out.print("이름 : ");
			String name = scan.next();
			scan.nextLine();
			
			tmpList = mm.getMemberList(new Member("", "", name, ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			deleteMember(index, tmpList);
			
			break;
		case 2:
			System.out.print("번호 : ");
			String num = scan.next();
			scan.nextLine();
			
			tmpList = mm.getMemberList(new Member("", "", "", num));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			deleteMember(index, tmpList);
			break;
		case 3:
			System.out.print("아이디 : ");
			String id = scan.next();
			scan.nextLine();
			
			tmpList = mm.getMemberList(new Member(id, "", "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, true);

			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			deleteMember(index, tmpList);
			break;
		case 4:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
		}
	}

	private void runDeleteBookMenu(int menu) {
		
		List<Book> tmpList;
		int index;
		
		switch(menu) {
		case 1:
			System.out.print("도서명 : ");
			String title = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", title, "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			deleteBook(index, tmpList);
			
			break;
		case 2:
			System.out.print("작가명 : ");
			String author = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", author, ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			deleteBook(index, tmpList);
			
			break;
		case 3:
			System.out.print("출판사 : ");
			String publisher = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", "", publisher));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			deleteBook(index, tmpList);
			
			break;
		case 4:
			System.out.print("도서코드 : ");
			String bookCode = scan.nextLine();
			
			tmpList = bm.getBookList(new Book(bookCode, "", "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, true);
	
			System.out.print("선택 : ");
			index = scan.nextInt() - 1;
			scan.nextLine();
			
			deleteBook(index, tmpList);
			
			break;
		case 5:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
			System.out.println("[잘못된 입력입니다.]");
		}
		
	}

	private void deleteMember(int index, List<Member> tmpList) {
		if(mm.delete(tmpList.get(index))) {
			System.out.println("[회원을 삭제했습니다.]");
		} else {
			System.out.println("[회원을 삭제하지 못했습니다.]");
		}
	}

	private void deleteBook(int index, List<Book> tmpList) {
		if(bm.delete(tmpList.get(index))) {
			System.out.println("[도서를 삭제했습니다.]");
		} else {
			System.out.println("[도서를 삭제하지 못했습니다.]");
		}
	}

	private void searchMember() {
		int menu;
		
		do {
			printSearchMemberMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runSearchMemberMenu(menu);
			
		} while(menu != 4);
	}

	private void searchBook() {
		int menu;
		do {
			
			printSearchBookMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runSearchBookMenu(menu);
			
			
		} while(menu != 5);
		
	}

	private void printSearchMemberMenu() {
		System.out.println("1. 이름으로 검색");
		System.out.println("2. 연락처로 검색");
		System.out.println("3. 아이디로 검색");
		System.out.println("4. 이전 메뉴");
		System.out.print("메뉴 입력 : ");
	}

	private void printSearchBookMenu() {
		System.out.println("-------------------");
		System.out.println("도서 검색");
		System.out.println("1. 도서명으로 검색");
		System.out.println("2. 작가명으로 검색");
		System.out.println("3. 출판사로 검색");
		System.out.println("4. 도서 번호로 검색");
		System.out.println("5. 이전으로");
		System.out.println("-------------------");
		System.out.print("메뉴 입력 : ");
	}

	private void runSearchMemberMenu(int menu) {
		List<Member> tmpList;
		switch(menu) {
		case 1:
			System.out.print("이름 : ");
			String name = scan.next();
			scan.nextLine();
			tmpList = mm.getMemberList(new Member("", "", name, ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, false);
			
			break;
		case 2:
			System.out.print("번호 : ");
			String num = scan.next();
			scan.nextLine();
			tmpList = mm.getMemberList(new Member("", "", "", num));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, false);
			break;
		case 3:
			System.out.print("ID : ");
			String id = scan.next();
			scan.nextLine();
			tmpList = mm.getMemberList(new Member(id, "", "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 회원 정보가 없습니다.]");
				return;
			}
			
			searchMember(tmpList, false);
			break;
		case 4:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
		}
	}

	private void runSearchBookMenu(int menu) {
		List<Book> tmpList;
		
		switch(menu) {
		case 1:
			System.out.print("도서명 : ");
			String title = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", title, "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, false);
			break;
		case 2:
			System.out.print("작가명 : ");
			String author = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", author, ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, false);
			break;
		case 3:
			System.out.print("출판사 : ");
			String publisher = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", "", publisher));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, false);
			break;
		case 4:
			System.out.print("도서코드 : ");
			String bookCode = scan.nextLine();
			
			tmpList = bm.getBookList(new Book(bookCode, "", "", ""));
			
			if(tmpList == null || tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			searchBook(tmpList, false);
			break;
		case 5:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
			System.out.println("[잘못된 입력입니다.]");
		}
	}

	private void searchMember(List<Member> tmpList, boolean isMember) {
		
		if(tmpList.isEmpty() || tmpList == null) {
			System.out.println("[일치하는 회원 정보가 없습니다.]");
			return;
		}
		
		mm.print(tmpList, isMember);
		
	}

	private void searchBook(List<Book> tmpList, boolean isBook) {
		
		
		if(tmpList.isEmpty() || tmpList == null) {
			System.out.println("[일치하는 도서가 없습니다.]");
			return;
		}
		
		bm.print(tmpList, isBook);
		
	}

	private void rentReturnBook() {
		int menu;
		do {
			
			printRentReturnMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runRentReturnMenu(menu);
			
			
		} while(menu != 3);
		
	}

	

	private void printRentReturnMenu() {
		System.out.println("1. 도서 대여");
		System.out.println("2. 도서 반납");
		System.out.println("3. 이전 메뉴");
		System.out.print("메뉴 입력 : ");
	}

	private void runRentReturnMenu(int menu) {
		switch(menu) {
		case 1:
			rentBook();
			break;
		case 2:
			returnBook();
			break;
		case 3:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
		}
	}
	
	private void rentBook() {
		int menu;
		do {
			printSearchBookMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runRentBookMenu(menu);
			
		} while(menu!=5);
		
	}

	private void runRentBookMenu(int menu) {
		List<Book> tmpList;
		int index;
		Book book;
		
		switch(menu) {
		case 1:
			System.out.print("도서명 : ");
			String title = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", title, "", ""));
			
			if(tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			bm.print(tmpList, true);
			
			System.out.print("대여할 도서 선택 : ");
			index = scan.nextInt() - 1;
			
			book = tmpList.get(index);
			
			rentBook(book);
			
			break;
		case 2:
			System.out.print("작가명 : ");
			String author = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", author, ""));
			
			if(tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			bm.print(tmpList, true);
			
			System.out.print("대여할 도서 선택 : ");
			index = scan.nextInt() - 1;
			
			book = tmpList.get(index);
			
			rentBook(book);
			
			break;
		case 3:
			System.out.print("출판사 : ");
			String publisher = scan.nextLine();
			
			tmpList = bm.getBookList(new Book("", "", "", publisher));
			
			if(tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			bm.print(tmpList, true);
			
			System.out.print("대여할 도서 선택 : ");
			index = scan.nextInt() - 1;
			
			book = tmpList.get(index);
			
			rentBook(book);
			
			break;
		case 4:
			System.out.print("도서 코드 : ");
			String code = scan.nextLine();
			
			tmpList = bm.getBookList(new Book(code, "", "", ""));
			
			if(tmpList.isEmpty()) {
				System.out.println("[일치하는 도서가 없습니다.]");
				return;
			}
			
			bm.print(tmpList, true);
			
			System.out.print("대여할 도서 선택 : ");
			index = scan.nextInt() - 1;
			
			book = tmpList.get(index);
			
			rentBook(book);
			
			break;
		case 5:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
			System.out.println("[올바른 메뉴가 아닙니다.]");
		}
		
	}

	private void rentBook(Book book) {
		if(!book.isRentReturn()) {
			System.out.println("[이미 대여 중인 도서입니다.]");
			return;
		}
		if(rr.rentBook(user.getId(), book)) {
			System.out.println("[도서 대여 완료]");
			bm.setRentReturn(book, false);
		} else {
			System.out.println("[도서 대여 실패]");
		}
		
	}

	private void returnBook() {
		
		List<Book> rentList;
		
		rentList = rr.print(user.getId());
		
		if(rentList == null) {
			return;
		}
		
		System.out.print("반납할 도서 선택 : ");
		int index = scan.nextInt() - 1;
		
		if(index < 0 || index >= rentList.size()) {
			System.out.println("[잘못 입력했습니다.]");
			return;
		}
		
		Book book = rentList.get(index);
		
		if(rr.returnBook(book, user.getId())) {
			System.out.println("[도서 반납 완료]");
			bm.setRentReturn(book, true);
		} else {
			System.out.println("[도서 반납 실패]");
		}
	}
	private void saveDataToFile(String membersFileName, String booksFileName, String rentalsFileName) {
        save(membersFileName, mm.getMembers());
        save(booksFileName, bm.getBooks());
        save(rentalsFileName, rr.getRentReturnMap());
        System.out.println("[데이터가 파일에 저장되었습니다.]");
    }
}