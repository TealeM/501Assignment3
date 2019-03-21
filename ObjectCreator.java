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
	//			case 3 :
	//				createObjArrayPrim();
	//				break;
	//			case 4 : 
	//				createObjArrayRef();
	//				break;
	//			case 5 :
	//				createObjColl();
	//				break;
			}
			createAnother = userContact.askCreateAnother();
		}
	}
	
	// Create a Car object with user's specified field values, and adds to objects ArrayList
	private int createPlanet() 
	{
		int thisObjID;
		System.out.println("\nCREATING A PLANET OBJECT");
		
		int size = userContact.askFieldValueInt("size");
		boolean lifeSustaining = userContact.askFieldValueBool("lifeSustaining");
		
		Planet aPlanet = new Planet(size, lifeSustaining);
		objects.put(workingID, aPlanet);
		
		thisObjID = workingID;
		System.out.println("SUCCESSFULLY CREATED PLANET OBJECT! (OBJECT ID = " +thisObjID+")\n");
		workingID++;
		return thisObjID;
	}
	
	private int createAlien()
	{
		int thisObjID;
		System.out.println("\nCREATING AN ALIEN OBJECT");
		
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
			favePlanet = objects.get(createAlien());
		
		Alien anAlien = new Alien ((Planet)homePlanet, (Planet)favePlanet);
		
		objects.put(workingID, anAlien);
		
		thisObjID = workingID;
		System.out.println("SUCCESSFULLY CREATED ALIEN OBJECT! (OBJECT ID = " +thisObjID+")\n");
		workingID++;
		return thisObjID;
	}
}
