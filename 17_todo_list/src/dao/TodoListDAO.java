package dao;

import java.io.IOException;
import java.util.List;

import dto.Todo;

public interface TodoListDAO {
	
	public List<Todo> getTodoList();
	
	public boolean addTodo(Todo todo) throws IOException;
	
	public void saveTodoList() throws IOException;
	
}
