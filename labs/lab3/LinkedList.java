// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list	reference to a node

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// COPY ALL NODES FROM OTHER LIST INTO THIS LIST. WHEN COMPLETED THIS LIST IDENTICAL TO OTHER
	//every new command no guarantee that the chunk of memory is related to last time you asked for a piece of memory
	//
	public LinkedList( LinkedList<T> other )
	{
		//copy other list into this list
		//head is head of the first element of the list
		for (Node<T> curr = head; curr != null; curr = curr.getNext())
		{
			head = other.head;
		}
	// YOU ABSOLUTLEY MUST CHANGE THIS. THIS IS A SHALLOW COPY :(
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);		//full constructor
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.getNext())
		{
			toString += curr.getData();		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.getNext() != null)
				toString += " -> ";
		}

		return toString + "\n";
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################

	// TACK A NEW NODE ONTO THE END (CABOOSE) OF THE LIST
	public void insertAtTail(T data)
	{
		//base case : if the list is empty
			Node<T> curr = head;
		//	if(curr==null)
				if(curr==null)
				 	insertAtFront(data);

				//else get a ref to the very last node and hang it off the last node's next reference
			else
			{
				while(curr.getNext()!= null)
				{
					curr = curr.getNext();
				}
				//curr at tail, tack new node at end
				Node<T>	tail = new Node<T>(data,null);
				//set curr's next field to be tail
				curr.setNext(tail);
			}
	}

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT WE MAKE YOU
	// COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP

	//
	public int size()
	{
		//walk the list and count the nodes
		//initialize counter
		int counter = 0;
		// start curr at head of LIST
		//Node<T> curr = head;

		//loop until we fall off at the end of the LIST
	//	while(curr.getNext()!= null)
	//Node<T> curr = head;
		for (Node<T> curr = head; curr != null; curr = curr.getNext())
		{
			if(curr!= null)
				{
				counter++;
				}
		}

		return counter;
	}

	// MUST USE search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
	// NO LOOPS ALLOWED
	public boolean contains( T key )
	{
			if(search(key)!=null)
				return true;

			return false;
	}

	// TRAVERSE LIST FRONT TO BACK LOOKING FOR THIS DATA VALUE.
	// RETURN REF TO THE FIRST NODE THAT CONTAINS THIS KEY.
	// DO NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	public Node<T> search( T key )
	{
		//start curr at the head of our LIST
	//	Node<T> curr = head;

				//traverse through the list until we get null
				for (Node<T> curr = head; curr != null; curr = curr.getNext())
				{
						//check if current node data matches key
						if (curr.getData().equals(key))
						{
							//if matches return the node itself
						//	return curr.Node()
							return curr;
						}
						//increment curr
						curr = curr.getNext();
				}
				//if key not found, return null
		return null;
	}


//*/

} //END OF LINKEDLIST CLASS DEFINITION
