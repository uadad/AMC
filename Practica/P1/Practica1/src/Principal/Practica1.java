/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Principal;

import PB.Solucion;
import PB.Algoritmos;
import PA.Algoritmo;
import PA.LLenarfichero;
import PA.Parpuntos;
import PA.Punto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author wadad
 */
public class Practica1 {

    private static Punto[] p;
    private static Scanner teclado = new Scanner(System.in);
    private static Parpuntos l;
    private static String nombre;

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("***************************Practica1***************************************");
        System.out.println("*********************Estrategias Algoritmica*************************");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("**********************Menu Principal*****************************************");
        System.out.println("--------------------1.Parte A-------------------------------------------");
        System.out.println("--------------------2.Parte B-------------------------------------------");
        int elige = new Scanner(System.in).nextInt();
        switch (elige) {
            case 1:

                ParteA();
                break;
            case 2:
                ParteB();
                break;

            default:
                System.exit(0);
        }

    }
    
    /***************************************************PArteA***************************************************************************/

    public static void ParteA() throws FileNotFoundException, IOException {
        int opc;
        Parpuntos l;
        String nombre = "";
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("""
                                       --------------------------------------------------****************Menu Principal********************-------------------------
                                                   
                                                   1.Cear un fichero datos .tsp aleatorio
                                                   2.Cargar un dataset en memoria
                                                   3.Comprobar Estrategia
                                                   4.Comparar todas las Estrategias
                                                   5.Comparar Dos Estrategias
                                                   6.Salir.
                                                """);
            opc = teclado.nextInt();
            switch (opc) {
                case 1: {
                    metodo1A();

                    break;
                }
                case 2: {

                    metodo2A();
                    break;
                }

                case 3: {
                    metodo3A();
                    break;
                }
                case 4:
                    metodo4A();
                    break;
                case 5:
                    metodo5A();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Error,elige un numero correcto.");

                    break;
            }
        } while (opc != 6);
    }

    
    /***************************************************PArteB***************************************************************************/
    
    private static void ParteB() throws IOException {

        int f;
        Solucion solucion = null;
        Punto[] puntos = null;
        String nomb = null,
                nom2 = null;
        do {
            System.out.println("""
                                                   Menu 
                                         1.Cargar un fichero en memoria.
                                         2.Crear puntos aleatorios
                                         3.Menu Algoritmo
                                         4.Generar fichero
                                         5.Salir""");
            f = new Scanner(System.in).nextInt();
            switch (f) {
                case 1:
                    System.out.println("Introduce el nombre del fichero que quieres cargar en memoria:");
                    nomb = new Scanner(System.in).nextLine();
                    puntos = LLenarfichero.llenarfichero(nomb + ".tsp");
                    nom2 = "huelva" + puntos.length;
                    break;
                case 2:
                    System.out.println("Introduce el numero de puntos: ");
                    int s = new Scanner(System.in).nextInt();
                    puntos = new Punto[s];
                    Random r = new Random(System.currentTimeMillis());
                    for (int i = 0; i < s; i++) {
                        puntos[i] = new Punto(r.nextInt(50) + 1, r.nextInt(50) + 1, i + 1);

                    }

                    Parpuntos linea = new Parpuntos();
                    for (int i = 0; i < s; i++) {
                        int k = r.nextInt(s);
                        if (k != i) {
                            linea.setLinea(puntos[i], puntos[k]);
                        }
                        int re = r.nextInt(14) + 1;
                        linea.setDistancia(re);
                        System.out.println(linea + " " + re);

                    }
                    nom2 = "huelva" + s;
                    break;
                case 3:

                    System.out.println("""
                                                      Menu 
                                                   1.Unidireccional
                                                   2.Bidireccional
                                                   3.Solucion Optima
                                                   4.salir""");
                    s = new Scanner(System.in).nextInt();
                    switch (s) {
                        case 1:
                            solucion = Algoritmos.unidireccional(puntos);
                            break;
                        case 2:
                            solucion = Algoritmos.bidireccional(puntos);
                            break;
                        case 3:
                             solucion=Algoritmos.SolucionOptima(puntos);
                            break;
                        case 4:
                            break;
                        default:
                            throw new AssertionError();
                    }
                    for (int i = 0; i < solucion.getA().size(); i++) {
                        System.out.println(solucion.getA().get(i));
                    }
                    System.out.println("Distancia " + nomb + " :" + solucion.getDis());

                    break;
                case 4:
                    if (solucion == null) {
                        System.out.println("No se puede generar el fichero porque la solucion esta vacia.");
                    } else {
                        Algoritmos.generarFicheroGRafo(nom2, solucion);
                    }
                    break;

            }

        } while (f != 5);
    }

    private static void metodo1A() throws IOException {

        System.out.println("Introduce el tamaño de array: ");
        int tam = teclado.nextInt();
        p = new Punto[tam];

        System.out.println("""
                                                                   elige el caso
                                                                   1.Caso Peor
                                                                   2.Caso Mejor""");
        int mismax = teclado.nextInt();
        switch (mismax) {
            case 1:
                LLenarfichero.generarDataSet(tam, true, false);
                break;
            case 2:
                LLenarfichero.generarDataSet(tam, false, false);
                break;
            default:
                System.out.println("Numero introducido invalido.");
                break;
        }
    }

    private static void metodo2A() throws IOException {
        int opc2;

        System.out.println("""
                                                                          ***************************************************************************************************************
                                                                          1.Cargar una fichero de memoria
                                                                          2.Cargar un dataset aleatorio
                                                                   ******************************************************************************************************
                                                                          """);
        opc2 = teclado.nextInt();
        if (opc2 == 1) {
            teclado.nextLine();
            System.out.println("Introduce el nombre del fichero que quieres cargar en memoria:");
            nombre = teclado.nextLine();
            p = LLenarfichero.llenarfichero(nombre + ".tsp");

            System.out.println("""
                                                                           ----****************Menu para los algoritmos********************-------------------------
                                                                           1.Exhaustivo
                                                                           2.Poda
                                                                           3.Divide y Venceras
                                                                           4.Divide y Venceras Mejorado """);
            int opc1 = teclado.nextInt();

            switch (opc1) {
                case 1:
                    l = Algoritmo.Exhaustivo(p, 0, p.length - 1);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                case 2:
                    l = Algoritmo.Bpoda(p, p.length - 1);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                case 3:
                    Algoritmo.quick_sort(p, 0, p.length - 1);
                    l = Algoritmo.DyV(p, 0, p.length - 1, 0);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                case 4:
                    Algoritmo.quick_sort(p, 0, p.length - 1);
                    l = Algoritmo.AlgoritmoDyVMejorado(p, 0, p.length - 1, 0);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                default:
                    System.out.println("Numero incorrecto elige otro valido.");
                    break;
            }

        } else if (opc2 == 2) {
            System.out.println("Introduce el tamaño de array: ");
            int tam = teclado.nextInt();
            p = new Punto[tam];

            System.out.println("""
                                                                           elige el caso
                                                                           1.Caso Peor
                                                                           2.Caso Medio""");
            int mismax = teclado.nextInt();
            switch (mismax) {
                case 1:
                    LLenarfichero.generarDataSet(tam, true, false);
                    break;
                case 2:
                    LLenarfichero.generarDataSet(tam, false, false);
                    break;
                default:
                    System.out.println("Numero introducido invalido.");
                    break;
            }
            p = LLenarfichero.llenarfichero("Dataset" + tam + ".tsp");
            nombre = "Dataset" + tam;

            int opc1;
            System.out.println("""
                                                                           ----****************Menu para los algoritmos********************-------------------------
                                                                           1.Exhaustivo
                                                                           2.Poda
                                                                           3.Divide y Venceras
                                                                           4.Divide y Venceras Mejorado""");
            opc1 = teclado.nextInt();
            switch (opc1) {
                case 1:
                    l = Algoritmo.Exhaustivo(p, 0, p.length - 1);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                case 2:
                    l = Algoritmo.Bpoda(p, p.length - 1);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                case 3:
                    Algoritmo.quick_sort(p, 0, p.length - 1);
                    l = Algoritmo.DyV(p, 0, p.length - 1, 0);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                case 4:
                    Algoritmo.quick_sort(p, 0, p.length - 1);
                    l = Algoritmo.AlgoritmoDyVMejorado(p, 0, p.length - 1, 0);
                    System.out.println("Distancia: " + l.distancia() + " Puntos: " + l);
                    break;
                default:
                    System.out.println("Numero incorrecto eligo otro valido.");
                    break;
            }
        }
    }

    private static void metodo3A() {
        if (p != null) {
            System.out.println(nombre);
            System.out.println("Estrategia                      Punto1                                        Punto2                                            distancia              calculadas       tiempo(mseg)");

            long t_inicial = System.nanoTime();
            l = Algoritmo.Exhaustivo(p, 0, p.length - 1);
            long t_final = System.nanoTime();
            long t_medio = t_final - t_inicial;
            System.out.println("Exhaustivo        " + l.getP1() + "            " + l.getP2() + "      " + l.distancia() + "              " + l.getCalculadas() + "                    " + t_medio / 1000000.0000);

            t_inicial = System.nanoTime();
            l = Algoritmo.Bpoda(p, p.length - 1);
            t_final = System.nanoTime();
            t_medio = t_final - t_inicial;
            System.out.println("Poda              " + l.getP1() + "            " + l.getP2() + "      " + l.distancia() + "              " + l.getCalculadas() + "                    " + t_medio / 1000000.0000);

            t_inicial = System.nanoTime();
            Algoritmo.quick_sort(p, 0, p.length - 1);
            l = Algoritmo.DyV(p, 0, p.length - 1, 0);
            t_final = System.nanoTime();
            t_medio = t_final - t_inicial;
            System.out.println("DyV        " + l.getP1() + "            " + l.getP2() + "      " + l.distancia() + "              " + l.getCalculadas() + "                    " + t_medio / 1000000.0000);

            t_inicial = System.nanoTime();
            Algoritmo.quick_sort(p, 0, p.length - 1);
            l = Algoritmo.AlgoritmoDyVMejorado(p, 0, p.length - 1, 0);
            t_final = System.nanoTime();
            t_medio = t_final - t_inicial;
            System.out.println("DyV Mejorado      " + l.getP1() + "            " + l.getP2() + "      " + l.distancia() + "              " + l.getCalculadas() + "                    " + t_medio / 1000000.0000);
        } else {
            System.out.println("el array de puntos esta vacio, no has cargado ningun fichero en memoria todavia.");
        }

    }

    private static void metodo4A() throws IOException {
        int talla_fin,
                talla_ini,
                talla_incremento;
        System.out.println("Introduce el tamaño de datos inicial:");
        talla_ini = teclado.nextInt();
        do {
            System.out.println("Introduce el tamaño de datos final:");
            talla_fin = teclado.nextInt();
        } while (talla_fin <= talla_ini);
        System.out.println("Introduce el incemento:");
        talla_incremento = teclado.nextInt();
        Punto[] puntos;
        long[] tiempo = new long[4];
        long t_inicial,
                t_media,
                t_final;

        System.out.println("              Exhaustivo   ExhaustivoPoda   DivideyVenceras    DyV Mejorado");
        System.out.println("Talla        Tiempo(mseg)   Tiempo(mseg)     Tiempo(mseg)       Tiempo(mseg)");
        for (int i = talla_ini; i <= talla_fin; i = i + talla_incremento) {
            tiempo[0] = 0;
            tiempo[1] = 0;
            tiempo[2] = 0;
            tiempo[3] = 0;
            for (int j = 0; j < 10; j++) {
                puntos = new Punto[i];
                LLenarfichero.generarDataSet(i, false, true);
                for (int k = 1; k <= 4; k++) {
                    puntos = LLenarfichero.llenarfichero("\\Estrategia4\\DataSet" + i + ".tsp");

                    t_inicial = System.nanoTime();
                    switch (k) {
                        case 1:
                            Algoritmo.Exhaustivo(puntos, 0, puntos.length - 1);
                            break;
                        case 2:
                            Algoritmo.Bpoda(puntos, puntos.length - 1);
                            break;
                        case 3:
                            Algoritmo.quick_sort(puntos, 0, puntos.length - 1);
                            Algoritmo.DyV(puntos, 0, puntos.length - 1, 0);
                            break;
                        case 4:
                            Algoritmo.quick_sort(puntos, 0, puntos.length - 1);
                            Algoritmo.AlgoritmoDyVMejorado(puntos, 0, puntos.length - 1, 0);
                            break;
                        default:
                            throw new AssertionError();
                    }
                    t_final = System.nanoTime();
                    t_media = t_final - t_inicial;
                    tiempo[k - 1] = tiempo[k - 1] + t_media;
                }
            }
            System.out.println(i + "          " + tiempo[0] / 1000000.0000 + "           " + tiempo[1] / 1000000.0000 + "            " + tiempo[2] / 1000000.0000 + "          " + tiempo[3] / 1000000.0000);
        }
    }

    private static void metodo5A() throws IOException {

        long[] tiempo = new long[2];
        Punto[] puntos;
        int talla_fin,
                talla_ini,
                talla_incremento;
        long t_inicial,
                t_media,
                t_final;
        double[] distancias = new double[2];
        System.out.println("Introduce el tamaño de datos inicial:");
        talla_ini = teclado.nextInt();
        do {
            System.out.println("Introduce el tamaño de datos final:");
            talla_fin = teclado.nextInt();
        } while (talla_fin <= talla_ini);
        System.out.println("Introduce el incemento:");
        talla_incremento = teclado.nextInt();
        int f1,
                f2;
        int t[] = new int[2];
        String nombre1 = "",
                nombre2 = "";
        do {
            do {
                System.out.println("""
                                                                                                        Elige un algoritmos
                                                                                                1.Exhaustivo
                                                                                                2.Poda
                                                                                                3.DyV
                                                                                                4.DyV Mejorado
                                                                                   """);

                f1 = teclado.nextInt();
            } while (0 > f1 && f1 > 5);

            do {
                System.out.println("""
                                                                                                        Elige otro algoritmos
                                                                                                1.Exhaustivo
                                                                                                2.Poda
                                                                                                3.DyV
                                                                                                4.DyV Mejorado
                                                                                   """);

                f2 = teclado.nextInt();
            } while (0 > f2 && f2 > 5);

        } while (f1 == f2);
        switch (f1) {
            case 1:
                nombre = "Exhaustivo";

                break;
            case 2:
                nombre = "ExhaustivoPoda";

                break;
            case 3:
                nombre = "DivideyVenceras";
                break;
            case 4:
                nombre = "DyV Mejorado";
                break;
            default:
        }
        switch (f2) {
            case 1:
                nombre2 = "Exhaustivo";

                break;
            case 2:
                nombre2 = "ExhaustivoPoda";

                break;
            case 3:
                nombre2 = "DivideyVenceras";
                break;
            case 4:
                nombre2 = "DyV Mejorado";
                break;
            default:
        }
        t[0] = f1;
        t[1] = f2;
        System.out.println("              " + nombre + "        " + nombre2 + "      " + nombre + "       " + nombre2);
        System.out.println("Talla        Tiempo(mseg)   Tiempo(mseg)     Distancias          Distancias");
        for (int i = talla_ini; i <= talla_fin; i = i + talla_incremento) {
            tiempo[0] = 0;
            tiempo[1] = 0;
            distancias[0] = 0;
            distancias[1] = 0;
            for (int j = 0; j < 10; j++) {
                puntos = new Punto[i];
                LLenarfichero.generarDataSet(i, false, true);
                for (int k = 1; k <= 2; k++) {
                    puntos = LLenarfichero.llenarfichero("\\Estrategia4\\DataSet" + i + ".tsp");

                    t_inicial = System.nanoTime();

                    switch (t[k - 1]) {
                        case 1:
                            distancias[k - 1] += Algoritmo.Exhaustivo(puntos, 0, puntos.length - 1).distancia();
                            break;
                        case 2:
                            distancias[k - 1] += Algoritmo.Bpoda(puntos, puntos.length - 1).distancia();
                            break;
                        case 3:
                            Algoritmo.quick_sort(puntos, 0, puntos.length - 1);
                            distancias[k - 1] += Algoritmo.DyV(puntos, 0, puntos.length - 1, 0).distancia();
                            break;
                        case 4:
                            Algoritmo.quick_sort(puntos, 0, puntos.length - 1);
                            l = Algoritmo.AlgoritmoDyVMejorado(puntos, 0, puntos.length - 1, 0);
                            distancias[k - 1] += l.distancia();
                            break;
                        default:
                            throw new AssertionError();
                    }
                    t_final = System.nanoTime();
                    t_media = t_final - t_inicial;
                    tiempo[k - 1] = tiempo[k - 1] + t_media;
                }
            }
            System.out.println(i + "          " + tiempo[0] / 1000000.0000 + "           " + tiempo[1] / 1000000.0000 + "            " + distancias[0] + "          " + distancias[1]);
        }
    }
}
