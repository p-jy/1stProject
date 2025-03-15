package db2.service;

import java.util.List;

import db2.dao.BookDAO;
import db2.dao.BookDAOImpl;
import db2.model.vo.Book;

public class BookManager2 {

    private BookDAOImpl bookDao;  // BookDAOImpl을 사용

    // BookDAOImpl을 인자로 받는 생성자
    public BookManager2(BookDAOImpl bookDao) {
        this.bookDao = bookDao;
    }

    // 도서 등록
    public boolean registBook(Book book) {
        return bookDao.registBook(book);
    }

    // 도서 수정
    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    // 도서 삭제
    public boolean deleteBook(Book book) {
        return bookDao.deleteBook(book.getBookCode());
    }

    // 도서 목록
    public List<Book> getAllBooks() {
        return bookDao.selectAllBooks();
    }

    // 도서 검색 (도서명으로)
    public List<Book> searchBooksByTitle(String title) {
        return bookDao.searchBooksByTitle(title);
    }

    // 도서 검색 (작가명으로)
    public List<Book> searchBooksByAuthor(String author) {
        return bookDao.searchBooksByAuthor(author);
    }

    // 도서 검색 (출판사로)
    public List<Book> searchBooksByPublisher(String publisher) {
        return bookDao.searchBooksByPublisher(publisher);
    }

    // 도서 검색 (도서 코드로)
    public List<Book> searchBooksByCode(String bookCode) {
        return bookDao.searchBooksByCode(bookCode);
    }

    // 전체 도서 목록 출력
    public static void listBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("[등록된 도서가 없습니다.]");
            return;
        }

        System.out.println("===== 도서 목록 =====");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // 샘플 데이터 추가 (필요한 경우)
    public void addSampleBookData() {
        registBook(new Book(getLastNum("000"), "총류", "한국의 꽃살·기둥·누각", "임석재", "이화여자대학교출판부"));
        registBook(new Book(getLastNum("100"), "철학", "장자의 철학", "강신주", "태학사"));
        registBook(new Book(getLastNum("200"), "종교", "저항하는 그리스도인", "강성호", "복 있는사람"));
        registBook(new Book(getLastNum("300"), "사회과학", "수익 분배의 경제학", "오정석", "삼성경제연구소"));
        registBook(new Book(getLastNum("400"), "자연과학", "역발상의 과학 : 더하고 빼고 뒤집으면 답이 보인다", "김준래", "오엘북스"));
        registBook(new Book(getLastNum("500"), "기술과학", "이유석의 이유식", "이유석", "BR미디어"));
        registBook(new Book(getLastNum("600"), "예술", "프로처럼 사진 찍기", "조승범", "청년정신"));
        registBook(new Book(getLastNum("700"), "언어", "프랑스어동사활용사전", "서영하", "청록출판사"));
        registBook(new Book(getLastNum("800"), "문학", "위로가 필요한 시간", "김경집", "조화로운삶"));
        registBook(new Book(getLastNum("900"), "역사", "미술로 보는 우리 역사", "전국역사교사모임", "푸른나무"));
        registBook(new Book(getLastNum("900"), "역사", "유배인과 배수첩들의 뒤안길", "전웅", "소나기"));
    }

    // 도서 코드의 마지막 번호를 가져오기
    public int getLastNum(String codePrefix) {
        List<Book> books = bookDao.selectAllBooks();
        if (books == null || books.isEmpty()) {
            return -1;
        }
        int max = 0;

        for (Book book : books) {
            String bookCodePrefix = book.getBookCode().substring(0, 3);
            String bookCodeSuffix = book.getBookCode().substring(3);
            if (bookCodePrefix.equals(codePrefix)) {
                int num = Integer.parseInt(bookCodeSuffix);
                if (max < num) {
                    max = num;
                }
            }
        }
        return max;
    }
}
