/*
CSE 17
Amber Wallace
alw218
Homework #5 DEADLINE: April 13, 2015
Program Description: Generics Inventory
*/ 

//import necessary classes
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**Create generic class Inventory*/
public class Inventory<I extends Item>{
  //instance field
  private ArrayList<I> array; //arrays of only type and subtypes of Items
  
  /**no arg constructor*/
  public Inventory(){
    //initializes an instance field of type ArrayList. 
    this.array = new ArrayList<I>(); //parameterized with the concrete type chosen for the Inventory.
  }
  
  /**Method to add item to array*/
  public void addItem(I item){
    this.array.add(item);
  }
  
  /**Method to get item off of an array list*/
  public I getItem(int index){
    return this.array.get(index);
  }
  
  /**method to get quantity*/
  public int getQuantity(int index){
    return this.getItem(index).getQuantity();
  }
  
  /**method to print Inventory*/
  public static void printInventory(Inventory inventory){
    //loop through all items in inventory
    for(int i = 0; i<inventory.array.size(); i++){
      //print items' to string
      System.out.println(inventory.array.get(i).toString());
    }
  }
  
  /**Method to get oldest item by comparing dates*/
  public static <E extends DatedItem> E getOldestItemFromInventory(Inventory<E> inventory){ //Inventory items must extend DatedItem to be comparable by date
    //track oldest item's index
    int index = 0;
    //loop through all of inventory items
    for(int i = 1; i<inventory.<E>array.size(); i++){
      if(inventory.getItem(i).compareTo(inventory.getItem(index)) < 0){ //item is older
        //update index of oldest item
        index = i;
      }
    }
    //return oldest item according to index
    return inventory.getItem(index);
  }
  
  /**Method to add oranges to inventory*/
  public static void addOrangesToInventory(Inventory<? super Orange> inventory, String name, int quantity){
    //get current date
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
    Date date = new Date();
    //add new orange
    inventory.array.add(new Orange(name, quantity, date));
  }
}