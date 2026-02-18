package HeuristicasTestes.HeuristicasConstrutivas;

import Heuristicas.HeuristicasConstrutivas.MenorCustoInsercao;
import TSP.ImportarTSP;
import TSP.TSP;
import TSP.Network.Network;
import TSP.City;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MenorCustoInsercaoTest {

    @Test
    public void testResolucaoTSPMenorCustoInsercao() {
        ImportarTSP importar = new ImportarTSP();
        TSP tspTeste = importar.importarTSP("./30problemasTSPLIB/bier127.tsp");

        assertNotNull(tspTeste, "A instância do TSP não pode ser nula");

        MenorCustoInsercao menor = new MenorCustoInsercao();
        Network solucaoOtima = menor.resolucaoTSPMenorCustoInsercao(tspTeste);

        assertNotNull(solucaoOtima, "A solução obtida não pode ser nula");
        assertTrue(solucaoOtima.getTotCusto() > 0, "O custo total da solução deve ser maior que zero");

        City[] cidades = tspTeste.getCities();
        Iterator<City> itr = solucaoOtima.iteratorBFS(cidades[0]);
        int count = 0;
        while (itr.hasNext()) {
            itr.next();
            count++;
        }

        assertEquals(tspTeste.getDimension(), count, "Nem todos os vértices foram inseridos na solução");
    }
}
