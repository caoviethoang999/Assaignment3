package com.example.hoangcv2_todo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.List;


public class AddListNoteFragment extends Fragment implements View.OnClickListener {
    EditText edtTitle, edtDes;
    Button btnAdd;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recyler, container, false);
    }

    public void initView(View view) {
        edtTitle = view.findViewById(R.id.edttitle);
        edtDes = view.findViewById(R.id.edtdes);
        btnAdd = view.findViewById(R.id.btnAdd);
    }

    public void addData() {
        String title = edtTitle.getText().toString();
        String des = edtDes.getText().toString();
        Todo todo = new Todo(title, des);
        TodoDatabase.getInstance(getContext()).TodoDAO().insertTodo(todo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                addData();
                backToListNote();
                break;
            default:
                break;
        }
    }

    public void backToListNote() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        ListNoteFragment recylerFragment = new ListNoteFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).commit();
    }
}