import java.io.*;
import java.util.*;

public class TreeSetDemo1
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader allStates = new BufferedReader( new FileReader("AllStates.txt") );
		TreeSet<String> states = new TreeSet<String>();
		while ( allStates.ready() ) states.add( allStates.readLine() ); // i.e "Ohio"
		System.out.println("Stored these states in hashset allStates:");
		for ( String state : states ) System.out.print( state + " ");
		System.out.println( "\n\nNow reading through a file of strings that may or may not be states\n" );
			
		BufferedReader queries = new BufferedReader(new FileReader("queries.txt"));
		while ( queries.ready() ) 
		{ 
			String key = queries.readLine(); // i.e "Ontario" or "Florida"
			System.out.print("Searching allStates hashset for " + key );
			if ( states.contains( key ) )
				System.out.println( " FOUND");
			else
				System.out.println( " NOT found");
		}

	} // END MAIN
} // END CLASS

