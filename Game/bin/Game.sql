--멤버 TABLE
create table gmember(
member_id varchar2(10) PRIMARY key,
member_pw varchar2(20)not null,
email varchar2(20)not null,
phone varchar2(20)unique not null,
cash_total number DEFAULT 0
);

--점수 테이블
create table scoreboard(
member_id varchar2(10),
score_num number(30),
quizscore number(38) default 0,
undscore number(38) default 0,
total number default 0,
join_date date DEFAULT sysdate
);

-- 코인(캐쉬) 테이블
create table cash(
cash_number number PRIMARY key,
member_id varchar2(10),
cash_move number,
cash_date date DEFAULT sysdate, 
CONSTRAINT gamecash FOREIGN key(member_id) references gmember(member_id) on DELETE cascade
);

--JLPT N1 퀴즈 테이블
create table quizboard(
quiz_num number PRIMARY key,
quiz varchar2(4000) not null,
qoption varchar2(4000) not null,
answer varchar2(100) not null
);
--JLPT N2 퀴즈 테이블
create table quizboard2(
quiz_num number PRIMARY key,
quiz varchar2(4000) not null,
qoption varchar2(4000) not null,
answer varchar2(100) not null
);

-- 시퀀스 설정하기, 기본값은 1부터 시작된다.
create sequence cash_seq start with 1; -- 캐쉬
create sequence score_seq start with 1; --스코어
create sequence quiz_seq start with 1; -- JLPT N1 퀴즈 시퀀스
create sequence quiz_seq2 start with 1; -- JLPT N2 퀴즈 시퀀스

-- JLPT N1용 문제 INSERT 값 (반드시 INSERT)
insert into quizboard VALUES (quiz_seq.nextval, '～たる의 뜻은?' ,'1.~의 입장에 있는 2. ~에 이르기까지 3. ~도 아니고 4. ~은 물론이거니와 (?師たるもの)',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～のいたり의 뜻은?' ,'1.지극히 ~함, 최고의 ~ 2. ~ 없이 3. ~에 입각하여 4. ~이라서',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～にしたところで의 뜻은?' ,'1.~라고 한들 2. 반드시 ~한다. 3. ~여하에 따라서는 4. ~하는 한편',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～ずくめ의 뜻은?' ,'1.~일색, ~투성이, ~이 계속해서 생김 2. ~에 이르기까지 3, ~할 것 까지도 없다. 4. ~에 입각하여',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～てやまない의 뜻은?' ,'1.~해 마지않다 2. 단지 ~뿐만 아니라 3. ~없이 4. ~이므로',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, 'ひとり～のみならず의 뜻은?' ,'1.단지 ~뿐만 아니라 2. ~ 이므로 3,하면서 4. 게다가',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～に至るまで의 뜻은?' ,'1.~에 이르기까지 2. ~ 뿐만 아니라 3. ~ 함으로써 4. ~하기 힘들다.',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～なしに(は)의 뜻은?' ,'1.~없이(는) 2. 반드시 ~한다. 3, ~여하에 따라서는 4. ~ 하면서',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～ないではおかない의 뜻은?' ,'1.반드시 ~한 다 2. ~를 잘한다 3. ~를 못한다 4. ~가 특기이다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～いかんでは?～いかんによっては의 뜻은?' ,'1.~여하에 따라서는 2. ~를 잘한다 3. ~를 못한다 4. ~가 특기이다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ではあるまいし의 뜻은?' ,'1.~도 아니고, ~도 아닐테고 2. ~조차 3,~하기 싫다. 4 ~하면 ~ 할 수록',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～てからというもの의 뜻은?' ,'1.~하고 나서(계속) 2.~ 하면 3. ~하지만 4. ~ 할테니',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～までもない의 뜻은?' ,'1.~할 것 까지도 없다 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～もさることながら의 뜻은?' ,'1.~은 물론이거니와 (또) 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～すら의 뜻은?' ,'1.~조차 2. ~ 조금 3. ~ 해줘 4. ~하자',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～にもまして의 뜻은?' ,'1.~보다도 더 2. 힘들다. 3. 하지말자 4. 최고다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～までもなく의 뜻은?' ,'1.~할 필요도 없이 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ごとき?～ごとく의 뜻은?' ,'1.~와 같은/~와 같이, ~처럼',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～に?して의 뜻은?' ,'1.~에 입각하여 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～かたわら의 뜻은?' ,'1.~하는 한편 2. ~를 잘한다 3. ~를 못한다 4. ~가 특기이다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～こととて의 뜻은?' ,'1.~이라서 2. ~를 잘한다 3. ~를 못한다 4. ~가 특기이다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～ところを의 뜻은?' ,'1.~인 중에 2. ~를 잘한다 3. ~를 못한다 4. ~가 특기이다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～んばかりに/の/だ의 뜻은?' ,'1.곧 ~할 듯이/듯한/듯하다 2. ~를 잘한다 3. ~를 못한다 4. ~가 특기이다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～を禁じ得ない의 뜻은?' ,'1.~을 금할 수 없다 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～といったらない?～といったらありゃしない의 뜻은?' ,'1.정말이지 ~하다 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ときたら의 뜻은?' ,'1.~로 말할 것 같으면, ~은 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '4' );
insert into quizboard VALUES (quiz_seq.nextval, '～と(も)なると?～と(も)なれば의 뜻은?' ,'1.~쯤 되면 2. ~ 해야한다. 3~ 할 것이다. 4.~ 할 수 있다.',  '4' );
insert into quizboard VALUES (quiz_seq.nextval, '～べからず의 뜻은?' ,'1.~하지 마시오 2, ~ 하시오 3. 할 수 있오 4. 해야만 하오',  '4' );
insert into quizboard VALUES (quiz_seq.nextval, '～といえども의 뜻은?' ,'1.~라 할지라도, ~라도 2. 나는 널 사랑해 3. ~하지 않아 4. 그래도',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～とあっては?とあれば의 뜻은?' ,'1.~이라면 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ずにはすまない의 뜻은?' ,'1.반드시 ~해야 한다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～まじき의 뜻은?' ,'1.~해서는 안 될 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '4' );
insert into quizboard VALUES (quiz_seq.nextval, '～極まりない?～極まる의 뜻은?' ,'1.~하기 짝이 없다 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～をもって의 뜻은?' ,'1.~으로, ~로써 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '4' );
insert into quizboard VALUES (quiz_seq.nextval, '～まみれ의 뜻은?' ,'1.~투성이 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～(よ)うが～まいが?～(よ)うと～まいと의 뜻은?' ,'1.~하든 ~하지 않든 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～のきわみ의 뜻은?' ,'1.~의 극치, 최고의 ~ 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～に～(でき)ない의 뜻은?' ,'1.~하고 싶어도 ~할 수 없다 2. ~을 아랑곳 하지 않고 3. 무심코~ 무특~ 4.~해서는 알될',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～ないではすまない의 뜻은?' ,'1.반드시 ~해야 한다 2. ~을 아랑곳 하지 않고 3. 무심코~ 무특~ 4.~해서는 알될',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～とは의 뜻은?' ,'1.~하다니, ~이라니 2. ~을 아랑곳 하지 않고 3. 무심코~ 무특~ 4.~해서는 알될',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～(よ)うにも～(でき)ない의 뜻은?' ,'1.~하려 해도 ~할 수 없다 2. ~을 아랑곳 하지 않고 3. 무심코~ 무특~ 4.~해서는 알될',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～にひきかえ의 뜻은?' ,'1.~와는 달리 2. ~을 아랑곳 하지 않고 3. 무심코~ 무특~ 4.~해서는 알될',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～をよそに의 뜻은?' ,'1.~을 아랑곳하지 않고, ~을 무시하고 (무시의 뉘앙스) 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ともなしに?～ともなく의 뜻은?' ,'1.무심코~, 문득~ (앞뒤로 같은 동사)2.~하면서 3. ~를 시작으로  4.~끝내자마자',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～べからざる의 뜻은?' ,'1.~해서는 안 될 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ないまでも의 뜻은?' ,'1.~까지는 않더라도 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～に至って(は)의 뜻은?' ,'1.~에 이르러서(는) 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～きらいがある의 뜻은?' ,'1.~하는 경향이 있다 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～にかたくない의 뜻은?' ,'1.~하기에 어렵지 않다 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～が早いか의 뜻은?' ,'1.~하자마자 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~와는 달리',  '2' );
insert into quizboard VALUES (quiz_seq.nextval, '～をかわきりに（～を皮切りに）의 뜻은?' ,'1.~을 시작으로 2.~만의, ~이 아니고는 할 수 없는 3.~와는 달리 4.~하자마자',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～ならでは(の)의 뜻은?' ,'1.~만의, ~이 아니고는 할 수 없는 2.~와는 달리, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～なくして(は)의 뜻은?' ,'1.~없이(는), 2~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자' ,  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～んがため(に)의 뜻은?' ,'1.~하기 위해서 2.~할 만한 3.~외에는 4.~ 반드시 ~한다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～でなくてなんだろう의 뜻은?' ,'1.(이것이야말로)~그 자체이다 2.~할 만한 3.~외에는 4.~ 반드시 ~한다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～なりに / ～なりの의 뜻은?' ,'1.~나름대로 2.~할 만한 3.~외에는 4.~ 반드시 ~한다 ~나름대로의',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～ながらに의 뜻은?' ,'1.~인 채로 2.~할 만한 3.~외에는 4.~ 반드시 ~한다, ~하면서',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ばそれまでだ의 뜻은?' ,'1.~하면 그것으로 끝이다 2.~할 만한 3.~외에는 4.~ 반드시 ~한다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～たところで의 뜻은?' ,'1.~해 보았자 2.~할 만한 3.~외에는 4.~ 반드시 ~한다',  '3' );
insert into quizboard VALUES (quiz_seq.nextval, '～ずにはおかない의 뜻은?' ,'1.반드시 ~한다 2. ~하기 힘들다. 3. ~와 즐겁게 4. ~와 함께 ',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～をおいて의 뜻은?' ,'1.~외에는2.~할 만한 3.~때문에 4.~ 반드시 ~한다',  '4' );
insert into quizboard VALUES (quiz_seq.nextval, '～にたる의 뜻은?' ,'1.~할 만한 2.~외에는 3.~외에는 4.~ 반드시 ~한다',  '1' );
insert into quizboard VALUES (quiz_seq.nextval, '～ゆえ(に)의 뜻은?' ,'1.~때문(에), ~탓(에) 2.~할 만한 3.~외에는 4.~ 반드시 ~한다',  '4' );

-- JLPT N2용 퀴즈 TABLE INSERT 값 (반드시 INSERT)
insert into quizboard2 VALUES (quiz_seq2.nextval, '～以上의 뜻은?','1.~하는 이상 2. ~에 이르기까지 3. ~도 아니고 4. ~은 물론이거니와', '4' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～一方だ의 뜻은?' ,'1.~하기만 한다 2. ~ 없이 3. ~에 입각하여 4. ~이라서', '4' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～うちに의 뜻은?' ,'1.~하는 동안에  2. 반드시 ~한다. 3. ~여하에 따라서는 4. ~하는 한편', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ないうちに의 뜻은?' ,'1.~하기 전에 2. ~에 이르기까지 3, ~할 것 까지도 없다. 4. ~에 입각하여', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～おかげで의 뜻은?' ,'1.~덕분에  2. 단지 ~뿐만 아니라 3. ~없이 4. ~이므로', '4' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～おそれがある의 뜻은?' ,'1.~우려가 있다 2. ~ 이므로 3,하면서 4. 게다가', '4' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～限り의 뜻은?' ,'1.~하는 한 2. ~ 뿐만 아니라 3. ~ 함으로써 4. ~하기 힘들다', '4' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～がちだ의 뜻은?' ,'1.자주~하다, ~하는 경향이 있다, ~하기 쉽다 2. 반드시 ~한다. 3, ~여하에 따라서는 4. ~ 하면서', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～かねない의 뜻은?' ,'1.~듯 하다, ~지도 모른다 2.~와는 달리 3. ~하다니 4.~하려해도 ~할 수 없다', '4' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ことだから의 뜻은?' ,'1.~이니까~을 아랑곳 하지 않고 3. 무심코~ 무특~ 4.~해서는 알될', '2' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ことはない의 뜻은?' ,'1.~할 필요(는) 없다 2.~할 만한 3.~외에는 4.~ 반드시 ~한다  ~나름대로의', '2' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ざるをえない의 뜻은?' ,'1.~하지 않을 수 없다  2.~할 만한 3.~외에는 4.~ 반드시 ~한다 ~나름대로의', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～しかない의 뜻은?' ,'1.~할 수 밖에 없다  2.~할 만한 3.~외에는 4.~ 반드시 ~한다 ~나름대로의', '3' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～次第의 뜻은?' ,'1.~하는 대로  2.~할 만한 3.~외에는 4.~ 반드시 ~한다 ~나름대로의', '3' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ずにはいられない의 뜻은?' ,'1.~하지 않고는 못 배긴다 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ば～ほど의 뜻은?' ,'1.~하면 ~할 수록 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자', '2' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～だけあって의 뜻은?' ,'1.~인 만큼(당연히) 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자', '2' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～だけに의 뜻은?' ,'1.~이기 때문에(역시) 2.~할 만한 3.~때문에 4.~ 반드시 ~한다', '3' );
insert into quizboard2 VALUES (quiz_seq2.nextval, 'たとえ～ても의 뜻은?' ,'1.비록~해도, 설령~할지라도 2.~할 만한 3.~때문에 4.~ 반드시 ~한다', '3' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～たびに의 뜻은?' ,'1.~할 때마다 2.~할 만한 3.~때문에 4.~ 반드시 ~한다', '3' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ついでに의 뜻은?' ,'1.~하는 김에 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～どころではない의 뜻은?' ,'1.~할 상황이 아니다 2.~할 만한 3.~때문에 4.~ 반드시 ~한다', '2' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ところに의 뜻은?' ,'1.~하는 상황에 2.~할 만한 3.~외에는 4.~ 반드시 ~한다 ~나름대로의', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ところへ의 뜻은?' ,'1.~하는 상황에 2.~할 만한 3.~때문에 4.~ 반드시 ~한다', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ところを의 뜻은?' ,'1.~하는 상황을 2.~할 만한 3.~외에는 4.~ 반드시 ~한다 ~나름대로의', '3' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～としたら의 뜻은?' ,'1.~라고 한다면 2.~할 만한 3.~때문에 4.~ 반드시 ~한다', '3' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～とすれば의 뜻은?' ,'1.~라고 한다면 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ないことには의 뜻은?' ,'1.~하지 않고서는 2.~할 만한 3.~때문에 4.~ 반드시 ~한다', '1' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～にすぎない의 뜻은?' ,'1.~에 지나지 않는다 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자', '2' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～べきではない의 뜻은?','1.~해서는 안 된다 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자', '2' );
insert into quizboard2 VALUES (quiz_seq2.nextval, '～ほど의 뜻은?' ,'1.~정도, ~만큼 2.~만의, ~이 아니고는 할 수 없는 3.~을 시작으로 4.~하자마자 ', '2' );
-- 문제 적용
commit;

-- Table Drop , 미리 만들어 놨다면 순서대로 실행 후 commit;
drop sequence cash_seq;
drop sequence score_seq;
drop sequence quiz_seq;
drop sequence quiz_seq2;
drop table quizboard;
drop table quizboard2;
drop table cash;
drop table scoreboard;
drop table gmember;
commit;
