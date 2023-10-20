package com.example.task.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.db.MyDatabase;
import com.example.task.models.UserDetails;

import java.util.List;
import java.util.Random;

public class LoginFragment extends Fragment {
    EditText otp,mobile,password;
    LinearLayout otp_view;
        LinearLayout login_view;
    ProgressBar progressBar;
    Button login,verify;
    MyDatabase myDatabase;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_login, container, false);
        navController=new NavController(getContext());
        findIds(view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random=new Random();
               int otpValue= random.nextInt(10000);
               otp.setText(otpValue+"");

            }
        },3000);

        verify.setOnClickListener(view1 -> {
            showToast("Verified");
            CheckVisibility();

        });


        login.setOnClickListener(view1 -> {
            Login();
        });

        return view;

    }


    private void findIds(View view){
        otp=view.findViewById(R.id.ed_otp);
        mobile=view.findViewById(R.id.ed_mobile);
        password=view.findViewById(R.id.ed_password);
        otp_view=view.findViewById(R.id.otp_view);
        login_view=view.findViewById(R.id.login_view);
        progressBar=view.findViewById(R.id.progress);
        login=view.findViewById(R.id.btn_login);
        verify=view.findViewById(R.id.btn_verify);

    }

    private void CheckVisibility(){
        progressBar.setVisibility(View.GONE);
        otp_view.setVisibility(View.GONE);
        login_view.setVisibility(View.VISIBLE);
    }

    private void showToast(String message){
        Toast.makeText(getContext(), message+"", Toast.LENGTH_SHORT).show();
    }

    private void Login(){
        if (mobile.getText() !=null && !mobile.getText().toString().equalsIgnoreCase("")){
            if (password.getText() !=null && !password.getText().toString().equalsIgnoreCase("")){
                myDatabase=MyDatabase.getDatabase(getContext());
                List<UserDetails> list=myDatabase.getDAO().getUserDtails();

                if(list !=null && list.size()>0) {
                    if (list.get(0).getMobile_No().equalsIgnoreCase(mobile.getText().toString())
                            && list.get(0).getPassword().equalsIgnoreCase(password.getText().toString())) {

                        showToast("Login success");
                        navController= NavHostFragment.findNavController(this);
                        navController.navigate(R.id.action_loginFragment_to_kycFragment);

                    } else {
                        showToast("Login number and password are invalid");
                    }
                }
            }else {
                showToast("enter password");
            }
        }else {
            showToast("Please Enter mobile no");
        }

    }


}