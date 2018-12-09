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
import com.ore.vicse.integrador4toclient.models.Pedido;
import com.ore.vicse.integrador4toclient.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.ViewHolder> {

    private FragmentActivity activity;

    private List<Pedido> pedidos;
    private List<Producto> productos;

    public PedidosAdapter(FragmentActivity activity){
        this.activity = activity;
        this.pedidos = new ArrayList<>();
        this.productos = new ArrayList<>();
    }

    public void setPedidos(List<Pedido> pedidos){
        this.pedidos = pedidos;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView productoText;
        public TextView estadoText;

        public ViewHolder(View itemView){
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_imagePedido);
            productoText = itemView.findViewById(R.id.producto_text);
            estadoText = itemView.findViewById(R.id.estado_text);
        }
    }

    @NonNull
    @Override
    public PedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosAdapter.ViewHolder viewHolder, int position) {
            Pedido pedido = this.pedidos.get(position);
            Producto producto = this.productos.get(position);

            viewHolder.productoText.setText(producto.getNombre());
            viewHolder.estadoText.setText(pedido.getEstado());

            String url = producto.getImagen();
            Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.pedidos.size();
    }
}
