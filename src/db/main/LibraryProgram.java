package db.main;

import java.util.Scanner;

import db.dao.MemberDAO;
import db.model.vo.Member;

public class LibraryProgram {
	public void run() {
		Scanner scanner = new Scanner(System.in);
		MemberDAO memberDAO = new MemberDAO();
		
		while (true) {
		    printMenu();

		    System.out.print("메뉴 입력: ");
		    int menu = scanner.nextInt();
		    scanner.nextLine();

		    switch (menu) {
		        case 1:
		            System.out.println("로그인 기능 (미구현)");
		            break;
		        case 2:
		            registerMember(scanner, memberDAO);
		            break;
		        case 3:
		            System.out.println("프로그램 종료");
		            return;
		        default:
		            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
		    }
		}
    }
	
	 public void printMenu() {
	        System.out.println("\n==== 도서 관리 시스템 ====");
	        System.out.println("1. 로그인");
	        System.out.println("2. 회원가입");
	        System.out.println("3. 종료");
    }
	 
	 public void registerMember(Scanner scanner, MemberDAO memberDAO) {
	        System.out.println("회원가입을 시작합니다.");
	        
	        System.out.print("아이디: ");
	        String id = scanner.nextLine();

	        System.out.print("비밀번호: ");
	        String pw = scanner.nextLine();

	        System.out.print("이름: ");
	        String name = scanner.nextLine();

	        System.out.print("연락처: ");
	        String num = scanner.nextLine();

	        boolean added = memberDAO.addMember(new Member(id, pw, name, num));
	        System.out.println(added ? "회원가입 성공!" : "회원가입 실패!");
    }

}
