package com.example.new_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.orderAdapterHolder>{
    private Context context;
    private List<ViewOrder> data;
    private dbmanager dbManager;


    public orderAdapter(Context context,List<ViewOrder> vOrder) {
        this.context = context;
        this.data = vOrder;
        this.dbManager = new dbmanager(context);
    }

    @NonNull
    @Override
    public orderAdapter.orderAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin__layout_current_order,parent,false);
        return new orderAdapter.orderAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderAdapter.orderAdapterHolder holder, int position) {
        ViewOrder vOrder = data.get(position);
        holder.id.setText(String.valueOf(vOrder.getId()));
        holder.name.setText(vOrder.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, admin_view_order_details.class);
                intent.putExtra("order_id", vOrder.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class orderAdapterHolder extends RecyclerView.ViewHolder {
        TextView id, name;
        Button view;
        public orderAdapterHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.username);
            view = itemView.findViewById(R.id.buttonAdminViewOrder);
        }
    }
}
