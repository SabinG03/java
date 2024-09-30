import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
//ArrayLists to store products and items in the shopping cart
  static ArrayList<Product> stock = new ArrayList<>();
  static ArrayList<Product> cart = new ArrayList<>();
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static Scanner input = new Scanner(System.in);
        
  public static void main(String[] args) {
    File saveFile = new File("data.ser");
              
    if (saveFile.exists()) {
      try {
        loadData();
      } catch (Exception e) {
        System.out.println("Error Loading File");
      }
      mainMenu();
    } else {
      prePopulate();
      mainMenu();
    }
  }
      //displays main menu
  private static void mainMenu() {
    System.out.println("Press 1 - To see Items");
    System.out.println("Press 2 - for Shopping Cart");
    System.out.println("Press 3 - to Save and Exit");
                    
    String choice = input.next();
    choice = choice.toLowerCase();
    switch (choice) {
      case "1":
        productMenu();
        break;
      case "2":
        viewShoppingCart();
        break;
      case "3":
        try {
          saveData();
        } catch (Exception e) {
          System.out.println("Error Saving File.");
        }
        System.out.println("System is Closing.");
        System.exit(0);; 
      default:
        System.out.println("Invalid choice!");
        mainMenu();
    }
  }
        
  private static void loadData() throws Exception {
    FileInputStream importFile = new FileInputStream("data.ser");
    ObjectInputStream objReader = new ObjectInputStream(importFile);
    stock = (ArrayList<Product>) objReader.readObject();
    objReader.close();
  }
          
  private static void saveData() throws Exception {
    FileOutputStream exportFile = new FileOutputStream("data.ser");
    ObjectOutputStream writer = new ObjectOutputStream(exportFile);
    writer.writeObject(stock);
    writer.close();
  }
            //Product menu
  private static void productMenu() {
    System.out.println("1 - Add Item");
    System.out.println("2 - View All Products");
    System.out.println("3 - Delete Item");
                          
    String choice = input.next();
                              
    switch (choice) {
      case "1":// add new item to stock
        try {
          addItem();
        } catch (Exception e) {
          System.out.println("Error Adding Item");
        }
        break;
      case "2"://view all products in stock
        viewAllProducts();
        break;
      case "3"://delete item from stock
        deleteItem();
        break;
      default:
        System.out.println("Invalid choice!");
    }
  }
     //add new item to stock
  private static void addItem() throws Exception {
    System.out.println("Enter Product Name:");
    String productName = reader.readLine();
    System.out.println("Enter Product Price:");
    double price = Double.parseDouble(input.next());
    System.out.println("Enter Quantity of Product:");
    int qty = Integer.parseInt(input.next());
    System.out.println("Enter Product Type:");
    String productType = reader.readLine();
    Product product = new Product(productName, price, qty, productType);
    stock.add(product);
    saveData();
    input.nextLine(); 
    System.out.println("Item added successfully!");
    mainMenu(); 
  }
       //delete item from stock         
  private static void deleteItem() {
    if (stock.isEmpty()) {
      System.out.println("No items to delete.");
      mainMenu();
      return;
    }
    System.out.println("Select the number of the item to delete:");
    for (int i = 0; i < stock.size(); i++) {
      System.out.println((i + 1) + ". " + stock.get(i).getProductName());   
    }
    int selection = input.nextInt();
    if (selection >= 1 && selection <= stock.size()) {
      Product removedProduct = stock.remove(selection - 1);
      System.out.println("Item '" + removedProduct.getProductName() + "' deleted successfully.");
    } else {
      System.out.println("Invalid selection.");
    }
    mainMenu();
  }
   //view all products that are in stock               
  private static void viewAllProducts() {
    System.out.println("All Products:");
    System.out.println("--------------------------------");
    int index = 1;
    for (Product p : stock) {
      System.out.println(index + ". ");
      p.printDetails();
      index++;
    }
    System.out.println("--------------------------------");
                                        
    
    System.out        
        .println("Press 0 to return to the main menu, or enter the number of the product to add to the shopping cart:");
    int choice = input.nextInt();
    if (choice == 0) {
      mainMenu(); 
    } else if (choice == 0) {
      mainMenu(); 
    } else if (choice > 0 && choice <= stock.size()) {
      Product selectedProduct = stock.get(choice - 1);
      if (selectedProduct.getProductQty() > 0) {
        cart.add(selectedProduct);
        System.out.println(selectedProduct.getProductName() + " added to the shopping cart.");
        selectedProduct.setProductQty(selectedProduct.getProductQty() - 1);; 
      } else {
        System.out.println("Sorry, this product is out of stock.");
      }
      mainMenu(); 
    } else {
      System.out.println("Invalid choice!");
      viewAllProducts(); 
    }
  }
    //view items in shopping cart                
  private static void viewShoppingCart() {
    if (cart.isEmpty()) {
      System.out.println("Shopping Cart is empty.");
    } else {
      double totalCost = 0;
      int totalItems = 0;
      System.out.println("Shopping Cart:");
      System.out.println("--------------------------------");
      for (int i = 0; i < cart.size(); i++) {
        System.out.println((i + 1) + ". Product: " + cart.get(i).getProductName());   
        System.out.println("   Price: $" + cart.get(i).getProductPrice());
        totalCost += cart.get(i).getProductPrice();
        totalItems ++;
        System.out.println("--------------------------------");
      }
      double averageCost = totalCost/ cart.size();
      System.out.printf("Total Items:%d%n",totalItems);
      System.out.printf("Total Cost: $%.2f%n", totalCost, null);
      System.out.printf("Average Cost per Item: $%.2f%n", averageCost);
      System.out.println("--------------------------------");
      
    }
                          
    
    mainMenu();
  }
                      
  private static void prePopulate() {
    Product plainTShirt = new Product("Plain T-Shirt", 9.99, 10, "Clothing");
    Product designerTShirt = new Product("Designer T-Shirt", 199.99, 5, "Clothing");
    Product winterCoat = new Product("Winter Coat", 99.99, 25, "Clothing");
    Product pufferCoat = new Product("Puffer Coat", 259.99, 15, "Clothing");
    stock.add(plainTShirt);
    stock.add(designerTShirt);
    stock.add(winterCoat);
    stock.add(pufferCoat);
   }
}
