package pl.wat.tal.tests;

import junit.framework.TestCase;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;
import pl.wat.tal.main.Solution;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ignacy Sawicki
 * Date: 30.05.13
 * Time: 22:51
 */
public class SolutionTest extends TestCase {
    @Test
    public void testGetSolution() throws Exception {

        List<String> solution = new LinkedList<String>();

        solution.add("Lodz");
        solution.add("Warszawa");
        solution.add("Katowice");
        solution.add("Berlin");
        solution.add("Lodz");

        Solution sol = new Solution();
        List<String> result = sol.getSolution(new SimpleGraph<String, DefaultEdge>(DefaultEdge.class));

        assertEquals(solution, result);
    }
}
