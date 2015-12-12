/*
CSE 17
Amber Wallace
alw218
Homework #6 DEADLINE: May 3, 2015
Program Description: Linked Lists versus Binary Search Trees
*/ 

import java.util.Iterator;
import java.util.ArrayList;

/** This class demonstrates the implementation of a binary search
  * tree. Note a binary search tree has the property that it
  * either empty, or that it is node whose content is greater
  * than the content of all nodes in the left subtree, and less
  * tha the content of all nodes in the right subtree. */
public class BinarySearchTree <E extends Comparable<E>> {

  /* We only need to know the root of the tree.  From this node
   * we can find all other nodes. */
  TreeNode<E> root;

  /** Create an empty binary search tree. */
  public BinarySearchTree() {
    root = null;
  }
  
  public BinarySearchTree(E[] objects) {
    for (int i=0; i < objects.length; i++)
      insert(objects[i]);
  }
 
  
  /** Insert an element into the appropriate available place in
    * the tree. */
  public boolean insert(E element) {
    // if tree is empty, new element is the root
    if (root == null)
      root = new TreeNode<E>(element);
    else {
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null) {
        if (element.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (element.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          return false;           // duplicate node is not inserted
      }
      if (element.compareTo(parent.element) < 0)
        parent.left = new TreeNode<E>(element);
      else 
        parent.right = new TreeNode<E>(element);
    }
    return true;
  }
  
  /** Delete an element e from the tree. Ensures that the
    * tree is still a binary search tree afterwards. */
  public boolean delete(E e) {
    TreeNode<E> parent = null;      // the node above current
    TreeNode<E> current = root;
    
    // move current to reference the node with the selected element
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break;      // element is in the tree, and referenced by current
    }
    
    if (current == null)
      return false;          // element not in tree
    
    // case 1: selected element has no left child
    if (current.left == null) {
      if (parent == null) {            // if the deleted node was the root...
        root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    
    // case 2: selected element has a left child
    else {
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;
      
      // search for the rightmost descendant in the left subtree
      // this has the largest element in the subtree!!!
      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right;
      }
      
      // copy this node's element to current
      // this preserves the ordering, because it is larger than any of current's other children
      current.element = rightMost.element;
      
      /* rightmost node has no right child, so when it moves, we can simply have
       its parent point to its left child */
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else               // if the rightmost child is current.left... 
        parentOfRightMost.left = rightMost.left;
    }
    return true;
  }

  /** Perform an in order traversal of the tree and print out its
    * elements. */
  public void inorder() {
    inorder(root);
    System.out.println();
  }
  
  /** A recursive helper function for inorder(). */
  protected void inorder(TreeNode<E> root) {
    if (root == null) 
      return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  /** Perform a preorder traversal of the tree and print out its
    * elements in the form node [left-subtree right-subtree] */
  public void preorder() {
    preorder(root);
    System.out.println();
  }
  
  /** A recursive helper function for preorder(). */
  public void preorder(TreeNode<E> root) {
    if (root == null) {
      System.out.print(".");    // use "." as placeholder for empty
      return;
    }
    System.out.print(root.element + " [");
    preorder(root.left);
    System.out.print(" ");
    preorder(root.right);
    System.out.print("] ");
  }

  /** Returns true if element is in the tree, else false. In the
    * worst-case, only needs to traverse a number of nodes equal to
    * the height of the tree. */
  public boolean search(E element) {
    
    TreeNode<E> current = root;
    while (current != null) {
      if (element.compareTo(current.element) == 0)
        return true;
      if (element.compareTo(current.element) < 0)
        current = current.left;
      else
        current = current.right;
    }
    return false;
  }

  /* Return an iterator the retrieves the elements in order. */
  public Iterator<E> inorderIterator() {
    return new InorderIterator();
  }
  
  /**Return the element at the given index*/
  public E get(int index){
    InorderIterator iterator = new InorderIterator();
    
    for(int i=0; i<index; i++){
      if(iterator.hasNext()){ 
        iterator.next();
      }
      else{
        return null;
      }
    }
    
    if(iterator.hasNext()){
      return iterator.next();
    }
    else{
      return null;
    }
  }
  
  /**Remove and return element at index*/
  public E remove(int index){
    //get element at index
    E e = get(index);
    //if there is an element to be deleted return it
    if(e != null){
      delete(e);
      return e;
    }
    else{ //no element
      return null;
    }
  }

  /** An inner class that defines the operations for an inorder iterator. 
    * Since it is an inner class, it can access the instance variables of
    * binary search tree. */
  class InorderIterator implements Iterator<E> {
    
    /** The elements in order. */
    private ArrayList<E> list = new ArrayList<E>();
    /** Index of the next element in the iterator. */
    private int current = 0;
    
    /** Create a new InorderIterator. Performs an inorder traversal
      * and stores the results in list. */
    public InorderIterator() {
      inorder();
    }
    
    /** Perform a inorder traversal of the tree and stores the results in list. */
    private void inorder() {
      inorder(root);
    }
    
    /** A recursive helper method for inorder() */
    private void inorder(TreeNode<E> root) {
      if (root == null) 
        return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }
    
    /** Return true if the iterator has a next element, else false. */
    public boolean hasNext() {
      if (current < list.size())
        return true;
      else
        return false;
    }
    
    /** Return the next element in the iterator. After retrieving the
      * element, increment current to point to the element after it. */
    public E next() {
      return list.get(current++);
    }
    
    /** Remove the current element from the iterator. Not all
      * iterators support this operation, but they must provide something
      * in order to implement the interface. In this case, we remove
      * the element and rebuild the underlying ArrayList. */
    public void remove() {
      delete(list.get(current));
      list.clear();
      inorder();
    }
  }

  /** An inner class that defines a node in a tree.  */
  public static class TreeNode<E> {
    
    /* all of the instance variables have package private access, which makes it
     * possible for BinarySearchTree to access the values directly. */
    E element;
    TreeNode<E> left = null;
    TreeNode<E> right = null;
    
    public TreeNode(E e) {
      element = e;
    }
  }

}