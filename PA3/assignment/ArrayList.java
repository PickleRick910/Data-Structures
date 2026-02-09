package csc311;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {


	public static final int CAPACITY = 16; //line of code from book implementation on page 260
	private E[] array;
	private int size = 0;



	public ArrayList() 
	{
		array = (E[]) new Object[CAPACITY]; //line of code from book implementation on page 260
	}

	@Override
	public int size() //returns size of array
	{

		return size;

	}

	@Override
	public boolean isEmpty() //returns true if array size = 0
	{
		if (size == 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {


		if (i >= size || i < 0)  //throws exception when i is not a valid input
		{
			throw new IndexOutOfBoundsException();
		}

		return array[i];         //returns value stored at array[i]
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException { //replaces value at index i with e

		if (i >= size || i < 0)
		{
			throw new IndexOutOfBoundsException();
		}


		E temp = array[i];                                     //saves value at index i

		array[i] = e;                                          //replaces value at index i with e

		return temp;                                           //returns value that was replaced at index i
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {


		if (i < 0 || i > size + 1)                         //found in book from page 260 / 261
		{
			throw new IndexOutOfBoundsException();
		}


		if ((size + 1) == array.length)                    //if max size is reached
		{
			E[] temp = (E[]) new Object[array.length * 2]; //creates a new array double the size of the last
			for (int j = 0; j < array.length; j++)         //for loop copies elements from old array to new one
			{
				temp[j] = array[j];
			}
			array = temp;                                  //sets old array to new array so it will now have space
		}                                                  //to add new elements


		for (int j = size; j > i; j--)                     //shifts values to the right to insert value in the correct
		{                                                  //place
			array[j] = array[j - 1];
		}

		array[i] = e;                                      //sets the value at index i to e

		size++;                                            //increments size

	}

	@Override
	public E remove(int i) throws IndexOutOfBoundsException {

		if (size == 0 || i >= size || i < 0)
		{
			throw new IndexOutOfBoundsException();
		}

		//code in this section found in book on page 261
		E temp = array[i];

		for (int j = i; j < size - 1; j++)
		{
			array[j] = array[j + 1];
		}



		size--;

		array[size] = null;

		return temp;
	}

	//format/implementation taken from book page 285
	private class ListIterator implements Iterator<E>
	{
		private int i = 0;

		@Override
		public boolean hasNext() {   //checks if there is another element that exists
			if (i < size)
			{
				return true;
			}

			return false;
		}

		@Override
		public E next()             //gets next element
		{
			if (!hasNext())         //checks if next element exists
			{
				return null;
			}
			return array[i++];      //returns the next element
		}


	}


	//format/implementation taken from book page 285
	@Override
	public Iterator<E> iterator() 
	{	
		return new ListIterator();
	}


	public void addFirst(E e)  {    //adds to the front by calling add(0, e), 0 is first in an array
		add(0, e);
	}



	public void addLast(E e)  {                             //adds to end of array
		if ((size + 1) == array.length)                     //increases size of array if it has no more space
		{
			E[] temp = (E[]) new Object[array.length * 2];
			for (int j = 0; j < array.length; j++)
			{
				temp[j] = array[j];
			}
			array = temp;
		}

		array[size] = e;                                    //sets array[size] to value, size would be the end of the array

		size++;

	}



	public E removeFirst() throws IndexOutOfBoundsException { //removes the first value in the array by calling remove(0)
		if (size == 0)
		{
			throw new IndexOutOfBoundsException();
		}
		return remove(0);
	}



	public E removeLast() throws IndexOutOfBoundsException {  //removes last element of array 

		if (size == 0)
		{
			throw new IndexOutOfBoundsException();
		}

		E temp = array[size - 1];                            //stores last value in array

		array[size - 1] = null;                              //sets last value to null

		return temp;                                         //returns saved value that was removed
	}

	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() {
		return array.length;
	}

}
