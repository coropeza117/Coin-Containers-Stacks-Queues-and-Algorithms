package edu.uwm.cs351.money;

import java.util.NoSuchElementException;


/**
 * @author * CHRISTIAN OROPEZA CS351 ...RECIEVED HELP FROM BOOK, LIBRARY TUTORS, ONLINE CS TUTOR, AND ADVICE FROM FRIENDS ON HOW TO APPROACH FIXING PERSISTENT BUGS.
 * COLLABORATORS: JOSHUA KNIGHT, JULLIAN MURANO, BIJAY PANTA, JIAHUI YANG (WHILE IN TUTORING LIBRARY SECTION) BUT NO CODE WAS SHARED.
 * Online Sources: //	Source for add method: https://www.javatpoint.com/java-program-to-insert-a-new-node-at-the-beginning-of-the-singly-linked-list
//	Source for remove method: Lecture on stack pop
 */

/**
 * A LIFO Container of Coins
 */
public class DefaultContainer implements Container 
{
	protected Coin head; // the only field.  Do not declare any other fields
	
	/**
	 * Report an invariant error.
	 * @param message message to print
	 */
	protected boolean report(String message) 
	{
		System.out.println("Invariant error: " + message);
		return false;
	}

	/**
	 * Return true if the data structure invariant is established.
	 * @return the value of the predicate for the invariant
	 */
	protected boolean wellFormed() 
	{
		// TODO: 
		// 1. No cycles allowed (use Tortoise and Hare)
		if (head != null) 
		{
			// This check uses the "tortoise and hare" algorithm attributed to Floyd.
			Coin fast = head.next;
			
			for (Coin p = head; fast != null && fast.next != null; p = p.next) 
			{
				if (p == fast)	return report("list is cyclic!");
				
				fast = fast.next.next;
			}
		}
		
		// 2. Every coin in this container must be owned by this container
		for (Coin p = head; p != null; p = p.next) 
		{
			if (p.owner != this)	return report("Coin is not owned by this container!");
		}
		
		
		return true;
	}
	
	/**
	 * Start adding a coin.  We first check that we can add it
	 * and then take over ownership.
	 * @param c coin to take possession of, must be one we can add
	 */
	protected void takeOwnership(Coin c) 
	{
		if (!canAdd(c)) throw new IllegalArgumentException("cannot add " + c);
		c.owner = this;
	}

	/**
	 * Get ready to return a coin.  We remove ownership.
	 * @param c coin to relinquish
	 */
	protected void relinquish(Coin c) 
	{
		c.owner = null;
	}

	/**
	 * return true if head is equal to null indicating no coins are present
	 */
	@Override	//	implementation
	public boolean isEmpty() 
	{
		assert wellFormed() : "invariant failed at start of isEmpty";
		
		return head == null;
	}

	/**
	 * instantiating a variable to count the counts while iterating 
	 * through the stack using a for loop and incrementing count before returning
	 */
	@Override	//	implementation
	public int size() 
	{
		assert wellFormed() : "invariant failed at start of size";

		int count = 0;
		
		for(Coin p = head; p != null; p = p.next)
		{
				++count;
		}		
		
		assert wellFormed() : "invariant failed at end of size";
		
		return count;
	}

	/**
	 * if coin has a null value or if is already owned return false
	 * we cannot add this coin if false otherwise true we can add!
	 */
	@Override	//	implementation
	public boolean canAdd(Coin c) 
	{
		assert wellFormed() : "invariant failed at start of canAdd";
		
		if(c == null || c.isOwned() == true )	return false;
		
		assert wellFormed() : "invariant failed at end of canAdd";

		return true;
	}
	
	/**
	 * take ownership of coin so we can add it to stack
	 * if no coin at head then we add at the head
	 * otherwise we add coin after the coin at the head
	 */
	@Override	//	implementation
	public void add(Coin c) 
	{
		assert wellFormed() : "invariant failed at start of add";
		
		this.takeOwnership(c);
		
		if(head == null)
		{
			head = c;
			head.next = null;
		}
		else
		{
			Coin temp = head;
			head = c;		
			head.next = temp;
		}
		
		assert wellFormed() : "invariant failed at end of add";
	}

	/**
	 * remove coin at the head and head now points to coin after old head coin
	 * relinquish ownership since the coin is no longer in our stack
	 * return the new head
	 */
	@Override	//	implementation
	public Coin remove() throws NoSuchElementException 
	{
		assert wellFormed() : "invariant failed at start of remove";
		
		if(isEmpty())	throw new NoSuchElementException("can't remove head is null");
		
		Coin temp = head;
		head = head.next;
		
		this.relinquish(temp);
		
		assert wellFormed() : "invariant failed at end of remove";
		
		return temp;
	}
	
	@Override	//	decorate
	public String toString()
	{
		Coin p;
		for( p = head; p != null; p = p.next)
		{
			System.out.println(p);	
		}
		
		return super.toString();
	}

}
