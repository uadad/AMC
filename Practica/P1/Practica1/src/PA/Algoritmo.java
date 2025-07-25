/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author wadad
 */
public class Algoritmo {

    //********************************Calcular los puntos por la distancia*******************************//
    public Parpuntos DistanciaPuntos(double distancia, Punto[] p) {

        Parpuntos l = new Parpuntos();
        for (int i = 0; i < p.length - 1; i++) {
            for (int j = i + 1; j < p.length - 1; j++) {
                l.setLinea(p[i], p[j]);
                if (l.distancia() == distancia) {
                    return l;
                }
            }
        }
        return null;
    }

    //****************************Exhaustivo**************//
    public static Parpuntos Exhaustivo(Punto p[], int in, int f) {
        int c = 0;                                                //1OE
        Parpuntos l = new Parpuntos(p[0], p[1]);                  //3OE
        double min = l.distancia();                               //3OE
        for (int i = in; i < f; i++) {                            //1OE+3OE
            for (int j = i + 1; j < f; j++) {                     //2OE+3OE
                Parpuntos aux = new Parpuntos(p[i], p[j]);        //3OE
                c++;                                              //2OE
                if (aux.distancia() < min) {                      //3OE
                    l.setLinea(p[i], p[j]);                       //5OE
                    min = aux.distancia();                        //3OE
                }
            }
        }
        l.setCalculadas(c);                              //1OE
        return l;                                                //1OE
    }

    //********************Algoritmo poda************************//
    public static int partition(Punto p[], int in, int f, Punto pivote) {
        int i = in;
        int j = in;
        while (i <= f) {
            if (p[i].getX() > pivote.getX()) {
                i++;
            } else {
                intercambiar(p, i, j);
                i++;
                j++;
            }
        }
        return j - 1;
    }

    public static void intercambiar(Punto p[], int p1, int p2) {
        Punto aux;
        aux = p[p1];
        p[p1] = p[p2];
        p[p2] = aux;
    }

    public static void quick_sort(Punto p[], int in, int f) {
        Punto pivote;
        int q;
        if (in < f) {
            pivote = p[f];
            q = partition(p, in, f, pivote);
            quick_sort(p, in, q - 1);
            quick_sort(p, q + 1, f);
        }
    }

    public static Parpuntos Bpoda(Punto p[], int f) {
        int c = 0;                                                  //1OE
        quick_sort(p, 0, f);                                   //N LOGN
        Parpuntos l = new Parpuntos(p[0], p[1]);                  //3OE
        double min = l.distancia();                               //3OE
        c++;                                                      //2OE
        int j;
        for (int i = 0; i < f; i++) {                             //2OE+3OE
            j = i + 1;                                            //2OE
            while (j < f && ((p[j].getX() - p[i].getX()) < min)) {
                Parpuntos aux = new Parpuntos(p[i], p[j]);        //3OE
                c++;                                              //2OE
                if (aux.distancia() < min) {                      //3OE
                    l.setLinea(p[i], p[j]);                          //3OE
                    min = aux.distancia();
                }
                                                                 //3OE
                j++;                                            //2OE
            }
        }
        l.setCalculadas(c);
        return l;                                               //1OE
    }

    ///////////////////Algoritmo DyV///////////////////////////
    public static Parpuntos DyV(Punto T[], int i, int d,int calculadas) {
        Parpuntos l = null;
        int opc = d - i + 1;
        if (opc > 3) {
            int m = (i + d) / 2;

            Parpuntos di = DyV(T, i, m,calculadas);
            Parpuntos dd = DyV(T, m + 1, d,calculadas);
            Parpuntos dmin = new Parpuntos();
            dmin.setDistancia(Double.min(di.distancia(), dd.distancia()));
            calculadas+=2;
            if (dmin.distancia() == di.distancia()) {
                dmin = di;
            } else {
                dmin = dd;
            }
            int a = m;

            while ((a >= i) && ((T[m + 1].getX() - T[a].getX()) < dmin.distancia())) {
                a--;
                calculadas++;
            }
            int b = m + 1;

            while ((b <= d) && ((T[b].getX() - T[m].getX()) < dmin.distancia())) {
                b++;
                calculadas++;
            }
            l = Exhaustivo(T, a + 1, b - 1);
            calculadas+=l.getCalculadas();
            calculadas+=2;
            if (l.distancia() < dmin.distancia()) {
                dmin = l;
                dmin.setCalculadas(calculadas);
            }
            dmin.setCalculadas(calculadas);
            return dmin;

        } else {
            l = Exhaustivo(T, i, d);
            calculadas+=l.getCalculadas();
            l.setCalculadas(calculadas);
            return l;
        }
    }

    public static Parpuntos AlgoritmoDyVMejorado(Punto[] p, int in, int f,int calculadas) {
        Parpuntos l = null;
        if (f - in <= 2) {
            l = Exhaustivo(p, in, f);
            calculadas+=l.getCalculadas();
            l.setCalculadas(calculadas);
            return l;
        }
        int medio = (in + f) / 2;
        Punto m = p[medio];
       
        Parpuntos di = AlgoritmoDyVMejorado(p, in, medio,calculadas);
        Parpuntos dd = AlgoritmoDyVMejorado(p, medio + 1, f,calculadas);
        l = dd;
        calculadas+=2;
        if (l.distancia() > di.distancia()) {
            l = di;
        }

        List<Punto> puntos = new ArrayList<>();
        for (int i = in; i <= f; i++) {
            calculadas++;
            if (Math.abs(p[i].getX() - m.getX()) < l.distancia()) {
                puntos.add(p[i]);
            }
        }
        puntos.sort(Comparator.comparingDouble(pu -> pu.getY()));
        for (int i = 0; i < puntos.size(); i++) {
            for (int j = i + 1; j < puntos.size() && (puntos.get(j).getY() - puntos.get(i).getY()) < l.distancia(); j++) {
                Parpuntos aux = new Parpuntos(puntos.get(i), puntos.get(j));
                double distancia = aux.distancia();
                   calculadas+=2;
                if (l.distancia() > distancia) {
                    l = aux;
                }
            }
        }
        l.setCalculadas(calculadas);
        return l;
    }
}
