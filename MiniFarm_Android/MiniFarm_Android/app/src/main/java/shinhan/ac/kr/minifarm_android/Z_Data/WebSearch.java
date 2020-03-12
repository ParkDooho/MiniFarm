 package shinhan.ac.kr.minifarm_android.Z_Data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

 public class WebSearch extends AppCompatActivity {
     String ID;
     String crop;
     String searchTem_Average;
     String searchName;
     String searchTem;
     String searchHum;
     String searchIllu;
     String searchPH;

     TextView cropName;
     TextView cropTem;
     TextView cropHum;
     TextView cropill;
     TextView cropPh;
     TextView cropAdd;
     TextView cropBack;

     Float Tem_average;


     public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search);
        mContext = this;
        Intent intent = getIntent();
        crop=intent.getStringExtra("searchData");
        searchTem_Average=intent.getStringExtra("searchTem_Average");//온도 중간값

        DisplayMetrics dm = getApplication().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        int width = dm.widthPixels; //디바이스 화면 너비
        int height = dm.heightPixels; //디바이스 화면 높이

        cropName=(TextView)findViewById(R.id.cropName);
        cropTem=(TextView)findViewById(R.id.cropTem);
        cropHum=(TextView)findViewById(R.id.cropHum);
        cropill=(TextView)findViewById(R.id.cropill);
        cropPh=(TextView)findViewById(R.id.cropPh);
        cropAdd=(TextView)findViewById(R.id.cropAdd);
        cropBack=(TextView)findViewById(R.id.cropBack);

        //Toast.makeText(getApplicationContext(),"넘어온온도값"+ searchTem_Average,Toast.LENGTH_SHORT).show();
        Tem_average=Float.parseFloat(searchTem_Average);
        Toast.makeText(getApplicationContext(),"넘어온 float타입 온도값"+ searchTem_Average,Toast.LENGTH_SHORT).show();




        ID= UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action","webSearch");
        val.put("userId",ID);
        val.put("searchData",crop);

        WebSearch.NetworkTask networkTask = new WebSearch.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();


        cropAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           if(Tem_average+8.0<=Float.parseFloat(searchHum)||Tem_average-8.0>=Float.parseFloat(searchHum)){
                Toast.makeText(getApplicationContext(),"이 작물은 재배할 수 없습니다.",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(WebSearch.this, Crops_create.class);
                intent.putExtra("searchName", searchName);
                intent.putExtra("searchTem", searchTem);
                intent.putExtra("searchHum", searchHum);
                intent.putExtra("searchIllu", searchIllu);
                intent.putExtra("searchPH", searchPH);
                WebSearch.this.startActivity(intent);
                finish();
            }
            }
        });

        cropBack.setOnClickListener(new View.OnClickListener() {
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
                     //Toast.makeText(getApplicationContext(),temp.getString("userId"),Toast.LENGTH_SHORT).show();
                     if(!crop.equals(temp.getString("searchName"))){
                             Toast.makeText(getApplicationContext(),"일치하는 작물이 없습니다.",Toast.LENGTH_SHORT).show();
                             finish();
                     }
                     else {

                         searchName = temp.getString("searchName");
                         searchTem = temp.getString("searchTem");
                         searchHum = temp.getString("searchHum");
                         searchIllu = temp.getString("searchIllu");
                         searchPH = temp.getString("searchPH");

                         cropName.setText(searchName);
                         cropTem.setText(searchTem);
                         cropHum.setText(searchHum);
                         cropill.setText(searchIllu);
                         cropPh.setText(searchPH);
                     }

                     /*
                     Intent intent = new Intent(WebSearch.this,Crops_check.class);
                     intent.putExtra("searchName",searchName);
                     intent.putExtra("searchTem",searchTem);
                     intent.putExtra("searchHum",searchHum);
                     intent.putExtra("searchIllu",searchIllu);
                     intent.putExtra("searchPH",searchPH);
                     WebSearch.this.startActivity(intent);*/

                 }



             } catch (JSONException e) {
                 e.printStackTrace();
             }

         }

         }

         public void Finish(){
            finish();
         }


}
