<h1> 게시판 서버 구현하기 </h1>

<h2> Use Case </h2>
<img src="src/main/resources/static/img/UseCase.png">



<br> <h2>데이터 테이블 </h2>

CREATE TABLE board
<br>(
<br>id BIGINT NOT NULL AUTO_INCREMENT,
<br>name VARCHAR(20) NOT NULL,
<br>title VARCHAR(255) NOT NULL,
<br>contents VARCHAR(500) NOT NULL,
<br>password VARCHAR(50) NOT NULL,
<br>created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
<br>updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
<br>PRIMARY KEY (id)
<br>);

<br>
<h2>API 명세서 <h2>
<img src="src/main/resources/static/img/BoardAPI.png">

* 참고
  <br> .html파일은 추후 적용하고 싶어서 미리 구조만 만든 상태로 기능구현은 없음