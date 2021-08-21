package com.example.hoangcv2_todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
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
        holder.txtTimeNotif.setText(todo.getTime());
        holder.txtDateNotif.setText(todo.getDate());
        if (todo.getFinished()){
            holder.rbIsCompleted.setChecked(true);
        }else{
            holder.rbIsCompleted.setChecked(false);
        }
        holder.rbIsCompleted.setEnabled(false);
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setMessage("Are you sure want to delete");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                TodoDatabase.getInstance(holder.itemView.getContext()).TodoDAO().delete(todo);
                                Toast.makeText(holder.itemView.getContext(),"Deleted",Toast.LENGTH_LONG).show();
                                list.remove(position);
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
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
                bundle.putString("time", todo.getTime());
                bundle.putString("date", todo.getDate());
                bundle.putString("iscompleted", String.valueOf(todo.getFinished()));
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
        TextView txttitle1,txtdes1,txtTimeNotif,txtDateNotif;
        Button btndel,btnupdate;
        RadioButton rbIsCompleted;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle1=itemView.findViewById(R.id.txttitle1);
            txtdes1=itemView.findViewById(R.id.txtdes1);
            txtTimeNotif=itemView.findViewById(R.id.txttimenotif);
            txtDateNotif=itemView.findViewById(R.id.txtdatenotif);
            rbIsCompleted=itemView.findViewById(R.id.rbIsCompleted);
            btndel=itemView.findViewById(R.id.btndel);
            btnupdate=itemView.findViewById(R.id.btnupdate);
        }
    }
}
