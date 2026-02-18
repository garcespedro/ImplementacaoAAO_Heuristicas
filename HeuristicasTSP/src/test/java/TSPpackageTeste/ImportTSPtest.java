package TSPpackageTeste;

import TSP.TSP;
import TSP.ImportarTSP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImportTSPtest {

    @Test
    public void testImportarFicheiroTSPValido() {
        ImportarTSP importar = new ImportarTSP();
        TSP tsp = importar.importarTSP("./30problemasTSPLIB/a280.tsp");

        assertNotNull(tsp, "O TSP importado não deve ser nulo");
        assertEquals("a280", tsp.getName(), "O nome do TSP deve ser 'a280'");
        assertEquals(280, tsp.getDimension(), "A dimensão deve ser 280");
        assertNotNull(tsp.getCities(), "A lista de cidades não deve ser nula");
        assertEquals(280, tsp.getCities().length, "A quantidade de cidades deve ser 280");
        assertNotNull(tsp.getSolucaoOtima(), "A solução ótima não deve ser nula");
    }

    @Test
    public void testImportarFicheiroInexistente() {
        ImportarTSP importar = new ImportarTSP();
        TSP tsp = importar.importarTSP("./ficheiro/inexistente.tsp");

        assertNull(tsp, "O TSP deve ser nulo se o ficheiro não existir");
    }
}
