/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.models.Artista;

/**
 *
 * @author maria
 */
public class DaoArtista extends AdapterDao<Artista> {
    private Artista obj;
   
    public DaoArtista(){
        super(Artista.class);
    }
    
    public Artista getObj() {
        if (obj == null)
            this.obj = new Artista();
        return this.obj;
    }

    public void setObj(Artista obj) {
        this.obj = obj;
    }
    
    public Boolean save(){
        try{
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public Boolean update(Integer pos){
        try{
            this.update(obj, pos);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public static void main(String[] args) {
        DaoArtista da = new DaoArtista();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNacionalidad("Ecuatoriana");
        da.getObj().setNombre("Italo Chica");
        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
        da.setObj(null);
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNacionalidad("Mexicana");
        da.getObj().setNombre("Reik");
        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
    }
    
}
