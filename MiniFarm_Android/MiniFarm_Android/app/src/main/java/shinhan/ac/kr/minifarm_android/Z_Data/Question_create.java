package shinhan.ac.kr.minifarm_android.Z_Data;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class Question_create extends AppCompatActivity {
    String qname;
    String qcontent;
    String ID;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_create);

        Intent intent = getIntent();
        qname=intent.getStringExtra("qTitle");
        qcontent=intent.getStringExtra("qQuestion");
        ID= UserData.getUserId();
        String url = SeverURL.URL();
        Toast.makeText(getApplicationContext(),ID, Toast.LENGTH_SHORT).show();

        ContentValues val = new ContentValues();
        val.put("action", "createQuestionInfo");
        val.put("qNumber", "10");
        val.put("qTitle",qname);
        val.put("userId",ID);
        val.put("qQuestion",qcontent);
        val.put("qDate",getTime());

        Toast.makeText(getApplicationContext(), getTime(), Toast.LENGTH_SHORT).show();
        Question_create.NetworkTask networkTask = new Question_create.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();

        finish();

    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
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
