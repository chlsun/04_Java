package service;

import java.io.IOException;
import java.util.List;

import dto.Todo;

public interface TodoListService {
	
	public String selectAll();
	
	public List<Todo> getTodoList();
	
	public List<Todo> getSuccessList();
	
	public String selectTodoIndex(int index);
	
	public String addTodo(String title, String content) throws IOException;

	public String updateComplete(int index) throws IOException;
	
	public String beforeTodoIndex(int index);
	
	public String updateTodoIndex(int index, String title, String content) throws IOException;
	
	public String deleteTodoIndex(int index) throws IOException;
}
