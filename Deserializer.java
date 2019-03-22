import java.util.*;

import org.jdom2.*;

import java.lang.reflect.*;

public class Deserializer {
	
	List<Element> objElems;
	HashMap <Integer, Object> objectTable;
	Vector<Object> objects;
	Document document;
	
	public Deserializer () {
		objectTable = new HashMap <Integer, Object>();
		objects = new Vector<Object>();
	}
	
	public Object deserialize(Document document)
	{
		this.document = document;
		Element root = document.getRootElement();
		objElems = root.getChildren();
		
		for (int i=0; i<objElems.size(); i++)
			deserializeObject(objElems.get(i));
		
		return objects;
	}
	
	private void deserializeObject (Element objElem) 
	{
		try {
			String className = objElem.getAttributeValue("class");
			int ID = Integer.parseInt(objElem.getAttributeValue("id"));
			
			Class objClass = Class.forName(className);

			if (objClass.isArray()) 
				deserializeObjectArray(objElem);
			
			else {	// deserialize non-array object
				Object obj = objClass.newInstance();
				
				List<Element> fieldElems = objElem.getChildren();
				
				for (int i=0; i<fieldElems.size(); i++)
				{
					Element fieldElem = fieldElems.get(i);
					String fieldName = fieldElem.getAttributeValue("name");
					
					Field field = objClass.getDeclaredField(fieldName);
					
					field.setAccessible(true);
					
					Class fieldType = field.getType();
					
					Element valueElem = fieldElem.getChildren().get(0);				
					
					//If the field contains a primitive value
					if (valueElem.getName().contains("value"))
					{
						if(!Modifier.isStatic(field.getModifiers()))
							field.set(obj, getPrimValue(field.getType(),valueElem));
					}
					
					//If the field contains a reference to another object
					else if (valueElem.getName().contains("reference"))
					{
						if (valueElem.getText() != "null")
						{
							int refID = Integer.parseInt(valueElem.getText());
							field.set(obj, objectTable.get(refID));
						}
						else
							field.set(obj, null);
					}
				}
				objectTable.put(ID, obj);
				objects.add(obj);
			}
			
		}catch(Exception e) {e.printStackTrace();}
	}
	
	private void deserializeObjectArray(Element objElem)
	{
		try {
			String className = objElem.getAttributeValue("class");
			int ID = Integer.parseInt(objElem.getAttributeValue("id"));
			int length = Integer.parseInt(objElem.getAttributeValue("length"));
			
			Class objClass = Class.forName(className);
			Object obj = Array.newInstance(objClass.getComponentType(), length);
			objClass = obj.getClass();
			
			List<Element> arrayElems = objElem.getChildren();
			for (int i=0; i<arrayElems.size(); i++)
			{
				Element arrayElem = arrayElems.get(i);
				//If the array elements are primitive values
				if (objClass.getComponentType().isPrimitive())
				{
					Array.set(obj, i, getPrimValue(objClass.getComponentType(), arrayElem));
				}
				//If the array elements are references to other objects
				else
				{
					if (arrayElem.getText() != "null")
					{
						int refID = Integer.parseInt(arrayElem.getText());
						Array.set(obj, i, objectTable.get(refID));
					}
					else
					Array.set(obj, i, null);
				}
			}
			objectTable.put(ID, obj);
			objects.add(obj);
		}catch(Exception e) {e.printStackTrace();}
	}
	
	private Object getPrimValue(Class type, Element valueElem) throws IllegalAccessException
	{
		if (type.getSimpleName().contains("int"))
			return Integer.parseInt(valueElem.getText());
		
		else if (type.getSimpleName().contains("boolean"))
			return Boolean.parseBoolean(valueElem.getText());
		
		else if (type.getSimpleName().contains("char"))
			return valueElem.getText().charAt(0);
		
		else if (type.getSimpleName().contains("long"))
			return Long.parseLong(valueElem.getText());
		
		else if (type.getSimpleName().contains("short"))
			return Short.parseShort(valueElem.getText());
		
		else if (type.getSimpleName().contains("float"))
			return Float.parseFloat(valueElem.getText());
		
		else if (type.getSimpleName().contains("double"))
			return Double.parseDouble(valueElem.getText());
		
		else if (type.getSimpleName().contains("byte"))
			return Byte.parseByte(valueElem.getText());
		
		return valueElem.getText();
	}
	
	public Vector<Object> getObjects()
	{
		return objects;
	}
}
