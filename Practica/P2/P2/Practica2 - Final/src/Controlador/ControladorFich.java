/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;

import Modelo.AFD;
import Modelo.AFND;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author wadad
 */
public class ControladorFich {

    private static AFD automataD;
    private static AFND automataND;
    private Scanner scan;

    public Object leerFichero(File fichero) {
        Object devolver = null;
        try {
            if (fichero.exists()) {
                scan = new Scanner(fichero);
                String linea = scan.nextLine();
                String[] partes = linea.split(" ");
                if (partes[1].equals("AFD")) {
                    devolver = leerAFD();

                } else {

                    devolver = leerAFND();

                }
            } else {
                JOptionPane.showConfirmDialog(null, "Error no se encuentra dicho fichero.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        }
        return devolver;
    }
   /**
     * metodo que lee del fichero un grafo de tipo AFD
     * 
     * @return Devuelve el grafo AFD del fichero 
     */
    public AFD leerAFD() {
        automataD = new AFD();
        for (int i = 0; i < 4; i++) {
            String linea = scan.nextLine();
            String[] partes = linea.split(" ");
            switch (partes[0]) {
                case "INICIAL:":
                    String[] ei = partes[1].split("q");
                    automataD.setEstadoInicial(Integer.parseInt(ei[1]));
                    break;

                case "FINALES:":
                    for (int j = 1; j < partes.length; j++) {
                        String[] ef = partes[j].split("q");
                        automataD.añadirEstadoFinal(Integer.parseInt(ef[1]));
                    }
                    break;

                case "TRANSICIONES:":
                    linea = scan.nextLine();
                    while (!linea.equals("FIN")) {

                        partes = linea.split(" ");
                        System.out.println(partes[0] + " " + partes[1] + " " + partes[2]);
                        String[] eo = partes[0].split("q");
                        String[] simb = partes[1].split("'");
                        String[] ed = partes[2].split("q");
                        System.out.println(eo[1] + " " + simb[1] + " " + ed[1]);
                        automataD.agregarTransicion(Integer.parseInt(eo[1]), simb[1].charAt(0),
                                Integer.parseInt(ed[1]));

                        linea = scan.nextLine();
                    }
                    break;

            }

        }
        return automataD;
    }
    /**
     * metodo que lee del fichero un grafo de tipo AFND
     * 
     * @return Devuelve el grafo AFND del fichero 
     */
    private AFND leerAFND() {
        automataND = new AFND();
        for (int i = 0; i < 4; i++) {
            String linea = scan.nextLine();
            String[] partes = linea.split(" ");

            switch (partes[0]) {
                case "INICIAL:":
                    break;

                case "FINALES:":
                    int[] fin = new int[partes.length];
                    for (int j = 1; j < partes.length; j++) {
                        String[] ef = partes[j].split("q");
                        fin[j - 1] = Integer.parseInt(ef[1]);
                    }
                    automataND.setEstadosFinales(fin);
                    break;

                case "TRANSICIONES:":
                    linea = scan.nextLine();
                    while (!linea.equals("TRANSICIONES LAMBDA:")) {

                        partes = linea.split(" ");

                        String[] eo = partes[0].split("q");
                        String[] simb = partes[1].split("'");

                        int[] ed = new int[partes.length - 2];
                        int k = 0;
                        for (int j = 2; j < partes.length; j++) {

                            String[] a = partes[j].split("q");
                            ed[k] = Integer.parseInt(a[1]);
                            k++;
                        }
                        automataND.agregarTransicion(Integer.parseInt(eo[1]), simb[1].charAt(0),
                                ed);

                        linea = scan.nextLine();
                    }
                    linea = scan.nextLine();
                    while (!linea.equals("FIN")) {
                        partes = linea.split(" ");

                        String[] eo = partes[0].split("q");
                        int[] ed = new int[partes.length - 1];
                        int k = 0;
                        for (int j = 1; j < partes.length; j++) {

                            String[] a = partes[j].split("q");
                            ed[k] = Integer.parseInt(a[1]);
                            k++;
                        }
                        automataND.agregarTransicionλ(Integer.parseInt(eo[1]), ed);

                        linea = scan.nextLine();
                    }
                    break;

            }

        }

        return automataND;
    }
    
    /**
     * metodo que crea un fichero a partir de un grafo en formato object y un nombre con el que se guarda
     * 
     * @param devolver grafo en formato Object que va a ser guardado
     * @param nom cadena de caracteres que contiene el nombre del fichero donde se van a guardar los datos
     * @throws java.io.IOException
     */
    public void CreaFich(Object devolver, String nom) throws IOException {
        FileWriter fich;

        fich = new FileWriter("Ficheros\\" + nom + ".txt");
        if (devolver instanceof AFD) {

            AFD afd = (AFD) devolver;
            fich.write("TIPO: AFD");
            fich.write("\nESTADOS:");
            for (int i = 0; i < afd.getEstados().size(); i++) {
                fich.write(" q" + afd.getEstados().get(i));
            }
            fich.write("\nINICIAL: q" + afd.getEstadoinicial());
            fich.write("\nFINALES:");
            for (int i = 0; i < afd.getEstadosFinales().size(); i++) {
                fich.write(" q" + afd.getEstadosFinales().get(i));
            }
            fich.write("\nTRANSICIONES:");
            for (int i = 0; i < afd.getTransiciones().size(); i++) {
                fich.write("\nq" + afd.getTransiciones().get(i).getInicio() + " '" +
                        afd.getTransiciones().get(i).getsimbolo() + "' q" +
                        afd.getTransiciones().get(i).getDestino());
            }
            fich.write("\nFIN");
        } else {
            AFND afnd = (AFND) devolver;
            fich.write("TIPO: AFND");
            fich.write("\nESTADOS:");
            for (int i = 0; i < afnd.getEstados().size(); i++) {
                fich.write(" q" + afnd.getEstados().get(i));
            }
            fich.write("\nINICIAL: q" + afnd.getTransiciones().get(0).getInicio());
            fich.write("\nFINALES:");
            for (int i = 0; i < afnd.getEstadosFinales().length; i++) {
                fich.write(" q" + afnd.getEstadosFinales()[i]);
            }
            fich.write("\nTRANSICIONES:");
            for (int i = 0; i < afnd.getTransiciones().size(); i++) {
                fich.write("\nq" + afnd.getTransiciones().get(i).getInicio() + " '" + 
                        afnd.getTransiciones().get(i).getsimbolo() + "'");
                for (int j = 0; j < afnd.getTransiciones().get(i).getDestinos().length; j++) {
                    fich.write(" q" + afnd.getTransiciones().get(i).getDestinos()[j]);
                }
            }
            fich.write("\nTRANSICIONES LAMBDA:");
            for (int i = 0; i < afnd.getTransicionesλ().size(); i++) {
                fich.write("\nq" + afnd.getTransicionesλ().get(i).getInicio());
                for (int j = 0; j < afnd.getTransicionesλ().get(i).getDestinos().length; j++) {
                    fich.write(" q" + afnd.getTransicionesλ().get(i).getDestinos()[j]);
                }
            }
            fich.write("\nFIN");
        }
        fich.close();
    }
}
