package com.example.hoangcv2_gym;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ItemViewHolder> {
    List<Exercise> list;

    public ExerciseAdapter() {
        list = new ArrayList<>();
    }

    public void getAll(List<Exercise> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ExerciseAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_exercise, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ItemViewHolder holder, int position) {
        Exercise exercise=list.get(position);
        holder.txttitle1.setText(exercise.getTitle());
        Bitmap photo= null;
        try {
            photo = new ExerciseAdapter.ImageRequestAsk().execute(exercise.getImageTitle()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        holder.imgExercise.setImageBitmap(photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), ExerciseDetailActivity.class);
                intent.putExtra("id",String.valueOf(exercise.getId()));
                intent.putExtra("title", exercise.getTitle());
                intent.putExtra("description", exercise.getDescription());
                intent.putExtra("time", exercise.getTime());
                intent.putExtra("instruction", exercise.getInstruction());
                intent.putExtra("imageInstruction", exercise.getImageInstruction());
                intent.putExtra("videoInstruction", exercise.getVideoInstruction());
                intent.putExtra("moreDetail", exercise.getMoreDetail());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list !=null){
            return list.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txttitle1;
        ImageView imgExercise;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle1=itemView.findViewById(R.id.txtTitleExercise);
            imgExercise=itemView.findViewById(R.id.imgexercise);
        }
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
