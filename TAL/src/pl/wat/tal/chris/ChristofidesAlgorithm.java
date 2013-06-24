package pl.wat.tal.chris;

import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.AdvancedWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.traverse.DepthFirstIterator;
import pl.wat.tal.common.AbstractAlgorithm;
import pl.wat.tal.common.Algorithm;
import pl.wat.tal.misc.TSPResult;

import java.util.*;

/**
 * Klasa ChristofidesAlgorithm
 *
 * @author Ignacy Sawicki <igesef@gmail.com>
 * @version 1.0 01.06.13
 */
public class ChristofidesAlgorithm extends AbstractAlgorithm implements Algorithm {

    @Override
    public TSPResult findSolution(String startVertex, SimpleWeightedGraph<String, AdvancedWeightedEdge> graph) {

        // Znalezienie minimalnego drzewa spinającego mst grafu G - algorytm Kruskala
        SimpleWeightedGraph<String, AdvancedWeightedEdge> mst = findMinimumSpanningTree(graph);
        // Znalezienie zbioru Vodd wierzchołków nieparzystego stopnia w drzewie mst
        Set<String> vodd = findOddDegreeVertices(mst);
//        // Znalezienie w zbiorze Vodd minimalnych skojarzeń dokładnych M0odd
//        List<List<String>> M0odd = findMinimumWeightMatching(graph, vodd);

        SimpleWeightedGraph<String, AdvancedWeightedEdge> minimumMatchingGraph = greedyMatch(vodd, graph);
        WeightedMultigraph<String, AdvancedWeightedEdge> combinedGraph = combineGraphs(mst, minimumMatchingGraph);
        List<String> eulerianCycle = findEulerianCycle(combinedGraph);
        List<String> tspSolution = findHamiltonianCycle(eulerianCycle, graph, startVertex);

        TSPResult tspResult = new TSPResult();
        tspResult.setRoute(tspSolution);
        tspResult.setDistance(countDistance(tspSolution, graph));

        return tspResult;
    }

    /**
     * Znajduje cykl hamiltona
     *
     * @param eulerianCycle
     * @param graph
     * @param startVertex
     * @return
     */
    private List<String> findHamiltonianCycle(List<String> eulerianCycle, SimpleWeightedGraph<String,
            AdvancedWeightedEdge> graph, String startVertex) {

        // Usuwamy ostatni wierzcholek z cyklu eulera, i tak dlugo jak pierwszym wierzcholkiem cyklu nie bedzie nasz
        // wierzcholek startowy, przestawiamy kolejnosc wierzcholkow

        eulerianCycle.remove(eulerianCycle.size() - 1);
        List<String> eulerianCycleCopy = new LinkedList<String>(eulerianCycle);
        Iterator<String> iterator = eulerianCycleCopy.iterator();
        while (!eulerianCycle.get(0).equals(startVertex)) {
            String current = eulerianCycle.get(0);
            eulerianCycle.remove(current);
            eulerianCycle.add(current);
        }
//        while (iterator.hasNext()) {
//            String current = iterator.next();
//            if (current.equals(startVertex)) {
//                break;
//            } else {
//                eulerianCycle.remove(current);
//                eulerianCycle.add(current);
//            }
//        }

        List<String> tspSolution = new LinkedList<String>();
        // Przechodzimy po wszystkich wierzcholkach z cyklu eulera
        for (String vertex : eulerianCycle) {
            // jeśli jakis wierzcholek jest juz na liscie przejrzanych, omijamy go i idziemy do nastepnego
            if (!tspSolution.contains(vertex)) {
                // a jak go nie ma, to dodajemy go do listy przejrzanych
                tspSolution.add(vertex);
            }
        }

        // dodajemy jeszcze nasz poczatkowy wierzcholek na koniec
        tspSolution.add(startVertex);

        return tspSolution;
    }

    /**
     * Łączy ze sobą dwa grafy, z czego lista wierzchołków pochodzi tylko z pierwszego
     *
     * @param graph1
     * @param graph2
     * @return
     */
    private WeightedMultigraph<String, AdvancedWeightedEdge> combineGraphs(
            WeightedGraph<String, AdvancedWeightedEdge> graph1,
            WeightedGraph<String, AdvancedWeightedEdge> graph2
    ) {
        // Tworzymy nowy graf
        WeightedMultigraph<String, AdvancedWeightedEdge> combinedGraph = new WeightedMultigraph<String,
                AdvancedWeightedEdge>(AdvancedWeightedEdge.class);
        // kopiujemy wszystkie wierzcholki z pierwszego
        for (String vertex : graph1.vertexSet()) {
            combinedGraph.addVertex(vertex);
        }
        // oraz wszystkie krawedzie
        for (AdvancedWeightedEdge edge : graph1.edgeSet()) {
            combinedGraph.addEdge((String) edge.getSourceVertex(), (String) edge.getTargetVertex(), edge);
        }
        // A teraz wszystkie krawedzie z drugiego grafu (mogą się powtarzać)
        for (AdvancedWeightedEdge edge : graph2.edgeSet()) {
            combinedGraph.addEdge((String) edge.getSourceVertex(), (String) edge.getTargetVertex(), edge);
        }

        // Zwracamy polaczony graf
        return combinedGraph;
    }

    /**
     * @param graph Graf dla ktorego szukamy drzewa mst
     * @return Minimalne drzewo spinające
     */
    private SimpleWeightedGraph<String, AdvancedWeightedEdge> findMinimumSpanningTree(WeightedGraph<String, AdvancedWeightedEdge> graph) {
        KruskalMinimumSpanningTree<String, AdvancedWeightedEdge> kruskal = new KruskalMinimumSpanningTree<String, AdvancedWeightedEdge>(graph);

        Set<AdvancedWeightedEdge> mstEdges = kruskal.getEdgeSet();

        SimpleWeightedGraph<String, AdvancedWeightedEdge> mst = new SimpleWeightedGraph<String,
                AdvancedWeightedEdge>(AdvancedWeightedEdge.class);

        // Dodajemy wszystkie wierzcholki z poprzedniego grafu
        for (String vertex : graph.vertexSet()) {
            mst.addVertex(vertex);
        }

        // Dodajemy wszystkie kraweędzie znalezione przez algorytm kruskala
        for (AdvancedWeightedEdge edge : mstEdges) {
            mst.addEdge((String) edge.getSourceVertex(), (String) edge.getTargetVertex(), edge);
        }

        return mst;
    }

    /**
     * Znajduje zbiór krawędzi nieparzystego stopnia (Vodd) dla podanego grafu
     *
     * @param mst Graf dla którego szukamy zbioru krawędzi minimalnego stopnia
     * @return
     */
    private Set<String> findOddDegreeVertices(SimpleWeightedGraph<String, AdvancedWeightedEdge> mst) {
        Set<String> set = new HashSet<String>();

        for (String vertex : mst.vertexSet()) {
            if ((mst.degreeOf(vertex) % 2) != 0) {
                set.add(vertex);
            }
        }
        return set;
    }

    /**
     * @param graph
     * @param vodd
     * @return
     */
    private List<List<String>> findMinimumWeightMatching(
            SimpleWeightedGraph<String, AdvancedWeightedEdge> graph,
            Set<String> vodd
    ) {

        List<List<String>> list = new LinkedList<List<String>>();

        // Dla każdego wierzchołka w zbiorze
        for (String startVertex : vodd) {
            DepthFirstIterator<String, AdvancedWeightedEdge> i = new DepthFirstIterator<String, AdvancedWeightedEdge>(graph,
                    startVertex);
            while (i.hasNext()) {
                String current = i.next();
                int ojej = 0;
                ojej++;
            }
        }

        return list;
    }

    /**
     * Znajdowanie cyklu eulera
     *
     * @param graph
     * @return
     */
    private List<String> findEulerianCycle(WeightedMultigraph<String, AdvancedWeightedEdge> graph) {

        List<String> list = EulerianCircuit.getEulerianCircuitVertices(graph);

        return list;
    }

    private SimpleWeightedGraph<String, AdvancedWeightedEdge> greedyMatch(Set<String> voddVertices,
                                                                          SimpleWeightedGraph<String,
                                                                                  AdvancedWeightedEdge> graph) {
        LinkedList<AdvancedWeightedEdge> matches = new LinkedList<AdvancedWeightedEdge>();

        Set<String> vertices = new HashSet<String>();
        for (String vertex : graph.vertexSet()) {
            vertices.add(vertex);
        }

        //HashMap<String, LinkedList<AdvancedWeightedEdge>> adjList = g.getAdjList();
        int numberOfMatches = voddVertices.size() / 2;

        HashMap<String, LinkedList<AdvancedWeightedEdge>> trash = new HashMap<String,
                LinkedList<AdvancedWeightedEdge>>();
        // Lecimy po wszystkich wierzcholkach
        for (String vertex : vertices) {

            Set<AdvancedWeightedEdge> edges = graph.edgesOf(vertex);
            outer:
            for (AdvancedWeightedEdge e : edges) {

                // wrzucamy wierzcholki krawedzi do listy wierzcholkow
                LinkedList<String> verticesOfEdge = new LinkedList<String>();
                verticesOfEdge.add((String) e.getSourceVertex());
                verticesOfEdge.add((String) e.getTargetVertex());

                for (String vertexOfEdge : verticesOfEdge) {

                    if (!voddVertices.contains(vertexOfEdge)) {
                        // zapisujemy do wywalenia
                        if (trash.get(vertexOfEdge) == null) {
                            trash.put(vertexOfEdge, new LinkedList<AdvancedWeightedEdge>());
                        }
                        trash.get(vertexOfEdge).add(e);
                        continue outer;
                    }
                }
            }
        }

        // Usuwamy wszystkie znalezione krawędzie
        Iterator iterator = trash.entrySet().iterator();
        Set<AdvancedWeightedEdge> edges = new HashSet<AdvancedWeightedEdge>();

        for (AdvancedWeightedEdge e : graph.edgeSet()) {
            edges.add(e);
        }

        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            String key = (String) (pair.getKey());
            LinkedList<AdvancedWeightedEdge> edgeList = (LinkedList<AdvancedWeightedEdge>) (pair.getValue());

            List<AdvancedWeightedEdge> toRemove = new LinkedList<AdvancedWeightedEdge>();

            if (!voddVertices.contains(key)) {
                // Usuwamy wierzcholek
                voddVertices.remove(key);
                // I wszystkie z nim krawedzie
                for (AdvancedWeightedEdge e : edgeList) {
                    if (e.getSourceVertex().equals(key) || e.getTargetVertex().equals(key)) {
                        toRemove.add(e);
                    }
                }
            } else {
                for (AdvancedWeightedEdge e : edgeList) {
                    if (e.getSourceVertex().equals(key) || e.getTargetVertex().equals(key)) {
                        toRemove.add(e);
                    }
                }
            }

            for (AdvancedWeightedEdge edge : toRemove) {
                edgeList.remove(edge);
            }
        }


        // tak dlugo, jak mamy mniej krawedzi od spodziewanej liczby dopasowan
        while (matches.size() < numberOfMatches) {
            // znajdujemy krawedz o najnizszej wadze

            // Tworzymy nowy graf z nowymi wierzcholkami i krawedziami
            SimpleWeightedGraph<String, AdvancedWeightedEdge> newGraph =
                    new SimpleWeightedGraph<String, AdvancedWeightedEdge>(AdvancedWeightedEdge.class);

            for (String v : vertices) {
                newGraph.addVertex(v);
            }

            for (AdvancedWeightedEdge edge : edges) {
                newGraph.addEdge((String) edge.getSourceVertex(), (String) edge.getTargetVertex(), edge);
            }

            AdvancedWeightedEdge emin = new AdvancedWeightedEdge();
            emin.setWeight(Double.MAX_VALUE);

            for (String vertex : vertices) {

                Set<AdvancedWeightedEdge> edgesOfVertex = newGraph.edgesOf(vertex);
                outer:
                for (AdvancedWeightedEdge e : edgesOfVertex) {
                    // ignore edge if it is not incident on any of the given vertices

                    // wrzucamy wierzcholki krawedzi do listy wierzcholkow
                    LinkedList<String> verticesOfEdge = new LinkedList<String>();
                    verticesOfEdge.add((String) e.getSourceVertex());
                    verticesOfEdge.add((String) e.getTargetVertex());

                    for (String vertexOfEdge : verticesOfEdge) {
                        if (!voddVertices.contains(vertexOfEdge)) {
                            continue outer;
                        }
                    }

                    if (e.getWeight() < emin.getWeight()) {
                        emin = e;
                    }
                }
            }

            // dodajemy krawedz do zwracanego grafu
            matches.add(emin);

            HashMap<String, LinkedList<AdvancedWeightedEdge>> deathRow = new HashMap<String,
                    LinkedList<AdvancedWeightedEdge>>();

            // usuwamy wszystkie krawędzie incydentne z wierzcholkami
            for (String vertex : vertices) {
                Set<AdvancedWeightedEdge> edgesOfVertex = newGraph.edgesOf(vertex);

                for (AdvancedWeightedEdge e : edgesOfVertex) {
                    // wrzucamy wierzcholki krawedzi do listy wierzcholkow
                    LinkedList<String> verticesOfMinEdge = new LinkedList<String>();
                    verticesOfMinEdge.add((String) emin.getSourceVertex());
                    verticesOfMinEdge.add((String) emin.getTargetVertex());

                    for (String v : verticesOfMinEdge) {
                        LinkedList<String> verticesOfEdge = new LinkedList<String>();
                        verticesOfEdge.add((String) e.getSourceVertex());
                        verticesOfEdge.add((String) e.getTargetVertex());

                        if (verticesOfEdge.contains(v)) {
                            // zapisujemy do usuniecia
                            if (deathRow.get(vertex) == null) {
                                deathRow.put(vertex, new LinkedList<AdvancedWeightedEdge>());
                            }
                            deathRow.get(vertex).add(e);
                        }
                    }
                }
            }

            // usuwamy wszystkie oznaczone krawedzie
            Iterator iterator2 = deathRow.entrySet().iterator();
            while (iterator2.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator2.next();
                LinkedList<AdvancedWeightedEdge> edgesOfVertex = (LinkedList<AdvancedWeightedEdge>) (pair.getValue());
                for (AdvancedWeightedEdge e : edgesOfVertex) {
                    edges.remove(e);
                }
            }
        }

        // Tworzymy ostateczny graf z nowymi wierzcholkami i krawedziami
        SimpleWeightedGraph<String, AdvancedWeightedEdge> returnGraph =
                new SimpleWeightedGraph<String, AdvancedWeightedEdge>(AdvancedWeightedEdge.class);

        for (String vertex : vertices) {
            returnGraph.addVertex(vertex);
        }

        for (AdvancedWeightedEdge edge : matches) {
            // Tworzymy nowe obiekty krawedzi, gdyz inaczej jgrapht moze myslec ze dwa razy dodalismy taką samą
            // krawędź
            AdvancedWeightedEdge newEdge = new AdvancedWeightedEdge();
            newEdge.setWeight(edge.getWeight());
            returnGraph.addEdge((String) edge.getSourceVertex(), (String) edge.getTargetVertex(), newEdge);
        }

        return returnGraph;
    }
}
