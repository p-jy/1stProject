package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
	//도서관리
	
	private static List<Book> list;
	
	public BookManager(List<Book> list) {
		if(list == null) {
			this.list = new ArrayList<Book>();
		} else {
			this.list = list;
		}
	}
	
	public BookManager() {
		this.list = new ArrayList<Book>();
	}
	
	
	public boolean registBook(Book book) {
		
		if(book == null || list == null) {
			return false;
		}
		
		if(list.contains(book)) {
			return false;
		}
		
		return list.add(book);
	}

	public void print(String title) {
		
		if(list == null) {
			System.out.println("");
		}
		
	}

	public List<Book> getBookList(Book book) {
		if(book.getTitle() != null) {
			String title = book.getTitle();
			
			return list.stream()
					.filter(b-> b.getTitle().contains(title))
					.collect(Collectors.toList());
			
		} else if(book.getAuthor() != null) {
			String author = book.getAuthor();
			
			return list.stream()
					.filter(b-> b.getAuthor().contains(author))
					.collect(Collectors.toList());
			
		} else if(book.getPublisher() != null) {
			String publisher = book.getPublisher();
			
			return list.stream()
					.filter(b-> b.getPublisher().contains(publisher))
					.collect(Collectors.toList());
			
		} else if(book.getBookCode() != null) {
			String bookCode = book.getBookCode();
			
			return list.stream()
					.filter(b-> b.getBookCode().contains(bookCode))
					.collect(Collectors.toList());
		}
		
		System.out.println("[일치하는 도서가 없습니다.]");
		return null;
		
	}

	public void print(List<Book> tmpList, boolean isBook) {
		if(tmpList == null || tmpList.isEmpty()) {
			System.out.println("[일치하는 도서가 없습니다.]");
			return;
		}
		
		for(int i = 0; i < tmpList.size(); i++) {
			if(isBook == true) {
				System.out.print(i+1 + ". ");
			}
			System.out.println(tmpList.get(i));
		}
	}

	public int getLastNum(String codePrefix) {
		if(list == null) {
			return -1;
		}
		int max = 0;
		
		for(Book book : list) {
			String bookCodePrefix = book.getBookCode().substring(0, 3);
			String bookCodeSuffix = book.getBookCode().substring(3);
			if(bookCodePrefix.equals(codePrefix)) {
				int num = Integer.parseInt(bookCodeSuffix);
				if(max < num) {
					max = num;
				}
			}
		}
		return max;
	}

	public boolean update(Book book, Book bookObj) {
		
		if(book == null || bookObj == null) {
			return false;
		}
		
		book.update(bookObj);
		
		return true;
	}

	public boolean delete(Book book) {
		return list.remove(book);
	}
	
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

	public void setRentReturn(Book book, boolean b) {
		book.setRentReturn(b);
	}
	
	public static void listBook() {
	    if (list.isEmpty()) { 
	        System.out.println("[등록된 도서가 없습니다.]");
	        return;
	    }

	    System.out.println("===== 도서 목록 =====");
	    for (Book book : list) {
	        System.out.println(book);
	    }
	}
	
}