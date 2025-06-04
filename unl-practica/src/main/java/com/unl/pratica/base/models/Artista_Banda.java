/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.models;

/**
 *
 * @author maria
 */
public class Artista_Banda {
    private Integer id;
    private RolArtistaEnum rol;
    private Integer id_artista;
    private Integer id_banda;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolArtistaEnum getRol() {
        return this.rol;
    }

    public void setRol(RolArtistaEnum rol) {
        this.rol = rol;
    }

    public Integer getId_artista() {
        return this.id_artista;
    }

    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
    }

    public Integer getId_banda() {
        return this.id_banda;
    }

    public void setId_banda(Integer id_banda) {
        this.id_banda = id_banda;
    }
}
