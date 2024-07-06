import java.io.*;
import java.util.*;


public class TreeSetDemo
{
	public static void main( String args[] ) throws Exception
	{
		TreeSet<String> set1 = new TreeSet<String>();
		set1.add("charlie");
		set1.add("bravo");
		set1.add("delta");
			set1.add("alpha");
				set1.add("golf");
				set1.add("india");
				set1.add("hotel");
				set1.add("echo");
		TreeSet<String> set2 = new TreeSet<String>();
	set2.add("foxtrot");
	set2.add("baker");
		set2.add("xray");
set2.add("zebra");
set2.add("charlie");
set2.add("victor");
		//difference: set 1 minus set 2
		TreeSet<String> union = new TreeSet<String>();
		for(String addAll: set1)
			union.addAll(set1);
		for(String addAll: set2)
			union.addAll(set2);
		System.out.println("this is union" + union);

		TreeSet<String> intersection = new TreeSet<String>();
		//check if set 2 has the element from set 1
		// if it does, then add it to intersection

		//traverse the set

		// if set 2 has the element from set 1 in it, then add to intersection


		intersection.addAll(set1);
		intersection.retainAll(set2);
		//iterate through set 1
	/*	if(toCount.hasNext())
		{
				if(set1.contains(toCount2.next()));
				for(String addIt: set1)
						intersection.add(set1);
		}*/


		System.out.println("this is intersection" + intersection);

		//difference is set 1 minus the elements from set 2

		//add all elements of set 1 into the set
		// then add all elements that are not duplicates to set 2
		TreeSet<String> difference = new TreeSet<String>();

		difference.addAll(set1);

			difference.addAll(set2);
			//how to get the data of set 2 into
			//String key =
			difference.removeAll(set2);
			System.out.println("this is differnece" + difference);

		//xor is set 1 and set 2 but not nintersection
		TreeSet<String> xor = new TreeSet<String>();
		xor.addAll(union);
		xor.removeAll(intersection);
		System.out.println("this is xor" + xor);

  }
}
