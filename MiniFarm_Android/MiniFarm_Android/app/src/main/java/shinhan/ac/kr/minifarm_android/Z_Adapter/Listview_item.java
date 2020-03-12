package shinhan.ac.kr.minifarm_android.Z_Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.R;

public class Listview_item extends LinearLayout {

    TextView textView0; //작물이름
    TextView textView1; //온도
    TextView textView2; //습도
    TextView textView3; //조도
    TextView textView4; //농도

    public Listview_item(Context context){
        super(context);
        init(context);
    }

    public Listview_item(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        //inflate
        // system service에서 LayoutInflater를 가져다 사용하겠다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // singer_item xml을 inflate함
        inflater.inflate(R.layout.activity_listview_item, this, true);

        textView0 = (TextView) findViewById(R.id.textView0); //작물이름
        textView1 = (TextView) findViewById(R.id.textView1); //온도
        textView2 = (TextView) findViewById(R.id.textView2); //습도
        textView3 = (TextView) findViewById(R.id.textView3); //조도
        textView4 = (TextView) findViewById(R.id.textView4); //농도

    }

    public void setSearchName(String searchName) {
        textView0.setText(searchName);
    }
    public void setSearchTem(String searchTem){
        textView1.setText(searchTem);
    }

    public void setSearchHum(String searchHum){
        textView2.setText(searchHum);
    }

    public void setSearchIllu(String searchIllu){
        textView3.setText(searchIllu);
    }

    public void setSearchPH(String searchPH){
        textView4.setText(searchPH);
    }


}
