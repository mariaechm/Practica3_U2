/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import java.util.HashMap;
import java.util.logging.Handler;

import com.unl.pratica.base.controller.Utiles;
import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.controller.data_struct.list.LinkedList;
import com.unl.pratica.base.models.Artista;
import com.unl.pratica.base.models.Artista_Banda;
import com.unl.pratica.base.models.Cancion;
import com.unl.pratica.base.models.TipoArchivoEnum;

/**
 *
 * @author maria
 */
public class DaoCancion extends AdapterDao<Cancion> {
    private Cancion obj;

    public DaoCancion() {
        super(Cancion.class);
    }

    public Cancion getObj() {
        if (obj == null)
            this.obj = new Cancion();
        return obj;
    }

    public void setObj(Cancion obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            if (obj.getId() == null) {
                obj.setId(listAll().getLength());
            }
            obj.setId(this.listAll().getLength());
            this.persist(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean update(Integer pos) {
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LinkedList<HashMap<String, String>> all() throws Exception {
        LinkedList<HashMap<String, String>> lista = new LinkedList<>();
        if (!this.listAll().isEmpty()) {
            Cancion[] arreglo = this.listAll().toArray();
            for (int i = 0; i < arreglo.length; i++) {

                lista.add(toDict(arreglo[i]));
            }
        }
        return lista;
    }

    private HashMap<String, String> toDict(Cancion arreglo) throws Exception {
        HashMap<String, String> aux = new HashMap<>();

        aux.put("id", arreglo.getId().toString());
        aux.put("nombre", arreglo.getNombre());
        aux.put("genero", new DaoGenero().listAll().get(arreglo.getId_genero() - 1).getNombre());
        aux.put("duracion", arreglo.getDuracion().toString());
        aux.put("album", new DaoAlbum().listAll().get(arreglo.getId_album() - 1).getNombre());

        aux.put("id_genero", String.valueOf(new DaoGenero().listAll().get(arreglo.getId_genero() - 1).getId()));
        aux.put("id_album", String.valueOf(new DaoAlbum().listAll().get(arreglo.getId_album() - 1).getId()));

        aux.put("url", arreglo.getUrl());
        aux.put("tipo", arreglo.getTipo().toString());
        return aux;
    }

    // Ordenamiento
    /*
     * public LinkedList<HashMap<String, String>> orderByCancion(Integer type,
     * String attribute) throws Exception {
     * LinkedList<HashMap<String, String>> lista = all();
     * if (!lista.isEmpty()) {
     * HashMap arr[] = lista.toArray();
     * int n = arr.length;
     * if (type == Utiles.ASCEDENTE) {
     * for (int i = 0; i < n - 1; i++) {
     * int min_idx = i;
     * for (int j = i + 1; j < n; j++)
     * if (arr[j].get(attribute).toString().toLowerCase()
     * .compareTo(arr[min_idx].get(attribute).toString().toLowerCase()) < 0) {
     * min_idx = j;
     * }
     * 
     * HashMap temp = arr[min_idx];
     * arr[min_idx] = arr[i];
     * arr[i] = temp;
     * }
     * } else {
     * for (int i = 0; i < n - 1; i++) {
     * int min_idx = i;
     * for (int j = i + 1; j < n; j++)
     * if (arr[j].get(attribute).toString().toLowerCase()
     * .compareTo(arr[min_idx].get(attribute).toString().toLowerCase()) > 0) {
     * min_idx = j;
     * }
     * 
     * HashMap temp = arr[min_idx];
     * arr[min_idx] = arr[i];
     * arr[i] = temp;
     * }
     * }
     * }
     * return lista;
     * }
     */

    // QuickSort
    private int partition(HashMap<String, String>[] arr, int inicio, int fin, Integer type, String attribute,
            boolean isEntero) {
        HashMap<String, String> pivot = arr[fin];
        int i = (inicio - 1); // toma el elemento que se compara con los demas

        for (int j = inicio; j < fin; j++) { // recorrer los elementos
            int compare;
            if (isEntero) { // si es entero convierte los valores a doubles y los compara
                Double valJ = Double.parseDouble(arr[j].get(attribute));
                Double valPivot = Double.parseDouble(pivot.get(attribute));
                compare = valJ.compareTo(valPivot);
            } else { // si es attribute(STring) compar como cadenas
                compare = arr[j].get(attribute).toLowerCase().compareTo(pivot.get(attribute).toLowerCase());
            }
            boolean condition = (type == Utiles.ASCEDENTE) ? (compare < 0) : (compare > 0);

            if (condition) {
                i++;
                HashMap<String, String> swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }
        HashMap<String, String> swapTemp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = swapTemp;

        return i + 1;
    }

    // QuickSort para atributos String
    private void quickSortS(HashMap<String, String>[] arr, int inicio, int fin, Integer type, String attribute) {
        if (inicio < fin) {
            int partitionIndex = partition(arr, inicio, fin, type, attribute, false);
            quickSortS(arr, inicio, partitionIndex - 1, type, attribute);
            quickSortS(arr, partitionIndex + 1, fin, type, attribute);
        }
    }

    // QuickSort para atributos enteros
    private void quickSortE(HashMap<String, String>[] arr, int inicio, int fin, Integer type, String attribute) {
        if (inicio < fin) {
            int partitionIndex = partition(arr, inicio, fin, type, attribute, true);
            quickSortE(arr, inicio, partitionIndex - 1, type, attribute);
            quickSortE(arr, partitionIndex + 1, fin, type, attribute);
        }
    }

    // Ordenar por atributo s

    public LinkedList<HashMap<String, String>> orderByCancion(Integer type, String attribute, boolean isNumero)
            throws Exception {
        if (attribute.equalsIgnoreCase("id_album") || attribute.equalsIgnoreCase("id_genero")) {
            throw new IllegalArgumentException("No se permite ordenar por id_album ni id_genero.");
        }
        LinkedList<HashMap<String, String>> lista = all();
        if (!lista.isEmpty()) {
            HashMap<String, String>[] arr = lista.toArray();
            if (isNumero) {
                quickSortE(arr, 0, arr.length - 1, type, attribute);
            } else {
                quickSortS(arr, 0, arr.length - 1, type, attribute);
            }
            lista.toList(arr);
        }
        return lista;
    }

    // BUSQUEDA
    public LinkedList<HashMap<String, String>> buscar(String attribute, String text, Integer type) throws Exception {
        LinkedList<HashMap<String, String>> lista = all();
        LinkedList<HashMap<String, String>> resp = new LinkedList<>();

        if (!lista.isEmpty()) {
            HashMap<String, String>[] arr = lista.toArray();
            System.out.println(attribute + " " + text + " ** *** * * ** * * * *");
            switch (type) {
                case 1:
                    System.out.println(attribute + " " + text + " UNO");
                    for (HashMap m : arr) {
                        if (m.get(attribute).toString().toLowerCase().startsWith(text.toLowerCase())) {
                            resp.add(m);
                        }
                    }
                    break;
                case 2:
                    System.out.println(attribute + " " + text + " DOS");
                    for (HashMap m : arr) {
                        if (m.get(attribute).toString().toLowerCase().endsWith(text.toLowerCase())) {
                            resp.add(m);
                        }
                    }
                    break;
                default:
                    System.out.println(attribute + " " + text + " TRES");
                    for (HashMap m : arr) {
                        System.out.println("***** " + m.get(attribute) + "   " + attribute);
                        if (m.get(attribute).toString().toLowerCase().contains(text.toLowerCase())) {
                            resp.add(m);
                        }
                    }
                    break;
            }
        }
        return resp;
    }

    private Integer bynaryLineal (HashMap<String, String>[] array, String attribute, String text) { 
        Integer half = 0; 
        if (!(array.length==6) && !text.isEmpty()) { 
            half = array.length / 2; 
            int aux = 0; 
            if(text.trim().toLowerCase().charAt(8) > array[half].get(attribute).toString().trim().toLowerCase().charAt(e)) 
            aux = 1; 
        else if(text.trim().toLowerCase().charAt(8) < array[half].get(attribute).toString().trim().toLowerCase().charAt(8)) 
            aux = -1; 

        half = half * aux; 
    }
    return half;
  }
}
