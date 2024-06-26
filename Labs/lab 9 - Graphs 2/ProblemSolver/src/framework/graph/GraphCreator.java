package framework.graph;

import framework.problem.Problem;
import framework.problem.State;
import framework.problem.State;
import java.util.List;
import java.util.Stack;

/**
 * @author Mike Bros
 */
public class GraphCreator {
    
    public Graph createGraphFor(Problem problem) {
        graph = new Graph();
        stack = new Stack<Vertex>();
        
        start = new Vertex(problem.getInitialState());
        stack.push(start);
        
        moves = problem.getMover().getMoveNames();
        
        while(!stack.empty()){
            Vertex u = stack.pop();
            for(int i = 0;i<moves.size();i++){
                State next = problem.getMover().doMove(moves.get(i),(State) u.getData());
                if(next != null){
                    Vertex v = new Vertex(next);
                    if(graph.find(v)!=null){
                        v =reclaim(v);
                    } else {
                        stack.push(v);
                    }
                    graph.addEdge(u, v);
                }
            }
        }
        
        return new Graph();
    }
    
    private Graph graph = null;
    private Stack<Vertex> stack = null;
    private Vertex start = null;
    private List<String> moves = null;

    private Vertex reclaim(Vertex v) {
        if(graph.getVertices().containsKey(v)){
            return graph.getVertices().get(v);
        }
        return null;
    }
//    
//    private Vertex convertToVertex(State s){
//        Vertex v = new Vertex(s);
//        return v;
//    }
    
}