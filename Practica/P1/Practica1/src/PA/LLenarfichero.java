/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author wadad
 */
public class LLenarfichero {

    public static Punto[] llenarfichero(String direccion) throws FileNotFoundException {
        Punto[] p = null;
        Scanner teclado = new Scanner(new File("Dataset\\" + direccion));
        int i = 0;
        while (teclado.hasNext()) {
            String mensaje = teclado.nextLine();
            String[] list = mensaje.split(" ");
            if (list[0].equals("DIMENSION:")) { //si has llegado a la dimension pon la longitud de puntos 
                p = new Punto[Integer.parseInt(list[1])];
            }

            if (i > 5 && !list[0].equals("EOF")) { // si has terminado de las 6 primeras lineas significa que has llegado a las ciudades
                int iterador = Integer.parseInt(list[0]); //lees la posicion
                double x = Double.parseDouble(list[1]);//la cordenada x de la ciudad
                double y = Double.parseDouble(list[2]);//la cordenada y de la ciudad
                p[iterador - 1] = new Punto(x, y, iterador);
            }
            i++;
        }
        return p;
    }

    public static void generarDataSet(int num, boolean mismax, boolean opcion4) throws IOException {
        FileWriter fich;
        if (opcion4 == true) {
            fich = new FileWriter("C:\\Users\\wadad\\OneDrive - UNIVERSIDAD DE HUELVA\\UHU\\repositorio\\3\\1-Cuatrimestre\\AMC\\Practica\\P1\\Practica1\\Dataset\\Estrategia4\\DataSet" + num + ".tsp");
        } else {
            fich = new FileWriter("C:\\Users\\wadad\\OneDrive - UNIVERSIDAD DE HUELVA\\UHU\\repositorio\\3\\1-Cuatrimestre\\AMC\\Practica\\P1\\Practica1\\Dataset\\DataSet" + num + ".tsp");
        }

        fich.write("NAME: DataSet" + num + " Wadadi sidelgaum");
        fich.write("\nTYPE: TSP");
        fich.write("\nCOMMENT: " + num + " locations in Dataset" + num);
        fich.write("\nDIMENSION: " + num + "");
        fich.write("\nEDGE_WEIGHT_TYPE: EUC_2D");
        fich.write("\nNODE_COORD_SECTION");

        int e, den;
        double x, y, aux;
        Random r = new Random(System.currentTimeMillis());
        if (mismax) { //peor caso 
            for (int i = 0; i < num; i++) {
                aux = r.nextDouble(1000) + 7;
                y = aux / ((double) i + 1 + i * 0.100);
                e = r.nextInt(3);
                y += ((i % 500) - e * (r.nextDouble() % 100));
                x = 1;
                fich.write("\n" + (i + 1) + " " + x + " " + y + "");
            }
        } else { //caso medio
            for (int i = 0; i < num; i++) {
                e = r.nextInt(4000) + 1;
                den = r.nextInt(11) + 7;
                x = e / ((double) den + 0.37);
                y = (r.nextDouble(4000) + 1) / ((double) (r.nextDouble(11) + 7) + 0.37);
                fich.write("\n" + (i + 1) + " " + x + " " + y + "");
            }
        }

        fich.write("\nEOF");
        fich.close();
    }
    
    

}
