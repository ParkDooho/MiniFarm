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
   SearchDAO Search_DB = new SearchDAO(con);
   SensorDAO Sensor_DB = new SensorDAO(con);
   
   JSONObject jsonObj = new JSONObject();
   JSONArray jsonArr = new JSONArray();  
   ArrayList<UserDTO> arrayUserData = new ArrayList<UserDTO>();
   ArrayList<SearchDTO> arraySearchData = new ArrayList<SearchDTO>();
   ArrayList<SensorDTO> arraySensorData = new ArrayList<SensorDTO>();
   
   String selectUserId = request.getParameter("userId");
   
   jsonObj = User_DB.findUserInfo_userId(selectUserId);
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
   
   jsonObj = Search_DB.findSearchInfo(selectUserId);
   if(jsonObj.toString().equals("{\"success\":[]}")){
	   SearchDTO temp = new SearchDTO();
	   temp.setUserId("");
	   temp.setSearchName("");
	   temp.setSearchTem(0);
	   temp.setSearchHum(0);
	   temp.setSearchIllu(0);
	   temp.setSearchPH(0);
	   temp.setSearchFD(0);
	   arraySearchData.add(temp);
   }else{
   try {
       JSONArray resultArray = (JSONArray)jsonObj.get("success");
       for(int i=0;i<resultArray.size();i++){
    	   JSONObject tempObject = (JSONObject)resultArray.get(i);
    	   SearchDTO temp = new SearchDTO();
    	   temp.setUserId((String)tempObject.get("userId"));
    	   temp.setSearchName((String)tempObject.get("searchName"));
    	   temp.setSearchTem((Float)tempObject.get("searchTem"));
    	   temp.setSearchHum((Float)tempObject.get("searchHum"));
    	   temp.setSearchIllu((Integer)tempObject.get("searchIllu"));
    	   temp.setSearchPH((Float)tempObject.get("searchPH"));
    	   temp.setSearchFD((Integer)tempObject.get("searchFD"));
    	
    	   arraySearchData.add(temp);
    	   

    	  // out.println(arrayUserData.get(i).getUserId());
       }
   }catch (Exception e) {
       e.printStackTrace();
   }
   }
   
   
   jsonObj = Sensor_DB.findSensorInfo(selectUserId);
   if(jsonObj.toString().equals("{\"success\":[]}")){
	   SensorDTO temp = new SensorDTO();
	   temp.setUserId("");
	   temp.setSensorName("");
	   temp.setSensorTem(0);
	   temp.setSensorHum(0);
	   temp.setSensorIllu(0);
	   temp.setSensorPH(0);
	   temp.setSensorFD(0); 
	   arraySensorData.add(temp);
   }else{
   try {
       JSONArray resultArray = (JSONArray)jsonObj.get("success");
       for(int i=0;i<resultArray.size();i++){
    	   JSONObject tempObject = (JSONObject)resultArray.get(i);
    	   SensorDTO temp = new SensorDTO();
    	   temp.setUserId((String)tempObject.get("userId"));
    	   temp.setSensorName((String)tempObject.get("sensorName"));
    	   temp.setSensorTem((Float)tempObject.get("sensorTem"));
    	   temp.setSensorHum((Float)tempObject.get("sensorHum"));
    	   temp.setSensorIllu((Integer)tempObject.get("sensorIllu"));
    	   temp.setSensorPH((Float)tempObject.get("sensorPH"));
    	   temp.setSensorFD((Integer)tempObject.get("sensorFD"));
  
   
    	   arraySensorData.add(temp);
    	   

    	  // out.println(arrayUserData.get(i).getUserId());
       }
   }catch (Exception e) {
       e.printStackTrace();
   }
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
    padding: 100px 0 20px 850px;
  }
  table{
    margin:40px 0 0 410px;
  }
  tbody{
    font-size: 23px;
    font-family: '배달의민족 주아';
    height: 40px;
  }
  td{
    border-bottom: 1px solid #BFBFBF;
  }
  .col2{
    background-color: #F2F2F2;
    color: #325798;
    border-bottom: 1px solid gray;
  }
  .center{
    text-align: center;
    color: #767171;
  }
  .BtnTd {
    height: 60px;
  }
  .BtnTd button{
    float: right;
  }
  .Backbtn{
    border-radius: 5px;
    width: 80px;  height: 35px;
    border: 3px solid #AFABAB;
    background-color: #FFFFFF;
    color: #AFABAB;
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
     <span>회원정보 조회</span>
   </div>
     <table>
     <%for(int x = 0 ; x <arrayUserData.size(); x++){%>
       <colgroup>
         <col style="width:250px;">
         <col style="width:800px;">
       </colgroup>
       <tbody>
         <tr>
         <td class="col2" colspan="2">&nbsp;&nbsp;&nbsp;기본정보</td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">아이디</td>
          <td scope="col"><%= arrayUserData.get(x).getUserId() %></td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">비밀번호</td>
          <td scope="col"><%= arrayUserData.get(x).getUserPw()%></td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">이 름</td>
          <td scope="col"><%= arrayUserData.get(x).getUserName() %></td>
         </tr>
         <tr>
         <td class="col2" colspan="2">&nbsp;&nbsp;&nbsp;센서정보</td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">작물</td>
          <td scope="col"><%= arraySearchData.get(x).getSearchName() %></td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">온도</td>
          <td scope="col">현재 : <%= arraySensorData.get(x).getSensorTem() %>℃ / 적정 : <%= arraySearchData.get(x).getSearchTem() %> ℃</td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">습도</td>
          <td scope="col">현재 : <%= arraySensorData.get(x).getSensorHum() %>% /  적정 : <%= arraySearchData.get(x).getSearchHum() %>%</td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">조도</td>
          <td scope="col">현재 : <%= arraySensorData.get(x).getSensorIllu() %> /  적정 : <%= arraySearchData.get(x).getSearchIllu() %></td>
         </tr>
         <tr style=" ">
          <td class="center" scope="col">PH농도</td>
          <td scope="col">현재 : PH<%= arraySensorData.get(x).getSensorPH() %> /  적정 : PH<%= arraySearchData.get(x).getSearchPH() %></td>
         </tr>
         
           <td class="BtnTd" colspan="2">
             <a href="MiniFarm_Web_User_main.jsp">
               <button type="button" class="Backbtn" name="button">목 록</button>
               </a>
           </td>
         </tr>

       </tbody>
       <%} %>
       </table>
 </body>
</html>


<%DBConnection.closeConnection(con);%>