# 콜버스 프로젝트



## 구현된 기능 

- ### REST API
  - Account
    - 회원가입/로그인/인증번호 발송
  - User
    - 티켓 조회/ 티켓 생성/ 티켓 수정/ 티켓 취소

- ### 기타

  - local / develop profile 분리
    - local (h2)
      - DB 스키마 및 샘플 데이터 생성 자동화
    - develop
      - MariaDB로 연결

<br/>
<br/>

## TO-DO list

- ### REST API

  - User
    - 티켓 취소시 입찰한 기사님에게 취소 푸시메시지 전송

- ### 기타

  - CI 구축
    - 젠킨스 이용한 테스트 자동화
  - AWS 배포

<br/>
<br/>

## ERD
<img src="https://github.com/jayyhkwon/portfolio/blob/develop/CallBus_ERD_v2.png">

