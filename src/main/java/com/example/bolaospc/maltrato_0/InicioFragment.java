package com.example.bolaospc.maltrato_0;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    //private TextView info_text;
    private WebView winfo_text;
    private FragmentActivity myContext;
    public ImageButton ninios,pareja,graficas,acercade,salir;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.inicio_fragment, container, false);
        ninios= (ImageButton) view.findViewById(R.id.btnNiños);

        pareja= (ImageButton) view.findViewById(R.id.btnParejas);

        acercade= (ImageButton) view.findViewById(R.id.btnacerca);
        salir= (ImageButton) view.findViewById(R.id.btnsal);

        ninios.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Fragment fragment = null;
                Class fragmentClass= ViolenciaNiniosFragment.class;
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            }
        });


        pareja.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Fragment fragment = null;
                Class fragmentClass= ParejaFragment.class;
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            }
        });

        acercade.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                AlertDialog.Builder uBuilder2 = new AlertDialog.Builder(InicioFragment.super.getContext());
                View aView2 = getLayoutInflater().inflate(R.layout.acercade, null);
                uBuilder2.setView(aView2);
                final AlertDialog dialog2 = uBuilder2.create();
                dialog2.show();
                Button close = (Button) aView2.findViewById(R.id.close);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.cancel();
                    }
                });

            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myContext.finish();
            }
        });
        return view;
    }

}
