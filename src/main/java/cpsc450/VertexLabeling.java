/**
 * CPSC 450, HW-3
 * 
 * NAME: Connor Goldschmidt
 * DATE: Fall 2024
 */

package cpsc450;

import java.util.ArrayList;
import java.util.Optional;


/**
 * A vertex labeling for a given graph, where labels can be of any
 * type and a graph can have many vertex labelings.
 */
public class VertexLabeling<T> {

  private Graph graph;           // the graph to be labeled
  private ArrayList<T> labels;   // the graph vertex labels
  
  /**
   * Create a vertex labeling for a graph.
   * @param graph The graph to label.
   */ 
  public VertexLabeling(Graph graph) {
    this.graph = graph;
    this.labels = new ArrayList<>(); 
    for(int i = 0; i < this.graph.vertices(); i++){
      this.labels.add(null);
    }
  }

  /**
   * Check if a vertex has a label. 
   * @param x The vertex to check.
   * @returns True if the vertex has a label, false otherwise.
   */
  boolean hasLabel(int x) {
    if (x >= 0 && x < this.graph.vertices() && this.labels.get(x) != null){
      return true;
    }
    return false;
  }
  
  /**
   * Add a label to a vertex in the graph. If the vertex is not valid,
   * has no effect.
   * @param x The vertex to label.
   * @param label The label to assign to the vertex.
   */ 
  public void addLabel(int x, T label) {
    if(x >= 0 && x < this.graph.vertices() && this.graph.hasVertex(x)){
      labels.set(x, label);
    }
  }

  /**
   * Remove a label from a vertex in the graph. If the vertex is not
   * valid, or if the vertex does not have a label, has no effect.
   * @param x The vertex to remove the label from.
   */
  public void removeLabel(int x) {
    if(x >= 0 && x < this.graph.vertices() && this.graph.hasVertex(x)){
      labels.set(x, null);
    }
  }

  /**
   * Returns the label for the given vertex. 
   * @param x The vertex. 
   * @returns The label as an optional value.
   */
  public Optional<T> getLabel(int x) {
    if(x >= 0 && x < this.graph.vertices()){
      return Optional.ofNullable(this.labels.get(x));
    }
    return Optional.empty();
  }

}
