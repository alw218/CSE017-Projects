/*
CSE 17
Amber Wallace
alw218
Program #3 DEADLINE: March 26, 2015
Program Description: Online Store
*/ 

/**create InventoryItem class. This class represents a particular item in the store’s inventory. The store may sell many different book titles
and shirts, each with its own serial number. Each of these items for sale is represented by a different InventoryItem.
The InventoryItem has all of the information about the product and keeps track of how many units of the item are currently in stock.*/
public class InventoryItem{
  private int qty; //The number of units of the item in stock.
  private Product product; //The product available for sale
  
  /**create constructor. Specifies the product and the number of units in stock (qty).*/
  public InventoryItem(Product product, int qty){
    this.qty = qty;
    this.product = product;
  }
  
  /**Returns a string suitable for listing the item in the Inventory.*/
  public String getListing(){
    return this.getSerialNum()+"\t"+this.product.getDescription()+"\t"+this.product.getPrice()+"\t"+this.qty;
  }
  
  /**getter for product*/
  public Product getProduct(){
    return this.product;
  }
  
  /**getter for quantity*/
  public int getQty(){
    return this.qty;
  }
  
  /**getter for serial number of product in inventory*/
  public int getSerialNum(){
    return this.product.getSerialNum();
  }
  
  /**mehtod used to reduce the inventory of the item.*/
  public void removeFromQty(int reduction){
    this.qty -= reduction;
  }
}