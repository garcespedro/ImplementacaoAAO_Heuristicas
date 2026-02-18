package Heuristicas;

import TSP.TSP;
import TSP.City;

/**
 * A classe {@code CalculosDistancias} fornece métodos para calcular distâncias
 * entre cidades e inicializar a matriz de custos para um problema de TSP.
 */
public class CalculosDistancias {
    /**
     * Calcula o valor absoluto da diferença entre dois valores.
     * <p>
     * Esse método é utilizado para determinar a distância (delta) entre dois pontos
     * em uma mesma dimensão (ex: eixo X ou Y).
     * </p>
     *
     * @param variavelA o primeiro valor (por exemplo, coordenada de um ponto)
     * @param variavelB o segundo valor (por exemplo, coordenada de outro ponto)
     * @return o valor absoluto da diferença entre {@code variavelB} e {@code variavelA}
     */
    private double calculateDeltas(double variavelA, double variavelB) {
        double cateto = variavelB - variavelA;

        // Se a diferença for negativa, converte para positivo (valor absoluto)
        if(cateto < 0) {
            cateto = cateto * -1;
        }

        return cateto;
    }

    /**
     * Metodo que retorna a distancia entre dois vertices, onde o calculo para obter
     * a distancia consiste na forma d^2= deltaX^2 + deltaY^2,
     * que como nós nunca sabemos o valor da distancia, essa formula resumesse em
     * d = raiz quadrada( deltaX^2 + deltaY^2)
     * Metodo de calcular: EDGE_WEIGHT_TYPE : EUC_2D, meter verificação para caso
     * o edge_Weight_Type for este levar apra este calculateDistante se não leva para outro
     * */
    private double caluclateDistanteEUC_2D(double deltaX, double deltaY) {
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Nota: Funciona
     * Metodo que inicializa a nossa matriz de custos, de acordo com a dimensão do nosso TSP
     * , ou seja, o numero de cidades que constituem o nosso problema.
     * */
    public double[][] inicializaMatrizCustos(TSP tsp) {
        double[][] matriz_custos = new double[tsp.getDimension()][tsp.getDimension()];
        City[] cities = tsp.getCities();
        
        if (tsp.getEdge_weight_type().equals("EUC_2D")) {
            for(int i = 0; i < tsp.getDimension(); i++) {
                for(int j = 0; j < tsp.getDimension(); j++) {
                    double deltaX = calculateDeltas(cities[i].getValue_x(), cities[j].getValue_x());
                    double deltaY = calculateDeltas(cities[i].getValue_y(), cities[j].getValue_y());

                    matriz_custos[i][j] = caluclateDistanteEUC_2D(deltaX, deltaY);
                }
            }
        } else {
            System.out.println("Novo tipo de métrica para calcular os pesos das arestas: " + tsp.getDimension());
        }

        return matriz_custos;
    }

}
