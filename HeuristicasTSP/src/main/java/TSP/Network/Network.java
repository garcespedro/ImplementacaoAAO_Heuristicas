package TSP.Network;

import Exceptions.CaminhoNotFoundException;
import TSP.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Representa uma rede (grafo) para o problema TSP, onde os vértices
 * são instâncias de {@link TSP.City}. Essa classe estende a classe {@code Graph}
 * (cuja implementação não é mostrada aqui) e adiciona uma matriz de adjacência
 * para armazenar os pesos das arestas entre os vértices.
 *
 * @param <T> O tipo de vértice, que deve estender {@link TSP.City}.
 */
public class Network<T extends City> extends Graph<T> {

    /**
     * Matriz de adjacência que armazena os pesos das arestas entre os vértices.
     * Inicialmente, os valores são definidos como {@code Double.POSITIVE_INFINITY} para
     * indicar que não há conexão.
     */
    protected double[][] adjMatrix;

    /**
     * Custo total acumulado das arestas adicionadas na rede.
     */
    private double totCusto;

    /**
     * Construtor padrão que inicializa a rede com a capacidade padrão (definida em {@code Graph.DEFAULT_CAPACITY})
     * e configura a matriz de adjacência com valores infinitos.
     */
    public Network() {
        super();
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        for(int i = 0; i < DEFAULT_CAPACITY; i++) {
            for(int j = 0; j < DEFAULT_CAPACITY; j++) {
                adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        totCusto = 0;
    }

    /**
     * Construtor que inicializa a rede com um array de vértices e um tamanho específico.
     *
     * @param vertices Array de vértices.
     * @param size     Número de vértices.
     */
    public Network(T[] vertices, int size) {
        super(vertices, size);
        this.adjMatrix = new double[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                adjMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        totCusto = 0;
    }

    /**
     * Retorna o custo total acumulado das arestas presentes na rede.
     *
     * @return O custo total.
     */
    public double getTotCusto() {
        return totCusto;
    }

    /**
     * Obtém o peso da aresta entre dois vértices.
     *
     * @param from Vértice de origem.
     * @param to   Vértice de destino.
     * @return Peso da aresta.
     */
    public double getWeightEdge(T from, T to) {
        int ind_vertex = getIndex(from);
        int ind_vertex2 = getIndex(to);

        return getWeightEdge(ind_vertex, ind_vertex2);
    }

    /**
     * Retorna o peso da aresta entre dois vértices do grafo, dado seus índices.
     *
     * @param from O índice do primeiro vértice.
     * @param to O índice do segundo vértice.
     * @return O peso da aresta entre os dois vértices, ou infinito positivo se não houver aresta.
     */
    private double getWeightEdge(int from, int to) throws CaminhoNotFoundException{
        int num_vertices = this.numVertices;
        double[] dist = new double[num_vertices];
        boolean[] visited = new boolean[num_vertices];

        for(int i = 0; i < num_vertices; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }

        dist[from] = 0;

        int i = 0;
        boolean found = false;
        boolean inacessivel = false;
        while(i < num_vertices && !found && !inacessivel) {
            // Escolhe o vértice u não visitado com menor distância
            int u = -1;
            double best = Double.POSITIVE_INFINITY;
            for (int v = 0; v < num_vertices; v++) {
                if (!visited[v] && dist[v] < best) {
                    best = dist[v];
                    u = v;
                }
            }


            if (u == to) {
                found = true;
            } else if (u == -1 || best == Double.POSITIVE_INFINITY) {
                //Quer dizer que não existe nenhum caminho entre esses dois pontos
                inacessivel = true;
            } else {
                visited[u] = true;

                for (int v = 0; v < num_vertices; v++) {
                    double w = adjMatrix[u][v];
                    if (!visited[v] && w != Double.POSITIVE_INFINITY) {
                        double alt = dist[u] + w;
                        if (alt < dist[v]) {
                            dist[v] = alt;
                        }
                    }
                }
            }

           i++;
        }

        if(inacessivel && !found) {
            throw new CaminhoNotFoundException("Não existe nenhum caminho entre esses dois pontos");
        }

        return dist[to];
    }

    /**
     * Expande a matriz de adjacência para dobrar a capacidade quando não houver mais espaço.
     * O método invoca {@link Graph#expandCapacity()} para expandir o array de vértices e cria
     * uma nova matriz maior, inicializando-a com {@code Double.POSITIVE_INFINITY} e copiando os dados
     * da matriz antiga.
     */
    protected void expandadweightMatrix() {
        // primeiro dobra vértices e boolean matrix
        super.expandCapacity(); // ou o teu código que já faz isso

        // agora expande adjMatrix
        int oldCap = this.adjMatrix.length;
        int newCap = oldCap * 2;
        double[][] newWeights = new double[newCap][newCap];

        // inicializa os novos valores como infinito
        for (int i = 0; i < newCap; i++) {
            Arrays.fill(newWeights[i], Double.POSITIVE_INFINITY);
        }

        // copia os pesos antigos
        for (int i = 0; i < oldCap; i++) {
            System.arraycopy(this.adjMatrix[i], 0,
                    newWeights[i], 0,
                    oldCap);
        }

        this.adjMatrix = newWeights;
    }

    /**
     * Expande a matriz de adjacência para dobrar a capacidade quando não houver mais espaço.
     * O método invoca {@link Graph#expandCapacity()} para expandir o array de vértices e cria
     * uma nova matriz maior, inicializando-a com {@code Double.POSITIVE_INFINITY} e copiando os dados
     * da matriz antiga.
     */
    @Override
    public void addVertex(T vertex) {
        if(this.vertices.length == this.numVertices) {
            expandadweightMatrix();
        }

        super.addVertex(vertex);
    }

    /**
     * Adiciona uma aresta (não direcionada) entre dois vértices no grafo com um peso especificado.
     * A aresta é adicionada nas duas direções na matriz de adjacência e, em seguida, o custo total é atualizado.
     *
     * @param vertex1 O primeiro vértice da aresta.
     * @param vertex2 O segundo vértice da aresta.
     * @param weight  O peso da aresta.
     */
    public void addEdge(T vertex1, T vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = weight;
            this.adjMatrix[index2][index1] = weight;
            super.addEdge(index1, index2);
        }

        totCusto += weight;
    }

    /**
     * Adiciona uma aresta (não direcionada) entre dois vértices no grafo com um peso especificado.
     * A aresta é adicionada nas duas direções na matriz de adjacência e, em seguida, o custo total é atualizado.
     *
     * @param vertex1 O primeiro vértice da aresta.
     * @param vertex2 O segundo vértice da aresta.
     * @param weight  O peso da aresta.
     */
    public void removeEdge(T vertex1, T vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);

        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = Double.POSITIVE_INFINITY;
            this.adjMatrix[index2][index1] = Double.POSITIVE_INFINITY;
            super.removeEdge(index1, index2);
        }

        totCusto -= weight;
    }


    /**
     * Retorna um iterador para os vértices adjacentes a partir de um vértice de início.
     * Apenas os vértices conectados (onde o peso não é {@code Double.POSITIVE_INFINITY}) são incluídos.
     *
     * @param startVertex O vértice de início.
     * @return Um iterador para os vértices adjacentes ao vértice de início.
     */
    public Iterator<T> iteratorNextVertexs(T startVertex) {
        int index = getIndex(startVertex);

        if (!indexIsValid(index)) {
            return new ArrayList<T>().iterator();
        }

        List<T> adjacentVertices = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[index][i] != Double.POSITIVE_INFINITY) {
                adjacentVertices.addLast(vertices[i]);
            }
        }

        return adjacentVertices.iterator();
    }

    /**
     * Obtém o peso da aresta entre dois vértices.
     *
     * @param from Vértice de origem.
     * @param to   Vértice de destino.
     * @return Peso da aresta.
     * @throws IllegalArgumentException se um dos vértices não existir.
     */
    public double getWeight(T from, T to) {
        int fromIndex = getIndex(from);
        int toIndex = getIndex(to);

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException("Vértice de origem não encontrado: " + from);
        }

        return this.adjMatrix[fromIndex][toIndex];
    }
}
