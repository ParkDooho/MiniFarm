package shinhan.ac.kr.minifarm_android.Z_Fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import shinhan.ac.kr.minifarm_android.C_Main.MainActivity;
import shinhan.ac.kr.minifarm_android.C_Main.SearchActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Adapter.Listview_item;
import shinhan.ac.kr.minifarm_android.Z_Data.Crops_delete;
import shinhan.ac.kr.minifarm_android.Z_Data.SearchDTO_String;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;


public class menu2Fragment extends Fragment {
    View View;
    ImageView btnsearch;
    ListView listCrops; //리스트뷰
    String ID=""; //아이디
    SwipeRefreshLayout layoutSr;

    ArrayList<SearchDTO_String> Result = new ArrayList<SearchDTO_String>();
    String[] x = Result.toArray(new String[Result.size()]);
    pjAdapter adapter = new pjAdapter();


    public static menu2Fragment newInstance(){
        return new menu2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(View == null)
        View=inflater.inflate(R.layout.fragment_menu2, container, false);
        layoutSr = (SwipeRefreshLayout)View.findViewById(R.id.layoutSr);


        listCrops = (ListView)View.findViewById(R.id.listCrops);
        btnsearch=(ImageView)View.findViewById(R.id.btnsearch);

        ID= UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "findSearchInfo");
        val.put("userId", ID);

        menu2Fragment.NetworkTask networkTask = new menu2Fragment.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                View.getContext().startActivity(intent);
            }
        });

        listCrops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                SearchDTO_String Name = (SearchDTO_String)adapter.getItem(position);
                final String message = "Name = " + Name.getSearchName() + " / FD = " + Name.getSearchFD() + " / Hum = " + Name.getSearchHum() + " / Illu = " + Name.getSearchIllu() + " / PH = " + Name.getSearchPH() + " / Tem = " + Name.getsearchTem();
                final String ResultName = Name.getSearchName();
                //Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();



                DialogInterface.OnClickListener deleteListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), Crops_delete.class);
                        intent.putExtra("searchName",ResultName);
                        View.getContext().startActivity(intent);
                    }
                };


                new AlertDialog.Builder(getActivity())
                        .setTitle("나의 작물")
                        .setMessage(message)
                        .setPositiveButton("삭제", deleteListener)
                        .show();

            }
        });

        layoutSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((MainActivity)getActivity()).refresh();
                layoutSr.setRefreshing(false);
            }
        });



        return View;
    }

    class pjAdapter extends BaseAdapter {

        ArrayList<SearchDTO_String> items = new ArrayList();


        @Override
        public int getCount() {
            return items.size();    // 아이템의 갯수 (배열의 갯수)
        }

        // 클래스 밖에서 item data 추가하는 메소드 정의
        public void addItem(SearchDTO_String item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) { // 아이템 선택
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {   // id값이 있다면 넘겨줘라.
            return position;                   // 특정 아이디를 만들어서 넘겨줘도 됨.
        }


        // SingerItemView(아이템뷰)를 리턴하는 메소드
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            Listview_item view = new Listview_item(getActivity()); // 어떤 뷰든 안드로이드는 context객체를 받음*
            // 뷰의 몇번째 아이템인지 - position(index값)
            SearchDTO_String item = items.get(position);
            view.setSearchName(item.getSearchName());       // name 설정
            view.setSearchTem(item.getsearchTem());   // 온도
            view.setSearchHum(item.getSearchHum()); //습도
            view.setSearchIllu(item.getSearchIllu());   //조도
            view.setSearchPH(item.getSearchPH());   //농도
            return view;
        }
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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //final ListView listview = (ListView)view.findViewById(R.id.listview1);
            try {
                JSONObject resultData = new JSONObject(s);
                JSONArray resultArray = new JSONArray(resultData.getString("success"));

                for (int i = 0; i < resultArray.length(); i++) {

                    JSONObject temp = resultArray.getJSONObject(i);
                    adapter.addItem(new SearchDTO_String(temp.getString("searchName"), temp.getString("userId"),temp.getString("searchFD"),temp.getString("searchHum"),temp.getString("searchIllu"),temp.getString("searchPH"),temp.getString("searchTem")));
                    listCrops.setAdapter(adapter);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }



}
