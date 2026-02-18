package TSP;

import TSP.Network.Network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe responsável por importar instâncias de problemas TSP (Traveling Salesman Problem)
 * a partir de ficheiros no formato da TSPLIB.
 */
public class ImportarTSP {

    /**
     * Importa os dados de um ficheiro .tsp e retorna um objeto {@link TSP} correspondente.
     *
     * @param filePath Caminho para o ficheiro TSP.
     * @return Objeto {@link TSP} preenchido com os dados do ficheiro ou {@code null} se ocorrer um erro.
     */
    public TSP importarTSP(String filePath) {
        //Variaveis que correspondem a campos da classe TSP
        TSP tspProblem = null;
        String nameTSP = "";
        String type = "";
        String comment = "";
        int dimension = 0;
        String edgeWeightType = "";
        ArrayList<City> listCities = new ArrayList<City>();

        //Variavel que ajuda a determinar se estamos ou já passamos pela linha: "EDGE_WEIGHT_TYPE"
        boolean inNodeSection = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            //Leitura de cada linha do ficheiro até chegar ao fim do mesmo
            while ((line = br.readLine()) != null) {
                line = line.trim();

                //Verifica se a linha atual está vazia, se sim não executa nada
                if (!line.isEmpty()) {
                    if (inNodeSection) {

                        //Verifica cheagmos há linha EOF, se não estamos a ler a linha de uma cidade
                        if (!line.equalsIgnoreCase("EOF")) {
                            String[] parts = line.split("\\s+");
                            if (parts.length >= 3) {
                                try {
                                    String nameCity = parts[0].toString();
                                    double x = Double.parseDouble(parts[1]);
                                    double y = Double.parseDouble(parts[2]);
                                    City city = new City(nameCity, x, y);
                                    listCities.add(city);
                                } catch (NumberFormatException e) {
                                    System.err.println("Erro ao converter os valores de coordenadas: " + line);
                                }
                            }
                        }
                    } else {
                        //Lê os valores de cada campo do ficheiro e guarda na variavel correspondente
                        if (line.startsWith("NAME")) {
                            nameTSP = line.split(":")[1].trim();
                        } else if (line.startsWith("TYPE")) {
                            type = line.split(":")[1].trim();
                        } else if (line.startsWith("COMMENT")) {
                            comment = line.split(":")[1].trim();
                        } else if (line.startsWith("DIMENSION")) {
                            try {
                                dimension = Integer.parseInt(line.split(":")[1].trim());
                            } catch (NumberFormatException e) {
                                System.err.println("Erro ao converter a dimensão: " + line);
                            }
                        } else if (line.startsWith("EDGE_WEIGHT_TYPE")) {
                            edgeWeightType = line.split(":")[1].trim();
                        } else if (line.equalsIgnoreCase("NODE_COORD_SECTION")) {
                            inNodeSection = true;
                        }
                    }
                }
            }

            //Converto a lista de cidades, num array
            City[] cities = new City[dimension];
            cities = listCities.toArray(cities);

            //Inicialização da solucaoOtima incial, que vai ser 0
            Network solucaoOtima = new Network(cities, dimension);

            //Inicialização do TSP
            tspProblem = new TSP(nameTSP, type, comment, dimension, edgeWeightType, cities, solucaoOtima);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tspProblem;
    }
}
