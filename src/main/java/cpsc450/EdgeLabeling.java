/**
 * CPSC 450, HW-3
 *
 * NAME: Connor Goldschmidt
 * DATE: Fall 2024
 */

package cpsc450;

import java.util.HashMap;
import java.util.Optional;


/**
 * An edge labeling for a given graph, where labels can be of any
 * type and a graph can have many edge labelings.
 */
public class EdgeLabeling<T> {

  private Graph graph;                                  // the graph to be labeled
  private HashMap<Integer,HashMap<Integer,T>> labels;   // the graph edge labels

  /**
   * Create an edge labeling for the given graph.
   * @param graph The graph to label.
   */ 
  public EdgeLabeling(Graph graph) {
    this.graph = graph;
    this.labels = new HashMap<>();
  }

  /**
   * Check to see if a label exists on an edge. Only returns true if
   * underlying graph still has the given edge. 
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   * @returns True if the edge has a label, false otherwise.
   */
  public boolean hasLabel(int x, int y) {
    if(this.graph.hasEdge(x, y) && this.labels.containsKey(x)){
      return this.labels.get(x).get(y) != null;
    }
    removeLabel(x, y);
    return false;
  }
  
  /**
   * Add or overwrite an edge label. The edge must exist in the
   * underlying graph.
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   */ 
  public void addLabel(int x, int y, T label) {
    if(this.graph.hasEdge(x, y)){
      if(!this.labels.containsKey(x)){
        this.labels.put(x, new HashMap<>());
      }
      this.labels.get(x).put(y, label);
    }
  }

  /**
   * Remove an edge label if it exists. 
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   */ 
  public void removeLabel(int x, int y) {
    if(!this.labels.containsKey(x)){
      return;
    }
    this.labels.get(x).remove(y);
  }

  /**
   * Returns the label for the given edge, if it exists. 
   * @param x The start (from) vertex of the edge.
   * @param y The end (to) vertex of the edge.
   * @returns The label or empty as an optional value.
   */ 
  public Optional<T> getLabel(int x, int y) throws GraphException {
    if(hasLabel(x, y) && this.graph.hasEdge(x, y)){
      return Optional.ofNullable(this.labels.get(x).get(y));
    }
    return Optional.empty();
  }

}
