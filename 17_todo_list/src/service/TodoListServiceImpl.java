package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.TodoListDAOImpl;
import dto.Todo;

public class TodoListServiceImpl implements TodoListService{
	
	TodoListDAOImpl dao = null;
	
	public TodoListServiceImpl() throws Exception, IOException, FileNotFoundException{
		dao = new TodoListDAOImpl();
	}
	
	@Override
	public String selectAll() {
		
		List<Todo> todoList = dao.getTodoList();
		List<Todo> successList = getSuccessList();
		
		
		if(todoList.size() == 0) {
			return "등록된 스케줄이 없습니다";
		}
		
		String result = "";
		
		result += String.format("[ 완료된 스케줄 개수 / 전체 스케줄 수 : %d / %d ]\n",
				successList.size(), todoList.size());
		
		for(int i=0; i<todoList.size(); i++) {
			Todo todo = todoList.get(i);
			
			String date = todo.getRegDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss"));
			
			result += String.format("[ %d]\t%s\t(%c)\t%-15s\n"
					, i, date, todo.isComplete() ? 'O' : 'X', todo.getDetail().split("\n")[0]);
		}
		
		return result;
	}
	
	
	@Override
	public List<Todo> getTodoList() {
		
		return dao.getTodoList();
	}
	
	@Override
	public List<Todo> getSuccessList(){
		List<Todo> todoList = dao.getTodoList();
		List<Todo> successList = new ArrayList<Todo>();
		
		if(todoList.size() == 0) {
			return successList;
		}
		
		for(Todo todo : todoList) {
			if(todo.isComplete()) {
				successList.add(todo);
			}
		}
		
		return successList;
	}
	
	@Override
	public String selectTodoIndex(int index) {
		
		List<Todo> todoList = dao.getTodoList();
		
		if(index < 0 || index >= todoList.size()) {
			return "### 입력한 인덱스 번호에 할 일(Todo)가 존재하지 않습니다 ###";
		}
		
		Todo target = todoList.get(index);
		
		String date = target.getRegDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss"));
		String result = "--------------------------------------------\n";
		
		result += String.format("제목 : %s\n등록일 : %s\n완료여부 : %c\n\n",
				target.getTilte(), date, target.isComplete() ? 'O' : 'X');
		
		result += String.format("[세부 내용]\n--------------------------------------------\n%s", target.getDetail());
		
		
		
		return result;
	}
	
	@Override
	public String addTodo(String title, String content) throws IOException{
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		List<Todo> todoList = dao.getTodoList();
		
		Todo todo = new Todo(title, content, false, currentDateTime);
			
		dao.addTodo(todo);
		
		return String.format("[%d] 인덱스에 추가 되었습니다", todoList.size() - 1);

	
	}
	
	
	@Override
	public String updateComplete(int index) throws IOException{
		
		List<Todo> todoList = dao.getTodoList();
		
		if(index < 0 || index >= todoList.size()) {
			return "### 입력한 인덱스 번호에 할 일(Todo)가 존재하지 않습니다 ###";
		}
		
		Todo target = todoList.get(index);
		
		target.setComplete(!target.isComplete());
		
		dao.saveTodoList();
		
		return "[변경 되었습니다]";
	}
	
	
	@Override
	public String beforeTodoIndex(int index) {
		
		List<Todo> todoList = dao.getTodoList();
		
		if(index < 0 || index >= todoList.size()) {
			return "### 입력한 인덱스 번호에 할 일(Todo)가 존재하지 않습니다 ###";
		}
		
		String result = "@@@@@@@@@@@[수정 전]@@@@@@@@@@@@@@@\n";
		result += selectTodoIndex(index);
		result += "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
		
		return result;
		
	}
	
	@Override
	public String updateTodoIndex(int index, String title, String content) throws IOException{
		
		Todo target = dao.getTodoList().get(index);
		
		target.setTilte(title);
		target.setDetail(content);
		
		dao.saveTodoList();
		
		return "[수정 되었습니다]";
	}
	
	public String deleteTodoIndex(int index) throws IOException{
		
		dao.getTodoList().remove(index);
		
		dao.saveTodoList();
		
		return "[삭제 되었습니다]";
	}

}
