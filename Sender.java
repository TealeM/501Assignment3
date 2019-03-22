import java.io.*;
import java.util.HashMap;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Sender {

	public static void main (String[] args)
	{
		ObjectCreator objCreator = new ObjectCreator();
		Serializer aSerializer = new Serializer();
		Document xmlDoc;
		XMLOutputter xmlOP;
		OutputStream out = null;
		Socket sock = null;
		InetAddress ip = null;
		
		// Allow user to create objects, and get the HashMap of objects & IDs from the ObjectCreator
		objCreator.createObjs();
		HashMap <Integer, Object> objects = objCreator.getObjects();
		
		// Serialize all objects, and create an XML document
		xmlDoc = aSerializer.serialize(objects);
		
		xmlOP = new XMLOutputter();
		xmlOP.setFormat(Format.getPrettyFormat());
		// Set up a connection to receiver, link output stream to socket
		try {
			ip = InetAddress.getLocalHost();
		
//		InetAddress ip = InetAddress.getByName(<<hostname of receiving computer>>);			//FOR DEMO
			int port = 3000;
			sock = new Socket(ip, port);

			out = sock.getOutputStream();
		
			xmlOP.output(xmlDoc, out);
			sock.close();
		}catch(Exception e) {e.printStackTrace();}
	}
}
