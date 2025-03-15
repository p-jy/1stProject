package db2.service;

import java.util.List;
import java.util.Map;

import db2.model.vo.Book;

public class RentReturn {
    
    private Map<String, List<Book>> rentList;

    // RentReturn에 Map<String, List<Book>>를 받는 생성자 추가
    public RentReturn(Map<String, List<Book>> rentList) {
        this.rentList = rentList;
    }

    // 기존 메소드들 (rentBook, returnBook 등)
    public boolean rentBook(String userId, Book book) {
        // 대여 처리 로직
        // rentList에 userId에 해당하는 책 리스트가 없다면 추가
        return true;
    }

    public boolean returnBook(Book book, String userId) {
        // 반납 처리 로직
        return true;
    }

    // rentList 출력 메소드 등
    public List<Book> print(String userId) {
        return rentList.get(userId);
    }

    // rentList 반환 메소드
    public Map<String, List<Book>> getRentReturnMap() {
        return rentList;
    }
}
