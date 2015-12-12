/*
CSE 17
Amber Wallace
alw218
Homework #6 DEADLINE: May 3, 2015
Program Description: Linked Lists versus Binary Search Trees
*/ 

/**Create class for LinkedListVersusBst*/
public class LinkedListVersusBst{
  /**Main method*/
  public static void main(String args[]){
    //create an array of 1000000 random integers
    Integer[] array = new Integer[1000000];
    for(int i=0; i<1000000; i++){
      //values are random between 1 and 2000000 inclusive
      array[i] = (int) (java.lang.Math.random()*2000000) + 1;
    }
    
    //print necesssary formating
    System.out.printf("Task\t\t\tLinked\tBST\n");
    System.out.printf("----------------------------\t-------\t-------\n");
    
    //create linked list from the array and track time
    long startTime = System.currentTimeMillis();
    MyLinkedList<Integer> linkedList = new MyLinkedList<Integer>(array);
    long endTime = System.currentTimeMillis();
    long linkedListExecutionTime = endTime - startTime;
    
    //create bst from array
    startTime = System.currentTimeMillis();
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(array);
    endTime = System.currentTimeMillis();
    long bstExecutionTime = endTime - startTime;
    
    System.out.printf("Time to build structures\t%3d\t%3d\n", linkedListExecutionTime, bstExecutionTime);
    
    
    //linked list get 10
    startTime = System.currentTimeMillis();
    //attempt to retrieve the 10 elements at every 100,000 index values starting at 0
    for(int i=0; i<=900000; i+=100000){
      linkedList.get(i);
    }
    endTime = System.currentTimeMillis();
    long llGet10 = endTime - startTime;
    
    //bst get 10
    startTime = System.currentTimeMillis();
    //attempt to retrieve the 10 elements at every 100,000 index values starting at 0
    for(int i=0; i<=900000; i+=100000){
      bst.get(i);
    }
    endTime = System.currentTimeMillis();
    long bstGet10 = endTime - startTime;
    
    System.out.printf("Get 10 items by index\t%3d\t%3d\n", llGet10, bstGet10);
    
    
    //linked list find by value
    Integer[] llIntArray = new Integer[10];
    startTime = System.currentTimeMillis();
    //attempt to find 10 elements with values that are multiples of 200000
    int index = 0;
    for(int multiple = 1; multiple<=11; multiple++){
      if(linkedList.contains(new Integer(200000*multiple))){
        llIntArray[index] = new Integer(200000*multiple);
        index++;
      }
    }
    endTime = System.currentTimeMillis();
    long llSearchByValue = endTime - startTime;
    
    //bst find by value
    Integer[] bstIntArray = new Integer[10];
    startTime = System.currentTimeMillis();
    index = 0;
    for(int multiple = 1; multiple<=11; multiple++){
      if(bst.search(new Integer(200000*multiple))){
        bstIntArray[index] = new Integer(200000*multiple);
        index++;
      }
    }
    endTime = System.currentTimeMillis();
    long bstSearchByValue = endTime - startTime;
    
    System.out.printf("Search for 10 items by value\t%3d\t%3d\n", llSearchByValue, bstSearchByValue);
    
    //linked list remove by value
    startTime = System.currentTimeMillis();
    //attempt to remove 10 elements with values that are multiples of 200000
    for(int i = 0; i<index; i++){
      linkedList.remove(llIntArray[i]);
    }
    endTime = System.currentTimeMillis();
    long llRemoveByValue = endTime - startTime;
    
    //bst list search by value
    startTime = System.currentTimeMillis();
    for(int i = 0; i<index; i++){
      bst.delete(bstIntArray[i]);
    }
    endTime = System.currentTimeMillis();
    long bstRemoveByValue = endTime - startTime;
    
    System.out.printf("Remove 10 items by value\t%3d\t%3d\n", llRemoveByValue, bstRemoveByValue);
    
    
    //linked list remove by index
    startTime = System.currentTimeMillis();
    //remove
    for(int i=0; i<=900000; i+=100000){
      if(linkedList.getSize() > i){
        linkedList.remove(i);
      }
    }
    endTime = System.currentTimeMillis();
    long llRemoveByIndex = endTime - startTime;
    
    //bst remove by index
    startTime = System.currentTimeMillis();
    for(int i=0; i<=900000; i+=100000){
        bst.remove(i);
    }
    endTime = System.currentTimeMillis();
    long bstRemoveByIndex = endTime - startTime;
    
    System.out.printf("Remove 10 items by index\t%3d\t%3d\n", llRemoveByIndex, bstRemoveByIndex);
  }
}