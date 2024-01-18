package com.example.new_shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class all_orderAdapter extends RecyclerView.Adapter<all_orderAdapter.all_orderAdapterHolder>{

    private Context context;

    private List<ViewOrder> data;
    private dbmanager dbmanager;

    public all_orderAdapter(Context context,List<ViewOrder> data) {
        this.context = context;
        this.data = data;
        this.dbmanager = new dbmanager(context);
    }

    @NonNull
    @Override
    public all_orderAdapter.all_orderAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_view,parent,false);
        return new all_orderAdapterHolder(view);
    }

    public void onBindViewHolder(@NonNull all_orderAdapter.all_orderAdapterHolder holder, int position) {
        ViewOrder order = data.get(position);
        holder.name.setText(String.valueOf(order.getProduct_name()));
        holder.rm.setText(order.getTotal());
        holder.process.setText(order.getProcess());
        dbmanager.open();
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("username", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("key", "Default Value");
                dbmanager.updateOrderprocess(name);
                Intent intent = new Intent(context, Profile.class);
                context.startActivity(intent);
                /*Bundle bundle = new Bundle();
                bundle.putString("product_name",order.getName());
                bundle.putString("total",order.getTotal());
                intent.putExtras(bundle);
                context.startActivity(intent);*/
                //holder.process.setText("Receive");
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class all_orderAdapterHolder extends RecyclerView.ViewHolder {
        TextView name, rm;
        TextView button,process;
        public all_orderAdapterHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.product_name);
            rm=itemView.findViewById(R.id.rm);
            button = itemView.findViewById(R.id.receive);
            process=itemView.findViewById(R.id.Process);
        }
    }
}
