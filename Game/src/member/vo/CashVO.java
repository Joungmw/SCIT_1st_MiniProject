package member.vo;

public class CashVO {
   // 멤버 ID, 변경된 시기
   private String member_id , cash_date;
   // CASH의 번호 , CASH의 변화값
   private int cash_number, cash_move;
   
   
   public CashVO(){ 
   }
   
   public CashVO(String member_id, int cash_move){
	   this.member_id = member_id;
	   this.cash_move = cash_move; 
   }
   
   public CashVO(int cash_number, String member_id,  int cash_move , String cash_date) {
      super();
      this.cash_number = cash_number;
      this.member_id = member_id;
      this.cash_move = cash_move;
      this.cash_date = cash_date;
   }
   
   public CashVO(int cash_number, String member_id,  int cash_move) {
	      super();
	      this.member_id = member_id;
	      this.cash_number = cash_number;
	      this.cash_move = cash_move;
	   }

public String getMember_id() {
	return member_id;
}

public void setMember_id(String member_id) {
	this.member_id = member_id;
}

public String getCash_date() {
	return cash_date;
}

public void setCash_date(String cash_date) {
	this.cash_date = cash_date;
}

public int getCash_number() {
	return cash_number;
}

public void setCash_number(int cash_number) {
	this.cash_number = cash_number;
}

public int getCash_move() {
	return cash_move;
}

public void setCash_move(int cash_move) {
	this.cash_move = cash_move;
}

@Override
public String toString() {
	return "CashVO [member_id=" + member_id + ", cash_date=" + cash_date + ", cash_number=" + cash_number
			+ ", cash_move=" + cash_move + "]";
}
   
   
   
   
}