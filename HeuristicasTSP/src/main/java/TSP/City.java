package TSP;

/**
 * Representa uma cidade no contexto do problema do Caixeiro Viajante.
 * Cada instância contém um nome identificador e suas coordenadas no plano.
 */
public class City {

    /**
     * Nome da cidade.
     */
    private String name;

    /**
     * Coordenada X da cidade.
     */
    private double value_x;

    /**
     * Coordenada Y da cidade.
     */
    private double value_y;

    /**
     * Construtor que cria uma nova instância de {@code City}.
     *
     * @param name    Nome da cidade.
     * @param value_x Valor da coordenada X.
     * @param value_y Valor da coordenada Y.
     */
    public City(String name, double value_x, double value_y) {
        this.name = name;
        this.value_x = value_x;
        this.value_y = value_y;
    }

    /**
     * Retorna o nome da cidade.
     *
     * @return o nome da cidade.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o valor da coordenada X.
     *
     * @return o valor da coordenada X.
     */
    public double getValue_x() {
        return value_x;
    }

    /**
     * Retorna o valor da coordenada Y.
     *
     * @return o valor da coordenada Y.
     */
    public double getValue_y() {
        return value_y;
    }

    /**
     * Calcula a distância euclidiana até outra cidade.
     *
     * @param outra A outra cidade.
     * @return Distância entre esta cidade e a outra.
     */
    public double distanciaPara(City outra) {
        double dx = this.value_x - outra.value_x;
        double dy = this.value_y - outra.value_y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Retorna uma representação em forma de string da cidade.
     *
     * @return uma string que descreve a cidade.
     */
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", value_x=" + value_x +
                ", value_y=" + value_y +
                '}';
    }
}
