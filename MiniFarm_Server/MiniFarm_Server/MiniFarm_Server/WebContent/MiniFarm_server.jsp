<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="javafx.util.converter.DateStringConverter"%>
<%@page import="Project_Server.*"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.*"%>
<%@page import="org.json.simple.*"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("UTF-8");
%>

<%
   Connection con = DBConnection.openConnection();
   UserDAO User_DB = new UserDAO(con);
   SensorDAO Sensor_DB = new SensorDAO(con);
   SearchDAO Search_DB = new SearchDAO(con);
   QuestionDAO Question_DB = new QuestionDAO(con);
   AnswerDAO Answer_DB = new AnswerDAO(con);
   
   
   JSONObject jsonObj = new JSONObject();
   JSONArray jsonArr = new JSONArray();  
   String action = request.getParameter("action");
   
   //User변수 
   UserDTO userDTO;
   String userId, userPw, userName, userAnswer;
   int userQuestion;
   
   //Sensor변수
   SensorDTO sensorDTO;
   float sensorTem, sensorHum, sensorPH;
   int sensorIllu, sensorFD;
   String sensorName;
   
   //Search변수
   SearchDTO searchDTO;
   String sensorSearch;
   float searchTem, searchHum, searchPH;
   int searchIllu, searchFD;
   String searchName;
   
   //QuestionDTO
   QuestionDTO questionDTO;
   String qTitle; // +userId;
   DateFormat DateFnc = new SimpleDateFormat("yyyy-MM-dd");
   Date qDate;
   int qNumber;
   
   //AnswerDTO
   AnswerDTO answerDTO;
   int aNumber;
   String aAnswer;
   
   //WebCrawling
   String searchData;
   
   switch (action) {  
   //User 함수
   case "createUserInfo":
	   userDTO = new UserDTO(request.getParameter("userId"),request.getParameter("userPw"),request.getParameter("userName"),
			   Integer.parseInt(request.getParameter("userQuestion")),request.getParameter("userAnswer"));	   
	   User_DB.createUserInfo(userDTO);	      
	   break;
   case "updateUserInfo":
	   userDTO = new UserDTO(request.getParameter("userId"),request.getParameter("userPw"),request.getParameter("userName"),
			   Integer.parseInt(request.getParameter("userQuestion")),request.getParameter("userAnswer"));	   
	   User_DB.updateUserInfo(userDTO);
	   break;
   case "deleteUserInfo":
	   userId = request.getParameter("userId");
	   userPw = request.getParameter("userPw");	   
	   User_DB.deleteUserInfo(userId,userPw);
	   break;
   case "userLogin":
	   userId = request.getParameter("userId");
	   userPw = request.getParameter("userPw");
	   jsonObj = User_DB.userLogin(userId, userPw);
	   out.println(jsonObj.toJSONString());
	   break;
   case "findUserInfo_userId":
	   userId = request.getParameter("userId");
	   jsonObj = User_DB.findUserInfo_userId(userId);
	   out.println(jsonObj.toJSONString());
	   break;
   case "findUserInfo_userName":
	   userName = request.getParameter("userName");
	   jsonObj = User_DB.findUserInfo_userName(userName);
	   out.println(jsonObj.toJSONString());
	   break;
   case "findUserPw":
	   userId = request.getParameter("userId");
	   userName = request.getParameter("userName");
	   userQuestion = Integer.parseInt(request.getParameter("userQuestion"));
	   userAnswer = request.getParameter("userAnswer");
	   jsonObj = User_DB.findUserPw(userId, userName, userQuestion, userAnswer);
	   out.println(jsonObj.toJSONString());
	   break;
   case "showAllUser":
	   jsonObj = User_DB.showAllUser();
	   out.println(jsonObj.toJSONString());
	   break;
	   
	  
	   
   //Sensor함수
   case "createSensorInfo":
	   sensorDTO = new SensorDTO(request.getParameter("userId"),request.getParameter("sensorName"),Float.parseFloat(request.getParameter("sensorTem")),Float.parseFloat(request.getParameter("sensorHum")),
			   Integer.parseInt(request.getParameter("sensorIllu")),Float.parseFloat(request.getParameter("sensorPH")),Integer.parseInt(request.getParameter("sensorFD")));	  
	   Sensor_DB.createSensorInfo(sensorDTO);
	   break;
   case "updateSensorInfo":
	   sensorDTO = new SensorDTO(request.getParameter("userId"),request.getParameter("sensorName"),Float.parseFloat(request.getParameter("sensorTem")),Float.parseFloat(request.getParameter("sensorHum")),
			   Integer.parseInt(request.getParameter("sensorIllu")),Float.parseFloat(request.getParameter("sensorPH")),Integer.parseInt(request.getParameter("sensorFD")));	   
	   Sensor_DB.updateSensorInfo(sensorDTO);
	   break;
   case "deleteSensorInfo":
	   userId = request.getParameter("userId");
	   sensorName = request.getParameter("sensorName");
	   Sensor_DB.deleteSensorInfo(userId,sensorName);
	   break;
   case "findSensorInfo":
	   userId = request.getParameter("userId");
	   jsonObj = Sensor_DB.findSensorInfo(userId);
	   out.println(jsonObj.toJSONString());
	   break;
   case "showAllSensor":
	   jsonObj = Sensor_DB.showAllSensor();
	   out.println(jsonObj.toJSONString());
	   break;
	      
	   
	   
   //Search함수
   case "createSearchInfo":
	   searchDTO = new SearchDTO(request.getParameter("userId"),request.getParameter("searchName"),Float.parseFloat(request.getParameter("searchTem")),Float.parseFloat(request.getParameter("searchHum")),
			   Integer.parseInt(request.getParameter("searchIllu")),Float.parseFloat(request.getParameter("searchPH")),Integer.parseInt(request.getParameter("searchFD")));	   
	   Search_DB.createSearchInfo(searchDTO);
	   break;   
   case "updateSearchInfo":
	   searchDTO = new SearchDTO(request.getParameter("userId"),request.getParameter("searchName"),Float.parseFloat(request.getParameter("searchTem")),Float.parseFloat(request.getParameter("searchHum")),
			   Integer.parseInt(request.getParameter("searchIllu")),Float.parseFloat(request.getParameter("searchPH")),Integer.parseInt(request.getParameter("searchFD")));	   
	   Search_DB.updateSearchInfo(searchDTO);
	   break; 
   case "deleteSearchInfo":
	   userId = request.getParameter("userId");
	   searchName = request.getParameter("searchName");
	   Search_DB.deleteSearchInfo(userId, searchName);
	   break; 
   case "findSearchInfo":
	   userId = request.getParameter("userId");
	   jsonObj = Search_DB.findSearchInfo(userId);
	   out.println(jsonObj.toJSONString());
	   break; 
   case "showAllsearch":
	   jsonObj = Search_DB.showAllsearch();
	   out.println(jsonObj.toJSONString());
	   break;
   case "Average_Search":
	   userId = request.getParameter("userId");
	   jsonObj = Search_DB.Average_Search(userId);
	   out.println(jsonObj.toJSONString());
	   break;
   case "webSearch":
	   userId = request.getParameter("userId");
	   searchData = request.getParameter("searchData");
	   jsonObj = Search_DB.webSearch(userId, searchData);
	   out.println(jsonObj.toJSONString());
	   break; 
	
	
	   //Question함수
   case "createQuestionInfo":
	   qDate = Date.valueOf(request.getParameter("qDate"));
	   questionDTO = new QuestionDTO(Integer.parseInt(request.getParameter("qNumber")),request.getParameter("qTitle"),request.getParameter("userId"),request.getParameter("qQuestion"),
			   qDate);
	   Question_DB.createQuestionInfo(questionDTO);	 
	   break;
   case "updateQuestionInfo":
	   qDate = Date.valueOf(request.getParameter("qDate"));
	   questionDTO = new QuestionDTO(Integer.parseInt(request.getParameter("qNumber")),request.getParameter("qTitle"),request.getParameter("userId"),request.getParameter("qQuestion"),
			   qDate);   
	   Question_DB.updateQuestionInfo(questionDTO);	 
	   break;
   case "deleteQuestionInfo":
	   qNumber = Integer.parseInt(request.getParameter("qNumber"));
	   Question_DB.deleteQuestionInfo(qNumber);
	   break;
   case "findQuestionInfo_qNumber":
	   qNumber = Integer.parseInt(request.getParameter("qNumber"));
	   jsonObj=Question_DB.findQuestionInfo_qNumber(qNumber);
	   out.println(jsonObj.toJSONString());
	   break;
   case "findQuestionInfo_userId":
	   userId = request.getParameter("userId");
	   jsonObj=Question_DB.findQuestionInfo_userId(userId);
	   out.println(jsonObj.toJSONString());
	   break;
   case "findQuestionInfo_qTitle":
	   qTitle = request.getParameter("qTitle");
	   jsonObj=Question_DB.findQuestionInfo_qTitle(qTitle);
	   out.println(jsonObj.toJSONString());
	   break;
   case "showAllQuestion":
	   jsonObj = Question_DB.showAllQuestion();
	   out.println(jsonObj.toJSONString());
	   break;	
   case "Question_Join_Answer":	   
	   userId = request.getParameter("userId");
	   jsonObj=Question_DB.Question_Join_Answer(userId);
	   out.println(jsonObj.toJSONString());
	   break;
	   
	   
	   //Answer함수
   case "createAnswerInfo":
	   answerDTO = new AnswerDTO(Integer.parseInt(request.getParameter("qNumber")),Integer.parseInt(request.getParameter("aNumber")),request.getParameter("aAnswer"),
			   request.getParameter("userId"));
	   Answer_DB.createAnswerInfo(answerDTO);	 
	   break;
   case "updateAnswerInfo":
	   answerDTO = new AnswerDTO(Integer.parseInt(request.getParameter("qNumber")),Integer.parseInt(request.getParameter("aNumber")),request.getParameter("aAnswer"),
			   request.getParameter("userId"));
	   Answer_DB.updateAnswerInfo(answerDTO);	
	   break;
   case "deleteAnswerInfo":
	   qNumber = Integer.parseInt(request.getParameter("qNumber"));
	   aNumber = Integer.parseInt(request.getParameter("aNumber"));
	   Answer_DB.deleteAnswerInfo(qNumber,aNumber);
	   break;
   case "findAnswerInfo":
	   qNumber = Integer.parseInt(request.getParameter("qNumber"));
	   jsonObj=Answer_DB.findAnswerInfo(qNumber);
	   out.println(jsonObj.toJSONString());
	   break;
	   

	   
   }
   
  
   DBConnection.closeConnection(con);

%>