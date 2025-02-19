package section3.run;

import section3.service.ObjectService;

public class ObjectRun {
	public static void main(String[] args) {
		
		ObjectService service = new ObjectService();
		
//		service.outputMember();
//		 service.inputMember();
		
//		service.outputMemberList();
		service.inputMemberList();
		
	}
}