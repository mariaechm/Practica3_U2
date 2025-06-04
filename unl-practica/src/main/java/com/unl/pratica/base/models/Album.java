/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.models;

import java.util.Date;

/**
 *
 * @author maria
 */

public class Album {
    private Integer id;
    private String nombre;
    private Date fecha;
    private Integer id_artista;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId_artista() {
        return this.id_artista;
    }

    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
    }
}
