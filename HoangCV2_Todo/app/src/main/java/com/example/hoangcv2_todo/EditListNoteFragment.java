package com.example.hoangcv2_todo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class EditListNoteFragment extends Fragment implements View.OnClickListener {
    EditText edtTitle2, edtDes2;
    Button btnUpdate2;
    int id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        btnUpdate2.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    public void initView(View view) {
        edtTitle2 = view.findViewById(R.id.edttitle2);
        edtDes2 = view.findViewById(R.id.edtdes2);
        btnUpdate2 = view.findViewById(R.id.btnUpdate2);
        Bundle bundle = this.getArguments();
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        id = bundle.getInt("id");
        edtTitle2.setText(title);
        edtDes2.setText(description);
    }

    public void updateData() {
        String titleChange = edtTitle2.getText().toString();
        String desChange = edtDes2.getText().toString();
        TodoDatabase.getInstance(getContext()).TodoDAO().updateTodo(desChange, titleChange, id);
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
}