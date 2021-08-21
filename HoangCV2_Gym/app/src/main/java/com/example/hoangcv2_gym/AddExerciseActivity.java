package com.example.hoangcv2_gym;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edttitle,edttitleimage,edtdes,edtinst,edtinstimage,edtinstvideo,edtmoredetail,edttime;
    Button btnAdd;
    Spinner spnCategory;
    int hour, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        initView();
        btnAdd.setOnClickListener(this);
        edttime.setOnClickListener(this);
    }
    public void initView(){
        edttitle=findViewById(R.id.edttitle);
        edttitle.setText(getIntent().getStringExtra("data"));
        edttitleimage=findViewById(R.id.edttitleimage);
        edtdes=findViewById(R.id.edtdes);
        edtinst=findViewById(R.id.edtinstruction);
        edtinstimage=findViewById(R.id.edtimageinstruction);
        edtinstvideo=findViewById(R.id.edtvideoinstruction);
        edtmoredetail=findViewById(R.id.edtmoredetail);
        edttime=findViewById(R.id.edttimenotif1);
        spnCategory=findViewById(R.id.spnCategory);
        btnAdd=findViewById(R.id.btnAdd);
    }
    public void addData() {
        String title = edttitle.getText().toString();
        String des = edtdes.getText().toString();
        String time = edttime.getText().toString();
        String instruction = edtinst.getText().toString();
        String instructionimage = edtinstimage.getText().toString();
        String instructionvideo = edtinstvideo.getText().toString();
        String moredetail = edtmoredetail.getText().toString();
        String category = spnCategory.getSelectedItem().toString();
        String titleimage = edttitleimage.getText().toString();
        Exercise exercise = new Exercise(title,titleimage,category,time,des,instruction,instructionimage,instructionvideo,moredetail);
        ExerciseDatabase.getInstance(this).ExerciseDAO().insertExercise(exercise);
    }
    public void timePicker(){
        Calendar calender = Calendar.getInstance();
        minute = calender.get(Calendar.MINUTE);
        hour = calender.get(Calendar.HOUR_OF_DAY);
        TimePickerDialog timePickerDialog=new TimePickerDialog(AddExerciseActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edttime.setText(""+hourOfDay + ":" + minute);
            }
        },minute,hour,true);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                addData();
                break;
            case R.id.edttimenotif1:
                timePicker();
                break;
            default:
                break;
        }
    }
}