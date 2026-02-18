package TSPpackageTeste;

import TSP.TSP;
import TSP.City;
import TSP.Network.Network;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TSPtest {

    private TSP tsp;
    private City[] cities;
    private Network solucaoOtima;

    @BeforeEach
    public void setUp() {
        cities = new City[] {
                new City("1", 0.0, 0.0),
                new City("2", 1.0, 1.0)
        };

        solucaoOtima = new Network(); // Garante que há uma instância válida

        tsp = new TSP("exemplo", "TSP", "Comentário teste", 2, "EUC_2D", cities, solucaoOtima);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("exemplo", tsp.getName());
        assertEquals("TSP", tsp.getType());
        assertEquals("Comentário teste", tsp.getComment());
        assertEquals(2, tsp.getDimension());
        assertEquals("EUC_2D", tsp.getEdge_weight_type());
        assertArrayEquals(cities, tsp.getCities());
        assertEquals(solucaoOtima, tsp.getSolucaoOtima());
    }

    @Test
    public void testSetSolucaoOtima() {
        Network novaSolucao = new Network();
        tsp.setSolucaoOtima(novaSolucao);
        assertEquals(novaSolucao, tsp.getSolucaoOtima());
    }

    @Test
    public void testToString() {
        String toString = tsp.toString();

        assertTrue(toString.contains("name='exemplo'"));
        assertTrue(toString.contains("type='TSP'"));
        assertTrue(toString.contains("comment='Comentário teste'"));
        assertTrue(toString.contains("dimension=2"));
        assertTrue(toString.contains("edge_weight_type='EUC_2D'"));
        assertTrue(toString.contains("cities="));
        assertTrue(toString.contains("solucaoOtima="));
    }
}
