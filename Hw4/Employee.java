/*
CSE 17
Amber Wallace
alw218
Homework #4 DEADLINE: March 17, 2015
Program: CSE Department Personnel
*/ 

/**create class*/
public class Employee{
  //fields
  protected String name; //employee's full name
  protected String title; //The position of the employee
  protected String email; //The employee’s e-mail
  
  /**Employee Constructor, initializes the fields using the parameter values*/
  public Employee(String name, String title, String email){
    this.name=name;
    this.title=title;
    this.email=email;
  }
  
  /**Get method for name*/
  public String getName(){
    return this.name;
  }
  
  /**Get Method for title*/
  public String getTitle(){
    return this.title;
  }
  
  /**Get Method for email*/
  public String getEmail(){
    return this.email;
  }
  
  /**String Overrides Object’s toString method. Returns a string of the form “name (email), Staff, title”*/
  public String toString(){
    return this.name+" ("+this.email+"), Staff, "+this.title;
  }
  
  /**Overrides Object’s equals method. Two employees are considered equal if they have the same email address.*/
  public boolean equals(Object o){
    if (o instanceof Employee){
      Employee e = (Employee) o;
      if (this.getEmail().equals(e.getEmail())){
        return true;
      }
      else{
        return false;
      }
    }
    else{
      return false;
    }
  }
}
  