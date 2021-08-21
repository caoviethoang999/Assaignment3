package com.example.hoangcv2_todo;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;


public class AddListNoteFragment extends Fragment implements View.OnClickListener {
    EditText edtTitle,edtDes,edtTimeNotif,edtDateNotif;
    Button btnAdd;
    int year, month, day;
    int hour, minute;
    boolean isCompleted;
    int notificationId=1;
    final String CHANNEL_ID = "101";
    String time;
    String date;
    Toolbar toolbar1;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        AppCompatActivity AppCompatActivity = (AppCompatActivity)getActivity();
        AppCompatActivity.setSupportActionBar(toolbar1);
        AppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createNotificationChannel();
        btnAdd.setOnClickListener(this);
        edtTimeNotif.setOnClickListener(this);
        edtDateNotif.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    public void initView(View view) {
        toolbar1=view.findViewById(R.id.toolbar1);
       edtTitle = view.findViewById(R.id.edttitle);
       edtDes = view.findViewById(R.id.edtdes);
       edtTimeNotif = view.findViewById(R.id.edttimenotif1);
       edtDateNotif = view.findViewById(R.id.edtdatenotif1);
       btnAdd = view.findViewById(R.id.btnAdd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addData();
                backToListNote();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            Calendar c = Calendar.getInstance();
                            int hour, minute, second;
                            hour = c.get(Calendar.HOUR_OF_DAY);
                            minute = c.get(Calendar.MINUTE);
                            month = c.get(Calendar.MONTH);
                            day = c.get(Calendar.DAY_OF_MONTH);
                            if (hour == getHour(time) && minute == getMinute(time) && month== getMonth(date) && day==getDay(date)) {
                                NotificationCompat.Builder builder =
                                        new NotificationCompat.Builder(getContext(),CHANNEL_ID)
                                                .setSmallIcon(R.drawable.ic_baseline_notifications_none_24)
                                                .setContentTitle("Have You finished your task")
                                                .setContentText("TIME'S UP")
                                                .setColor(Color.RED)
                                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                                .setAutoCancel(true);
                                NotificationManagerCompat notificationManagerCompat =
                                        NotificationManagerCompat.from(getContext());
                                notificationManagerCompat.notify(notificationId, builder.build());
                                break;
                            }
                        }
                    }
                }).start();
                break;
            case R.id.edtdatenotif1:
                datePicker();
                break;
            case R.id.edttimenotif1:
                timePicker();
                break;
            default:
                break;
        }
    }

    public void backToListNote() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        ListNoteFragment recylerFragment = new ListNoteFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).addToBackStack(null).commit();
    }
    public void addData() {
        String title = edtTitle.getText().toString();
        String des = edtDes.getText().toString();
        time=edtTimeNotif.getText().toString();
        date=edtDateNotif.getText().toString();
        isCompleted=false;
        Todo todo = new Todo(title,des,time,date,isCompleted);
        TodoDatabase.getInstance(getContext()).TodoDAO().insertTodo(todo);
    }
    public void datePicker(){
        Calendar calender = Calendar.getInstance();
        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtDateNotif.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    public void timePicker(){
        Calendar calender = Calendar.getInstance();
        minute = calender.get(Calendar.MINUTE);
        hour = calender.get(Calendar.HOUR_OF_DAY);
        TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edtTimeNotif.setText(""+hourOfDay + ":" + minute);
            }
        },minute,hour,true);
        timePickerDialog.show();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("This is notification channel");
            NotificationManager manager = (NotificationManager)getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    public int getHour(String time){
        String[] tmp = time.split(":");
        final int tmpHour, tmpMinute, tmpSecond;
        tmpHour = Integer.parseInt(tmp[0]);
        tmpMinute = Integer.parseInt(tmp[1]);
        return tmpHour;
    }
    public int getMinute(String time){
        String[] tmp = time.split(":");
        final int tmpHour, tmpMinute;
        tmpHour = Integer.parseInt(tmp[0]);
        tmpMinute = Integer.parseInt(tmp[1]);
        return tmpMinute;
    }
    public int getDay(String time){
        String[] tmp = time.split("/");
        final int tmpDay, tmpMonth, tmpYear;
        tmpDay = Integer.parseInt(tmp[0]);
        tmpMonth = Integer.parseInt(tmp[1])-1;
        tmpYear= Integer.parseInt(tmp[2]);
        return tmpDay;
    }
    public int getMonth(String time){
        String[] tmp = time.split("/");
        final int tmpDay, tmpMonth, tmpYear;
        tmpDay = Integer.parseInt(tmp[0]);
        tmpMonth = Integer.parseInt(tmp[1])-1;
        tmpYear= Integer.parseInt(tmp[2]);
        return tmpMonth;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}