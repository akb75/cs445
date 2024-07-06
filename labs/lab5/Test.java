import java.io.*;
import java.util.*;


public class TreeSetDemo
{
	public static void main( String args[] ) throws Exception
	{
		TreeSet<String> set1 = new TreeSet<String>();
		set1.add("one");
		TreeSet<String> set2 = new TreeSet<String>();
		set2.add("yellow");
		TreeSet<String> union = new TreeSet<String>();
		for( String set: set1)
			union.add(set1);
		for(String sets: set2)
				union.add(set2);

  }
}
