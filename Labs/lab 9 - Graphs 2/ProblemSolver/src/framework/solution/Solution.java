package framework.solution;

import framework.graph.Vertex;
import framework.problem.State;
import framework.graph.Graph;
import framework.graph.GraphSearcher;
import java.util.Stack;

public class Solution {

    /**
     * Creates an object that represents a path from a start
     * vertex to an end vertex in a problem solving domain.
     * This constructor will be called after a search has been
     * initiated on the start vertex.
     * If a path from start to end exists, it can be constructed
     * from the predecessor information stored in the end vertex.
     * @param start the start vertex
     * @param end the end vertex
     */
    public Solution(Vertex start, Vertex end) {
        /* you must provide */
        while(start!=end){
            stack.push(end);
            end = end.getPredecessor();
        }
        stack.push(start);
        
    }

    /**
     * Gets the length of the solution, that is, the number of moves
     * to get to the end state from the start.
     * @return the solution length
     */
    public int getLength() {
        /* you must provide */
        return stack.size();
    }
    
    /**
     * Checks whether there are more vertices in this solution.
     * @return true if there are more vertices in this solution, false
     * otherwise
     */
    public boolean hasNext() {
        /* you must provide */
        return !stack.empty();
    }
    
    /**
     * Removes and returns the next vertex in this solution.
     * @return the next vertex in this solution
     * @throws RuntimeException if there are no more vertices
     */
    public Vertex next() {
        /* you must provide */
        if(!hasNext()){
            throw new RuntimeException("No more vertices");
        }
        return stack.pop();
    }
    
    /* private instance fields and methods here */
    private Stack<Vertex> stack = new Stack<>();
}