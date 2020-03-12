package shinhan.ac.kr.minifarm_android.B_Login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Register_ID_Check;
import shinhan.ac.kr.minifarm_android.Z_Data.Register_Result;

public class RegisterActivity extends AppCompatActivity {
    EditText textId; //아이디입력
    Button btnId; //중복확인
    EditText textPassword; //비밀번호
    EditText textPasswordcheck; //비밀번호 확인
    Spinner questionSpinner; //비밀번호 질문 선택
    EditText textAnswer; //비밀번호 답변
    EditText textName; //이름
    Button btnOk; //회원가입버튼
    Button btnCancel; //뒤로가기버튼
    final static int Register_ResultData = 0;
    int check = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textId = (EditText)findViewById(R.id.textId);
        btnId = (Button)findViewById(R.id.btnId);
        textPassword = (EditText)findViewById(R.id.textPassword);
        textPasswordcheck = (EditText)findViewById(R.id.textPasswordcheck);
        textAnswer = (EditText)findViewById(R.id.textAnswer);
        textName = (EditText)findViewById(R.id.textName);
        btnOk = (Button)findViewById(R.id.btnOk);
        btnCancel = (Button)findViewById(R.id.btnCancel);


        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //id체크
                String Id = textId.getText().toString().trim();
                if(Id.equals("")){
                    Toast.makeText(getApplication(), "ID를 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    Intent LogCheck = new Intent(RegisterActivity.this, Register_ID_Check.class);
                    LogCheck.putExtra("Id",Id);
                    startActivityForResult(LogCheck,Register_ResultData);
            }
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id=textId.getText().toString();
                String Pw=textPassword.getText().toString();
                String PwCheck=textPasswordcheck.getText().toString();
                String PwQuestion=questionSpinner.getSelectedItem().toString();
                String PwAnswer=textAnswer.getText().toString();
                String userName=textName.getText().toString();

                if(check==1){
                    Toast.makeText(getApplicationContext(),"아이디 중복 체크 확인",Toast.LENGTH_SHORT).show();
                }
                else if(!Pw.equals(PwCheck)){
                    Toast.makeText(getApplicationContext(),"비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }
                else if(PwQuestion.equals("")){
                    Toast.makeText(getApplicationContext(),"비밀번호 질문 선택해주세요",Toast.LENGTH_SHORT).show();
                }
                else if(PwAnswer.equals("")){
                    Toast.makeText(getApplicationContext(),"비밀번호 답변 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if (userName.equals("")) {
                    Toast.makeText(getApplicationContext(),"이름을 입력해주세요",Toast.LENGTH_SHORT).show();
                }

                if(check==0 &&Pw.equals(PwCheck)&&!PwQuestion.equals("")&&!PwAnswer.equals("")&&!userName.equals("")){
                    Intent LogCheck = new Intent(RegisterActivity.this, Register_Result.class);

                    LogCheck.putExtra("Id",Id);
                    LogCheck.putExtra("Pw",Pw);
                    LogCheck.putExtra("PwQuestion",PwQuestion);
                    LogCheck.putExtra("PwAnswer",PwAnswer);
                    LogCheck.putExtra("userName",userName);
                    RegisterActivity.this.startActivity(LogCheck);
                    finish();



                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final ArrayList<String> list = new ArrayList<>();
        list.add("기억에 남는 추억의 장소는?");
        list.add("자신의 보물 제1호는?");
        list.add("인상 깊게 읽은 책 이름은?");
        list.add("내가 좋아하는 캐릭터는?");

        questionSpinner = (Spinner)findViewById(R.id.spinnerQuestion);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,list);
        questionSpinner.setAdapter(adapter);

        questionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RegisterActivity.this,"선택된 아이템 : "+questionSpinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case Register_ResultData:
                if(resultCode == Activity.RESULT_OK){
                //String ID=data.getStringExtra("Id");
                check = data.getIntExtra("Check",1);
                //textId.setText(ID);
            }
                break;
        }

    }
}
