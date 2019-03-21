import java.util.*;

public class ObjectCreator {

	private UserContact userContact;
	private HashMap<Integer, Object> objects; 
	int workingID;
	
	public ObjectCreator()
	{
		userContact = new UserContact();
		objects = new HashMap<Integer, Object>();
		workingID = 0;
	}
	
	public void createObj()
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
	//			case 5 :
	//				createObjColl();
	//				break;
			}
			createAnother = userContact.askCreateAnother();
		}
	}
	
	// Create a Planet object with user's specified field values, and add to objects HashMap
	private int createPlanet() 
	{
		int thisObjID;
		System.out.println("\nCREATING A PLANET OBJECT");
		
		//Set field values
		int size = userContact.askFieldValueInt("size");
		boolean lifeSustaining = userContact.askFieldValueBool("lifeSustaining");
		
		//Create object
		Planet aPlanet = new Planet(size, lifeSustaining);
	
		thisObjID = workingID;
		objects.put(thisObjID, aPlanet);
	
		System.out.println("SUCCESSFULLY CREATED PLANET OBJECT! (OBJECT ID = " +thisObjID+")\n");
		workingID++;
		return thisObjID;
	}
	
	private int createAlien()
	{
		int thisObjID;
		System.out.println("\nCREATING AN ALIEN OBJECT");
		
		//Set field values
		Object homePlanet = null;
		int homePlanetID = userContact.askFieldValueRef("homePlanet");
		if (homePlanetID > -1)
			homePlanet = objects.get(homePlanetID);
		else if (homePlanetID == -1)
			homePlanet = objects.get(createPlanet());
		
		Object favePlanet = null;
		int favePlanetID = userContact.askFieldValueRef("favePlanet");
		if (favePlanetID > -1)
			favePlanet = objects.get(homePlanetID);
		else if (favePlanetID == -1)
			favePlanet = objects.get(createPlanet());
		
		//Create object
		Alien anAlien = new Alien ((Planet)homePlanet, (Planet)favePlanet);
		
		thisObjID = workingID;
		objects.put(thisObjID, anAlien);
		
		System.out.println("SUCCESSFULLY CREATED ALIEN OBJECT! (OBJECT ID = " +thisObjID+")\n");
		workingID++;
		return thisObjID;
	}
	
	private int createSpaceShip()
	{
		int thisObjID;
		System.out.println("\nCREATING A SPACESHIP OBJECT");
	
		//Set field values
		int[] astronautIDs;
		astronautIDs = userContact.askFieldValueArrayInts("astronautIDs", 5);
		
		//Create object
		SpaceShip aSpaceShip = new SpaceShip(astronautIDs);
		
		thisObjID = workingID;
		objects.put(thisObjID, aSpaceShip);
		
		System.out.println("SUCCESSFULLY CREATED SPACESHIP OBJECT! (OBJECT ID = " +thisObjID+")\n");
		workingID++;
		return thisObjID;	
	}
	
	private int createSolarSystem()
	{
		int thisObjID;
		System.out.println("\nCREATING A SOLAR SYSTEM OBJECT");
		
		//Set field values
		Planet[] planets = new Planet[4];
		int objID;
		System.out.println("Please set the 4 object reference elements for the array value of the field, planets.");
		for (int i=0; i<4; i++)
		{
			System.out.println("Planet object for Element "+i);
			objID = userContact.askFieldValueRef();
			if (objID >-1)
				planets[i] = (Planet)objects.get(objID);
			else if (objID == -1)
				planets[i] = (Planet)objects.get(createPlanet());
			else
				planets[i] = null;
		}
		
		SolarSystem aSolarSystem = new SolarSystem(planets);
		
		thisObjID = workingID;
		objects.put(thisObjID, aSolarSystem);
		
		System.out.println("SUCCESSFULLY CREATED SOLAR SYSTEM OBJECT! (OBJECT ID = " +thisObjID+")\n");
		workingID++;
		return thisObjID;	
	}
}
