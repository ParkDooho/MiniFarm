package shinhan.ac.kr.minifarm_android.Z_Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Adapter.ExpandableListAdapter;


public class menu3Fragment_FAQ extends Fragment {
    View View;
    ExpandableListView expandableListView;
    android.widget.ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(View == null)
            View=inflater.inflate(R.layout.fragment_menu3_faq, container, false);
        expandableListView = (ExpandableListView)View.findViewById(R.id.expandableListView);
        expandableListDetail = new ExpandableListData().getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);






        return View;

    }

    public class ExpandableListData {
        public HashMap<String, List<String>> getData() {

            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> question1 = new ArrayList<String>();
            question1.add("A)   Mini Farm을 이용하기 위해서는 회원가입이 꼭 필요합니다. \n\n" +
                    "회원가입 후 로그인에 성공하게 되면 메뉴에 가입 시 작성했던 해당 이름과 함께 나타나는 것을 볼 수 있습니다. \n\n" +
                    "정보를 수정하기 위해서는 마이페이지->회원정보관리에 들어가 정보 변경 후 확인버튼을 누르면 " +
                    "원하는 이름으로 변경 후 수정버튼을 누르면 앱을 껐다가 켰을 때 변경된 이름으로 업데이트가 되게 됩니다. \n\n" +
                    "Update 2019.07.18");

            List<String> question2 = new ArrayList<String>();
            question2.add("A) 로그인 화면에서 비밀번호 찾기를 이용할 수 있습니다. \n\n회원가입에 사용한 질문과 답변을 " +
                    "일치하게 적어주시면 비밀번호를 쉽게 찾을 수 있습니다. \n\n " +
                    "Update 2019.07.18");

            List<String> question3 = new ArrayList<String>();
            question3.add("A) (상극식물 답변).\n\n" +
                    " \n\n" +
                    "Update 2019.07.18");

            List<String> question4 = new ArrayList<String>();
            question4.add("A) (원하는 식물이 없을때).\n\n" +
                    " \n\n" +
                    "Update 2019.07.18");

            expandableListDetail.put(" Q) 회원정보를 수정하고 싶을때는 어떻게 해야하나요?", question1);
            expandableListDetail.put(" Q) 비밀번호를 잊어버렸을떄는 어떻게 해야하나요?", question2);
            expandableListDetail.put(" Q) 상극식물일때는 어떻게 해야하나요?", question3);
            expandableListDetail.put(" Q) 원하는 작물이 없을때는 어떻게 해야하나요?", question4);


            return expandableListDetail;
        }
    }
}
