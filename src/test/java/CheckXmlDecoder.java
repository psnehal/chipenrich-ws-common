import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class CheckXmlDecoder{

	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream("status.xml");
		BufferedInputStream bis = new BufferedInputStream(fis);
		XMLDecoder xmlDecoder = new XMLDecoder(bis);
		Object deSerializedObject = xmlDecoder.readObject();
		xmlDecoder.close();
		
		// System.out.println("\nChecking For Values In De-Serialized Object");
	     //   System.out.println("...First Name: " + deSerializedObject.getClass().getClass());
	       
	
	
	}

}