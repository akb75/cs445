import java.io.*;
import java.util.*;

public class GraphLL
{
	private final int NO_EDGE = -1; // all real edges are positive
	private  Edge[] G;              // every G[i] is the head of a linked list, i.e ref to an Edge

	private int numEdges;
	public GraphLL( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );
		int numNodes = graphFile.nextInt();
		G = new Edge[numNodes];
		numEdges=0;
		int src = 0;
		int dest = 0;
		int weight = 0;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// DO an insertAtFront using g[SRC] as the head

		while ( graphFile.hasNextInt() )
		{	// read in the src, dest, weight
			// call addEdge
			src = graphFile.nextInt();
			dest = graphFile.nextInt();
			weight = graphFile.nextInt();
			addEdge(src, dest, weight);

		}
	} // END readGraphFile

	// GO TO G[src] AND DO INSERTATFRONT ON THAT 'head' POINTER I.E. G[src]
	private void addEdge( int src, int dest, int weight )
	{
		G[src] = new Edge(dest, weight, G[src]);
		numEdges++;

	}

	private boolean hasEdge(int src, int dest)
	{

	//	how to walk linked list at particular location G[src] -->
	// thinking of making g[src] the head pointer	.dest, .weight
	//G[src] acts as the head
		Edge curr = G[src];

		//base case 1
	if(G[src] == null)
		return false;

	//base case 2: check if current edge has the destination we are looking for
	if(G[src].dest == dest )
		return true;

	//traverse the list
		while(curr!=null)
		{
			//if the current node's destination is equal to our destination
			if(curr.dest == dest)
				return true;

				//otherwise traverse to next curr
			curr= curr.next;
		}
		//otherwise return false
		return false;

	}





	private int inDegree(int dest) // # of roads(edges) entering this city (node)
	{	// THE HARDER ONE
		int inDeg = 0;
		//all of the routes into Pittsburgh
		//all of the edges leading into Pittsburgh

			// WALK ALL THE LISTS COUNTING THE NODE HAVING THIS DEST
		//check all of the edges except edges into Pittsburgh

		//loop through every source city in the array

			//skip when src == destination
			//otherwise call hasEdge(src, dest) and increment inDeg if there is an edge
			for(int i = 0; i<G.length; i++)
			{
				if(i == dest)
					continue;

					if(hasEdge(i, dest))
						inDeg++;

			}
			return inDeg;
		}


	private int outDegree(int src) // # of roads(edges) leaving this city (src node #)
	{	// THE EASIER ONE
		int outDeg=0;
		// JUST RETURN THE LENGTH OF THIS LIST AT G[src]
		Edge curr = G[src];
		//at G[src] increment curr to next one and count
	/*	for(int i = 0; i<G.length; i++)
		{

			curr = curr.next;
			outDeg++;
		}*/
		while(curr!=null)
		{
				outDeg++;
			curr = curr.next;

		}

		return outDeg;
	}

	private int degree(int node) // indegree + outdegree of this node #
	{
		return (inDegree(node) + outDegree(node));
	}

	// PUBLIC METHODS SINCE IT IS NOT
	// DEPENDENT ON UNDERLYING DATA STRUCTURE AND RELIES ONLY ON CALLING THE 3 DEGREE FUNCTIONS

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

	public void removeEdge(int src, int dest)
	{	// ITS AN OLD FASHIONED FIND & REMOVE NODE ON A 1 WAY LINKED LIST
		// IF THE LIST AT G[src] IS NULL -OR-  SRC OR DEST OUT OF BOUNDS
		// 		THROW AND CATCH AN EXCEPTION - SEE OUTPUT

		// USE A BASE CASE TEST FOR 1ST NODE BEGIN THE ONE

		// WALK A CURR UP TO THE PRED OF THE DEADNODE
		// REMOVE THE NODE (IF ITS THERE)

		// ITS NOT THERE THROW AND CATCH AN EXCEPTION (SEE OUTPUT)

		//if src >G.length, dest>G.length -> out of bounds
		// if src<0, dest< 0 --> out of bounds

		try{
			//check if both src and dest are in bounds AND that G[src] == null

			if(src<0 || dest <0 || src > G.length || dest > G.length || G[src] == null)
			{
				throw new ArrayIndexOutOfBoundsException();
			}

						Edge curr = G[src];


				if(hasEdge(src, dest))
					{
							if(curr.dest == dest)
							{

								G[src] = G[src].next;
								return;
							}

							while(curr.next!=null)
							{
								//walk curr up to the predeccessor of the deadNode
								//remove the Node if its there

								if(curr.next.dest == dest)
								{

									curr.next = curr.next.next;
									return;
								}
								curr = curr.next;
							}

				
							}

								else
							System.out.println("java.lang.Exception: Non Existent Edge Exception: removeEdge(" + src + "," + dest + ")" );

						}catch (Exception e)
						{
							System.out.println("java.lang.Exception: Non Existent Edge Exception: removeEdge(" + src + "," + dest + ")");
						}

			// USE A BASE CASE TEST FOR 1ST NODE BEGIN THE ONE being removed
			//if the first node inside the list is being removed, reassign the value
			//of G[src] and return

	} // E N D  R E M O V E D G E

	// TOSTRING
	public String toString()
	{
		String the2String = "";
		//Edge curr = G[src];
		for(int i = 0; i<G.length; i++)
		{
				Edge curr = G[i];
			//the2String += G[i] + ": ->" + G[i].dest + " -> " + G[i].next;    G[i].next.dest, G[i].next.weight
			//destination node, weight, next
			the2String+= i + ": ";
			while(curr!=null)
			{
				if(curr.next!=null)
				{
						the2String+= "-> [" + curr.dest + "," + curr.weight + "] ";
				}

				else
						the2String+= "-> [" + curr.dest + "," + curr.weight + "]";

				curr = curr.next;
			}
				the2String += '\n';
		}

		//see recitation video

		return the2String;
	} // END TOSTRING
} // E N D    G R A P H L L    C L A S S

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

class Edge
{
	// DEFINE dest,weight,nextInt
	// DEFINE FULL CONSTRUCTOR
	// int dest, weight
	// Edge next
	// a Constructor that takes dest,weight, next
	int dest, weight;
	Edge next;

	Edge(int dest, int weight, Edge next)
	{
		this.dest = dest;
		this.weight = weight;
		this.next = next;

	}
}
