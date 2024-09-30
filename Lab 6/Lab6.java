import java.util.ArrayList;
import java.util.List;

public class Lab6 {

  // Method to calculate the number of steps for a given number according to the
  // prompt
  static int Steps(long n) {
    int steps = 0;
    while (n != 1) {
      if (n % 2 == 0) {
        n /= 2;
      } else {
        n = 3 * n + 1;
      }
      steps++; // Increment steps here
    }
    return steps;
  }

  // number of steps
  static List<Long> findLargestConsecutiveGroup(long limit) {
    int maxSteps = 0;
    List<Long> largestGroup = new ArrayList<>();
    List<Long> currentGroup = new ArrayList<>();
    int currentSteps = 0;

    // Iterate through numbers up to the specified limit
    for (long num = 2; num < limit; num++) {
      int steps = Steps(num);

      // If the number of steps is the same as the current group, add the number to
      // the group
      if (steps == currentSteps) {
        currentGroup.add(num);
      } else {
        // If the current group is larger than the largest group, update the largest
        // group
        if (currentGroup.size() > largestGroup.size()) {
          largestGroup = new ArrayList<>(currentGroup);
        }
        // Clear the current group and add the current number to start a new group
        currentGroup.clear();
        currentGroup.add(num);
        currentSteps = steps; // Update the current number of steps
      }

      // Update the maximum number of steps if needed
      if (steps > maxSteps) {
        maxSteps = steps;
      }
    }

    return largestGroup;
  }

  public static void main(String[] args) {
    long limit = 500000000;
    // Find the largest group of consecutive numbers
    List<Long> largestGroup = findLargestConsecutiveGroup(limit);

    // Print the result
    System.out.println("Largest group of consecutive numbers requiring " + Steps(largestGroup.get(0)) + " steps: "
        + largestGroup);
  }
}