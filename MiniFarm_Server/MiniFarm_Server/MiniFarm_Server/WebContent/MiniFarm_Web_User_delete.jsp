<%@page import="java.util.*"%>
<%@page import="Project_Server.*"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<%   request.setCharacterEncoding("UTF-8");%>



<%
   Connection con = DBConnection.openConnection();
   UserDAO User_DB = new UserDAO(con);
   JSONObject jsonObj = new JSONObject();
   JSONArray jsonArr = new JSONArray();  
   ArrayList<UserDTO> arrayUserData = new ArrayList<UserDTO>();
  %>



<html>
<head>

<style>
  .deletePage{
        display: inline;
      }
      .mul{
        margin: 30px 0 0 55px;
        width: 250px;
        height: 78px;
      }
      span{
        margin: 0 0 0 10px;
        font-family: '배달의민족 주아';
        font-size: 23px;
      }
      .Ok{
        margin: 15px 0 0 150px;
        width: 80px;
        height: 40px;
        border: 1px solid black;
        background-color: #F2F2F2;
        color : black;
        font-family: '배달의민족 주아';
        font-size: 22px;
      }
</style>

<script>


function _GET(search) {
    var obj = {};
    var uri = decodeURI(search);
        uri = uri.slice(1,uri.length);

    var param = uri.split('&');
    
    for (var i = 0; i < param.length; i++) {
        var devide = param[i].split('=');
        obj[devide[0]] = devide[1];
    }

    return obj;
}

window.onload = function () {
    var search = window.location.search;
    var getData =  _GET(search);
    var sender = document.querySelector('#sender');
	request.setAttribyte(getData.Id,Id);
	request.setAttribyte(getData.Pw,Pw);

    
    <%  
    String userId = request.getParameter("Id");
    String userPw = request.getParameter("Pw");
    User_DB.deleteUserInfo(userId, userPw);
    %>
    

}


</script>
</head>



<body>

<div class="deletePage">
        <img src="삭제.png" class="mul" alt="">
    </div>
    <div class="gray">
      <form class="" action="index.html" method="post">
      <input type="submit" class="Ok" name="button" value="확 인" onclick= opener.parent.location="http://localhost:8080/MiniFarm_Server/MiniFarm_Web_User_main.jsp";window.close();>
      </form>
    </div>


</body>
</html>

<%DBConnection.closeConnection(con);%>