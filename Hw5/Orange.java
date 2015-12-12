/*
CSE 17
Amber Wallace
alw218
Homework #5 DEADLINE: April 13, 2015
Program Description: Generics Inventory
*/ 

/**create class Orange*/
public class Orange extends Fruit{
  /**initialize an Orange Item*/
  public Orange(String name, int quantity, java.util.Date packDate){
    super(name, quantity, packDate); //call super
  }
  
  /**toString method*/
  public String toString(){
    return this.getName()+" Orange, "+this.getQuantity()+" fruits, Packaged: "+this.getPackDate();
  }
}