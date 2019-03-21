import java.util.*;

public class ObjectCreator {

	private UserContact userContact;
	private Vector objIDList;
	private int workingID;
	public ObjectCreator()
	{
		userContact = new UserContact();
		objIDList = new Vector();
		workingID = 0;
	}
	
	public void createObj()
	{
		int objType = userContact.askObjectType();
		switch (objType)
		{
			case 1 :
				createObjPrim();
				break;
//			case 2 :
//				createObjRef();
//				break;
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
	
	private void createObjPrim() 
	{
		
	}
}
