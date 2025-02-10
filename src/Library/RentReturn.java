package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Data;

@Data
public class RentReturn {
    
    private Member member;
    private Book book;
    private boolean returned;
    
    
    
    private static List<RentReturn> rentals = new ArrayList<>();
    
    public RentReturn(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.returned = false;
    }
    
    public static boolean rentBook(Member member, Book book) {
        Scanner scan = new Scanner(System.in);
        
       
        if (member == null || book == null) {
            System.out.println("회원 또는 도서 정보가 없습니다.");
            return false;
        }
      
        if (!book.isRentReturn()) {
            System.out.println("도서 \"" + book.getTitle() + "\"는 현재 대여 중입니다.");
            return false;
        }
        
      
        int currentRentCount = 0;
        for (RentReturn rr : rentals) {
            if (rr.getMember().equals(member) && !rr.isReturned()) {
                currentRentCount++;
            }
        }
        if (currentRentCount >= 3) {
            System.out.println("회원은 이미 최대 3권의 도서를 대여 중입니다. 반납 후 대여해주세요.");
            return false;
        }
        
       
        System.out.println("도서 대여를 하시겠습니까? (y/n)");
        String answer = scan.nextLine();
        if (!answer.equalsIgnoreCase("y")) {
            System.out.println("도서 대여가 취소되었습니다.");
            return false;
        }
        
        
        RentReturn rr = new RentReturn(member, book);
        rentals.add(rr);
        member.getRentList().add(rr);
        book.setRentReturn(false); 
        System.out.println("도서 \"" + book.getTitle() + "\"가 성공적으로 대여되었습니다.");
        return true;
    }
    
    // 반납 
    public static boolean returnBook(Member member, Book book) {
        for (RentReturn rr : rentals) {
            if (rr.getMember().equals(member) && rr.getBook().equals(book) && !rr.isReturned()) {
                rr.setReturned(true);
                
                book.setRentReturn(true);
                System.out.println("도서 \"" + book.getTitle() + "\"가 반납되었습니다.");
                return true;
            }
        }
        System.out.println("대여 기록을 찾을 수 없습니다.");
        return false;
    }
    
    @Override
    public String toString() {
        return "[" + book.getBookCode() + "] " + book.getTitle() +
               " - " + (returned ? "반납 완료" : "대여 중");
    }
}