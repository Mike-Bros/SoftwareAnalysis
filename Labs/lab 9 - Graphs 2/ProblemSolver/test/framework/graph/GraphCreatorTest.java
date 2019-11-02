package framework.graph;

import domains.farmer.FarmerProblem;
import domains.puzzle.PuzzleProblem;
import framework.problem.Problem;
import framework.solution.Solution;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author tcolburn
 */
public class GraphCreatorTest {
    
    public GraphCreatorTest() {
        creator = new GraphCreator();
    }

    @Test
    public void createFarmerGraph() {
        numVertices = 10;
        numEdges = 20;
        solutionLength = 7;
        createAndTest(new FarmerProblem(), numVertices, numEdges, solutionLength);
    }

    @Test
    public void createPuzzleGraph() {
        numVertices = 181440;
        numEdges = 483840;
        solutionLength = 5;
        createAndTest(new PuzzleProblem(), numVertices, numEdges, solutionLength);
    }
    
    private void createAndTest(Problem problem, int numVertices, int numEdges, int solutionLength) {
        graph = creator.createGraphFor(problem);
        assertTrue(graph.getNumVertices() == numVertices);
        assertTrue(graph.getNumEdges() == numEdges);
        
        searcher = new GraphSearcher(graph);
        start = graph.find(new Vertex(problem.getInitialState()));
        end = graph.find(new Vertex(problem.getFinalState()));
        
        searcher.breadthFirstSearch(start);
        solution = new Solution(start, end);
        assertTrue(solution.getLength() == solutionLength);
        
        searcher.depthFirstSearch(start);
        solution = new Solution(start, end);
        System.out.println ("DFS solution length for " + problem.getName() + " problem: " + solution.getLength() + "\n");
    }
    
    private GraphCreator creator;
    private Graph graph;
    private int numVertices;
    private int numEdges;
    private int solutionLength;
    private GraphSearcher searcher;
    private Vertex start;
    private Vertex end;
    private Solution solution;
}