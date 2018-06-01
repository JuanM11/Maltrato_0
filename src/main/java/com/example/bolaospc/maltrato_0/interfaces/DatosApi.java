package com.example.bolaospc.maltrato_0.interfaces;

import com.example.bolaospc.maltrato_0.models.Ninios;
import com.example.bolaospc.maltrato_0.models.Pareja;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DatosApi {
    @GET("kdgc-bwai.json")
    Call<ArrayList<Ninios>> getNinios();

    @GET("uc7j-idgs.json")
    Call<ArrayList<Pareja>> obtenerDatosPareja();



}
