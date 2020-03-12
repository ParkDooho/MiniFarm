package shinhan.ac.kr.minifarm_android.Z_Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import shinhan.ac.kr.minifarm_android.R;

public class Listview_qna extends LinearLayout {

    TextView textView00; //제목
    TextView textView11; //날짜

    public Listview_qna(Context context){
        super(context);
        init(context);
    }

    public Listview_qna(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        //inflate
        // system service에서 LayoutInflater를 가져다 사용하겠다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // singer_item xml을 inflate함
        inflater.inflate(R.layout.activity_listview_qna, this, true);

        textView00 = (TextView) findViewById(R.id.textView00); //제목
        textView11 = (TextView) findViewById(R.id.textView11); //날짜
    }

    public void setqTitle(String qTitle) {
        textView00.setText(qTitle);
    }
    public void setqDate(String qDate){
        textView11.setText(qDate);
    }

}
