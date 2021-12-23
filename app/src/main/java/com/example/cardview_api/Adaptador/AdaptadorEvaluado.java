package com.example.cardview_api.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cardview_api.Modelo.Evaluado;
import com.example.cardview_api.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorEvaluado extends RecyclerView.Adapter<AdaptadorEvaluado.ViewHolder> {

    List<Evaluado> evaluados;
    LayoutInflater layout;
    Context context;

    public AdaptadorEvaluado(Context context, ArrayList<Evaluado> datos) {
        evaluados = datos;
        layout = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return evaluados.size();
    }

    @NonNull
    @Override
    public AdaptadorEvaluado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layout.inflate(R.layout.adapterevaluado, null);
        AdaptadorEvaluado.ViewHolder vh = new AdaptadorEvaluado.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEvaluado.ViewHolder holder, int position) {
        holder.bindData(evaluados.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id_evaluado;
        TextView txt_nombres;
        TextView txt_cargo;
        ImageView imgJPG;

        ViewHolder(View view) {
            super(view);
            txt_id_evaluado = view.findViewById(R.id.txt_id_evaluado);
            txt_nombres = view.findViewById(R.id.txt_nombres);
            txt_cargo = view.findViewById(R.id.txt_cargo);
            imgJPG = view.findViewById(R.id.imgJPG);
        }

        void bindData(final Evaluado evaluado){
            txt_id_evaluado.setText(evaluado.getId_evaluado());
            txt_nombres.setText(evaluado.getNombres());
            txt_cargo.setText(evaluado.getCargo());
            Glide.with(context)
                    .load(evaluado.getImgJPG())
                    .error(R.drawable.unknown)
                    .load(evaluado.getImgjpg())
                    .error(R.drawable.unknown)
                    .into(imgJPG);
        }
    }
}
