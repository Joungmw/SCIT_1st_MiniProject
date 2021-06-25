package member.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

import member.dao.MemberDAO;
import member.vo.CashVO;
import member.vo.MemberVO;
import member.vo.QuizVO;
import member.vo.ScoreVO;

public class MemberUI {
		private MemberDAO dao = new MemberDAO();
		private Scanner key = new Scanner(System.in);
		private String loginId = null;
		private Random rd = new Random();	// 게임에 사용할 랜덤
		
		
		public MemberUI(){
			
			System.out.println("============== 41기 3조 게임프로그램 ==============");
			while(true) {
				menuprint();
				System.out.println();
				System.out.print("이용하실 메뉴를 선택해 주세요 >>");
				int a;
				try {
					a = key.nextInt();
					}
					catch(InputMismatchException e){
						key.nextLine();
						System.out.println("잘못입력하셨습니다. 다시입력하세요.\n");
						continue;
					}
				
				switch (a) {
				
					case 1: join(); break;
					case 2: login(); break;
					case 3: blackjack(); break;
					case 4: updown(loginId); break;
					case 5: quiz(); break;
					case 6: sniffling(); break;
					case 7: score(); break;
					case 8: charge(); break;
					case 9: logout(); break;
					case 10: delete(); break;
					case 11: cashtable(loginId); break;
					case 0: System.out.println("프로그램이 종료되었습니다.");return;
					default:
					return;
				}
		
			}
	
		}
		
		private void menuprint() {
			System.out.println();
			if(loginId == null) {
				System.out.println("1. 회원가입\t2. 로그인");
			}
			else {
			System.out.println("3 . 블랙잭\t4 . 업앤다운\t5 . 퀴즈");
			System.out.println("6 . 홀짝  \t7 . 점수검색\t8 . 캐시충전");
			System.out.println("9 . 로그아웃\t10 . 회원탈퇴\t11 . 캐쉬이용내역");
			}
			System.out.println("0 . 종료");
		}
		
		private void join() {
			if(loginId != null) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			System.out.println();
			System.out.println("[회원가입]");
			String id,password,email,phone;
			
			System.out.print("아이디 >>");
			id = key.next();
			
			if(dao.getMember_id(id) !=null) {
				System.out.println(id+"는 이미 존재하는 아이디입니다");
				return;
			}
			
			System.out.print("비밀번호 >>");
			password = key.next();
			System.out.print("이메일 >>");
			email = key.next();
			System.out.print("전화번호 >>");
			phone = key.next();
			
			
			MemberVO vo = new MemberVO(id, password,email,phone);
			dao.join(vo);
			
			// 가입 축하금
			CashVO welcome = new CashVO(id, 1000);	
			dao.charge(welcome);					
			dao.charge_add(welcome);	
			
			System.out.println("가입 축하금 1000Cash가 지급되었습니다!\n"+ id +"님 환영합니다");
			System.out.println();
			
		}
		
		
		private void login() {
			if(loginId != null) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			System.out.println();
			System.out.println("[로그인]");
			String id,password;
			
			System.out.print("아이디 >>");
			id = key.next();
			System.out.print("비밀번호 >>");
			password = key.next();
			MemberVO vo = dao.getMember_id(id);
			if(vo != null && vo.getMember_pw().equals(password)) {
				loginId = String.valueOf(vo.getMember_id());
				System.out.println(id+"님 환영합니다");
				System.out.println();
			}
			else {
				System.out.println("아이디 혹은 비밀번호가 존재하지 않습니다.");
				System.out.println();
			}

	}

		
		private void blackjack() {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			int card, mycard, dealcard, bet = 0;	// 블랙잭 사용 변수선언
			
			System.out.println("[ 블랙잭 ]");
			System.out.println("블랙잭은 원하는 만큼 카드를 뽑아 딜러보다 높은 수의 카드를 보유하면 이기는 게임입니다.");
			System.out.println("단, 패의 합이 21이 넘어가면 버스트되어 자동으로 패배합니다.");
			System.out.println("히트를 하시면 카드를 더 뽑을 수 있습니다.");
			System.out.println("게임에서 승리시 배팅금의 두배를 돌려드리며 비기는 경우 배팅금을 돌려드립니다.");
			System.out.println();
			while(true) {
				int mycash = dao.getCash_total(loginId);
				//	보유금액 보여주며 배팅 시작
				System.out.println("현재 보유 cash :" + mycash);
				System.out.print("베팅값을 입력하시오 (exit : 0) >>");
				try {
					bet = key.nextInt();
					}
					catch(InputMismatchException e){
						key.nextLine();
						System.out.println("잘못입력하셨습니다. 다시입력하세요.");
						continue;
					}
			
				// 배팅 조건
				if(bet > mycash) {
					System.out.println("배팅값이 총 금액보다 큽니다.");
					System.out.println();
					break;
				} else {
					CashVO vo = new CashVO(loginId, -bet);
					dao.charge(vo);
					if(bet == 0) break;
					
					// 배팅 입력값이 보유금보다 작고 0이 아닐경우 배팅
					dao.charge_add(vo);
					System.out.println(bet + "원 배팅하셨습니다." );
					System.out.println("남은 Cash는 " + dao.getCash_total(loginId) + "원 입니다.");
					
					// dealer's cards
					dealcard = (rd.nextInt(11) + 12);	// 12 ~ 22 까지의 딜러 카드 결정
					System.out.println();
					// 딜러 카드 경우에 따라 보여주는 패 값 변화
					if(dealcard < 11) {
						System.out.println("딜러의 카드 패 : " + "| " + (dealcard + rd.nextInt(11) + 1) +" |" + " + | @ |");
					}else if(dealcard > 21) {
						System.out.println("딜러의 카드 패 : " + "| " + (dealcard - 10) +" |" + " + | @ |");
					}else {
						System.out.println("딜러의 카드 패 : " + "| " + (dealcard - rd.nextInt(3) - 1) +" |" + " + | @ |");
					}
					
					// my cards
					mycard = 0;
					while(true) {
						System.out.print("나의 카드 패 :\t ");	// 처음 뽑는 패
						for(int i = 0; i < 2; i++) {
							card = (rd.nextInt(11) + 1);	// 첫 패는 1 ~ 11 까지의 수
							System.out.print("| " + card + " |   ");
							mycard += card;
							//	첫패에서 burst가 날경우 수정
							if(mycard > 21) {
								mycard -= (rd.nextInt(5) + 1);
							}
						}
						System.out.println("\n");
						System.out.println("현재 나의 패의 합 : " + mycard);	// 자신 패의 합
						
						String hits;
						key.nextLine();
						while(true) {
							//	패를 더 받을 것인지 Hit를 물어봄
							System.out.print("히트하시겠습니까 (y/n) >>");
							hits = key.nextLine();
							System.out.println();
							
							//	받겠다고 y를 입력하면 1 ~ 11까지의 랜덤 패를 받고 무엇을 받았는지 출력, 지금까지 패의 합을 보여주고 21이 넘으면 burst
							if("y".equals(hits)){
								card = (rd.nextInt(11) + 1);
								System.out.println("히트한 카드는 " + "| " + card + " |   ");
								mycard += card;
								System.out.println("현재 나의 패의 합 : " + mycard);
								if(mycard > 21) {
									System.out.println("버스트!!\n");
									System.out.println();
									break;
								}
							}else if("n".equals(hits)) {	// 받지 않겠다고 n을 입력하면 승부
								System.out.println("딜러의 패의 합 : " + dealcard + " || 나의 패의 합 : " + mycard);
								if(dealcard > 21) {	// 딜러의 카드가 21이 넘으면 딜러의 burst 배팅금액의 2배를 받고 테이블에 입력
									System.out.println("딜러의 버스트!! 게임에서 승리하셨습니다!!");
									
									CashVO atari = new CashVO(loginId, 2 * bet);
									dao.charge(atari);
									dao.charge_add(atari);
									
									System.out.println((2 * bet) + "원 획득하셨습니다!");
									System.out.println();
									
								}else if(mycard > dealcard) {	// 둘다 burst 되지 않고 자신의 패가 딜러보다 크면 배팅금액의 2배를 받고 테이블에 입력
									System.out.println("게임에서 승리하셨습니다!!");
									
									CashVO atari = new CashVO(loginId, 2 * bet);
									dao.charge(atari);
									dao.charge_add(atari);
									
									System.out.println((2 * bet) + "원 획득하셨습니다!");
									System.out.println();
									
								}else if(mycard == dealcard) {	// 둘다 burst 되지 않고 자신의 패와 딜러의 패가 같으면 배팅금액만큼을 받고 테이블에 입력
									System.out.println("비겼습니다.");
									
									CashVO atari = new CashVO(loginId, bet);
									dao.charge(atari);
									dao.charge_add(atari);
									
									System.out.println(bet + "원을 돌려 받으셨습니다.");
									System.out.println();
									
								}else {	// 둘다 burst 되지 않고 이기지도 비기지도 않았을 경우 이미 배팅금액을 뺐으므로 패배 메시지 출력후 종료
									System.out.println("게임에서 패배하셨습니다.\n");
									System.out.println();
									
								}
								System.out.println("남은 Cash는 " + dao.getCash_total(loginId) + "원 입니다.");
								System.out.println();
								
								break;
							}else continue;
						}
						
						mycard = 0;
						
						break;
					}
				}
			}
		}
		
		
		private void updown(String loginid) {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			// 1부터 100까지 맞출 값을 무작위로 하나 생성하여 저장
			Random randNumber = new Random();
			int correctNumber = (randNumber.nextInt(100)+1);
			// 도전 기회 세팅
			int count = 5;
			// 최초 점수 세팅
			int score = 1000;
			// 사용자가 입력할 답을 저장할 int 변수 선언
			int answer = 0;
			System.out.println();
				System.out.println(" [ Up&Down ] ");
				System.out.println("총 5번의 기회가 주어지고, 실패할 때마다 최종 Score가 줄어듭니다.");
				System.out.println("기본 Score는 1000점, 실패할 경우 200씩 차감됩니다.");
				System.out.println("범위는 1 ~ 100 까지입니다.");
				System.out.println("게임을 시작합니다.");
				System.out.println("----------------------------------------------");
				// 반복 루틴 시작
				while(true) {
					System.out.println("------"+count+" 번의 남은 도전기회 !"+"------");
					System.out.print("Up? or Down? 예측 숫자를 입력하시오 >>");
					try {
						answer = key.nextInt();
						}
						catch(InputMismatchException e){
							key.nextLine();
							System.out.println("잘못입력하셨습니다. 다시입력하세요.");
							continue;
						}
					if(answer>correctNumber) {
						System.out.println("더 낮은 값이 정답입니다!");
						count--;
						score=score-200;
						System.out.println("남은 기회"+count+"번, 획득 가능 점수는 "+score+" 입니다.");
						if(count==0) {
							System.out.println("획득 실패! 모든 기회를 잃었습니다.");
							break;
						}
					}
					else if (answer<correctNumber) {
						System.out.println("더 높은 값이 정답입니다!");
						count--;
						score=score-200;
						System.out.println("남은 기회"+count+"번, 획득 가능 점수는 "+score+" 입니다.");
						if(count==0) {
							System.out.println("실패! 모든 기회를 잃었습니다.");
							break;
						}
					}
					else {
						System.out.println(" 정 답 입 니 다 ! ");
						System.out.println((6-count)+"번 만에 성공, 현재 점수는"+score+" 점 입니다!");
						// ScoreVO 객체를 생성하여, ID와 SCORE 점수를 넘겨주어 저장
						ScoreVO vo = new ScoreVO(loginid, score);
						// UpAndDownScoreNew 메소드로 DB에 결과를 저장
						dao.UpAndDownScoreNew(vo);
						break;
					}
					
				}
				System.out.println("G A M E O V E R");
				System.out.println("정답은 " + correctNumber + "였습니다!");
		}
		
		
		private void quiz() {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			// 문제 선택 안내 문구 시작
			System.out.println("[ JLPT 문법 문제 맞추기]");
			System.out.println("1. n1 문형 맞추기");
			System.out.println("2. n2 문형 맞추기");
			// 변수 선언 시작
			int selectQuiz=0, score=0;
			String Qoption, Answer = null;
			selectQuiz=key.nextInt();
			// 문제 유형 선택 시작 case 1 = N1, case 2 = N2
			switch(selectQuiz) {
				case 1:
					// N1 문제 시작
					System.out.println("[JLPT N1 문제 풀이 5개] ");
					System.out.println("[규칙 : 5개의 랜덤 문제가 출제됩니다.]");
					System.out.println("[문제 하나를 맞출 때마다 200점 score가 적립됩니다.]");
					// 무작위 숫자 다섯개 출력하여 nums[]에 저장
					int[] nums = new int[5];
					for(int i=0;i<nums.length;i++) {
						int temp=(int)(Math.random()*5)+1;
						nums[i]=temp;
						for(int j=0;j<i;j++) {
							if(nums[j]==temp) {
								i--; break;
							}
						}
					}
					// for=5 문제 5유형 출력, getQuiz에서 각 랜덤 숫자에 의한 bNum 출력
					for(int i=0; i<5; i++) {
				        QuizVO vo2=dao.getQuiz(nums[i]);
				        
						System.out.println("문제 : "+vo2.getQuiz());
						System.out.println("보기 : "+vo2.getQoption());
						System.out.print("답을 입력하세요 : ");
						Answer = key.next();
						if(Answer.equals(vo2.getAnswer())) {
							System.out.println("정답");
							score++;
						}
						else {
							System.out.println("틀림");
						}
						System.out.println();	
					}
					// 맞춘 갯수 * 200점 점수 추가
					score = score * 200;
					System.out.println("당신의 최종 점수는 "+score+"점 입니다.");
					// 결과 score를 DB에 저장
					ScoreVO vo3 = new ScoreVO(loginId,score);
					dao.QuizNew(vo3);
					break;
					
				case 2:
					// N2 문제 시작
					System.out.println("[JLPT N2 문제 풀이 5개] ");
					System.out.println("[규칙 : 5개의 랜덤 문제가 출제됩니다.]");
					System.out.println("[문제 하나를 맞출 때마다 200점 score가 적립됩니다.]");
					// 무작위 숫자 다섯개 출력하여 nums[]에 저장
					int[] nums1 = new int[5];
					for(int i=0;i<nums1.length;i++) {
						int temp=(int)(Math.random()*5)+1;
						nums1[i]=temp;
						for(int j=0;j<i;j++) {
							if(nums1[j]==temp) {
								i--; break;
							}
						}
					}
					// for=5 문제 5유형 출력, getQuiz에서 각 랜덤 숫자에 의한 bNum 출력
					for(int i=0; i<5; i++) {
				        QuizVO vo2=dao.getQuiz2(nums1[i]);
				        
						System.out.println("문제 : "+vo2.getQuiz());
						System.out.println("보기 : "+vo2.getQoption());
						System.out.print("답을 입력하세요 : ");
						Answer = key.next();
						if(Answer.equals(vo2.getAnswer())) {
							System.out.println("정답");
							score++;
						}
						else {
							System.out.println("틀림");
						}
						System.out.println();	
					}
					// 맞춘 갯수 * 200점 점수 추가
					score = score * 200;
					System.out.println("당신의 최종 점수는 "+score+"점 입니다.");
					System.out.println("결과는 저장되었습니다. 만약 0점일 경우 저장되지 않습니다.");
					// 결과 score를 DB에 저장
					if(score!=0) {
						ScoreVO vo31 = new ScoreVO(loginId,score);
						dao.QuizNew(vo31);
					}
					break;

					// 이외의 값을 입력했을 때 다시 입력 안내
					default: System.out.println("다시 입력하세요"); break;
			}
		}

		
		private void sniffling() {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			Random random=new Random();
			Scanner ip=new Scanner(System.in);
			
			int i = 0;
			int input_cash=0;
			int resultCash=0;
			int cnt = 0;
			int input = 0;
			while(true) {
				// 배팅
				System.out.println();
				System.out.println(" [ 홀 짝 ] ");
				System.out.print("베팅값을 입력하시오 (exit : 0) >>");
				input_cash= ip.nextInt();
				if(input_cash == 0) break;
				
				// 배팅금액과 보유금액 비교
				int myCash = dao.getCash_total(loginId);
				if(input_cash > myCash) {
					System.out.println("보유금액보다 배팅금액이 더 큽니다");
					break;
				}
				else {
					// 모든 조건이 만족하면 실제 배팅, Cash변화 후 cash 테이블에 기록
					CashVO vo = new CashVO(loginId, -(input_cash));
					cnt = dao.charge(vo);
					dao.charge_add(vo);
					
					System.out.println(input_cash + "원 배팅하셨습니다." );
					System.out.println("남은 Cash는 " + dao.getCash_total(loginId) + "원 입니다.");
					
					// 홀짝 게임 기능
					int ranNum=random.nextInt();
					int ranResult=0;
					if(ranNum %2 == 0) {
						ranResult=2;
					}else if(ranNum %2 == 1) {
						ranResult=1;
					}
					
					System.out.print("홀[1] 짝[2] :");
					while(true) {
					try {
					input = ip.nextInt();
					}
					catch(InputMismatchException e){
						ip.nextLine();
						System.out.println("없는 숫자 입니다 다시입력하세요");
						continue;
					}
					break;
					}
						// 승리했을 경우 배팅금액의 2배 반환후 테이블 기록
						if(ranResult==input) {
						System.out.println("성공");
						
						CashVO atari = new CashVO(loginId, 2 * input_cash);
						dao.charge(atari);
						dao.charge_add(atari);
						
						System.out.println((2 * input_cash) + "원 획득하셨습니다!");
						System.out.println("현재 보유금액은"+dao.getCash_total(loginId)+"입니다");
						
					}else if(ranResult!=input) {
						// 패배했을 경우 이미 배팅금은 사용했으므로 메시지만 출력 후 종료
						System.out.println("실패");
						System.out.println("현재 보유금액은"+dao.getCash_total(loginId)+"입니다");
						
					}
					i++;
					
			 	}
				
			}
		}
		
		
		private void score() {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			ArrayList<ScoreVO> list = dao.ScoreAll();
			// 데이터가 없으면 없다고 출력함
			if(list.size()==0) {
				System.out.println();
				System.out.println("현재 아무런 데이터가 없습니다!");
				System.out.println();
			}
			else {
				// 데이터를 출력함
				System.out.println();
				System.out.println("가장 높은 점수 순으로 정렬되어 표시됩니다.");
				System.out.println("회원ID\t\t퀴즈점수\t\t업다운점수\t\t플레이 날짜\t\t총합 점수");
				System.out.println("=========================================================================================");
				for(int i= 0; i < list.size (); i++) {
					ScoreVO s = list.get(i);
					System.out.println();
					System.out.println(s.getMember_id()+"\t\t"+s.getQuizscore()+"\t\t"+s.getUndscore()+"\t\t"+s.getJoin_date()+"\t\t"+s.getTotal());
				}
				System.out.println();
			}
	}
		
		
		private void charge() {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			int cnt = 0, ip = 0;
			
			System.out.println();
			System.out.println(" [ 캐쉬충전 ] ");
			System.out.println("충전할 캐쉬를 입력하세요:");
			ip = key.nextInt();
			if(ip > 10000 || ip < 10) {	// 충전 한도
				System.out.println("10이상 10000이하의 금액만 충전 가능합니다.");
				cnt = 0;
			} else {
				CashVO vo = new CashVO(loginId, ip);	// 아이디와 충전값
				cnt = dao.charge(vo);					// Cash에 충전
				cnt = dao.charge_add(vo);				// cash 테이블에 내역저장
			}
		
			if(cnt == 0) {
				System.out.println("충전실패");
			}
			else {
				System.out.println(ip+"원 충전되었습니다");
				System.out.println("잔액은 " + dao.getCash_total(loginId) + "원 입니다.");
			}
			
		}
		
		
		private void logout() {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				System.out.println();
				return;
			}
			System.out.println(loginId + " 회원이 로그아웃 되었습니다.");
			System.out.println();
			loginId = null;
			return;
		}
		
		
		private void delete() {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			System.out.println();
			System.out.println(" [ 회원탈퇴 ] ");
			System.out.println(loginId + "회원을 정말로 탈퇴하시겠습니까? (y/n) >>");
			String d = key.next();
			
			if(d.equals("n")) {
				System.out.println();
				return;
			}else if(d.equals("y")) {
				System.out.print("회원 확인을 위해 비밀번호를 다시한번 입력해주세요 >>");
				String pass = key.next();
				
				MemberVO vo = dao.getMember_id(loginId);
				if(pass.equals(vo.getMember_pw())) {
					int cnt = 0;
					cnt = dao.deleteMember(loginId);
					
					if(cnt == 0) {
						System.out.println("회원 탈퇴에 실패하였습니다.");
						System.out.println();
					}
					else {
						System.out.println(loginId + "는 탈퇴되었습니다");
						System.out.println();
						loginId = null;
					}
				}
				else System.out.println("비밀번호가 다릅니다.\n");
				
			}else {
				System.out.println("잘못된 입력 입니다.");
				System.out.println();
			}
			
			
			
			
			
		}

		
		private void cashtable(String loginId) {
			if(loginId == null || loginId.isEmpty()) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			
			System.out.println();
			ArrayList<CashVO> list = dao.Cashtable();	// cash테이블의 값을 가져오는 Cashtable
			if(list.size()==0) {
				System.out.println("아무런 데이터가 없습니다");
			}
			else {
				System.out.println("아이디\t변경금액\t갱신날짜");
				System.out.println("=====================================");
				// 리스트의 모든 크기만큼 반복하여 모든 레코드를 가져옴
				for(int i= 0; i < list.size (); i++) {
					CashVO c = list.get(i);
					
					if(c.getMember_id().equals(loginId)) {	// 그 중에 현재 로그인 중인 아이디와 가져온 아이디의 값이 같은 것들만 골라서 출력
						System.out.println(c.getMember_id()+"\t"+c.getCash_move()+"\t"+c.getCash_date());
					}
				}
				
				System.out.println();
				System.out.println("총 보유 Cash는 " + dao.getCash_total(loginId) + "원 입니다.");
				System.out.println();
			}
		}
		
	
}

