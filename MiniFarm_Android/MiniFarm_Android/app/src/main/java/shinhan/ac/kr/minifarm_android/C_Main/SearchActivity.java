package shinhan.ac.kr.minifarm_android.C_Main;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.WebSearch;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class SearchActivity extends AppCompatActivity {
    String ID; //ID
    String Tem; //온도
    ImageView searchok; //검색버튼
    EditText search_crop; //검색한 작물
    String crop;//작물
    RelativeLayout search_act_back_btn; //뒤로가기버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();


        searchok=(ImageView)findViewById(R.id.searchok);
        search_crop=(EditText)findViewById(R.id.search_crop);
        search_act_back_btn=(RelativeLayout)findViewById(R.id.search_act_back_btn);

        ID= UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action","Average_Search");
        val.put("userId",ID);

        SearchActivity.NetworkTask networkTask = new SearchActivity.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();




        searchok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crop=search_crop.getText().toString();
                if(crop.equals("")){
                    Toast.makeText(getApplicationContext(), "작물을 입력해 주세요.", Toast.LENGTH_SHORT).show();;
                }
                else{
                    Intent intent = new Intent(SearchActivity.this, WebSearch.class);
                    intent.putExtra("searchData",crop);
                    intent.putExtra("searchTem_Average",Tem);
                    SearchActivity.this.startActivity(intent);
                    finish();
                }
            }
        });

        search_act_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
            try {
                JSONObject resultData = new JSONObject(s);
                JSONArray resultArray = new JSONArray(resultData.getString("success"));
                for (int i = 0; i < resultArray.length(); i++) {
                    JSONObject temp = resultArray.getJSONObject(0);
                    Tem=temp.getString("searchTem");

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
