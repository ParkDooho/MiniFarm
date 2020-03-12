package shinhan.ac.kr.minifarm_android.Z_Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Forgot_PW_check;

public class passwordFragment extends Fragment {
    View view;
    Button btnOk;
    Button btnCancel;
    EditText textId; //아이디
    Spinner spinneridQuestion; //질문선택
    EditText textAnswer; //답변
    EditText textName; //이름



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_password,container,false);
        btnOk=(Button)view.findViewById(R.id.btnOk);
        btnCancel=(Button)view.findViewById(R.id.btnCancel);
        textId=(EditText)view.findViewById(R.id.textId);
        textAnswer=(EditText)view.findViewById(R.id.textAnswer);
        textName=(EditText)view.findViewById(R.id.textName);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentManager manager = getActivity().getSupportFragmentManager();
                //manager.beginTransaction().remove(idFragment.this).commit();
                //manager.popBackStack();
                getActivity().finish();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id=textId.getText().toString();
                String PwQuestion=spinneridQuestion.getSelectedItem().toString();
                String PwAnswer=textAnswer.getText().toString();
                String userName=textName.getText().toString();


                Intent LogCheck = new Intent(view.getContext(), Forgot_PW_check.class);

                LogCheck.putExtra("Id",Id);
                LogCheck.putExtra("PwQuestion",PwQuestion);
                LogCheck.putExtra("PwAnswer",PwAnswer);
                LogCheck.putExtra("userName",userName);
                view.getContext().startActivity(LogCheck);
            }
        });


        final ArrayList<String> list = new ArrayList<>();
        list.add("기억에 남는 추억의 장소는?");
        list.add("자신의 보물 제1호는?");
        list.add("인상 깊게 읽은 책 이름은?");
        list.add("내가 좋아하는 캐릭터는?");

        spinneridQuestion = (Spinner)view.findViewById(R.id.spinneridQuestion);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item,list);
        spinneridQuestion.setAdapter(adapter);

        spinneridQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RegisterActivity.this,"선택된 아이템 : "+questionSpinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }
}
