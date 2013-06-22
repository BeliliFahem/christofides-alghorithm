package pl.wat.tal.brute;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import pl.wat.tal.common.Algorithm;
import pl.wat.tal.misc.TSPResult;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author k37
 */

public class BruteForce implements Algorithm {
    private long bestDistance;
    private List<String> bestRoute;

    public BruteForce() {
        bestDistance = 0;
    }

    /**
     * Metoda oblicza najkrotsza rozwiazanie problemu komiwojazera algorytmem brute force
     *
     * @param startVertex poczatek trasy
     * @param graf
     * @return droga + obliczona odleglosc
     */

    @Override
    public TSPResult findSolution(String startVertex,
                                  SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {
        TSPResult result = new TSPResult();
        List<String> route = createFirstRoute(startVertex, graph);

        List<List<String>> permutations = new LinkedList<List<String>>();  // inicjalizacja listy list dla permutacji

        permute(route, 1, permutations); // znalezienie wszystkich permutacji

        Iterator<List<String>> i = permutations.iterator();

        List<String> permutation = i.next();
        bestDistance = countDistance(permutation, graph); // pierwsza odleglosc
        bestRoute = permutation;  // pierwsza droga

        while (i.hasNext()) {
            long distance;
            permutation = i.next();
            distance = countDistance(permutation, graph);
            if (bestDistance > distance) {
                bestDistance = distance;
                bestRoute = permutation;
            }
        }

        result.setDistance(bestDistance);
        result.setRoute(bestRoute);

        return result;
    }

    /**
     * Metoda tworzy pierwsza przykladowa droge
     *
     * @param start poczatek trasy
     * @param graph graf
     * @return lista wierzcholkow drogi
     * @author k37
     */

    private List<String> createFirstRoute(String start, WeightedGraph<String, AdvancedWeightedEdge> graph) {
        List<String> route = new LinkedList<String>();

        route.add(start);
        Iterator<String> i = new DepthFirstIterator<String, AdvancedWeightedEdge>(graph);
        while (i.hasNext()) {
            String vertex = i.next();
            if (!vertex.equals(start)) {  // musi byc rozny od startu
                route.add(vertex);  // dodanie kolejnego wierzcholka do drogi
            }
        }
        route.add(start);

        return route;
    }

    /**
     * Metoda zwraca wszystkie permutacje zbioru. Zachowuje kolejnosc pierwszego i dwoch ostatnich elementow.
     *
     * @param route        lista z przykladowa droga
     * @param index        aktualny element permutacji
     * @param permutations lista zwrotna
     * @author k37
     */

    private void permute(List<String> route, int index, List<List<String>> permutations) {
        if (index >= route.size() - 1) {  // koniec permutacji
            permutations.add(new LinkedList<String>(route));  // aby dodac nowy obiekt, a nie REFERENCJE!
            System.out.println(route.toString());  // DEBUG
        } else {
            // puszczenie elemntu bez permutacji
            permute(route, index + 1, permutations);

            String element = route.get(index);
            for (int i = index + 1; i < route.size() - 1; i++) {
                // zamiana elemntow na pozycjach index oraz i
                route.set(index, route.get(i));
                route.set(i, element);

                // rekurencja
                permute(route, index + 1, permutations);

                // powrot do poprzedniej postaci
                route.set(i, route.get(index));
                route.set(index, element);
            }
        }

    }

    /**
     * Metoda obliczajaca dlugosc drogi
     *
     * @param route lista zawierajaca droge
     * @param graph graf
     * @return dlugosc drogi
     * @author k37
     */

    private long countDistance(List<String> route, WeightedGraph<String, AdvancedWeightedEdge> graph) {
        long result = 0;
        String from = new String();
        String to = new String();

        Iterator<String> i = route.iterator();
        from = i.next();  // pierwszy wierzcholek
        to = i.next();  // pierwsze odwiedzane miasto

        while (i.hasNext()) {
            result = (long) (result + graph.getEdgeWeight(graph.getEdge(from, to)));  // dodanie wagi obecnej krawedzi
            from = to;  // zamiana koncowego na poczatkowy
            to = i.next();  // pobranie kolejnego wierzcholka
        }

        result = (long) (result + graph.getEdgeWeight(graph.getEdge(from, to)));  // ostatni wierzcholek poza petla
        return result;
    }
}
