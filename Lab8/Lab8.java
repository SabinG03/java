import java.util.PriorityQueue;

// Node class represents a node in the Huffman tree
class Node implements Comparable<Node> {
  char data; // Character stored in the node
  int frequency; // Frequency of the character
  Node left, right; // Left and right child nodes

  // Constructor to initialize the node
  Node(char data, int frequency) {
    this.data = data;
    this.frequency = frequency;
    left = right = null;
  }

  // compareTo method to compare nodes based on their frequencies
  public int compareTo(Node node) {
    return this.frequency - node.frequency;
  }
}

public class Lab8 {

  // Method to count the number of bits required to represent the uncompressed
  // string
  public static int getUncompressedSize(String input) {
    String Uncomp = "";
    for (int j = 0; j < input.length(); j++) {
      // Convert each character in the input string to its binary representation and
      // append to Uncomp
      Uncomp += Integer.toBinaryString((int) input.charAt(j));
    }
    return Uncomp.length(); // Return the length of the uncompressed string
  }

  // Method to count the number of bits required to represent the Huffman encoded
  // string
  public static int getCompressedSize(Node root, String input, String arr[]) {
    String compString = "";
    for (int i = 0; i < input.length(); i++) {
      // Encode each character in the input string using the Huffman tree
      compString += arr[(int) input.charAt(i)];
    }
    return compString.length(); // Return the length of the compressed string
  }

  public static void main(String[] args) {
    String input = "to be or not to be";

    // Counting frequencies of characters in the input string
    int[] frequency = new int[256]; // Assuming ASCII characters
    for (char c : input.toCharArray()) {
      frequency[c]++; // Increment the frequency of the character c
    }

    // Create trees for each character and add them to a PriorityQueue
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    for (int i = 0; i < 256; i++) {
      if (frequency[i] > 0) {
        priorityQueue.add(new Node((char) i, frequency[i])); // Add node to PriorityQueue if frequency is greater than 0
      }
    }

    // Combine trees until only one tree is left
    while (priorityQueue.size() > 1) {
      Node left = priorityQueue.poll(); // Extract the node with the lowest frequency
      Node right = priorityQueue.poll(); // Extract the node with the second lowest frequency

      // Create a combined node with frequency as the sum of frequencies of left and
      // right nodes
      Node combinedNode = new Node('\0', left.frequency + right.frequency);
      combinedNode.left = left;
      combinedNode.right = right;

      // Add the combined node back to the PriorityQueue
      priorityQueue.add(combinedNode);
    }

    // The remaining node in the PriorityQueue is the root of the Huffman tree
    Node huffmanTreeRoot = priorityQueue.poll();

    // Print the Huffman tree
    String[] arr = new String[256];
    printHuffmanTree(huffmanTreeRoot, "", arr);

    // Get uncompressed and compressed sizes
    int uncompressedSize = getUncompressedSize(input);
    int compressedSize = getCompressedSize(huffmanTreeRoot, input, arr);

    // Print compressed and uncompressed sizes
    System.out.println("Uncompressed size: " + uncompressedSize + " bits");
    System.out.println("Compressed size: " + compressedSize + " bits");
  }

  // Method to print the Huffman tree (optional)
  private static void printHuffmanTree(Node root, String code, String arr[]) {
    if (root == null)
      return;

    // If the node is a leaf node, print its character and Huffman code
    if (root.left == null && root.right == null) {
      System.out.println("'" + root.data + "' : " + code);
      arr[root.data] = code; // Store the Huffman code for the character
    }

    // Recursively traverse left and right subtrees, appending '0' for left edges
    // and '1' for right edges
    printHuffmanTree(root.left, code + "0", arr);
    printHuffmanTree(root.right, code + "1", arr);
  }
}
