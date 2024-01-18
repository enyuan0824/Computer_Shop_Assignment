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

public class addressAdapter extends RecyclerView.Adapter<addressAdapter.addressAdapterHolder> {

    private Context context;

    private List<Address> data;
    private dbmanager dbmanager;

    public addressAdapter(Context context,List<Address> data) {
        this.context = context;
        this.data = data;
        this.dbmanager = new dbmanager(context);
    }

    @NonNull
    @Override
    public addressAdapter.addressAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_view,parent,false);
        return new addressAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addressAdapterHolder holder, int position) {
        Address address = data.get(position);
        holder._id.setText(String.valueOf(address.getId()));
        holder.fl.setText(address.getFloor());
        holder.bl.setText(address.getBuilding());
        holder.re.setText(address.getRegion());
        holder.edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, edit_address.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",String.valueOf(address.getId()));
                bundle.putString("building",address.getBuilding());
                bundle.putString("floor",address.getFloor());
                bundle.putString("region",address.getRegion());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class addressAdapterHolder extends RecyclerView.ViewHolder {
        TextView fl, bl,re,_id,edit_address;
        //ImageView product_image;
        public addressAdapterHolder(@NonNull View itemView) {
            super(itemView);
            edit_address=itemView.findViewById(R.id.edit);
            _id = itemView.findViewById(R.id.id);
            fl = itemView.findViewById(R.id.product_name);
            bl = itemView.findViewById(R.id.ads2);
            re=itemView.findViewById(R.id.ads3);
        }
    }
}
