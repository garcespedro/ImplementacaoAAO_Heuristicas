package TSPpackageTeste;

import TSP.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CityTest {
    private City city;

    /**
     * Método executado antes de cada teste para inicializar uma cidade padrão.
     */
    @BeforeEach
    public void setUp() {
        city = new City("Cidade1", 10.5, 20.5);
    }

    /**
     * Testa o construtor e os métodos getters.
     */
    @Test
    public void testConstructorAndGetters() {
        assertEquals("Cidade1", city.getName());
        assertEquals(10.5, city.getValue_x());
        assertEquals(20.5, city.getValue_y());
    }

    /**
     * Testa o método {@code toString()} para garantir que os dados essenciais estão presentes.
     */
    @Test
    public void testToString() {
        String cityString = city.toString();
        assertTrue(cityString.contains("name='Cidade1'") || cityString.contains("name=\"Cidade1\""));
        assertTrue(cityString.contains("value_x=10.5"));
        assertTrue(cityString.contains("value_y=20.5"));
    }
}
