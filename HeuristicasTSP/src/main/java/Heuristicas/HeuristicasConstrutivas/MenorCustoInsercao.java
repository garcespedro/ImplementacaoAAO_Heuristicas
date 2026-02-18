package Heuristicas.HeuristicasConstrutivas;

import Heuristicas.CalculosDistancias;
import TSP.Network.Network;
import TSP.TSP;
import TSP.City;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A classe {@code MenorCustoInsercao} implementa uma heurística construtiva para resolver o
 * problema do caixeiro viajante (TSP) utilizando o critério de inserção de menor custo.
 * <p>
 * A heurística inicia com uma solução básica (subtour) formada pelos dois primeiros vértices e, em seguida,
 * insere iterativamente os demais vértices na posição que minimiza o aumento do custo total da rota.
 * Para isso, a classe utiliza uma matriz de custos pré-incializada e, para cada aresta presente na solução
 * parcial, calcula o custo adicional de inserir cada vértice ainda não visitado.
 * </p>
 *
 * @author Artur Gentil Silva Pinto nº 8230138
 */
public class MenorCustoInsercao {

    /**
     * Calcula o custo de inserção de um vértice em uma aresta existente.
     * <p>
     * O custo de inserção é definido como a soma das distâncias do vértice candidato até cada um
     * dos extremos da aresta, menos a distância direta entre os extremos da aresta original.
     * </p>
     *
     * @param distanceik a distância entre o primeiro vértice da aresta e o vértice a ser inserido
     * @param distancekj a distância entre o vértice a ser inserido e o segundo vértice da aresta
     * @param distanceij a distância da aresta original entre os dois vértices
     * @return o custo adicional para inserir o vértice na posição entre os dois vértices da aresta
     */
    private double custoInsercao(double distanceik, double distancekj, double distanceij) {
        return distanceik + distancekj - distanceij;
    }

    /**
     * Resolve o problema do TSP utilizando a heurística de inserção de menor custo.
     * <p>
     * Este método constrói iterativamente uma solução para o TSP começando com uma aresta inicial
     * entre os dois primeiros vértices e, a cada iteração, insere o vértice que resultar no menor
     * aumento do custo total da solução. A inserção é feita selecionando, dentre as arestas existentes
     * na solução, aquela que gera a menor variação no custo quando um novo vértice for inserido.
     * </p>
     *
     * @param tsp o objeto que representa a instância do problema do caixeiro viajante, contendo as cidades,
     *            a solução parcial e demais informações necessárias para a resolução
     * @return o objeto {@code Network} e atualiza o tsp incial com a solução encontrada através da heurística de inserção de menor custo
     */
    public Network<City> resolucaoTSPMenorCustoInsercao(TSP tsp) {
        // Instancia a classe responsável por calcular as distâncias entre as cidades
        long tempoInicial = System.currentTimeMillis();
        CalculosDistancias calculosDistancias = new CalculosDistancias();

        // Cria uma matriz com os custos/distâncias entre todos os vértices
        double[][] matriz_custos = calculosDistancias.inicializaMatrizCustos(tsp);
        int totVertices = tsp.getDimension();

        // Array que controla quais vértices já foram incluídos na solução
        boolean[] visited = new boolean[totVertices];

        // Lista que armazena os índices dos vértices inseridos na solução (para evitar iterar todo o array)
        List<Integer> indicesVisitados = new ArrayList<>();

        // Recupera o vetor de cidades e a solução ótima parcial (objeto Network)
        City[] vertices = tsp.getCities();
        Network<City> solucaoOtima = tsp.getSolucaoOtima();

        // Inicializa a solução com uma aresta entre os dois primeiros vértices
        solucaoOtima.addEdge(vertices[0], vertices[1], matriz_custos[0][1]);
        visited[0] = true;
        visited[1] = true;
        indicesVisitados.add(0);
        indicesVisitados.add(1);
        int countVisited = 2;

        // Enquanto nem todos os vértices estiverem na solução, busca-se a inserção de menor custo
        while (countVisited < totVertices) {
            //Variaveis para determinar a menor inserção e os vertices envolvidos
            City verticeInicial = null;
            int indiceVerticeInicial = 0;
            City verticeFinal = null;
            int indiceVerticeFinal = 0;
            City verticeInserir  = null;
            int indiceVerticeInserir = 0;
            double menorCusto = Double.MAX_VALUE;

            // Percorresse os índices dos vértices já inseridos na solução
            for (int i: indicesVisitados) {
                // Obtém os vizinhos do vértice atual na solução
                Iterator<City> vizinhos = solucaoOtima.iteratorNextVertexs(vertices[i]);

                while (vizinhos.hasNext()) {
                    City vizinho = vizinhos.next();
                    int j = solucaoOtima.getIndex(vizinho);

                    // Itera sobre os vértices que ainda não foram visitados
                    for (int k = 0; k < totVertices; k++) {
                        if (!visited[k]) {

                            // Calcula o custo de inserir o vértice k entre os vértices i e j
                            double custoInsercao = custoInsercao(matriz_custos[i][k],
                                                                matriz_custos[k][j],
                                                                matriz_custos[i][j]);

                            // Atualiza se encontrar uma inserção com custo menor
                            if (custoInsercao < menorCusto) {
                                menorCusto = custoInsercao;
                                verticeInicial = vertices[i];
                                indiceVerticeInicial = i;
                                verticeFinal = vizinho;
                                indiceVerticeFinal = j;
                                verticeInserir = vertices[k];
                                indiceVerticeInserir = k;
                            }
                        }
                    }
                }
            }

            // Atualiza a solução removendo a aresta onde será feita a inserção
            solucaoOtima.removeEdge(verticeInicial, verticeFinal, matriz_custos[indiceVerticeInicial][indiceVerticeFinal]);

            // Insere o novo vértice dividindo a aresta removida em duas novas arestas
            solucaoOtima.addEdge(verticeInicial, verticeInserir, matriz_custos[indiceVerticeInicial][indiceVerticeInserir]);
            solucaoOtima.addEdge(verticeInserir , verticeFinal, matriz_custos[indiceVerticeInserir][indiceVerticeFinal]);

            // Marca o novo vértice como visitado e adiciona seu índice à lista de vértices visitados
            visited[indiceVerticeInserir] = true;
            indicesVisitados.add(indiceVerticeInserir);
            countVisited++;
        }

        // Atualiza a solução ótima do objeto TSP com o tour construído
        tsp.setSolucaoOtima(solucaoOtima);
        long tempoExecucao = System.currentTimeMillis() - tempoInicial;
        System.out.println("Tempo de execução: " + tempoExecucao + " ms");
        return solucaoOtima;
    }
}
