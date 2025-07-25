/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PA;

/**
 *
 * @author usuario
 */
public class Parpuntos {

    private Punto p1;
    private Punto p2;
    private double distancia;
    private int calculadas = 0;

    public Parpuntos() {
        super();
    }

    public Parpuntos(Punto p1, Punto p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
        CalcularDistancia();
        this.calculadas = 0;
    }

    public int getCalculadas() {
        return calculadas;
    }

    public void setCalculadas(int calculadas) {
        this.calculadas = calculadas;
    }

    public void setLinea(Punto p1, Punto p2) {
        this.p1 = p1;
        this.p2 = p2;
        CalcularDistancia();
    }

    public Punto getP1() {
        return this.p1;
    }

    public void setP1(Punto p1) {
        this.p1 = p1;
        CalcularDistancia();
    }

    public Punto getP2() {
        return this.p2;
    }

    public void setP2(Punto p2) {
        this.p2 = p2;
        CalcularDistancia();
    }

    public double distancia() {
        return distancia;
    }

    public void setDistancia(double d) {
        distancia = d;
    }

    public void CalcularDistancia() {
        distancia = Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));

    }

    @Override
    public String toString() {
        return "Parpuntos [p1=" + p1 + ", p2=" + p2 + "]";
    }
}
