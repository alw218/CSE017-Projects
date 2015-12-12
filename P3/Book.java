/*
CSE 17
Amber Wallace
alw218
Program #3 DEADLINE: March 26, 2015
Program Description: Online Store
*/ 

/**create class book- a subclass of Product. It is the class of all books that can be sold.*/
public class Book extends Product{
  private String title; //the title of the book
  private String author; //the author of the book
  
  /**four arg constructor for book*/
  public Book(int serialNum, String title, String author, double price){
    //call the super
    super(price, serialNum);
    
    //store other vars
    this.title = title;
    this.author = author;
  }
  
  /**method returns a description of the book in the form “title by author”*/
  public String getDescription(){
    return this.title+" by "+this.author;
  }
}