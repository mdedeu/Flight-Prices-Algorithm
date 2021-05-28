import java.util.*;

public class Ejercicio1 {

  static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    Map<Integer, List<Edge>> nodes = new HashMap<>();
    // Setea los nodos y aristas del grafo (undirected)
    for (int i = 0; i < n; i++)
      nodes.put(i, new ArrayList<>());

    for (int[] flight : flights) {
      nodes.get(flight[0]).add(new Edge(flight[1], flight[2]));
    }

    return dijkstra(src,dst, nodes, k);
  }
  private static int dijkstra(int src,int dest, Map<Integer, List<Edge>> nodes,int k) {
    int minCost=-1;
    Map<Integer,Integer> distances = new HashMap<>();
    PriorityQueue<Edge> queue = new PriorityQueue<>();
    queue.add(new Edge(src, 0));
    Set<Integer> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Edge lastEdge = queue.remove();
      if (visited.contains(lastEdge.target))
        continue;
      visited.add(lastEdge.target);
      for (Edge edge : nodes.get(lastEdge.target)) {
        int totalCost = lastEdge.weight + edge.weight;
        if ((!distances.containsKey(edge.target) || totalCost <= distances.get(edge.target))) {
            distances.put(edge.target, totalCost);
            queue.add(new Edge(edge.target, totalCost));
        }
      }
      if(lastEdge.target==dest){
        minCost=distances.get(dest);
      }
    }
    return minCost;
  }

  static class Edge implements Comparable<Edge> {
    int target;
    int weight;
    int stops=0;
    public Edge(int target, int weight) {
      this.target = target;
      this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
      int c = Integer.compare(weight, o.weight);
      if(c == 0)
          return  -1;
      return 1;
    }
  }

}
