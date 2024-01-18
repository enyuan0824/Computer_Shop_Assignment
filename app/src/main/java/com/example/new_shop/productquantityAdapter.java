package com.example.new_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class productquantityAdapter extends RecyclerView.Adapter<productquantityAdapter.productquantityAdapterHolder> {

    private Context context;

    private List<Product> data;
    private dbmanager dbmanager;

    public productquantityAdapter(Context context,List<Product> data) {
        this.context = context;
        this.data = data;
        this.dbmanager = new dbmanager(context);
    }
    @NonNull
    @Override
    public productquantityAdapter.productquantityAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_quantity,parent,false);
        return new productquantityAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productquantityAdapter.productquantityAdapterHolder holder, int position) {
        Product product = data.get(position);
        holder.name.setText(product.getProductName());
        holder.entry.setText(product.getProductQuantity());
        holder.remaining.setText(product.getQuantityremaining());
        holder.sold.setText(product.getQuantitysold());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class productquantityAdapterHolder extends RecyclerView.ViewHolder {
        TextView name, entry,remaining,sold;
        public productquantityAdapterHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            entry = itemView.findViewById(R.id.entry);
            remaining = itemView.findViewById(R.id.remaining);
            sold = itemView.findViewById(R.id.sold);
        }
    }

}
