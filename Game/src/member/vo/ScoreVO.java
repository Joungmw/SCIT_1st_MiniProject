package member.vo;


public class ScoreVO {
	private String member_id, join_date;
	private int quizscore, undscore, score_num;
	private int total=0;
	public ScoreVO() {
		
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ScoreVO(String member_id, int undscore) {
		this.member_id=member_id;
		this.undscore=undscore;
		this.total=undscore+quizscore;
	}
	public ScoreVO(String member_id, String join_date, int quizscore, int undscore, int score_num) {
		super();
		this.member_id = member_id;
		this.join_date = join_date;
		this.quizscore = quizscore;
		this.undscore = undscore;
		this.score_num = score_num;
		this.total=quizscore+undscore;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public int getQuizscore() {
		return quizscore;
	}
	public void setQuizscore(int quizscore) {
		this.quizscore = quizscore;
	}
	public int getUndscore() {
		return undscore;
	}
	public void setUndscore(int undscore) {
		this.undscore = undscore;
	}
	public int getScore_num() {
		return score_num;
	}
	public void setScore_num(int score_num) {
		this.score_num = score_num;
	}
	
	
}
