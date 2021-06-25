package member.vo;

public class MemberVO {
	private String member_id, member_pw, email, phone;
	private int cash_total;
	public MemberVO(){
		
	}
	public MemberVO(String member_id, String member_pw, String email, String phone) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.email = email;
		this.phone = phone;
	}
	public MemberVO(String member_id, String member_pw, String email, String phone, int cash_total) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.email = email;
		this.phone = phone;
		this.cash_total = cash_total;
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getCash_total() {
		return cash_total;
	}
	public void setCash_total(int cash_total) {
		this.cash_total = cash_total;
	}
	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", member_pw=" + member_pw + ", email=" + email + ", phone=" + phone
				+ ", cash_total=" + cash_total + "]";
	}

	
}
