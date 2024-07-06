/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE )
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		//initialize deck to be exactly numCards long (size of deck)
		//initialize the stuff in deck to be numCards -1 in order
		deck = new int[numCards];
		// YOU DO THIS => init deck to be exactly numCards long
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
			
		for(int i = 0; i<numCards; i++)
		{
			deck[i] = i;													//i is less than or equal to numCards-1
		}
	}
						//toString method already finished
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
	{
		//new deck to be same length as old deck
		 int[] newDeck = new int[deck.length];

		//middle of deck variable
		int midpoint = deck.length/2;			//this is the index of the middle of old deck

		int top = 0;			//top of old deck
		int mid = midpoint;			//middle of old deck
		int counter = 0;
		for(int i = 0; i<midpoint; i++)
		{
			newDeck[counter] = deck[mid];					//middle of old deck to top of new deck
			mid = mid+1;
			counter = counter+1;
			newDeck[counter]= deck[top];						//top of old deck to second position of new deck
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

	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		for(int i = 0; i<deck.length; i++)
		{
			// if(deck[i] == i)
			// 	return true;
			if(deck[i]!=i)
				return false;
		}
		// YOUR CODE HERE DELETE LINE
		return true; // JUST HERE TO COMPILE
	}
}	// END DECK CLASS
