package shinhan.ac.kr.minifarm_android.Z_Data;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;

public class Forgot_PW_check extends AppCompatActivity {
    String Id;
    String PwCheck;
    String PwAnswer;
    String userName;
    int PwQuestion;

    TextView textFpw;
    Button btnFLogin;
    Button btnFCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__pw_check);
        textFpw=(TextView)findViewById(R.id.textFpw);
        btnFLogin=(Button)findViewById(R.id.btnFLogin);
        btnFCancel=(Button)findViewById(R.id.btnFCancel);

        Intent intent = getIntent();
        Id = intent.getStringExtra("Id");
        PwCheck = intent.getStringExtra("PwQuestion");
        PwAnswer = intent.getStringExtra("PwAnswer");
        userName = intent.getStringExtra("userName");

        String url = SeverURL.URL();

        switch (PwCheck){
            case "기억에 남는 추억의 장소는?":
                PwQuestion = 1;
                break;
            case "자신의 보물 제1호는?":
                PwQuestion = 2;
                break;
            case "인상 깊게 읽은 책 이름은?":
                PwQuestion = 3;
                break;
            case "내가 좋아하는 캐릭터는?":
                PwQuestion = 4;
                break;
        }


        ContentValues val = new ContentValues();
        val.put("action", "findUserPw");
        val.put("userId", Id);
        val.put("userName", userName);
        val.put("userQuestion", PwQuestion);
        val.put("userAnswer", PwAnswer);

        Forgot_PW_check.NetworkTask networkTask = new Forgot_PW_check.NetworkTask(url, val);
        networkTask.execute();


        btnFLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgot_PW_check.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnFCancel.setOnClickListener(new View.OnClickListener() {
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
                    textFpw.setText(temp.getString("userPw"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

}
