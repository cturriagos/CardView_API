package com.example.cardview_api.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cardview_api.Modelo.Evaluador;
import com.example.cardview_api.R;

import java.util.ArrayList;

public class AdaptadorEvaluador {

    public AdaptadorEvaluador(Context context, ArrayList<Evaluador> datos) {
        super(context, R.layout.ly_item, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_item, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.lbl);
        TextView lblemail = (TextView)item.findViewById(R.id.lblEmail);
        TextView lblweb = (TextView)item.findViewById(R.id.lblweb);

        ImageView imageView = (ImageView)item.findViewById(R.id.imgUsr);

        Glide.with(this.getContext())
                .load(getItem(position).getUrlavatar())
                .into(imageView);

        //.error(R.drawable.imgnotfound)



        lblNombre.setText(getItem(position).getNombre());
        lblemail.setText(getItem(position).getEmail());
        lblweb.setText(getItem(position).getWebsite());

        return(item);
    }
}
