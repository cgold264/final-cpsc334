/**
 * CPSC 450, HW-8
 * 
 * NAME: S. Bowers
 * DATE: Fall 2024
 *
 * Basic unit tests for HW-8
 */

package cpsc450;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


class HW8Test {

  //======================================================================
  // Only Doing Adjacency Matrix Tests for HW-8
  //======================================================================

  //----------------------------------------------------------------------
  // Prim's Algorithm

  @Test
  void primOneVertexTest() {
    Graph g = new AdjList(1);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    Map<Integer,Integer> mst = GraphAlgorithms.mst(g, l);
    assertEquals(1, mst.size());
    assertEquals(-1, mst.get(0));
  }

  @Test
  void primOneEdgeTest() {
    Graph g = new AdjList(2);
    g.addEdge(0, 1);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 10);
    Map<Integer,Integer> mst = GraphAlgorithms.mst(g, l);
    assertEquals(2, mst.size());
    assertEquals(-1, mst.get(0));
    assertEquals(0, mst.get(1));
  }

  @Test
  void primThreeEdgeTest() {
    Graph g = new AdjList(3);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 2);
    l.addLabel(0, 2, 3);
    l.addLabel(1, 2, 2);
    Map<Integer,Integer> mst = GraphAlgorithms.mst(g, l);
    assertEquals(3, mst.size());
    assertEquals(-1, mst.get(0));
    assertEquals(0, mst.get(1));
    assertEquals(1, mst.get(2));
  }
  
  @Test
  void primOppositeThreeEdgeTest() {
    Graph g = new AdjList(3);
    g.addEdge(1, 0);
    g.addEdge(2, 0);
    g.addEdge(2, 1);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(1, 0, 2);
    l.addLabel(2, 0, 3);
    l.addLabel(2, 1, 2);
    Map<Integer,Integer> mst = GraphAlgorithms.mst(g, l);
    assertEquals(3, mst.size());
    assertEquals(-1, mst.get(0));
    assertEquals(0, mst.get(1));
    assertEquals(1, mst.get(2));
  }

  @Test
  void primFiveEdgeTest() {
    Graph g = new AdjList(4);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 2);
    l.addLabel(0, 3, 3);
    l.addLabel(0, 2, 4);
    l.addLabel(1, 3, 2);
    l.addLabel(2, 3, 5);
    Map<Integer,Integer> mst = GraphAlgorithms.mst(g, l);
    assertEquals(4, mst.size());
    assertEquals(-1, mst.get(0));
    assertEquals(0, mst.get(1));
    assertEquals(0, mst.get(2));
    assertEquals(1, mst.get(3));    
  }

  @Test
  void primOppositeFiveEdgeTest() {
    Graph g = new AdjList(4);
    g.addEdge(1, 0);
    g.addEdge(3, 0);
    g.addEdge(2, 0);
    g.addEdge(3, 1);
    g.addEdge(3, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(1, 0, 2);
    l.addLabel(3, 0, 3);
    l.addLabel(2, 0, 4);
    l.addLabel(3, 1, 2);
    l.addLabel(3, 2, 5);
    Map<Integer,Integer> mst = GraphAlgorithms.mst(g, l);
    assertEquals(4, mst.size());
    assertEquals(-1, mst.get(0));
    assertEquals(0, mst.get(1));
    assertEquals(0, mst.get(2));
    assertEquals(1, mst.get(3));    
  }
  
  //----------------------------------------------------------------------
  // Bellman-Ford single-source shortest paths

  @Test
  void bellmanFordOneVertexTest() {
    Graph g = new AdjList(1);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(1, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
  }

  @Test
  void bellmanFordPositiveTwoVertexNoPath() {
    Graph g = new AdjList(2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(2, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
    assertEquals(Integer.MAX_VALUE, pathCosts.get(1));
  }

  @Test
  void bellmanFordPositiveTwoVertexWithPath() {
    Graph g = new AdjList(2);
    g.addEdge(0, 1);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 33);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(2, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
    assertEquals(33, pathCosts.get(1));
  }
  
  @Test
  void bellmanFordPositiveThreeVertexShortcut() {
    Graph g = new AdjList(3);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(0, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(1, 2, 3);
    l.addLabel(0, 2, 2);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(3, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
    assertEquals(1, pathCosts.get(1));
    assertEquals(2, pathCosts.get(2));    
  }

  @Test
  void bellmanFordPositiveThreeVertexLongRoute() {
    Graph g = new AdjList(3);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(0, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(1, 2, 2);
    l.addLabel(0, 2, 4);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(3, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
    assertEquals(1, pathCosts.get(1));
    assertEquals(3, pathCosts.get(2));    
  }

  @Test
  void bellmanFordPositiveFourVertex() {
    Graph g = new AdjList(4);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 0);
    g.addEdge(3, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(0, 3, 4);
    l.addLabel(1, 2, 6);
    l.addLabel(1, 3, 2);
    l.addLabel(2, 0, 1);
    l.addLabel(3, 2, 3);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(4, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
    assertEquals(1, pathCosts.get(1));
    assertEquals(6, pathCosts.get(2));
    assertEquals(3, pathCosts.get(3));
  }

  @Test
  void bellmanFordPositiveFourVertexDifferentStart() {
    Graph g = new AdjList(4);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 0);
    g.addEdge(3, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(0, 3, 4);
    l.addLabel(1, 2, 6);
    l.addLabel(1, 3, 2);
    l.addLabel(2, 0, 1);
    l.addLabel(3, 2, 3);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 1);
    assertEquals(4, pathCosts.size());
    assertEquals(6, pathCosts.get(0));
    assertEquals(0, pathCosts.get(1));
    assertEquals(5, pathCosts.get(2));
    assertEquals(2, pathCosts.get(3));
  }

  @Test
  void bellmanFordNegativeFourVertex() {
    Graph g = new AdjList(4);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(0, 2, 3);
    l.addLabel(1, 3, 2);
    l.addLabel(2, 3, -4);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(4, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
    assertEquals(1, pathCosts.get(1));
    assertEquals(3, pathCosts.get(2));
    assertEquals(-1, pathCosts.get(3));
  }

  @Test
  void bellmanFordNegativeFiveVertex() {
    Graph g = new AdjList(5);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(1, 4);
    g.addEdge(2, 1);
    g.addEdge(3, 2);
    g.addEdge(3, 4);
    g.addEdge(4, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 6);
    l.addLabel(0, 3, 7);
    l.addLabel(1, 2, 5);
    l.addLabel(1, 3, 8);
    l.addLabel(1, 4, -4);
    l.addLabel(2, 1, -2);
    l.addLabel(3, 2, -3);
    l.addLabel(3, 4, 9);
    l.addLabel(4, 2, 7);    
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(5, pathCosts.size());
    assertEquals(0, pathCosts.get(0));
    assertEquals(2, pathCosts.get(1));
    assertEquals(4, pathCosts.get(2));
    assertEquals(7, pathCosts.get(3));
    assertEquals(-2, pathCosts.get(4));
  }

  @Test
  void bellmanFordNegativeCycle() {
    Graph g = new AdjList(5);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(2, 4);
    g.addEdge(3, 1);
    g.addEdge(4, 3);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(0, 3, 1);
    l.addLabel(1, 2, 2);
    l.addLabel(2, 4, 3);
    l.addLabel(3, 1, -10);
    l.addLabel(4, 3, 4);
    List<Integer> pathCosts = GraphAlgorithms.shortestPaths2(g, l, 0);
    assertEquals(0, pathCosts.size());
  }

  //----------------------------------------------------------------------
  // Floyd-Warshall all-pairs shortest paths

  @Test
  void floydWarshallOneVertex() {
    Graph g = new AdjList(1);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    assertEquals(1, pathCosts.size());
    assertEquals(1, pathCosts.get(0).size());
    assertEquals(0, pathCosts.get(0).get(0));
  }
  
  @Test
  void floydWarshallTwoVertexNoPath() {
    Graph g = new AdjList(2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    assertEquals(2, pathCosts.size());
    assertEquals(2, pathCosts.get(0).size());
    assertEquals(2, pathCosts.get(1).size());
    assertEquals(0, pathCosts.get(0).get(0));
    assertEquals(Integer.MAX_VALUE, pathCosts.get(0).get(1));
    assertEquals(Integer.MAX_VALUE, pathCosts.get(1).get(0));
    assertEquals(0, pathCosts.get(1).get(1));
  }
  
  @Test
  void floydWarshallTwoVertexWithPath() {
    Graph g = new AdjList(2);
    g.addEdge(0, 1);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 33);
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    assertEquals(2, pathCosts.size());
    assertEquals(0, pathCosts.get(0).get(0));
    assertEquals(33, pathCosts.get(0).get(1));
    assertEquals(Integer.MAX_VALUE, pathCosts.get(1).get(0));
    assertEquals(0, pathCosts.get(1).get(1));
  }

  @Test
  void floydWarshallPositiveThreeVertexShortcut() {
    Graph g = new AdjList(3);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(0, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(1, 2, 3);
    l.addLabel(0, 2, 2);
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    int M = Integer.MAX_VALUE;
    assertEquals(3, pathCosts.size());
    assertEquals(List.of(0, 1, 2), pathCosts.get(0));
    assertEquals(List.of(M, 0, 3), pathCosts.get(1));
    assertEquals(List.of(M, M, 0), pathCosts.get(2));
  }
  
  @Test
  void floydWarshallPositiveFourVertex() {
    Graph g = new AdjList(4);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 0);
    g.addEdge(3, 2);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(0, 3, 4);
    l.addLabel(1, 2, 6);
    l.addLabel(1, 3, 2);
    l.addLabel(2, 0, 1);    
    l.addLabel(3, 2, 3);    
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    assertEquals(4, pathCosts.size());
    assertEquals(List.of(0, 1, 6, 3), pathCosts.get(0));
    assertEquals(List.of(6, 0, 5, 2), pathCosts.get(1));
    assertEquals(List.of(1, 2, 0, 4), pathCosts.get(2));
    assertEquals(List.of(4, 5, 3, 0), pathCosts.get(3));
  }
  
  @Test
  void floydWarshallNegativeFourVertex() {
    Graph g = new AdjList(4);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 3);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(0, 2, 3);
    l.addLabel(1, 3, 2);
    l.addLabel(2, 3, -4);
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    int M = Integer.MAX_VALUE;
    assertEquals(4, pathCosts.size());
    assertEquals(List.of(0, 1, 3, -1), pathCosts.get(0));
    assertEquals(List.of(M, 0, M, 2), pathCosts.get(1));
    assertEquals(List.of(M, M, 0, -4), pathCosts.get(2));
    assertEquals(List.of(M, M, M, 0), pathCosts.get(3));
  }

  @Test
  void floydWarshallNegativeFiveVertex() {
    Graph g = new AdjList(5);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(1, 4);
    g.addEdge(2, 1);
    g.addEdge(3, 2);
    g.addEdge(3, 4);
    g.addEdge(4, 2);    
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 6);
    l.addLabel(0, 3, 7);
    l.addLabel(1, 2, 5);
    l.addLabel(1, 3, 8);
    l.addLabel(1, 4, -4);
    l.addLabel(2, 1, -2);
    l.addLabel(3, 2, -3);
    l.addLabel(3, 4, 9);
    l.addLabel(4, 2, 7);    
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    int M = Integer.MAX_VALUE;
    assertEquals(5, pathCosts.size());
    assertEquals(List.of(0, 2, 4, 7, -2), pathCosts.get(0));
    assertEquals(List.of(M, 0, 3, 8, -4), pathCosts.get(1));
    assertEquals(List.of(M, -2, 0, 6, -6), pathCosts.get(2));
    assertEquals(List.of(M, -5, -3, 0, -9), pathCosts.get(3));
    assertEquals(List.of(M, 5, 7, 13, 0), pathCosts.get(4));    
  }

  @Test
  void floydWarshallNegativeCycle() {
    Graph g = new AdjList(5);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(2, 4);
    g.addEdge(3, 1);
    g.addEdge(4, 3);
    EdgeLabeling<Integer> l = new EdgeLabeling<>(g);
    l.addLabel(0, 1, 1);
    l.addLabel(0, 3, 1);
    l.addLabel(1, 2, 2);
    l.addLabel(2, 4, 3);
    l.addLabel(3, 1, -10);
    l.addLabel(4, 3, 4);
    List<List<Integer>> pathCosts = GraphAlgorithms.allShortestPaths(g, l);
    assertEquals(0, pathCosts.size());
  }

  
  //======================================================================
  // TODO: Design and implement the following unit tests. Note that
  // each graph below must be different. You must also draw and
  // include each graph (correctly labeled to the unit test) in your
  // write up. Your test graphs must be "interesting" (i.e., not
  // trivial for the unit test) and you need to state why you think
  // they are interesting for the test cases in your write up.
  //
  //  1. Create 2 minimum spanning tree tests using different
  //     non-trivial graphs. 
  // 
  //  2. Create 2 Bellman-Ford tests using different non-trivial
  //     directed graphs. One of your graphs should have negative
  //     weights.
  //
  //  3. Create 2 Floyd-Warshall tests using different non-trivial
  //     directed graphs. 
  //
  //======================================================================

  
}
