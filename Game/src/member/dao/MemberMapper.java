package member.dao;

import java.util.ArrayList;

import member.vo.CashVO;
import member.vo.MemberVO;
import member.vo.QuizVO;
import member.vo.ScoreVO;

public interface MemberMapper {
	public MemberVO getMember(String id);

	public int join(MemberVO vo);

	public ArrayList<MemberVO> MemberAll();

	public ArrayList<ScoreVO> ScoreAll();

	public int charge(CashVO vo);

	public int deleteMember(String d);

	public int getCash_total(String id);

	public int charge_add(CashVO vo);

	public ArrayList<CashVO> Cashtable();
	
	public int UpAndDownScoreNew(ScoreVO vo);

	public QuizVO getQuiz(int i);

	public int QuizNew(ScoreVO vo3);

	public QuizVO getQuiz2(int i);

}
