package section01;

import java.util.Objects;

public class Book {
	private long bookNumber; 	// 책 번호
	private String title; 		// 제목
	private String author; 		// 저자
	private int price;				// 가격
	
	
	
	public Book() {
		super();
	}

	public Book(long bookNumber, String title, String author, int price) {
		super();
		this.bookNumber = bookNumber;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public long getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(long bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [BookNumber=" + bookNumber + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}

	
	
	
	
	/* equals() 오버라이딩
	 * -> 비교하려는 두 객체의 필드 값이 같은지 비교
	 * 		(동등 비교)
	 */
	@Override
	public boolean equals(Object obj) {
		
		// 같은 객체를 참조하고 있는 경우(주소가 같은 경우)
		// -> 같은 객체 비교하는데 시간 낭비 하지 말자
		if(this == obj) return true;	// 무조건 같음 -> true
		
		
		// 비교 대상이 없는 경우 -> 무조건 false
		if(obj == null) return false; 
		
		// 비교 대상이 같은 타입의 객체인지 확인
		// -> 다른 타입이면 비교조차 안하겠다 -> false 반환
		if( !(obj instanceof Book) ) return false;
		
		if(this.getClass() != obj.getClass()) return false;
		
		
		// 전달 받은 매개 변수를 Book으로 다운캐스팅
		Book other = (Book)obj;
		
		// 현재 객체와 전달 받은 객체의 bookNumber가 다르면
		if(this.bookNumber != other.bookNumber) return false;
		
		// 위에 조건식 처럼 단순 비교연산자로 불가능 
		// Why? 문자열(String)은 객체여서 참조주소의 비교가 됨
		// this.title.equals(other.title) << this.title 은 String이기 때문에
		// String 클래스안에서 오버라이딩 된 eqauls로 문자열 비교
		// 현재 객체와 전달 받은 객체의 author이 다를 경우
		if( !this.title.equals(other.title) ) return false;
		
		// 현재 객체와 전달 받은 객체의 author이 다를 경우
		if( !this.author.equals(other.author) ) return false;
		
		// 현재 객체와 전달 받은 객체의 price이 다를 경우
		if(this.price != other.price) return false;
		
		
		// 위 if문이 하나도 수행되지 않음
		// == 모든 필드가 같음 == 동등 -> true 반환
		return true;
	}

	
	// --------------------------------------------------
	
	/* hashCode() 오버라이딩
	 * - 동등한 객체는 같은 식별 번호를 가져야 한다
	 * - A.equals(B) -> true 이면
	 *   A.hashCode() == B.hashCode() -> true 이다
	 */

	@Override
	public int hashCode() {
		
		return Objects.hash(bookNumber, title, author, price);
		
		/* 옛날 방식 */
//		int result = 1;
//		final int PRIME = 31;
//		
//		result *= (int)bookNumber * PRIME;
//		result *= title.hashCode() * PRIME;
//		result *= author.hashCode() * PRIME;
//		result *= price * PRIME;
//		
//		return result;
	}
	
	
	/* 자동완성으로 만든 equals & hashCode 오버라이딩 */
	// art + shift + s -> h
	
//	@Override
//	public String toString() {
//		return "Book [BookNumber=" + bookNumber + ", title=" + title + ", author=" + author + ", price=" + price + "]";
//	}
//
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Book other = (Book) obj;
//		return Objects.equals(author, other.author) && bookNumber == other.bookNumber && price == other.price
//				&& Objects.equals(title, other.title);
//	}
//
//	
//	// --------------------------------------------------
//	
//	/* hashCode() 오버라이딩
//	 * - 동등한 객체는 같은 식별 번호를 가져야 한다
//	 * - A.equals(B) -> true 이면
//	 *   A.hashCode() == B.hashCode() -> true 이다
//	 */
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(author, bookNumber, price, title);
//	}
	
	
}
