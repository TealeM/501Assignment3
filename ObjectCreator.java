import java.util.*;

public class ObjectCreator {

	private UserContact userContact;
	private Vector<Object> objects; 
	
	public ObjectCreator()
	{
		userContact = new UserContact();
		objects = new Vector<Object>();
	}
	
	public Vector<Object> getObjects()
	{
		return objects;
	}
	
	public void createObjs()
	{
		boolean createAnother = true;
		while (createAnother) {
			int objType = userContact.askObjectType();
			switch (objType)
			{
				case 1 :
					createPlanet();
					break;
				case 2 :
					createAlien();
					break;
				case 3 :
					createSpaceShip();
					break;
				case 4 : 
					createSolarSystem();
					break;
				case 5 :
					createUniverse();
					break;
			}
			createAnother = userContact.askCreateAnother();
		}
	}
	
	// Create a Planet object with user's specified field values, and add to objects HashMap
	private Planet createPlanet() 
	{
		System.out.println("\nCREATING A PLANET OBJECT");
		
		//Set field values
		int size = userContact.askFieldValueInt("size");
		boolean lifeSustaining = userContact.askFieldValueBool("lifeSustaining");
		
		//Create object
		Planet aPlanet = new Planet(size, lifeSustaining);
	
		objects.add(aPlanet);
	
		System.out.println("SUCCESSFULLY CREATED PLANET OBJECT!\n");
		return aPlanet;
	}
	
	// Create a Alien object with user's specified field values (object references), and add to objects Vector
	private void createAlien()
	{
		System.out.println("\nCREATING AN ALIEN OBJECT");
		
		//Set field values
		Object homePlanet = null;
		int homePlanetField = userContact.askFieldValueRef("homePlanet");
		if (homePlanetField == 1)		
			homePlanet = createPlanet();
		
		Object favePlanet = null;
		int favePlanetField = userContact.askFieldValueRef("favePlanet");
		if (favePlanetField == 1)
			favePlanet = createPlanet();
		
		//Create object
		Alien anAlien = new Alien ((Planet)homePlanet, (Planet)favePlanet);
		
		objects.add(anAlien);
		
		System.out.println("SUCCESSFULLY CREATED ALIEN OBJECT!\n");
	}
	
	// Create a Spaceship object with user's specified array elements, and add to objects Vector
	private void createSpaceShip()
	{
		System.out.println("\nCREATING A SPACESHIP OBJECT");
	
		//Set field values
		int[] astronautIDs = new int[5];
		astronautIDs = userContact.askFieldValueArrayInts("astronautIDs", astronautIDs.length);
		
		//Create object
		SpaceShip aSpaceShip = new SpaceShip(astronautIDs);
		
		objects.add(aSpaceShip);
		objects.add(astronautIDs);
		
		System.out.println("SUCCESSFULLY CREATED SPACESHIP OBJECT!\n");
	}
	
	// Create a Solar System object with user's specified array elements (object references), and add to objects Vector
	private void createSolarSystem()
	{
		System.out.println("\nCREATING A SOLAR SYSTEM OBJECT");
		
		//Set field values
		Planet[] planets = new Planet[5];
		System.out.println("Please set the 5 object reference elements for the array, planets.");
		for (int i=0; i<planets.length; i++)
		{
			System.out.println("Planet object for Array Element "+i);
			int elementField = userContact.askFieldValueRef();
			if (elementField == 1)
				planets[i] = createPlanet();
			else
				planets[i] = null;
		}
		
		SolarSystem aSolarSystem = new SolarSystem(planets);
		
		objects.add(aSolarSystem);
		objects.add(planets);
		
		System.out.println("SUCCESSFULLY CREATED SOLAR SYSTEM OBJECT!\n");
	}
	
	// Create a Universe object with user's specified Queue elements (object references), and add to objects Vector
	private void createUniverse()
	{
		System.out.println("\nCREATING A UNIVERSE OBJECT");
		
		//Set field values
		LinkedList<Planet> planetsWithLife = new LinkedList<Planet>();
		int objID;
		System.out.println("Please set the 5 object reference elements for the Linked List, planetsWithLife.");
		
		for (int i=0; i<planetsWithLife.size(); i++)
		{
			System.out.println("Planet object for next List Element: ");
			int elementField = userContact.askFieldValueRef();
			if (elementField == 1)
				planetsWithLife.add(createPlanet());
			else
				planetsWithLife.add(null);
		}
		
		Universe aUniverse = new Universe(planetsWithLife);
		
		objects.add(aUniverse);
		objects.add(planetsWithLife);
		
		System.out.println("SUCCESSFULLY CREATED UNIVERSE OBJECT!\n");
	}
}
