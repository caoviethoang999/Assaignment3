package com.example.hoangcv2_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtLogin1,edtPass1;
    TextView txtLoginHere1;
    CardView cardViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        cardViewSignUp.setOnClickListener(this);
        txtLoginHere1.setOnClickListener(this);
    }
    public void initView(){
        txtLoginHere1=findViewById(R.id.txtLoginHere1);
        cardViewSignUp=findViewById(R.id.cardviewSignUp);
        edtLogin1=findViewById(R.id.edtLogin1);
        edtPass1=findViewById(R.id.edtPass1);
    }
    public void addUser(){
        String username=edtLogin1.getText().toString();
        String password=edtPass1.getText().toString();
        User user=new User(username,password);
        UserDatabase.getInstance(this).UserDAO().addUser(user);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtLoginHere1:
                backToSignInPage();
                break;
            case R.id.cardviewSignUp:
                addUser();
                break;
            default:
                break;
        }
    }
    public void backToSignInPage(){
        Intent intent=new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}