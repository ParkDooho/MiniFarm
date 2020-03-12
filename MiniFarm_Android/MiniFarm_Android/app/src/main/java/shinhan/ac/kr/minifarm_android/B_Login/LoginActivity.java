package shinhan.ac.kr.minifarm_android.B_Login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Result_Login;

public class LoginActivity extends AppCompatActivity {

    TextView textRegister;
    Button btnLogin; //로그인
    TextView textFind;
    EditText textid;
    EditText textpassword;
    String ID="";
    String PW="";
    final static int Login_ResultData =0;

    public static Activity LoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textRegister = (TextView)findViewById(R.id.textRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        textFind=(TextView)findViewById(R.id.textFind);
        textid=(EditText)findViewById(R.id.textid);
        textpassword=(EditText)findViewById(R.id.textpassword);
        LoginActivity = LoginActivity.this;



        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
               startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID=textid.getText().toString().trim();
                PW=textpassword.getText().toString().trim();
                Intent LogCheck = new Intent(LoginActivity.this, Result_Login.class);
                LogCheck.putExtra("ID", ID);
                LogCheck.putExtra("PW", PW);
                startActivityForResult(LogCheck, Login_ResultData);
            }
        });

        textFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(LoginActivity.this,ForgotActivity.class);
                startActivity(intent3);
            }
        });
    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case Login_ResultData:
                if(resultCode == Activity.RESULT_OK){
                    ID=data.getStringExtra("ID");
                    PW=data.getStringExtra("PW");
                }
                break;
        }
    }






}
