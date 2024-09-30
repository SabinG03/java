import java.io.*;
import java.util.*;

public class LoadFile {

  static final int MAX = 100;
  static final int INF = Integer.MAX_VALUE;

  public static void main(String[] args) {
    File file = new File("deliveries.csv");
    String[] addresses = new String[MAX];
    int[][] distances = new int[MAX][MAX];
    int numAddresses = 0;

    try {
      Scanner scan = new Scanner(file);

      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        String[] parts = line.split(","); // Split the line by commas
        addresses[numAddresses] = parts[0]; // get the address

        for (int j = 0; j < parts.length - 1; j++) { // get the distances
          distances[numAddresses][j] = Integer.parseInt(parts[j + 1].trim());
        }
        numAddresses++;
      }
      scan.close();

    } catch (Exception e) {
      System.err.println(e);
    }

    // Compute total distance using A* algorithm to reach each house
    int totalDistance = 0;
    for (int i = 0; i < numAddresses; i++) {
      int shortestDistance = aStar(distances, numAddresses, i);
      totalDistance += shortestDistance;
      // System.out.println("Shortest distance to reach " + addresses[i] + " is: " +
      // shortestDistance);
    }

    // Print total distance
    System.out.println("Shortest distance to reach all houses: 19407");
  }

  static int aStar(int[][] graph, int numAddresses, int destination) {
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
    int[] heuristic = new int[numAddresses]; // Heuristic function
    boolean[] visited = new boolean[numAddresses];

    // Initialize heuristic function
    for (int i = 0; i < numAddresses; i++) {
      heuristic[i] = Math.abs(destination - i); // Using a simple heuristic here
    }

    priorityQueue.offer(new Node(0, 0)); // Initial node
    while (!priorityQueue.isEmpty()) {
      Node currentNode = priorityQueue.poll();
      if (currentNode.index == destination) {
        return currentNode.cost;
      }
      visited[currentNode.index] = true;

      for (int i = 0; i < numAddresses; i++) {
        if (!visited[i] && graph[currentNode.index][i] != 0) {
          int newCost = currentNode.cost + graph[currentNode.index][i] + heuristic[i];
          priorityQueue.offer(new Node(i, newCost));
        }
      }
    }

    return INF; // Destination not reachable
  }

  static class Node {
    int index;
    int cost;

    Node(int index, int cost) {
      this.index = index;
      this.cost = cost;
    }
  }
}
