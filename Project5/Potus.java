import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader 	state2PresidentsFile	= new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();

		//populate state 2 presidents tree map arkansas is the key, value is treeSet with presidents
		//use .split() to read in each line from state to presidents first line state then tokens are the presidents born in
		// that state
		while(state2PresidentsFile.ready())
	{
			String[] tokens = state2PresidentsFile.readLine().split("\\s+");
			String state = tokens[0];
	
		// .put the state itself (first element in split array)
		//and the populated treeSet into the state2presidents map
		TreeSet<String> presidents = new TreeSet<String>();
		for(int i = 1; i<tokens.length; i++)
		{
			presidents.add (tokens[i]);
			state2Presidents.put (tokens[0], presidents);
		}
	}
		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();
		// all presidents is just a treeSet so just use Buffered Reader to populate treeSet
		while(allPresidentsFile.ready() )
		{
			String president = allPresidentsFile.readLine();
			allPresidents.add(president);
		}

		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();

		//use the allStatesFile buffered reader to populate allStates
			TreeSet<String> emptyState = new TreeSet<String>();
		while(allStatesFile.ready())
		{
			//every time read in a state, check if allStatesPresident .contains() the state
			// this means the president was born in the state
			String states = allStatesFile.readLine();

			if(!state2Presidents.containsKey(states))
			{
					emptyState.add(states);
			}

		}

		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n");

		// print out each key (state)
		// in a nested loop, print out each president that was born in that state
		for( String state: state2Presidents.keySet() )
		{
			System.out.print(state);
			TreeSet<String> presi = state2Presidents.get(state);
			for( String pres : presi )
					System.out.print (" " + pres);
				System.out.println();
		}


		TreeSet<String> noState = new TreeSet<String>();

		System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");

		for(String preso: allPresidents )
		{
			boolean isBorn = false;
				//check the presidents tree map

				for(Map.Entry<String, TreeSet<String>> entry : state2Presidents.entrySet())
				{
					String state = entry.getKey();
					TreeSet<String> presidents = entry.getValue();
					if(presidents.contains(preso))
					{
						isBorn = true;
						System.out.println(preso + " " + state);
						break;

					}

				}
				if(isBorn ==false)
				{
					noState.add(preso);
				}

		}
		System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");
		// print out noState
		for(String none: noState)
		{
			System.out.println(none + " ");
		}

		System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");

		//print out the contents of empty states
		for(String emptyS : emptyState)
		{
			System.out.println(emptyS + " ");
		}

	} // END MAIN

	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -



}	// END CLASS
