package shinhan.ac.kr.minifarm_android.Z_Fragment;


import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shinhan.ac.kr.minifarm_android.C_Main.MainActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Dialog.Dialog_curtain;
import shinhan.ac.kr.minifarm_android.Z_Dialog.Dialog_fan;
import shinhan.ac.kr.minifarm_android.Z_Dialog.Dialog_sprinkler;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;


public class menu1Fragment extends Fragment {
    View view;
    String ID;

    TextView sensorTem;
    TextView sensorFD;
    TextView sensorName;
    TextView sensorHum;
    TextView sensorIllu;
    TextView sensorPH;

    ImageView Image_search;

    LinearLayout my_sensor;
    LinearLayout tem_sensor;
    LinearLayout hum_sensor;
    LinearLayout ill_sensor;
    LinearLayout ph_sensor;


    Dialog_fan fan; //커스텀다이얼로그
    Dialog_curtain curtain; //커스텀다이얼로그
    Dialog_sprinkler sprinkler; //커스텀다이얼로그

    public static menu1Fragment newInstance(){
        return new menu1Fragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_menu1,container,false);
        sensorTem = (TextView)view.findViewById(R.id.sensorTem);
        sensorHum=(TextView)view.findViewById(R.id.sensorHum);
        sensorIllu=(TextView)view.findViewById(R.id.sensorIllu);
        sensorPH=(TextView)view.findViewById(R.id.sensorPH);

        my_sensor=(LinearLayout)view.findViewById(R.id.my_sensor);
        tem_sensor=(LinearLayout)view.findViewById(R.id.tem_sensor);
        hum_sensor=(LinearLayout)view.findViewById(R.id.hum_sensor);
        ill_sensor=(LinearLayout)view.findViewById(R.id.ill_sensor);
        ph_sensor=(LinearLayout)view.findViewById(R.id.ph_sensor);

        ID= UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "findSensorInfo");
        val.put("userId",ID);
        menu1Fragment.NetworkTask networkTask = new menu1Fragment.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();

        fan = new Dialog_fan(getContext());
        curtain = new Dialog_curtain(getContext());
        sprinkler = new Dialog_sprinkler(getContext());



        my_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).refresh1();
            }
        });



        tem_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fan.show();
            }
        });

        ill_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //curtain.show();
            }
        });

        ph_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sprinkler.show();
            }
        });


        /*btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(searchFragment.newInstance());


            }
        });*/

      /*  Image_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                view.getContext().startActivity(intent);
            }
        });*/


        return view;
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
                    if(ID.equals(temp.getString("userId"))){
                        sensorTem.setText("온도 : "+temp.getString("sensorTem"));
                        //sensorFD.setText("모르겠음 : "+temp.getString("sensorFD"));
                        //sensorName.setText("작물이름 : "+temp.getString("sensorName"));
                        sensorHum.setText("습도 :"+temp.getString("sensorHum"));
                        sensorIllu.setText("조도 :"+temp.getString("sensorIllu"));
                        sensorPH.setText("농도 :"+temp.getString("sensorPH"));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
