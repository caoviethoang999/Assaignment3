package com.example.hoangcv2_todo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListNoteFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    SearchView searchView;
    FloatingActionButton floatingActionButtonAdd,floatingActionButtonSMS;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        searchView.setOnQueryTextListener(this);
        floatingActionButtonAdd.setOnClickListener(this);
        floatingActionButtonSMS.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fltbtnAdd:
                getToAddListNote();
                break;
            case R.id.fltbtnSendSMS:
                sendSMS();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchData(newText);
        return true;
    }

    public void getToAddListNote() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        AddListNoteFragment recylerFragment = new AddListNoteFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).commit();
    }

    public void initView(View view) {
        recyclerView = view.findViewById(R.id.test);
        searchView = view.findViewById(R.id.searchview);
        floatingActionButtonAdd = view.findViewById(R.id.fltbtnAdd);
        floatingActionButtonSMS=view.findViewById(R.id.fltbtnSendSMS);
        todoAdapter = new TodoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public void searchData(String newText) {
        List<Todo> list = TodoDatabase.getInstance(getContext()).TodoDAO().search(newText);
        todoAdapter.getAll(list);
        recyclerView.setAdapter(todoAdapter);
    }
    public void sendSMS(){
        Uri uri = Uri.parse("smsto:12346556");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "Hello");
        startActivity(it);
    }
    public void loadData() {
        List<Todo> list;
        list = TodoDatabase.getInstance(getContext()).TodoDAO().getListTodo();
        todoAdapter.getAll(list);
        recyclerView.setAdapter(todoAdapter);
    }
}