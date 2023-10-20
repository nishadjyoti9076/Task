package com.example.task.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.db.MyDatabase;
import com.example.task.models.UserDetails;
import com.example.task.util.Global;

import java.util.List;

public class HomeFragment extends Fragment {
    TextView fname,lname,mobile,email,password;
    ImageView address,id;
    MyDatabase myDatabase;
    Button vediocall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
        findIds(view);

        myDatabase=MyDatabase.getDatabase(getContext());
        BindUserData(myDatabase.getDAO().getUserDtails());

        vediocall.setOnClickListener(view1 -> {

            Intent intent=new Intent(getContext(),VedioCall.class);
            startActivity(intent);

        });

        return view;
    }

    private void findIds(View view){
        fname=view.findViewById(R.id.ed_fname);
        lname=view.findViewById(R.id.ed_lname);
        mobile=view.findViewById(R.id.ed_mobile);
        email=view.findViewById(R.id.ed_email);
        password=view.findViewById(R.id.password);
        address=view.findViewById(R.id.add_proof);
        id=view.findViewById(R.id.id_proof);

        vediocall=view.findViewById(R.id.vediocall);
    }

    private void BindUserData(List<UserDetails> list){

        if (list!=null && list.size()>0){
            fname.setText(list.get(0).getFirst_Name().toString());
            lname.setText(list.get(0).getLast_Name().toString());
            mobile.setText(list.get(0).getMobile_No().toString());
            email.setText(list.get(0).getEmail().toString());
            password.setText(list.get(0).getPassword().toString());
            if (Global.addBitmap !=null) {
                address.setImageBitmap(Global.addBitmap);
            }
            if (Global.idBitmap !=null) {
                id.setImageBitmap(Global.idBitmap);
            }
        }

    }

}