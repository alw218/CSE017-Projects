/*
CSE 17
Amber Wallace
alw218
Homework #4 DEADLINE: March 17, 2015
Program: CSE Department Personnel
*/ 

/**Create class Faculty as a subclass of Employee*/
public class Faculty extends Employee{
  //field
  private String research;
  
  /**Faculty constructor */
  public Faculty(String name, String title, String email, String research){
    super(name, title, email);
    this.research=research;
  }
  
  /**Get method for research*/
  public String getResearch(){
    return this.research;
  }
  
  /**Overrides toString. Returns string w/ form “name (email), Faculty, title” followed by the research topics on a separate line and indented by a tab.*/
  public String toString(){
    return this.getName()+" ("+this.getEmail()+"), Faculty, "+this.getTitle()+"\n\t"+this.research;
  }
  
}