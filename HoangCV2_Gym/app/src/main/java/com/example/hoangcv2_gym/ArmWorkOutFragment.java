package com.example.hoangcv2_gym;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.List;


public class ArmWorkOutFragment extends Fragment implements SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    ExerciseAdapter exerciseAdapter;
    Toolbar toolbarArm;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        AppCompatActivity AppCompatActivity = (AppCompatActivity)getActivity();
        AppCompatActivity.setSupportActionBar(toolbarArm);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_arm_work_out, container, false);
    }
    public void initView(View view){
        recyclerView=view.findViewById(R.id.recylerviewArm);
        toolbarArm=view.findViewById(R.id.toolbarArm);
        exerciseAdapter=new ExerciseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }
    public void getData(){
        List<Exercise> list;
        String category="ArmWorkOut";
        list = ExerciseDatabase.getInstance(getContext()).ExerciseDAO().getExercise(category);
        exerciseAdapter.getAll(list);
        recyclerView.setAdapter(exerciseAdapter);
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
    public void searchData(String newText) {
        List<Exercise> list = ExerciseDatabase.getInstance(getContext()).ExerciseDAO().searchExercise(newText);
        exerciseAdapter.getAll(list);
        recyclerView.setAdapter(exerciseAdapter);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}