import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;  // MAY CHOSE ANOTHER NUMBER

	public MyHashSet( int numBuckets )
	{	size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}
	// RETURNS INT IN RANGE [0..numBuckets-1]
	private int hashOf( String key, int numBuckets ) //  number returned -MUST- MUST BE IN [0..numBuckets-1]
	{
		//char[] charString = new char[key.length()];

		char charString;
		int sum = 0;
		int number = 0;
		for(int i = 0; i< key.length(); i++)
		{
			charString = key.charAt(i);
			//for each char, do a modulo and assign to a different int

			//number = number *7 + charString;

			number = (number)/2 * 7 + charString;
			sum = sum + number;
			sum = Math.abs(sum);
			sum = sum % numBuckets;

		}
		//return sum % numBuckets;
		return sum;
	}

	public boolean add( String key )
	{
		//HS do not contain duplicates

		//base case - does it does not contain duplicate
		if(contains(key))
				return false;

		// your code here to add the key to the table and ++ your size variable
		int hashedTo = 0;
		//hash the key using hashOf to get an index  -> index in mine is hashedTo
		hashedTo = hashOf(key, numBuckets);
		//insert a new node Node() containing the key into the LL at the index that the key hashed to

	 	bucketArray[hashedTo] = new Node (key, null);

		++size; // you just added a key to one of the lists
		if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
		return true;
	}

	//advantage of hashset is o(1) calls
	public boolean contains( String key )
	{
		// You hash this key. goto that list. look for this key in that list
		int myIndex;
		myIndex = hashOf(key, numBuckets);
		//hash key, go to LL located at that hashed index
		//look for the key in that index
		Node head = bucketArray[myIndex];
		if(head == null)
			return false;
		//head of linked list locating at index returned by hashOf method then standard LL contains method
		Node curr = head;
		while(curr !=null)
		//curr pointer pointing at that Node, check if there is a match with key, increment curr
		{
			if(curr.data.equals(key))
					return true;

				curr = curr.next;
		}
			return false;
	}

	private void upSize_ReHash_AllKeys()
	{	System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArray.length, bucketArray.length*2  );
		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;

	/*	FOR EACH LIST IN THE ARRAY
			FOR EACH NODE IN THAT LIST
				HASH THAT KEY INTO THE BIGGER TABLE
				BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS
	*/
	int hashedIndex = 0;
	for(int i = 0; i<bucketArray.length; i++)
	{
			Node head = bucketArray[i];				//while(curr.next!=null)
			//for(Node curr = head; curr!=null; curr=curr.next)
			Node curr = head;
			if(curr == null)
				break;

			while(curr!=null)
			{
				hashedIndex = hashOf(curr.data,biggerArray.length );
				curr = curr.next;
			}
	}

		bucketArray = biggerArray;
	} // END OF UPSIZE & REHASH

		public boolean remove( String key )
		{// if not found return false else remove & return true
		//hash the keys
		if(contains(key) != true)
					return false;

			int myIndex;
						myIndex = hashOf(key, numBuckets);
		//initialize curr to head of linkedlist located at the hash index
				Node head = bucketArray[myIndex];
				Node previous = null;
				boolean found = false;
		//loop through and use curr located at that hash index
		//remove the node containing the key if foound
		Node curr = head;
		//for(Node curr = head; curr!=null; curr = curr.next )

		while(curr!=null)
		//curr pointer pointing at that Node, check if there is a match with key, increment curr
		{
			//remove node if curr data equals key, return true
			if(curr.data.equals(key))
			{
				size--;
				found = true;
						return true;
			}
				previous = curr;
				curr = curr.next;
				//	curr.next = curr.next.next;
		//		curr = curr.next;
		}
		//if not found return false
		if(found == false)
				return false;
		if(curr == head)
		{
			head = head.next;
			size--;
			return true;
		}
		else{
			previous.next = curr.next;
			return true;
		}

		}
		public boolean isEmpty() // use the call to size
		//call to size
		{
			return size() == 0;
		}

		// if size == 0 return true
		public int size() // number of keys currently stored in the container
		{
			return size;
		}
		//size is incremented every add
		//in remove method decrement size



	   public void clear()
		 {

			 if(bucketArray[0] != null)
			 {	//walk the bucketArray and remove all of the nodes
				 for(int i = 0; i<bucketArray.length; i++)
				 {
					  Node head = bucketArray[i];
					 	Node curr = head;
						while(curr.next!=null)
						{
							remove(curr.data);
						}

				 }
			 }

		 }	//clears the hash set
} //END MyHashSet CLASS

class Node
{	String data;
	Node next;
	public Node ( String data, Node next )
	{ 	this.data = data;
		this.next = next;
	}
}
