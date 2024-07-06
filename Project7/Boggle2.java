import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle2
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

		 if(word.length() >=3)
 		{
 			if(dict.contains(word))
 				hits.add(word);
				//are there any words that start with this word
				String prefix = word;
				/* If dictionary tree set contains the word, add to hits treeSet. otherwise,
				check whether there are any words that start with the word you have (ie prefix)
				so make word become
				we want the word to grow, so we set prefix = word
				then if dictionary does not contain this word, we check whether the word starts with the prefix
				and we continue building the word, otherwise we break out of the statement */
/*
int searchPrefix(String s, TreeSet){
if (prefix && word) return 3;\ 1
word is a prefix so check the treeSet, keep recursing/building
		else if (word) return 2; 2
		just a word don't have to keep recursing/building
		else if (prefix) return prefix; 3
		not a word, just a prefix, keep recursing/building
		else return 0; */
		
if(prefix.equals(word))
	{
		if(dict.contains(word))
			hits.add(word);

		//dfs(r, c, word);
	}
else if(!prefix.equals(word))
{
	if(dict.contains(word))
		hits.add(word);
}
else if(!prefix.equals(word))
{
	//build on prefix
	dfs(r, c, prefix);
}
else
	return;

			}
			//the calls are factored out
			String original = board[r][c];
			board[r][c] = null;

		// THIS IS THE FORM OF EACH OF YOUR N,NE,E,SE,S,SW,W,NW BLOCKS

		// IM GIVING you THE NORTH VERSION - YOU MUST WRITE 7 MORE BELOW IT
		// DO NOT ELSE THEM OFF GIVE EVERY BLOCK AN INDEPENDENT IF TEST
		// YOU WANT TO RESUME YOUR CLOCKWISE SWEEP OF NEIGHBORS

		// NORTH IS [r-1][c]


		//north is [r-1][c]
		//examine that neighbor
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
