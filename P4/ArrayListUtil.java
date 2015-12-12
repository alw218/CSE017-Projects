/*
CSE 17
Amber Wallace
alw218
Program #4 DEADLINE: April 6, 2015
Program Description: Spell Checker
*/ 

//import arraylist
import java.util.ArrayList;

/**create class ArrayListUtil that provides methods for processing ArrayLists*/
public class ArrayListUtil{
  
  /**method uses selection sort to sort an ArrrayList of Comparable objects*/
  public static <T extends Comparable<? super T>> void selectionSort (ArrayList<T> list){
    //non recursive method for sorting
    int index=0;
    T tempO;
   
    //loop through all elements
    for(int i=0; i<list.size(); i++){
      index=i;
      for(int j=i; j<list.size(); j++){
        if(list.get(index).compareTo(list.get(j)) > 0){
          index=j;
        }
      }
      
      tempO = list.get(i);
      list.set(i, list.get(index));
      list.set(index, tempO);
      
    }      
  }
  
  /**A recursive implementation of binary search that works on a sorted ArrayList of Comparable objects.*/
  public static <T extends Comparable<? super T>> int binarySearch(ArrayList<T> list, Comparable key){
    //send to helper method
    return binarySearch(list, key, 0, list.size()-1);
  }
  
  /** A recursive helper method for binarySearch. Low and high are the start and end indexes of the sublist to search. */
  public static <T extends Comparable<? super T>> int binarySearch(ArrayList<T> list, Comparable key, int low, int high) {
    if (low > high){ // not found
      //returns Ðx-1 where x is the place to insert the term.
      return -low - 1;
    }
    
    int mid = (low + high) / 2;
    if (key.compareTo(list.get(mid)) < 0){ // must search in first half of array
      return binarySearch(list, key, low, mid - 1);
    }
    else if (key.compareTo(list.get(mid)) == 0){ //found
      //Returns the index of the key
      return mid;
    }
    else{ // must search in second half of array
      return binarySearch(list, key, mid + 1, high);
    }
  }
}