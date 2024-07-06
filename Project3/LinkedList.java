import java.io.*;
import java.util.*;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

			for (Node<T> curr = head; curr != null; curr = curr.next )
			{
				toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
				if (curr.next != null)
					toString += " ";
			}

			return toString;


	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		//walk the list and count the nodes
		int counter = 0;
		// start curr at head of LIST
		//loop until we fall off at the end of the LIST
		for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			if(curr!= null)
				{
				counter++;
				}
		}
		return counter;
	}

	public boolean empty()
	{	//Must call size() NOT examine head
		//be written as 1 line return statement
		return size()==0;
	}


	public boolean contains( T key )
	{	//WRITE AS ONE LINE RETURN STATEMENT
		if(search(key)!=null)
			return true;

		else
			return false;
	}

	public Node<T> search( T key )
	{
		//start curr at the head of our LIST
				//traverse through the list until we get null
				for (Node<T> curr = head; curr != null; curr = curr.next)
				{
						//check if current node data matches key
						if (curr.data.equals(key))
						{
							//if matches return the node itself
						//	return curr.Node()
							return curr;
						}
						//increment curr
						curr = curr.next;
				}
				//if key not found, return null
		return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{	//lab 3
		//base case : if the list is empty
		Node<T> curr = head;

			if(curr==null)
				insertAtFront(data);
			//else get a ref to the very last node and hang it off the last node's next reference
		else
		{
			while(curr.next!= null)
			{
				curr = curr.next;
			}
			//curr at tail, tack new node at end
			Node<T>	tail = new Node<T>(data,null);
			//set curr's next field to be tail

			curr.next = tail;
		}
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS
		@SuppressWarnings("unchecked")
	public void insertInOrder(T  data)
	{
			//cast data to COMPARABLE
			// Comparable cmpData = (Comparable) data (lets us use .comparetTo () method)
			Comparable cmpData = (Comparable) data;

			//base cases
				//if the list is empty OR cmpData.CompareTo(current head data) < 0
					//return insertAtFront()

				if(head ==null || cmpData.compareTo(head.data)<0)
				{
					insertAtFront(data);
					return;
				}
			//while curr.next is null && cmpData is greater than the next node's data
				Node<T> curr = head;
				Node<T> prev = null;
				while(curr!=null && cmpData.compareTo(curr.data)>0)
				{
					//increment curr
						prev = curr;
						curr = curr.next;
				}
			//curr is now pointing to the node BEFORE where we want to insert the new node
				//make new node and then set curr.setNext to that new node
					Node<T> insert = new Node<T>(data, null);
					prev.next = insert;
					insert.next = curr;
					return;

	}

	public boolean remove(T key)
	{
		boolean found = false;
		Node<T> curr = head;
		Node<T> previous = null;

		while(curr!=null)
		{
				//set previous equal to curr
			if (curr.data.equals(key))
			{
					found = true;
					break;
			}

			previous = curr;
			curr = curr.next;
		}

		if(found == false)
		return false;

		if(curr == head)
		{
				//node we want to delete is the head
				head = head.next;
				return true;
		}
		else
		{
				//node we want to delete is not in the head
				//set previous next equal to curr's next
				previous.next = curr.next;
				return true;
		}
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		Node<T> curr = head;
		Node<T> previous = null;

		if(curr == null)
			return false;
		//else find and remove last node
		else
		{
			//traverse the list
			for(int i = 0; i<size()-1; i++)
			{
				curr = curr.next;
			}
			//now curr is at the tail
				Node<T> tail = curr;
			//	tail = null;
				return remove(tail.data);
		}
	}


	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		Node<T> curr = head;
			//how to know if there is a last node- if list is not EMPTY
			if(curr == null)
				return false;
			else
				return remove(curr.data);
	}

	public LinkedList<T> union( LinkedList<T> other )
	{	//this = set1 other = set2
		LinkedList<T> union = new LinkedList<T>();
		Node<T> currInUnion = union.head;
		for (Node<T> curr = this.head; curr != null; curr = curr.next)
		{
					union.insertInOrder(curr.data);
		}
		for(Node<T> currInOther = other.head; currInOther!= null; currInOther = currInOther.next)
		{		//if other does not contain this key
				//add to  union from other
			if(!this.contains( currInOther.data ))
				union.insertInOrder(currInOther.data);
		}
	
		return union;
	}

	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();
		Node<T> otherCurr = other.head;
		for(Node<T> thisCurr =this.head; thisCurr != null; thisCurr = thisCurr.next)
		{

			for(otherCurr = other.head; otherCurr!= null; otherCurr = otherCurr.next)
				{
							if( otherCurr.data.equals( thisCurr.data ) )
							{
								inter.insertAtFront( thisCurr.data );
							}
				}
		}

		return inter;
	}
	public LinkedList<T> diff( LinkedList<T> other )
	{
		boolean match = false;
		LinkedList<T> diff = new LinkedList<T>();
			Node<T> otherCurr = other.head;
		for(Node<T> curr = this.head; curr != null; curr = curr.next)
		{
				match = false;
				for(otherCurr = other.head; otherCurr!= null; otherCurr = otherCurr.next)
				{

					if ( otherCurr.data.equals(curr.data) )
						match = true;

					}

			if ( match == false)
				diff.insertInOrder( curr.data );
		}
		return diff;
	}

	public LinkedList<T> xor( LinkedList<T> other )
	{
		return  union(other).diff(inter(other));  // REPLACE WITH YOUR CODE

	}


} //END LINKEDLIST CLASS

// A D D   N O D E   C L A S S  D O W N   H E R E
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S
// M A K E  T O  S T R I N G  P U B L I C
class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable

{
  T data;
   Node<T> next;

  Node()
  {
    this( null, null );
  }

  Node(T data)
  {
    this( data, null );
  }

  Node(T data, Node<T> next)
  {
    this.data = data; // setData( data );
    this.next = next; //setNext( next );
  }

  //T getData()
//  {
  //  return data;
//  }

//  Node<T> getNext()
//  {
  //  return next;
  //}

//  void setData(T data)
  //{
  //   this.data = data;
//  }

//  void setNext(Node<T> next)
//  {
//    this.next = next;
//  }
  public String toString()
  {
	  return ""+ data;
  }

} //EOF
