package Library;

import java.util.ArrayList;
import java.util.List;


import lombok.Data;

@Data
public class MemberManager {
	
	//회원관리
	private List<Member> members;
	
	public MemberManager(List<Member> members) {
		if(members == null) {
			this.members = new ArrayList<Member>();
		}
		else {
			this.members = members;
		}
	}
	
	public MemberManager() {
		members = new ArrayList<Member>();
	}
	
	public boolean insertMember(Member mb) {
		if(mb == null || members == null) {
			return false;
		}
		if(members.contains(mb)) {
			return false;
		}
		return members.add(mb);
	}
	
	public Member getMember(Member mb) {
		int index = members.indexOf(mb);
		
		return index < 0 ? null : members.get(index);
	}
	
	public boolean updateMember(Member member, String newName, String newNum) {
        if(member == null) return false;
        for (Member m : members) {
            if(m.equals(member)) {
                if(newName != null && !newName.isEmpty()) m.setName(newName);
                if(newNum != null && !newNum.isEmpty()) m.setNum(newNum);
                return true;
            }
        }
        return false;
    }
	
	public boolean deleteMember(Member mb) {
		if(mb == null || members == null) {
			return false;
		}
		return members.remove(mb);
	}
	
	public void printMember(Member mb) {
		if(mb == null) {
			System.out.println("회원 정보가 없습니다.");
			return;
		}	
		if(members == null) {
			System.out.println("회원 리스트가 없습니다.");
			return;
		}
		Member tmp = getMember(mb);
		if(tmp == null) {
			System.out.println("일치하는 회원이 없습니다.");
			return;
		}
		tmp.print();
	}
	
	
	
	
}
