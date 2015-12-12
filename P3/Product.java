/*
CSE 17
Amber Wallace
alw218
Program #3 DEADLINE: March 26, 2015
Program Description: Online Store
*/ 

/**create abstract class Product- this is the class of all the goods that can be sold by the store*/
public abstract class Product{
  private double price; //The price of the product in dollars.
  private int serialNumber; //The product’s serial number.
  
  /**create two arg constructor*/
  protected Product(double price, int serialNumber){
    this.price = price;
    this.serialNumber = serialNumber;
  }
  
  /**abstract method for getting description of product*/
  public abstract String getDescription(); //Returns a description of the item suitable for display in a listing of the inventory.
  
  /**method returns the price of a single unit of the product in dollars.*/
  public double getPrice(){
    return this.price; 
  }
  
  /**Returns the serial number of the product.*/
  public int getSerialNum(){
    return this.serialNumber;
  }
}