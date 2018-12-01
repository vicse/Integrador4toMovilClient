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
import com.ore.vicse.integrador4toclient.adapters.ProveedoresAdapter;
import com.ore.vicse.integrador4toclient.services.ApiService;
import com.ore.vicse.integrador4toclient.services.ApiServiceGenerator;
import com.ore.vicse.integrador4toclient.models.Proveedor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProviderFragment extends Fragment {

    private static final String TAG = ProviderFragment.class.getSimpleName();

    private RecyclerView proveedoresList;

    public ProviderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_provider, container, false);

        proveedoresList = (RecyclerView) view.findViewById(R.id.recyclerview);
        proveedoresList.setLayoutManager(new LinearLayoutManager(getContext()));

        proveedoresList.setAdapter(new ProveedoresAdapter(getActivity()));
        initialize();

        return view;
    }

    private void initialize(){

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Proveedor>> call = service.getProveedores();

        call.enqueue(new Callback<List<Proveedor>>() {
            @Override
            public void onResponse(Call<List<Proveedor>> call, Response<List<Proveedor>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Proveedor> proveedores = response.body();
                        Log.d(TAG, "Proveedores: " + proveedores);

                        ProveedoresAdapter adapter = (ProveedoresAdapter) proveedoresList.getAdapter();
                        adapter.setProveedores(proveedores);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Proveedor>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
