package section03.run;

import section03.dto.CYSCalculator;
import section03.dto.CYSCalculator2;
import section03.dto.CYSCalculator3;
import section03.dto.Calculator;

public class interfaceRun {
	public static void main(String[] args) {
		
		// 인터페이스를 부모 타입 참조 변수로 하여
		// Calculator 구현체 생성하기
		// (업 캐스팅 + 동적 바인딩)
		
//		Calculator calc = new CYSCalculator();
		
		/* 같은 Calculator 인터페이스를 상속 받은 다른 객체 생성
		 * -> Calculator가 제공하는 같은 이름의 메서드가 존재
		 * -> 다른 코드 수정 없이 객체 수정 부분만 수정해도
		 * 		새 객체의 기능을 모두 호출 가능
		 * 		-> 유지 보수성 향상!!
		 */
		Calculator calc = new CYSCalculator2();
		
		System.out.println("plus : " + calc.plus(16, 5));
		System.out.println("plus : " + calc.minus(16, 5));
		System.out.println("plus : " + calc.multi(126556, 161551555));
		System.out.println("plus : " + calc.div(16, 5));
		System.out.println("plus : " + calc.mod(16, 5));
		System.out.println("plus : " + calc.pow(16, 5));
		System.out.println("plus : " + calc.areaOfCircle(16.5));

	}
}
