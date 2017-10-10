package SortAndSearch;

//WE HAVE USED THE ALGORITHMS BOOK TO HELP US IMPLEMENT THIS FUNCTION

//BST TO CREATE BINARY SEARCH TREE

import java.util.NoSuchElementException;

public class BST <CourseName extends Comparable <CourseName>, Index>  //creates new BST
{
    private Node root; 	//holds the root of the BST

    private class Node 
    {
        private CourseName courseName;   // bst is sorted by courseName
        private Index index;         // holds index of the course name
        private Node left, right;  // holds the left and right subtrees
        private int N;             // number of nodes in  thesubtree

        public Node(CourseName courseName, Index index, int N)  //node constructor
        {
        	//sets each course name and  index and N
            this.courseName = courseName;
            this.index = index;
            this.N = N;
        }
    }

    
    public boolean isEmpty() 
    {	//checks if bst is empty
        return size() == 0;
    }


    public int size() 
    { //returns the size of bst
        return size(root);
    }

   
    private int size(Node x) 
    {//private method to check size of bst
        if (x == null) return 0;
        else return x.N;
    }

    public boolean contains(CourseName courseName) 
    {//checks if there is a course name to value pair using a key as an arguemtn
        return get(courseName) != null;
    }
 
    public Index get(CourseName courseName) 
    {//returns value associated with the key
        return get(root, courseName);
    }
    
    //private methdod to return value associated with key
    private Index get(Node x, CourseName courseName) 
    {
        if (x == null) return null; //returns null if empty
        int cmp = courseName.compareTo(x.courseName); //compares course to course at root
        if      (cmp < 0) return get(x.left, courseName); //if -ve, retursn less portion
        else if (cmp > 0) return get(x.right, courseName); //iff +ve, returns right portion
        else              return x.index; //otherwise returns the root
    }

    public void put(CourseName courseName, Index index) 
    {//adds a course name and index to the root
        if (index == null) {delete(courseName); return;}
        root = put(root, courseName, index);
    }

    private Node put(Node x, CourseName courseName, Index index) 
    {//priave method to add course and value to the root
        if (x == null) return new Node(courseName, index, 1); //returns null if empty
        int cmp = courseName.compareTo(x.courseName); //compares course name to root
        if      (cmp < 0) x.left  = put(x.left,  courseName, index); //if -ve, then puts in left 
        else if (cmp > 0) x.right = put(x.right, courseName, index); //if greater then puts in right 
        else              x.index   = index; //otherwise puts at root 
        x.N = 1 + size(x.left) + size(x.right); //increases the size
        return x; //returns x
    }
    
     public void deleteMin() 
     {//deletes the minimum note
         if (isEmpty()) throw new NoSuchElementException("Symbol table underflow"); //exception if empty
         root = deleteMin(root);
     }

     private Node deleteMin(Node x)
     {//private method to delete mi
         if (x.left == null) return x.right; //if left is emtpy, return the right
         x.left = deleteMin(x.left); //recursive function to delete the min
         x.N = size(x.left) + size(x.right) + 1; //sets the size
         return x; //returns x
     }

     public void deleteMax()
     {	//deletes the max item
         if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
         root = deleteMax(root);
     }

     private Node deleteMax(Node x) 
     {
         if (x.right == null) return x.left; //if right is null then return the left
         x.right = deleteMax(x.right); //deletes on the the right
         x.N = size(x.left) + size(x.right) + 1; //resizes tree
         return x;//returns x
     }

     public void delete(CourseName courseName) 
     {//deletes a specified course name
         root = delete(root, courseName);
     }

     private Node delete(Node x, CourseName courseName) 
     {
         if (x == null) return null; //iff empty, return null
         int cmp = courseName.compareTo(x.courseName); //compare course name to root
         if      (cmp < 0) x.left  = delete(x.left,  courseName); //if less then delete from the left
         else if (cmp > 0) x.right = delete(x.right, courseName); //if more, then delete from the right
         else { 
             if (x.right == null) return x.left; //otherwise if right is empty, reutnthe left
             if (x.left  == null) return x.right; //if left is empty, return right
             Node t = x;
             x = min(t.right); //sets x as min right 
             x.right = deleteMin(t.right); //sets right of root and deltes right 
             x.left = t.left; //sets root left as empty left
         } 
         x.N = size(x.left) + size(x.right) + 1; //resizes 
         return x; //returns x
     } 
     
      public CourseName min()  
      { //returns the minimum value
          if (isEmpty()) return null;
          return min(root).courseName;
      } 

      private Node min(Node x)
      {  
          if (x.left == null) return x;  //if left is null then return root
          else                return min(x.left);  //otherwise keep goring through untill null
      } 

      public CourseName max() 
      {//returns max root
          if (isEmpty()) return null;
          return max(root).courseName;
      } 

      private Node max(Node x) 
      { 
          if (x.right == null) return x;  //if right is null then return x
          else                 return max(x.right);  //otherwise keep going thgou until right is null
      } 
}





