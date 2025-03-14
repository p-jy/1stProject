package db.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import db.model.vo.Member;
//import db.service.BookManager;
import db.service.MemberManager;

public class LibraryProgram implements ConsoleProgram{
	
	private Scanner scan = new Scanner(System.in);
	
	private MemberManager mm = new MemberManager();
//	private BookManager bm = new BookManager();
	
	private Member user = null;
	
	@Override
	public void run() {
		
		int menu = 0;
		final int EXIT = 3;
		
		do {
			printMenu();
			
			try {
				
				menu = scan.nextInt();
				removeBuffer();
				
				runMenu(menu);
				
			} catch(InputMismatchException e) {
				System.out.println("올바른 입력이 아닙니다.");
				removeBuffer();
			}
			
			
		} while(menu != EXIT);

        do {
            printMenu();
            try {
                menu = scan.nextInt();
                removeBuffer();
                
                runMenu(menu);
            } catch (InputMismatchException e) {
                System.out.println("[올바른 입력이 아닙니다.]");
                scan.nextLine();
            }
        } while(menu != EXIT);
       
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
				
		Member tmp = new Member(id, pw, "", "");
		user = mm.getMember(tmp);
		
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

	private void runAdmin() {
		int menu = 0;
		final int EXIT = 3;
		
		do {
			
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
			
		} while (menu != EXIT);
		
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

	private void runMemberMenu(int menu) {
		switch(menu) {
		case 1:
			deleteID();
			break;
		case 2:
//			searchBook();
			break;
		case 3:
//			rentReturnBook();
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
		System.out.println("2. 회원 조회");
		System.out.println("3. 이전 메뉴");
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
//			searchMember();
			break;
		default:
		}
	}
	
	private void printSearchMemberMenu() {
		System.out.println("1. 이름으로 검색");
		System.out.println("2. 연락처로 검색");
		System.out.println("3. 아이디로 검색");
		System.out.println("4. 이전 메뉴");
		System.out.print("메뉴 입력 : ");
	}


	private void updateMember() {
		int menu;
		do {
			
//			printSearchMemberMenu();
			menu = scan.nextInt();
			scan.nextLine();
			
			runUpdateMemberMenu(menu);
			
			
		} while(menu != 4);
	}

	private void runUpdateMemberMenu(int menu) {
		Member searchMem;
		Member selMem;
		Member newMem;
		
		switch(menu) {
		case 1:
			System.out.print("이름 : ");
			String name = scan.next();
			removeBuffer();
			
			searchMem = new Member("", "", name, "");
			selMem = mm.getMember(searchMem);
			
			if(selMem == null) {
				System.out.println("일치하는 회원 정보가 없습니다.");
				return;
			}
			
			System.out.println("변경할 정보를 입력해주세요.");
			newMem = inputMemberBase();
			
			if(mm.updateMember(selMem, newMem)) {
				System.out.println("회원 정보를 수정했습니다.");
				return;
			}
			
			System.out.println("이미 등록된 회원 정보입니다.");
			break;
		case 2:
			System.out.print("번호 : ");
			String num = scan.next();
			scan.nextLine();
			
			searchMem = new Member("", "", "", num);
			selMem = mm.getMember(searchMem);
			
			if(selMem == null) {
				System.out.println("일치하는 회원 정보가 없습니다.");
				return;
			}
			
			System.out.println("변경할 정보를 입력해주세요.");
			newMem = inputMemberBase();
			
			if(mm.updateMember(selMem, newMem)) {
				System.out.println("회원 정보를 수정했습니다.");
				return;
			}
			
			System.out.println("이미 등록된 회원 정보입니다.");
			break;
		case 3:
			System.out.print("아이디 : ");
			String id = scan.next();
			removeBuffer();
			
			searchMem = new Member(id, "", "", "");
			selMem = mm.getMember(searchMem);
			
			if(selMem == null) {
				System.out.println("일치하는 회원 정보가 없습니다.");
				return;
			}
			
			System.out.println("변경할 정보를 입력해주세요.");
			newMem = inputMemberBase();
			
			if(mm.updateMember(selMem, newMem)) {
				System.out.println("회원 정보를 수정했습니다.");
				return;
			}
			
			System.out.println("이미 등록된 회원 정보입니다.");
			break;
		case 4:
			System.out.println("[이전 메뉴로 돌아갑니다.]");
			break;
		default:
		}
	}

	private void deleteMember() {
		
	}

	private void searchMember() {
		
	}

	private void manageBook() {
		
	}

	private void runMember() {
		
	}
	
	private Member inputMemberBase() {
		System.out.print("이름 : ");
		String name = scan.next();
		scan.nextLine();
		
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
	            System.out.println("[아이디는 영어, 숫자만 입력가능하고, 6~12자이어야 합니다.]");
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
	            System.out.println("[비밀번호는 영어, 숫자, 특수문자(!@#$)만 입력가능하고, 8~16자여야 합니다.]");
	        }
	    }
		
		Member member = inputMemberBase();
		return new Member(id, pw, member.getName(), member.getNum());
	}

	private void deleteID() {
		System.out.print("아이디 : ");
		String id = scan.next();
		scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		
		Member mem = new Member(id, pw, "", "");
		Member selMem = mm.getMember(mem);
		
		if(mm.deleteMember(mem)) {
			System.out.println("회원 탈퇴 완료");
			user = null;
		} else {
			System.out.println("회원 탈퇴 실패");
		}
		
		
	}

//	private void searchMember() {
//		int menu;
//		
//		do {
//			printSearchMemberMenu();
//			menu = scan.nextInt();
//			scan.nextLine();
//			
//			runSearchMemberMenu(menu);
//			
//		} while(menu != 4);
//	}
//
//	private void printSearchMemberMenu() {
//		System.out.println("1. 이름으로 검색");
//		System.out.println("2. 연락처로 검색");
//		System.out.println("3. 아이디로 검색");
//		System.out.println("4. 이전 메뉴");
//		System.out.print("메뉴 입력 : ");
//	}
//
//	private void runSearchMemberMenu(int menu) {
//		List<Member> tmpList;
//		switch(menu) {
//		case 1:
//			System.out.print("이름 : ");
//			String name = scan.next();
//			scan.nextLine();
//			tmpList = mm.getMemberList(new Member("", "", name, ""));
//			
//			if(tmpList == null || tmpList.isEmpty()) {
//				System.out.println("[일치하는 회원 정보가 없습니다.]");
//				return;
//			}
//			
//			searchMember(tmpList, false);
//			
//			break;
//		case 2:
//			System.out.print("번호 : ");
//			String num = scan.next();
//			scan.nextLine();
//			tmpList = mm.getMemberList(new Member("", "", "", num));
//			
//			if(tmpList == null || tmpList.isEmpty()) {
//				System.out.println("[일치하는 회원 정보가 없습니다.]");
//				return;
//			}
//			
//			searchMember(tmpList, false);
//			break;
//		case 3:
//			System.out.print("ID : ");
//			String id = scan.next();
//			scan.nextLine();
//			tmpList = mm.getMemberList(new Member(id, "", "", ""));
//			
//			if(tmpList == null || tmpList.isEmpty()) {
//				System.out.println("[일치하는 회원 정보가 없습니다.]");
//				return;
//			}
//			
//			searchMember(tmpList, false);
//			break;
//		case 4:
//			System.out.println("[이전 메뉴로 돌아갑니다.]");
//			break;
//		default:
//		}
//	}
//
//	private void searchMember(List<Member> tmpList, boolean isMember) {
//		
//		if(tmpList.isEmpty() || tmpList == null) {
//			System.out.println("[일치하는 회원 정보가 없습니다.]");
//			return;
//		}
//		
//		mm.print(tmpList, isMember);
//		
//	}
	
}
