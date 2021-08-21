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
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListNoteFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    FloatingActionButton floatingActionButtonAdd,floatingActionButtonSMS;
    Toolbar toolbar2;
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        AppCompatActivity AppCompatActivity = (AppCompatActivity)getActivity();
        AppCompatActivity.setSupportActionBar(toolbar2);
        AppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        floatingActionButtonAdd.setOnClickListener(this);
        floatingActionButtonSMS.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).addToBackStack(null).commit();
    }

    public void initView(View view) {
        toolbar2=view.findViewById(R.id.toolbar2);
        recyclerView = view.findViewById(R.id.test);
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
    public void loadDataActive() {
        List<Todo> list;
        Boolean check=false;
        list = TodoDatabase.getInstance(getContext()).TodoDAO().searchNotCompleted(check);
        todoAdapter.getAll(list);
        recyclerView.setAdapter(todoAdapter);
    }
    public void loadDataCompleted() {
        List<Todo> list;
        Boolean check=true;
        list = TodoDatabase.getInstance(getContext()).TodoDAO().searchIsCompleted(check);
        todoAdapter.getAll(list);
        recyclerView.setAdapter(todoAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.mnSearch);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.mnFilter:
                PopupMenu popupMenu=new PopupMenu(getContext(),getActivity().findViewById(R.id.mnFilter));
                popupMenu.getMenuInflater().inflate(R.menu.menu_filter, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.all:
                                loadData();
                                break;
                            case R.id.active:
                                loadDataActive();
                                break;
                            case R.id.completed:
                                loadDataCompleted();
                                break;
                        }
                        return  true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}