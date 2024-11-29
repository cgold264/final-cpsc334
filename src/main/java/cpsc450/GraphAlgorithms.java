/**
 * CPSC 450, HW-8
 *
 * NAME: <YOUR NAME>
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
    // TODO: Implement Prim's mst algorithm
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
    // TODO: Implement the Bellman-Ford algorithm
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
    // TODO: Implement the Floyd-Warshall algorithm
  }
  
}

