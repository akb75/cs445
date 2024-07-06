import java.io.*;
import java.util.*;
public class WoodChuckSet
{
	public static void main(String args[]) throws Exception
	{
		TreeSet<String> setOfWords = new TreeSet<String>();
		Scanner infile = new Scanner( new File( args[0] ) ); // infile1.txt
		while ( infile.hasNext() )
			setOfWords.add( infile.next() );
		infile.close();

		System.out.print("Words from " + args[0] + ": ");
		for ( String word : setOfWords )
			System.out.print( word + " " );
		System.out.println();

		infile = new Scanner( new File( args[1] ) ); // infile2.txt
		System.out.print("Words from " + args[1] + " that are found in " + args[0] + ": ");
		while ( infile.hasNext() )
		{
				String word = infile.next();
				if (setOfWords.contains( word ))
					System.out.print( word + " ");
		}
		System.out.println();
		infile.close();

		System.out.print("Sorted list of words from " + args[0] +  ": " );
		for ( String word : setOfWords )
			System.out.print( word + " " );
	} // END MAIN
} // CLASS WOODCHUCKSET
