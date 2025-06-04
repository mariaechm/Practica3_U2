/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import java.util.Date;

import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.models.Album;

/**
 *
 * @author maria
 */
public class DaoAlbum extends AdapterDao<Album> {
    private Album obj;
    
    public DaoAlbum(){
        super(Album.class);
    }
    
    public Album getObj() {
        if(obj == null)
            this.obj = new Album();
        return this.obj;
    }

    public void setObj(Album obj) {
        this.obj = obj;
    }
    
    public Boolean save(){
        try{
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch (Exception e) { 
            return false;  
        }
    }
    
    public Boolean update(Integer pos){
        try{
            this.update(obj, pos);
            return true;
        } catch (Exception e) { 
            return false;  
        }
    }

    public static void main(String[] args) {
        DaoAlbum da = new DaoAlbum();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNombre("Map of Soul Persona");
        da.getObj().setFecha(new Date());
        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
        da.setObj(null);
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNombre("Mundos Opuestos");
        da.getObj().setFecha(new Date());
        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
    }
}
