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
import com.ore.vicse.integrador4toclient.adapters.PedidosAdapter;
import com.ore.vicse.integrador4toclient.models.Pedido;
import com.ore.vicse.integrador4toclient.services.ApiService;
import com.ore.vicse.integrador4toclient.services.ApiServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoFragment extends Fragment {

    private static final String TAG = PedidoFragment.class.getSimpleName();

    private RecyclerView pedidosList;
    private Integer idCliente;


    public PedidoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idCliente = getArguments().getInt("idCli");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pedido, container, false);

        pedidosList = (RecyclerView) view.findViewById(R.id.recyclerviewPedidos);
        pedidosList.setLayoutManager(new LinearLayoutManager(getContext()));
        pedidosList.setAdapter(new PedidosAdapter(getActivity()));

        initialize();

        return view;
    }

    private void initialize(){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Pedido>> call = service.getPedidos(idCliente);
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {
                        List<Pedido> pedidos = response.body();
                        Log.d(TAG, "pedidos: " + pedidos);

                        PedidosAdapter adapter = (PedidosAdapter) pedidosList.getAdapter();
                        adapter.setPedidos(pedidos);
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");

                    }
                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                    } catch (Throwable x) {}
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

}
