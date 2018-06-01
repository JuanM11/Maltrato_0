package com.example.bolaospc.maltrato_0;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.bolaospc.maltrato_0.adapters.AdapterNinios;
import com.example.bolaospc.maltrato_0.interfaces.DatosApi;
import com.example.bolaospc.maltrato_0.models.Ninios;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViolenciaNiniosFragment extends Fragment {
   private ImageButton bmap1;
    public static final String TAG="Datos Colombia";
    private Retrofit retrofit;
    private AdapterNinios adapterNinios;
    private RecyclerView recyclerView;
    private int ofset;
    private boolean cargar;
    private FragmentActivity myContext;
    public ViolenciaNiniosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_violencia_ninios, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyNinios);
        adapterNinios = new AdapterNinios(getContext());
        recyclerView.setAdapter(adapterNinios);
/*        bmap1= view.findViewById(R.id.ibtnninios);
        bmap1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent=new Intent(myContext,MapsActivity.class);
                miIntent.putExtra("lugar",1);

                startActivity(miIntent);
            }
        });*/

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        Log.i(TAG, "Pandora");
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0)
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();


                    if (cargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, ".");
                            cargar = false;
                            ofset += 1;
                            obtenerDatos();
                        }
                    }

                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cargar = true;
        ofset = 0;

        obtenerDatos();
        return view;


    }

    private void obtenerDatos() {
        DatosApi service = retrofit.create(DatosApi.class);
        Call<ArrayList<Ninios>> reporteRespuestaCall = service.getNinios();

        reporteRespuestaCall.enqueue(new Callback<ArrayList<Ninios>>() {
            @Override
            public void onResponse(Call<ArrayList<Ninios>> call, Response<ArrayList<Ninios>> response) {
                if(response.isSuccessful())
                {
                    ArrayList listado = response.body();
                    adapterNinios.adicionarLista(listado);
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Ninios>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Context activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

}
