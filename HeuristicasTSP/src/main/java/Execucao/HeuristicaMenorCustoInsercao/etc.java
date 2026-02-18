package Execucao.HeuristicaMenorCustoInsercao;

import Heuristicas.HeuristicasConstrutivas.MenorCustoInsercao;
import TSP.ImportarTSP;
import TSP.TSP;

public class etc {
    public static void main(String[] args) {
        ImportarTSP importar = new ImportarTSP();
        TSP tspTeste = importar.importarTSP("./30problemasTSPLIB/u159.tsp");

        MenorCustoInsercao menor = new MenorCustoInsercao();
        menor.resolucaoTSPMenorCustoInsercao(tspTeste);

        System.out.println("Solucao: " + tspTeste.getSolucaoOtima().getTotCusto());
    }
}
