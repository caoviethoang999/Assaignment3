package com.example.hoangcv2_gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TimeCountDownActivity extends AppCompatActivity implements View.OnClickListener {
    private long timeCountInMilliSeconds;
    String timepass;
    private enum TimerStatus {
        STARTED,
        STOPPED
    }
    private TimerStatus timerStatus = TimerStatus.STOPPED;
    private ProgressBar progressBarCircle;
    private EditText editTextMinute;
    private TextView textViewTime;
    private ImageView imageViewReset;
    private ImageView imageViewStartStop;
    private CountDownTimer countDownTimer;
    private Toolbar toolbarCountDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_countdown);
        initViews();
        initListeners();
        setSupportActionBar(toolbarCountDown);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViews() {
        toolbarCountDown=findViewById(R.id.toolbarCountDown);
        progressBarCircle = findViewById(R.id.progressBarCircle);
        editTextMinute = findViewById(R.id.editTextMinute);
        textViewTime = findViewById(R.id.textViewTime);
        imageViewReset = findViewById(R.id.imageViewReset);
        imageViewStartStop = findViewById(R.id.imageViewStartStop);
        Intent intent=getIntent();
        timepass=intent.getStringExtra("gettime");
        editTextMinute.setText(timepass);
    }
    private void initListeners() {
        imageViewReset.setOnClickListener(this);
        imageViewStartStop.setOnClickListener(this);
        editTextMinute.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewReset:
                reset();
                break;
            case R.id.imageViewStartStop:
                startStop();
                break;
        }
    }
    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }
    private void startStop() {
        if (timerStatus == TimerStatus.STOPPED) {
            setTimerValues();
            setProgressBarValues();
            imageViewReset.setVisibility(View.VISIBLE);
            imageViewStartStop.setImageResource(R.drawable.icon_stop);
            editTextMinute.setEnabled(false);
            timerStatus = TimerStatus.STARTED;
            startCountDownTimer();

        } else {
            imageViewReset.setVisibility(View.GONE);
            imageViewStartStop.setImageResource(R.drawable.icon_start);
            editTextMinute.setEnabled(true);
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();

        }

    }
    private void setTimerValues() {
        int time = converTime(timepass);
        timeCountInMilliSeconds = time * 60 * 1000;
    }

    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));
                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }
            @Override
            public void onFinish() {
                textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                setProgressBarValues();
                imageViewReset.setVisibility(View.GONE);
                imageViewStartStop.setImageResource(R.drawable.icon_start);
                editTextMinute.setEnabled(true);
                timerStatus = TimerStatus.STOPPED;
            }

        }.start();
        countDownTimer.start();
    }
    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }
    private void setProgressBarValues() {

        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }
    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;
    }
    public int converTime(String time){
        String[] tmp = time.split(":");
        final int tmpHour, tmpMinute, tmpSecond;
        tmpHour = Integer.parseInt(tmp[0]);
        tmpMinute = Integer.parseInt(tmp[1]);
        int minute=tmpHour*60+tmpMinute;
        return minute;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}