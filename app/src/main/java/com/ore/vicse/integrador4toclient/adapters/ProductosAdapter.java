package com.ore.vicse.integrador4toclient.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private List<Producto> productos;

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView nombreText;
        public TextView precioText;

        public ViewHolder(View itemView){
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_imageProducto);
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
    public void onBindViewHolder(@NonNull ProductosAdapter.ViewHolder holder, int position) {

        Producto producto = this.productos.get(position);

        holder.nombreText.setText(producto.getNombre());
        holder.precioText.setText("S/." + producto.getPrecio());

        String url = producto.getImagen();
        Picasso.with(holder.itemView.getContext()).load(url).into(holder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.productos.size();
    }


}
