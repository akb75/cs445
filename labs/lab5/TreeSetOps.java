import java.io.*;
import java.util.*;

public class TreeSetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		TreeSet<String> set1 = loadSet( infile1 );
		TreeSet<String> set2 = loadSet( infile2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		TreeSet<String> union = union( set1, set2 );
		printSet( "\nunion: ", union );

		TreeSet<String> intersection = intersection( set1, set2 );
		printSet( "\nintersection: ",intersection );

		TreeSet<String> difference = difference( set1, set2 );
		printSet( "\ndifference: ",difference );

		TreeSet<String> xor = xor( set1, set2 );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN
	// no testerfile just run the same file itself
	//first thing to do is use buffered readers to read in the contents of the files
	// Y O U    W R I T E   T H I S     M E T H O D

	//return type is TreeSet<String>

	static TreeSet<String> loadSet( BufferedReader infile ) throws Exception
	{
		//declare a treeSet String
		TreeSet<String> set = new TreeSet<String>();
		//use the passed in buffered reader to populate the tree set with the input data
		while( infile.ready() )
			{
				String data = infile.readLine();
					//set.add(infile.readLine());

				set.add(data);


			}
			infile.close();
		//example: when loadSet(infile1) --> args[0] --> set1
		// reads in the data from set 1
		// infile2 --> set 2
		return set;
	}

	// Y O U    W R I T E   T H I S     M E T H O D
	// elements in a tree set are always in a sorted order
	// elements do not accept duplicates
	static void printSet( String caption, TreeSet<String> set )
	{
		// print out the caption "set 1:"
		System.out.print(caption);
		//print out the contents of the tree set string --> complex for loop

		for(String element: set)
			System.out.print(element + " ");

		//print a new line
		System.out.println();
	}

	// Y O U    W R I T E   T H I S     M E T H O D
	static TreeSet<String> union( TreeSet<String> set1, TreeSet<String> set2 )
	{
		//union of set 1 and set 2 of treeSet
		TreeSet<String> union = new TreeSet<String>();

			union.addAll(set1);
				union.addAll(set2);
		// add set 1 to union
		return union;
	}

	static TreeSet<String> intersection( TreeSet<String> set1, TreeSet<String> set2 )
	{	//no loops
			TreeSet<String> intersection = new TreeSet<String>();
				intersection.addAll(set1);
				intersection.retainAll(set2);

		return intersection;
	}

	static TreeSet<String> difference( TreeSet<String> set1, TreeSet<String> set2 )
	{
			TreeSet<String> difference = new TreeSet<String>();
		difference.addAll(set1);
		difference.addAll(set2);
		difference.removeAll(set2);
		return difference;
	}

	static TreeSet<String> xor( TreeSet<String> set1, TreeSet<String> set2 )
	{	//set1 and set2 but not intersection (no charlie)
			TreeSet<String> xor = new TreeSet<String>();
		xor.addAll(union(set1, set2));
		xor.removeAll(intersection(set1,set2));
		return xor;
	}


} // END CLASS
