package loop.practice;

import java.util.Scanner;

/**
 * 기능 제공용 클래스
 */
public class LoopPractice {
	
	Scanner sc = new Scanner(System.in);
	
	public void displayMenu() {
		do {
		System.out.print("\n메뉴 번호 입력 >> ");
		int input = sc.nextInt();
		
		// (중요) 같은 클래스 내 필드, 메서드는 이름만 부르면 호출 가능!!
		switch(input) {
		case 1 : practice1(); break;
		case 2 : practice2(); break;
		case 3 : practice3(); break;
		case 4 : practice4(); break;
		case 5 : practice5(); break;
		case 6 : practice6(); break;
		case 7 : practice7(); break;
		case 8 : practice8(); break;
		case 9 : practice9(); break;
		case 10 : practice10(); break;
		case 11 : practice11(); break;
		case 12 : practice12(); break;
		case 13 : practice13(); break;
		case 14 : practice14(); break;
		case 15 : practice15(); break;
		case 16 : practice16(); break;
		default : System.out.println("없는 메뉴 번호 입니다"); return;
		}
		}while(true);
	}
	
	public void practice1(){
		System.out.print("1 이상의 숫자를 입력하세요 : ");
		int input = sc.nextInt();
		
		if(input < 1) {
			System.out.println("1 이상의 숫자를 입력해주세요.");
		}
		
		for(int i=1; i<=input; i++) {
			System.out.print(i + " ");
		}
	}
	
	
	public void practice2(){
		System.out.print("1 이상의 숫자를 입력하세요 : ");
		int input = sc.nextInt();
		
		if(input < 1) {
			System.out.println("1 이상의 숫자를 입력해주세요.");
		}
		
		for(int i=input; i>=1; i--) {
			System.out.print(i + " ");
		}
	}
	public void practice3(){
		System.out.print("정수를 하나 입력하세요 : ");
		int input = sc.nextInt();
		
		int sum = 0;
		
		for(int i=1; i<=input; i++) {
			sum += i;
			if(i == input) {
				System.out.print(i);
				continue;
			}
			System.out.print(i + " + ");
		}
		System.out.print(" = " + sum);
	}
	
	public void practice4(){
		System.out.print("첫 번째 숫자 : ");
		int input1 = sc.nextInt();
		
		System.out.print("두 번째 숫자 : ");
		int input2 = sc.nextInt();
		
		int var;
		
		if(input1 < 1 || input2 < 1) {
			System.out.println("1 이상의 숫자를 입력해주세요.");
			return;
		}
		
		if(input1 > input2) {
			var = input2;
			input2 = input1;
			input1 = var;
		}
		
		for(int i = input1; i<=input2; i++) {
			System.out.print(i + " ");
		}
	}
	public void practice5(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for(int i=1; i<=9; i++) {
			System.out.printf("%2d * %d = %d\n", input, i, input*i);
		}
	}
	public void practice6(){
		
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		if(input < 2 || input > 9) {
			System.out.println("2~9 사이 숫자만 입력해주세요.");
			return;
		}
		
		for(int i=input; i<=9; i++) {
			System.out.printf("===== %d단 =====\n", i);
			for(int j=1; j<=9; j++) {
				System.out.printf("%2d * %d = %d\n", i, j, i*j);
			}
		}
		
	}
	public void practice7(){
		
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for(int i=1; i<=input; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public void practice8(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for(int i=input; i>=1; i--) {
			for(int j=1; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public void practice9(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for(int i=1; i<=input; i++) {
			for(int j=input; j>=1; j--) {
				System.out.print(j>i ? " " : "*");
			}
			System.out.println();
		}
	}

	public void practice10(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for(int i=1; i<=(input * 2 - 1); i++) {
			
			for(int j=1; j<=(i>input ? (input * 2 - i) : i); j++) {
				System.out.print("*");
			}
			System.out.println();
			
		}
	}
	
	public void practice11(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for(int i=1; i<=input; i++) {
			for(int j=input; j>i; j--) {
				System.out.print(" ");
			}

			for(int j=1; j<=i*2-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public void practice12(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for(int i=1; i<=input; i++) {
			for(int j=1; j<=input; j++) {
				if(i == 1 || j == 1 || i == input || j == input) {
					System.out.print("*");
					continue;
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void practice13(){
		System.out.print("자연수 하나를 입력하세요 : ");
		int input = sc.nextInt();
		
		int count = 0;
		
		for(int i=1; i<=input; i++) {
			if(i%2 == 0 || i%3 == 0) {
				System.out.print(i + " ");
			}
			if(i%2 == 0 && i%3 == 0) {
				count++;
			}
		}
		System.out.printf("\ncount = %d", count);
	}
	
	public void practice14(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		int count = 0;
		
		if(input < 2) {
			System.out.println("잘못 입력하셨습니다");
			return;
		}
		
		for(int i=2; i<input; i++) {
				if(input % i == 0) {
					System.out.println("소수가 아닙니다");
					return;
				}
		}
		System.out.println("소수입니다");
	}
	
	public void practice15(){
		
		while(true) {
			System.out.print("숫자 : ");
			int input = sc.nextInt();
			
			int count = 0;
			
			if(input < 2) {
				System.out.println("잘못 입력하셨습니다");
				continue;
			}
			
			for(int i=2; i<input; i++) {
					if(input % i == 0) {
						System.out.println("소수가 아닙니다");
						return;
					}
			}
			System.out.println("소수입니다");
			return;
		}
	}
	
	public void practice16(){
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		int count = 0;
		boolean bool;
		
		for(int i=2; i<=input; i++) {
			bool = true;
			
			for(int j=2; j<i; j++) {
				if(i % j == 0) {
					bool = false;
					break;
				}
			}
			
			if(bool) {
				System.out.print(i + " ");
				count++;
			}
		}
		System.out.printf("\n2부터 %d까지 소수의 개수는 %d개입니다.", input, count);
	}
}
