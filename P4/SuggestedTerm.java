/*
CSE 17
Amber Wallace
alw218
Program #4 DEADLINE: April 6, 2015
Program Description: Spell Checker
*/ 

/**Create class SuggestedTerm- A possible replacement term for a misspelled word. Implements the Comparable interface.*/
public class SuggestedTerm implements Comparable{
  private String term; //The suggested word.
  private int editDistance; //The number of changes needed to convert the misspelled word to the term.
  
  /**reate a new SuggestedTerm given a term and its editDistance from the misspelled word.*/
  public SuggestedTerm(String term, int editDistance){
    this.term = term;
    this.editDistance = editDistance;
  }
  
  /**Get method for term.*/
  public String getTerm(){
    return this.term;
  }
  
  /**Get method for editDistance.*/
  public int getEditDistance(){
    return this.editDistance;
  }
  
  /**Returns a string of the form: term (editDistance)*/
  public String toString(){
    return this.term+": ("+this.editDistance+")";
  }
  
  /**Returns a negative number if this object has an smaller editDistance than o. Returns a positive number if this object 
  has a larger editDistance than o. Otherwise returns 0*/
  public int compareTo(Object o){
    //check that the object passed through is a dictionaryItem
    if(o instanceof SuggestedTerm){
      //cast to dictionaryItem
      SuggestedTerm s = (SuggestedTerm) o;
      
      //compare editDistances (will return positive if this is larger than s, negative if smaller, and 0 if they are equal)
      return this.editDistance - s.getEditDistance();
    }
    else{ //not comparable
      System.out.println("The object you passed is not comparable to SuggestedTerm.");
      System.exit(1);
      return 0;
    }
  }
}