package com.example.cardview_api;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardview_api.Adaptador.AdaptadorEvaluado;
import com.example.cardview_api.Modelo.Evaluado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Evaluado> listevaluado;
    RequestQueue requestQueue;
    AdaptadorEvaluado adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        obtenerEvaluados(id);
    }

    private void obtenerEvaluados(String id){
        String url = "https://uealecpeterson.net/ws/listadoaevaluar.php?e=" + id;
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url , null ,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarr = response.getJSONArray("listaaevaluar");
                            int size = jsonarr.length();
                            JSONObject json_transform;
                            if (size > 0) {
                                Evaluado eval;
                                listevaluado = new ArrayList<>();
                                for (int i = 0; i < size; i++) {
                                    json_transform = jsonarr.getJSONObject(i);
                                    eval = new Evaluado(json_transform);
                                    listevaluado.add(eval);
                                }
                                llenarLista();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        } else {
            requestQueue.add(request);
        }
    }

    public void llenarLista (){
        adapter = new AdaptadorEvaluado(MainActivity2.this, listevaluado);
        recyclerView = (RecyclerView) findViewById(R.id.listado2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}