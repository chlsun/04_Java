package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dto.Todo;
import service.TodoListServiceImpl;

public class TodoListView {
	
	TodoListServiceImpl service = null;
	Scanner sc = null;
	
	
	public TodoListView() {
		
		try {
			service = new TodoListServiceImpl();
			sc = new Scanner(System.in);
		}catch(Exception e) {
			System.out.println("*** 프로그램 실행 중 오류 발생 ***");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	

	public void menuListView() {
		
		int menuIndex = -1;
			
			do {
				try {
				System.out.println("===== 스케줄 플래너 =====\n");
				
				System.out.println("1. 스케줄 목록");
				System.out.println("2. 선택된 스케줄 확인");
				System.out.println("3. 스케줄 추가");
				System.out.println("4. 스케줄 완료여부 수정");
				System.out.println("5. 스케줄 수정");
				System.out.println("6. 스케줄 삭제");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택하기 >>> ");
				menuIndex = sc.nextInt();
				sc.nextLine();
				
				switch(menuIndex) {
				case 1: selectTodoList(); break;
				case 2: selectTodoIndex(); break;
				case 3: addTodo(); break;
				case 4: updateComplete(); break;
				case 5: updateTodoIndex(); break;
				case 6: deleteTodoIndex(); break;
				case 0: return;
				default : System.out.println("메뉴를 잘못 선택하셨습니다"); continue;
				}
				
				System.out.println("=====================================================================");
				
				}catch (NumberFormatException e) {
					System.out.println("\n### 숫자만 입력 해주세요 ###\n");
					menuIndex = -1; 
					
				}catch(InputMismatchException e){
					System.out.println("잘못 입력하셨습니다");
					sc.nextLine();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}while(menuIndex != 0);
			
		
		
		
		
	}
	
	
	public void selectTodoList() {
		System.out.println("============== 1. 스케줄 목록 ==============/n");
		
		System.out.println(service.selectAll());
		
	}
	
	public void selectTodoIndex() throws InputMismatchException{
		System.out.println("============== 2. 선택된 스케줄 확인 ==============\n");
		
		System.out.print("인덱스 번호 입력 : ");
		int inputIndex = sc.nextInt();
		sc.nextLine();
		
		System.out.println(service.selectTodoIndex(inputIndex));
		
	}
	
	
	public void addTodo() throws InputMismatchException, IOException{
		System.out.println("============== 3. 스케줄 추가 ==============\n");
		
		System.out.print("할 일 제목 입력 : ");
		String title = sc.nextLine();
		
		String inputLine = "";
		String content = "";
		System.out.println("세부 내용 작성 (입력 종료 시 !wq 작성 후 엔터)");
		System.out.println("-----------------------------------------------");
		
		do {
			inputLine = sc.nextLine();
			
			if(!inputLine.equals("!wq")) {
				content += inputLine + "\n";
			}
		}while(!inputLine.equals("!wq"));
		
		System.out.println("-----------------------------------------------");
		
		
		System.out.println(service.addTodo(title, content));
		
		
		
	}
	
	public void updateComplete() throws IOException{
		System.out.println("============== 4. 스케줄 완료여부 수정 ==============\n");
		
		System.out.print("O <-> X 변경할 인덱스 번호 입력 : ");
		int inputIndex = sc.nextInt();
		sc.nextLine();
		
		System.out.println(service.updateComplete(inputIndex));
	}
	
	
	public void updateTodoIndex() throws IOException{
		System.out.println("============== 5. 스케줄 수정 ==============\n");
		
		System.out.print("수정할 스케줄 인덱스 번호 입력 : ");
		int inputIndex = sc.nextInt();
		sc.nextLine();
		
		System.out.println(service.beforeTodoIndex(inputIndex));
		
		System.out.print("수정할 제목 : ");
		String title = sc.nextLine();
		
		String inputLine = "";
		String content = "";
		System.out.println("수정할 세부 내용 작성 (입력 종료 시 !wq 작성 후 엔터)");
		System.out.println("-----------------------------------------------");
		
		do {
			inputLine = sc.nextLine();
			
			if(!inputLine.equals("!wq")) {
				content += inputLine + "\n";
			}
		}while(!inputLine.equals("!wq"));
		
		System.out.println("-----------------------------------------------");
		
		System.out.println(service.updateTodoIndex(inputIndex, title, content));
	}
	
	
	public void deleteTodoIndex() throws IOException{
		System.out.print("삭제할 스케줄 인덱스 번호 입력 : ");
		int inputIndex = sc.nextInt();
		sc.nextLine();
		
		System.out.println(service.deleteTodoIndex(inputIndex));
	}
	
	
	
}
