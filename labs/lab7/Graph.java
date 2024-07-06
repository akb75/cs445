/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );

//		T E M P O R A R Y    C O D E    T O    V E R I F Y    P R I V A T E
// 		M E T H O D S    W E    C A N T    C A L L    F R O M   T E S T E R
//		      ........R E M O V E   A F T E R   T E S T I N G .......
/*
		for (int node = 0 ; node < G.length ; ++node )
		{
			System.out.format( "DEBUG:: in (%d)=%d  ",node,inDegree(node) );
			System.out.format( "out(%d)=%d  ",node,outDegree(node) );
			System.out.format( "deg(%d)=%d\n",node,degree(node) );
		}
*/
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;
		int row = 0;
		int col = 0;
		int weight = 0;
		// WRITE A nested LOOP THAT PUTS NO_EDGE VALUE EVERYWHERE EXCPT ON THE DIAGONAL
		// diagonal is when row == col, put no edge value
		for( row = 0; row< G.length; row++)
		{
			for( col = 0; col<G.length; col++)
			{
				if(row!=col)
					G[row][col]= NO_EDGE;

			}
		}
		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

		while ( graphFile.hasNextInt() )
		{
			// read in the row,col,weight // that eat us this line
			// call add edge
			//make an array of 3 from each line
			row = graphFile.nextInt();
			col = graphFile.nextInt();
			weight = graphFile.nextInt();
			//System.out.println("here I print row" + row + "col " + col + " weight" + weight);
			addEdge(row, col, weight);

		}

	} // END readGraphFile

	//dont modify
	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}


  private boolean hasEdge(int fromNode, int toNode)
  {
		//there is not an edge between fromNode to toNode is the cell
		//fromNode to toNode is no_edge
		//return true if there is an edge, return false if not an edge
		//there is not an edge if on the matrix we have a -1
		if(G[fromNode][toNode] == NO_EDGE)
			return false;
		if(G[fromNode][toNode] != NO_EDGE)
			return true;

		return true;
  }

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		//node is the column
		//in degree for column 3 will be number, city 3 how many arrows pointing to Phoenix
		// in degree of Phoenix is 2
			//loop through each row in columnNode and increment inDegree if we encounter a number >0 in a row
		//node is the column you look at
		int inDegree = 0;
		for(int i = 0; i<G.length; i++)
		{
			if (G[i][node] >0)
				inDegree++;
		}

		return inDegree;

	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		//each column in a ROW
		int outDegree = 0;
		for(int i = 0; i<G.length; i++)
		{
			if(G[node][i] >0)
				outDegree++;
		}
		return outDegree;
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY
	private int degree(int node)
	{
		//call indegree and outdegree and sum and return
		return inDegree(node) + outDegree(node);

	}

	// PUBLIC METHODS- do not access 2D method, just call previous private methods that were written

	public int maxOutDegree()
	{
		int maxOutDegree = outDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// LOOP OVER ALL NODES CALLING THE OUTDEG OF THAT NODE- RMEMBER THE LARGEST OUTDEG
		for(int i = 1; i<G.length; i++)
		{
			if(outDegree(i) >maxOutDegree)
				maxOutDegree = outDegree(i);
		}
		return maxOutDegree;
	}

	public int maxInDegree()
	{
		int maxInDegree = inDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME AS ABOVE BUT CALLING ALL INS AND REMEMBERING  LARGEST INDEG
		// int maxInDegree = G.length - 1;

		for(int i = 1; i< G.length; i++)
		{
			if(inDegree(i) > maxInDegree)
				maxInDegree = inDegree(i);
		}
		return maxInDegree;
	}

	public int minOutDegree()
	{
		int minOutDegree = outDegree(0); // ASSUM 1STNODE HAS SMALLES OUTDEG
		// SAME PATTERN AS ABOVE
		for(int i = 1; i< G.length; i++)
		{
			if(outDegree(i) < minOutDegree)
				minOutDegree = outDegree(i);
		}
		return minOutDegree;
	}
	public int minInDegree()
	{
		int minInDegree = inDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME PATTERN
		for(int i = 1; i<G.length; i++)
		{
			if(inDegree(i)< minInDegree)
				minInDegree = inDegree(i);
		}
		return minInDegree;
	}

	public int maxDegree()
	{
		int maxDegree = degree(0);
		// SAME PATTERN
		//loop through each node and call the degree method
		//return the highest total degree
		for(int i = 1; i<G.length; i++)
		{
			if(degree(i)> maxDegree)
				maxDegree = degree(i);
		}
		return maxDegree;
	}

	public int minDegree()
	{
		int minDegree = degree(0);
		// SAME PATTERN
		for(int i = 1; i<G.length; i++)
		{
			if(degree(i) < minDegree)
				minDegree = degree(i);
		}
		return minDegree;
	}

	public void removeEdge(int fromNode, int toNode)
	{
		/* if caller passes in a row col pair that
		   out of bound or has no edge there, you must
		   throw and catch an exception. See my output.


		   if the edge is there then remove it by writing
		   in a NO_EDGE value at that coordinate in the grid
		*/

		try
		{
			if(fromNode <0 || toNode <0 || fromNode > G.length || toNode > G.length || G[fromNode] == null)
			{
				throw new ArrayIndexOutOfBoundsException();
			}
			//fromNode and toNode are within bounds and that there is an edge between them
			//if not throw an exception

			//reassign [fromNode][toNode] to NO_EDGE
			G[fromNode][toNode] = NO_EDGE;

		}catch (Exception e)
		{
			//print the exception
							System.out.println("java.lang.Exception: Non Existent Edge Exception: removeEdge(" + fromNode + "," + toNode + ")");
		}


	}

	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING

} //EOF
