package com.example.hoangcv2_gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class ExerciseDetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtExerciseTitle,txtdes1,txtinstruction1,txtmoredetail1,txtTimeExercise;
    String time,videoInstruction,imgInstruction,moredetail;
    WebView videoInstr;
    ImageView imgCountDown,imgInstr;
    Toolbar toolbarDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        initView();
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgCountDown.setOnClickListener(this);
        txtmoredetail1.setOnClickListener(this);
    }
    public void initView(){
        txtExerciseTitle=findViewById(R.id.txtTitleExercise1);
        txtTimeExercise=findViewById(R.id.txttimeexercise);
        txtdes1=findViewById(R.id.txtdes1);
        txtinstruction1=findViewById(R.id.txtinstruction1);
        txtmoredetail1=findViewById(R.id.txtmoredetail1);
        videoInstr=findViewById(R.id.videoinst);
        imgCountDown=findViewById(R.id.imgCountDown);
        imgInstr=findViewById(R.id.imginst);
        toolbarDetail=findViewById(R.id.toolbarDetail);
        Intent intent=getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        time = intent.getStringExtra("time");
        imgInstruction = intent.getStringExtra("imageInstruction");
        videoInstruction = intent.getStringExtra("videoInstruction");
        moredetail = intent.getStringExtra("moreDetail");
        String instruction = intent.getStringExtra("instruction");
        txtExerciseTitle.setText(title);
        txtdes1.setText(description);
        txtinstruction1.setText(instruction);
        txtmoredetail1.setText(moredetail);
        txtTimeExercise.setText("TIME TO EXERCISE:"+time);
        Bitmap photo= null;
        try {
            photo = new ExerciseDetailActivity.ImageRequestAsk().execute(imgInstruction).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        imgInstr.setImageBitmap(photo);
        videoViewHandler();
    }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgCountDown:
                startExcersise();
                break;
            case R.id.txtmoredetail1:
                moreDetailHandler();
                break;
            default:break;
        }
    }
    public void moreDetailHandler(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(moredetail));
        startActivity(i);
    }
    public void startExcersise(){
        Intent intent=new Intent(ExerciseDetailActivity.this,TimeCountDownActivity.class);
        intent.putExtra("gettime",time);
        startActivity(intent);
    }
    public void videoViewHandler(){
        videoInstr.setWebViewClient(new WebViewClient());
        videoInstr.getSettings().setLoadsImagesAutomatically(true);
        videoInstr.getSettings().setJavaScriptEnabled(true);
        videoInstr.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        videoInstr.loadUrl(videoInstruction);
    }
    public class ImageRequestAsk extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                InputStream inputStream =new java.net.URL(params[0]).openStream();
                return BitmapFactory.decodeStream(inputStream);
            }catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
}