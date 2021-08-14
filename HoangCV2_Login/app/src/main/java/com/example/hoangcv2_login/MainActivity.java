package com.example.hoangcv2_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtResetPass,txtSignUp;
    EditText edtLogin,edtPass;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        cardView.setOnClickListener(this);
        txtResetPass.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
    }
    public void initView(){
        txtResetPass=findViewById(R.id.txtResetPass);
        cardView=findViewById(R.id.cardviewLogin);
        edtLogin=findViewById(R.id.edtLogin);
        edtPass=findViewById(R.id.edtPass);
        txtSignUp=findViewById(R.id.txtSignUp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtResetPass:
                getToResetPassPage();
                break;
            case R.id.cardviewLogin:
                loginUser();
                break;
            case R.id.txtSignUp:
                getToSignUpPage();
                break;
            default:
                break;
        }
    }
    public void loginUser(){
        int i;
        User user=new User();
        List<User > list;
        String username=edtLogin.getText().toString();
        list=UserDatabase.getInstance(this).UserDAO().loginUser(username);
        String password=edtPass.getText().toString();
        for (i=0;i<list.size();i++){
            user= list.get(i);
        }
        if(password.equals(user.getPassword())){
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.setComponent(new ComponentName("com.example.hoangcv2_todo","com.example.hoangcv2_todo.MainActivity"));
            Toast.makeText(this,"Login Successfully!",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }else{
            Toast.makeText(this,"Password not correctly!",Toast.LENGTH_LONG).show();
        }
    }
    public void getToSignUpPage(){
        Intent intent=new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
    public void getToResetPassPage(){
        Intent intent=new Intent(MainActivity.this, ResetPassActivity.class);
        startActivity(intent);
    }

}