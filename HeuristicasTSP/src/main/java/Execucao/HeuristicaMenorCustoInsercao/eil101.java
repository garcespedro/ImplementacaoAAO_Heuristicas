package Execucao.HeuristicaMenorCustoInsercao;

import Heuristicas.HeuristicasConstrutivas.MenorCustoInsercao;
import TSP.ImportarTSP;
import TSP.TSP;

public class eil101 {
    public static void main(String[] args) {
        ImportarTSP importar = new ImportarTSP();
        TSP tspTeste = importar.importarTSP("./30problemasTSPLIB/eil101.tsp");

        MenorCustoInsercao menor = new MenorCustoInsercao();
        menor.resolucaoTSPMenorCustoInsercao(tspTeste);

        System.out.println("Solucao:  " + tspTeste.getSolucaoOtima().getTotCusto());
    }
}
