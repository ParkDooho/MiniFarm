package Project_Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;


public class SearchDAO {
	private Connection con;

	public SearchDAO(Connection con) {
		super();
		this.con = con;
	}

	// 센서 정보 생성
	public void createSearchInfo(SearchDTO nSearch) {
		String sql = "insert into search values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nSearch.getUserId().replace("\r\n", ""));
			pstmt.setString(2, nSearch.getSearchName().replace("\r\n", ""));
			pstmt.setFloat(3, nSearch.getSearchTem());
			pstmt.setFloat(4, nSearch.getSearchHum());
			pstmt.setInt(5,nSearch.getSearchIllu());
			pstmt.setFloat(6, nSearch.getSearchPH());
			pstmt.setInt(7, nSearch.getSearchFD());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("검색 데이터 생성");
	}

	// 센서 정보 수정
	public void updateSearchInfo(SearchDTO nSearch) {
		String sql = "update search set searchName=?, searchTem=?, searchHum=?, searchIllu=?, searchPH=?, searchFd=? where userId=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, nSearch.getSearchName().replace("\r\n", ""));
			pstmt.setFloat(2, nSearch.getSearchTem());
			pstmt.setFloat(3, nSearch.getSearchHum());
			pstmt.setInt(4,nSearch.getSearchIllu());
			pstmt.setFloat(5, nSearch.getSearchPH());
			pstmt.setInt(6, nSearch.getSearchFD());
			pstmt.setString(7, nSearch.getUserId().replace("\r\n", ""));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("검색 데이터 수정");
	}

	// 센서 정보 삭제
	public void deleteSearchInfo(String userId , String searchName) {
		String sql = "delete from search where userId=? and searchName=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			pstmt.setString(2, searchName.replace("\r\n", ""));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("검색 데이터 삭제_이름");
	}

	// 센서 정보 조회
	@SuppressWarnings("unchecked")
	public JSONObject findSearchInfo(String userId) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from search where userId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				SearchDTO temp = new SearchDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setSearchName(rs.getString("searchName"));
				temp.setSearchTem(rs.getFloat("searchTem"));
				temp.setSearchHum(rs.getFloat("searchHum"));
				temp.setSearchIllu(rs.getInt("searchIllu"));
				temp.setSearchPH(rs.getFloat("searchPH"));
				temp.setSearchFD(rs.getInt("searchFD"));

				jObject.put("userId", temp.getUserId());
				jObject.put("searchName", temp.getSearchName());
				jObject.put("searchTem", temp.getSearchTem());
				jObject.put("searchHum", temp.getSearchHum());
				jObject.put("searchIllu", temp.getSearchIllu());
				jObject.put("searchPH", temp.getSearchPH());
				jObject.put("searchFD", temp.getSearchFD());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("검색 데이터 조회_아이디");
		return jsonMain;
	}

	
	@SuppressWarnings("unchecked")
	public JSONObject Average_Search(String userId) {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from search where userId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId.replace("\r\n", ""));
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			
			String av_Id = "";
			String av_Name = "";
			float av_tem = (float)0.0;
			float av_hum = (float)0.0;
			int av_Illu =0;
			float av_Ph = (float)0.0;
			
			while (rs.next()) {		
				SearchDTO temp = new SearchDTO();
				temp.setUserId(rs.getString("userId"));
				temp.setSearchName(rs.getString("searchName"));
				temp.setSearchTem(rs.getFloat("searchTem"));
				temp.setSearchHum(rs.getFloat("searchHum"));
				temp.setSearchIllu(rs.getInt("searchIllu"));
				temp.setSearchPH(rs.getFloat("searchPH"));
				temp.setSearchFD(rs.getInt("searchFD"));
				av_Id=temp.getUserId();
				av_Name+=temp.getSearchName()+" ";
				av_tem+= temp.getSearchTem();
				av_hum+= temp.getSearchHum();
				av_Illu+= temp.getSearchIllu();
				av_Ph+= temp.getSearchPH();
				count++;
			}

			JSONObject jObject = new JSONObject();
			jObject.put("userId",av_Id);
			jObject.put("searchName", av_Name);
			jObject.put("searchTem", av_tem/count);
			jObject.put("searchHum", av_hum/count);
			jObject.put("searchIllu", (int)av_Illu/count);
			jObject.put("searchPH", av_Ph/count);
			jObject.put("searchFD", 0);
			JArray.add(0, jObject);
			
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("검색 데이터 조회_평균");
		return jsonMain;
	}
	
	
	// 모든 센서 정보 조회
	@SuppressWarnings("unchecked")
	public JSONObject showAllsearch() {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();
		String sql = "select * from search";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				SearchDTO temp = new SearchDTO();
				JSONObject jObject = new JSONObject();
				temp.setUserId(rs.getString("userId"));
				temp.setSearchName(rs.getString("searchName"));
				temp.setSearchTem(rs.getFloat("searchTem"));
				temp.setSearchHum(rs.getFloat("searchHum"));
				temp.setSearchIllu(rs.getInt("searchIllu"));
				temp.setSearchPH(rs.getFloat("searchPH"));
				temp.setSearchFD(rs.getInt("searchFD"));

				jObject.put("userId", temp.getUserId());
				jObject.put("searchName", temp.getSearchName());
				jObject.put("searchTem", temp.getSearchTem());
				jObject.put("searchHum", temp.getSearchHum());
				jObject.put("searchIllu", temp.getSearchIllu());
				jObject.put("searchPH", temp.getSearchPH());
				jObject.put("searchFD", temp.getSearchFD());
				JArray.add(count, jObject);
				count++;
			}
			jsonMain.put("success", JArray);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("모든 검색 데이터 출력");
		return jsonMain;
	}
	
	
	
	@SuppressWarnings({ "unchecked" })
	public JSONObject webSearch(String userId, String searchData) throws IOException {
		JSONObject jsonMain = new JSONObject();
		JSONArray JArray = new JSONArray();		
		
		float temData=0;
		float humData=(float)47.0;
		int illuData=78;  //max 85
		float phData=(float)6.8;
		int fdData=0;

		String startUrl = "http://www.nongsaro.go.kr/portal/search/nongsaroSearch.ps?menuId=PS00007&categoryName=SCH01&sortOrdr=02&pageIndex=1&pageSize=10&pageUnit=10&includeWord=&exEqWord=&ikEqWork=&excludeWord=&Hflag=&qura=&reCountingYn=Y&categoryNm=SCH01&searchWord=";
		String TemUrl = "+%EC%83%9D%EC%9C%A1%EC%A0%81%EC%98%A8&option=0&dispIncludeWord=&dispExEqWord=&dispIkEqWork=&dispExcludeWord=";
		Document doc = null;

		try {
			doc = Jsoup.connect(startUrl+searchData+TemUrl).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select(".result-list");
		String data = element.text();
		
		//온도
		Pattern pattern = Pattern.compile("(.*?)℃");
		Matcher matcher = pattern.matcher(data);
		List<String> stringList = new ArrayList<String>();
		
		int count = 0;
		
		while (matcher.find()) {
			if(Float.parseFloat(matcher.group(0).replaceAll("[^0-9]", "").replace("℃", "~").replace(" ", "").replace("-","~"))<40 &&Float.parseFloat(matcher.group(0).replaceAll("[^0-9]", "").replace("℃", "~").replace(" ", "").replace("-","~"))>0)
			{
				stringList.add(count, matcher.group(0).replaceAll("[^0-9]", "").replace("℃", "~").replace(" ", "").replace("-","~"));	
				count++;
			}
			
		}
		
		int temp =0;	
		if(!(stringList.get(0)==null)) {
			for(int i = 0 ; i <stringList.size(); i++) {
				temp+=Integer.parseInt(stringList.get(i));
			}
			temData=temp/stringList.size();
		}else {
			temData=22;
		}
		
		if(searchData.contains("버섯")) {
			humData=(float)55.0;
			illuData = 55; //수정
		}
		
		
		//Json파시
		JSONObject jObject = new JSONObject();
		jObject.put("userId", userId.replace("\r\n", ""));
		jObject.put("searchName", searchData.replace("\r\n", ""));
		jObject.put("searchTem", temData);
		jObject.put("searchHum", humData);
		jObject.put("searchIllu", illuData);
		jObject.put("searchPH", phData);
		jObject.put("searchFD", fdData);
		JArray.add(0, jObject);
		jsonMain.put("success", JArray);
		
		return jsonMain;
	}
}
