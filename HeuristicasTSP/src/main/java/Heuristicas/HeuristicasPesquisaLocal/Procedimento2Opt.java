package Heuristicas.HeuristicasPesquisaLocal;

import TSP.City;
import TSP.Network.Network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementação do algoritmo de otimização 2-opt para resolver o problema do caixeiro-viajante (TSP).
 */
public class Procedimento2Opt {
    private final Network<City> grafo;
    private final List<City> cidades;

    /**
     * Construtor.
     *
     * @param grafo   Rede de cidades com distâncias.
     * @param cidades Lista de cidades na ordem dos índices.
     */
    public Procedimento2Opt(Network<City> grafo, List<City> cidades) {
        this.grafo = grafo;
        this.cidades = cidades;
    }

    /**
     * Calcula a distância total de um determinado percurso.
     *
     * @param percurso Lista de índices representando o percurso.
     * @return Distância total do percurso.
     */
    public double calcularDistanciaTotal(List<Integer> percurso) {
        double distanciaTotal = 0.0;
        for (int i = 0; i < percurso.size() - 1; i++) {
            City c1 = cidades.get(percurso.get(i));
            City c2 = cidades.get(percurso.get(i + 1));
            distanciaTotal += grafo.getWeight(c1, c2);
        }
        City ultima = cidades.get(percurso.get(percurso.size() - 1));
        City primeira = cidades.get(percurso.get(0));
        distanciaTotal += grafo.getWeight(ultima, primeira);
        return distanciaTotal;
    }

    /**
     * Aplica a heurística 2-opt para melhorar um percurso inicial.
     *
     * @param percursoInicial Lista de índices representando o percurso inicial.
     * @return Novo percurso otimizado.
     */
    public List<Integer> resolver2Opt(List<Integer> percursoInicial) {
        List<Integer> melhorPercurso = new ArrayList<>(percursoInicial);
        double melhorDistancia = calcularDistanciaTotal(melhorPercurso);
        boolean melhorou;

        do {
            melhorou = false;
            for (int i = 1; i < melhorPercurso.size() - 1; i++) {
                for (int j = i + 1; j < melhorPercurso.size(); j++) {
                    List<Integer> novoPercurso = new ArrayList<>(melhorPercurso);
                    Collections.reverse(novoPercurso.subList(i, j + 1));
                    double novaDistancia = calcularDistanciaTotal(novoPercurso);

                    if (novaDistancia < melhorDistancia) {
                        melhorDistancia = novaDistancia;
                        melhorPercurso = novoPercurso;
                        melhorou = true;
                    }
                }
            }
        } while (melhorou);

        return melhorPercurso;
    }
}
