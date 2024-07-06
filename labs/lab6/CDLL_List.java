import java.io.*;
import java.util.*;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;

	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_List( String fileName, String insertionMode ) throws Exception
	{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{	@SuppressWarnings("unchecked")
				T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
				if ( insertionMode.equals("atFront") )
					insertAtFront( data );
				else if ( insertionMode.equals( "atTail" ) )
					insertAtTail( data );
				else
					die( "FATAL ERROR: Unrecognized insertion mode <" + insertionMode + ">. Aborting program" );
			}
			infile.close();
	}

	private void die( String errMsg )
	{
		System.out.println( errMsg );
		System.exit(0);
	}

	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT YOU COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	@SuppressWarnings("unchecked")
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


	// TACK A NEW NODE ONTO THE FRONT OF THE LIST

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


	// TACK ON NEW NODE AT END OF LIST
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

		//2 lines of CODE
		//reuse insert at front and adjust head pointer
	}

	// RETURN TRUE/FALSE THIS LIST CONTAINS A NODE WITH DATA EQUALS KEY
	public boolean contains( T key )
	{
		//1 line that calls search method
		if(search(key)!=null)
			return true;

		return false;
	}

	// RETURN REF TO THE FIRST NODE (SEARCH CLOCKWISE FOLLOWING next) THAT CONTAINS THIS KEY. DO -NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
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
			do{
					myString += curr.data;
					myString += "<=>";
					curr = curr.next;
			}while(curr.next!=head);

			myString += curr.data;
			return myString;
			//if curr.next == head then that is the tail node

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
