import java.io.Serializable;

class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String productName;
  private double productPrice;
  private int productQty;
  private String productType;
   //constructor for product class       
  public Product(String productName, double productPrice, int productQty, String productType) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.productQty = productQty;
    this.productType = productType;
  }
            
  // Getters and setters
  public String getProductName() {
    return productName;
  }
                
  public void setProductName(String productName) {
    this.productName = productName;
  }
                  
  public double getProductPrice() {
    return productPrice;
  }
                    
  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }
                      
  public int getProductQty() {
    return productQty;
  }
                        
  public void setProductQty(int productQty) {
    this.productQty = productQty;
  }
                          
  public String getProductType() {
    return productType;
  }
                            
  public void setProductType(String productType) {
    this.productType = productType;
  }
                              
  // Method to print product details
  public void printDetails() {
    System.out.println("Product Type: " + productType);
    System.out.println("Product Name: " + productName);
    System.out.println("Price: $" + productPrice);
    System.out.println("Quantity: " + productQty);
  }
}
