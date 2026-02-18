package Heuristicas.HeuristicasPesquisaLocal;

import TSP.City;
import TSP.Network.Network;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Implementação didática (simples) da heurística 3-Opt
public class Procedimento3Opt {
    private final Network<City> grafo;
    private final City[] cidades;

    public Procedimento3Opt(Network<City> grafo, City[] cidades) {
        this.grafo = grafo;
        this.cidades = cidades;
    }

    /**
     * Otimiza o tour por 3-Opt e retorna um novo tour de índices.
     */
    public List<Integer> resolver3Opt(List<Integer> tour) {
        boolean improved = true;
        List<Integer> bestTour = new ArrayList<>(tour);
        double bestDistance = calcularDistanciaTotal(bestTour);

        while (improved) {
            improved = false;
            // percorre cortes i<j<k
            int n = bestTour.size();
            for (int i = 1; i < n - 2 && !improved; i++) {
                for (int j = i + 1; j < n - 1 && !improved; j++) {
                    for (int k = j + 1; k < n && !improved; k++) {
                        // testa 4 variantes
                        for (int variant = 0; variant < 4; variant++) {
                            List<Integer> candidate = apply3OptMove(bestTour, i, j, k, variant);
                            double d = calcularDistanciaTotal(candidate);
                            if (d < bestDistance) {
                                bestDistance = d;
                                bestTour = candidate;
                                improved = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Distância final: " + bestDistance);
        return bestTour;
    }

    /**
     * Constrói um novo tour aplicando a variante de 3-Opt.
     */
    private List<Integer> apply3OptMove(List<Integer> tour, int i, int j, int k, int variant) {
        int n = tour.size();
        List<Integer> newTour = new ArrayList<>();
        // prefixo 0..i-1
        newTour.addAll(tour.subList(0, i));

        switch (variant) {
            case 0:
                // A->B->C: segmento i..k seguido do restante
                newTour.addAll(tour.subList(i, k + 1));
                newTour.addAll(tour.subList(k + 1, n));
                break;
            case 1:
                // A + rev(B) + C: i..j then rev(j+1..k) then rest
                newTour.addAll(tour.subList(i, j));
                newTour.addAll(reverseSegment(tour.subList(j, k + 1)));
                newTour.addAll(tour.subList(k + 1, n));
                break;
            case 2:
                // rev(A) + B + C: rev(i..j) then j+1..k then rest
                newTour.addAll(reverseSegment(tour.subList(i, j + 1)));
                newTour.addAll(tour.subList(j + 1, k + 1));
                newTour.addAll(tour.subList(k + 1, n));
                break;
            case 3:
                // B + A + C: j..k then i..j-1 then rest
                newTour.addAll(tour.subList(j, k + 1));
                newTour.addAll(tour.subList(i, j));
                newTour.addAll(tour.subList(k + 1, n));
                break;
        }
        return newTour;
    }

    /**
     * Inverte uma sublista.
     */
    private List<Integer> reverseSegment(List<Integer> segment) {
        List<Integer> rev = new ArrayList<>(segment);
        Collections.reverse(rev);
        return rev;
    }

    /**
     * Calcula distância total do tour fechado.
     */
    public double calcularDistanciaTotal(List<Integer> tour) {
        double total = 0;
        int n = tour.size();
        for (int t = 0; t < n - 1; t++) {
            City c1 = cidades[tour.get(t)];
            City c2 = cidades[tour.get(t + 1)];
            total += grafo.getWeight(c1, c2);
        }
        // fecha ciclo
        City last = cidades[tour.get(n - 1)];
        City first = cidades[tour.get(0)];
        total += grafo.getWeight(last, first);
        return total;
    }
}
