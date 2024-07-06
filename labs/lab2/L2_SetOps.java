import java.io.*;
import java.util.*;

public class L2_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		String[] set1 = loadSet( infile1 );
		Arrays.sort( set1 );
		String[] set2 = loadSet( infile2 );
		Arrays.sort( set2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		String[] union = union( set1, set2 );
		Arrays.sort( union );
		printSet( "\nunion: ", union );


		String[] intersection = intersection( set1, set2 );
		Arrays.sort( intersection );
		printSet( "\nintersection: ",intersection );

		String[] difference = difference( set1, set2 );
		Arrays.sort( difference );
		printSet( "\ndifference: ",difference );

		String[] xor = xor( set1, set2 );
		Arrays.sort( xor );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// USE AS GIVEN - DO NOT MODIFY
	// CAVEAT: This method will not work *correctly* until you write a working doubleLength() method.

	static String[] loadSet( BufferedReader infile ) throws Exception
	{
		final int INITIAL_LENGTH = 5;
		int count=0;
		String[] set = new String[INITIAL_LENGTH];
		while( infile.ready() )
		{
				if (count >= set.length)
					set = doubleLength( set );
				set[ count++ ] = infile.readLine();
		}
		infile.close();
		return trimArray( set, count );
	}

	// USE AS GIVEN - DO NOT MODIFY
	static void printSet( String caption, String [] set )
	{
		System.out.print( caption );
		for ( String s : set )
			System.out.print( s + " " );
		System.out.println();
	}


	/* ###############################################################
		For each of the following set operations you must execute the following steps:
		1) dimension an array that is just big enough to handle the largest possible set for that operation.
		2) add the appropriate elements to the array as the operation prescribes.
		3) before returning the array, resize it to the exact size as the number of elements in it.
	*/




	static String[] union( String[] set1, String[] set2 )
	{
		//array that is large enough to handle largest possible set for union- set1 + set2
		String[] unionResult = new String[set1.length + set2.length];
		//initialize a counter
		int counter = 0;

		//loop over set1 and add each of its elements to unionResult
			//increment count with each element that we add
			for(int i = 0; i<set1.length; i++){
				unionResult[i]= set1[i];
				counter = counter+1;
			}
			for(int j = 0; j<set2.length; j++){
			//	if(unionResult!= contains())
											//how to check if unionresult contains() the element
					if (!contains(unionResult, set2[j])){ // checks if unionResult does NOT contain the current element in set2
						unionResult[counter] = set2[j];
						counter = counter+1;
					}
			}

		return trimArray(unionResult, counter); // change this to return a trimmed full array
	}

	static String[] intersection( String[] set1, String[] set2 )
	{
		//use Math.min to initialize new intersection array - size of the
		//smaller of the two arrays
		String[] interArr = new String[Math.min(set1.length, set2.length)];
		int count = 0;
		for(int i = 0; i<set1.length; i++)
		{
				if(contains(set2, set1[i]))			//set2 is the array we are checking, the current element is set1[i]
				{
						interArr[count]= set1[i];
						count = count +1;
				}
		}
		//loop over set1
			//if set 2 contains() the current element, add it to inter and increment count
			//use counter to return trimmed array
		return trimArray(interArr, count); // change this to return a trimmed full array
	}

	static String[] difference( String[] set1, String[] set2 )
	{
		//initialize difference array to size of the first set
		String[] diffArr = new String[set1.length];
		//initialize counter
		int counter = 0;
		//ORDER MATTERS BECAUSE DIFFERENCE IS NOT COMMUTATIVE
							//first we work on set 1 and for each element we want to
							//if set2 does not contains the current element, then we
							//add it to diff and increment counter
		for(int i = 0; i<set1.length; i++)
		{
			if(!contains(set2, set1[i]))
			{
				diffArr[counter]= set1[i];
				counter = counter+1;
			}
		}
							//use count to return diff array


	return trimArray(diffArr, counter); // change this to return a trimmed full array
	}

	static String[] xor( String[] set1, String[] set2 )
	{
		//no arrays - one line

		//difference of the union(set1,set2) and intersection (set1,set2)
		return difference(union(set1,set2), intersection(set1,set2)); // change this to return a trimmed full array
	}

	// return an array of length 2x with all data from the old array stored in the new array
	static String[] doubleLength( String[] old )
		String[] newArray = new String[old.length*2];
		for(int i = 0; i<old.length; i++)
		{

			newArray[i]= old[i];
		}
		return newArray;


	}

	// return an array of length==count with all data from the old array stored in the new array
	static String[] trimArray( String[] old, int count )
	{
		//initialize a new string array of size count
		String[] newArra = new String[count];

		//loop from 0 to count to copy elements from old array to new Arrays
		for(int i = 0; i<count; i++)
		{
			newArra[i]= old[i];
		}

		return newArra; // you change accordingly
	}

	//searches an array for a given element- call it key
	static boolean contains(String[] arr, String key)
	{		//use a base case of key being null
		if (key == null)
		return false;
		//loop through each element in the array to search for key
		for(int i = 0; i<arr.length; i++)
		{
			if(key.equals(arr[i]))
			{
					return true;
			}

		}

			return false;
		//check if key matches the current element (IN THIS SPECIFIC ORDER
		//TO AVOID NULL POINTER EXCEPTIONS FROM USING .equals())



	}






} // END CLASS
