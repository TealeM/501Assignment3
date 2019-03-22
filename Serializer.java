import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Vector;

import org.jdom2.*;


public class Serializer {
	
	private Vector<Object> objects;
	private Vector<Integer> alreadySerialized;
	private Document xmlDoc;
	
	public Serializer () {
		alreadySerialized = new Vector<Integer>();
	}
	
	public Document serialize(Vector<Object> objects) throws Exception
	{
		this.objects = objects;
		xmlDoc = new Document(new Element("serialized"));
		
		for (int i=0; i < objects.size(); i++) 
		{
			
			Object object = objects.get(i);
			Element elemObj;
			if(object.getClass().isArray())
				elemObj = serializeObjectArray(object);
			else
				elemObj = serializeObject(object);
			
			xmlDoc.getRootElement().addContent(elemObj);
		}
		
		return xmlDoc;
	}
	
	private Element serializeObject(Object object) throws Exception
	{		
		boolean isColl = checkIfColl(object);
		if (isColl)
			System.out.println("FOUND AN OBJECT THAT USES A COLLECTIONS CLASS");
		
		int objID = object.hashCode();
		Class objClass = object.getClass();
		
		Element elem = new Element("object");
		elem.setAttribute("id", String.valueOf(objID));
		elem.setAttribute("class", objClass.getName());
		
		Field[] fields = objClass.getDeclaredFields();
		if (fields.length>0)
		{
			for (int i=0; i<fields.length; i++)
			{
				Field field = fields[i];
				field.setAccessible(true);
				Element elemField = serializeField(field, object);
				elem.addContent(elemField);
			}
		}

		alreadySerialized.add(objID);
		return elem;
	}
	private boolean checkIfColl(Object object)
	{
		boolean isColl = false;
		for (int i=0; i<object.getClass().getInterfaces().length; i++)
		{
			String interfaceName = object.getClass().getInterfaces()[i].getClass().getName();
			if(interfaceName.contains("Collections") || interfaceName.contains("List") || interfaceName.contains("Deque"))
			{
				isColl = true;
			}
		}
		return isColl;
	}
	
	private Element serializeObjectArray(Object object)
	{
		int objID = object.hashCode();
		Class objClass = object.getClass();
		int length = Array.getLength(object);
		
		Element elem = new Element("object");
		elem.setAttribute("length", String.valueOf(length));
		elem.setAttribute("id", String.valueOf(objID));
		elem.setAttribute("class", objClass.getName());
		
		Element elemArrayVal;
		for(int i=0; i<length; i++)
		{
			Object arrayVal = Array.get(object, i);
			
			if(objClass.getComponentType().isPrimitive())
				elemArrayVal = serializeArrayVal(arrayVal);
			else
				elemArrayVal = serializeArrayRef(arrayVal);
			
			elem.addContent(elemArrayVal);
		}
		
		alreadySerialized.add(objID);
		return elem;
	}
	
	private Element serializeField(Field field, Object obj) throws Exception
	{
		Element elem = new Element("field");
		elem.setAttribute("declaringclass", obj.getClass().getName());
		elem.setAttribute("name", field.getName());
		Element elemFieldValue;
		if (field.getType().isPrimitive())
			elemFieldValue = serializeFieldVal(field, obj);
		else
			elemFieldValue = serializeFieldRef(field, obj);
		
		elem.addContent(elemFieldValue);
		
		return elem;
	}
	
	private Element serializeFieldVal(Field field, Object obj) throws IllegalAccessException
	{
		Element elem = new Element("value");
		elem.setText(String.valueOf(field.get(obj)));
		return elem;
	}
	private Element serializeFieldRef(Field field, Object obj) throws IllegalAccessException
	{
		Element elem = new Element("reference");
		if(field.get(obj) != null)
			elem.setText(String.valueOf(field.get(obj).hashCode()));
		else
			elem.setText("null");
		return elem;
	}
	private Element serializeArrayVal(Object arrayVal)
	{
		Element elem = new Element("value");
		elem.setText(String.valueOf(arrayVal));
		return elem;
	}
	private Element serializeArrayRef(Object arrayVal)
	{
		Element elem = new Element("reference");
		if(arrayVal != null)
			elem.setText(String.valueOf(arrayVal.hashCode()));
		else
			elem.setText("null");
		return elem;
	}
}
