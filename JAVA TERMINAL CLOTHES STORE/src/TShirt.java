import java.io.Serializable;

public class TShirt extends Product implements Serializable {
  private String color;
  private String material;
  private static final long serialVersionUID = 1L;
     //constructor for Tshirt Class 
  public TShirt(String productName, double productPrice, int productQty, String color,String material) {
    super(productName, productPrice, productQty, "T-Shirt");
    this.color = color;
    this.material = material;
  }
        //getter for color
  public String getColor() {
    return color;
  }
       //setter for color   
  public void setColor(String color) {
    this.color = color;
  }
      //getter for material      
  public String getMaterial() {
    return material;
  }
      //setter for material        
  public void setMaterial(String material) {
    this.material = material;
  }
                
  @Override
  public String toString() {
    return "TShirt{" + ", color='" + color + '\'' +", material='" + material + '\'' +"} " + super.toString();
  }
}