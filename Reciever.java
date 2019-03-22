import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class Reciever {

	public static void main(String[] args) {
		ServerSocket serverSock = null;
		Socket sock = null;
		InputStream in = null;
		Document xmlDoc = new Document();
		SAXBuilder parser = new SAXBuilder();
		Deserializer aDeserializer = new Deserializer();
		Inspector anInspector = new Inspector();
		
		Vector<Object> objects = new Vector<Object>();
		
		try {		
			int port = 3000;
			serverSock = new ServerSocket(port);
			sock = serverSock.accept();
			
			in = sock.getInputStream();
			
			xmlDoc = parser.build(in);
			aDeserializer.deserialize(xmlDoc);
			objects = aDeserializer.getObjects();
			
			System.out.println("SIZE OF OBJECTS: "+ objects.size());	//DEBUG
			
			//DEBUG
			for(int i = 0; i<objects.size(); i++)
			{
				System.out.println("Element "+i+" of objects Vector: "+String.valueOf(objects.get(i).hashCode()));
			}
			
			//Visualizer
			System.out.println("DOING VISUALIZER STUFF");	//DEBUG
			for (int i=0; i<objects.size(); i++)
				anInspector.inspect(objects.get(i), false);
			
			sock.close();
			serverSock.close();
		}catch(Exception e) {e.printStackTrace();}
	}
}
