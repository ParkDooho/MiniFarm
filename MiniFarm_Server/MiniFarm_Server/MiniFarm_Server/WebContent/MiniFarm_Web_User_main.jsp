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

   UserDAO User_DB = new UserDAO(con);
   JSONObject jsonObj = new JSONObject();
   JSONArray jsonArr = new JSONArray();  
   ArrayList<UserDTO> arrayUserData = new ArrayList<UserDTO>();
   
   jsonObj = User_DB.showAllUser();
   try {
       JSONArray resultArray = (JSONArray)jsonObj.get("success");
       for(int i=0;i<resultArray.size();i++){
    	   JSONObject tempObject = (JSONObject)resultArray.get(i);
    	   UserDTO temp = new UserDTO();
    	   temp.setUserId((String)tempObject.get("userId"));
    	   temp.setUserPw((String)tempObject.get("userPw"));
    	   temp.setUserName((String)tempObject.get("userName"));
    	   temp.setUserQuestion((Integer)tempObject.get("userQuestion"));
    	   temp.setUserAnswer((String)tempObject.get("userAnswer"));
    	   arrayUserData.add(temp);
    	   

    	  // out.println(arrayUserData.get(i).getUserId());
       }
   }catch (Exception e) {
       e.printStackTrace();
   }
   %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>


<style >
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
    font-family: '배달의민족 주아';
    font-size:25px;
    background-color:#F2F2F2;
  }
  tbody{
    font-family: '배달의민족 주아';
    font-size:25px;
    text-align: center;

  }
  #DeleteButton{
    border-radius: 5px;
  }
  .Deletebtn{
    margin: 5px 20px 5px 20px;
    border-radius: 5px;
    border-radius: 5px;
    width: 65px;  height: 35px;
    border: 3px solid #8FAADC;
    background-color: #FFFFFF;
    color : #8FAADC;
    font-family: '배달의민족 주아';
    font-size: 25px;
  }
  .Deletebtn :hover{
    color :white;
    background-color :#8FAADC;
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
     <span>회원정보관리</span>
   </div>
   <form name= deleteName class="delete" action="index.html" method="post">
    <div id="tableDiv">
     <table>
       <colgroup>
         <col style="width:130px;">
         <col style="width:270px;">
         <col style="width:300px;">
         <col style="width:190px">
         <col style="width:200px;">         
         <col style="width:70px;">
       </colgroup>
       <thead id="Thead">
         <tr style=" ">           
           <th scope="col">번 호</th>
           <th scope="col">아이디</th>
           <th scope="col">비밀번호</th>
           <th scope="col">힌  트</th>
           <th scope="col">이 름</th>
           <th scope="col"> </th>
           </tr>
       </thead>
       <tbody>       
         	<%for(int x = 0 ; x <arrayUserData.size(); x++){%>
         <tr>
           <td><%= Integer.toString(x+1) %></td>
           <td><a href="MiniFarm_Web_User_select.jsp?userId=<%=URLEncoder.encode(arrayUserData.get(x).getUserId(),"UTF-8") %>" style="color:black;"><%= arrayUserData.get(x).getUserId() %></a></td>
           <td><%= arrayUserData.get(x).getUserPw()%></td>
           <td><%= arrayUserData.get(x).getUserQuestion() %></td>
           <td><%= arrayUserData.get(x).getUserName() %></td>
           <td><button type="button" class="Deletebtn" name = deleteBtn >삭제</button></td>
            
         </tr>
         <% } %>
       </tbody>
       </table>
     </div>
   <script>
   
   $(document).on("click","button[name=deleteBtn]",function(){
       
       var tr = $(this).parent().parent();
       var td = tr.children();
       var Id = td.eq(1).text();
       var Pw = td.eq(2).text();
       
       
       var url = "MiniFarm_Web_User_delete.jsp?Id="+Id+"&Pw="+Pw;
       var name = "delete_user";
       var option = "width=390, height=220, left=690, top=370, location = no"
       window.open(url, name, option);
           
     
        
   });
         </script>
  </form>

</body>
</html>

<%DBConnection.closeConnection(con);%>