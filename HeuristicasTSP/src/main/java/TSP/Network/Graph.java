package TSP.Network;

import Exceptions.EmptyCollectionException;
import TSP.City;

import java.util.*;

/**
 * Representa um grafo não direcionado cujos vértices são instâncias da classe {@link TSP.City}.
 * O grafo é implementado utilizando uma matriz de adjacência booleana para indicar se existe
 * uma conexão entre dois vértices. Caso não haja conexão, o valor é {@code false}.
 *
 * @param <T> O tipo de vértice, que deve estender {@link TSP.City}.
 */
public class Graph<T extends City> {
    /**
     * A matriz de adjacência que representa as conexões entre os vértices.
     * Cada posição adjMatrixboolean[i][j] contém {@code true} se houver uma aresta entre os vértices
     * i e j, ou {@code false} caso contrário.
     */
    protected boolean[][] adjMatrixboolean;

    /**
     * Número de vértices atualmente armazenados no grafo.
     */
    protected int numVertices;

    /**
     * Array que armazena os vértices do grafo.
     */
    protected T[] vertices;

    /**
     * Capacidade inicial padrão do grafo.
     */
    protected Integer DEFAULT_CAPACITY = 100;

    /**
     * Cria um grafo vazio com a capacidade inicial padrão.
     */
    public Graph() {
        this.vertices = (T[]) new City[DEFAULT_CAPACITY];
        this.adjMatrixboolean = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.numVertices = 0;
    }

    /**
     * Cria um grafo com os vértices fornecidos e um tamanho específico.
     *
     * @param vertices Array de vértices.
     * @param size     Número de vértices presentes inicialmente.
     */
    public Graph(T[] vertices, int size) {
        this.vertices = vertices;
        this.adjMatrixboolean = new boolean[size][size];
        this.numVertices = size;
    }

    /**
     * Expande a capacidade do grafo, dobrando o tamanho do array de vértices
     * e da matriz de adjacência.
     */
    protected void expandCapacity() {
        int oldCap = this.vertices.length;
        int newCap = oldCap * 2;

        @SuppressWarnings("unchecked")
        T[] temp = (T[]) java.lang.reflect.Array.newInstance(
                this.vertices.getClass().getComponentType(),
                newCap);
        System.arraycopy(this.vertices, 0, temp, 0, oldCap);
        this.vertices = temp;

        boolean[][] newBool = new boolean[newCap][newCap];
        for (int i = 0; i < oldCap; i++) {
            System.arraycopy(this.adjMatrixboolean[i], 0,
                    newBool[i], 0,
                    this.adjMatrixboolean[i].length);
        }
        this.adjMatrixboolean = newBool;
    }

    /**
     * Adiciona um vértice ao grafo. Se a capacidade for atingida,
     * a capacidade é expandida automaticamente.
     *
     * @param vertex O vértice a ser adicionado.
     */
    public void addVertex(T vertex) {
        if (numVertices == vertices.length) {
            expandCapacity();
        }

        vertices[numVertices] = vertex;

        for (int i = 0; i <= numVertices; i++) {
            adjMatrixboolean[numVertices][i] = false;
            adjMatrixboolean[i][numVertices] = false;
        }

        numVertices++;
    }

    /**
     * Remove um vértice do grafo.
     *
     * @param vertex O vértice a ser removido.
     * @throws EmptyCollectionException se o vértice não existir.
     */
    public void removeVertex(T vertex) throws EmptyCollectionException {
        int indexVertex = getIndex(vertex);

        if (indexVertex == -1) {
            throw new EmptyCollectionException("O vertice que introduziu não existe");
        }

        for (int i = 0; i <= numVertices; i++) {
            removeEdge(indexVertex, i);
        }

        this.vertices[indexVertex] = null;
        numVertices--;

        for (int i = indexVertex; i < numVertices; i++) {
            this.vertices[i] = this.vertices[i + 1];
            this.adjMatrixboolean[i] = this.adjMatrixboolean[i + 1];
        }

        this.vertices[numVertices] = null;
        this.adjMatrixboolean[numVertices] = null;
    }


    /**
     * Retorna o índice de um dado vértice no array.
     *
     * @param vertex O vértice a procurar.
     * @return O índice do vértice ou -1 se não for encontrado.
     */
    public int getIndex(T vertex) {
        int i = 0;
        boolean find = false;

        while (i < this.numVertices && !find) {
            if (this.vertices[i].equals(vertex)) {
                find = true;
            } else {
                i++;
            }
        }

        if (!find) {
            i = -1;
        }

        return i;
    }

    /**
     * Verifica se um índice é válido, ou seja, se está dentro dos limites e se o vértice nesse índice não é nulo.
     *
     * @param index O índice a ser verificado.
     * @return {@code true} se o índice for válido; {@code false} caso contrário.
     */
    protected boolean indexIsValid(int index) {
        boolean valid = false;

        if (index >= 0 && index < this.numVertices && this.vertices[index] != null) {
            valid = true;
        }

        return valid;
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo, dados os vértices.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo, dados seus índices.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrixboolean[index1][index2] = true;
            adjMatrixboolean[index2][index1] = true;
        }
    }

    /**
     * Remove a aresta entre dois vértices do grafo, dados os vértices.
     *
     * @param vertex1 O primeiro vértice.
     * @param vertex2 O segundo vértice.
     */
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Remove a aresta entre dois vértices do grafo, dados seus índices.
     *
     * @param index1 O índice do primeiro vértice.
     * @param index2 O índice do segundo vértice.
     */
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            adjMatrixboolean[index1][index2] = false;
            adjMatrixboolean[index2][index1] = false;
        }
    }

    /**
     * Retorna um iterador que realiza uma busca em largura (BFS) a partir do vértice especificado.
     *
     * @param startVertex O vértice inicial da busca.
     * @return Um iterador contendo os vértices na ordem de visitação.
     */
    public Iterator<T> iteratorBFS(T startVertex) {
        int indexVertex = getIndex(startVertex);
        List<T> resultList = new ArrayList<>();

        if (!indexIsValid(indexVertex)) {
            return resultList.iterator();
        }

        Integer x;
        Queue<Integer> traversalQueue = new LinkedList<>();
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.add(indexVertex);
        visited[indexVertex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.remove();
            resultList.addLast(vertices[x.intValue()]);

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrixboolean[x.intValue()][i] && !visited[i]) {
                    traversalQueue.add(i);
                    visited[i] = true;
                }
            }
        }

        return resultList.iterator();
    }

    /**
     * Retorna um iterador que realiza uma busca em profundidade (DFS) a partir do vértice especificado.
     *
     * @param startVertex O vértice inicial da busca.
     * @return Um iterador contendo os vértices na ordem de visitação.
     */
    public Iterator<T> iteratorDFS(T startVertex) {
        Integer x;
        int startIndex = getIndex(startVertex);
        boolean found;
        Stack<Integer> traversalStack = new Stack<>();
        List<T> resultList = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addLast(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrixboolean[x.intValue()][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addLast(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }

        return resultList.iterator();
    }

    /**
     * Verifica se o grafo é conexo, ou seja, se existe um caminho entre todos os vértices.
     *
     * @return {@code true} se o grafo for conexo; {@code false} caso contrário.
     */
    public boolean isConnected() {
        boolean connected = false;

        if (this.numVertices > 0) {
            Iterator<T> itr = iteratorBFS(vertices[0]);
            int visti_cont = 0;

            while (itr.hasNext()) {
                itr.next();
                visti_cont++;
            }

            if (visti_cont == numVertices) {
                connected = true;
            }
        }

        return connected;
    }

}
