/*
CSE 17
Amber Wallace
alw218
Program #4 DEADLINE: April 6, 2015
Program Description: Spell Checker
*/ 

/**create class for DictionaryItem- An entry in the spelling dictionary.*/
public class DictionaryItem implements Comparable{
  private String term; //A word in the dictionary.
  private String soundex; //The soundex code for the term
  
  /**Create a new DictionaryItem given a term and its soundex code.*/
  public DictionaryItem(String term, String soundex){
    this.term = term;
    this.soundex = soundex;
  }
  
  /**getter for term*/
  public String getTerm(){
    return this.term;
  }
  
  /**getter for soundex*/
  public String getSoundex(){
    return this.soundex;
  }
  
  /**Returns true if this object has the same term as o.*/
  public boolean equals(Object o){
    //check if object is Dictionary item
    if (o instanceof DictionaryItem){
      //cast object to dictionary item
      DictionaryItem d = (DictionaryItem) o;
      //check if terms are equal
      if(this.term.equals(d.term)){ //curently is taking capitalization into account************************************
        return true;
      }
    }
    return false;
  }
  
  /**Returns a negative number if this object has an earlier soundex than o, or has the same soundex and an earlier term. 
    * Returns a positive number if this object has a latter soundex than o, or has the same soundex and an later term. Returns 0 if the
    * soundex and term are the same. */
  public int compareTo(Object o){
    //make sure Object passed in is a DictionaryItem
    if(o instanceof DictionaryItem){
      //cast object to DictionaryItem
      DictionaryItem d = (DictionaryItem) o;
      //compare soundex
      if(this.soundex.compareTo(d.getSoundex()) < 0 || (this.soundex.compareTo(d.getSoundex()) == 0 && this.term.compareTo(d.getTerm()) < 0)){
        return -1;
      }
      else if(this.soundex.compareTo(d.getSoundex()) == 0 && this.term.compareTo(d.getTerm()) == 0){
        return 0;
      }
      else{
        return 1;
      }
    }
    else{
      //user passed an object that was not a DictionaryItem
      System.out.println("You passed an object to DictionaryItem.compareTo() that was not comparable to DictionaryItem.");
      System.exit(1);
      return 0;
    }
  }
  
  /**Returns a string of the form soundex:term*/
  public String toString(){
    return this.soundex+":"+this.term;
  }
}