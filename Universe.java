/**
 * This is the declaring class for an object created by the user, when they choose option 5:
 * to create an object which uses a collection class to refer to several other objects.
 * 
 * This class uses a LinkedList to refer to several Planet objects.
 *
 */

import java.util.LinkedList;;

public class Universe {

	LinkedList<Planet> planetsWithLife;
	
	public Universe (LinkedList<Planet> planetsWithLife)
	{
		this.planetsWithLife = planetsWithLife;
	}
}
