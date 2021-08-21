package com.example.hoangcv2_todo;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

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


public class EditListNoteFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    EditText edtTitle2,edtDes2,edtTimeNotif2,edtDateNotif2;
    Button btnUpdate2;
    RadioButton rbIsCompleted;
    RadioGroup rdCheckComplete;
    int year, month, day;
    int hour, minute;
    int id;
    Toolbar toolbar3;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        AppCompatActivity AppCompatActivity = (AppCompatActivity)getActivity();
        AppCompatActivity.setSupportActionBar(toolbar3);
        AppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnUpdate2.setOnClickListener(this);
        rdCheckComplete.setOnCheckedChangeListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit_list_note, container, false);
    }

    public void initView(View view) {
        toolbar3=view.findViewById(R.id.toolbar3);
        edtTitle2 = view.findViewById(R.id.edttitle2);
        edtDes2 = view.findViewById(R.id.edtdes2);
        edtTimeNotif2 = view.findViewById(R.id.edttimenotif2);
        edtDateNotif2 = view.findViewById(R.id.edtdatenotif2);
        btnUpdate2 = view.findViewById(R.id.btnUpdate2);
        rbIsCompleted=view.findViewById(R.id.rbIsCompleted1);
        rdCheckComplete=view.findViewById(R.id.rdCheckComplete);
        Bundle bundle = this.getArguments();
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        String time = bundle.getString("time");
        String date = bundle.getString("date");
        String iscompleted = bundle.getString("iscompleted");
        id = bundle.getInt("id");
        edtTitle2.setText(title);
        edtDes2.setText(description);
        edtTimeNotif2.setText(time);
        edtDateNotif2.setText(date);
        rbIsCompleted.setChecked(Boolean.parseBoolean(iscompleted));
    }

    public void updateData() {
        String titleChange = edtTitle2.getText().toString();
        String desChange = edtDes2.getText().toString();
        String timeChange = edtTimeNotif2.getText().toString();
        String dateChange = edtDateNotif2.getText().toString();
        Boolean isComplete;
        if(rbIsCompleted.isChecked()){
            isComplete=true;
        }else{
            isComplete=false;
        }
        TodoDatabase.getInstance(getContext()).TodoDAO().updateTodo(desChange,titleChange,timeChange,dateChange,isComplete, id);
    }

    public void backToListNote() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        ListNoteFragment recylerFragment = new ListNoteFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate2:
                updateData();
                backToListNote();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();
        if(checkedRadioId== R.id.rbIsCompleted1) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
            builder1.setMessage("Have you finished your job");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            rbIsCompleted.setChecked(true);
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            rbIsCompleted.setChecked(false);
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
    public void datePicker(){
        Calendar calender = Calendar.getInstance();
        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtDateNotif2.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
                edtTimeNotif2.setText(""+hourOfDay + ":" + minute);
            }
        },minute,hour,true);
        timePickerDialog.show();
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