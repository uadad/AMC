/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PB;

import PA.Parpuntos;
import PA.Punto;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author wadad
 */
public class Algoritmos {

    public static void generarFicheroGRafo(String nom, Solucion s) throws IOException {
        ArrayList<Punto> p = s.getA();
        int num = p.size() - 1;
        FileWriter fich;
        fich = new FileWriter("C:\\Users\\wadad\\OneDrive - UNIVERSIDAD DE HUELVA\\UHU\\repositorio\\3\\1-Cuatrimestre\\AMC\\Practica\\P1\\Practica1\\Dataset\\PB\\" + nom + ".out.tour");

        fich.write("NAME: " + nom + num + " Wadadi sidelgaum");
        fich.write("\nTYPE: TOUR");
        fich.write("\nDIMENSION : " + num + "");
        fich.write("\nSOLUTION: " + s.getDis());
        fich.write("\nTOUR_SECTION");
        fich.write("\n");
        for (int i = 0; i < num; i++) {
            fich.write("" + p.get(i));
            if (i + 1 != num) {
                fich.write(",");
            }

        }
        // fich.write("\n");
        for (int i = 0; i < num; i++) {
            Parpuntos l = new Parpuntos(p.get(i), p.get(i + 1));
            fich.write("\n" + l.distancia() + " - " + p.get(i).getId() + "," + p.get(i + 1).getId());

        }

        fich.write("\nEOF");
        fich.close();
    }

    public static Solucion unidireccional(Punto[] ciudades) {
        int n = ciudades.length;
        Random rand = new Random(System.currentTimeMillis());
        int r = rand.nextInt(n - 1) + 1;
        boolean[] visitadas = new boolean[n];
        double distancia = 0;
        ArrayList<Punto> Camino = new ArrayList<>();
        int cInicial = r;
        Camino.add(ciudades[cInicial]);
        visitadas[cInicial] = true;
        int cCercana = cInicial;
        for (int i = 0; i < n - 1; i++) {
            Punto puntoActual = ciudades[cCercana];
            double distanciaMinima = Double.MAX_VALUE;
            cCercana = -1;

            for (int j = 0; j < n; j++) {
                if (!visitadas[j]) {
                    Parpuntos seg = new Parpuntos(puntoActual, ciudades[j]);
                    double d = seg.distancia();
                    if (d < distanciaMinima) {
                        distanciaMinima = d;
                        distancia += distanciaMinima;
                        cCercana = j;
                    }
                }
            }
            Camino.add(ciudades[cCercana]);
            visitadas[cCercana] = true;
        }
        Camino.add(ciudades[cInicial]);

        Solucion s = new Solucion(distancia, Camino);

        return s;
    }

    public static Solucion bidireccional(Punto[] ciudades) {
        int n = ciudades.length;
        Random rand = new Random(System.currentTimeMillis());
        int r = rand.nextInt(n - 1) + 1;
        double distancia = 0.0;
        boolean[] visitadas = new boolean[n];
        ArrayList<Punto> ruta = new ArrayList<>();
        int cInicial = r;
        int cCercana = -1;
        ruta.add(ciudades[cInicial]);
        for (int i = 0; i < visitadas.length; i++) {
            visitadas[i] = false;
        }
        visitadas[cInicial] = true;
        for (int i = 0; i < ruta.size() && ruta.size() < n; i++) {
            double distanciaMinima = Double.MAX_VALUE;
            Punto puntoActual = ruta.get(i);
            for (int j = 0; j < n; j++) {
                if (!visitadas[j]) {
                    Parpuntos seg = new Parpuntos(puntoActual, ciudades[j]);
                    double d = seg.distancia();
                    if (d < distanciaMinima) {
                        distanciaMinima = d;
                        distancia += distanciaMinima;
                        cCercana = j;
                    }
                }
            }
            ruta.add(ciudades[cCercana]);
            visitadas[cCercana] = true;
        }
        ruta.add(ciudades[cInicial]);
        Solucion s = new Solucion(distancia, ruta);
        return s;
    }

    public static Solucion SolucionOptima(Punto[] ciudades) {
        int n = ciudades.length;
        boolean[] visitadas = new boolean[n];
        double distancia = 0;
        ArrayList<Punto> caminoOptimo = new ArrayList<>();
        ArrayList<Punto> mejorCamino = new ArrayList<>();
        double mejorDistancia = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visitadas[j] = false;
            }
            int actual = i;
            visitadas[actual] = true;
            caminoOptimo.add(ciudades[actual]);

            for (int k = 0; k < n - 1; k++) {
                Punto puntoActual = ciudades[actual];
                double distanciaMinima = Double.MAX_VALUE;
                int siguiente = -1;

                for (int j = 0; j < n; j++) {
                    if (!visitadas[j]) {
                        Parpuntos seg = new Parpuntos(puntoActual, ciudades[j]);
                        double d = seg.distancia();
                        if (d < distanciaMinima) {
                            distanciaMinima = d;
                            siguiente = j;
                        }
                    }
                }
                distancia += distanciaMinima;
                actual = siguiente;
                visitadas[actual] = true;
                caminoOptimo.add(ciudades[actual]);
            }
            distancia += new Parpuntos(ciudades[actual], ciudades[i]).distancia();
            caminoOptimo.add(ciudades[i]);

            if (distancia < mejorDistancia) {
                mejorDistancia = distancia;
                mejorCamino = new ArrayList<>(caminoOptimo);
            }
            caminoOptimo.clear();
            distancia = 0;
        }
        return new Solucion(mejorDistancia, mejorCamino);
    }
}
