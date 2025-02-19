package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import dto.Todo;

public class TodoListDAOImpl implements TodoListDAO{
	
	
	public final String FILE_NAME = "dao_file.bin";
	
	private List<Todo> todoList = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	public TodoListDAOImpl() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		File file = new File(FILE_NAME);
			
		if(file.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
					
				todoList = (ArrayList<Todo>)ois.readObject();
				
			}finally {
				if(ois != null)ois.close();
			}
		}else {
			todoList = new ArrayList<Todo>();
		}
	}
	
	
	public boolean addTodo(Todo todo) throws IOException{
		
		todoList.add(todo);
		
		saveTodoList();
		
		return true;
	}
	
	
	public List<Todo> getTodoList() {
		return todoList;
	}
	
	public void saveTodoList() throws IOException{
		
		File file = new File(FILE_NAME);
		
		if(file.exists()) {
			try {
				oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
				
				oos.writeObject(todoList);
				
			}finally {
				if(oos != null) oos.close();
			}
		}
	}
	
	
	
}
