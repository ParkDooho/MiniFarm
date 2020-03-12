package shinhan.ac.kr.minifarm_android.C_Main;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Userinfo_update;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class UserInfo_edit extends AppCompatActivity {

    String ID = ""; //아이디
    String PW = ""; //비밀번호
    String PWcheck = ""; //비밀번호 확인받아온거
    String NAME = ""; //이름
    String Q = ""; //질문
    String A = ""; //답변

    ImageView btn_user_info_edit_back; //뒤로가기 버튼
    TextView btn_user_info_edit_complete; //회원정보 수정완료 버튼
    TextView btnsecession; //회원탈퇴 버튼
    EditText tv_mypw; //비밀번호
    EditText tv_mypwcheck; //비밀번호 체크

    TextView tv_myid; //아이디
    int check = 0; //비밀번호 일치하는지 확인하는 거

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);

        btn_user_info_edit_back = (ImageView) findViewById(R.id.btn_user_info_edit_back);
        btn_user_info_edit_complete = (TextView) findViewById(R.id.btn_user_info_edit_complete);
        btnsecession = (TextView) findViewById(R.id.btnsecession);
        tv_mypw = (EditText) findViewById(R.id.tv_mypw);
        tv_mypwcheck = (EditText) findViewById(R.id.tv_mypwcheck);
        tv_myid=(TextView)findViewById(R.id.tv_myid);

        ID = UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "findUserInfo_userId");
        val.put("userId", ID);

        UserInfo_edit.NetworkTask networkTask = new UserInfo_edit.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();

        tv_myid.setText(ID);
        btn_user_info_edit_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PW=tv_mypw.getText().toString().trim();
                PWcheck=tv_mypwcheck.getText().toString().trim();

                if(PW.equals(PWcheck) && !(PW.equals(""))){
                    check=1;
                }
                else{
                    Toast.makeText(UserInfo_edit.this,"비밀번호 확인.",Toast.LENGTH_SHORT).show();
                }

                if(check==1){
                    Intent intent = new Intent(UserInfo_edit.this, Userinfo_update.class);
                    intent.putExtra("ID",ID);
                    intent.putExtra("PW",PW);
                    intent.putExtra("NAME",NAME);
                    intent.putExtra("Q",Q);
                    intent.putExtra("A",A);
                    UserInfo_edit.this.startActivity(intent);
                }


            }
        });

        btn_user_info_edit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnsecession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfo_edit.this, SecessionActivity.class);
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
                    NAME = temp.getString("userName");
                    Q = temp.getString("userQuestion");
                    A = temp.getString("userAnswer");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
