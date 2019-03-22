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
					field = setFieldPrimValue(field, obj, valueElem);
				}
			}
			
			objectTable.put(ID, obj);
			objects.add(obj);
			
		}catch(Exception e) {e.printStackTrace();}
	}
	
	private Field setFieldPrimValue(Field field, Object obj, Element valueElem) throws IllegalAccessException
	{
		if (field.getType() == Integer.class)
		{
			int value = Integer.parseInt(valueElem.getText());
			field.set(obj, value);
		}
		else if (field.getType() == Boolean.class)
		{
			boolean value = Boolean.parseBoolean(valueElem.getText());
			field.set(obj, value);
		}
		else if (field.getType() == Character.class)
		{
			char value = valueElem.getText().charAt(0);
			field.set(obj, value);
		}
		else if (field.getType() == Long.class)
		{
			long value = Long.parseLong(valueElem.getText());
			field.set(obj, value);
		}
		else if (field.getType() == Short.class)
		{
			short value = Short.parseShort(valueElem.getText());
			field.set(obj, value);
		}
		else if (field.getType() == Float.class)
		{
			float value = Float.parseFloat(valueElem.getText());
			field.set(obj, value);
		}
		else if (field.getType() == Double.class)
		{
			double value = Double.parseDouble(valueElem.getText());
			field.set(obj, value);
		}
		else if (field.getType() == Byte.class)
		{
			byte value = Byte.parseByte(valueElem.getText());
			field.set(obj, value);
		}
		
		
		return field;
	}
	
	public Vector<Object> getObjects()
	{
		return objects;
	}
}
