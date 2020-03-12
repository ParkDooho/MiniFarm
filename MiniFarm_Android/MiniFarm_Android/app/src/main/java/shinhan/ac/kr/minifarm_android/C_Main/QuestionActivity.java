package shinhan.ac.kr.minifarm_android.C_Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Question_create;
import shinhan.ac.kr.minifarm_android.Z_Network.UserData;

public class QuestionActivity extends AppCompatActivity {
    EditText edit_qname; //문의제목
    EditText edit_qcontent; //문의내용


    TextView btn_question;
    TextView btn_qback; // 뒤로가기

    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        edit_qname=(EditText)findViewById(R.id.edit_qname);
        edit_qcontent=(EditText)findViewById(R.id.edit_qcontent);
        btn_question=(TextView)findViewById(R.id.btn_question);
        btn_qback=(TextView)findViewById(R.id.btn_qback);

        String qname=edit_qname.getText().toString().trim();
        String qcontent=edit_qcontent.getText().toString().trim();





        ID= UserData.getUserId();
        Toast.makeText(getApplicationContext(),ID, Toast.LENGTH_SHORT).show();

        btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_qname.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(),"제목을 입력해 주세요.",Toast.LENGTH_SHORT).show();
                else if(edit_qcontent.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                else {
                    Intent LogCheck = new Intent(QuestionActivity.this, Question_create.class);
                    LogCheck.putExtra("qTitle",edit_qname.getText().toString());
                    LogCheck.putExtra("qQuestion",edit_qcontent.getText().toString());
                    //Toast.makeText(getApplicationContext(), qname, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), qcontent, Toast.LENGTH_SHORT).show();
                    QuestionActivity.this.startActivity(LogCheck);
                    finish();
                }
            }
        });


        btn_qback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
}
