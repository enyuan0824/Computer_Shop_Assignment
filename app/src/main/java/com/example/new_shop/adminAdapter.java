package com.example.new_shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adminAdapter extends RecyclerView.Adapter<adminAdapter.adminAdapterHolder> {

    private Context context;
    private List<admin_product> ap;
    private dbmanager dbmanager;
    //private ArrayList product_image, product_name, product_price;

    public adminAdapter(Context context,List<admin_product> product) {
        this.context = context;
        this.ap = product;
        this.dbmanager = new dbmanager(context);
    }

    @NonNull
    @Override
    public adminAdapter.adminAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_layout_product, parent, false);
        return new adminAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adminAdapterHolder holder, int position) {
        //holder.product_image.setImageURI(Uri.parse(String.valueOf(product_image.get(position))));
        admin_product product = ap.get(position);
        holder._id.setText(String.valueOf(product.getId()));
        holder.product_name.setText(product.getProductName());
        holder.product_price.setText(product.getProductPrice());
        holder.details.setText(product.getDescription());

        //holder.product_price.setText(String.valueOf(product_price.get(position)));
        //edit button to admin_edit_page
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the product information from the clicked item
                Intent intent = new Intent(context, admin_edit_product.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(product.getId()));
                bundle.putString("name",product.getProductName());
                bundle.putString("price",product.getProductPrice());
                bundle.putString("details",product.getDescription());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btndetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productNameToDelete = product.getProductName();
                dbmanager.open();
                boolean isProductRecordDeleted = dbmanager.deleteProductByName(productNameToDelete);

                if (isProductRecordDeleted) {
                    Toast.makeText(context, "Delete Product Successful", Toast.LENGTH_SHORT).show();
                    boolean isOrderRecordDeleted=dbmanager.deleteOrderbyproductname(productNameToDelete);
                    if (isOrderRecordDeleted){
                        Toast.makeText(context, "Delete Order Successful", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Not This Product Order", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(context, admin_add_product_page.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Failed to delete the product", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ap.size();
    }

    public class adminAdapterHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price,details,_id;
        Button btnEdit,btndetele;
        //ImageView product_image;
        public adminAdapterHolder(@NonNull View itemView) {
            super(itemView);

            //product_image = itemView.findViewById(R.id.imageView9);
            _id = itemView.findViewById(R.id.id);
            details = itemView.findViewById(R.id.textViewDetails);
            product_name = itemView.findViewById(R.id.textViewProductName);
            product_price = itemView.findViewById(R.id.textViewPrice);
            btnEdit = itemView.findViewById(R.id.buttonAdminEdit);
            btndetele=itemView.findViewById(R.id.buttonAdminDelete);

        }
    }
}
