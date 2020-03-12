package shinhan.ac.kr.minifarm_android.Z_Fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shinhan.ac.kr.minifarm_android.C_Main.QuestionActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Adapter.ExpandableListAdapter;
import shinhan.ac.kr.minifarm_android.Z_Data.Question_Join_AnswerDTO;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class menu3Fragment extends Fragment {
    View view;
    ImageView btnAddQ;//문의사항 작성
    TextView textFaq;
    TextView textmyq;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_menu3, container, false);

        getFragmentManager().beginTransaction().add(R.id.child_fragment, new menu3Fragment_FAQ()).commit();
        RelativeLayout re_faq = (RelativeLayout)view.findViewById(R.id.re_faq);
        RelativeLayout re_myq = (RelativeLayout)view.findViewById(R.id.re_myq);

        btnAddQ = (ImageView)view.findViewById(R.id.btnAddQ);
        textFaq = (TextView)view.findViewById(R.id.textFaq);
        textmyq = (TextView)view.findViewById(R.id.textmyq);


        re_faq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textmyq.setTextColor(Color.parseColor("#414141"));
                textFaq.setTextColor(Color.parseColor("#A1CDE1"));
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new menu3Fragment_FAQ()).commit();
            }
        });

        re_myq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textFaq.setTextColor(Color.parseColor("#414141"));
                textmyq.setTextColor(Color.parseColor("#A1CDE1"));
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new menu3Fragment_MyQ()).commit();
            }
        });


        btnAddQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuestionActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }

    public void refresh(){
        getFragmentManager().beginTransaction().replace(R.id.child_fragment, new menu3Fragment_MyQ()).commit();
    }






}