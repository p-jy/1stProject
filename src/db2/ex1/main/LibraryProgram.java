package db2.ex1.main;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import db2.ex1.model.vo.Book;
import db2.ex1.model.vo.Member;
import db2.ex1.service.BookManager;
import db2.ex1.service.MemberManager;

public class LibraryProgram implements ConsoleProgram {
	
	
	private Scanner scan = new Scanner(System.in);
	
	private BookManager bm = new BookManager();
	private MemberManager mm = new MemberManager();
	
	private Member user = null;
		
	@Override
	public void run() {
        
       
        
       
        int menu = 0;
        do {
            printMenu();
            try {
                menu = scan.nextInt();
                removeBuffer();
                runMenu(menu);
            } catch (InputMismatchException e) {
                System.out.println("[올바른 입력이 아닙니다.]");
                removeBuffer();
            }
        } while(menu != 3);
        
        
        
       
	}

	private void removeBuffer() {
		scan.nextLine();		
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
		removeBuffer();
		
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
			removeBuffer();
			
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
		int menu;
		do {
			
			//회원 관리
			System.out.println("1. 회원 수정");
			System.out.println("2. 회원 삭제");
			System.out.println("3. 회원 조회");
			System.out.println("4. 이전 메뉴");
			System.out.print("메뉴 입력 : ");
			
			menu = scan.nextInt();
			removeBuffer();
			
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
		} while (menu != 4);
		
	}

	private void manageBook() {
		int menu;
		
		do {
			
			//도서 관리
			System.out.println("1. 도서 등록");
			System.out.println("2. 도서 수정");
			System.out.println("3. 도서 삭제");
			System.out.println("4. 도서 조회");
			System.out.println("5. 이전 메뉴");
			System.out.print("메뉴 입력 : ");
			
			menu = scan.nextInt();
			removeBuffer();
			
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
				System.out.println("[이전 메뉴로 돌아갑니다.]");
				break;
			default:
			}
			
		} while(menu != 5);
		
		
	}

	private void updateMember() {
		
		System.out.print("수정할 회원의 ID : ");
		String id = scan.nextLine();
		
		Member member = new Member(id, "", "", "");
		
		if(!mm.contains(member)) {
			System.out.println("[일치하는 회원 ID가 없습니다.]");
			return;
		}
		
		System.out.println("수정할 회원의 정보를 입력하세요.");
		
		Member newMember = inputMemberBase();
		
		if(mm.update(member, newMember)) {
			System.out.println("[회원 정보를 수정했습니다.]");
			return;
		}
		
		System.out.println("[이미 등록된 회원입니다.]");
		
	}

	private void updateBook() {

		System.out.print("수정할 도서의 도서코드 : ");
		String code = scan.nextLine();
		
		Book book = new Book(code, "", "", "");
		
		if(!bm.contains(book)) {
			System.out.println("[일치하는 도서 정보가 없습니다.]");
			return;
		}
		
		System.out.println("수정할 도서의 정보를 입력하세요.");
		
		Book newBook = inputBookBase();
		
		if(bm.update(book, newBook)) {
			System.out.println("[도서 정보를 수정했습니다.]");
			return;
		}
		
		System.out.println("[이미 등록된 도서입니다.]");
		
		
	}

	private Member inputMemberBase() {
		System.out.print("이름 : ");
		String name = scan.next();
		removeBuffer();
		
		String numPattern = "[0][1][0]-[0-9]{4}-[0-9]{4}$"; //010-nnnn-nnnn
		String num = "";
		while (true) {
	        System.out.print("번호(010-xxxx-xxxx) : ");
	        num = scan.nextLine();
	        if (num.matches(numPattern)) {
	            break;
	        } else {
	            System.out.println("[010-xxxx-xxxx 형식으로 입력해주세요.]");
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
		removeBuffer();
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
		System.out.print("삭제할 회원 ID : ");
		String id = scan.nextLine();
		
		if(id == "admin") {
			System.out.println("[관리자 계정은 삭제할 수 없습니다.]");
			return;
		}
		
		Member member = mm.getMember(id, "");
		
		if(mm.deleteByAdmin(member)) {
			System.out.println("[" + id + " 회원을 삭제하였습니다.]");
		} else {
			System.out.println("[존재하지 않는 회원 ID 입니다.]");
		}
		
	}

	private void deleteID() {
		System.out.print("아이디 : ");
		String id = scan.next();
		removeBuffer();
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
		
		System.out.print("삭제할 도서 코드 : ");
		String code = scan.nextLine();
		
		Book book = bm.getBook(code);
		
		if(bm.delete(book)) {
			System.out.println("[도서 삭제 성공]");
		} else {
			System.out.println("[도서 삭제 실패]");
		}
		
	}
	

	private void searchMember() {
		
		mm.print();
		
	}

	private void searchBook() {
		
		bm.print();
		
	}

	private void rentReturnBook() {
		int menu;
		do {
			
			printRentReturnMenu();
			menu = scan.nextInt();
			removeBuffer();
			
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
	    System.out.print("대여할 도서의 코드 입력: ");
	    String code = scan.nextLine().trim(); // 사용자 입력 (공백 제거)

	    // 도서 조회
	    Book book = bm.getBook(code); // BookManager를 통해 도서 조회

	    if (book == null) {
	        System.out.println("[해당 도서가 존재하지 않습니다.]");
	        return;
	    }

	    // 사용자의 대여 기록 조회
	    if (bm.isAlreadyRented(user.getId(), code)) { // 같은 사람이 같은 책을 빌렸는지 체크
	        System.out.println("[이미 대여한 도서입니다.]");
	        return;
	    }

	    // 도서 대여 가능 여부 확인
	    if ("Y".equalsIgnoreCase(book.getBoRent())) { // 이미 대여된 경우
	        System.out.println("[해당 도서는 이미 대여 중입니다.]");
	        return;
	    }

	    // 현재 날짜 및 반납 기한 설정 (7일 후)
	    Date rentDate = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(rentDate);
	    cal.add(Calendar.DATE, 7);
	    Date dueDate = cal.getTime();

	    // 도서 대여 상태 변경 및 DB 반영
	    book.setBoRent("Y"); // 도서 상태를 'Y' (대여 중)으로 설정
	    bm.updateBook(book); // 데이터베이스 업데이트 (대여 상태 반영)
	    bm.insertRentalRecord(user.getId(), code, dueDate); // 대여 기록 저장

	    System.out.println("[도서 대여 완료! 반납 기한: " + dueDate + "]");
	}


	private void returnBook() {
	
		
		
	}
	
}