package com.ore.vicse.integrador4toclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.adapters.ProveedoresAdapter;
import com.ore.vicse.integrador4toclient.models.Producto;
import com.ore.vicse.integrador4toclient.services.ApiService;
import com.ore.vicse.integrador4toclient.services.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;

public class ProductFragment extends Fragment {

    private static final String TAG = "ProductosFragment";

    private RecyclerView productosList;

    public ProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);

        productosList= (RecyclerView) view.findViewById(R.id.recyclerviewProductos);
        productosList.setLayoutManager(new LinearLayoutManager(getContext()));
        productosList.setAdapter(new ProveedoresAdapter());
        initialize();

        return view;
    }

    private void initialize(){

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        //Call<List<Producto>> call = service.getProductos();
    }

}
