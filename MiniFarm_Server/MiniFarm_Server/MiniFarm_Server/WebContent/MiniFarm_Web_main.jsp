<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <style>

  #position{
    width: 600px;
    height: 80px;
  }
  #logo {
    float: left;
  }
  #menu{
    margin: 30px 20px 0 0;
  }
  li{
    list-style-type: none;
  }
  #menu li{
    display: inline;
    font-size: 25px;
    font-family: '배달의민족 주아';
    color: gray;
  }
  .item {
    margin: 20px 20px 0 20px;
  }
  #ss_menu{
    margin: 0 0 0 540px;
  }
  .s_menu{
    width: 350px;
    height: 330px;
    margin: 250px 25px 0 25px;
  }
  a {
    text-decoration:none; }
  </style>
</head>
<body>
  <div id="position">
    <div id="logo">
    <a href = "MiniFarm_Web_main.jsp">
     <img src="minifarm.png" alt = "이미지 표시할 수 없음">
   </a>
 </div>

   <ul id="menu">
     <a href="MiniFarm_Web_User_main.jsp">
     <li class="item">회원정보</li>
   </a>
     <li>|</li>
     <a href="MiniFarm_Web_Question_main.jsp">
     <li class="item">문의사항</li>
   </a>
   </ul>
   </div>
   <div id="ss_menu">
   <a href="MiniFarm_Web_User_main.jsp">
     <img class="s_menu" src="회원관리.png" alt="이미지 표시할 수 없음">
   </a>
   <a href="MiniFarm_Web_Question_main.jsp">
     <img class="s_menu" src="문의사항.png" alt="이미지 표시할 수 없음">
   </a>
 </div>

</body>
</html>