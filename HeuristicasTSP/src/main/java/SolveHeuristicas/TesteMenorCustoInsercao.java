package SolveHeuristicas;

import Heuristicas.HeuristicasConstrutivas.MenorCustoInsercao;
import TSP.ImportarTSP;
import TSP.Network.Network;
import TSP.*;

import java.util.Iterator;

public class TesteMenorCustoInsercao {

    public static void main(String[] args) {
        ImportarTSP importar = new ImportarTSP();
        TSP tspTeste = importar.importarTSP("./30problemasTSPLIB/u159.tsp");

        System.out.println(tspTeste);
        tspTeste.getCities();

        MenorCustoInsercao menor = new MenorCustoInsercao();

        Network<City> solucaoOtima = menor.resolucaoTSPMenorCustoInsercao(tspTeste);
        System.out.println(solucaoOtima.toString());
        System.out.println("Custo Total: " + solucaoOtima.getTotCusto());
        City[] cidades = tspTeste.getCities();
        Iterator<City> itr = solucaoOtima.iteratorBFS(cidades[0]);

        while (itr.hasNext()) {
            City city = itr.next();
            System.out.println(city.toString());
        }

        System.out.println("Custo Total: " + solucaoOtima.getTotCusto());
    }

}
