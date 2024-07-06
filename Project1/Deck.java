/*
	Deck class (for TopCardPlacer class of project #1
	Anant Bajwa
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE )
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
	}

	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{//new deck to be same length as old deck
	 int[] newDeck = new int[deck.length];

	//middle of deck variable
	int midpoint = deck.length/2;			//this is the index of the middle of old deck

	int top = 0;			//top of old deck
	int mid = midpoint;			//middle of old deck
	int counter = 0;
	for(int i = 0; i<midpoint; i++)
	{
		newDeck[counter] = deck[mid];
		mid = mid+1;
		counter = counter+1;
		newDeck[counter]= deck[top];
		counter = counter+1;
		top = top+1;
	}
	//set old deck to new deck
	deck = newDeck;

	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int[] newDeck = new int[deck.length];
	 //middle of deck variable
	 int midpoint = deck.length/2;			//this is the index of the middle of old deck

	 int top = 0;			//top of old deck
	 int mid = midpoint;			//middle of old deck
	 int counter = 0;
	 for(int i = 0; i<midpoint; i++)
	 {
		 newDeck[counter]= deck[top];
		 counter = counter +1;
		 top = top+1;
		 newDeck[counter]= deck[mid];
		 counter = counter +1;
		 mid = mid+1;
	 }

	 //set old deck to new deck
	 deck = newDeck;
	}

	public String toBitString( int n )
	{
		//takes an int and returns a string of characters that represents the binary value of the number
		String myBit = "";

		//base case return empty string if n = 0
		if(n==0){
			return "";}
			char[] bits;
			bits = new char[(int)((Math.log(n))/Math.log(2))+1];			//length of bits is log2n and use int to truncate down from double

			//fill array of chars with zeros
			for(int i = 0; i<bits.length; i++)
			{
				bits[i] = '0';													//i is less than or equal to numCards-1
			}
				int p;																		//makes p into 0,1,2,3,4,5,6,7,8

			//ex: if p = 8 then 2^8 = 256, put a 1 in the index 0 spot and subtract 273-256
			//and continue with the loop}
			//do while loop versus while loop					p	8 7 6 5 4 3 2 1 0
														//bit position	  0 1 2 3 4 5 6 7 8
			do
		  {
		    p = (int)(Math.log(n)/Math.log(2));
				bits[bits.length- p-1]= '1';
		    n = n-(int)(Math.pow(2,p));

		  }while(n>=1);
			
					for(int i = 0;i<bits.length; i++)
					{
			  		myBit += bits[i] + "";
					}

		return myBit; // REPLACE WITH YOUR CODE
	}

}	// END DECK CLASS
