/*
CSE 17
Amber Wallace
alw218
Homework #3       DEADLINE: February 18, 2015
Program Description: Document Statistics
*/

//import scanner
import java.util.Scanner;

/**create document class*/
public class Document{
  //data fields
  private Term[] terms; //store the distinct terms found in the document in sorted order
  
  /**one arg constructor that takes a String parameter containing the text of the document. 
   Breaks string into tokens and then invokes record Term Freqs below*/
  public Document(String text){
    
    //break strings into tokens
    String[] tokens=text.split("\\s+");
    
    //invoke recordTermFreqs
    recordTermFreqs(tokens);
    
  }
  
  /**take an array of strings as formal parameter and populates terms field based on these tokens. the method first sorts strings
   using sortStrings, counts the number of occurrences of each distinct token and creates corresponding Term objects to put in the terms array*/
  private void recordTermFreqs(String[] strings){
    
    //sort strings using sortStrings() 
    sortStrings(strings);
    
    //count the number of occurrences of each distinct token
    int numDistinctTokens=1;
    for(int i=0; i<strings.length-1; i++){
      if(strings[i].compareTo(strings[i+1])!=0){
        numDistinctTokens++;
      }
    }
   
    //create array of length numDistinctTokens to store terms
    this.terms=new Term[numDistinctTokens];
   
    //create counter
    int count=0;
    
    //create initial start
    int start=0;
    
    //loop through distinct tokens
    for(int currentDistinctToken=0; currentDistinctToken<numDistinctTokens; currentDistinctToken++){
      
      //if the strings are the same, update the count
      while(start+count<strings.length && strings[start].compareTo(strings[start+count])==0){
        count++;
      }
      
      //create term
      this.terms[currentDistinctToken]=new Term(strings[start],count);
      
      //update start
      start=start+count;
      
      //reset count
      count=0;  
            
    }
  }
  
  public static void sortStrings(String[] strings){
    //use selection sort to sort strings alphebetically, capital letters first, and include punctuation
    for (int i=0; i < strings.length - 1; i++) {
      String currentMin = strings[i];
      int currentMinIndex = i;
      for (int j=i+1; j < strings.length; j++) {
        if (currentMin.compareTo(strings[j]) > 0) {
          currentMin = strings[j];
          currentMinIndex = j;
        }
      }
      
      // swap strings[i] with strings[currentMinIndex]
      if (currentMinIndex != i) {
        strings[currentMinIndex] = strings[i];
        strings[i] = currentMin;
      }
    }
  }
  
  public void printTermFreqs(){
    //prints a table of terms and their frequencies
    
    //print header
    System.out.println("Term\t\tFrequency");
    System.out.println("-----\t\t----------");
    
    //loop through terms and frequencies
    for(int i=0; i<this.terms.length; i++){
      System.out.println(this.terms[i].getToken()+"\t\t"+this.terms[i].getFrequency());
    }
    
    //table should print one token/frequency per line, sorted by token
  }
  
  public int getLetterCount(char letter){
    
    //initialize letter counter
    int letterCount=0;
    
    //returns the total number of times the letter appears in the document
    for(int i=0; i<this.terms.length; i++){
      letterCount=letterCount+(this.terms[i].getFrequency()*this.terms[i].getLetterCount(letter));
    }
    //use getLetterCount method from Term and make use of the fact we know the frequency of each term
    return letterCount;
  }
  
  /**Main method*/
  public static void main(String[] args){
    
    //create scanner
    Scanner input=new Scanner(System.in);
    
    //check that exactly one command line argument has been given and that this agument is exactly one character long
    if(!(args.length==1 && args[0].length()==1)){
      //if command line argument is wrong print "Usage: java Document letter" and exit
      System.out.println("Usage: java Document letter");
      System.exit(0);
    }
    
    //ask user to input a line of text, read this line from System.in, and then create a document object from the line
    System.out.println("Enter a line of text");
    String text=input.nextLine();
    Document doc=new Document(text);
    
    //Use printTermFreqs method to print the term-frequenct list for the file
    doc.printTermFreqs();
    
    //use the getLetterCount method to find out how many times the letter specified on the commandline appears in the document and print out the result
    System.out.println("The letter "+args[0]+" appears "+doc.getLetterCount(args[0].charAt(0))+" times.");
  }
}