import java.util.Scanner;

public class Lab5 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Prompt user to enter numbers separated by commas
    System.out.print("Enter the numbers to insert: ");
    String input = scanner.nextLine();
    String[] numbers = input.split(",");

    // Create a new binary tree
    BinaryTree tree = new BinaryTree();

    // Insert each number into the binary tree
    for (String number : numbers) {
      tree.insert(Integer.parseInt(number.trim())); // Convert string to integer and insert into tree
    }

    // Calculate and print the number of levels in the binary tree
    int levels = tree.maxDepth(tree.root);
    System.out.println("The resulting binary tree has " + levels + " levels");
  }
}

// Node class representing individual elements in the binary tree
class Node {
  int data;
  Node left, right;

  // Constructor to initialize a node with given data
  public Node(int data) {
    this.data = data;
    left = right = null; // Initialize left and right child nodes to null
  }
}

// BinaryTree class representing the binary tree structure and operations
class BinaryTree {
  Node root; // Root node of the binary tree

  // Constructor to initialize an empty binary tree
  public BinaryTree() {
    root = null; // Initialize root to null, indicating an empty tree
  }

  // Method to insert a new node with given data into the binary tree
  public void insert(int data) {
    root = insertRec(root, data); // Call the recursive insert method
  }

  // Recursive method to insert a new node with given data into the binary tree
  private Node insertRec(Node root, int data) {
    // If the tree or subtree is empty, create a new node with the given data
    if (root == null) {
      root = new Node(data);
      return root;
    }

    // If the data is less than the current node's data, insert into the left
    // subtree
    if (data < root.data)
      root.left = insertRec(root.left, data);
    // If the data is greater than the current node's data, insert into the right
    // subtree
    else if (data > root.data)
      root.right = insertRec(root.right, data);

    return root; // Return the root of the subtree after insertion
  }

  // Method to calculate the maximum depth (number of levels) of the binary tree
  public int maxDepth(Node node) {
    // If the current node is null, return 0 (base case for recursion)
    if (node == null)
      return 0;
    else {
      // Recursively calculate the maximum depth of left and right subtrees
      int leftDepth = maxDepth(node.left);
      int rightDepth = maxDepth(node.right);

      // Return the maximum depth among left and right subtrees, plus 1 for the
      // current node
      return Math.max(leftDepth, rightDepth) + 1;
    }
  }
}
