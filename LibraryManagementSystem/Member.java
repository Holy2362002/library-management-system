public class Member {

	private String memberId;
	private String name;

	public Member(String memberId,String name) {
		this.memberId = memberId;
		this.name = name;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
        return String.format("Member ID: %-10s Name: %-20s", memberId, name);
    }
}