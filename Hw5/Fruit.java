/*
CSE 17
Amber Wallace
alw218
Homework #5 DEADLINE: April 13, 2015
Program Description: Generics Inventory
*/ 

/**create class Fruit*/
public abstract class Fruit extends DatedItem{
  /**initialize a fruit item*/
  public Fruit(String name, int quantity, java.util.Date packDate){
    super(name, quantity, packDate); //call super
  }
}