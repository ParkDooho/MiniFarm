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
import android.widget.TextView;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.R;

public class Dialog_logout extends Dialog {
    RelativeLayout textlogout;

    public Dialog_logout(Context context){
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_dialog_logout);
        textlogout=(RelativeLayout) findViewById(R.id.textlogout);

        textlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext()/*현재 액티비티 위치*/ , LoginActivity.class/*이동 액티비티 위치*/);
                getContext().startActivity(i);
                dismiss();
            }
        });



    }
}
