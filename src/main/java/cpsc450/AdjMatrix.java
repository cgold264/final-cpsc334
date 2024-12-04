/**
 * CPSC 450, Fall 2024
 * 
 * NAME: Connor Goldschmidt
 * DATE: Fall 2024
 */

package cpsc450;

import java.util.HashSet;
import java.util.Set;

/**
 * Adjacency Matrix implementation of the Graph interface. 
 */
public class AdjMatrix implements Graph {

  private int vertexCount;      // total number of vertices
  private int edgeCount;        // running count of number of edges
  private boolean matrix[];     // storage for the matrix as "flattened" 2D array

  /**
   * Create an adjacency matrix (graph) given a specific (fixed)
   * number of vertices.
   * @param vertices The number of vertices in the graph. 
   */ 
  public AdjMatrix(int vertices) throws GraphException {
    if (vertices <= 0){
      throw new GraphException();
    }
    vertexCount = vertices;
    edgeCount = 0;
    matrix = new boolean[(vertices * vertices)];
  }

  @Override
  public void addEdge(int x, int y) {
    if(x < vertexCount && x >= 0 && y < vertexCount && y >= 0){
      edgeCount++;
      matrix[(x + (vertexCount * y))] = true;
    }
  }

  @Override
  public void removeEdge(int x, int y) {  
    if(x < vertexCount && x >= 0 && y < vertexCount && y >= 0){
      int edgeIndex = (x + (vertexCount * y));
      if (matrix[edgeIndex]){
        edgeCount--;
      }
      matrix[edgeIndex] = false;
    }
  }

  @Override
  public Set<Integer> out(int x) {
    Set<Integer> out_set = new HashSet<>();
    for (int i =0; i < vertexCount; i++){
      if(matrix[(x + (vertexCount * i))]){
        out_set.add(i);
      }
    }
    return out_set;
  }

  @Override
  public Set<Integer> in(int x) {
    Set<Integer> in_set = new HashSet<>();
    if(x < vertexCount && x >=0 ){
      for (int i = 0; i < vertexCount; i++){
        if(matrix[(i + (vertexCount * x))]){
          in_set.add(i);
        }
      }
    }
    return in_set;
  }

  @Override
  public Set<Integer> adj(int x) {
    Set<Integer> adjset = new HashSet<>();
    if(x < vertexCount && x >=0 ){
      for (int i = 0; i < vertexCount; i++){
        if(matrix[(i + (vertexCount * x))] || matrix[(x + (vertexCount * i))]){
          adjset.add(i);
        }
      }
    }
    return adjset;
  }

  @Override
  public boolean hasEdge(int x, int y) {
    if(x < vertexCount && x >= 0 && y < vertexCount && y >= 0){
      return matrix[(x + (vertexCount * y))];
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
