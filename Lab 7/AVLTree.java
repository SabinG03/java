import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AVLNode {
  String key;
  String value;
  int height;
  AVLNode left;
  AVLNode right;

  AVLNode(String key, String value) {
    this.key = key;
    this.value = value;
    height = 1; // Initialize height to 1 for a new node
  }
}

public class AVLTree {
  AVLNode root;

  // Method to get the height of a node
  int height(AVLNode node) {
    if (node == null)
      return 0; // Return 0 if node is null
    return node.height;
  }

  // Method to get the maximum of two integers
  int max(int a, int b) {
    return (a > b) ? a : b; // Return a if a is greater, otherwise return b
  }

  // Method to get the balance factor of a node
  int getBalance(AVLNode node) {
    if (node == null)
      return 0; // Return 0 if node is null
    return height(node.left) - height(node.right); // Return the difference in height of left and right subtrees
  }

  // Right rotation operation
  AVLNode rightRotate(AVLNode y) {
    AVLNode x = y.left;
    AVLNode T2 = x.right;

    // Perform rotation
    x.right = y;
    y.left = T2;

    // Update heights
    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;

    return x;
  }

  // Left rotation operation
  AVLNode leftRotate(AVLNode x) {
    AVLNode y = x.right;
    AVLNode T2 = y.left;

    // Perform rotation
    y.left = x;
    x.right = T2;

    // Update heights
    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;

    return y;
  }

  // Method to insert a new node into the AVL tree
  AVLNode insert(AVLNode node, String key, String value) {
    if (node == null)
      return (new AVLNode(key, value)); // Create a new node if the tree is empty

    // Perform standard BST insertion
    if (key.compareTo(node.key) < 0)
      node.left = insert(node.left, key, value);
    else if (key.compareTo(node.key) > 0)
      node.right = insert(node.right, key, value);
    else
      return node; // Duplicate keys not allowed in AVL tree

    // Update height of the current node
    node.height = 1 + max(height(node.left), height(node.right));

    // Get the balance factor of this node
    int balance = getBalance(node);

    // If the node becomes unbalanced, there are four cases:

    // Left Left Case
    if (balance > 1 && key.compareTo(node.left.key) < 0)
      return rightRotate(node);

    // Right Right Case
    if (balance < -1 && key.compareTo(node.right.key) > 0)
      return leftRotate(node);

    // Left Right Case
    if (balance > 1 && key.compareTo(node.left.key) > 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    // Right Left Case
    if (balance < -1 && key.compareTo(node.right.key) < 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    // Return the unchanged node
    return node;
  }

  // Method to search for a node with a given key in the AVL tree
  String search(AVLNode node, String key) {
    if (node == null)
      return null; // Return null if node is null

    // Goes down to search for the inputed
    if (key.compareTo(node.key) < 0)
      return search(node.left, key); // Search in the left subtree
    else if (key.compareTo(node.key) > 0)
      return search(node.right, key); // Search in the right subtree
    else
      return node.value; // Node found, return its value
  }

  // Method to find the height of the AVL tree
  int findHeight(AVLNode node) {
    if (node == null)
      return 0; // Return 0 if node is null
    return Math.max(findHeight(node.left), findHeight(node.right)) + 1; // Return the maximum height of left and right
                                                                        // subtrees + 1
  }

  public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    String csvFile = "EnglishSpanish.csv";
    String line = "";
    String cvsSplitBy = ",";

    List<String[]> pairs = new ArrayList<>();

    // Read CSV file and store pairs in a list
    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
      while ((line = br.readLine()) != null) {
        String[] pair = line.split(cvsSplitBy);
        pairs.add(pair);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Sort pairs by English words
    Collections.sort(pairs, (a, b) -> a[0].compareTo(b[0]));

    // Insert sorted pairs into AVL tree
    for (String[] pair : pairs) {
      tree.root = tree.insert(tree.root, pair[0], pair[1]);
    }

    // Calculate height of AVL tree
    int height = tree.findHeight(tree.root);
    System.out.println("Height of the AVL tree: " + height);

    // Test the translation
    String englishText = "The news will list the new price";
    String[] words = englishText.toLowerCase().split("\\s+");
    StringBuilder spanishText = new StringBuilder();

    // Translate each word and append to the translated text
    for (String word : words) {
      String translation = tree.search(tree.root, word);
      if (translation != null) {
        spanishText.append(translation).append(" ");
      } else {
        spanishText.append(word).append(" "); // If translation not found, append the original word
      }
    }

    // Print the translated text
    System.out.println("Spanish translation: " + spanishText.toString().trim());
  }
}
