package shinhan.ac.kr.minifarm_android.C_Main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Question_delete;
import shinhan.ac.kr.minifarm_android.Z_Dialog.Dialog_Qdelete;

public class CheckmyqActivity extends AppCompatActivity {
    TextView textqTitle; //제목
    TextView textqQuestion; //내용
    TextView textqAnswer; //답변
    Button Qok; //완료
    ImageView question_delete; //수정 삭제 나오는 메뉴
    String qNumber;//삭제할때 넘길것
    Dialog_Qdelete cd;


    public static Context mContext;/////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkmyq);

        mContext = this;/////

        question_delete = (ImageView) findViewById(R.id.question_delete);
        textqTitle = (TextView) findViewById(R.id.textqTitle);
        textqQuestion = (TextView) findViewById(R.id.textqQuestion);
        textqAnswer = (TextView) findViewById(R.id.textqAnswer);
        Qok=(Button)findViewById(R.id.Qok);

        Intent intent = getIntent();

        textqTitle.setText(intent.getStringExtra("qTitle"));
        textqQuestion.setText(intent.getStringExtra("qQuestion")); //qQuestion이 아이디가 나옴 ㅠㅠ
        textqAnswer.setText(intent.getStringExtra("aAnswer"));
        qNumber=intent.getStringExtra("qNumber");

        DisplayMetrics dm = getApplication().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
        int width = dm.widthPixels; //디바이스 화면 너비
        int height = dm.heightPixels; //디바이스 화면 높이

        cd=new Dialog_Qdelete(this);

        question_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(CheckmyqActivity.this, Dialog_Qdelete.class);
                //intent.putExtra("qNumber",qNumber);
                //CheckmyqActivity.this.startActivity(intent);
                //finish();

                cd.show();
            }
        });

        Qok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public  String getQNumber(){
            return qNumber;
    }

    public void Finish(){
        finish();
    }

}
