package member.dao;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import member.vo.CashVO;
import member.vo.MemberVO;
import member.vo.QuizVO;
import member.vo.ScoreVO;



public class MemberDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public int join(MemberVO vo) {
		SqlSession ss = null;
		int result = 0;

		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			result  = mapper.join(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) ss.close();
		}
		return result;
	}
	
	public MemberVO getMember_id(String id) {
		SqlSession ss = null;
		MemberVO vo = null;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			vo = mapper.getMember(id);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(ss != null) ss.close();
		}
		return vo;
	}
	
	public int getCash_total(String id) {
		SqlSession ss = null;
		int result = 0;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			result = mapper.getCash_total(id);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(ss != null) ss.close();
		}
		return result;
	}
	
	public ArrayList<MemberVO> MemberAll(){
		SqlSession ss = null;
		ArrayList<MemberVO> list = null;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			list = mapper.MemberAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(ss != null) ss.close();
		}
		return list;
	}

	public ArrayList<ScoreVO> ScoreAll() {
		SqlSession ss = null;
		ArrayList<ScoreVO> list = null;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			list = mapper.ScoreAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(ss != null) ss.close();
		}
		return list;
	}
	
	public int charge(CashVO vo) {
		SqlSession ss = null;
		int result = 0;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			result  = mapper.charge(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	public int charge_add(CashVO vo) {
		SqlSession ss = null;
		int result = 0;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			result  = mapper.charge_add(vo);
			ss.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (ss != null) ss.close();
		}
		return result;
	}

	public int deleteMember(String d) {
		SqlSession ss = null;
		int cnt=0;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			cnt = mapper.deleteMember(d);
			ss.commit();	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(ss != null) ss.close();
		}
		return cnt;
	}

	public ArrayList<CashVO> Cashtable() {
		SqlSession ss = null;
		ArrayList<CashVO> list = null;
		
		try {
			ss = factory.openSession();
			MemberMapper mapper = ss.getMapper(MemberMapper.class);
			list = mapper.Cashtable();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if(ss != null) ss.close();
		}
		return list;
	}

	// UND 결과의 score를 insert 하는 메소드
	public int UpAndDownScoreNew(ScoreVO vo) {
			SqlSession ss = null;
			int result = 0;
			try {
				ss = factory.openSession();
				MemberMapper mapper = ss.getMapper(MemberMapper.class);
				result  = mapper.UpAndDownScoreNew(vo);
				ss.commit();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (ss != null) ss.close();
			}
			return result;
		}
	// JLPT N1 유형 문제 가져오는 메소드
	public QuizVO getQuiz(int i) {
		      SqlSession ss = null;
		      QuizVO list = null;
		      try {
		         ss = factory.openSession();
		         MemberMapper mapper = ss.getMapper(MemberMapper.class);
		         list = mapper.getQuiz(i);
		      }
		      catch(Exception e){
		         e.printStackTrace();
		      }
		      finally {
		         if(ss != null) ss.close();
		      }
		      return list;
		}
	// JLPT N2 유형 문제 가져오는 메소드
	public QuizVO getQuiz2(int i) {
			SqlSession ss = null;
			QuizVO result = null;
			try {
				ss = factory.openSession();
				MemberMapper mapper = ss.getMapper(MemberMapper.class);
				result  = mapper.getQuiz2(i);
				ss.commit(); 
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (ss != null) ss.close();
			}
			return result;
		}
	// Quiz의 Score 결과를 DB에 저장하는 메소드
	public int QuizNew(ScoreVO vo3) {
			SqlSession ss = null;
			int result = 0;
			try {
				ss = factory.openSession();
				MemberMapper mapper = ss.getMapper(MemberMapper.class);
				result  = mapper. QuizNew(vo3);
				ss.commit();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (ss != null) ss.close();
			}
			return result;
		}
	
}