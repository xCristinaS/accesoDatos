package ejercicio12;

import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Ejercicio12 {
	public static void main(String[] args) {
		XMLReader procesadorXML;
		GestionContenido gestor;
		InputSource fileXML;

		try {
			procesadorXML = XMLReaderFactory.createXMLReader();
			gestor = new GestionContenido();
			procesadorXML.setContentHandler(gestor);
			fileXML = new InputSource("Contactos.xml");
			procesadorXML.parse(fileXML);
		} catch (SAXException e) {

		} catch (IOException e){
			
		}
	}
}
