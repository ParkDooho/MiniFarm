package shinhan.ac.kr.minifarm_android.C_Main;

import android.content.ContentValues;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Userinfo_delete;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class SecessionCheckActivity extends AppCompatActivity {

    TextView btnsecessionok;
    ImageView btnsecessioncancel;
    EditText textpw; //비밀번호 입력한거
    String ID; //아이디
    String PW; //비밀번호
    int check = 0; //비밀번호 확인하는거


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secessioncheck);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnsecessionok=(TextView)findViewById(R.id.btnsecessionok);
        btnsecessioncancel=(ImageView)findViewById(R.id.btnsecessioncancel);
        textpw=(EditText)findViewById(R.id.textpw);

        ID= UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "findUserInfo_userId");
        val.put("userId", ID);

        SecessionCheckActivity.NetworkTask networkTask = new SecessionCheckActivity.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();

        btnsecessionok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PW.equals(textpw.getText().toString()) && !(PW.equals(""))){
                    Toast.makeText(getApplicationContext(),"비밀번호 확인 완료",Toast.LENGTH_SHORT).show();
                    check=1;
                }

                if(check==1){
                    Intent intent = new Intent(SecessionCheckActivity.this, Userinfo_delete.class);
                    intent.putExtra("ID",ID);
                    intent.putExtra("PW",PW);
                    SecessionCheckActivity.this.startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"비밀번호 확인 해주세요.",Toast.LENGTH_SHORT).show();
                    textpw.setText(null);
                }

                //Intent intent = new Intent(SecessionCheckActivity.this,LoginActivity.class);
                //startActivity(intent);
            }
        });


        btnsecessioncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecessionCheckActivity.this,SecessionActivity.class);
                startActivity(intent);
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
                    PW=temp.getString("userPw");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }





}
