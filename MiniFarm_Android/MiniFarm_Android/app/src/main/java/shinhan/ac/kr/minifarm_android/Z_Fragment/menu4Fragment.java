package shinhan.ac.kr.minifarm_android.Z_Fragment;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.C_Main.MainActivity;
import shinhan.ac.kr.minifarm_android.C_Main.SecessionActivity;
import shinhan.ac.kr.minifarm_android.C_Main.UserInfo_edit;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Userinfo_update;
import shinhan.ac.kr.minifarm_android.Z_Dialog.Dialog_logout;
import shinhan.ac.kr.minifarm_android.Z_Network.RequestHttpURLConnection;
import shinhan.ac.kr.minifarm_android.Z_Network.SeverURL;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class menu4Fragment extends Fragment {
    View view;
    ImageView iv_my_page_logout; //로그아웃 버튼
    RelativeLayout my_page_frag_scrab_bar_btn; //개인정보 수정
    TextView tv_my_page_more_my_crops; //작물 더보기
    TextView textId_my_page; //아이디
    String ID="";

    Dialog_logout cd; //커스텀다이얼로그
    public static menu4Fragment newInstance(){
        return new menu4Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_menu4,container,false);
        iv_my_page_logout=(ImageView)view.findViewById(R.id.iv_my_page_logout);
        my_page_frag_scrab_bar_btn=(RelativeLayout)view.findViewById(R.id.my_page_frag_scrab_bar_btn);
        tv_my_page_more_my_crops=(TextView)view.findViewById(R.id.tv_my_page_more_my_crops);

        textId_my_page=(TextView)view.findViewById(R.id.textId_my_page);

        ID=UserData.getUserId();
        textId_my_page.setText(ID);

        DisplayMetrics dm = getActivity().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        int width = dm.widthPixels; //디바이스 화면 너비
        int height = dm.heightPixels; //디바이스 화면 높이

        cd = new Dialog_logout(getContext());
       /* WindowManager.LayoutParams wm = cd.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
        wm.copyFrom(cd.getWindow().getAttributes());  //여기서 설정한값을 그대로 다이얼로그에 넣겠다는의미
        wm.width = width / 2;  //화면 너비의 절반
        wm.height = height / 2;  //화면 높이의 절반*/


        iv_my_page_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*new AlertDialog.Builder(getActivity())
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(getActivity(), LoginActivity.class);
                                startActivity(i);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();*/
                cd.show();
            }
        });


        my_page_frag_scrab_bar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfo_edit.class);
                view.getContext().startActivity(intent);
        }});


        tv_my_page_more_my_crops.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).replaceFragment(menu2Fragment.newInstance());
            }
        });


        return view;
    }


}
