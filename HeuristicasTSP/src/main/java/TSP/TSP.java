package TSP;

import TSP.Network.Network;
import java.util.Arrays;

/**
 * Classe que representa uma instância do problema do Caixeiro Viajante (TSP - Traveling Salesman Problem).
 * Armazena informações relevantes sobre o problema, como nome, tipo, cidades envolvidas, tipo de peso das arestas
 * e uma possível solução ótima.
 */
public class TSP {
    /**
     * Nome da instância do problema
     * */
    private String name;

    /**
     * Tipo do problema (ex: TSP)
     * */
    private String type;

    /**
     * Comentário adicional sobre a instância
     * */
    private String comment;

    /**
     * Número de cidades (dimensão do problema)
     * */
    private int dimension;

    /**
     * Tipo de métrica para o cálculo do peso das arestas (ex: EUC_2D, GEO, etc.)
     * */
    private String edge_weight_type;

    /**
     * Lista de cidades envolvidas no problema
     * */
    private City[] cities;

    /**
     * Representação da solução ótima para o problema, se disponível
     * */
    private Network<City> solucaoOtima;

    /**
     * Construtor da classe TSP.
     *
     * @param name Nome da instância
     * @param type Tipo do problema
     * @param comment Comentário sobre a instância
     * @param dimension Número de cidades
     * @param edge_weight_type Tipo de métrica usada
     * @param cities Lista de cidades
     * @param solucaoOtima Solução ótima (caso conhecida)
     */
    public TSP(String name, String type, String comment, int dimension, String edge_weight_type, City[] cities, Network solucaoOtima) {
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.dimension = dimension;
        this.edge_weight_type = edge_weight_type;
        this.cities = cities;
        this.solucaoOtima =  solucaoOtima;
    }

    /**
     * Getter para obter o nome
     * */
    public String getName() {
        return name;
    }

    /**
     * Getter para obter o tipo
     * */
    public String getType() {
        return type;
    }

    /**
     * Getter para obter o comentario
     * */
    public String getComment() {
        return comment;
    }

    /**
     * Getter para obter a dimensão
     * */
    public int getDimension() {
        return dimension;
    }

    /**
     * Getter para obter o tipo de metrica para calcular os pesos
     * */
    public String getEdge_weight_type() {
        return edge_weight_type;
    }

    /**
     * Getter para obter as cidades do problema
     * */
    public City[] getCities() {
        return cities;
    }

    /**
     * Getter para obter a solução ótima
     * */
    public Network<City> getSolucaoOtima() {
        return solucaoOtima;
    }

    /**
     * Setter para alterar a solução ótima
     * */
    public void setSolucaoOtima(Network<City> solucaoOtima) {
        this.solucaoOtima = solucaoOtima;
    }
    /**
     * Método que retorna uma representação textual da instância TSP
     * */
    @Override
    public String toString() {
        return "TSP{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                ", dimension=" + dimension +
                ", edge_weight_type='" + edge_weight_type + '\'' +
                ", cities=" + Arrays.toString(cities) +
                ", solucaoOtima=" + solucaoOtima +
                '}';
    }
}
