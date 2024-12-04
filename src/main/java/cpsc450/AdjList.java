/**
 * CPSC 450, HW-3
 * 
 * NAME: Connor Goldschmidt
 * DATE: Fall, 2024 
 */

package cpsc450;

import static java.lang.System.out;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Basic adjacency List implementation of the Graph interface.
 */
public class AdjList implements Graph {

  private int vertexCount;                     // total number of vertices
  private int edgeCount;                       // running count of number of edges
  private Map<Integer,Set<Integer>> outEdges;  // storage for the out edges
  private Map<Integer,Set<Integer>> inEdges;   // storage for the in edges

  /**
   * Create an adjacency list (graph) given a specific (fixed) number
   * of vertices.
   * @param vertices The number of vertices of the graph.
   */
  public AdjList(int vertices) throws GraphException {
    if (vertices <= 0){
      throw new GraphException();
    }
    vertexCount = vertices;
    edgeCount = 0;
    outEdges = new HashMap<Integer,Set<Integer>>();
    inEdges = new HashMap<Integer,Set<Integer>>();

  }

  @Override
  public void addEdge(int x, int y) {
    if(x >= 0 && x < vertexCount && y >= 0 && y < vertexCount){
      edgeCount++;
      if(!inEdges.containsKey(y)){
        inEdges.put(y, new HashSet<Integer>());
      }
      inEdges.get(y).add(x);
      if(!outEdges.containsKey(x)){
        outEdges.put(x, new HashSet<Integer>());
      }
      outEdges.get(x).add(y);
    }
  }

  @Override
  public void removeEdge(int x, int y) {
    if( x >= 0 && x < vertexCount && y >= 0 && y < vertexCount){
      if(inEdges.containsKey(y) && outEdges.containsKey(x)){
        edgeCount--;

        inEdges.get(y).remove(x);
        outEdges.get(x).remove(y);
      }
    }
  }

  @Override
  public Set<Integer> out(int x) {
    Set<Integer> tempOutEdges = new HashSet<>();
    if(outEdges.get(x) != null)
      tempOutEdges.addAll(outEdges.get(x));
    return tempOutEdges;
  }

  @Override
  public Set<Integer> in(int x) {
    Set<Integer> tempInEdges = new HashSet<>();
    if(inEdges.get(x) != null)
      tempInEdges.addAll(inEdges.get(x));
    return tempInEdges;
  } 

  @Override
  public Set<Integer> adj(int x) {
    Set<Integer> adjset = new HashSet<>();
    if(outEdges.get(x) != null)
      adjset.addAll(outEdges.get(x));
    if(inEdges.get(x) != null)
      adjset.addAll(inEdges.get(x));
    return adjset;
  }

  @Override
  public boolean hasEdge(int x, int y) {
    if(outEdges.containsKey(x) && outEdges.get(x).contains(y)){
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean hasVertex(int x) {
    return (x < vertexCount && x >= 0);
  }

  @Override
  public int vertices() {
    return vertexCount;
  }
  
  @Override
  public int edges() {
    return edgeCount;
  }
}
