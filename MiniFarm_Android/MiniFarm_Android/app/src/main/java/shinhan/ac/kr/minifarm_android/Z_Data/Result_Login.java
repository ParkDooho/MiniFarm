package shinhan.ac.kr.minifarm_android.Z_Data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.C_Main.MainActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Fragment.menu1Fragment;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class Result_Login extends AppCompatActivity {
    String ID;
    String PW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__login);
        Intent intent = getIntent();
        ID=intent.getStringExtra("ID");
        PW=intent.getStringExtra("PW");
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "userLogin");
        val.put("userId",ID);
        val.put("userPw",PW);

        Result_Login.NetworkTask networkTask = new Result_Login.NetworkTask(url, val); //여기에 넣기
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



            try {
                JSONObject resultData = new JSONObject(s);
                JSONArray resultArray = new JSONArray(resultData.getString("success"));
                int check = 0;
                for (int i = 0; i < resultArray.length(); i++) {
                    JSONObject temp = resultArray.getJSONObject(0);
                   //Toast.makeText(getApplicationContext(),temp.getString("userId"),Toast.LENGTH_SHORT).show();

                    if(ID.equals(temp.getString("userId"))&&PW.equals(temp.getString("userPw"))){
                        check=1;
                    }
                }
                    if(check == 1){
                        UserData.setUserId(ID);
                        Intent Loginintent = new Intent(Result_Login.this, MainActivity.class);
                        //Loginintent.putExtra("userId",ID);
                        Result_Login.this.startActivity(Loginintent);
                        LoginActivity finishLog = (LoginActivity)LoginActivity.LoginActivity;
                        finishLog.finish();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplication(),"아이디 혹은 비밀번호를 확인바랍니다.",Toast.LENGTH_SHORT).show();
                        Intent returnData = new Intent(Result_Login.this, LoginActivity.class);
                        returnData.putExtra("ID", ID);
                        returnData.putExtra("PW", PW);
                        setResult(Activity.RESULT_OK, returnData);
                        finish();
                    }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
