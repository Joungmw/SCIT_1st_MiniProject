package member.vo;

public class QuizVO {

	private int quiz_num;
	private String quiz , qoption, answer;
	public QuizVO() {
	
	}
	public QuizVO(int quiz_num) {
		this.quiz_num=quiz_num;
	}
	public QuizVO(int quiz_num, String quiz, String qoption, String answer) {
		super();
		this.quiz_num = quiz_num;
		this.quiz = quiz;
		this.qoption = qoption;
		this.answer = answer;
	}
	public int getQuiz_num() {
		return quiz_num;
	}
	public void setQuiz_num(int quiz_num) {
		this.quiz_num = quiz_num;
	}
	public String getQuiz() {
		return quiz;
	}
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	public String getQoption() {
		return qoption;
	}
	public void setQoption(String qoption) {
		this.qoption = qoption;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	@Override
	public String toString() {
		return "QuizVO [quiz_num=" + quiz_num + ", quiz=" + quiz + ", qoption=" + qoption + ", answer=" + answer + "]";
	} 
	
	
	
	
}
