// 2021 FALL CS 445 LAB #4  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LL_Recursive<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LL_Recursive()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

 	// MUST USE (CALL) search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
 	// NO LOOPS ALLOWED NO RECURSION ALLOWED.  THE SEARCH WILL BE RECURSIVE THOUGH
 /*	public boolean contains( T key )
 	{
		// same as lab 3, but can be written in one line
		return (search(key)!=null);
	}
*/
	// #####  W R I T E  (O R  R E-W R I T E)  T H E S E   M E T H O D S   R E C U R S I V E L Y ####

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST WILL BE IDENTICAL TO OTHER
	// MUST USE RECURSION. THIS MUST BE A DEEP COPY OF THE OTHER LIST INTO THIS LIST
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public LL_Recursive( LL_Recursive<T> other )
	{

		copyHelper( other.head );  // got you started here
	}
	//idea is to create a copy of a linked list
	//constructor takes in a linkedlist and then copies linkedlist starting from
	//head of other linkedlist
	private void copyHelper( Node<T> otherHead )
	{	//copy an entire LL
			//base CASE
				//return when otherHead is null
				if(otherHead == null)
								return;
				else
				{
					insertAtFront(otherHead.data);
					otherHead = otherHead.next;
					copyHelper(otherHead);
				}

	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public void insertAtTail(T data)
	{
		 head = insertAtTailHelper(head, data);
	}

	private Node<T> insertAtTailHelper(Node<T> curr, T data)
	{

			// base case : we are at current tail of the LIST
			if(curr == null)
			{
				//make a new node and then return it
				curr = new Node<T>(data, null);
			}
			//while not at the tail
			else
			{
				curr.next = insertAtTailHelper(curr.next, data);
			}
				return curr;
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public int size()
	{
		return sizeHelper(head);
	}

	private int sizeHelper(Node<T> curr)
	{
		if(curr == null)
		{
			return 0;
		}

		else
		{
			//curr = curr.next;
			return 1+ sizeHelper(curr.next);
		}
		//return 0 when we reach the end of the base case
		//otherwise return 1+ recurse with curr.next
	}

	// USE THE TOSTRING AS OUR PRINT.  ***MUST RE-WRITE USING RECURSION***
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public String toString()
	{
		// base case: if the list is empty return an empty string
			if(head == null)
			{

				return "";
			}

		//otherwise call toStringHelper with this.head
		return toStringHelper(head);
	}

	private String toStringHelper(Node<T> curr)
	{
		//base case: if we are at the last node in the list, return its data plus
		//newLine character
		String toString = "";
		if(curr.next== null)
		{
			return curr.data.toString() + "\n";
		}
		//otherwise return current node's data,
		//an arrow, and then recurse onto curr.next
		//while not at the tail
		//return "->";
		else
		{
				return curr.data.toString() +  " -> " + toStringHelper(curr.next);
		}
	}
	// MUST BE RECURSIVE. YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public Node<T> search( T key )
	{
		Node<T> curr = head;
		//if key is null return false
		if(key== null)
				return null;						// you cannot return false since not boolean

		return searchHelper(curr, key);
		//otherwise recurse return searchHelper (head, key)

	}
	private Node<T> searchHelper(Node<T> curr, T key)
	{
		//base case key is not tfound anywhere in the list
			//or it is found at the current node
				//return curr
				//stop when curr == null
				if(curr == null)
				{
					return curr;
				}

				if(curr.data.equals(key))
				{
					return curr;
				}
				//if curr data does not match key, continue to recurse
				else
					return searchHelper(curr.next, key);

	}
public boolean contains(T key)
{
	// we want to recurse through the
	if(search(key)!=null)
				return true;

	else
				return false;
}

} //END OF LL_Recursive CLASS


///////////////////////////////////////////////////////////////////////////////////////////////////

class Node<T>
{ T data;
  Node<T> next;
  Node() { this( null, null ); }
  Node(T data){this( data, null ); }
  Node(T data, Node<T> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS
