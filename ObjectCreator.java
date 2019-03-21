import java.util.*;

public class ObjectCreator {

	private UserContact userContact;
	private ArrayList<Object> objects;
	public ObjectCreator()
	{
		userContact = new UserContact();
		objects = new ArrayList<Object>();
	}
	
	public void createObj()
	{
		int objType = userContact.askObjectType();
		switch (objType)
		{
			case 1 :
				createObjPrim();
				break;
			case 2 :
				createObjRef();
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
	}
	
	// Create a Car object with user's specified field values, and adds to objects ArrayList
	private void createObjPrim() 
	{
		System.out.println("\nCREATING A PLANET OBJECT (only primitive field values)");
		int size = userContact.askFieldValueInt("size");
		boolean lifeSustaining = userContact.askFieldValueBool("lifeSustaining");
		
		Planet aPlanet = new Planet(size, lifeSustaining);
		objects.add(aPlanet);
		System.out.println("\nSUCCESSFULLY CREATED PLANET OBJECT");
	}
	
	
	private void createObjRef()
	{
		
	}
}
