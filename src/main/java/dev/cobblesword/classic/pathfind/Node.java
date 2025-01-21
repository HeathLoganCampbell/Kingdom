package dev.cobblesword.classic.pathfind;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
      private static int idCounter = 0;
      public int id;

      public Node parent = null;

      public List<Edge> neighbors;

      public double f = Double.MAX_VALUE;

      @Getter
      private int x;
      @Getter
      private int y;

      Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.id = idCounter++;
            this.neighbors = new ArrayList<>();
      }

      @Override
      public int compareTo(Node n) {
            return Double.compare(this.f, n.f);
      }

      public double calculateHeuristic(Node target) {
            return Math.abs(this.x - target.x) + Math.abs(this.y - target.y);
      }

      public static class Edge {
            Edge(Node node){
                  this.node = node;
            }

            public Node node;
      }

      public void addBranch(Node node){
            Edge newEdge = new Edge(node);
            neighbors.add(newEdge);
      }
}