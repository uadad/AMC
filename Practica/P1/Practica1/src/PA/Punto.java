/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PA;

/**
 *
 * @author wadad
 */
public class Punto {

    private double x;
    private double y;
    private int id;

    public Punto() {
        super();
    }

    public Punto(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Punto(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return id + "(" + x + "," + y + ")";
    }
}
