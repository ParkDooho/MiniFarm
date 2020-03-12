package shinhan.ac.kr.minifarm_android.Z_Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import shinhan.ac.kr.minifarm_android.R;
import shinhan.ac.kr.minifarm_android.Z_Data.Forgot_ID_check;


public class idFragment extends Fragment {
    View view;
    TextView textName;
    Button btnOk;
    Button btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_id,container,false);

        textName=(TextView)view.findViewById(R.id.textName);
        btnOk=(Button)view.findViewById(R.id.btnOk);
        btnCancel=(Button)view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentManager manager = getActivity().getSupportFragmentManager();
                //manager.beginTransaction().remove(idFragment.this).commit();
                //manager.popBackStack();
                getActivity().finish();
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Forgot_ID_check.class);
                intent.putExtra("userName", textName.getText().toString());
                view.getContext().startActivity(intent);
            }
        });



        return view;
    }


    }

