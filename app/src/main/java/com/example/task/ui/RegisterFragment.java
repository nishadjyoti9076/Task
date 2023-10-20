package com.example.task.ui;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.db.MyDatabase;
import com.example.task.models.UserDetails;


public class RegisterFragment extends Fragment {
    EditText fname,lname,mobile,email,password;
    Button register;
    MyDatabase myDatabase;
    NavController navController;
    RelativeLayout container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_register, container, false);
        findIds(view);
        navController=new NavController(getContext());

        register.setOnClickListener(view1 -> {

            if (Validate()){
                myDatabase=MyDatabase.getDatabase(getContext());
                myDatabase.getDAO().Delete();
                myDatabase.getDAO().insertUser(InsertUser());

              navController= NavHostFragment.findNavController(this);
               navController.navigate(R.id.action_registerFragment_to_loginFragment);

            }
        });

        return view;
    }

    private void findIds(View view){
        fname=view.findViewById(R.id.ed_fname);
        lname=view.findViewById(R.id.ed_lname);
        mobile=view.findViewById(R.id.ed_mobile);
        email=view.findViewById(R.id.ed_email);
        password=view.findViewById(R.id.ed_password);
        register=view.findViewById(R.id.btn_apply);
        container=view.findViewById(R.id.container);
    }


    private Boolean Validate(){

        if (fname.getText() == null || fname.getText().toString().equalsIgnoreCase(""))
        {
            showToast("Enter your first name");
            return false;
        } else  if (lname.getText() == null || lname.getText().toString().equalsIgnoreCase(""))
        {
            showToast("Enter your last name");
            return false;
        }else  if (mobile.getText() == null || mobile.getText().toString().equalsIgnoreCase("") || mobile.length()>10)
        {
            showToast("Enter valid mobile no");
            return false;
        }else  if (email.getText() == null || email.getText().toString().equalsIgnoreCase(""))
        {
            showToast("Enter valid email");
            return false;
        }else  if (password.getText() == null || password.getText().toString().equalsIgnoreCase(""))
        {
            showToast("Enter password");
            return false;
        }else {
            return true;
        }
    }

    private UserDetails InsertUser(){
        return new UserDetails(
        (fname.getText().toString()),
        (lname.getText().toString()),
        (mobile.getText().toString()),
        (email.getText().toString()),
        (password.getText().toString()));

    }

    private void showToast(String message){
        Toast.makeText(getContext(), message+"", Toast.LENGTH_SHORT).show();
    }
}