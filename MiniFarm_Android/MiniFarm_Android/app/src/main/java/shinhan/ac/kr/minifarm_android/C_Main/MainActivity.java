package shinhan.ac.kr.minifarm_android.C_Main;



import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Fragment.menu1Fragment;
import shinhan.ac.kr.minifarm_android.Z_Fragment.menu2Fragment;
import shinhan.ac.kr.minifarm_android.Z_Fragment.menu3Fragment;
import shinhan.ac.kr.minifarm_android.Z_Fragment.menu4Fragment;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private RelativeLayout mMainFrame;
    private menu1Fragment menu1Fragment;
    private menu2Fragment menu2Fragment;
    private menu3Fragment menu3Fragment;
    private menu4Fragment menu4Fragment;


    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainFrame = (RelativeLayout) findViewById(R.id.main_frame);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        menu1Fragment = new menu1Fragment();
        menu2Fragment = new menu2Fragment();
        menu3Fragment = new menu3Fragment();
        menu4Fragment = new menu4Fragment();

        replaceFragment(menu1Fragment);


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch ((item.getItemId())) {

                    case R.id.navigation_main1:

                        mMainNav.setItemBackgroundResource(R.color.white);
                        replaceFragment(menu1Fragment);
                        return true;

                    case R.id.navigation_main2:
                        mMainNav.setItemBackgroundResource(R.color.white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new menu2Fragment()).commit();
                        return true;

                    case R.id.navigation_main3:
                        mMainNav.setItemBackgroundResource(R.color.white);
                        replaceFragment(menu3Fragment);
                        return true;

                    case R.id.navigation_main4:
                        mMainNav.setItemBackgroundResource(R.color.white);
                        replaceFragment(menu4Fragment);
                        return true;

                    default:
                        return false;
                }
            }
        });


        /*
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar) ;
        setSupportActionBar(tb) ;



        fragmentTransaction.add(R.id.main_frame, menu1Fragment.newInstance()).commit();


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new menu1Fragment()).commit();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new menu2Fragment()).commit();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new menu3Fragment()).commit();

            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new menu4Fragment()).commit();

            }
        });
    }*/
    }


    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();
    }

    public void refresh(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new menu2Fragment()).commit();
    }

    public void refresh1(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new menu1Fragment()).commit();
    }


}
