package edu.uwm.cs351;

import edu.uwm.cs351.money.Bank;
import edu.uwm.cs351.money.Coin;
import edu.uwm.cs351.money.Stack;
import edu.uwm.cs351.money.Type;

/**
 * A variation on Towers of Hanoi
 */

/**
 * @author * CHRISTIAN OROPEZA CS351 ...RECIEVED HELP FROM BOOK, LIBRARY TUTORS, ONLINE CS TUTOR, AND ADVICE FROM FRIENDS ON HOW TO APPROACH FIXING PERSISTENT BUGS.
 * COLLABORATORS: JOSHUA KNIGHT, JULLIAN MURANO, BIJAY PANTA, JIAHUI YANG (WHILE IN TUTORING LIBRARY SECTION) BUT NO CODE WAS SHARED.
 * Online Sources: 
 */
public class MoveStack 
{

	/**
	 * Move all the coins from
	 * one stack to another, in the same order,
	 * with the help of another stack.
	 * @param from stack to get coins from, must not be null
	 * @param to stack to which the coins should be added, must not be null
	 * and must be empty.
	 * @param helper stack which can be used to hold coins temporarily.
	 * 	It must not be the same as the "to" stack, must not be null and 
	 * must not already have coins in it.
	 */
	public static void doMove(Stack from, Stack to, Stack helper) 
	{
		if(from == null || to == null || helper == null)	throw new NullPointerException("from stack to get coins from is NULL");
		if(helper == to ||  to.size() != 0)	throw new IllegalArgumentException("Stack-helper is equal to Stack-to or Stack-to is not empty");
		
		// TODO: check arguments and then call recursive helper method to do work
		hanoiHelper(from.size(), from, to, helper);
	}
	
	/**
	 * 
	 * @param int n - number of disks in the stacks
	 * @param from - This is the initial stack we start from
	 * @param to - This is the target stack we must obtain
	 * @param helper - This is the borrowed stack we use for organization of discs during movement
	 */
	public static void hanoiHelper(int n, Stack from, Stack to, Stack helper )
	{
		if(n == 0)	return;
		
		else
		{
			hanoiHelper(n - 1, from, helper, to);
			to.add(from.remove());
            hanoiHelper(n - 1, helper, to, from);
		}
	}
	
	// coins to request.  We only have $2.00, so we
	// use a lot of pennies
	private static final Type[] coinsToStack = 
		{
			Type.HALFDOLLAR, Type.DOLLAR, Type.QUARTER, 
			Type.NICKEL, Type.NICKEL, 
			Type.PENNY, Type.PENNY, Type.PENNY, Type.PENNY, Type.PENNY, 
			Type.DIME, 
		};
	
	/**
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) 
	{
		Bank b = Bank.getInstance();
		// TODO
		// 1. Create a stack and place in it coins
		// withdrawn from the bank (all the ones in coinsToStack).
		// 2. Create two other stacks.
		// 3. Call doMove to move coins from first stack to second stack
		// using a third stack as the helper stack.
		// Do not use spies!
		//	make sure i look at other classes for main method to see which method to use.
		Stack fromStack = new Stack();	
		Stack helperStack = new Stack();
		Stack toStack = new Stack();
		
		for(int cash = 0; cash < coinsToStack.length; ++cash)
		{
			Coin chaching = b.withdraw(coinsToStack[cash]);
			
			fromStack.add(chaching);
		}
		
		System.out.println(":( •before any discs have been moved• :(");
		System.out.println("NUMBER OF COINS: "+fromStack.size());
		fromStack.toString();
		doMove(fromStack, toStack, helperStack);
		System.out.println(":( •before any discs have been moved• :(");
		System.out.println("~~~~~~~$~~~~~~~~~~~~~$~~~~~~~~~~$~~~~~~~~");
		System.out.println("~~~~~~~$~~~~~~~~~~~~~$~~~~~~~~~~$~~~~~~~~");
		System.out.println(":) •after all discs have been moved• :)");	
		toStack.toString();
		System.out.println("NUMBER OF COINS: "+toStack.size());
		System.out.println(":) •after all discs have been moved• :)");
	}
}
