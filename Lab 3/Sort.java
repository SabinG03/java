public class Sort {
  
  // Merges two strings alternately.
  public static String mergeAlternately(String word1, String word2) {
    // Create a character array to store the merged string
    char[] merged = new char[word1.length() + word2.length()];
    // Initialize pointers for word1, word2, and the merged array
    int i = 0, j = 0, k = 0;

    // Merge characters from word1 and word2 alternately until one of them is fully processed
    while (i < word1.length() && j < word2.length()) {
      merged[k++] = word1.charAt(i++);
      merged[k++] = word2.charAt(j++);
    }

    // Add remaining characters from word1, if any
    while (i < word1.length()) {
      merged[k++] = word1.charAt(i++);
    }

    // Add remaining characters from word2, if any
    while (j < word2.length()) {
      merged[k++] = word2.charAt(j++);
    }

    // Convert the merged character array to a string and return
    return new String(merged);
  }

  // Main method to test the mergeAlternately function.
  public static void main(String[] args) {
    // Test cases
    String word1 = "abc";
    String word2 = "pqr";
    System.out.println(mergeAlternately(word1, word2));

    word1 = "ab";
    word2 = "pqrs";
    System.out.println(mergeAlternately(word1, word2));

    word1 = "abcd";
    word2 = "pq";
    System.out.println(mergeAlternately(word1, word2));
  }
}
