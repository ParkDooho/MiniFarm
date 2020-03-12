package shinhan.ac.kr.minifarm_android.Z_Data;

public class SearchDTO_String {
    String userId;
    String searchName;
    String searchFD;
    String searchHum;
    String searchIllu;
    String searchPH;
    String searchTem;


    public SearchDTO_String(){
        super();
    }

    public SearchDTO_String(String searchName, String userId, String searchFD, String searchHum, String searchIllu, String searchPH, String searchTem){
        super();
        this.searchName=searchName;
        this.userId=userId;
        this.searchFD=searchFD;
        this.searchHum=searchHum;
        this.searchIllu=searchIllu;
        this.searchPH=searchPH;
        this.searchTem=searchTem;
    }

    public String getUserId(){

        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;

    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }


    public String getSearchFD(){

        return searchFD;
    }

    public void setSearchFD(String searchFD){
        this.searchFD=searchFD;

    }
    public String getSearchHum(){

        return searchHum;
    }

    public void setSearchHum(String searchHum){
        this.searchHum=searchHum;

    }
    public String getSearchIllu(){

        return searchIllu;
    }

    public void setSearchIllu(String searchIllu){
        this.searchIllu=searchIllu;

    }
    public String getSearchPH(){

        return searchPH;
    }

    public void setSearchPH(String searchPH){
        this.searchPH=searchPH;

    }
    public String getsearchTem(){

        return searchTem;
    }

    public void setSearchTem(String searchTem){
        this.searchTem=searchTem;

    }


    @Override
    public String toString() {
        return "SearchDTO [userId=" + userId + ", searchName=" + searchName + ", searchTem=" + "]";
    }

}
