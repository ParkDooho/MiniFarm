package shinhan.ac.kr.minifarm_android.Z_Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import shinhan.ac.kr.minifarm_android.C_Main.CheckmyqActivity;
import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Question_delete;

public class Dialog_Qdelete extends Dialog {
    RelativeLayout textdelete;
    String qNumber; //번호

    public Dialog_Qdelete(Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                setContentView(R.layout.activity_dialog__qdelete);
                //qNumber= ((CheckmyqActivity)getOwnerActivity()).getQNumber();
                textdelete=(RelativeLayout)findViewById(R.id.textdelete);
                textdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), Question_delete.class);
                        getContext().startActivity(intent);
                        ((CheckmyqActivity)CheckmyqActivity.mContext).Finish();
                        dismiss();


            }
        });




    }



}
