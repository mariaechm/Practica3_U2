package com.unl.pratica.base.controller.PrimeraParte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.unl.pratica.base.controller.data_struct.list.LinkedList;

public class ControllerDatos {
    private Integer[] matriz;
    private LinkedList<Integer> lista;

    public void cargar(String ruta) {
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
    public Integer[] detectarArreglo() {
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
    }

    public static void main(String[] args) {
        ControllerDatos p = new ControllerDatos();
        p.cargar("/home/maria/Escritorio/practicas/unl-practica/src/main/java/com/unl/pratica/base/controller/PrimeraParte/data.txt");

        long[] tiemposArreglo = new long[3];
        long[] tiemposLista = new long[3];

        Integer[] repetidosArreglo = null;
        Integer[] repetidosLista = null;

        // Medición para arreglo
        for (int i = 0; i < 3; i++) {
            long inicio = System.nanoTime();
            repetidosArreglo = p.detectarArreglo();
            long fin = System.nanoTime();
            tiemposArreglo[i] = fin - inicio;
        }

        // Medición para lista enlazada
        for (int i = 0; i < 3; i++) {
            long inicio = System.nanoTime();
            repetidosLista = p.detectarLista();
            long fin = System.nanoTime();
            tiemposLista[i] = fin - inicio;
        }

        // resultados arreglo
        System.out.println("Números repetidos en ARREGLO:");
        if (repetidosArreglo.length == 0) {
            System.out.println("No hay números repetidos.");
        } else {
            for (Integer n : repetidosArreglo) {
                System.out.println(n);
            }
        }
        System.out.println("Total distintos repetidos en ARREGLO: " + repetidosArreglo.length);

        //resultados lista
        System.out.println("\nNúmeros repetidos en LISTA:");
        if (repetidosLista.length == 0) {
            System.out.println("No hay números repetidos.");
        } else {
            for (Integer n : repetidosLista) {
                System.out.println(n);
            }
        }
        System.out.println("Total de numeros repetidos en LISTA: " + repetidosLista.length);

        // Tabla comparativa de tiempos
        System.out.println("\n-------------------------------------------");
        System.out.println("\nTabla comparativa de tiempos (nanosegundos):");
        System.out.printf("%-10s %-20s %-20s%n", "Iteración", "Arreglo", "Lista Enlazada");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-10d %-20d %-20d%n", (i + 1), tiemposArreglo[i], tiemposLista[i]);
        }
    }
}
