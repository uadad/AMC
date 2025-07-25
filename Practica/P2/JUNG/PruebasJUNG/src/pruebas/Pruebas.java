/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

/**
 *
 * @author USER
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create a directed graph
        DirectedSparseGraph<String, String> graph = new DirectedSparseGraph<>();

        // Add vertices
        graph.addVertex("q0");
        graph.addVertex("q1");
        graph.addVertex("q2");

        // Add directed edges with labels
        graph.addEdge("Edge1", "q0", "q1", EdgeType.DIRECTED);
        graph.addEdge("Edge2", "q1", "q2", EdgeType.DIRECTED);
        graph.addEdge("Edge3", "q2", "q1", EdgeType.DIRECTED);
        graph.addEdge("Edge4", "q0", "q2", EdgeType.DIRECTED);

        // Create a visualization viewer with a layout
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout(graph));
        
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        
        
        // Set up the vertex shape and paint transformer using Java 8 Function
        Function<String, Paint> vertexPaint = vertex -> {
            // Customize the vertex color based on your logic
            /*if (vertex.equals("A")) {
                return Color.RED;
            } else if (vertex.equals("B")) {
                return Color.BLUE;
            } else {
                return Color.GREEN;
            }*/
            return Color.YELLOW;
        };

        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint::apply);

        // Create a JFrame to display the graph
        JFrame frame = new JFrame("Grafo de pruebas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv, BorderLayout.CENTER);
        frame.pack();  //frame.setSize(x, y);
        frame.setVisible(true);
    }
    
}
