/*
CSE 17
Amber Wallace
alw218
Program #5 DEADLINE: April 23, 2015
Program Description: Digital Music Library with Advanced Sorting
*/ 

//imports
import java.util.ArrayList;
import java.util.Comparator;

/**create class ComparatorSorts containing static generic methods to merge sort and quick sort ArrayLists using comparators.*/
public class ComparatorSorts{
  
  /**method for mergeSort*/
  public static <T> void mergeSort(ArrayList<T> list, Comparator<T> sortOrder){
    //only divide and merge if list is larger than 1 item
    if (list.size() > 1) {
      //merge sort the first half
      ArrayList<T> firstHalf = new ArrayList<T>();
      for(int i = 0; i<(int)(list.size()/2); i++){
        firstHalf.add(list.get(i));
      }
      mergeSort(firstHalf, sortOrder);
      
      // merge sort the second half
      ArrayList<T> secondHalf = new ArrayList<T>();
      for(int i = (int)(list.size()/2); i<list.size(); i++){
        secondHalf.add(list.get(i));
      }
      mergeSort(secondHalf, sortOrder);
      
      // merge two halves
      merge(firstHalf, secondHalf, list, sortOrder);
    }
  }
  
  /** The recursive helper method for the merge. */
  private static <T> ArrayList<T> merge(ArrayList<T> list1, ArrayList<T> list2, ArrayList<T> temp, Comparator<T> sortOrder) {
    int current1 = 0; // index in list 1
    int current2 = 0; // index in list 2
    int current3 = 0; // index in temp
    
    // as long as neither index is at the end, compare them
    // and copy the smaller value to temp
    while (current1 < list1.size() && current2 < list2.size()) {
      if (sortOrder.compare(list1.get(current1), list2.get(current2)) < 0)
        temp.set(current3++, list1.get(current1++));
      else
        temp.set(current3++, list2.get(current2++));
    }
    
    // copy remaining values from list1 to temp
    while (current1 < list1.size())
      temp.set(current3++, list1.get(current1++));
    
    // copy remaining values from list2 to temp
    while (current2 < list2.size())
      temp.set(current3++, list2.get(current2++));
    return temp;
  }
  
  
  /**method for quick sort*/
  public static <T> void quickSort(ArrayList<T> list, Comparator<T> sortOrder){
    quickSort(list, 0, list.size()-1, sortOrder);
  }
  
  private static <T> void quickSort(ArrayList<T> list, int first, int last, Comparator<T> sortOrder){
    if(last>first){
      int pivotIndex = partition(list, first, last, sortOrder);
      quickSort(list, first, pivotIndex - 1, sortOrder); //sort the lower partition
      quickSort(list, pivotIndex + 1, last, sortOrder); //sort the upper partition
    }
  }
  
  private static <T> int partition(ArrayList<T> list, int first, int last, Comparator<T> sortOrder){
    T pivot = list.get(first);
    int low = first + 1;
    int high = last;
    
    while (high > low) {
      //look for the leftmost element > pivot
      while (low <= high && sortOrder.compare(pivot, list.get(low)) >= 0){
        low++;
      }
      //look for rightmost element <= pivot
      while (low <= high && sortOrder.compare(pivot, list.get(high)) < 0){
        high--;
      }
      
      //if we found a pair of out of place elements, swap them
      if(high>low) {
        T temp = list.get(high);
        list.set(high, list.get(low));
        list.set(low, temp);
      }
    }
    
    //find where pivot needs to be placed
    while(high > first && sortOrder.compare(list.get(high), pivot) >= 0){
      high--;
    }
    
    //swap pivot with list.get(high)
    if(sortOrder.compare(pivot, list.get(high)) > 0){
      list.set(first, list.get(high));
      list.set(high, pivot);
      
      return high;
    }
    else{ //case if low partition is empty
      return first;
    }
  } 
}