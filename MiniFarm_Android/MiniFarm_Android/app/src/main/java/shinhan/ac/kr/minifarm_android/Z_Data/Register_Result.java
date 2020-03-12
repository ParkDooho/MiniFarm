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

public class Register_Result extends AppCompatActivity {

    String Id;
    String Pw;
    String PwCheck;
    //String PwQuestion;
    String PwAnswer;
    String userName;
    int PwQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__result);

        Intent intent = getIntent();
        Id = intent.getStringExtra("Id");
        Pw = intent.getStringExtra("Pw");
        PwCheck = intent.getStringExtra("PwQuestion");
        PwAnswer = intent.getStringExtra("PwAnswer");
        userName = intent.getStringExtra("userName");
        String url = SeverURL.URL();

        //Toast.makeText(getApplication(),Id ,Toast.LENGTH_SHORT).show();

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
        val.put("action", "createUserInfo");
        val.put("userId", Id);
        val.put("userPw", Pw);
        val.put("userName", userName);
        val.put("userQuestion", PwQuestion);
        val.put("userAnswer", PwAnswer);

        Register_Result.NetworkTask networkTask = new Register_Result.NetworkTask(url, val);
        networkTask.execute();

        Toast.makeText(getApplication(), "회원가입 완료", Toast.LENGTH_SHORT).show();

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
