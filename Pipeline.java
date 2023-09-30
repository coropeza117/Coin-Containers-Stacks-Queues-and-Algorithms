package edu.uwm.cs351.money;

import java.util.NoSuchElementException;

/**
 * @author * CHRISTIAN OROPEZA CS351 ...RECIEVED HELP FROM BOOK, LIBRARY TUTORS, ONLINE CS TUTOR, AND ADVICE FROM FRIENDS ON HOW TO APPROACH FIXING PERSISTENT BUGS.
 * COLLABORATORS: JOSHUA KNIGHT, JULLIAN MURANO, BIJAY PANTA, JIAHUI YANG (WHILE IN TUTORING LIBRARY SECTION) BUT NO CODE WAS SHARED.
 * Online Sources: https://www.geeksforgeeks.org/queue-linked-list-implementation/
 */

/**
 * A FIFO container of coins.
 */
public class Pipeline extends DefaultContainer 
{
	protected Coin tail; // Add only this field
	
	// TODO: Add what is necessary to get correct semantics
	@Override	//	required
	protected boolean wellFormed() 
	{
		if(!super.wellFormed())	return false;
		
		//	4. (specific to Pipeline) The "tail" must be the last Coin
		//		(if any) in the list starting at the "head".
		if((head == null && tail != null) || ( tail == null && head != null))	return report("Head is null but Tail is NOT!");
		
		if(head != null)
		{	
			for(Coin p = head; p != null; p = p.next )
			{
				if( p == tail.next)	return report("Tail is not null BUT Tail.Next is also NOT null");
				
				if(p != tail && p.next == null)	return report("Tail is not null BUT No node pointing to tail");
			}
		}
		return true;
	}
	
	/**
	 * if inherited canAdd returns false we can't add yet
	 * we cannot add this coin if false otherwise true we can add!
	 */
	@Override	//	required
	public boolean canAdd(Coin c) 
	{
		assert wellFormed() : "invariant failed at start of canAdd";
		
		if(!super.canAdd(c))	return false;
		
		assert wellFormed() : "invariant failed at end of canAdd";

		return true;
	}
	
	/**
	 * take ownership of coin so we can add it to stack
	 * if coin at tail then we add at the tail and tail.next is null
	 * otherwise we add coin after at the head and tail but tail.next is still null
	 */
	@Override	//	required
	public void add(Coin c) 
	{
		assert wellFormed() : "invariant failed at start of add";
		
		this.takeOwnership(c);
		
		if(tail != null)
		{
			tail.next = c;
			tail = c;
			tail.next = null;
		}
		else
		{
			head = tail = c;
			tail.next = null;
		}
	
		assert wellFormed() : "invariant failed at end of add";
	}
	
	/**
	 * remove coin at the head and head now points to coin after old head coin
	 * relinquish ownership since the coin is no longer in our stack
	 * if head is null make sure tail is as well
	 * return the new head
	 */
	@Override	//	required
	public Coin remove() throws NoSuchElementException 
	{
		assert wellFormed() : "invariant failed at start of remove";
		
		if(isEmpty())	throw new NoSuchElementException("can't remove head is null");
		
		Coin temp = head;
		head = head.next;
		
		if(head == null)	tail = null;
		
		this.relinquish(temp);
		
		assert wellFormed() : "invariant failed at end of remove";
		
		return temp;
	}
}
