/**
 * This is the declaring class for an object created by the user, when they choose option 1:
 * to create an object which only has field with primitive values.
 * 
 * This class only has an int field and a boolean field.
 *
 */


public class Planet {
	
	int size;
	boolean lifeSustaining;
	
	public Planet () {}
	
	public Planet (int size, boolean lifeSustaining)
	{
		this.size = size;
		this.lifeSustaining = lifeSustaining;
	}
}
