package com.unl.pratica.base.controller.PrimeraParte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.unl.pratica.base.controller.data_struct.list.LinkedList;

public class ControllerDatos {
    private Integer[] matriz;
    private LinkedList<Integer> lista;

    public void cargar(String ruta) { //Lee la data 
        lista = new LinkedList<>();
        matriz = new Integer[1000];
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                int num = Integer.parseInt(linea.trim());

                // Arreglo
                if (count == matriz.length) {
                    Integer[] nuevo = new Integer[matriz.length * 2];
                    System.arraycopy(matriz, 0, nuevo, 0, matriz.length);
                    matriz = nuevo;
                }
                matriz[count++] = num;

                // Lista
                lista.add(num);
            }
            matriz = Arrays.copyOf(matriz, count);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Detectar repetidos ARREGLO
    /*public Integer[] detectarArreglo() {
        java.util.ArrayList<Integer> vistos = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> repetidos = new java.util.ArrayList<>();

        for (int i = 0; i < matriz.length; i++) {
            int actual = matriz[i];
            boolean yaVisto = false;
            for (int j = 0; j < vistos.size(); j++) {
                if (vistos.get(j).equals(actual)) {
                    yaVisto = true;
                    break;
                }
            }
            if (yaVisto) {
                boolean yaRepetido = false;
                for (int j = 0; j < repetidos.size(); j++) {
                    if (repetidos.get(j).equals(actual)) {
                        yaRepetido = true;
                        break;
                    }
                }
                if (!yaRepetido) {
                    repetidos.add(actual);
                }
            } else {
                vistos.add(actual);
            }
        }

        return repetidos.toArray(new Integer[0]);
    }

    // Detectar repetidos LISTA ENLAZADA
    public Integer[] detectarLista() {
        java.util.ArrayList<Integer> vistos = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> repetidos = new java.util.ArrayList<>();

        for (int i = 0; i < lista.getLength(); i++) {
            Integer actual = lista.get(i);
            boolean yaVisto = false;
            for (int j = 0; j < vistos.size(); j++) {
                if (vistos.get(j).equals(actual)) {
                    yaVisto = true;
                    break;
                }
            }
            if (yaVisto) {
                boolean yaRepetido = false;
                for (int j = 0; j < repetidos.size(); j++) {
                    if (repetidos.get(j).equals(actual)) {
                        yaRepetido = true;
                        break;
                    }
                }
                if (!yaRepetido) {
                    repetidos.add(actual);
                }
            } else {
                vistos.add(actual);
            }
        }

        return repetidos.toArray(new Integer[0]);
    }*/

    //Metodos de Ordenacion

    //METODO QUICKSORT
    private void quick_sort (Integer arr[], int inicio, int fin){
        if (inicio < fin) {
            int particionIndex = particion(arr, inicio, fin);

            quick_sort(arr, inicio, particionIndex -1);
            quick_sort(arr, particionIndex + 1, fin);
        }
    }

    private int particion(Integer arr[], int inicio, int fin){
       int pivot = arr[fin]; //pivot --> pivote
       int i = (inicio -1); 

       for (int j =  inicio; j < fin; j++){
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
       }
       int swapTemp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = swapTemp;

        return i + 1;
    }

    //METODO SHELLSORT
     public void shell_sort(Integer arrayToSort[]) {
        int n = arrayToSort.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = arrayToSort[i];
                int j = i;
                while (j >= gap && arrayToSort[j - gap] > key) {
                    arrayToSort[j] = arrayToSort[j - gap];
                    j -= gap;
                }
                arrayToSort[j] = key;
            }
        }
    }

    public void quick_sort_order() {
        if (!lista.isEmpty()) {
            Integer arr[] = lista.toArray();
            long startTime = System.currentTimeMillis();
            quick_sort(arr, 0, arr.length - 1);
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("El tiempo que se ha demorado Quicksort es: " + endTime );
            lista.toList(arr);
        }        
    }

    public void shell_sort_order() {
        if (!lista.isEmpty()) {
            Integer arr[] = lista.toArray();
            long startTime = System.currentTimeMillis();
            shell_sort(arr);
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("El tiempo que se ha demorado Shellsort es: " + endTime );
            lista.toList(arr);
        }        
    }

    public static void main(String[] args) {
        ControllerDatos p = new ControllerDatos();
        p.cargar("/home/maria/Escritorio/practicas/unl-practica/src/main/java/com/unl/pratica/base/controller/PrimeraParte/data.txt");

        System.out.println("\nQUICKSORT");
        p.quick_sort_order();

        System.out.println("\nSHELLSORT");
        p.shell_sort_order();
    }
}