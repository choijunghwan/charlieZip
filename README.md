# charlieZip
내가 방문했던 카페의 커피 맛을 기록하고 공유하는 웹 서비스

**개발환경** : Spring boot(2.4.2), Java(11), MySQL

**기술스택** : Spring Data Jpa, Spring Security, Querydsl, Thymeleaf, junit5, AWS-EC2

# 기능 목록
**로그인**

<img src="https://user-images.githubusercontent.com/14924689/112106832-7e766680-8bf1-11eb-8988-531d3490a33a.png" width=50%></img>
* 회원등록

<img src="https://user-images.githubusercontent.com/14924689/112106736-6868a600-8bf1-11eb-84cc-d4f0c95c91fa.png" width=50%></img>
* Spring Security를 이용한 로그인, 로그아웃을 구현하였다.

  암호화는 Bcrypty를 사용했다.


**커피게시판**

<img src="https://user-images.githubusercontent.com/14924689/112106843-81715700-8bf1-11eb-96ea-58d4e3de61ba.png" width=75%></img>
* 게시물 등록
* 게시물 수정
* 게시물 조회
* 게시물 삭제
* 게시물 목록 페이징 

  페이징은 한페이지에 8개 게시물, 5페이지씩 보이는 전략을 사용했다. Spring Data Jpa의 페이징 기능을 이용하여 구현.

<img src="https://user-images.githubusercontent.com/14924689/112106850-82a28400-8bf1-11eb-8bcf-9fe799455fcc.png" width=75%></img>
* 게시물 검색

  Querydsl을 사용해 동적쿼리를 만들고 검색기능을 구현했다.

**패키지 구조**
* study.charlieZip
  * config
  * controller
  * service
  * repository
  * entity

