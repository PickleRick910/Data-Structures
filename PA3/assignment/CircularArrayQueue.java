/**
 * 
 */
package csc311;

import net.datastructures.Queue;


public class CircularArrayQueue<E> implements Queue<E> 
{

	private int size = 0;  //number of elements in array
	private E[] array;     //array for storing elements in
	private int front = 0; //index of the front element of the queue


	//code for the constructor found in book on page 243
	public CircularArrayQueue(int queueSize) //constructor for queue
	{
		array = (E[]) new Object[queueSize];
	}

	@Override
	public int size() //returns size of queue
	{
		return size;
	}

	@Override
	public boolean isEmpty() //returns if queue is empty or not
	{
		if (size == 0)       //if size is 0, returns true
		{
			return true;
		}
		return false;        //else false
	}


	//code for enqueue found in book on page 243
	@Override
	public void enqueue(E e) throws IllegalStateException //adds element to queue
	{
		if (size == array.length)                         //if the queue is full, throws exception
		{
			throw new IllegalStateException();
		}

		int i = (front + size) % array.length;            //modular math for to where to place element in queue, found in book on page 243
		array[i] = e;                                     //places e at index i
		size++;                                           //increments size

	}

	@Override
	public E first()          //returns the first element in queue
	{
		if (size == 0)        //if size of queue is 0, returns null since there is nothing in the queue
		{
			return null;
		}
		return array[front];  //returns the element located at index front
	}


	//code for dequeue found in book on page 243
	@Override
	public E dequeue()                       //removes element from queue
	{
		if (size == 0)                       //if nothing to remove, returns null
		{
			return null;
		}
		E temp = array[front];               //saves element being removed to temp
		array[front] = null;                 //sets element being removed to null
		front = (front + 1) % array.length;  //does modular math to determine where the new front of the queue is, found in page 243 of book
		size--;                              //decrements size
		return temp;                         //returns the value saved in temp
	}

}
