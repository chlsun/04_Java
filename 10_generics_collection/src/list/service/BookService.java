package list.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import list.dto.BookDTO;

/**
 * 도서 관리 프로그램 기능 제공 클래스
 */
public class BookService {

	private List<BookDTO> bookList = new ArrayList<BookDTO>(); 
	
	// 기본 생성자
	public BookService() {
		// 샘플 데이터 추가
		bookList.add(new BookDTO("소년이 온다", "한강", 15000, "창비"));
		bookList.add(new BookDTO("초역 부처의 말", "코이케 류노스케", 17800, "포레스트북스"));
		bookList.add(new BookDTO("채식주의자", "한강", 15000, "창비"));
		bookList.add(new BookDTO("작별하지 않는다", "한강", 13000, "문학동네"));
		bookList.add(new BookDTO("모순", "양귀자", 13000, "쓰다"));
	}
		
	// getter
	public List<BookDTO> getBookList() {
		return bookList;
	}
	
	/** 전달 받은 index 번째 bookList 요소 반환
	 * 단, 범위 초과 시 null 반환
	 * 
	 * @param index
	 * @return
	 */
	public BookDTO selectIndex(int index) {
		
		//범위 초과 검사 -> 초과 시
		if(!checkIndex(index)) return null;
		
		return bookList.get(index);
	}
	
	
	/** 
	 * 전달 받은 index가 정상 범위인지 확인
	 * @param index
	 * @return 정상 true, 아니면 false
	 */
	
	public boolean checkIndex(int index) {
		if(0 > index || index >= bookList.size()) {
			
			// 범위 초과 시 false
			return false;
		}
		
		return true; // 정상 범위 true
	}
	
	
	/** 전달 받은 book을 bookList에 추가
	 * 단, 책 정보가 모두 일치하는 책이 있다면 추가 X
	 * @param book
	 * @return 추가 O : true, 추가 X : false
	 */
	public boolean addBook(BookDTO book) {
		
		// 책 정보 비교 방법 1) 필드 값 하나씩 꺼내서 비교
		// 책 정보 비교 방법 2) equals() 오버라이딩 후 이용
		
//		for(BookDTO b : bookList) {
//			if(b.equals(book)) return false;
//		}
		
		// BookDTO의 equals()를 오버라이딩 했기 때문에
		// List가 제공하는 contains() (포함하는지 확인) 사용 가능
		if(bookList.contains(book)) return false;
		
		return bookList.add(book); 
	  // 컬렉션은 크기 제한이 없어서 무조건 추가 성공! (true 반환)
	}
	
	
	/** 전달 받은 index번째 요소의 가격을 newPrice로 수정 
	 * 
	 * @param index
	 * @param newPrice
	 * @return "[책제목] 가격이 [이전가격] -> [수정된 가격]으로 수정 되었습니다"
	 */
	public String modifyBookPrice(int index, int newPrice) {
		
		BookDTO target = bookList.get(index);
		
	  int beforePrice = target.getPrice();
	  
	  if(beforePrice == newPrice) {
	  	return "기존 가격과 입력된 가격이 동일합니다.";
	  }
	  
	  target.setPrice(newPrice);
		
		return String.format("%s 가격이 %d -> %d으로 수정 되었습니다.",
				target.getTitle(),beforePrice, target.getPrice());
	}
	
	
	public BookDTO deleteBook(int index) {
		
		if(!checkIndex(index)) {
			return null;
		}
		
		return bookList.remove(index);
		// bookList에서 index 번째 요소를 제거
		// == index 번째 요소를 뽑아냄
		
		
	}
	
	
	/**
	 * 제목이 일치하는 책 반환
	 * @param title : 찾으려는 책 제목
	 * @return
	 */
	public String selectTitle(String title) {
		
		String result = "";
		
		for(BookDTO book: bookList) { // bookList에서 하나씩 꺼냄
			
			// 꺼낸 책의 제목과 전달 받은 책의 제목이 같을 경우
			if(book.getTitle().equals(title)) { 
				result += String.format("책 제목 : %s / 책 저자 : %s / 책 가격 : %d / 책 출판사 : %s\n",
						book.getTitle(), book.getAuthor(), book.getPrice(), book.getPublisher());
			}
		}
		
		return result.equals("") ? "검색 결과 없음" : result;
		
	}
	
	public boolean removeBookTitle(String title) {
		
		for(BookDTO book: bookList){ // bookList에서 하나씩 꺼냄
			
			// 꺼낸 책의 제목과 전달 받은 책의 제목이 같을 경우
			if(book.getTitle().equals(title)) { 
				return bookList.remove(book);
			}
		}
		return false;
	}
	
	
	
	
	public String selectPublisherAll(String publisher) {
		String result = "";
		
		for(BookDTO book: bookList) { // bookList에서 하나씩 꺼냄
			
			// 꺼낸 책의 제목과 전달 받은 책의 제목이 같을 경우
			if(book.getPublisher().equals(publisher)) { 
				result += String.format("책 제목 : %s / 책 저자 : %s / 책 가격 : %d / 책 출판사 : %s\n",
						book.getTitle(), book.getAuthor(), book.getPrice(), book.getPublisher());
			}
		}
		
		return result.equals("") ? "검색 결과 없음" : result;
	}
	
	
	
	public List<BookDTO> selectAuthorAll(String author) {
		// BookDTO[] & List<BookDTO>
		List<BookDTO> removeBooks = new ArrayList<BookDTO>();
		
		for(BookDTO book: bookList) { // bookList에서 하나씩 꺼냄
			
			// 꺼낸 책의 제목과 전달 받은 책의 제목이 같을 경우
			if(book.getAuthor().equals(author)) { 
				removeBooks.add(book);
			}
		}
		
		return removeBooks;
	}
	
	
	public String searchBook(String search) {
		String result = "";
		
		for(BookDTO book: bookList) { // bookList에서 하나씩 꺼냄
			
			// 꺼낸 책의 제목과 전달 받은 책의 제목이 같을 경우
			if(book.getAuthor().contains(search) || book.getTitle().contains(search)) { 
				result += String.format("책 제목 : %s / 책 저자 : %s / 책 가격 : %d / 책 출판사 : %s\n",
						book.getTitle(), book.getAuthor(), book.getPrice(), book.getPublisher());
			}
		}
		
		return result.equals("") ? "검색 결과 없음" : result;
	}
	
	
	public int removeBooks(String title) {
		List<BookDTO> removeBooks = new ArrayList<BookDTO>();
		
		for(BookDTO book: bookList){ // bookList에서 하나씩 꺼냄
			
			// 꺼낸 책의 제목과 전달 받은 책의 제목이 같을 경우
			if(book.getTitle().equals(title)) { 
				removeBooks.add(book);
			}
		}
		
		for(BookDTO book: removeBooks) {
			bookList.remove(book);
		}
		
		return removeBooks.size();
	}
	
	/**
	 * 제목 오름차순 정렬
	 */
	public void bookListSorting() {
		
		Collections.sort(bookList);
		// 원본 리스트가 정렬된 형태로 변경됨
			
	}
}
