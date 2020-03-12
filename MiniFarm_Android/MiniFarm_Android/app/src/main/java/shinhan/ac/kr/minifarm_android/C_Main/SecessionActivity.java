package shinhan.ac.kr.minifarm_android.C_Main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Fragment.menu4Fragment;

public class SecessionActivity extends AppCompatActivity {
    TextView textcolor;
    TextView btnEditok2;
    ImageView btnEditcancel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secession);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textcolor = (TextView)findViewById(R.id.textcolor);
        btnEditok2 = (TextView)findViewById(R.id.btnEditok2);
        btnEditcancel2 = (ImageView)findViewById(R.id.btnEditcancel2);

        String content = textcolor.getText().toString();
        SpannableString spannableString = new SpannableString(content);

        String word = "비밀번호 일치 시";
        int start = content.indexOf(word);
        int end = start + word.length();

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        btnEditok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecessionActivity.this,SecessionCheckActivity.class);
                startActivity(intent);
            }
        });


        btnEditcancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
