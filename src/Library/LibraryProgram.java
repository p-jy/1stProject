package Library;

import java.util.Scanner;

public class LibraryProgram implements ConsoleProgram {
	//프로그램
	private MemberManager memberManager;
	private BookManager bookManager;
	private RentReturn rentReturn;
    private Scanner scanner;
    
    
    public LibraryProgram() {
        memberManager = new MemberManager();
        bookManager = new BookManager();
        rentReturn = new RentReturn(null,null);
        scanner = new Scanner(System.in);
    }
    
    
    
    
	
	@Override
	public void run() {
		System.out.println("----도서 프로그램----");
		System.out.println("1.관리자 메뉴");
		System.out.println("1.관리자 메뉴");
		System.out.println("3.종료");
	}

	@Override
	public void printMenu() {
		
	}

	@Override
	public void runMenu(int menu) {
		
	}

}