package shinhan.ac.kr.minifarm_android.A_Loading;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shinhan.ac.kr.minifarm_android.B_Login.LoginActivity;
import shinhan.ac.kr.minifarm_android.R;

public class StartActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                StartActivity.this.startActivity(intent);
                StartActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    }

