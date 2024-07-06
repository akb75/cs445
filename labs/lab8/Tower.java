import java.io.*;
import java.util.*;

public class Tower<T>
{
	private Disk<T> base;  // pointer to first disk at BASE of the tower (i.e. the old head pointer)
	private Disk<T> top;   // pointer to last disk at TOP of the tower   (i.e. the old tail pointer)

	public Tower()
	{	top = null;
		base = null; // compiler does this anyway. just for emphasis
	}

	public boolean empty()
	{
		return (base==null);
	}

	// i.e. the old insertAtTail or now insertAtTop we call it a PUSH
	public void push(T label)
	{
		// YOU WRITE THIS METHOD
		//base case when list is EMPTY
		if(base== null && top == null)
		{
			Disk<T> myNode = new Disk<T>(label, null);
			base = myNode;
			top = myNode;
		}

		else
		{
			//regular case- exactly 2 lines of code
			//no loop on this case
				Disk<T> tails = new Disk<T>(label, null);
				top.next = tails;
				top =  tails;
		}


	}

	// i.e. the old removeAtTail or now removeAtTop we call it a POP
	public Disk<T> pop() throws Exception
	{
		// YOU WRITE THIS METHOD
		// MUST THROW FATAL EXCEPTION IF TOWER IS EMPTY first base case
		Disk<T> curr = base;
		Disk<T> prev = null;
		if(base ==null || top == null)
			throw new EmptyStackException();

			//second base case
			if(top==base)
			{
				Disk<T> anotherNode = new Disk<T>(base.label, null);
				top = null;
				base = null;
				return anotherNode;
			}

		//actual case
		//loop to one node before the TOP
		while(curr.next.next !=null )
		{
			curr = curr.next;
	
		}
		Disk<T> myNode = new Disk<T>(curr.next.label, null);
		curr.next = null;
		top = curr;
		//remove the top node and reassign the top
		// return the node that was removed

		return myNode; // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// prints the tower base to top, left to right,  respectively //
	public String toString()
	{	if (base == null ) 	return "EMPTY\t";
		String toString = "";
		for ( Disk<T> curr = base; curr != null ; curr=curr.next )
			toString += curr.label + " ";

		return toString;
	}
} // END TOWER CLASS

/*###############################################################################*/

class Disk<T>
{
	T label;
	Disk<T> next;

	Disk(T data)
	{	this( data, null );
	}

	Disk(T label, Disk<T> next)
	{	this.label = label;
		this.next = next;
	}

} // END DISK CLASS
