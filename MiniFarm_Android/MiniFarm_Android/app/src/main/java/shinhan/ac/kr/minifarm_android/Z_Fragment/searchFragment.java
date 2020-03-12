package shinhan.ac.kr.minifarm_android.Z_Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import shinhan.ac.kr.minifarm_android.C_Main.MainActivity;
import shinhan.ac.kr.minifarm_android.R;


public class searchFragment extends Fragment {
    View view;
    Button btnOksearch;
    Button btnCancelsearch;


    public static searchFragment newInstance(){
        return new searchFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        btnOksearch = (Button)view.findViewById(R.id.btnOksearch);
        btnCancelsearch = (Button)view.findViewById(R.id.btnCancelsearch);

        btnOksearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(menu2Fragment.newInstance());
            }
        });

        btnCancelsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().remove(searchFragment.this).commit();
                manager.popBackStack();

                ((MainActivity)getActivity()).replaceFragment(menu1Fragment.newInstance());

            }
        });
        return view;
    }
}