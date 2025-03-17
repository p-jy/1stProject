package db3.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import db3.dao.BookDAO;
import db3.dao.MemberDAO;
import db3.dao.RentDAO;
import db3.model.vo.Book;
import db3.model.vo.Member;
import db3.model.vo.Rent;

public class RentService {
    
    private RentDAO rentDAO;
    private BookDAO bookDAO;
    private MemberDAO memberDAO;
    
    // 생성자: DAO 객체를 주입하거나 직접 생성할 수 있음
    public RentService() {
        this.rentDAO = new RentDAO();
        this.bookDAO = new BookDAO();
        this.memberDAO = new MemberDAO();
    }
    
    // 도서 대여 처리 메서드
    public boolean rentBook(String memberId, String bookCode) {
        // 1. 회원의 대여 가능 여부 체크 (예: 최대 3권 이하, 대여 불가 기간 체크)
        int currentRentCount = rentDAO.getCurrentRentCount(memberId);
        if (currentRentCount >= 3) {
            System.out.println("최대 3권까지 대여 가능합니다.");
            return false;
        }
        
        Member member = memberDAO.findById(memberId);
        if (member == null) {
            System.out.println("존재하지 않는 회원입니다.");
            return false;
        }
        
        // 2. 해당 도서의 대여 가능 상태 체크
        Book book = bookDAO.findByCode(bookCode);
        if (book == null) {
            System.out.println("존재하지 않는 도서입니다.");
            return false;
        }
        if (book.getBoRent().equals("Y")) {
            System.out.println("이미 대여 중인 도서입니다.");
            return false;
        }
        
        // 3. 대여 처리: 대여 시작일은 현재 시간, 반납 예정일은 7일 후
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueDate = now.plus(7, ChronoUnit.DAYS);
        
        Rent rent = new Rent();
        rent.setReMeId(memberId);
        rent.setReBoCode(bookCode);
        rent.setReRentDate(now);
        rent.setReDueDate(dueDate);
        rent.setReState("대여");  // 예: "대여" 상태로 설정
        
        // 4. RENT 테이블에 대여 기록 추가 및 도서 상태 업데이트
        boolean rentInserted = rentDAO.insertRent(rent);
        boolean bookUpdated = bookDAO.updateBookRentStatus(bookCode, "Y");
        
        return rentInserted && bookUpdated;
    }
    
    // 도서 반납 처리 메서드
    public boolean returnBook(String memberId, String bookCode) {
        // 1. 대여 기록 조회: 해당 회원이 대여 중인 도서 확인
        Rent rent = rentDAO.findActiveRent(memberId, bookCode);
        if (rent == null) {
            System.out.println("해당 도서에 대한 대여 기록이 없습니다.");
            return false;
        }
        
        // 2. 반납 처리: 반납일 업데이트, 상태 변경
        LocalDateTime returnDate = LocalDateTime.now();
        rent.setReReturnDate(returnDate);
        rent.setReState("반납");
        boolean rentUpdated = rentDAO.updateRentReturn(rent);
        
        // 3. 도서 상태 업데이트: 대여 가능 상태로 변경
        boolean bookUpdated = bookDAO.updateBookRentStatus(bookCode, "N");
        
        // 4. 연체 여부 체크 (반납일이 예정일보다 늦은 경우)
        if (returnDate.isAfter(rent.getReDueDate())) {
            long overdueDays = ChronoUnit.DAYS.between(rent.getReDueDate(), returnDate);
            System.out.println("연체 " + overdueDays + "일 발생. 해당 회원은 " + overdueDays + "일간 대여가 불가합니다.");
            // 연체 처리 로직 추가: 예) 회원의 대여 불가 기간 업데이트
            memberDAO.setRentBan(memberId, overdueDays);
        }
        
        return rentUpdated && bookUpdated;
    }
}
