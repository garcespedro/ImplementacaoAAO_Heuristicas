package TSPpackageTeste.NetworkTestes;

import Exceptions.EmptyCollectionException;
import TSP.City;
import TSP.Network.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    private Graph<City> graph;
    private City city1;
    private City city2;
    private City city3;
    private City city4;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
        city1 = new City("City1", 0.0, 0.0);
        city2 = new City("City2", 1.0, 1.0);
        city3 = new City("City3", 2.0, 2.0);
        city4 = new City("City4", 3.0, 3.0);

        graph.addVertex(city1);
        graph.addVertex(city2);
        graph.addVertex(city3);
    }

    @Test
    public void testAddVertexAndGetIndex() {
        assertEquals(0, graph.getIndex(city1));
        assertEquals(1, graph.getIndex(city2));
        assertEquals(2, graph.getIndex(city3));

        graph.addVertex(city4);
        assertEquals(3, graph.getIndex(city4));
    }

    @Test
    public void testAddEdgeAndBFSIterator() {
        graph.addEdge(city1, city2);
        graph.addEdge(city2, city3);

        List<City> bfsResult = new ArrayList<>();
        Iterator<City> itrBFS = graph.iteratorBFS(city1);
        while (itrBFS.hasNext()) {
            bfsResult.add(itrBFS.next());
        }

        assertTrue(bfsResult.contains(city1));
        assertTrue(bfsResult.contains(city2));
        assertTrue(bfsResult.contains(city3));

        assertEquals(3, bfsResult.size());
    }

    @Test
    public void testRemoveEdge() {
        graph.addEdge(city1, city2);
        List<City> bfsResult = new ArrayList<>();
        Iterator<City> itrBFS = graph.iteratorBFS(city1);

        while (itrBFS.hasNext()) {
            bfsResult.add(itrBFS.next());
        }

        assertTrue(bfsResult.contains(city2));

        graph.removeEdge(city1, city2);
        bfsResult.clear();
        itrBFS = graph.iteratorBFS(city1);
        while (itrBFS.hasNext()) {
            bfsResult.add(itrBFS.next());
        }

        if (bfsResult.size() > 1){
            assertFalse(bfsResult.get(1).equals(city2));
        }
    }

    @Test
    public void testRemoveVertex() throws EmptyCollectionException {
        assertEquals(1, graph.getIndex(city2));

        graph.removeVertex(city2);
        assertEquals(-1, graph.getIndex(city2));
        List<City> bfsResult = new ArrayList<>();
        Iterator<City> itrBFS = graph.iteratorBFS(city1);
        while (itrBFS.hasNext()) {
            bfsResult.add(itrBFS.next());
        }

        assertEquals(1, bfsResult.size());
        assertTrue(bfsResult.contains(city1));
        assertFalse(bfsResult.contains(city3));
    }

    @Test
    public void testIteratorDFS() {
        graph.addEdge(city1, city2);
        graph.addEdge(city1, city3);
        graph.addVertex(city4);

        List<City> dfsResult = new ArrayList<>();
        Iterator<City> itrDFS = graph.iteratorDFS(city1);
        while (itrDFS.hasNext()) {
            dfsResult.add(itrDFS.next());
        }

        assertTrue(dfsResult.contains(city1));
        assertTrue(dfsResult.contains(city2));
        assertTrue(dfsResult.contains(city3));
        assertFalse(dfsResult.contains(city4));
    }

    @Test
    public void testIsConnected() {
        graph.addEdge(city1, city2);
        graph.addEdge(city2, city3);
        assertTrue(graph.isConnected());

        graph.addVertex(city4);
        assertFalse(graph.isConnected());
    }
}
