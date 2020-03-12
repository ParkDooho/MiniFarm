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
   AnswerDAO Answer_DB = new AnswerDAO(con);
   JSONObject jsonObj = new JSONObject();
   JSONArray jsonArr = new JSONArray();  
   ArrayList<QuestionDTO> arrayQuestionData = new ArrayList<QuestionDTO>();
   ArrayList<AnswerDTO> arrayAnswerData = new ArrayList<AnswerDTO>();
   
   int selectqNumber = Integer.parseInt(request.getParameter("qNumber"));
   String selectUserId = request.getParameter("userId");
   
   jsonObj = Question_DB.findQuestionInfo_qNumber(selectqNumber);
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
       }
   }catch (Exception e) {
       e.printStackTrace();
   }
   
   
   jsonObj = Answer_DB.findAnswerInfo(selectqNumber);
   try {
       JSONArray resultArray = (JSONArray)jsonObj.get("success");
       for(int i=0;i<resultArray.size();i++){
    	   JSONObject tempObject = (JSONObject)resultArray.get(i);
    	   AnswerDTO temp = new AnswerDTO();
    	   temp.setqNumber((Integer)tempObject.get("qNumber"));
    	   temp.setaNumber((Integer)tempObject.get("aNumber"));
    	   temp.setaAnswer((String)tempObject.get("aAnswer"));
    	   temp.setUserId((String)tempObject.get("userId"));
    	   arrayAnswerData.add(temp);
       }
   }catch (Exception e) {
       e.printStackTrace();
   }
   %>
   
<!DOCTYPE html>
  <html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
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
      padding: 100px 0 20px 900px;
    }
  table{
    margin:30px 0 0 380px;
    border-top: 3px solid gray;
  }
  tbody{
    font-size: 23px;
    font-family: '배달의민족 주아';
    height: 40px;
  }
  td{
    border-bottom: 1px solid gray;
  }
  .center{
    text-align: center;
    width:280px;
    background-color:#F2F2F2;
  }
  .content{
    height:300px;
  }
  .BtnTd {
    height: 80px;

  }
  textarea{
    margin: 5px 0 0 5px;
    font-size: 15px;
  }
  .Addbtn{
    margin: 0 0 0 10px;
    margin-bottom: 10px;
    border-radius: 5px;
    width: 100px;  height: 35px;
    border: 3px solid gray;
    background-color: #FFFFFF;
    color : gray;
    font-family: '배달의민족 주아';
    font-size: 20px;
  }
  .Backbtn{
    margin: 0 0 0 900px;
    margin-bottom: 10px;
    border-radius: 5px;
    width: 80px;  height: 35px;
    border: 3px solid gray;
    background-color: #FFFFFF;
    color : gray;;
    font-family: '배달의민족 주아';
    font-size: 20px;
  }
  .QDelbtn{
    margin-bottom: 10px;
    border-radius: 5px;
    width: 80px;  height: 35px;
    border: 3px solid gray;
    background-color: gray;
    color : #FFFFFF;
    font-family: '배달의민족 주아';
    font-size: 20px;
  }
  .reviewTd{
    height: 90px;
  }
  #content{
    margin: 15px 0 0 20px;
  }

  .deleteBtn{
    margin: 0 0 0 1000px;
    margin-bottom: 10px;
    border-radius: 5px;
    width: 80px;  height: 35px;
    border: 3px solid gray;
    background-color: gray;
    color : #FFFFFF;
    font-family: '배달의민족 주아';
    font-size: 20px;
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
   <div class="mainTable">
     <table>
       <colgroup>
         <col style="width:280px;">
         <col style="width:800px; ">
       </colgroup>
        
       <tbody>
       <%for(int x = 0 ; x <arrayQuestionData.size(); x++){%>
         <tr style=" ">
           <td class="center" scope="col">제목</td>
           <td class="left">&nbsp;&nbsp;&nbsp;&nbsp;<%= arrayQuestionData.get(x).getqTitle()%></td>
         </tr>
         <tr>
           <td class="center" scope="col">아이디</td>
           <td scope="col">&nbsp;&nbsp;&nbsp;&nbsp;<%= arrayQuestionData.get(x).getUserId()%></td>
         </tr>
         <tr>
           <td class="center" scope="col">작성일</td>
           <td scope="col">&nbsp;&nbsp;&nbsp;&nbsp;<%= arrayQuestionData.get(x).getqDate()%></td>
         </tr>
         <tr>
           <td class="center">문의내용</td>
           <td class="content">&nbsp;&nbsp;&nbsp;&nbsp;<%= arrayQuestionData.get(x).getqQuestion()%></td>
         </tr>
         
       <% } %>
       <%for(int x = 0 ; x <arrayAnswerData.size(); x++){%>
         <tr>
           <td class="reviewTd" colspan="2">
             <p id="content"><%= arrayAnswerData.get(x).getaAnswer()%></p>
             <form action="index.html" method="post">
            
               <button type=button class="deleteBtn" name="button" onclick="window.open('MiniFarm_Web_Question_delete.jsp?qNumber=<%=arrayAnswerData.get(x).getqNumber()%>&aNumber=<%=arrayAnswerData.get(x).getaNumber()%>','DeleteAnswer','width=390, height=220, left=690, top=370, location = no')">삭 제</button>
          
             </form>
           </td>
         </tr>
         
       <% } %>
         <tr>
           <td class="BtnTd" colspan="2">
              <textarea name="answerArea" rows="8" cols="128"></textarea>
              <br><br>
              <a href="javascript:addBtnFunction(<%=selectqNumber%>,'<%=selectUserId%>');" >
              <button type="button" class="Addbtn" name="addbtn">+리뷰추가</button>
              </a>
             <a href="MiniFarm_Web_Question_main.jsp">
               <button type="button" class="Backbtn" name="button">목 록</button>
             </a>
           </td>
         </tr>
       </tbody>
     </table>
   </div>
   <script>
   function addBtnFunction(number,id){
	   var aAnswer = $("textarea[name=answerArea]").val();
	   
	   var url = "MiniFarm_Web_Question_insert.jsp?qNumber="+number+"&userId="+id+"&aAnswer="+aAnswer;
       var name = "insert_user";
       var option = "width=390, height=220, left=690, top=370, location = no"
       window.open(url, name, option);
   }
   </script>
   </body>
</html>

<%DBConnection.closeConnection(con);%>