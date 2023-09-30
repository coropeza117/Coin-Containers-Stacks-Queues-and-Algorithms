package edu.uwm.cs351.money;

/**
 * @author * CHRISTIAN OROPEZA CS351 ...RECIEVED HELP FROM BOOK, LIBRARY TUTORS, ONLINE CS TUTOR, AND ADVICE FROM FRIENDS ON HOW TO APPROACH FIXING PERSISTENT BUGS.
 * COLLABORATORS: JOSHUA KNIGHT, JULLIAN MURANO, BIJAY PANTA, JIAHUI YANG (WHILE IN TUTORING LIBRARY SECTION) BUT NO CODE WAS SHARED.
 */
/**
 * A LIFO container of coins
 * that requires a coin cannot be placed on smaller ones
 */
public class Stack extends DefaultContainer 
{
	// TODO
	// Add the least amount that gives the required semantics.
	/**
	 * Return true if the data structure invariant is established.
	 * @return the value of the predicate for the invariant
	 */
	@Override	//	required
	protected boolean wellFormed() 
	{
		if(!super.wellFormed())	return false;
		
		//		3.	(specific to Stack) No larger coin may be placed on top of a smaller coin.
		if(head != null)
		{
			for (Coin p = head; p.next != null && head.next != head; p = p.next ) 
			{
				if(p.type.getSize() > p.next.type.getSize())	return report("Coin is too dang big !");
			}
		}
		return true;
	}
	
	/**
	 * if inherited canAdd from default container returns false we can't add yet
	 * if head is not null and the head coin is smaller than the next coin to be added on top we can't add
	 */
	@Override	//	required
	public boolean canAdd(Coin c) 
	{
		assert wellFormed() : "invariant failed at start of canAdd";
		
		if(!super.canAdd(c))	return false;
		
		if(head != null)
		{
			if( head.type.getSize() < c.type.getSize()) return false;
		}

		assert wellFormed() : "invariant failed at end of canAdd";

		return true;
	}
}
