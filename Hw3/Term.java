/*
CSE 17
Amber Wallace
alw218
Homework #3       DEADLINE: February 18, 2015
Program Description: Analyze a short passage of text, and print each term and the freaquency in which it apears.
*/

/**create term class, where term represents a unique token in the document and counts how many times that token appears*/
public class Term{
  
  //data fields
  private String token;
  private int frequency;
  
  /**two arg constructors*/
  public Term(String token, int frequency){
    this.token=token;
    this.frequency=frequency;
  }
  
  public String getToken(){
    return this.token;
  }
  
  public int getFrequency(){
    return this.frequency;
  }
  
  /**take a single char formal parameter and return number of timesthat character appears within the term, considering both 
  the number of times it appears in the term, and the term appears in the document.*/
  public int getLetterCount(char givenChar){
    
    //create var for letter count
    int letterCount=0;
    
    //loop through all letters
    for(int i=0; i<this.token.length(); i++){
      if(this.token.charAt(i)==givenChar){
        letterCount++; //increment letter count if char matches
      }
    }
    //return the total number of times the given charachter appears with the term
    return letterCount;
  }
  
}