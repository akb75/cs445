import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class BoggleStarter
{
	static String[][] board;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;

	// define your dictionary set and and your hits set UP HERE as TreeSets
	static TreeSet<String> dict = new TreeSet<String>();
	static TreeSet<String> hits = new TreeSet<String>();

	static long numWordsFormed = 0;

	public static void main( String args[] ) throws Exception
	{
		startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );			//board is args[1]

		// INITIALIZE DICT AND HITS HERE
		//TreeSet<String> dictionary = new TreeSet<String>();
		//TreeSet<String> wordHit = new TreeSet<String>();
		//dict is from dictionary file
		BufferedReader dictionaryFile = new BufferedReader( new FileReader(args[0]) );

		//load dictionary treeSet
		while(dictionaryFile.ready())
		{
			String dictionaryWords = dictionaryFile.readLine();
			dict.add(dictionaryWords);
		}

		// so if dictionary contains hits, then the word has been
		// hit

		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  ); // FOR EACH [R][C] THE WORD STARTS EMPTY

		// EVENTUALLY YOU ADD HERE
		// PRINT OUT YOUR SORTED HITS CONTAINER ONE WORD PER LINE

		for(String hitWord: hits)
			System.out.println(hitWord + " ");

		endTime =  System.currentTimeMillis(); // for timing
		// System.out.println("GENERATION COMPLETED: runtime=" + (endTime-startTime)/MILLISEC_PER_SEC );

	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{

		word += board[r][c];
		//if the current word.length >= 3
			// check if the dictionary contains the word
			//if so, we have a hit

		/*	if dict contains word add to hits

		 if NOT found then ask dict
		 are there any words in you that START WITH this word

		 if dict says NO what do you do ?
		 return; */

		 int decision = 0;

		 if(word.length() >=3)
 		{
 			if(dict.contains(word))
 				hits.add(word);

				//switch statement
				if(!dict.contains(word))
				{
					searchPrefix(prefix, word, dict);
					switch(decision)
					{
						case 1:
							//word is a prefix so check if treeSet has word
							//keep recusring
							if(dict.contains(word))
								hits.add(word);

								dfs(r,c,word);
								break;
							case 2:
								//just a word in the dictionary set no need to recurse
								if(dict.contains(word))
								hits.add(word);
								break;
							case 3:
								//keep recursing and building on prefix
								dfs(r,c,word);
								break;
							case 4:
								return;
								break;


					}
				}
 		}
			//the calls are factored out
			String original = board[r][c];
			board[r][c] = null;


		if ( r-1 >= 0 && board[r-1][c] != null )   // THE r +/- and c+/- WILL CHANGE FOR EVEY BLOCK BELOW
		{	String unMarked = board[r][c]; // SAVE TO RESTORE AFTER RET FROM RECURSION
			board[r][c] = null; // // null IS THE MARKER OF A VALUE AS IN USE ALREADY
			dfs( r-1, c, word ); // THE r-1,c WILL CHANGE WITH EVERY OTHER BLOCK BELOW
			board[r][c] = unMarked; // BACK. UNMARK IT
		}

		// NE IS [r-1][c+1]  YOU WILL NEED TO TEST BOTH r-1 AND c+1 FOR OUT OF BOUNDS
		if( r-1>= 0 && c+1>=0 && board[r-1][c+1] != null)
		{
			String unMarked = board[r-1][c+1];
			board[r-1][c+1] = null;
			dfs(r-1, c+1, word);
			board[r-1][c+1] = unMarked;

		}
		// E IS [r][c+1]
		if(c+1 >=0 && board[r][c+1]!= null)
		{
			String unMarked = board[r][c+1];
			board[r][c+1] = null;
			dfs(r, c+1, word);
			board[r][c+1] = unMarked;
		}

		// SE IS row + 1 column + 1
		if(r+1>=0 && c+1>=0 && board[r+1][c+1] != null)
		{
			String unMarked = board[r+1][c+1];
			board[r+1][c+1] = null;
			dfs(r+1, c+1, word);
			board[r+1][c+1] = unMarked;
		}

		// S IS row + 1, same column
		if(r+1>=0 && board[r+1][c]!= null )
		{
			String unMarked = board[r+1][c];
			board[r+1][c] = null;
			dfs(r+1, c, word);
			board[r+1][c] = unMarked;
		}

		// SW IS ... r+1, c-1
		if(r+1 >= 0 && c-1 >=0 && board[r+1][c-1] != null)
		{
			String unMarked = board[r+1][c-1];
			board[r+1][c-1] = null;
			dfs(r+1, c-1, word);
			board[r+1][c-1] = unMarked;
		}

		// W IS c-1
		if(c-1 >= 0 && board[r][c-1] != null)
		{
			String unMarked = board[r][c-1];
			board[r][c-1] = null;
			dfs(r, c-1, word);
			board[r][c-1] = unMarked;
		}

		// NW IS r-1, c-1
		if(r-1 >= 0 && c-1 >= 0 && board[r-1][c-1] != null)
		{
			String unMarked = board[r-1][c-1];
			board[r-1][c-1] = null;
			dfs(r-1, c-1, word);
			board[r-1][c-1] = unMarked;
		}


		board[r][c] = original;

	} // END DFS ----------------------------------------------------------------------------

static int searchPrefix (String p, String w, TreeSet<String> dictio)
{
	
	 int decision = 0;
	 if(p.equals(w))
	 	{
			decision = 1;
			return decision;
		}
	//	if(!prefix.equals(word))
	else if(w)
		{
			decision = 2;
			return decision;
		}
		else if(p)
		{
			decision = 3;
			return decision;
		}
		else
		{
			decision = 0;
			return decision;
		}
}
	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD

} // END BOGGLE CLASS
