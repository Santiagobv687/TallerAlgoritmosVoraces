import java.util.*;

public class MainKruskal {
    static class Edge implements Comparable<Edge> {
        int u, v, peso;
        Edge(int u, int v, int peso) { this.u = u; this.v = v; this.peso = peso; }
        public int compareTo(Edge e) { return this.peso - e.peso; }
    }

    static int find(int[] padre, int i) {
        if (padre[i] != i)
            padre[i] = find(padre, padre[i]);
        return padre[i];
    }

    static void union(int[] padre, int[] rango, int x, int y) {
        int raizX = find(padre, x);
        int raizY = find(padre, y);
        if (rango[raizX] < rango[raizY]) padre[raizX] = raizY;
        else if (rango[raizX] > rango[raizY]) padre[raizY] = raizX;
        else { padre[raizY] = raizX; rango[raizX]++; }
    }

    static int kruskalMST(List<Edge> aristas, int V) {
        Collections.sort(aristas); // Ordenar por peso
        int[] padre = new int[V];
        int[] rango = new int[V];
        for (int i = 0; i < V; i++) padre[i] = i;

        int costoTotal = 0, aristasTomadas = 0;
        for (Edge e : aristas) {
            int x = find(padre, e.u), y = find(padre, e.v);
            if (x != y) {
                union(padre, rango, x, y);
                costoTotal += e.peso;
                aristasTomadas++;
                if (aristasTomadas == V - 1) break;
            }
        }
        return costoTotal;
    }

    public static void main(String[] args) {
        int V = 5;
        List<Edge> aristas = new ArrayList<>();

        // Agregar aristas (no dirigido)
        aristas.add(new Edge(0, 1, 2));
        aristas.add(new Edge(0, 3, 6));
        aristas.add(new Edge(1, 2, 3));
        aristas.add(new Edge(1, 3, 8));
        aristas.add(new Edge(1, 4, 5));
        aristas.add(new Edge(2, 4, 7));

        int costo = kruskalMST(aristas, V);
        System.out.println("Costo total del MST (Kruskal): " + costo);
    }
}
