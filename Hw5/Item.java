/*
CSE 17
Amber Wallace
alw218
Homework #5 DEADLINE: April 13, 2015
Program Description: Generics Inventory
*/ 

/**create class Item*/
public abstract class Item{
  protected String name; //name of item
  protected int quantity; //quantity of the item in stock
  
  /**Initialize an item*/
  public Item(String name, int quantity){
    this.name = name;
    this.quantity = quantity;
  }
  
  /**getter for name*/
  public String getName(){
    return this.name;
  }
  
  /**getter for quantity*/
  public int getQuantity(){
    return this.quantity;
  }
}