package shinhan.ac.kr.minifarm_android.Z_Data;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class Crops_create extends AppCompatActivity {
    String ID;
    String searchName;
    String searchTem;
    String searchHum;
    String searchIllu;
    String searchPH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crops_create);


        Intent intent = getIntent();
        searchName=intent.getStringExtra("searchName");
        searchTem=intent.getStringExtra("searchTem");
        searchHum=intent.getStringExtra("searchHum");
        searchIllu=intent.getStringExtra("searchIllu");
        searchPH=intent.getStringExtra("searchPH");
        ID= UserData.getUserId();
        String url = SeverURL.URL();

        ContentValues val = new ContentValues();
        val.put("action", "createSearchInfo");
        val.put("userId",ID);
        val.put("searchName", searchName);
        val.put("searchTem",searchTem);
        val.put("searchHum",searchHum);
        val.put("searchIllu",searchIllu);
        val.put("searchPH",searchPH);
        val.put("searchFD", 0);

        Crops_create.NetworkTask networkTask = new Crops_create.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();

        finish();

    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.

            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다


            return result;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }

    }
}
