package com.ore.vicse.integrador4toclient.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private FragmentActivity activity;

    private List<Producto> productos;

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ProductosAdapter(FragmentActivity activity){
        this.activity = activity;
        this.productos = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImageProducto;
        public TextView nombreText;
        public TextView precioText;

        public ViewHolder(View itemView){
            super(itemView);
            fotoImageProducto = itemView.findViewById(R.id.foto_imageProducto);
            nombreText = itemView.findViewById(R.id.nombre_text);
            precioText = itemView.findViewById(R.id.precio_text);

        }
    }

    @NonNull
    @Override
    public ProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductosAdapter.ViewHolder viewHolder, int position) {

        Producto producto = this.productos.get(position);

        viewHolder.nombreText.setText(producto.getNombre());
        viewHolder.precioText.setText("S/." + producto.getPrecio());

        final String url = producto.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load("http://integrador4tociclo-vicse.c9users.io"+url).into(viewHolder.fotoImageProducto);

    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }


}
