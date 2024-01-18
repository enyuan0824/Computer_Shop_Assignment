package com.example.new_shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class addressviewadapter extends RecyclerView.Adapter<addressviewadapter.addressviewadapterHolder> {

    private Context context;
    private List<Address> datas;
    private dbmanager dbmanager;

    public addressviewadapter(Context context,List<Address> datas) {
        this.context = context;
        this.datas = datas;
        this.dbmanager = new dbmanager(context);
    }

    @NonNull
    @Override
    public addressviewadapter.addressviewadapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_view2,parent,false);
        return new addressviewadapterHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull addressviewadapterHolder holder, int position) {
        //holder.product_image.setImageURI(Uri.parse(String.valueOf(product_image.get(position))));
        Address address = datas.get(position);
        //holder.product_name.setText(product.getProductName());
        holder.ads1.setText(address.getFloor());
        holder.ads2.setText(address.getBuilding());
        holder.ads3.setText(address.getRegion());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Check_out.class);
                Bundle bundle = new Bundle();
                bundle.putString("floor",address.getFloor());
                bundle.putString("building",address.getBuilding());
                bundle.putString("region",address.getRegion());
                intent.putExtras(bundle);

                ((AppCompatActivity)context).setResult(Activity.RESULT_OK,intent);
                ((AppCompatActivity)context).finish();
            }
        });
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class addressviewadapterHolder extends RecyclerView.ViewHolder {
        TextView ads1, ads2,ads3;
        //ImageView product_image;
        public addressviewadapterHolder(@NonNull View itemView) {
            super(itemView);

            //product_image = itemView.findViewById(R.id.imageView9);
            ads1 = itemView.findViewById(R.id.product_name);
            ads2 = itemView.findViewById(R.id.ads2);
            ads3=itemView.findViewById(R.id.ads3);
        }
    }

}
