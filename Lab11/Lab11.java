import java.io.*;
import java.util.*;

public class Lab11 {

    public static void main(String[] args) {
        // Read the underground map from the CSV file
        char[][] undergroundMap = readUndergroundMap("Underground.csv");
        if (undergroundMap != null) {
            // Find the start and end points ('A' and 'B')
            Point start = findPoint(undergroundMap, 'A');
            Point end = findPoint(undergroundMap, 'B');

            if (start != null && end != null) {
                // Print the coordinates of the start and end points
                System.out.println("Start point: (" + start.x + ", " + start.y + ")");
                System.out.println("End point: (" + end.x + ", " + end.y + ")");
                
                // Calculate the total value of the shortest path
                int totalValue = findTotalValueOfShortestPath(undergroundMap, start, end);
                System.out.println("Total value of the shortest path from 'A' to 'B': " + totalValue);
            } else {
                System.out.println("Start or end point not found.");
            }
        }
    }

    // Read the underground map from a CSV file
    public static char[][] readUndergroundMap(String filename) {
        char[][] undergroundMap = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (undergroundMap == null) {
                    undergroundMap = new char[values.length][values.length];
                }
                for (int col = 0; col < values.length; col++) {
                    undergroundMap[row][col] = values[col].charAt(0);
                }
                row++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return undergroundMap;
    }

    // Find the coordinates of a specific point ('A' or 'B') on the map
    public static Point findPoint(char[][] map, char point) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == point) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    // Calculate the total value of the shortest path from start to end point
    public static int findTotalValueOfShortestPath(char[][] map, Point start, Point end) {
        int[][] values = new int[map.length][map[0].length];
        boolean[][] visited = new boolean[map.length][map[0].length];

        // Initialize values to 0 and visited to false
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                values[i][j] = 0;
                visited[i][j] = false;
            }
        }

        // traversal to find shortest path and sum up values
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(end)) {
                return values[current.x][current.y]; // Return the total value
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isValid(map, nx, ny) && map[nx][ny] != '*' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    values[nx][ny] = values[current.x][current.y] + Character.getNumericValue(map[nx][ny]);
                    queue.add(new Point(nx, ny));
                }
            }
        }

        // If no path found
        return -1;
    }

    // Check if a position is valid (within the map boundaries)
    public static boolean isValid(char[][] map, int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }

    // Class to represent a point on the map
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Override equals method for proper comparison
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        // Override hashCode method for proper hashing
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
