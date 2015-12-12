/*
CSE 17
Amber Wallace
alw218
Program #3 DEADLINE: March 26, 2015
Program Description: Online Store
*/ 

/**create class Shirt as subclass of Product. It is the class of all shirts that can be sold.*/
public class Shirt extends Product{
  private String type; //type of shirt
  private String size; //size of shirt
  private String color; //color of shirt
  
  /**Five arg constructor for shirt. Specifies the Shirt’s serial number, type, size, color and unit price in dollars.*/
  public Shirt(int serialNum, String type, String size, String color, double price){
    //call super constructor
    super(price, serialNum);
    
    //store remianing variables
    this.type = type;
    this.size = size;
    this.color = color;
  }
  
  /**getDescription method. Returns a description of the shirt in the form "color type - size"*/
  public String getDescription(){
    return this.color+" "+this.type+" - "+this.size;
  }
}
