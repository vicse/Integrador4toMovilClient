package com.ore.vicse.integrador4toclient.services;

import com.ore.vicse.integrador4toclient.models.Proveedor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    String API_BASE_URL = "http://integrador4tociclo-vicse.c9users.io";

    @GET("/api/proveedores/")
    Call<List<Proveedor>> getProveedores();

}
