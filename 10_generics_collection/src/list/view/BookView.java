package list.view;

import java.util.List;
import java.util.Scanner;

import list.dto.BookDTO;
import list.service.BookService;

/**
 * 도서 관리 프로그램
 */
public class BookView {
	private Scanner sc = new Scanner(System.in);
	private BookService service = new BookService();
	
	public void displayMenu() {
		int input = 0; // 메뉴 번호를 저장할 변수
		
		do {
			System.out.println("\n***** 도서 관리 프로그램 *****");
			System.out.println("1. 전체 조회");
			System.out.println("2. index 번호로 조회");
			System.out.println("3. 책 추가하기");
			System.out.println("4. 책 가격 수정하기");
			System.out.println("5. 책 제거하기(index)");
			
			// 추가 메뉴
			System.out.println("6. 제목이 일치하는 책 1권 조회하기");
			System.out.println("7. 제목이 일치하는 책 제거하기");
			
			System.out.println("8. 출판사가 일치하는 책 모두 조회하기");
			System.out.println("9. 저자가 일치하는 책 모두 조회하기");
			
			System.out.println("10. 검색어가 제목, 저자에 포함된 모든 책 조회하기");
			
			System.out.println("11. 제목이 일치하는 책 모두 제거하기");
			
			System.out.println("12. bookList 제목 오름차순으로 정렬시키기");
			
			System.out.println("0. 종료");
			System.out.println(); // 줄 바꿈
			
			System.out.print("메뉴 번호 입력 >> ");
			input = sc.nextInt();
			sc.nextLine(); // 입력 버퍼에 남아있는 문자열 제거(오류 방지)
			System.out.println("--------------------------------");
			
			switch(input) {
			case 1: selectAll(); break;
			case 2: selectIndex(); break;
			case 3: addBook(); break;
			case 4: modifyBookPrice(); break;
			case 5: deleteBook(); break;
			case 6: selectTitle(); break;
			case 7: removeBookTitle(); break;
			case 8: selectPublisherAll(); break;
			case 9: selectAuthorAll(); break;
			case 10: searchBook(); break;			
			case 11: removeBooks(); break;			
			case 12: bookListSorting(); break;			
			case 0: System.out.println("*** 프로그램이 종료됩니다 ***"); return;
			default: System.out.println("잘못 입력하셨습니다.");
			}
			
		}while(input != 0);
	}

	/** 
	 * 전체 조회
	 * - BookService의 필드 bookList를 얻어와
	 *  일반 for문을 이용하여 모든 요소 정보 출력
	 */
	private void selectAll() {
		System.out.println("\n### 전체조회 ###\n");
		
		List<BookDTO> list = service.getBookList();
		
		
		// int size() : 저장된 객체의 개수 반환
		// boolean isEmpty() : 저장된 객채가 없으면 true 반환
//		if(list.size() == 0) {
		if(list.isEmpty()) {
			System.out.println("저장된 책이 존재하지 않습니다");
			return;
		}
		
		// 모든 요소 정보 출력
		for(int i=0; i<list.size(); i++) {
			System.out.println(i + ") " + list.get(i));
		}
	}
	
	
	/**
	 * 조회하려는 책의 index 번호를 입력 받아 책 정보 출력
	 * 
	 * - bookService로부터
	 * 
	 * - 단, index 번호가 bookList의 번위를 초과하면
	 *  "해당 인댁스에 책이 존재 하지 않습니다" 출력
	 */
	private void selectIndex() {
		System.out.println("\n### index 번호로 조회 ###\n");
		
		System.out.print("조회할 책 index 번호 : ");
		int index = sc.nextInt();
		
		
		BookDTO book = service.selectIndex(index);
		
		if(book == null) {
			System.out.println("해당 인댁스에 책이 존재 하지 않습니다");
			return;
		}
		
		System.out.println();
		System.out.println("제목 : " + book.getTitle());
		System.out.println("저자 : " + book.getAuthor());
		System.out.println("가격 : " + book.getPrice());
		System.out.println("출판사 : " + book.getPublisher());
	}
	
	
	/** 책 정보(제목, 저자, 가격, 출판사)를 입력 받아
	 * BookService의 bookList에 마지막 요소로 추가
	 * 
	 * 단, 모든 정보가 일치하는 책은 추가 X (중복 저장 X)
	 */
	private void addBook() {
		System.out.println("\n### 책 추가하기 ###\n");
		
		System.out.print("제목 : ");
		String title = sc.nextLine(); 
		// 엔터가 입력되기 전 까지의 문자열 얻어오기(띄어쓰기 가능)
		
		System.out.print("저자 : ");
		String author = sc.nextLine(); 
		
		System.out.print("가격 : ");
		int price = sc.nextInt(); 
		sc.nextLine(); // 입력 버퍼 남은 문자열 제거
		// -> sc.next(); / sc.nexxtInt() 등을 수행 후 무조건 작성
		
		System.out.print("출판사 : ");
		String publisher = sc.nextLine(); 
		
		// 서비스 호출
		boolean result = service.addBook(new BookDTO(title, author, price, publisher));
		
		if(result) { // 추가 성공
			System.out.println(title + " 책이 추가되었습니다");
			
		}else { // 추가 실패
			System.out.println(title + " 책이 이미 존재합니다");
		}
	}
	
	
	/** 인덱스 번호를 입력 받아
	 * - 해당 인덱스에 책이 없다면
	 * 	-> "해당 인덱스에 책이 존재하지 않습니다" 출력
	 * 
	 * - 책이 있다면
	 * 1)	"수정할 가격 입력 : " 출력
	 * 2) 스캐너로 가격 입력 받기 
	 * 3) 입력 받은 index 번째 요소의 가격 수정
	 * 4) "[책제목] 가격이 [이전가격] -> [수정된 가격]으로 수정 되었습니다"
	 * 	  출력
	 */
	private void modifyBookPrice() {
		System.out.println("\n### 책 가격 수정하기 ###\n");
		
		System.out.print("수정할 책 index 번호 : ");
		int index = sc.nextInt();
		
		if(!service.checkIndex(index)) {
			System.out.println("해당 인덱스에 책이 존재하지 않습니다");
			return;
		}
		
		System.out.print("수정할 가격 입력 : ");
		int newPrice = sc.nextInt();
		
		// 서비스 호출
		System.out.println(service.modifyBookPrice(index, newPrice));
	}
	
	// 안좋은 코드
//	private void modifyBookPrice2() {
//		System.out.println("\n### 책 가격 수정하기 ###\n");
//		
//		System.out.print("수정할 책 index 번호 : ");
//		int index = sc.nextInt();
//		
//		BookDTO target = service.selectIndex(index);
//		
//		if(target == null) {
//			System.out.println("해당 인덱스에 책이 존재하지 않습니다");
//			return;
//		}
//		
//		System.out.print("수정할 가격 입력 : ");
//		int price = sc.nextInt();
//		
//		int oldPrice = target.getPrice(); // 이전 가격을 저장
//		target.setPrice(price); // 새 가격으로 변경
//		
//		System.out.printf("%s 가격이 %d -> %d으로 수정 되었습니다.",
//				target.getTitle(), oldPrice, price);
//	}
	
	
	/** index번호를 입력 받아 책 제거
	 * 단, 해당 index에 책이 없으면
	 * "해당 인덱스에 책이 존재하지 않습니다" 출력
	 * 
	 * 있으면
	 * "[책제목] 책이 제거되었습니다." 출력
	 */
	private void deleteBook() {
		System.out.println("\n### 책 삭제하기(index) ###\n");
		
		System.out.print("삭제할 책의 index 번호 입력 : ");
		int index = sc.nextInt();
		
		BookDTO target = service.deleteBook(index);
		
		
		if(target == null) {
			System.out.println("해당 인덱스에 책이 존재하지 않습니다");
		}else {
			System.out.println(target.getTitle() + " 책이 제거되었습니다.");
		}

	}
	
	
	private void selectTitle() {
		System.out.println("\n### 제목이 일치하는 책 조회 ###\n");
		
		System.out.print("조회하고싶은 책의 제목 : ");
		String title = sc.nextLine(); 
		
		System.out.println(service.selectTitle(title));
	}
	
	
	private void removeBookTitle() {
		System.out.println("\n### 제목이 일치하는 책 제거 ###\n");
		
		System.out.print("제거하고싶은 책의 제목 : ");
		String title = sc.nextLine(); 
		
		if(!service.removeBookTitle(title)) {
			System.out.println("제목이 일치하는 책이 없습니다.");
			return;
		}
		
		System.out.println("삭제 되었습니다.");
	}
	
	
	/**
	 * 출판사가 일치하는 책 모두 조회하기
	 */
	private void selectPublisherAll() {
		System.out.println("\n### 출판사가 일치하는 책 모두 조회하기 ###\n");
		
		System.out.print("제거하고싶은 책의 제목 : ");
		String publisher = sc.nextLine(); 
		
		System.out.println(service.selectTitle(publisher));
	}
	
	/**
	 * 저자가 일치하는 책 모두 조회하기
	 */
	private void selectAuthorAll() {
		System.out.println("\n### 저자가 일치하는 책 모두 조회하기 ###\n");
		
		System.out.print("검색하고싶은 저자의 제목 : ");
		String author = sc.nextLine(); 
		
		
		List<BookDTO> selectBooks = service.selectAuthorAll(author);
		
		if(selectBooks.isEmpty()) {
			System.out.println("검색 결과 없음");
		}
		
		for(BookDTO book : selectBooks) {
			System.out.printf("책 제목 : %s / 책 저자 : %s / 책 가격 : %d / 책 출판사 : %s\n",
					book.getTitle(), book.getAuthor(), book.getPrice(), book.getPublisher());
		}
	}
	
	
	private void searchBook() {
		System.out.println("\n### 검색어가 제목, 저자에 포함된 모든 책 조회하기 ###\n");
		
		System.out.print("검색 : ");
		String search = sc.nextLine(); 
		
		System.out.println(service.searchBook(search));
		
	}
	
	
	private void removeBooks() {
		System.out.println("\n### 제목이 일치하는 책 모두 제거 ###\n");
		
		System.out.print("제거하고싶은 책의 제목 : ");
		String title = sc.nextLine(); 
		
		int removeCount = service.removeBooks(title);
		if(removeCount == 0) {
			System.out.println("제목이 일치하는 책이 없습니다.");
			return;
		}
		
		System.out.printf("%d권이 삭제 되었습니다.", removeCount);
		
		
	}
	
	private void bookListSorting() {
		System.out.println("\n### bookList 제목 오름차순으로 정렬시키기 ###\n");
		
		service.bookListSorting();
		
		System.out.println("정렬되었습니다.");
	}
}
