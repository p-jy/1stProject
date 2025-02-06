package Library;

public class BookManager {
	//도서관리
	
<<<<<<< Updated upstream
=======
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
		registBook(new Book("총류", "한국의 꽃살·기둥·누각", "임석재", "이화여자대학교출판부"));
		registBook(new Book("철학", "장자의 철학", "강신주", "태학사"));
		registBook(new Book("종교", "저항하는 그리스도인", "강성호", "복 있는사람"));
		registBook(new Book("사회과학", "수익 분배의 경제학", "오정석", "삼성경제연구소"));
		
	}
>>>>>>> Stashed changes
}
