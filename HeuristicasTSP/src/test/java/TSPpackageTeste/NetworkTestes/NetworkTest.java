package TSPpackageTeste.NetworkTestes;

import TSP.City;
import TSP.Network.Network;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NetworkTest {

    private Network<City> network;
    private City city1;
    private City city2;
    private City city3;

    @BeforeEach
    public void setUp() {
        City[] cities = new City[3];
        city1 = new City("City1", 0.0, 0.0);
        city2 = new City("City2", 1.0, 1.0);
        city3 = new City("City3", 2.0, 2.0);

        cities[0] = city1;
        cities[1] = city2;
        cities[2] = city3;
        network = new Network<>(cities, cities.length);
    }

    @Test
    public void testAddEdge() {
        network.addEdge(city1, city2, 10.0);

        assertEquals(10.0, network.getTotCusto(), 0.0001);

        Iterator<City> iterator = network.iteratorNextVertexs(city1);
        boolean found = false;
        while (iterator.hasNext()) {
            if (iterator.next().equals(city2)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "City2 deve ser adjacente a City1 após a adição da aresta.");
    }

    @Test
    public void testRemoveEdge() {
        network.addEdge(city1, city2, 15.0);
        network.removeEdge(city1, city2, 15.0);

        assertEquals(0.0, network.getTotCusto(), 0.0001);

        Iterator<City> iterator = network.iteratorNextVertexs(city1);
        boolean found = false;
        while (iterator.hasNext()) {
            if (iterator.next().equals(city2)) {
                found = true;
                break;
            }
        }
        assertFalse(found, "City2 não deve ser adjacente a City1 após a remoção da aresta.");
    }

    @Test
    public void testGetTotCustoGetter() {
        assertEquals(0.0, network.getTotCusto(), 0.0001);

        network.addEdge(city1, city2, 8.5);
        assertEquals(8.5, network.getTotCusto(), 0.0001);

        network.addEdge(city2, city3, 12.0);
        assertEquals(20.5, network.getTotCusto(), 0.0001);
    }

    @Test
    public void testIteratorNextVertices() {
        network.addEdge(city1, city2, 5.0);
        network.addEdge(city1, city3, 10.0);

        Iterator<City> iterator = network.iteratorNextVertexs(city1);
        List<City> adjacentCities = new ArrayList<>();
        while (iterator.hasNext()) {
            adjacentCities.add(iterator.next());
        }

        assertTrue(adjacentCities.contains(city2), "City2 deve ser adjacente a City1.");
        assertTrue(adjacentCities.contains(city3), "City3 deve ser adjacente a City1.");

        assertEquals(2, adjacentCities.size(), "City1 deve ter 2 vértices adjacentes.");
    }
}
