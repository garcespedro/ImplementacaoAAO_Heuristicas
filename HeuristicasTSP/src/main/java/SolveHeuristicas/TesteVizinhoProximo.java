package SolveHeuristicas;

import Heuristicas.HeuristicasConstrutivas.VizinhoMaisProximo;
import TSP.ImportarTSP;
import TSP.Network.Network;
import TSP.*;

import java.util.Iterator;

public class TesteVizinhoProximo {

    public static void main(String[] args) {
        ImportarTSP importar = new ImportarTSP();
        String filePath = "./30problemasTSPLIB/u159.tsp";
        TSP tspTeste = importar.importarTSP(filePath);

        System.out.println(tspTeste);
        tspTeste.getCities();

        VizinhoMaisProximo menor = new VizinhoMaisProximo();

        Network<City> solucaoOtima = menor.resolucaoTSPVizinhoMaisProximo(tspTeste);
        System.out.println(solucaoOtima.toString());
        System.out.println("Custo Total: " + solucaoOtima.getTotCusto());
        City[] cidades = tspTeste.getCities();
        Iterator<City> itr = solucaoOtima.iteratorBFS(cidades[0]);

        while (itr.hasNext()) {
            City city = itr.next();
            System.out.println(city.toString());
        }

        System.out.println("Custo Total: " + solucaoOtima.getTotCusto());
        System.out.println("Tempo de execução: " + menor.getTempoExecucao() + " ms");
    }
}