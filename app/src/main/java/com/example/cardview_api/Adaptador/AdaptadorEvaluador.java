package com.example.cardview_api.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cardview_api.MainActivity2;
import com.example.cardview_api.Modelo.Evaluador;
import com.example.cardview_api.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorEvaluador extends RecyclerView.Adapter<AdaptadorEvaluador.ViewHolder> {

    List<Evaluador> evaluadores;
    LayoutInflater layout;
    Context context;

    public AdaptadorEvaluador(Context context, ArrayList<Evaluador> datos) {
        evaluadores = datos;
        layout = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return evaluadores.size();
    }

    @NonNull
    @Override
    public AdaptadorEvaluador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layout.inflate(R.layout.adapter, null);
        ViewHolder vh = new AdaptadorEvaluador.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEvaluador.ViewHolder holder, int position) {
        holder.bindData(evaluadores.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView txt_idevaluador;
        TextView txt_nombres;
        TextView txt_area;
        ImageView img_evaluador;
        Button button;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            txt_idevaluador = view.findViewById(R.id.txt_id_evaluador);
            txt_nombres = view.findViewById(R.id.txt_nombres);
            txt_area = view.findViewById(R.id.txt_area);
            img_evaluador = view.findViewById(R.id.imgJPG);
            button = view.findViewById(R.id.btnEvaluados);

        }

        void bindData(final Evaluador evaluador){
            txt_idevaluador.setText(evaluador.getIdevaluador());
            txt_nombres.setText(evaluador.getNombres());
            txt_area.setText(evaluador.getArea());
            //Glide.with(context).load(evaluador.getImgjpg()).into(img_evaluador);
            Glide.with(context)
                    .load(evaluador.getImgJPG())
                    .error(R.drawable.unknown)
                    .load(evaluador.getImgjpg())
                    .error(R.drawable.unknown)
                    .into(img_evaluador);
            //SystemClock.sleep(1000);
            //Wait for 500 ms then check!.
            /*Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (img_evaluador.getDrawable() == null){
                        Glide.with(context).load(evaluador.getImgJPG()).into(img_evaluador);
                    }
                }
            }, 500);*/


            /*if(hasImage(img_evaluador)){
                Glide.with(context).load(evaluador.getImgJPG()).into(img_evaluador);
            }*/
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity2.class);

                    Bundle b = new Bundle();

                    b.putString("id", txt_idevaluador.getText().toString());
                    b.putString("nombres", txt_nombres.getText().toString());
                    b.putString("area", txt_area.getText().toString());

                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }

        private boolean hasImage(@NonNull ImageView view) {
            Drawable drawable = view.getDrawable();
            boolean hasImage = (drawable != null);

            if (hasImage && (drawable instanceof BitmapDrawable)) {
                hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
            }

            return hasImage;
        }
    }

    /*public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.getContext(context);
        View item = inflater.inflate(R.layout.adapter, null);

        TextView txtIdevaluador = (TextView)item.findViewById(R.id.txt_id_evaluador);
        TextView txtNombres = (TextView)item.findViewById(R.id.txt_nombres);
        TextView txtArea = (TextView)item.findViewById(R.id.txt_area);

        ImageView imgEvaluador = (ImageView)item.findViewById(R.id.imgJPG);

        //.error(R.drawable.imgnotfound)

        Evaluador evaluador = evaluadores.get(position);
        Glide.with(context)
                .load(evaluador.getImgJPG())
                .into(imgEvaluador);
        txtIdevaluador.setText(evaluador.getIdevaluador());
        txtNombres.setText(evaluador.getNombres());
        txtArea.setText(evaluador.getArea());

        return(item);
    }*/

}
