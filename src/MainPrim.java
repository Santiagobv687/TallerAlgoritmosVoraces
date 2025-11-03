import java.util.*;

public class MainPrim {
    static class Edge {
        int destino, peso;
        Edge(int d, int p) { destino = d; peso = p; }
    }

    public static int primMST(List<List<Edge>> grafo) {
        int V = grafo.size();
        boolean[] visitado = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.peso));
        pq.add(new Edge(0, 0)); // Inicia desde vértice 0

        int costoTotal = 0;
        while (!pq.isEmpty()) {
            Edge actual = pq.poll();
            int v = actual.destino;
            if (visitado[v]) continue;

            visitado[v] = true;
            costoTotal += actual.peso;

            for (Edge vecino : grafo.get(v))
                if (!visitado[vecino.destino])
                    pq.add(new Edge(vecino.destino, vecino.peso));
        }
        return costoTotal;
    }

    public static void main(String[] args) {
        int V = 5; // Número de vértices
        List<List<Edge>> grafo = new ArrayList<>();

        // Inicializar listas de adyacencia
        for (int i = 0; i < V; i++) grafo.add(new ArrayList<>());

        // Agregar aristas (no dirigido)
        grafo.get(0).add(new Edge(1, 2));
        grafo.get(0).add(new Edge(3, 6));
        grafo.get(1).add(new Edge(0, 2));
        grafo.get(1).add(new Edge(2, 3));
        grafo.get(1).add(new Edge(3, 8));
        grafo.get(1).add(new Edge(4, 5));
        grafo.get(2).add(new Edge(1, 3));
        grafo.get(2).add(new Edge(4, 7));
        grafo.get(3).add(new Edge(0, 6));
        grafo.get(3).add(new Edge(1, 8));
        grafo.get(4).add(new Edge(1, 5));
        grafo.get(4).add(new Edge(2, 7));

        int costo = primMST(grafo);
        System.out.println("Costo total del MST (Prim): " + costo);
    }
}
