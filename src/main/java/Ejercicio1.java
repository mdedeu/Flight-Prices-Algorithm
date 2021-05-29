import java.util.*;

public class Ejercicio1 {

  static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    Map<Integer, List<Edge>> nodes = new HashMap<>();
    //Set a map where each node has a list of edges
    for (int i = 0; i < n; i++)
      nodes.put(i, new ArrayList<>());
    //initialize map
    for (int[] flight : flights) {
      nodes.get(flight[0]).add(new Edge(flight[1], flight[2],0));
    }
    //get least expensive path with k or less stops
    return dijkstra(src,dst, nodes, k);
  }
  private static int dijkstra(int src,int dest, Map<Integer, List<Edge>> nodes,int k) {
    int minCost = -1;
    Map<Integer, Integer> distances = new HashMap<>();
    PriorityQueue<Edge> queue = new PriorityQueue<>();

    queue.add(new Edge(src, 0,0));
    while (!queue.isEmpty()) {
      Edge lastEdge = queue.remove();
      for (Edge edge : nodes.get(lastEdge.target)) {
        int totalCost = lastEdge.weight + edge.weight;
        if ((!distances.containsKey(edge.target) || totalCost <= distances.get(edge.target))) {
            distances.put(edge.target, totalCost);
            queue.add(new Edge(edge.target, totalCost,lastEdge.length+1));
        }
        if(edge.target==dest && (lastEdge.getLength() <= k )){
          minCost=distances.get(dest);
        }
      }
    }
    return minCost;
  }

  static class Edge implements Comparable<Edge> {
    int target;
    int weight;
    int length; //length of the path till then
    public Edge(int target, int weight, int length) {
      this.target = target;
      this.weight = weight;
      this.length = length;
    }
    public int getLength(){
      return length;
    }
    @Override
    public int compareTo(Edge o) {
      int c = Integer.compare(o.weight, weight);
      if(c==0){
        return Integer.compare(o.getLength(),length);
      }
      return c;

    }
  }

}
