/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author wadad
 */
public class Transicion {

    private int inicio;
    private int destino = -1;
    private int[] destinos;
    private final char simbolo;

    /**
     * Constructor transicion determinista
     *
     * @param inicio estado inicial de la transicion
     * @param destino estado final de la transicion
     * @param simbolo simbolo de la transición
     */
    public Transicion(int inicio, int destino, char simbolo) {
        this.inicio = inicio;
        this.destino = destino;
        this.simbolo = simbolo;
    }

    /**
     * constructor transicion no determinista
     *
     * @param inicio estado inicial de la transicion
     * @param destinos estados finales de la transicion
     * @param simbolo simbolo de la transición
     */
    public Transicion(int inicio, int[] destinos, char simbolo) {
        this.inicio = inicio;
        this.destinos = destinos;
        this.simbolo = simbolo;
    }

    /**
     * constructor transicion lambda
     *
     * @param inicio estado inicial de la transicion
     * @param destinos estados finales de la transicion
     */
    public Transicion(int inicio, int[] destinos) {
        this.inicio = inicio;
        this.destinos = destinos;
        this.simbolo = '#';

    }

    /**
     * devuelve el id del estado inicial de la transicion
     *
     * @return devuelve el id del estado inicial
     */
    public int getInicio() {
        return inicio;
    }

    /**
     * devuelve el id del estado final de la transicion
     *
     * @return devuelve el id del estado final
     */
    public int getDestino() {
        return destino;
    }

    /**
     * devuelve el id del estado inicial de la transicion
     *
     * @return devuelve los ides de los estados finales
     */
    public int[] getDestinos() {
        return destinos;
    }

    /**
     * devuelve el simbolo de la transicion
     *
     * @return devuelve el simbolo
     */
    public char getsimbolo() {
        return simbolo;
    }

    @Override
    public String toString() {
        String s = "Transicion{" + "inicio=" + inicio + ", destino=" + destino + ", simbolo=" + simbolo + '}';
        if (destinos != null) {
            for (int i = 0; i < destinos.length; i++) {
                s += destinos[i] + "----";
            }
        }

        return s;
    }

}
