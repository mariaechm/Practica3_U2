/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.practica.base.controller.dao.dao_models;

import com.unl.practica.base.controller.dao.AdapterDao;
import com.unl.practica.base.models.Album;


/**
 *
 * @author maria
 */

public class DaoAlbum extends AdapterDao<Album> {
    private Album obj;

    public DaoAlbum(){
        super(Album.class);
    }

    public Album getObj(){
        if (obj == null) 
            this.obj = new Album();
        return this.obj;
    }
    
    public void setObj(Album obj){
        this.obj = obj;
    }

    public Boolean save (){
        try {
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
            
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
