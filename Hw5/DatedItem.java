/*
CSE 17
Amber Wallace
alw218
Homework #5 DEADLINE: April 13, 2015
Program Description: Generics Inventory
*/ 

/**create class*/
public abstract class DatedItem extends Item implements Comparable<DatedItem>{
  protected java.util.Date packDate; //The package date of the item
  
  /**initialize a DatedItem*/
  public DatedItem(String name, int quantity, java.util.Date packDate){
    super(name, quantity); //call the super
    this.packDate = packDate; 
  }
  
  /**getter for packDate*/
  public java.util.Date getPackDate(){
    return this.packDate;
  }
  
  /**compareTo method for dated items based on packDates.*/
  public int compareTo(DatedItem item){
    //return negative if item is older, 0 if date is the same, and positive if item is newer
    return this.getPackDate().compareTo(item.getPackDate());
  }
}