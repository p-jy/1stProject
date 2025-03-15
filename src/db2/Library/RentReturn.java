package db2.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db2.model.vo.Book;

public class RentReturn {
    
    private Map<String, List<Book>> rentList;

    // RentReturn에 Map<String, List<Book>>를 받는 생성자 추가
    public RentReturn(Map<String, List<Book>> rentList) {
        this.rentList = rentList;
    }

    // rentBook 메서드 수정: 대여 처리 로직 추가
    public boolean rentBook(String userId, Book book) {
        // 책의 대여 가능 여부를 체크
        if (!book.isRentReturn()) {
            System.out.println("[대여 불가] 이 책은 이미 대여 중입니다.");
            return false;
        }

        // rentList에 userId에 해당하는 책 리스트가 없다면 새로 생성
        if (!rentList.containsKey(userId)) {
            rentList.put(userId, new ArrayList<>());
        }

        // 대여된 책 리스트에 책 추가
        List<Book> userBooks = rentList.get(userId);
        userBooks.add(book);

        // 대여 상태 변경 (책의 rentReturn을 false로 설정하여 대여 중 상태로 변경)
        book.setRentReturn(false);

        System.out.println("[도서 대여 완료]");
        return true;
    }

    // returnBook 메서드 (반납 처리)
    public boolean returnBook(Book book, String userId) {
        // 해당 사용자에게 책이 있는지 확인
        List<Book> userBooks = rentList.get(userId);
        if (userBooks != null && userBooks.contains(book)) {
            // 책을 반납 처리
            userBooks.remove(book);
            book.setRentReturn(true); // 책의 대여 가능 상태로 변경
            System.out.println("[도서 반납 완료]");
            return true;
        }

        System.out.println("[반납 실패] 대여 기록이 없습니다.");
        return false;
    }

    // rentList 출력 메소드
    public List<Book> print(String userId) {
        return rentList.get(userId);
    }

    // rentList 반환 메소드
    public Map<String, List<Book>> getRentReturnMap() {
        return rentList;
    }
}
