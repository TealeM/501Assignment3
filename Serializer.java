import java.util.HashMap;
import org.jdom2.*;


public class Serializer {
	
	private HashMap<Integer, Object> objects; 
	private Document xmlDoc;
	
	public Serializer () {}
	
	public Document serialize(HashMap<Integer, Object> objects) 
	{
		//Populate HashMap of objects & IDs, and initialize XML document.
		this.objects = objects;
		xmlDoc = new Document(new Element("serialized"));
		
		for (int i=0; i < objects.size(); i++) 
		{
			Object object = objects.get(i);
			
			String objID = String.valueOf(i);
			Class objClass = object.getClass();
			
			Element elem = new Element("object");
			elem.setAttribute("class", objClass.getName());
			elem.setAttribute("id", objID);
			xmlDoc.getRootElement().addContent(elem);
			
			
		}
		
		
		return xmlDoc;
	}
	
}
