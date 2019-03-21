/**
 * This is the declaring class for an object created by the user, when they choose option 4:
 * to create an object which contains an array of object references.
 * 
 * This class contains an array of object references.
 *
 */
public class SolarSystem {
	Planet[] planets = new Planet[4];
	
	public SolarSystem(Planet[] planets)
	{
		this.planets = planets;
	}
}
