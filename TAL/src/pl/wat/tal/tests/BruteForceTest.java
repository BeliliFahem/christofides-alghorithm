package pl.wat.tal.tests;

import junit.framework.Assert;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.junit.Test;
import pl.wat.tal.brute.BruteForce;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ignacy Sawicki
 */
public class BruteForceTest {
    @Test
    public void testPermute() throws Exception {
        BruteForce bruteForce = new BruteForce(new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class));
//        Method method = BruteForce.class.getDeclaredMethod("permute", String.class);
//        method.setAccessible(true);
        List<String> list = new LinkedList<String>();
        list.add("Lodz");
        list.add("Warszawa");
        list.add("Katowice");
        list.add("Berlin");
        list.add("Lodz");

        List result = new LinkedList<LinkedList<String>>();
        //bruteForce.permute(list, 1, result);
//        method.invoke(bruteForce, list);

        List<String> list1 = new LinkedList<String>();
        list.add("Lodz");
        list.add("Katowice");
        list.add("Warszawa");
        list.add("Berlin");
        list.add("Lodz");

        List<String> list2 = new LinkedList<String>();
        list.add("Lodz");
        list.add("Katowice");
        list.add("Berlin");
        list.add("Warszawa");
        list.add("Lodz");

        List<String> list3 = new LinkedList<String>();
        list.add("Lodz");
        list.add("Warszawa");
        list.add("Katowice");
        list.add("Berlin");
        list.add("Lodz");

        List<String> list4 = new LinkedList<String>();
        list.add("Lodz");
        list.add("Warszawa");
        list.add("Berlin");
        list.add("Katowice");
        list.add("Lodz");

        List<String> list5 = new LinkedList<String>();
        list.add("Lodz");
        list.add("Berlin");
        list.add("Katowice");
        list.add("Warszawa");
        list.add("Lodz");

        List<String> list6 = new LinkedList<String>();
        list.add("Lodz");
        list.add("Berlin");
        list.add("Warszawa");
        list.add("Katowice");
        list.add("Lodz");

        Assert.assertTrue(containsList(result, list1));
        Assert.assertTrue(containsList(result, list2));
        Assert.assertTrue(containsList(result, list3));
        Assert.assertTrue(containsList(result, list4));
        Assert.assertTrue(containsList(result, list5));
        Assert.assertTrue(containsList(result, list6));
    }

    /**
     * @param haystack
     * @param needle
     * @return
     */
    private boolean containsList(List<List> haystack, List<String> needle) {
        for (List list : haystack) {
            if (list.equals(needle)) {
                return true;
            }
        }
        return false;
    }
}
