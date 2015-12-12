/*
CSE 17
Amber Wallace
alw218
Program #4 DEADLINE: April 6, 2015
Program Description: Spell Checker
*/ 

//import arrayList, scanner, file, and exceptions
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

/**create class for SpellChecker- An object that consists of a dictionary of words indexed for finding the best matches to misspelled words.*/
public class SpellChecker{
  private ArrayList<DictionaryItem> dictionary; //A list of DictionaryItems, sorted according to their compareTo method.
  
  /**constructor for spellChecker*/
  public SpellChecker(){
    this.dictionary = new ArrayList<DictionaryItem>();
    //read a list of words from the file us-dic.txt
    try{
      
      File file = new File("us-dic.txt"); //get file
      Scanner input = new Scanner(file); //create new scanner
      String term = ""; //create var to store terms temporarily
      String soundex = ""; //create var to temporarily store soundex code
      
      //loop through all dictionary terms
      while(input.hasNextLine()){
        
        //get next line (next term)
        term = input.nextLine();
        
        //find their Soundex codes
        soundex = this.soundex(term);
        
        //create DictionaryItems and put them in the dictionary
        this.dictionary.add(new DictionaryItem(term, soundex));
        
      }
      
      System.out.println("Loaded "+this.dictionary.size()+" words into dictionary."); //print loaded message
      ArrayListUtil.selectionSort(this.dictionary); //sort the dictionary
      
    }
    catch(FileNotFoundException ex){
      //print error message
      System.out.println("ERROR: Couldn't read from file us-dic.txt");
      System.exit(1);
    }
    
  }
  
  /**Returns true if the term is in the dictionary. For the word ÒIÓ the search is case sensitive, but for all other words, 
  the search is case-insensitive.*/
  public boolean checkDictionary(String term){
    if(!term.equals("I")){
      term = term.toLowerCase();
    }
    
    //create DictionaryItem for term
    DictionaryItem item = new DictionaryItem(term, soundex(term));
    //search dictionary for term
    if(ArrayListUtil.binarySearch(dictionary, item) >= 0){
      return true;
    }
    else{
      return false;
    }
  }
  
  /**Uses binary search to locate where typo would be located, and then finds the 10 preceding terms and the 10 subsequent
    * terms in the dictionary and returns a single ArrayList of these DictionaryItems. If there are fewer than 10 of either,
    * it returns as many as possible.*/
  private ArrayList<DictionaryItem> getCandidates(String typo){
    //change typo to dictionaryItem
    DictionaryItem item = new DictionaryItem(typo, soundex(typo));
    //use binary search to determine where typo would be located
    int location = -1 * ArrayListUtil.binarySearch(this.dictionary, item) - 1;
    
    int start = 0;
    int end = 0;
    
    //get 10 preceding terms and 10 subsequent terms
    if(location - 10 < 0){
      start = 0;
    }
    else{
      start = location - 10;
    }
    
    if(location + 10 < this.dictionary.size()){
      end = location + 10;
    }
    else{
      end = this.dictionary.size();
    }
    
    //create arraylist to store candidates
    ArrayList<DictionaryItem> candidates = new ArrayList<DictionaryItem>();
    
    //loop through dictionary adding appropriate DictionaryItems to candidates
    for(int index = start; index < end; index++){
      candidates.add(this.dictionary.get(index));
    }
    
    return candidates;
  }
  
  /**Calculates the edit distance for each DictionaryItem in candidates, creates an ArrayList of SuggestedTerm objects, 
    * sorts the list in order of ascending edit distance, and then returns it.*/
  private ArrayList rankCandidates(String typo, ArrayList<DictionaryItem> candidates){
    //create array list to store suggested terms
    ArrayList<SuggestedTerm> suggestedTerms = new ArrayList();
    
    //loop through candidates getting edit distance, creating suggestedTerm, and storing in arrayList
    for(int i = 0; i<candidates.size(); i++){
      suggestedTerms.add(new SuggestedTerm(candidates.get(i).getTerm(), editDistance(typo, candidates.get(i).getTerm())));
    }
    
    //sort by edit distance
    ArrayListUtil.selectionSort(suggestedTerms);
    
    //return sorted list
    return suggestedTerms;
  }
  
  /**If typo is not ÒIÓ, convert it to lower-case, get the candidate replacements, and then rank them using the methods above.*/
  public ArrayList<SuggestedTerm> getRankedCandidates(String typo){
    //if typo is not "I" convert to lower-case
    if(typo.equals("I")){
      typo = typo.toLowerCase();
    }
    
    //get canidate replacements and rank them
    return rankCandidates(typo, getCandidates(typo));
  }
  
  /**Uses recursion to calculate the edit distance between word1 and word2.*/
  public static int editDistance(String word1, String word2){
    return editDistance(word1, word2, word1.length(), word2.length());
  }
  
  /**A recursive helper method for editDistance.*/
  private static int editDistance(String word1, String word2, int index1, int index2){
    if(index1 == 0){
      return index2;
    }
    else if(index2 == 0){
      return index1;
    }
    else{
      if(word1.charAt(index1-1) == word2.charAt(index2-1)){
        return editDistance(word1, word2, index1-1, index2-1);
      }
      else{
        return 1 + Math.min(Math.min(editDistance(word1,word2, index1-1, index2), editDistance(word1, word2, index1, index2-1)), editDistance(word1, word2, index1-1, index2-1)); 
      }
    }
  }
    
  /**Return the Soundex code for word.*/
  public static String soundex(String word){
    //create string to hold soundex
    String soundex = "";
    //get first letter
    soundex += String.valueOf(word.charAt(0)).toUpperCase();
    
    int curPos = 1; //current position in word starts at one
    
    //loop through
    while(curPos < word.length() && soundex.length() < 4){
      if(soundexDigit(word.charAt(curPos)) == 0){ //if the letter does not have an assigned digit
        curPos++; //skip this letter
      }
      else if(soundexDigit(word.charAt(curPos)) == soundexDigit(word.charAt(curPos - 1))){ //if digit is same as that of preceeding letter
        curPos++; //skip this letter
      }
      else if(curPos >=2 && (word.charAt(curPos - 1) == 'h' || word.charAt(curPos - 1) == 'w') && (soundexDigit(word.charAt(curPos)) == soundexDigit(word.charAt(curPos - 2)))){
        curPos++; //skip this letter
      }
      else{
        soundex += soundexDigit(word.charAt(curPos)); //append digit to code
        curPos++; //move to next letter
      }
    }
    
    while(soundex.length() < 4){
      soundex += "0"; //append zeros until there are three numbers
    }
      
    return soundex;
  }
  
  /**Returns the Soundex digit for character c.*/
  private static int soundexDigit(char c){
    switch(c){
      case 'b':
      case 'f':
      case 'p':
      case 'v':
        return 1;
      case 'c':
      case 'g':
      case 'j':
      case 'k':
      case 'q':
      case 's':
      case 'x':
      case 'z':
        return 2;
      case 'd':
      case 't':
        return 3;
      case 'l':
        return 4;
      case 'm':
      case 'n':
        return 5;
      case 'r':
        return 6;
      default:
        return 0;
    }
  }
  
  /**create main method*/
  public static void main(String[] args){
    //create new SpellChecker object
    SpellChecker spellChecker = new SpellChecker();
    
    //ask user to type in a paragraph
    System.out.println();
    System.out.println("Enter a paragraph followed by a blank line:");
    
    Scanner input = new Scanner(System.in);
    
    //keep track of line numbers
    int lineCount = 0;
    //temporarily store word
    String word = "";
    //string for line
    String line = null;
    
    //read the paragraph in one line at a time.
    while(!(line = input.nextLine()).isEmpty()){
      
      lineCount++; //increment line count

      //create scanner to parse each line 
      Scanner scanLine = new Scanner(line);
      
      int wordCount = 0; //keep track of word count
      
      //look at each word in line until there are no more words
      while(scanLine.hasNext()){
        wordCount++; //increment word count
        
        //get each word in line and if there is a non-letter (punctuation) and the end, remove it.
        word = scanLine.next().replaceAll("([a-z]+)[?:!.,;]*", "$1");
    
        //look up word in dictionary and if word is not there
        if(!spellChecker.checkDictionary(word)){
          //get a ranked list of candidates
          ArrayList<SuggestedTerm> rankedCandidates = spellChecker.getRankedCandidates(word);
          
          //for each missing word it prints "Line: m, Word: n: word is not in the dictionary."
          System.out.println();
          System.out.println("Line: "+lineCount+", Word: "+wordCount+": '"+word+"' is not in dictionary"); 
          
          //display the top 5 canidates in order
          System.out.println("Suggested replacements");
          
          int i=0;
          while(i<5 && i<rankedCandidates.size()){
            System.out.println((i+1)+") "+rankedCandidates.get(i).getTerm());
            i++;
          }
        }
      }
    //terminate when user enters a blank line
    }
  }
}
  
  


/*
+main(args:String[]):void See below for details.*/
    
   /* char[] soundex = {'0','0','0','0'};
        
        int index=0;
        int i=0;
        
        while(index<4 && i<term.length()){
/*If this digit is the same as that of the preceding letter, skip it
d. If the preceding letter is h or w, and the letter before that has the same digit, skip this letter
e. Otherwise, append the digit to the code 
          if(i == 0){ //take first letter and make capital
            soundex[index]=term.charAt(i).toUpper();
          }
          else if(!term.charAt(i).equals(term.charAt(i-1))){ //if digit is different than preceeding letter
            
            else if(term.charAt(i).equals('b' || 'f' || 'p' || 'v')){
              soundex[index]='1';
            }
            else if(term.charAt(i).equals('c' || 'g' || 'j' || 'k' || 'q' || 's' || 'x' || 'z')){
              soundex[index]='2';
            }
            else if(term.charAt(i).equals('d' || 't')){
              soundex[index]='3';
            }
            else if(term.charAt(i).equals('l')){
              soundex[index]='4';
            }
            else if(term.charAt(i).equals('m' || 'n')){
              soundex[index]='5';
            }
            else if(term.charAt(i).equals('r')){
              soundex[index]='6';
            }
            else{
              
            }
          }
          else
          
          index++;
          i++;
        }*/