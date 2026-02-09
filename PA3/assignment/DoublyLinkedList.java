package csc311;
import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {


	private static class Node<E> implements Position<E> //implementation of node found in book page 277 
	{
		private E element;
		private Node<E> prev;
		private Node<E> next;

		public Node(E e, Node<E> prev, Node<E> next)    //constructor for node
		{
			element = e;
			this.prev = prev;
			this.next = next;
		}

		public Node<E> getPrev()
		{
			return prev;
		}

		public Node<E> getNext()
		{
			return next;
		}


		@Override
		public E getElement() throws IllegalStateException 
		{
			return element;
		}

		public void setElement(E e)
		{
			element = e;
		}

		public void setPrev(Node<E> p)
		{
			prev = p;
		}

		public void setNext(Node<E> n)
		{
			next = n;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size = 0;


	public DoublyLinkedList() //constructor for doublylinkedlist, creates head and tail pointer
	{
		head = new Node<E>(null, null, null);
		tail = new Node<E>(null, head, null); //sets tail pointer prev to head
		head.setNext(tail);                   //sets head next to tail
	}

	@Override
	public int size() //returns size of linked list
	{
		return size;
	}

	@Override
	public boolean isEmpty() //determines if linked list is empty
	{
		if (size == 0)       //if size is 0, list is empty
		{
			return true;
		}
		return false;
	}

	@Override
	public Position<E> first()      //returns first position of list
	{
		if (head.getNext() == tail) //if next element of head is tail, then returns null
		{
			return null;
		}
		return head.getNext();      //returns head next
	}

	@Override
	public Position<E> last()       //returns last position of list
	{
		if (tail.getPrev() == head) //if tail prev is head returns null since there is no node to return
		{
			return null;
		}
		return tail.getPrev();      //returns tail prev
	}


	//implementation found in book on page 278
	private Position<E> position(Node<E> node) 
	{
		if (node == head || node == tail) //checks if node is neither head or tail
		{
			return null;
		}

		return node;                      //returns node as a Position<E>

	}


	//implementation found in book on page 278
	private Node<E> validate(Position<E> p) throws IllegalArgumentException //checks if p is valid
	{
		if (!(p instanceof Node))                  //checks if p is an instance of Node
		{
			throw new IllegalArgumentException();
		}
		Node<E> node = (Node<E>) p;

		if (node.getNext() == null)                //checks if node is within list
		{
			throw new IllegalArgumentException();
		}

		return node;                               //returns node
	}


	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException //gets Position before p
	{
		Node<E> node = validate(p);          //checks if p is valid
		return position(node.getPrev());     //returns prev as a position
	}


	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException  //gets Position after p
	{
		Node<E> node = validate(p);          //checks if p is valid
		return position(node.getNext());     //returns next as position
	}


	@Override
	public Position<E> addFirst(E e) //adds element to front of list
	{
		Node<E> temp = new Node<E>(e, head, head.getNext()); //creates new node that contains element e, prev is head, and next is head's next
		head.setNext(temp);                                  //sets head next to the new node
		temp.getNext().setPrev(temp);                        //sets the node that was in front previously prev to the new node
		size++;                                              //inc size
		return temp;                                         //return position of new node
	}


	@Override
	public Position<E> addLast(E e) {
		Node<E> temp = new Node<E>(e, tail.getPrev(), tail); //creates new node at the end of the list, node's prev is tail prev and next is tail
		tail.setPrev(temp);                                  //set tail's prev to new node
		temp.getPrev().setNext(temp);                        //set the old last node's next to the new node
		size++;                                              //inc size
		return temp;                                         //return position of new node
	}


	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException //adds node before given position with element e
	{
		Node<E> node = validate(p);                           //validates node
		Node<E> temp = new Node<E>(e, node.getPrev(), node);  //creates new node where prev is the validated node's prev and next is the validated node
		temp.getPrev().setNext(temp);                         //sets the node that used to be before's next to the new temp node
		temp.getNext().setPrev(temp);                         //sets the validated node's prev to the temp node
		size++;
		return temp;                                          //returns the temp node
	}


	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException //adds node after give position
	{
		Node<E> node = validate(p);
		Node<E> temp = new Node<E>(e, node, node.getNext());  //creates new temp node with prev = node and next = node's next
		temp.getPrev().setNext(temp);                         //sets the node before temp's next to the temp node
		temp.getNext().setPrev(temp);                         //sets the node that was validated's prev to the temp node
		size++;
		return temp;                                          //returns the temp node
	}


	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException //replaces the node in the given position
	{
		Node<E> node = validate(p);
		Node<E> temp = new Node<E>(e, node.getPrev(), node.getNext()); //creates new node with same prev and next as the validated node
		temp.getPrev().setNext(temp);                                  //sets the node before the temp node to point to the temp node
		temp.getNext().setPrev(temp);                                  //sets the node after the temp node to point to the temp node
		return node.getElement();                                      //returns the element held by the node that has been replaced
	}


	@Override
	public E remove(Position<E> p) throws IllegalArgumentException //removes node from given position
	{
		Node<E> node = validate(p);
		node.getPrev().setNext(node.getNext());                    //sets the node before to point to the node after the given position
		node.getNext().setPrev(node.getPrev());                    //sets the node after to point to the node before the given position
		size--;
		return node.getElement();                                  //returns element of removed node
	}


	//implementation of iterator found in book on page 287
	private class PositionIterator implements Iterator<Position<E>>
	{
		private Position<E> cursor = first(); //cursor to get position of next position
		private Position<E> recent = null;    //returns the value of the most recently visited node

		@Override
		public boolean hasNext() //checks if there is another node
		{
			if (cursor != null)  //if cursor is not null, then there is another node
			{
				return true;
			}
			return false;
		}

		@Override
		public Position<E> next()    //gets next position
		{
			if (!hasNext())          //returns null if there is nothing to get
			{
				return null;
			}
			recent = cursor;         //changes recent to cursor, since cursor is now the most recently visited node 
			cursor = after(cursor);  //changes cursor to the node after the original cursor
			return recent;           //returns recent
		}

	}

	//implementation of iterator found in book on page 287
	private class ElementIterator implements Iterator<E>
	{
		Iterator<Position<E>> posIterator = new PositionIterator();
		@Override
		public boolean hasNext()
		{
			return posIterator.hasNext(); //calls posIterator.hasNext() to determine if there is another node that exists
		}

		@Override
		public E next() 
		{
			return posIterator.next().getElement(); //gets element of the next node
		}

	}

	//implementation of iterator found in book on page 287
	private class PositionIterable implements Iterable<Position<E>>
	{
		public Iterator<Position<E>> iterator()
		{
			return new PositionIterator();
		}
	}


	//implementation of iterator found in book on page 287
	@Override
	public Iterator<E> iterator() 
	{
		return new ElementIterator();
	}


	//implementation of iterator found in book on page 287
	@Override
	public Iterable<Position<E>> positions() 
	{
		return new PositionIterable();
	}

	public E removeFirst() throws IllegalArgumentException //removes first node in list
	{
		if (size == 0)                                     //can't remove something if there is nothing there
		{ 
			throw new IllegalArgumentException();          
		}
		E temp = head.getNext().getElement();              //stores element of first node into temp
		head.setNext(head.getNext().getNext());            //sets head to point at the node after the first node
		size--;
		return temp;                                       //returns temp, which has the value of the data stored in the removed node
	}

	public E removeLast() throws IllegalArgumentException  //removes last node
	{
		if (size == 0)
		{
			throw new IllegalArgumentException();
		}
		E temp = tail.getPrev().getElement();              //stores element into temp
		tail.setPrev(tail.getPrev().getPrev());            //sets tail prev to point to the node before the one being removed
		size--;
		return temp;                                       //returns temp
	}

}
