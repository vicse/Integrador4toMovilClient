package com.ore.vicse.integrador4toclient.services;

import com.ore.vicse.integrador4toclient.models.Cliente;
import com.ore.vicse.integrador4toclient.models.Pedido;
import com.ore.vicse.integrador4toclient.models.Producto;
import com.ore.vicse.integrador4toclient.models.Proveedor;

import java.net.URLDecoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    String API_BASE_URL = "http://integrador4tociclo-vicse.c9users.io";

    @GET("/api/proveedores/")
    Call<List<Proveedor>> getProveedores();

    @FormUrlEncoded
    @POST("/api/clientes/")
    Call<Cliente> createCliente(@Field("nombre") String nombre,
                                @Field("correo") String correo,
                                @Field("dni") String dni,
                                @Field("direccion") String direccion,
                                @Field("password") String password);

    @GET("/api/clientes/{id}/")
    Call<Cliente> showCliente(@Path("id") Integer id);

    @GET("proveedores/{id}/productos")
    Call<List<Producto>> getProductos(@Path("id") Integer id);

    @GET("/cliente/{id}/pedidos")
    Call<List<Pedido>> getPedidos(@Path("id") Integer id);

    @FormUrlEncoded
    @POST("/clientes/login/")
    Call<Cliente> login (@Field("dni") String dni,
                        @Field("password") String password);

}
