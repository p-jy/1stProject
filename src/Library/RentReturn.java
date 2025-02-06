package Library;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RentReturn {
	//대여반납
			//도서번호, 회원 아이디???
	private Member member;
    private Book book;
    private boolean returned;
    
    private List<RentReturn> rentals = new ArrayList<RentReturn>();
	
    public RentReturn(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.returned = false;
    }
    

	//대여
	public boolean rentBook(Member member, Book book) {
        if(member == null || book == null)
            return false;
        
        int count = 0;
        
        for(RentReturn rr : rentals) {
            if(rr.getMember().equals(member) && !rr.isReturned()) {
                count++;
            }
        }
        if(count >= 3) {
            System.out.println("최대 대여 가능 권수를 초과하였습니다.");
            return false;
        }
        RentReturn rr = new RentReturn(member, book);
        rentals.add(rr);
        return true;
	}
	//반납
	public boolean returnBook(Member member, Book book) {
        for(RentReturn rr : rentals) {
            if(rr.getMember().equals(member) && rr.getBook().equals(book) && !rr.isReturned()) {
                rr.setReturned(true);
                return true;
            }
        }
        System.out.println("대여 기록을 찾을 수 없습니다.");
        return false;
    }
}



