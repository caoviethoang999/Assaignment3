package com.example.hoangcv2_todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ItemViewHolder> {
    List<Todo> list;

    public TodoAdapter() {
        list = new ArrayList<>();
    }

    public void getAll(List<Todo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TodoAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_view, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ItemViewHolder holder, int position) {
        Todo todo=list.get(position);
        holder.txttitle1.setText(todo.getTitle());
        holder.txtdes1.setText(todo.getDescription());
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoDatabase.getInstance(holder.itemView.getContext()).TodoDAO().delete(todo);
                Toast.makeText(holder.itemView.getContext(),"deleted",Toast.LENGTH_LONG).show();
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                EditListNoteFragment miscellaneousfragment = new EditListNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id",todo.getId());
                bundle.putString("title", todo.getTitle());
                bundle.putString("description", todo.getDescription());
                miscellaneousfragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, miscellaneousfragment).addToBackStack(null).commit();
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
        TextView txttitle1,txtdes1;
        Button btndel,btnupdate;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle1=itemView.findViewById(R.id.txttitle1);
            txtdes1=itemView.findViewById(R.id.txtdes1);
            btndel=itemView.findViewById(R.id.btndel);
            btnupdate=itemView.findViewById(R.id.btnupdate);
        }
    }
}
