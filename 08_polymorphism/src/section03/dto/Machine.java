package section03.dto;

public interface Machine {
	
	public static final int MAX_NUM = 1_000_000_001;
	
	public abstract void powerOn();
	public abstract void powerOff();
	
	int plus(int a, int b);

}
