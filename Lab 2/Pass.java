public class Pass {
  public static void main(String[] args) {
    // Initialize the input string representing attendance record
    String s1 = "PLPLPL";
    // Initialize counts for late and absent occurrences
    int lateCount = 0;
    int absentCount = 0;
    boolean t;

    // Call the test method to determine if the attendance record passes
    t = test(s1, lateCount, absentCount);
    if (t) {
      System.out.println("Pass");
    } else {
      System.out.println("Fail");
    }
  }

  // Method to test if the attendance record passes
  public static boolean test(String s1, int lateCount, int absentCount) {
    // Check if student is absent twice or late 3 times
    if (absentCount == 2 || lateCount == 3) {
      return false;
    }
    // If the attendance record is empty, it passes
    if (s1.length() == 0) {
      return true;
    }
    // If the first character in the attendance record is 'L', increment late count
    if (s1.charAt(0) == 'L') {
      lateCount++;
      // Recursively call test method with updated late count and remaining attendance
      return test(s1.substring(1), lateCount, absentCount);
    }
    // If the first character in the attendance record is 'A', increment absent
    else if (s1.charAt(0) == 'A') {
      absentCount++;
      // Recursively call test method with reset late count and updated absent count
      return test(s1.substring(1), 0, absentCount);
    }
    // If the first character in the attendance record is 'P', continue without
    return test(s1.substring(1), 0, absentCount);
  }
}
