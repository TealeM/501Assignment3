/**
 * This is the declaring class for an object created by the user, when they choose option 2:
 * to create an object which contains references to other objects.
 * 
 * This class contains references to Planet objects.
 *
 */

public class Alien {

	Planet homePlanet;
	Planet favePlanet;
	
	public Alien () {}
	
	public Alien (Planet homePlanet, Planet favePlanet)
	{
		this.homePlanet = homePlanet;
		this.favePlanet = favePlanet;
	}
}
