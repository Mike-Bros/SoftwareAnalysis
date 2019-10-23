package domains.farmer;

import framework.graph.Graph;
import framework.graph.Vertex;

/**
 * A graph for the FWGC problem.
 * @author Your name and section here
 */
public class FarmerGraph extends Graph {
    
    public FarmerGraph() {
        /* you must provide */
        graph.addEdge(one, seven);
        graph.addEdge(seven, four);
        graph.addEdge(four, ten);
        graph.addEdge(four, eight);
        graph.addEdge(eight, five);
        graph.addEdge(ten, three);
        graph.addEdge(three, nine);
        graph.addEdge(five, nine);
        graph.addEdge(nine, two);
        graph.addEdge(two, six);
        
        graph.addEdge(seven, one);
        graph.addEdge(four, seven);
        graph.addEdge(ten, four);
        graph.addEdge(eight, four);
        graph.addEdge(five, eight);
        graph.addEdge(three, ten);
        graph.addEdge(nine, three);
        graph.addEdge(nine, five);
        graph.addEdge(two, nine);
        graph.addEdge(six, two);
        
    }
    
    public Vertex getStart() {
        /* you must provide */
        return one;
    }
    
    public Vertex getEnd() {
        /* you must provide */
        return six;
    }
    
    /* private fields and methods follow */
    private Graph graph = new Graph();
    
    private Vertex one = new Vertex(new FarmerState("West",
                                                    "West",
                                                    "West",
                                                    "West"));
    
    private Vertex two = new Vertex(new FarmerState("West",
                                                    "East",
                                                    "West",
                                                    "East"));
    
    private Vertex three = new Vertex(new FarmerState("West",
                                                    "East",
                                                    "West",
                                                    "West"));
    
    private Vertex four = new Vertex(new FarmerState("West",
                                                    "West",
                                                    "East",
                                                    "West"));
    
    private Vertex five = new Vertex(new FarmerState("West",
                                                    "West",
                                                    "West",
                                                    "East"));
    
    private Vertex six = new Vertex(new FarmerState("East",
                                                    "East",
                                                    "East",
                                                    "East"));
    
    private Vertex seven = new Vertex(new FarmerState("East",
                                                    "West",
                                                    "East",
                                                    "West"));
     
    private Vertex eight = new Vertex(new FarmerState("East",
                                                    "West",
                                                    "East",
                                                    "East")); 
    
    private Vertex nine = new Vertex(new FarmerState("East",
                                                    "East",
                                                    "West",
                                                    "East"));
    
    private Vertex ten = new Vertex(new FarmerState("East",
                                                    "East",
                                                    "East",
                                                    "West"));
}