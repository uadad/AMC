/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.util.function.Function;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import javax.swing.JOptionPane;

/**
 *
 * @author wadad
 */
public class AFD implements Proceso {

    private int estadoinicial;
    private ArrayList<Integer> estadosFinales; //indica cuales son los estados Finales
    private List<Transicion> transiciones; //indica la lista de transiciones del AFD
    private List<Integer> estados;
    private JFrame frame;

    public AFD() {
        estadosFinales = new ArrayList<>();
        estadoinicial = 0;
        transiciones = new ArrayList<>();
        estados = new ArrayList<>();
        frame = new JFrame("Grafo AFD");
    }

    /**
     * metodo que añade una transición la grafo
     *
     * @param e1 numero del nodo inicial de la transición
     * @param simbolo simbolo de la transición
     * @param e2 numero del nodo final de la transición
     */
    public void agregarTransicion(int e1, char simbolo, int e2) {
        Transicion tran = new Transicion(e1, e2, simbolo);
        boolean encontrado = false;
        int i = 0;
        while (i < transiciones.size() && encontrado == false) {
            if (transiciones.get(i).getInicio() == e1 && transiciones.get(i).getDestino() == e2 && transiciones.get(i).getsimbolo() == simbolo) {
                encontrado = true;
            }

            i++;
        }
        if (!encontrado) {
            añadeEstado(e1, e2);
            transiciones.add(tran);
        } else {
            System.out.println("Dicha transicion existe.");
        }
    }

    /**
     * metodo que devuelve el estado final de una transición pasandole su estado
     * inicial y su simbolo
     *
     * @param estado estado incicial de la transicion buscada
     * @param simbolo simbolo de la transición buscada
     * @return Devuelve el id del nodo final de la transición, si no existe
     * devuelve -1
     */
    public int transicion(int estado, char simbolo) {
        int estadof = -1;
        int i = 0;
        while (i < transiciones.size() && estadof == -1) {
            if (transiciones.get(i).getInicio() == estado && transiciones.get(i).getsimbolo() == simbolo) {
                estadof = transiciones.get(i).getDestino();
            }

            i++;
        }

        return estadof;
    }

    /**
     * metodo que comprueba si un estado es nodo final
     *
     * @param estado estado del que se desea saber si es final o inicial
     * @return Devuelve verdadero si es final y falso si no lo es
     */
    @Override
    public boolean esFinal(int estado) {
        return estadosFinales.contains(estado);
    }

    /**
     * metodo que comprueba si una cadena se encuentra dentro del grafo
     *
     * @param cadena cadena que se desea comprobar
     * @return Devuelve verdadero si existe en el grafo y falso si no existe
     */
    @Override
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        int estado = estadoinicial;
        for (int i = 0; i < simbolo.length; i++) {
            estado = transicion(estado, simbolo[i]);
        }
        return esFinal(estado);
    }

    /**
     * metodo que añade un estado final
     *
     * @param parseInt estado final que se añade
     */
    public void añadirEstadoFinal(int parseInt) {
        estadosFinales.add(parseInt);
    }

    /**
     * metodo que cambia el estado final del grafo
     *
     * @param ei estado que se desea poner como inicial
     */
    public void setEstadoInicial(int ei) {
        estadoinicial = ei;
    }

    @Override
    public String toString() {
        String s = "";
        s += "AFD{" + "estadoinicial=" + estadoinicial + ", estadosFinales= ";
        for (int i = 0; i < estadosFinales.size(); i++) {
            s += estadosFinales.get(i) + "---";
        }
        s += "\ntransiciones";
        for (int i = 0; i < transiciones.size(); i++) {
            s += transiciones.get(i) + "----";
        }
        return s;
    }

    /**
     * metodo que pinta el grafico
     *
     * @throws java.lang.InterruptedException
     */
    public void dibujar() throws InterruptedException {
        DirectedSparseMultigraph<String, String> graph = new DirectedSparseMultigraph<>();

        for (int i = 0; i < transiciones.size(); i++) {
            String v1 = "q" + transiciones.get(i).getInicio();
            String v2 = "q" + transiciones.get(i).getDestino();
            graph.addVertex(v1);
            graph.addVertex(v2);
            graph.addEdge("[ " + v1 + "-" + v2 + " ] " + transiciones.get(i).getsimbolo(), v1, v2, EdgeType.DIRECTED);
        }

        dibujarGrafo(graph);

    }

    /**
     * metodo que dibuja paso a paso el grafo
     *
     * @param Contador
     */
    public void dibujarPasoaPaso(int Contador) {
        DirectedSparseMultigraph<String, String> graph = new DirectedSparseMultigraph<>();

        // Add vertices
        if (Contador <= transiciones.size()) {
            for (int i = 0; i < Contador; i++) {
                String v1 = "q" + transiciones.get(i).getInicio();
                graph.addVertex(v1);
                String v2 = "q" + transiciones.get(i).getDestino();
                graph.addVertex(v2);
                graph.addEdge("[ " + v1 + "-" + v2 + "] " + transiciones.get(i).getsimbolo(), v1, v2, EdgeType.DIRECTED);

            }

            dibujarGrafo(graph);
        } else {
            JOptionPane.showMessageDialog(null, "Ya no existe mas estados.");
        }
    }

    private void dibujarGrafo(DirectedSparseMultigraph<String, String> graph) {
        frame.dispose();
        // Create a visualization viewer with a layout
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout(graph));

        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

        // Set up the vertex shape and paint transformer using Java 8 Function
        Function<String, Paint> vertexPaint = vertex -> Color.yellow;

        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint::apply);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv, BorderLayout.CENTER);
        frame.pack();  //frame.setSize(x, y);
        frame.setVisible(true);
    }

    /**
     * metodo que devuelve el id del estado inicial del grafo
     *
     * @return Devuelve el id del estado inicial del grafo
     */
    public int getEstadoinicial() {
        return estadoinicial;
    }

    /**
     * metodo que devuelve los estados finales del grafo
     *
     * @return devuelve la lista de estados finales del grafo
     */
    public ArrayList<Integer> getEstadosFinales() {
        return estadosFinales;
    }

    /**
     * metodo que devuelve las transiciones del grafo
     *
     * @return Devuelve un ArrayList con las transiciones del grafo
     */
    public List<Transicion> getTransiciones() {
        return transiciones;
    }

    /**
     * metodo que añade estados al array de estados. Compruba si existe y si no
     * existe los añade
     *
     * @param e1 id del estado que se añade
     * @param e2 id del estado que se añade
     */
    private void añadeEstado(int e1, int e2) {
        if (!estados.contains(e1)) {
            estados.add(e1);
        }
        if (!estados.contains(e2)) {
            estados.add(e2);
        }
    }

    /**
     * metodo que devuelve los estados del grafo
     *
     * @return devuelve la lista de estados del grafo
     */
    public List<Integer> getEstados() {
        return estados;
    }

    public void setEstadoinicial(int estadoinicial) {
        this.estadoinicial = estadoinicial;
    }

    public void setEstadosFinales(ArrayList<Integer> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

}
