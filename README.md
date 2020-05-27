# 콜버스 프로젝트



## 구현된 기능 

- ### REST API
  - Account
    
    - 회원가입/로그인/인증번호 발송
  - User
  
    - 티켓 조회/ 티켓 생성/ 티켓 수정/ 티켓 취소
    
    
  
- **CI /CD**

  - 젠킨스를 통한 테스트 자동화
  - nginx 이용한 무중단 배포

- **CD 전략**

  1. IDLE 상태의 인스턴스  shutdown
  2. IDLE Port에 신규 배포된 jar 실행
  3. 정상 실행되었는 지 확인 후 nginx reload 

- ### 기타

  - local / develop profile 분리
    - local (h2)
      - DB 스키마 및 샘플 데이터 생성 자동화
    - develop (MariaDB)

<br/>
<br/>

### 아키텍처
  - 무중단 배포를 위해 2개의 웹서버 인스턴스를 기동하며, 하나의 웹서버만 실제 운영한다.

<img src="https://github.com/jayyhkwon/portfolio/blob/develop/architecture.png">



## ERD
<img src="https://github.com/jayyhkwon/portfolio/blob/develop/CallBus_ERD_v2.png">

