package com.example.cardview_api;

import android.annotation.SuppressLint;
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
import com.example.cardview_api.Adaptador.AdaptadorEvaluador;
import com.example.cardview_api.Modelo.Evaluador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://www.uealecpeterson.net/ws/listadoevaluadores.php";
    RequestQueue requestQueue;
    ArrayList<Evaluador> listeva;
    RecyclerView recyclerView;
    AdaptadorEvaluador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handleSSLHandshake();
        obtenerRegistroVOLLEY();
    }

    private void obtenerEvaluados(int id){
        String url = "https://evaladmin.uteq.edu.ec/ws/listadoaevaluar.php?e=" + id;
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url , null ,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarr = response.getJSONArray("");
                            int size = jsonarr.length();
                            JSONObject json_transform;
                            if (size > 0) {
                                Evaluador eva;
                                listeva = new ArrayList<>();
                                for (int i = 0; i < size; i++) {
                                    json_transform = jsonarr.getJSONObject(i);
                                    eva = new Evaluador(json_transform);
                                    listeva.add(eva);
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

    private void obtenerRegistroVOLLEY(){

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url, null ,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarr = response.getJSONArray("listaaevaluador");
                            int size = jsonarr.length();
                            JSONObject json_transform;
                            if (size > 0) {
                                Evaluador eva;
                                listeva = new ArrayList<>();
                                for (int i = 0; i < size; i++) {
                                    json_transform = jsonarr.getJSONObject(i);
                                    eva = new Evaluador(json_transform);
                                    listeva.add(eva);
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
        adapter = new AdaptadorEvaluador(MainActivity.this, listeva);
        recyclerView = (RecyclerView) findViewById(R.id.listado);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public static String fixEncoding(String response) {
        try {
            byte[] u = response.toString().getBytes(
                    "ISO-8859-1");
            response = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}