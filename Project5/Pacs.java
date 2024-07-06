import java.io.*;
import java.util.*;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{
		BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		TreeSet<String> allPacs= new TreeSet<String>();

		//list of pacs is already populated
		while( AllPacsFile.ready())
			allPacs.add(AllPacsFile.readLine());
		TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED

		//populate treeMap ABOVE					

						for(String pac: allPacs)
						{
							TreeSet<String> users = new TreeSet<String>();
							pacToMembers.put(pac, users);
						}

		//read in the members from member2Pacs.txt and map them to each pac they belong to
		while(memberToPacsFile.ready())
		{
			//use split method to split each line from member2pacs.txt into an array
			String[] tokens = memberToPacsFile.readLine().split("\\s+");
			//loop over elements after first element in the array because first element is pac
			String person = tokens[0];
			for(int i = 1; i<tokens.length; i++)
			{	//tokens[i] are the pacs
				//tokens[0] are the users

				String organization = tokens[i];
				//System.out.println(organization);
			 TreeSet<String> people = pacToMembers.get(tokens[i]);
			 		people.add(person);
			}

		}

		//print the tree MAP
		for(Map.Entry<String, TreeSet<String>> entry : pacToMembers.entrySet())
		{
			System.out.print(entry.getKey() + " ");
			TreeSet<String> set = entry.getValue();
			for(String names: set)
					System.out.print(names + " ");
				System.out.println();
			//nested loop to print out the members of the treeSet associated with the current key
		}


	} // END MAIN
} // CLASS
