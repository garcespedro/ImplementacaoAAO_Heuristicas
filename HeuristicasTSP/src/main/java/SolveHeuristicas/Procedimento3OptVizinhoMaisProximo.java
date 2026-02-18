package SolveHeuristicas;

import Heuristicas.HeuristicasConstrutivas.VizinhoMaisProximo;
import Heuristicas.HeuristicasPesquisaLocal.Procedimento3Opt;
import TSP.ImportarTSP;
import TSP.Network.Network;
import TSP.TSP;
import TSP.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Procedimento3OptVizinhoMaisProximo {
   /*
   *  public static void main(String[] args) {
        // Carregar o problema TSP
        ImportarTSP importar = new ImportarTSP();
        String filePath = "./30problemasTSPLIB/kroB100.tsp";
        TSP tspTeste = importar.importarTSP(filePath);

        List<City> cidadesList = Arrays.asList(tspTeste.getCities());
        City[] cidadesArray = tspTeste.getCities();

        // Medir tempo de execução da heurística construtiva
        long inicioHeuristica = System.currentTimeMillis();
        VizinhoMaisProximo heuristica = new VizinhoMaisProximo();
        Network<City> solucaoInicial = heuristica.resolucaoTSPVizinhoMaisProximo(tspTeste);
        System.out.println("Custo Total Solução inicial: " + solucaoInicial.getTotCusto());
        long fimHeuristica = System.currentTimeMillis();

        // Converter a solução inicial em percurso (ordem de visita)
        List<Integer> percurso = new ArrayList<>();
        City atual = cidadesArray[0];
        percurso.add(0);
        boolean[] visitadas = new boolean[cidadesArray.length];
        visitadas[0] = true;

        for (int i = 1; i < cidadesArray.length; i++) {
            double menor = Double.MAX_VALUE;
            int proxima = -1;
            for (int j = 0; j < cidadesArray.length; j++) {
                if (!visitadas[j]) {
                    double dist = atual.distanciaPara(cidadesArray[j]);
                    if (dist < menor) {
                        menor = dist;
                        proxima = j;
                    }
                }
            }
            percurso.add(proxima);
            visitadas[proxima] = true;
            atual = cidadesArray[proxima];
        }

        // Aplicar o 3-opt e medir tempo
        Network<City> grafoCompleto = new Network<>();
        for (City cidade : cidadesList) grafoCompleto.addVertex(cidade);
        for (City from : cidadesList)
            for (City to : cidadesList)
                if (!from.equals(to))
                    grafoCompleto.addEdge(from, to, from.distanciaPara(to));

        Procedimento3Opt opt = new Procedimento3Opt(grafoCompleto, cidadesList);

        long inicio3Opt = System.currentTimeMillis();
        double distInicial = opt.calcularDistanciaTotal(percurso);
        List<Integer> percursoOtimizado = opt.resolver3Opt(percurso);
        double distFinal = opt.calcularDistanciaTotal(percursoOtimizado);
        long fim3Opt = System.currentTimeMillis();

        // Resultados
        System.out.println("Distância inicial (Vizinho + ciclo): " + distInicial);
        System.out.println("Distância após 3-opt: " + distFinal);
        System.out.println("Percurso otimizado: " + percursoOtimizado);

        System.out.println("Tempo heurística construtiva: " + (fimHeuristica - inicioHeuristica) + " ms");
        System.out.println("Tempo de execução 3-opt: " + (fim3Opt - inicio3Opt) + " ms");
    }
   * */
}
