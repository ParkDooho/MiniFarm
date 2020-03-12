package Project_Server;

public class SearchDTO {
	private String userId;
	private String searchName;
	private float searchTem;
	private float searchHum;
	private int searchIllu;
	private float searchPH;
	private int searchFD;
	
	public SearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public SearchDTO(String userId, String searchName, float searchTem, float searchHum, int searchIllu, float searchPH,
			int searchFD) {
		super();
		this.userId = userId;
		this.searchName = searchName;
		this.searchTem = searchTem;
		this.searchHum = searchHum;
		this.searchIllu = searchIllu;
		this.searchPH = searchPH;
		this.searchFD = searchFD;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public float getSearchTem() {
		return searchTem;
	}

	public void setSearchTem(float searchTem) {
		this.searchTem = searchTem;
	}

	public float getSearchHum() {
		return searchHum;
	}

	public void setSearchHum(float searchHum) {
		this.searchHum = searchHum;
	}

	public int getSearchIllu() {
		return searchIllu;
	}

	public void setSearchIllu(int searchIllu) {
		this.searchIllu = searchIllu;
	}

	public float getSearchPH() {
		return searchPH;
	}

	public void setSearchPH(float searchPH) {
		this.searchPH = searchPH;
	}

	public int getSearchFD() {
		return searchFD;
	}

	public void setSearchFD(int searchFD) {
		this.searchFD = searchFD;
	}

	@Override
	public String toString() {
		return "SearchDTO [userId=" + userId + ", searchName=" + searchName + ", searchTem=" + searchTem
				+ ", searchHum=" + searchHum + ", searchIllu=" + searchIllu + ", searchPH=" + searchPH + ", searchFD="
				+ searchFD + "]";
	}

	
	
}
