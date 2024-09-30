import java.util.Scanner;

// Node class represents each element in the linked list
class Node {
  String data; // Data stored in the node
  Node next; // Reference to the next node in the list

  // Constructor to create a new node with given data
  public Node(String data) {
    this.data = data;
    this.next = null;
  }
}

// LinkedList class represents the linked list data structure
class LinkedList {
  private Node head; // Reference to the first node in the list

  // Constructor to create an empty linked list
  public LinkedList() {
    this.head = null;
  }

  // Method to add a new node with given data to the beginning of the list
  public void add(String data) {
    Node newNode = new Node(data); // Create a new node
    newNode.next = head; // Set the next of the new node to the current head
    head = newNode; // Set the new node as the new head of the list
  }

  // Method to print all elements of the list
  public void printList() {
    Node current = head; // Start from the head of the list
    while (current != null) { // Traverse the list until reaching the end
      System.out.println(current.data); // Print the data of the current node
      current = current.next; // Move to the next node
    }
  }
}

// Main class for running the program
public class Main {
  public static void main(String[] args) {
    LinkedList list = new LinkedList(); // Create a new linked list
    Scanner scanner = new Scanner(System.in); // Create a scanner to read user input

    System.out.println("Enter a word (type END to finish):");
    String word = scanner.nextLine(); // Read the first word from the user

    while (!word.equals("END")) { // Keep reading words until the user enters "END"
      list.add(word); // Add the word to the linked list
      System.out.println("Enter a word (type END to finish):");
      word = scanner.nextLine(); // Read the next word from the user
    }

    System.out.println("Output:");
    list.printList(); // Print all the words stored in the linked list.
  }
}
