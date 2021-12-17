package com.example.cardview_api.Modelo;

import org.json.JSONException;
import org.json.JSONObject;

public class Evaluador {

    private String idevaluador;
    private String nombres;
    private String area;
    private String imgJPG;
    private String imgjpg;

    public String getIdevaluador() {
        return idevaluador;
    }

    public void setIdevaluador(String idevaluador) {
        this.idevaluador = idevaluador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Evaluador(JSONObject a) throws JSONException {
        idevaluador =  a.getString("idevaluador").toString();
        nombres =  a.getString("nombres").toString();
        area =  a.getString("area").toString();
        imgJPG = a.getString("imgJPG").toString();
        imgjpg = a.getString("imgjpg").toString();
    }


}
