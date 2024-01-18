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
import android.content.SharedPreferences;

public class productAdapter extends RecyclerView.Adapter<productAdapter.productAdapterHolder> {

    private Context context;
    private List<Product> datas;
    private dbmanager dbmanager;

    public productAdapter(Context context,List<Product> datas) {
        this.context = context;
        this.datas = datas;
        this.dbmanager = new dbmanager(context);
    }

    @NonNull
    @Override
    public productAdapter.productAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productentry,parent,false);
        return new productAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapterHolder holder, int position) {
        Product product = datas.get(position);

        int remainingQuantity = Integer.parseInt(product.getQuantityremaining());
        if (remainingQuantity > 0) {
            holder.product_name.setText(product.getProductName());
            holder.product_price.setText(product.getProductPrice());
            holder.details.setText(product.getDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, product_details.class);

                    SharedPreferences sharedPreferences = context.getSharedPreferences("product_info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("product_name", product.getProductName());
                    editor.putString("product_price", product.getProductPrice());
                    editor.putString("product_details", product.getDescription());
                    editor.apply();

                    context.startActivity(intent);
                }
            });
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class productAdapterHolder extends RecyclerView.ViewHolder {
        TextView  product_name, product_price,details;
        public productAdapterHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.textViewProductName);
            product_price = itemView.findViewById(R.id.textViewPrice);
            details=itemView.findViewById(R.id.textViewdetails);
        }
    }
}
