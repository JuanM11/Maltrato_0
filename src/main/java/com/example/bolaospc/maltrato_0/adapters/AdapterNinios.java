package com.example.bolaospc.maltrato_0.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.bolaospc.maltrato_0.MapsActivity;
import com.example.bolaospc.maltrato_0.R;
import com.example.bolaospc.maltrato_0.models.Ninios;

import java.util.ArrayList;

public class AdapterNinios extends RecyclerView.Adapter<AdapterNinios.ViewHolder> {


    private ArrayList<Ninios> datos;
    private Context context;

    public AdapterNinios(Context context) {
        this.context = context;
        datos = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviolencianinios, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Ninios p = datos.get(position);
        holder.TV1.setText(p.getHombreCasos());
        holder.TV2.setText(p.getHombre());
        holder.TV3.setText(p.getMujerCasos());
        holder.TV4.setText(p.getMujer());
        holder.TV5.setText(p.getMecanismoCausalDeLaLesiN());
        holder.TV6.setText(p.getTotalCasos());
        holder.TV7.setText(p.getTotal());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    public void adicionarLista(ArrayList<Ninios> lista) {
        datos.addAll(lista);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView TV1, TV2, TV3,TV4,TV5,TV6,TV7;
        private ImageButton imgBtn;
private CardView card;

        public ViewHolder(View itemView) {
            super(itemView);

            TV1 = (TextView) itemView.findViewById(R.id.txtNHombres);
            TV2 = (TextView) itemView.findViewById(R.id.txtPHombres);
            TV3 = (TextView) itemView.findViewById(R.id.txtNMujeres);
            TV4 = (TextView) itemView.findViewById(R.id.txtPMujeres);
            TV5 = (TextView) itemView.findViewById(R.id.txtCausalLesion);
            TV6 = (TextView) itemView.findViewById(R.id.txtTotal);
            TV7 = (TextView) itemView.findViewById(R.id.txtPTotal);
            card = (CardView) itemView.findViewById(R.id.card);
            imgBtn = (ImageButton) itemView.findViewById(R.id.ibtnninios);

            imgBtn.setOnClickListener(new View.OnClickListener()
            {

                public void onClick(View v) {
                    Intent miIntent=new Intent(context,MapsActivity.class);
                    miIntent.putExtra("lugar",1);

                    context.startActivity(miIntent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
