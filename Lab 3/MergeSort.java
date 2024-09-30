public class MergeSort {

  // Merges two sorted strings into a single sorted string.
  public static String mergeSorted(String word1, String word2) {
    // Create a character array to store the merged string
    char[] merged = new char[word1.length() + word2.length()];
    // Initialize pointers for word1, word2, and the merged array
    int i = 0, j = 0, k = 0;

    // Iterate through both strings until one of them is fully processed
    while (i < word1.length() && j < word2.length()) {
      // Compare characters from both strings and add the smaller one to the merged
      if (word1.charAt(i) <= word2.charAt(j)) {
        merged[k++] = word1.charAt(i++);
      } else {
        merged[k++] = word2.charAt(j++);
      }
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

  // Main method to test the mergeSorted function.
  public static void main(String[] args) {
    String word1 = "abcdhij";
    String word2 = "efg";
    System.out.println(mergeSorted(word1, word2));
  }
}
