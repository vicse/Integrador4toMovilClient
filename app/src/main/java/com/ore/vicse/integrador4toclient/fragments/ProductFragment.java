package com.ore.vicse.integrador4toclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.adapters.ProductosAdapter;
import com.ore.vicse.integrador4toclient.adapters.ProveedoresAdapter;
import com.ore.vicse.integrador4toclient.models.Producto;
import com.ore.vicse.integrador4toclient.services.ApiService;
import com.ore.vicse.integrador4toclient.services.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    private static final String TAG = "ProductosFragment";

    private Integer idProveedor;

    private RecyclerView productosList;

    public ProductFragment() {
    }

    public static Fragment newInstance(Integer id){
        Fragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt("idProveedor", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);

        productosList= (RecyclerView) view.findViewById(R.id.recyclerviewProductos);
        productosList.setLayoutManager(new LinearLayoutManager(getContext()));
        productosList.setAdapter(new ProductosAdapter(getActivity()));

        if(getArguments() != null){
            idProveedor = getArguments().getInt("idProveedor");
        }

        initialize();

        return view;
    }

    private void initialize(){

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Producto>> call = service.getProductos(idProveedor);

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                try{
                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code:" + statusCode);

                    if(response.isSuccessful()){

                        List<Producto> productos = response.body();
                        Log.d(TAG, "productos:"+ productos);

                        ProductosAdapter adapter = (ProductosAdapter) productosList.getAdapter();
                        adapter.setProductos(productos);
                        adapter.notifyDataSetChanged();
                    }else{
                        Log.e(TAG, "onError:"+ response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }
                }catch (Throwable t){
                    try{
                        Log.e(TAG, "onThrowable:" +t.toString(), t);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e(TAG, "onFailure:" +t.toString());
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

}
