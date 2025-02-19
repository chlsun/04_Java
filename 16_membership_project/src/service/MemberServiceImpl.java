package service;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import dto.Member;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 왜 Service, Dao 인터페이스를 만들어서 구현했을까?
 * - 인터페이스를 상속 받아 구현하면
 *   모든 자식 클래스가 똑같은 기능을 가지게되어
 *   비슷하게 생김!
 *
 *  -> 언제든지 서로 다른 자식 클래스로 대체 가능!!
 *    ==> 유지보수성 증가
 */

// MemberService를 상속 받아 구현
public class MemberServiceImpl implements MemberService{

    // dao 객체 부모 참조 변수 선언
    private MemberDAO dao = null;
    private String[] gradeArr = {"일반", "골드", "다이아"};


    // 기본 생성자
    // - MemberServiceImpl 객체 생성 시
    //   MemberDAOImpl 객체도 생성
    public MemberServiceImpl() 
    		throws FileNotFoundException, ClassNotFoundException, IOException {
        dao = new MemberDAOImpl();
    }

    
    //********************************
    // 추가, 수정 삭제 기능이 수행되면
    // 무조건 dao.saveFile() 수행!
    //********************************

    // 회원 추가
    @Override
    public boolean addMember(String name, String phone) throws IOException {
    		
    	for(Member member : dao.getMemberList()) {
    		if(member.getPhone().equals(phone)) {
    			return false;
    		}
    	}
    		Member member = new Member(name, phone, 0, 0);
    		
    		dao.addMember(member);
        return true;
    }


    // DAO에서 조회한 memberList를 그대로 반환
    @Override
    public List<Member> getMemberList() {
    	
    	//	dao에서 반환 받은 memberList를 그대로 view를 리턴
      return dao.getMemberList();
    }
    
    // 회원 전체 조회
    @Override
    public void selectAll() {
    	
    	if(dao.getMemberList().size() == 0) {
    		System.out.println("회원이 존재하지 않습니다.");
    		return;
    	}
    	
    	System.out.println("-------------------------------------------");
    	System.out.println("[이름]\t[휴대폰 번호]\t[누적 금액]\t[등급]");
    	
    	for(Member member : dao.getMemberList()) {
    		System.out.printf("%-10s %11s   %10d원  %3s\n", 
    				member.getName(), member.getPhone(), member.getAmount(),
    				gradeArr[member.getGrade()]);
    	}
    }


    // 이름 검색
    @Override
    public List<Member> selectName(String searchName) {
    	
    		List<Member> returnList = new ArrayList<Member>();
    	
    		for(Member member : dao.getMemberList()) {
    			if(member.getName().equals(searchName)){
    				returnList.add(member);
    			}
    		}
    	
        return returnList;
    }


    // 금액 누적
    @Override
    public String updateAmount(Member target, int acc) throws IOException {
    		
    	int beforeAmount = target.getAmount();
    	int beforeGrade = target.getGrade();
    	target.setAmount(beforeAmount + acc);
    	
    	String result = String.format("%s 회원님의 누적 금액\n"
        	+ "%d -> %d\n", target.getName(), beforeAmount, target.getAmount());
    	
    	
    	if(target.getAmount() >= 1000000) {
    		target.setGrade(2);
    	}else if(target.getAmount() >= 100000) {
    		target.setGrade(1);
    	}else {
    		target.setGrade(0);
    	}
    	
    	if(beforeGrade != target.getGrade()) {
    		result += String.format("* %s * 등급으로 변경 되셨습니다", gradeArr[target.getGrade()]);
    	}
    	
    	dao.saveFile();
    	
      return result;
    }


    //회원 정보(전화번호) 수정
    @Override
    public String updateMember(Member target, String phone) throws IOException {
    	
    	
    	for(Member member : dao.getMemberList()) {
    		if(member.getPhone().equals(phone)) {
    			return "### 중복되는 휴대폰 번호가 있습니다 ###\n";
    		}
    	}
    	
    	String beforePhone = target.getPhone();
    	
    	target.setPhone(phone);
    	
    	
    	dao.saveFile();
    	
      return String.format("%s님의 전화번호가 변경 되었습니다\n"
       	+ "%s -> %s", target.getName(), beforePhone, target.getPhone());
        
        // ex)
        // 홍길동님의 전화번호가 변경 되었습니다
        // 01012341234 -> 01044445555
    }


    // 회원 탈퇴
    @Override
    public String deleteMember(Member target) throws IOException {
    	String beforeName = target.getName();
    	dao.getMemberList().remove(target);
    	
    	dao.saveFile();
    	
      return beforeName + " 회원이 탈퇴 처리 되었습니다\n"; // 결과 문자열 반환
        // ex)
        // "홍길동 회원이 탈퇴 처리 되었습니다"
    }

}