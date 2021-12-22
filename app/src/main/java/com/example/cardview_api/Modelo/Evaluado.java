package com.example.cardview_api.Modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Evaluado {

    private String id;
    private String id_evaluado;
    private String nombres;
    private String genero;
    private String situacion;
    private String cargo;
    private String fecha_inicio;
    private String imgJPG;
    private String imgjpg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_evaluado() {
        return id_evaluado;
    }

    public void setId_evaluado(String id_evaluado) {
        this.id_evaluado = id_evaluado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getImgJPG() {
        return imgJPG;
    }

    public void setImgJPG(String imgJPG) {
        this.imgJPG = imgJPG;
    }

    public String getImgjpg() {
        return imgjpg;
    }

    public void setImgjpg(String imgjpg) {
        this.imgjpg = imgjpg;
    }

    public Evaluado(JSONObject a) throws JSONException {
        id =  a.getString("id");
        id_evaluado =  a.getString("idevaluado").toString();
        nombres =  a.getString("Nombres").toString();
        genero =  a.getString("genero").toString();
        situacion =  a.getString("situacion").toString();
        cargo =  a.getString("cargo").toString();
        fecha_inicio =  a.getString("fechainicio").toString();
        imgJPG =  a.getString("imgJPG").toString();
        imgjpg =  a.getString("imgjpg").toString();
    }
}
