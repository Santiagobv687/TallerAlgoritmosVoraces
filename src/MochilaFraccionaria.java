import java.util.Arrays;
import java.util.Comparator;

class Item {
    double peso, valor;
    Item(double peso, double valor) {
        this.peso = peso;
        this.valor = valor;
    }
}

public class MochilaFraccionaria {
    public static double resolver(Item[] items, double capacidad) {
        // Ordenar por valor/peso descendente
        Arrays.sort(items, Comparator.comparingDouble(i -> -i.valor / i.peso));

        double valorTotal = 0.0;
        double pesoActual = 0.0;

        for (Item item : items) {
            if (pesoActual + item.peso <= capacidad) {
                // Se toma todo el objeto
                valorTotal += item.valor;
                pesoActual += item.peso;
            } else {
                // Se toma fracción
                double restante = capacidad - pesoActual;
                valorTotal += item.valor * (restante / item.peso);
                break;
            }
        }
        return valorTotal;
    }

    public static void main(String[] args) {
        Item[] items = { new Item(10, 60), new Item(20, 100), new Item(30, 120) };
        double capacidad = 50;
        System.out.println("Valor máximo = " + resolver(items, capacidad));
    }
}
