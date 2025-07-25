/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PB;

import PA.Punto;
import java.util.ArrayList;

/**
 *
 * @author wadad
 */
public class Solucion {

    private double dis;
    private ArrayList<Punto> a;

    public Solucion(double dis, ArrayList<Punto> a) {
        this.dis = dis;
        this.a = a;
    }

    public double getDis() {
        return dis;
    }

    public ArrayList<Punto> getA() {
        return a;
    }

    public void setDis(double dis) {
        this.dis = dis;
    }

    public void setA(ArrayList<Punto> a) {
        this.a = a;
    }

}
