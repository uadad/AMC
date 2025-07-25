/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.swing.JFrame;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import javax.swing.JOptionPane;

/**
 *
 * @author wadad
 */
public class AFND implements Proceso {

    private int[] estadosFinales; //indica cuales son los estados Finales
    private List<Transicion> transiciones; //indica la lista de transiciones del AFND
    private List<Transicion> transicionesλ; //indica la lista de transiciones λ del AFND
    private List<Integer> estados; //indiva la lista de estados de las transiciones
    private JFrame frame;

    public AFND() {
        transiciones = new ArrayList<>();
        transicionesλ = new ArrayList<>();
        estados = new ArrayList<>();
        frame = new JFrame("Grafo AFND");
    }

    /**
     * metodo que añade una transición la grafo
     *
     * @param e1 numero del nodo inicial de la transición
     * @param simbolo simbolo de la transición
     * @param e2 numeros de los nodos finales de la transición
     */
    public void agregarTransicion(int e1, char simbolo, int[] e2) {
        Transicion tran = new Transicion(e1, e2, simbolo);
        transiciones.add(tran);
        añadeEstado(e1, e2);
    }

    /**
     * metodo que añade una transición lamda
     *
     * @param e1 numero del nodo inicial de la transición
     * @param e2 numeros de los nodos finales de la transición
     */
    public void agregarTransicionλ(int e1, int[] e2) {
        Transicion tran = new Transicion(e1, e2);
        transicionesλ.add(tran);
        añadeEstado(e1, e2);
    }

    /**
     * metodo que devuelve los estados finales de una transición pasandole su
     * estado inicial y su simbolo
     *
     * @param estado estado incicial de la transicion buscada
     * @param simbolo simbolo de la transición buscada
     * @return Devuelve el array de los nodos finales de la transición, si no
     * existe devuelve null
     */
    private int[] transicion(int estado, char simbolo) {

        int i = 0;
        while (i < transiciones.size()) {
            if (transiciones.get(i).getInicio() == estado && transiciones.get(i).getsimbolo() == simbolo) {
                return transiciones.get(i).getDestinos();
            }

            i++;
        }

        return null;
    }

    /**
     * metodo que devuelve los estados finales de una transición pasandole su
     * estado inicial y su simbolo
     *
     * @param macroestado estado incicial de la transicion buscada
     * @param simbolo simbolo de la transición buscada
     * @return Devuelve el array de los nodos finales de la transición, si no
     * existe devuelve null
     */
    public int[] transicion(int[] macroestado, char simbolo) {
        ArrayList<Integer> aux = new ArrayList<>();
        for (int i = 0; i < macroestado.length; i++) {
            int[] destinos = transicion(macroestado[i], simbolo);
            if (destinos != null) {
                for (int j = 0; j < destinos.length; j++) {
                    if (!aux.contains(destinos[j])) {
                        aux.add(destinos[j]);
                    }
                }
            }
        }
        int[] devolver = new int[aux.size()];
        for (int i = 0; i < aux.size(); i++) {
            devolver[i] = aux.get(i);
        }
        return devolver;
    }

    public int[] transicionλ(int estado) {
        int i = 0;
        while (i < transicionesλ.size()) {
            if (transicionesλ.get(i).getInicio() == estado) {
                return transicionesλ.get(i).getDestinos();
            }
            i++;
        }

        return null;
    }

    /**
     * metodo que comprueba si un estado es nodo final
     *
     * @param estado estado del que se desea saber si es final o inicial
     * @return Devuelve verdadero si es final y falso si no lo es
     */
    @Override
    public boolean esFinal(int estado) {
        int i = 0;
        if (estadosFinales != null) {
            while (i < estadosFinales.length) {
                if (estadosFinales[i] == estado) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    /**
     * metodo que comprueba si un macroestado es nodo final
     *
     * @param macroestado macroestado del que se desea saber si es final o
     * inicial
     * @return Devuelve verdadero si es final y falso si no lo es
     */
    public boolean esFinal(int[] macroestado) {
        boolean aux = false;
        for (int j = 0; j < macroestado.length; j++) {
            aux = aux || esFinal(macroestado[j]);
        }
        return aux;
    }

    public int[] λ_clausura(ArrayList<Integer> macroestado) {
        ArrayList<Integer> aux = new ArrayList<>();
        for (int i = 0; i < macroestado.size(); i++) {
            aux.add(macroestado.get(i));
        }

        for (int i = 0; i < macroestado.size(); i++) {
            int[] destinos = transicionλ(macroestado.get(i));
            if (destinos != null) {
                for (int j = 0; j < destinos.length; j++) {
                    if (!aux.contains(destinos[j])) {
                        aux.add(destinos[j]);
                    }
                    if (!macroestado.contains(destinos[j])) {
                        macroestado.add(destinos[j]);
                    }
                }
            }
        }

        int[] devolver = new int[aux.size()];
        for (int i = 0; i < aux.size(); i++) {
            devolver[i] = aux.get(i);
        }
        return devolver;
    }

    /**
     * metodo que comprueba si una cadena se encuentra dentro del grafo
     *
     * @param cadena cadena que se desea comprobar
     * @return Devuelve verdadero si existe en el grafo y falso si no existe
     */
    @Override
    @SuppressWarnings("empty-statement")
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        ArrayList<Integer> estado = new ArrayList<>(); //El estado inicial es el 0
        estado.add(0);
        int[] macroestado = λ_clausura(estado);
        for (int i = 0; i < simbolo.length; i++) {
            macroestado = transicion(macroestado, simbolo[i]);
        }
        ArrayList<Integer> devolver = new ArrayList<>(macroestado.length);
        for (int i = 0; i < macroestado.length; i++) {
            devolver.add(macroestado[i]);
        }
        return esFinal(λ_clausura(devolver));
    }

    @Override
    public String toString() {
        String s = "";

        s += "\ntransiciones";
        for (int i = 0; i < transiciones.size(); i++) {
            s += transiciones.get(i).toString() + "----";
        }
        s += "\ntransiciones lambda: ";
        for (int i = 0; i < transicionesλ.size(); i++) {
            s += transicionesλ.get(i).toString() + "----";
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

        // Add vertices
        for (int i = 0; i < transiciones.size(); i++) {
            String v1 = "q" + transiciones.get(i).getInicio();
            graph.addVertex(v1);

            int[] s = transiciones.get(i).getDestinos();
            for (int j = 0; j < s.length; j++) {
                String v2 = "q" + s[j];
                graph.addVertex(v2);
                graph.addEdge("[ " + v1 + "-" + v2 + "] " + transiciones.get(i).getsimbolo(), v1, v2, EdgeType.DIRECTED);
            }
        }

        for (int i = 0; i < transicionesλ.size(); i++) {
            String v1 = "q" + transicionesλ.get(i).getInicio();
            graph.addVertex(v1);

            int[] s = transicionesλ.get(i).getDestinos();
            for (int j = 0; j < s.length; j++) {
                String v2 = "q" + s[j];
                graph.addVertex(v2);
                graph.addEdge("[ " + v1 + "-" + v2 + "] " + "λ", v1, v2, EdgeType.DIRECTED);
            }
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
         int c;
        // Add vertices
        System.out.println("C: " + Contador + " T: " + transiciones.size());
        if (Contador > transiciones.size()  &&  Contador > transicionesλ.size()) {
            JOptionPane.showMessageDialog(null, "Ya no existe mas estados.");
        }else{
            if (Contador > transiciones.size()) {
                c = transiciones.size();
            }
            else{ 
                c=Contador;
            }
            for (int i = 0; i < c; i++) {
                String v1 = "q" + transiciones.get(i).getInicio();
                graph.addVertex(v1);

                int[] s = transiciones.get(i).getDestinos();
                for (int j = 0; j < s.length; j++) {
                    String v2 = "q" + s[j];
                    graph.addVertex(v2);
                    graph.addEdge("[ " + v1 + "-" + v2 + "] " + transiciones.get(i).getsimbolo(), v1, v2, EdgeType.DIRECTED);
                }
            }
          
            System.out.println("C: " + Contador + " T-: " + transicionesλ.size());
            if (Contador > transicionesλ.size()) {
                Contador = transicionesλ.size();
            }
            System.out.println("C: " + Contador + " T-: " + transicionesλ.size());
            for (int i = 0; i < Contador; i++) {
                String v1 = "q" + transicionesλ.get(i).getInicio();
                graph.addVertex(v1);

                int[] s = transicionesλ.get(i).getDestinos();
                for (int j = 0; j < s.length; j++) {
                    String v2 = "q" + s[j];
                    graph.addVertex(v2);
                    graph.addEdge("[ " + v1 + "-" + v2 + "] " + "λ", v1, v2, EdgeType.DIRECTED);
                }

            }
            dibujarGrafo(graph);
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

        // Create a JFrame to display the graph
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv, BorderLayout.CENTER);
        frame.pack();  //frame.setSize(x, y);
        frame.setVisible(true);
    }

    /**
     * metodo que devuelve los ids de los estados finales del grafo
     *
     * @return Devuelve un ArrayList con los ids de los estados finales del
     * grafo
     */
    public int[] getEstadosFinales() {
        return estadosFinales;
    }

    /**
     * metodo que devuelve las transiciones del grafo
     *
     * @return Devuelve un List con las transiciones del grafo
     */
    public List<Transicion> getTransiciones() {
        return transiciones;
    }

    /**
     * metodo que devuelve las transiciones lamda del grafo
     *
     * @return Devuelve un List con las transiciones lamda del grafo
     */
    public List<Transicion> getTransicionesλ() {
        return transicionesλ;
    }

    /**
     * metodo que añade estados al array de estados. Compruba si existe y si no
     * existe los añade
     *
     * @param e1 id del estado que se añade
     * @param e2 ids de los estados que se añade
     */
    private void añadeEstado(int e1, int[] e2) {
        if (!estados.contains(e1)) {
            estados.add(e1);
        }
        for (int i = 0; i < e2.length; i++) {
            if (!estados.contains(e2[i])) {
                estados.add(e2[i]);
            }
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

    public void setEstadosFinales(int[] estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

}
