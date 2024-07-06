public class Node<T>
{
  private T data;
  private Node<T> next;

  public Node()
  {
    this( null, null );
  }

  public Node(T data)
  {
    this( data, null );
  }

//this constructor is what we call upon each time we want to "hook another car onto the chain" hook another node onto the chain
  public Node(T data, Node<T> next)
  {
    setData( data );
    setNext( next );
  }

  public T getData()
  {
    return data;
  }

  public Node<T> getNext()
  {
    return next;
  }

  public void setData(T data)
  {
     this.data = data;
  }

  public void setNext(Node<T> next)
  {
    this.next = next;
  }
  public String toString()
  {
	  return ""+getData();
  }

} //EOF
