package com.ore.vicse.integrador4toclient.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.models.Proveedor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProveedoresAdapter extends RecyclerView.Adapter<ProveedoresAdapter.ViewHolder>{

    private List<Proveedor> proveedores;

    public ProveedoresAdapter(){
        this.proveedores = new ArrayList<>();
    }

    public void setProveedores(List<Proveedor> proveedores){
        this.proveedores = proveedores;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView empresaText;
        public TextView correoText;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_image);
            empresaText = itemView.findViewById(R.id.empresa_text);
            correoText = itemView.findViewById(R.id.correo_text);
        }
    }

    @Override
    public ProveedoresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProveedoresAdapter.ViewHolder viewHolder, int position) {

        Proveedor proveedor = this.proveedores.get(position);

        viewHolder.empresaText.setText(proveedor.getEmpresa());
        viewHolder.correoText.setText(proveedor.getCorreo());

        String url = proveedor.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.proveedores.size();
    }

}
