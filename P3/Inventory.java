/*
CSE 17
Amber Wallace
alw218
Program #3 DEADLINE: March 26, 2015
Program Description: Online Store
*/ 

//import array list
import java.util.ArrayList;

/**create class Inventory to keep track of the store's inventory*/
public class Inventory{
  private ArrayList<InventoryItem> items; //An ArrayList of all InventoryItems on hand, one for each catalog item sold by the store.
  
  /**constructor for Inventory- should initialize items to be empty*/
  public Inventory(){
    this.items = new ArrayList<InventoryItem>(); 
  }
  
  /**Adds a product p to inventory and specifies that qty units are stocked.*/
  public void addItem(Product p, int qty){
    //store info as an InventoryItem and add to items array
    this.items.add(new InventoryItem(p, qty));
  }
  
  /**check if there are enough units of a product with given serial number to successfully fill an order for qty units*/
  public boolean checkInventory(int serialNum, int qty){
    
    //create a boolean var and set to false- change to true only if there is enough of requested inventory
    boolean enoughInventory = false;
    
    //loop through all Inventory
    for(int i=0; i<this.items.size(); i++){
      //check if inventoryItem has matching serialNumber with specified serialNum
      if(this.items.get(i).getSerialNum()==serialNum){
        //if serial numbers match, check if inventoryItem has enough in stock for requested order
        if(this.items.get(i).getQty() >= qty){
          enoughInventory = true; //if quantity in inventory is greater than or equal to requested quantity there is enough
        }
      }
    }
    
    //return enoughInventory- will be false if no serial number matched or if there was not enough for requested qty
    return enoughInventory;
    
  }
  
  /**Reduces the inventory of item w/ given serial number by reduction units. This is used to indicate that a # of units have been sold.*/
  public void decreaseStock(int serialNum, int reduction){
    //loop through all Inventory
    for(int i=0; i<this.items.size(); i++){
      //check if inventoryItem has matching serialNumber with specified serialNum
      if(this.items.get(i).getSerialNum()==serialNum){
        //if serial numbers match, reduce inventory by specified amount
        this.items.get(i).removeFromQty(reduction);
      }
    }
  }
  
  /**method that prints the inventory to the screen. This is simply the listing of each InventoryItem on a separate line.*/
  public void list(){
    //loop through all inventory items
    for(int i=0; i<this.items.size(); i++){
      //print listing of each item
      System.out.printf(this.items.get(i).getListing()+"\n");
    }
  }
  
  /**method that, given a serial number, will locate the matching InventoryItem and return it.*/
  public InventoryItem lookupItem(int serialNum){
    //loop through all Inventory
    for(int i=0; i<this.items.size(); i++){
      //check if inventoryItem has matching serialNumber with specified serialNum
      if(this.items.get(i).getSerialNum()==serialNum){
        //if serial numbers match, return the inventoryItem
        return this.items.get(i);
      }
    }
    
    //in the case all inventory items are searched and no serial number matches- return first InventoryItem***************************************
    return this.items.get(0);
  }
}