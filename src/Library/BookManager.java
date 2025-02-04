package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
	//도서관리
	
	private List<Book> list;
	
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
					.filter(w-> w.getTitle().contains(title))
					.collect(Collectors.toList());
			
		} else if(book.getAuthor() != null) {
			String author = book.getAuthor();
			
			return list.stream()
					.filter(w-> w.getAuthor().contains(author))
					.collect(Collectors.toList());
			
		} else if(book.getPublisher() != null) {
			String publisher = book.getPublisher();
			
			return list.stream()
					.filter(w-> w.getPublisher().contains(publisher))
					.collect(Collectors.toList());
			
		} else if(book.getBookCode() != null) {
			String bookCode = book.getBookCode();
			
			return list.stream()
					.filter(w-> w.getBookCode().contains(bookCode))
					.collect(Collectors.toList());
		}
		
		System.out.println("[일치하는 도서가 없습니다.]");
		return null;
		
	}

	public void print(List<Book> tmpList) {
		if(tmpList == null || tmpList.isEmpty()) {
			System.out.println("[일치하는 도서가 없습니다.]");
			return;
		}
		
		tmpList.stream().forEach(b-> System.out.println(b));
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
}
