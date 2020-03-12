package shinhan.ac.kr.minifarm_android.Z_Data;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class Crops_delete extends AppCompatActivity {
    String ID;
    String searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crops_delete);

        Intent intent = getIntent();
        searchName = intent.getStringExtra("searchName");
        Toast.makeText(getApplicationContext(), searchName, Toast.LENGTH_SHORT).show();
        ID= UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "deleteSearchInfo");
        val.put("userId", ID);
        val.put("searchName", searchName);

        Crops_delete.NetworkTask networkTask = new Crops_delete.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();
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
            Toast.makeText(getApplicationContext(), "작물 삭제 완료",Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(Crops_delete.this, LoginActivity.class);
            //Crops_delete.this.startActivity(intent);
            finish();

        }
    }
}
