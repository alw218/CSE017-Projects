/*
CSE 17
Amber Wallace
alw218
Program #3 DEADLINE: March 26, 2015
Program Description: Online Store
*/ 

/**Store- This class represents a store.*/
public class Store{
  private Inventory inventory; //The store’s inventory.
  private double totalSales; //The total sales made by the store in dollars and cents.
  
  /**Constructor- takes no parameters, but should initialize the store’s inventory to be empty and the total sales to 0.*/
  public Store(){
    this.inventory = new Inventory();
    this.totalSales = 0;
  }
  
  /**Adds a new product for sale. Specifies both the product to add to the inventory and its initial inventory level, qty.*/
  public void addItem(Product product, int qty){
    this.inventory.addItem(product, qty);
  }
  
  /**Calculates the price of an order as the price of one unit of the product times the number of units ordered. Note, we are
  keeping our store simple, so we do not consider tax or shipping charges.*/
  public double getOrderTotal(Order newOrder){
    return this.inventory.lookupItem(newOrder.getSerialNum()).getProduct().getPrice() * newOrder.getQty();
  }
  
  /**Returns the current sales total of the store in dollars and cents.*/
  public double getTotalSales(){
    return this.totalSales;
  }
  
  /**method processes an order*/
  public boolean makeSale(Order newOrder){
    //check if there is enough inventory to make sale
    if(this.inventory.checkInventory(newOrder.getSerialNum(), newOrder.getQty())){
      //decrease stock of ordered item
      this.inventory.decreaseStock(newOrder.getSerialNum(), newOrder.getQty());
      //add total price of the order to the store's totalSales
      this.totalSales+=this.getOrderTotal(newOrder);
      //print sold message
      System.out.println("Sold "+newOrder.getQty()+" units of item #"+newOrder.getSerialNum()+" to "+newOrder.getCustomer());
      //return true
      return true;
    }
    else{
      System.out.println("Insufficient inventory to process order of "+newOrder.getQty()+" units of item #"+newOrder.getSerialNum()+
                         " from "+newOrder.getCustomer());
      //return false
      return false;
    }
  }
  
  /**Prints the store’s current inventory to the screen.*/
  public void showInventory(){
    System.out.println("Inventory");
    System.out.println("-------------------------------------------");
    
    this.inventory.list();
  }
}