package com.example.bolaospc.maltrato_0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.example.bolaospc.maltrato_0.adapters.ParejaAdapter;

import com.example.bolaospc.maltrato_0.interfaces.DatosApi;
import com.example.bolaospc.maltrato_0.models.Pareja;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParejaFragment extends Fragment {
private ImageButton bmap1;
    public static final String TAG="Datos Colombia";
    private Retrofit retrofit;
    private ParejaAdapter adapterPareja;
    private RecyclerView recyclerView;
    private int ofset;
    private boolean cargar;
    private FragmentActivity myContext;
    public ParejaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pareja, container, false);
/*        bmap1=(ImageButton)view.findViewById(R.id.ibtnParejas);

        bmap1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent=new Intent(myContext,MapsActivity.class);
                startActivity(miIntent);
            }
        });*/
        recyclerView = (RecyclerView) view.findViewById(R.id.recyPareja);
        adapterPareja = new ParejaAdapter(getContext());
        recyclerView.setAdapter(adapterPareja);

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
        Call<ArrayList<Pareja>> reporteRespuestaCall = service.obtenerDatosPareja();

        reporteRespuestaCall.enqueue(new Callback<ArrayList<Pareja>>() {
            @Override
            public void onResponse(Call<ArrayList<Pareja>> call, Response<ArrayList<Pareja>> response) {
                if(response.isSuccessful())
                {
                    ArrayList listado = response.body();
                    adapterPareja.adicionarLista(listado);
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pareja>> call, Throwable t) {
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
