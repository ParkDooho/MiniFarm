package shinhan.ac.kr.minifarm_android.Z_Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import shinhan.ac.kr.minifarm_android.C_Main.CheckmyqActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Adapter.Listview_qna;
import shinhan.ac.kr.minifarm_android.Z_Data.QuestionDTO;
import shinhan.ac.kr.minifarm_android.Z_Data.QuestionDTO_String;
import shinhan.ac.kr.minifarm_android.Z_Data.Question_Join_AnswerDTO;
import shinhan.ac.kr.minifarm_android.Z_Data.SearchDTO_String;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;


public class menu3Fragment_MyQ extends Fragment {
    View View;
    String ID=""; //아이디
    ListView list_q; //리스트뷰
    SwipeRefreshLayout layoutSr; //새로고침

    ArrayList<SearchDTO_String> Result = new ArrayList<SearchDTO_String>();
    String[] x = Result.toArray(new String[Result.size()]);
    menu3Fragment_MyQ.pjAdapter adapter = new menu3Fragment_MyQ.pjAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(View == null)
            View=inflater.inflate(R.layout.fragment_menu3_my_q, container, false);

        list_q=(ListView)View.findViewById(R.id.list_q);
        layoutSr = (SwipeRefreshLayout)View.findViewById(R.id.layoutSr);


        ID= UserData.getUserId();
        String url = SeverURL.URL();
        ContentValues val = new ContentValues();
        val.put("action", "Question_Join_Answer");
        val.put("userId", ID);

        menu3Fragment_MyQ.NetworkTask networkTask = new menu3Fragment_MyQ.NetworkTask(url, val); //여기에 넣기
        networkTask.execute();


        list_q.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question_Join_AnswerDTO Name = (Question_Join_AnswerDTO)adapter.getItem(position);
                Intent intent = new Intent(getActivity(), CheckmyqActivity.class);
                intent.putExtra("qNumber",Name.getqNumber());
                intent.putExtra("qTitle",Name.getqTitle());
                intent.putExtra("qQuestion",Name.getqQuestion());
                intent.putExtra("userId",Name.getUserId());
                intent.putExtra("qDate",Name.getqDate());
                intent.putExtra("aNumber",Name.getaNumber());
                intent.putExtra("aAnswer",Name.getaAnswer());
                View.getContext().startActivity(intent);
            }
        });

        layoutSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //((menu3Fragment)getContext()).refresh();
                layoutSr.setRefreshing(false);
            }
        });


        return View;
    }

    class pjAdapter extends BaseAdapter {

        ArrayList<Question_Join_AnswerDTO> items = new ArrayList();


        @Override
        public int getCount() {
            return items.size();    // 아이템의 갯수 (배열의 갯수)
        }

        // 클래스 밖에서 item data 추가하는 메소드 정의
        public void addItem(Question_Join_AnswerDTO item) {
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


            Listview_qna view = new Listview_qna(getActivity()); // 어떤 뷰든 안드로이드는 context객체를 받음*
            // 뷰의 몇번째 아이템인지 - position(index값)
            Question_Join_AnswerDTO item = items.get(position);
            view.setqTitle(item.getqTitle());       // name 설정
            view.setqDate(item.getqDate());   // mobile 설정

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
                    adapter.addItem(new Question_Join_AnswerDTO(temp.getString("qNumber"), temp.getString("qTitle"),temp.getString("qQuestion"),temp.getString("userId"),temp.getString("qDate"),temp.getString("aNumber"),temp.getString("aAnswer")));
                    list_q.setAdapter(adapter);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}
