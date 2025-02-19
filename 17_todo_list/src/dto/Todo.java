package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Todo implements Serializable{
	
	private String tilte;
	private String detail;
	private boolean complete;
	private LocalDateTime regDate;
	
	
	public Todo() {};
	
	public Todo(String tilte, String detail, boolean complete, LocalDateTime regDate) {
		super();
		this.tilte = tilte;
		this.detail = detail;
		this.complete = complete;
		this.regDate = regDate;
	}
	
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Todo [tilte=" + tilte + ", detail=" + detail + ", complete=" + complete + ", regDate=" + regDate + "]";
	}
	
}
