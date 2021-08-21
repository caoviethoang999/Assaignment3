package com.example.hoangcv2_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtLogin1,edtPass1;
    TextView txtLoginHere1;
    CardView cardViewSignUp;
    RadioButton rbIsAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cardViewSignUp.setOnClickListener(this);
        txtLoginHere1.setOnClickListener(this);
    }
    public void initView(){
        txtLoginHere1=findViewById(R.id.txtLoginHere1);
        cardViewSignUp=findViewById(R.id.cardviewSignUp);
        edtLogin1=findViewById(R.id.edtLogin1);
        edtPass1=findViewById(R.id.edtPass1);
        rbIsAdmin=findViewById(R.id.rbIsCompleted1);
        if(checkAdmin()) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Admin has been created,Ok to proceed");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            rbIsAdmin.setVisibility(View.INVISIBLE);
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
    public void addUser(){
        String username=edtLogin1.getText().toString();
        String password=edtPass1.getText().toString();
        Boolean flag;
        if(rbIsAdmin.isChecked()){
            flag=true;
        }else{
            flag=false;
        }
            User user = new User(username, password, flag);
            UserDatabase.getInstance(this).UserDAO().addUser(user);
    }
    public boolean checkAdmin(){
        Boolean check=true;
        List<User > list;
        list=UserDatabase.getInstance(this).UserDAO().checkAdmin(check);
        if(list.size()<1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.txtLoginHere1:
                backToSignInPage();
                break;
            case R.id.cardviewSignUp:
                addUser();
                backToSignInPage();
                break;
            default:
                break;
        }
    }
    public void backToSignInPage(){
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}