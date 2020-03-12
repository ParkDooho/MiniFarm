package shinhan.ac.kr.minifarm_android.Z_Data;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import shinhan.ac.kr.minifarm_android.C_Main.MainActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;

public class Userinfo_update extends AppCompatActivity {
    String ID;
    String PW;
    String NAME;
    String Q;
    String A;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_update);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        PW = intent.getStringExtra("PW");
        NAME=intent.getStringExtra("NAME");
        Q=intent.getStringExtra("Q");
        A=intent.getStringExtra("A");

        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action","updateUserInfo");
        val.put("userId",ID);
        val.put("userPw",PW);
        val.put("userName",NAME);
        val.put("userQuestion",Q);
        val.put("userAnswer",A);
        Userinfo_update.NetworkTask networkTask = new Userinfo_update.NetworkTask(url, val); //여기에 넣기
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
            Toast.makeText(getApplicationContext(), "회원정보 수정 완료",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Userinfo_update.this, MainActivity.class);
            Userinfo_update.this.startActivity(intent);
            finish();

        }
    }
}
