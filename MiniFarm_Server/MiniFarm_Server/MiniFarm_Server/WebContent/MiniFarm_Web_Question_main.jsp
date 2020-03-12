<%@page import="java.sql.Date"%>
<%@page import="java.util.*"%>
<%@page import="Project_Server.*"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.*"%>
<%@page import="java.net.URLEncoder" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
      
<%   request.setCharacterEncoding("UTF-8");%>
<%
   Connection con = DBConnection.openConnection();

   QuestionDAO Question_DB = new QuestionDAO(con);
   JSONObject jsonObj = new JSONObject();
   JSONArray jsonArr = new JSONArray();  
   ArrayList<QuestionDTO> arrayQuestionData = new ArrayList<QuestionDTO>();
   
   jsonObj = Question_DB.showAllQuestion();
   try {
       JSONArray resultArray = (JSONArray)jsonObj.get("success");
       for(int i=0;i<resultArray.size();i++){
    	   JSONObject tempObject = (JSONObject)resultArray.get(i);
    	   QuestionDTO temp = new QuestionDTO();
    	   temp.setqNumber((Integer)tempObject.get("qNumber"));
    	   temp.setqTitle((String)tempObject.get("qTitle"));
    	   temp.setUserId((String)tempObject.get("userId"));
    	   temp.setqQuestion((String)tempObject.get("qQuestion"));
    	   temp.setqDate((Date)tempObject.get("qDate"));
    	   arrayQuestionData.add(temp);
    	   

    	  // out.println(arrayUserData.get(i).getUserId());
       }
   }catch (Exception e) {
       e.printStackTrace();
   }
   %>
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
  a { 
    text-decoration:none; }
  span{
    font-size: 35px;
    font-family: '배달의민족 주아';
    font-weight: bold;
  }
  .titleArea{
    padding: 150px 0 20px 780px;
  }
  #tableDiv{
    margin: 30px 0 0 290px;
  }
  table{
    height:85px;
    border-bottom: 3px solid #F2F2F2;
  }
  #Thead{
    font-family:"배달의민족 주아";
    font-size:25px;
    background-color:#F2F2F2;
  }
  tbody{
    font-family:"배달의민족 주아";
    height:45px;
    font-size:25px;
    text-align: center;

  }
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

   <div class="titleArea">
     <span>문의사항</span>
   </div>
   <div id="tableDiv">
     <table>
       <colgroup>
         
         <col style="width:170px;">
         <col style="width:350px;">
         <col style="width:250px;">
         <col style="width:320px;">
       </colgroup>
       <thead id="Thead">
         <tr style=" ">
           <th scope="col">번 호</th>
           <th scope="col">제 목</th>
           <th scope="col">작성자</th>
           <th scope="col">작성일</th>
         </tr>
       </thead>
       <tbody>
       <%for(int x = 0 ; x <arrayQuestionData.size(); x++){%>
         <tr>

           <td><%= arrayQuestionData.get(x).getqNumber()%></td>
		   <td><a href="MiniFarm_Web_Question_select.jsp?qNumber=<%= URLEncoder.encode(arrayQuestionData.get(x).getqNumber()+"","UTF-8")%>&userId=<%= URLEncoder.encode(arrayQuestionData.get(x).getUserId(),"UTF-8")%>" style="color:black;"><%= arrayQuestionData.get(x).getqTitle()%></a></td>
		   <td><%= arrayQuestionData.get(x).getUserId()%></td>
           <td><%= arrayQuestionData.get(x).getqDate()%></td>
         </tr>
       </tbody>
        <% } %>
       </table>
    </div>
 </body>
</html>

<%DBConnection.closeConnection(con);%>