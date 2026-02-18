package Heuristicas.HeuristicasConstrutivas;

import Heuristicas.CalculosDistancias;
import TSP.Network.Network;
import TSP.TSP;
import TSP.City;

/**
 * A classe {@code VizinhoMaisProximo} implementa a heurística construtiva
 * do vizinho mais próximo para resolver o problema do caixeiro viajante (TSP).
 *
 * A heurística inicia em uma cidade (por padrão a primeira) e, a cada iteração,
 * escolhe a próxima cidade não visitada que tenha a menor distância da cidade atual.
 * Repete até que todas as cidades sejam visitadas e, por fim, retorna à origem.
 *
 */
public class VizinhoMaisProximo {
    public long tempoExecucao;
    /**
     * Constrói uma solução para o TSP utilizando a heurística do vizinho mais próximo.
     *
     * @param tsp Instância do problema TSP contendo cidades e solução parcial
     * @return Objeto {@code Network} representando o tour construído
     */
    public Network<City> resolucaoTSPVizinhoMaisProximo(TSP tsp) {
        long tempoInicial = System.currentTimeMillis();
        // Calcula matriz de custos/distâncias entre todas as cidades
        CalculosDistancias calculos = new CalculosDistancias();
        double[][] matrizCustos = calculos.inicializaMatrizCustos(tsp);
        int totVertices = tsp.getDimension();
        City[] cidades = tsp.getCities();

        // Vetor para controlar cidades já visitadas
        boolean[] visited = new boolean[totVertices];

        // Inicializa a rede de solução
        Network<City> solucao = new Network<>(cidades, totVertices);

        // Começa na primeira cidade (índice 0)
        int atual = 0;
        visited[atual] = true;
        int countVisited = 1;

        // Enquanto houver cidades não visitadas
        while (countVisited < totVertices) {
            int proximo = -1;
            double menorDist = Double.MAX_VALUE;

            // Encontra o vizinho mais próximo não visitado
            for (int j = 0; j < totVertices; j++) {
                if (!visited[j] && matrizCustos[atual][j] < menorDist) {
                    menorDist = matrizCustos[atual][j];
                    proximo = j;
                }
            }

            // Adiciona a aresta entre a cidade atual e o próximo
            solucao.addEdge(cidades[atual], cidades[proximo], menorDist);

            // Atualiza estado
            visited[proximo] = true;
            atual = proximo;
            countVisited++;
        }

        // Fecha o ciclo retornando à cidade inicial
        solucao.addEdge(cidades[atual], cidades[0], matrizCustos[atual][0]);

        // Atualiza a solução no objeto TSP e retorn
        tempoExecucao = System.currentTimeMillis() - tempoInicial;

        System.out.println("Tempo de execução: " + tempoExecucao + " ms");
        tsp.setSolucaoOtima(solucao);
        return solucao;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }
}
