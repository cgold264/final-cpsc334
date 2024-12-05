/**
 * CPSC 450, HW-8
 *
 * NAME: Connor Goldschmidt
 * DATE: Fall 2024
 *
 */ 

package cpsc450;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;


/** 
 * Suite of graph-based algorithms. 
 */
public class GraphAlgorithms {

  /**
   * Computes the minimim spanning tree using Prim's algorithm of the
   * given graph (treated as if it were undirected) and correspondign
   * (integer) edge labeling.
   * @param g The given undirected, connected graph.
   * @param l The given edge (weighted) labeling.
   * @returns The minimim spanning tree as a mapping from children
   * vertices to parent vertices with root vertex 0. 
   */
  public static Map<Integer,Integer> mst(Graph g, EdgeLabeling<Integer> l) {
    Map<Integer, Integer> T = new HashMap<>();
    Set<Integer> X = new HashSet<>();
    X.add(0);
    T.put(0, -1);
    while(true){
      Integer minWeight = Integer.MAX_VALUE;
      Integer uPrime = null;
      Integer vPrime = null;
      for (Integer u : X) {
        for (Integer v : g.adj(u)) {
          if (!X.contains(v)) {
            Integer weight;
            if(l.getLabel(u, v).isEmpty()){
              weight = l.getLabel(v, u).get();
            } else {
              weight = l.getLabel(u, v).get();
            }
            if (weight < minWeight) {
                minWeight = weight;
                uPrime = u;
                vPrime = v;
            }
          } 
        }
      }
      if (vPrime == null) {
        break;
      }
      X.add(vPrime);
      T.put(vPrime, uPrime);
    }
    return T;
    
  }
  
  /**
   * Computes the single-source shortest (integer) weighted paths from
   * the given source vertex using the Bellman-Ford algorithm. Assumes
   * weights are given as (possibly negative) integers where the
   * maximum weight is Integer.MAX_VALUE.
   * @param g The given directed graph.
   * @param l The given edge (integer-weighted) labeling
   * @param s The source vertex
   * @returns The minimum path cost from s to each vertex v (reachable
   * from s) given as a list with indexes as vertices and values as
   * path costs from s. If the graph has a negative cycle, an empty
   * list is returned. 
   */
  public static List<Integer> shortestPaths2(Graph g, EdgeLabeling<Integer> l, int s) {
    List<Integer> dist = new ArrayList<>();
    for(int i = 0; i < g.vertices(); i++){
      dist.add(i, Integer.MAX_VALUE);
    }
    dist.set(s, 0);
    for(int i = 1; i < g.vertices(); i++){
      for(int u = 0; u < g.vertices(); u++){
        for(Integer v : g.adj(u)){
            if(dist.get(u) != Integer.MAX_VALUE && g.hasEdge(u, v) && dist.get(v) > (dist.get(u) + l.getLabel(u, v).get())){
              dist.set(v, (dist.get(u) + l.getLabel(u, v).get()));
            }
        }
      }
    }

    for(int u = 0; u < g.vertices(); u++){
      for(Integer v : g.adj(u)){
        if(g.hasEdge(u, v) && dist.get(v) > (dist.get(u) + l.getLabel(u, v).get())){
          return new ArrayList<>();
        }
      }
    }
    return dist;
  }
  
  /**
   * Computes all pairs short (integer) weighted paths using the
   * Floyd-Warshall algorithm. Assumes (possibly negative) weights are
   * given as integers where the maximum weight is Integer.MAX_VALUE.
   * @param g The given directed graph.
   * @param l The given edge (integer-weighted) labeling
   * @returns A list of shortest-path-weight lists for each vertex in
   * the graph such that the shortest path weight from a vertex x to a
   * vertex y is given by weights.get(x).get(y). Returns an empty list
   * if the graph has a negative cycle.
   */
  public static List<List<Integer>> allShortestPaths(Graph g, EdgeLabeling<Integer> l) {
    int n = g.vertices();
    int[][][] A = new int[n + 1][n][n];

    for (int u = 0; u < n; u++) {
        for (int v = 0; v < n; v++) {
            if (u == v) {
                A[0][u][v] = 0;
            } else if (g.hasEdge(u, v)) {
                A[0][u][v] = l.getLabel(u, v).orElse(Integer.MAX_VALUE);
            } else {
                A[0][u][v] = Integer.MAX_VALUE;
            }
        }
    }

    for (int k = 1; k <= n; k++) {
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                int withoutK = A[k - 1][u][v];
                int withK;
                if (A[k - 1][u][k - 1] != Integer.MAX_VALUE && A[k - 1][k - 1][v] != Integer.MAX_VALUE) {
                    withK = A[k - 1][u][k - 1] + A[k - 1][k - 1][v];
                } else {
                    withK = Integer.MAX_VALUE;
                }
                A[k][u][v] = Math.min(withoutK, withK);
            }
        }
    }

    for (int u = 0; u < n; u++) {
        if (A[n][u][u] < 0) {
            return new ArrayList<>();
        }
    }

    List<List<Integer>> dist = new ArrayList<>();
    for (int u = 0; u < n; u++) {
        List<Integer> row = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            row.add(A[n][u][v]);
        }
        dist.add(row);
    }

    return dist;
    }
}

