package shinhan.ac.kr.minifarm_android.Z_Data;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;

public class Userinfo_delete extends AppCompatActivity {
    String ID;
    String PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_delete);
        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        PW=intent.getStringExtra("PW");

        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action","deleteUserInfo");
        val.put("userId",ID);
        val.put("userPw",PW);

        Userinfo_delete.NetworkTask networkTask = new Userinfo_delete.NetworkTask(url, val); //여기에 넣기
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
            Toast.makeText(getApplicationContext(), "회원정보 삭제 완료",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Userinfo_delete.this, LoginActivity.class);
            Userinfo_delete.this.startActivity(intent);
            finish();

        }
    }
}
