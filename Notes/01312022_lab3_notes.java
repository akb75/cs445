
//class ntoes about lab3

class Node<T>
{
  Node(T data, Node next)
  {
    this.data = data;
    this.next = next;
  }
}


//out in linkedList.java
//head pointer is currently a NULL
//when you create a linked list from scratch, you create this object out in memory and there is one variable member import junit.framework.TestCase;
//which starts out as NULL

//in rectangle with two squares- the node chain
//data is "foo", it is the first node of the train (square 1)
//next is null because there is nothing after (square 2)
//head pointer is no longer a null, head points to the rectangle
//another way
for(Node cur = head; cur!=null; cur = cur.next){

}

//another way
Node cur = head;
while(cur!= null)
{
  System.out.println(cur.data);
  cur = cur.next;   //cur = cur.getNext;
}


//insert at front is a member of the class it does this:
insertAtFront(T data){
  head = new Node<T>(data,head);
}

//in main
myList.insertAtFront("foo");
//in main someone inserts at the front
//list starts out empty
//foo is a reference being passed in, string object "foo"
