import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked")
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data );
		}
		infile.close();
	}

	//to string, size inserttail, search from lab

	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// TACK ON NEW NODE AT END OF LIST
	public void insertAtFront(T data)
	{
		//make head node point to current node

		CDLL_Node<T> curr = head;
		//initialize a new node
	CDLL_Node<T> insertThis = new CDLL_Node<T>(data, null, null);
		//make new node next node point at head node
		if(head == null)
		{

			head = insertThis;
			head.prev = insertThis;
			return;
		}

		insertThis.next = head;
		// make new node tail point at head's previous node
		insertThis.prev = head.prev;
		// make head's previous node's next node be the new node
		head.prev.next = insertThis;
		// make head's previous node be the new node
		head.prev = insertThis;
		// make head equal to the new node
		head = insertThis;
		// SEE THE LECTURE VIDEO HOW TO WRITe THIS FUNCTION

	}

	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		//make head node point to current node
		if(head == null)
			{
				insertAtFront(data);
				return;
			}


		insertAtFront(data);
		CDLL_Node<T> curr = head;
		head = head.next;
		head.prev = curr;
	}


	public int size()
	{
		//size of a linkedlist base case
		//base case list is empty
		CDLL_Node<T> curr = head;
		if(curr == null)
			return 0;

		//start curr at head and initialize a counter
		//do while loop to visit each node and increment size until we get
		// back to the head while head!= curr

		// move to next node and increment size in do while loop
		int size = 0;
		do{
			size++;
			curr = curr.next;

		}while(curr!=head);

		return size;
	}

	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{
		CDLL_Node<T> curr = head;
	//base case : list is empty or search key NULL
	if(curr== null || key == null)
		return null;

	//start curr at head
	//use a do while loop to check each node until we get back to head
	do{
		if(curr.data.equals(key))
				return curr;

			curr = curr.next;


	}while(curr!=head);

	//if key not found, return NULL
	return null;
	}

	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		CDLL_Node<T> curr = head;
	//base case list is empty return empty string
	if(curr == null)
		return "";

		//initialize an empty string and start curr at head
		String myString = "";

		//use a do while loop to append the data of each string
		// then intersperse the <=> after the data of every node
		//except the tail node
		/*
		do{
				myString += curr.data;
				myString += "<=>";
				curr = curr.next;
		}while(curr.next!=head);

		myString += curr.data;

		*/

		do{
			myString += curr.data;
			if(curr.next!=head)
				myString += "<=>";
			curr = curr.next;
		}while(curr!=head);

		return myString;

	}

	void removeNode( CDLL_Node<T> deadNode )
	{
		//given reference to node that you are trying to removeNode
		//adjust two pointers
		deadNode.prev.next = deadNode.next;
		deadNode.next.prev = deadNode.prev;
	}

	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		//curr is the node that is to be deleted
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;

		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.data;

			//deadNode is the curr, and deadName is the data in the deadNode
			System.out.println( "stopping on " + curr.data + " to delete " + curr.data);

			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS

			// 1: you gotta move that curr off of the deadNode.
			//    if skipCount poitive do curr=curr.next  esle do  curr=curr.prev
			// 2: check to see if HEAD is pointing to the deadnode.
			//    If so make head=curr

			if(skipCount >0)
				curr = curr.next;
			else
				curr = curr.prev;
			if(head == deadNode)
				head = curr;

			// NOW DELETE THE DEADNODE
			removeNode(deadNode);
			System.out.println("deleted. list now: " + toString() ); // toString prints the

			// if the list size has reached 1 return YOU ARE DONE.  RETURN RIGHT HERE
			if(size() ==1)
					return;
			 //System.out.println("resuming at curr.data, skipping curr.data + skipCount-1 nodes CLOCKWISE/COUNTERWISE after");
			 String route = " ";
			 if(skipCount>0)
			 	route = " CLOCKWISE";
				if(skipCount<0)
				route = " COUNTER_CLOCKWISE";
				int times = Math.abs(skipCount) -1;

			 System.out.println("resuming at " + curr.data + " skipping " + curr.data + " + " + times + " nodes " + route + " after");
			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE or COUNTER)
			//Math.abs - returns the absolute value of a number
			// advance skipCount times so if skip = 2 , we move to curr.next.next;
			int counter = 0;
			//System.out.println(skipCount + " this is skipCount");


			do
				{
					if(size() == 1)
						return;
					if(skipCount>0)
					{
						curr = curr.next;
						counter++;
					}
						//System.out.println("this is entered. the curr is incrementing");
					if(skipCount<0)
					{
						curr = curr.prev;
						counter++;

					}

				}	while(counter!= Math.abs(skipCount));



		//	} while (counter!= Math.abs(skipCount));




			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			// Scanner kbd = new Scanner( System.in ); String junk = kbd.nextLine();

		}
		while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}

} // END CDLL_LIST CLASS

// PRIVATE TO CODE OUTSIDE FILE. BUT PUBLIC TO CODE INSIDE
class CDLL_Node<T>
{
  T data; // DONT DEFINE MEMBERS AS PUBLIC OR PRIVATE
  CDLL_Node<T> prev, next; //
  CDLL_Node() 		{ this( null, null, null ); }
  CDLL_Node(T data) { this( data, null, null);  }
  CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {	this.data=data; this.prev=prev; this.next=next;
  }
  public String toString() // TOSTRING MUST BE PUBLIC
  {	return ""+data;
  }
} //END NODE CLASS
