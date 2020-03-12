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

import shinhan.ac.kr.minifarm_android.B_Login.RegisterActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;

public class Register_ID_Check extends AppCompatActivity {
    String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__id__check);
        Intent intent = getIntent();
        Id=intent.getStringExtra("Id");
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "findUserInfo_userId");
        val.put("userId",Id);
        Register_ID_Check.NetworkTask networkTask = new Register_ID_Check.NetworkTask(url, val);
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
                            // Toast.makeText(getApplication(), temp.getString("userId"), Toast.LENGTH_SHORT).show();
                            if (Id.equals(temp.getString("userId"))) {
                                check=1;
                            }
                    }
                if(check == 1){
                    Toast.makeText(getApplication(), "사용 할 수 없는 아이디입니다.", Toast.LENGTH_SHORT).show();}
                else if(check == 0){
                    Toast.makeText(getApplication(), "사용 할 수 있는 아이디입니다.", Toast.LENGTH_SHORT).show();}
                Intent returnData = new Intent(Register_ID_Check.this, RegisterActivity.class);
                //returnData.putExtra("Id", Id);
                returnData.putExtra("Check", check);

                setResult(Activity.RESULT_OK, returnData);
                finish();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
