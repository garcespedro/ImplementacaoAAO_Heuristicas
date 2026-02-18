package SolveHeuristicas;

import Heuristicas.HeuristicasConstrutivas.MenorCustoInsercao;
import Heuristicas.HeuristicasPesquisaLocal.Procedimento3Opt;
import TSP.City;
import TSP.ImportarTSP;
import TSP.Network.Network;
import TSP.TSP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Procedimento3OptInsercaoMenorCusto {
    public static void main(String[] args) {
        // 1. Carregar instância do TSP
        String filePath = "./30problemasTSPLIB/pr299.tsp";
        ImportarTSP importar = new ImportarTSP();
        TSP tspTeste = importar.importarTSP(filePath);

        List<City> cidadesList = Arrays.asList(tspTeste.getCities());
        City[] cidadesArray = tspTeste.getCities();

        // Medir tempo de execução da heurística construtiva
        long inicioHeuristica = System.currentTimeMillis();
        MenorCustoInsercao heuristica = new MenorCustoInsercao();
        Network<City> solucaoInicial = heuristica.resolucaoTSPMenorCustoInsercao(tspTeste);
        System.out.println("Custo Total Solução inicial: " + solucaoInicial.getTotCusto());
        long fimHeuristica = System.currentTimeMillis();
        System.out.println("Tempo heurística construtiva: " + (fimHeuristica - inicioHeuristica) + " ms");

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

        // Aplicar o 2-opt e medir tempo
        Network<City> grafoCompleto = new Network<>();
        for (City cidade : cidadesList) {
            grafoCompleto.addVertex(cidade);
        }

        for (City from : cidadesList) {
            for (City to : cidadesList) {
                if (!from.equals(to)) {
                    grafoCompleto.addEdge(from, to, from.distanciaPara(to));
                }
            }
        }
        // 4. Aplicar 3-Opt
        Procedimento3Opt procedimento = new Procedimento3Opt(grafoCompleto, cidadesArray);

        double distanciaInicial = procedimento.calcularDistanciaTotal(percurso);
        System.out.println("Distância inicial: " + distanciaInicial);

        long inicio = System.currentTimeMillis();
        List<Integer> resultadofinal = procedimento.resolver3Opt(percurso);  // Modifica o percurso in-place
        long fim = System.currentTimeMillis();

        double distanciaFinal = procedimento.calcularDistanciaTotal(resultadofinal);

        // 5. Exibir resultados
        System.out.println("Distância após 3-Opt: " + distanciaFinal);
        System.out.println("Percurso otimizado: " + percurso);
        System.out.println("Tempo total do 3-Opt: " + (fim - inicio) + " ms");

        //KroA100 -_> 22139
    }
}
