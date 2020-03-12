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
   AnswerDAO Answer_DB = new AnswerDAO(con);
   JSONObject jsonObj = new JSONObject();
   JSONArray jsonArr = new JSONArray();  
   ArrayList<AnswerDTO> arrayAnswerData = new ArrayList<AnswerDTO>();
   
  
   int selectqNumber = Integer.parseInt(request.getParameter("qNumber"));
   int selectaNumber = Integer.parseInt(request.getParameter("aNumber"));
   
   
   //out.println(selectqNumber + "   " +selectaNumber);
   Answer_DB.deleteAnswerInfo(selectqNumber, selectaNumber);
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


</head>



<body>

<div class="deletePage">
        <img src="삭제.png" class="mul" alt="">
    </div>
    <div class="gray">
      <form class="" action="index.html" method="post">
      <input type="submit" class="Ok" name="button" value="확 인" onclick= opener.parent.location="http://localhost:8080/MiniFarm_Server/MiniFarm_Web_Question_select.jsp?qNumber=<%=selectqNumber%>";window.close();>
      </form>
    </div>


</body>
</html>

<%DBConnection.closeConnection(con);%>