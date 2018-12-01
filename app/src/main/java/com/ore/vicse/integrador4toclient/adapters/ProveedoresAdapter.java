package com.ore.vicse.integrador4toclient.adapters;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.fragments.ProductFragment;
import com.ore.vicse.integrador4toclient.models.Proveedor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProveedoresAdapter extends RecyclerView.Adapter<ProveedoresAdapter.ViewHolder> {

    private FragmentActivity activity;

    private List<Proveedor> proveedores;

    public void setProveedores(List<Proveedor> proveedores){
        this.proveedores = proveedores;
    }

    public ProveedoresAdapter(FragmentActivity activity){
        this.activity = activity;
        this.proveedores = new ArrayList<>();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView correoText;
        public TextView empresaText;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = (ImageView) itemView.findViewById(R.id.foto_image);
            empresaText = (TextView) itemView.findViewById(R.id.empresa_text);
            correoText = (TextView) itemView.findViewById(R.id.correo_text);
        }
    }

    @Override
    public ProveedoresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProveedoresAdapter.ViewHolder viewHolder, int position) {

        final Proveedor proveedor = this.proveedores.get(position);

        String url = proveedor.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

        viewHolder.empresaText.setText(proveedor.getEmpresa());

        viewHolder.correoText.setText(proveedor.getCorreo());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proveedor.getId_proveedor() != null){
                    Fragment fragment = ProductFragment.newInstance(proveedor.getId_proveedor());
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("tag").commit();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return this.proveedores.size();
    }

}
