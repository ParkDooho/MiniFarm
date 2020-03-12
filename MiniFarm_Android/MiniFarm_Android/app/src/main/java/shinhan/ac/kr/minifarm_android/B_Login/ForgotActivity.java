package shinhan.ac.kr.minifarm_android.B_Login;



import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Fragment.idFragment;
import shinhan.ac.kr.minifarm_android.Z_Fragment.passwordFragment;


public class ForgotActivity extends AppCompatActivity {
    idFragment mIdFragment;
    passwordFragment mPasswordFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mIdFragment= new idFragment();
        mPasswordFragment= new passwordFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, mIdFragment).commit();


        TabLayout tabs=(TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("아이디찾기"));
        tabs.addTab(tabs.newTab().setText("비밀번호찾기"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position=tab.getPosition();
                        Fragment selected = null;
                        if(position==0){
                            selected=mIdFragment;
                        }
                        else if(position==1){
                            selected=mPasswordFragment;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}
