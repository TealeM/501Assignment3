import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class Reciever {

	public static void main(String[] args) {
		ServerSocket serverSock = null;
		Socket sock = null;
		InputStream in = null;
		Document xmlDoc = new Document();
		SAXBuilder parser = new SAXBuilder();
		
		try {		
			int port = 3000;
			serverSock = new ServerSocket(port);
			sock = serverSock.accept();
			
			in = sock.getInputStream();
			
		}catch(Exception e) {e.printStackTrace();}
		
		try {
		xmlDoc = parser.build(in);
		
		System.out.println("YOUR XML DOC LOOKS LIKE:" +xmlDoc.toString());
		
		}catch(Exception e) {e.printStackTrace();}
		
		try {
			sock.close();
			serverSock.close();
		}catch(Exception e) {e.printStackTrace();}
		
	}

}
