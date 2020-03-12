package shinhan.ac.kr.minifarm_android.Z_Data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Fragment.idFragment;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;

public class Forgot_ID_check extends AppCompatActivity {
    String userName;
    TextView textFid;
    Button btnFLogin;
    Button btnFCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__id_check);

        textFid = (TextView)findViewById(R.id.textFid);
        btnFLogin=(Button)findViewById(R.id.btnFLogin);
        btnFCancel=(Button)findViewById(R.id.btnFCancel);

        Intent intent = getIntent();
        userName=intent.getStringExtra("userName");

        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "findUserInfo_userName");
        val.put("userName",userName);
        Forgot_ID_check.NetworkTask networkTask = new Forgot_ID_check.NetworkTask(url, val);
        networkTask.execute();


        btnFLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgot_ID_check.this,LoginActivity.class);
                startActivity(intent);
                finish();
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
                    textFid.setText(temp.getString("userId"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }




        }


    }




}
