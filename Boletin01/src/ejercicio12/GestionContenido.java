package ejercicio12;

import java.io.IOException;
import java.io.PrintWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionContenido extends DefaultHandler{
	private PrintWriter escribir; String contenido = ""; private final String prefijo = "(Attb.";
	
	public GestionContenido() throws IOException{
		super();
		escribir = new PrintWriter("Ejercicio12.txt");
	}

	public void startDocument() throws SAXException {
		System.out.println("Parseando contenido de \"Contactos.xml\" a \"Ejercicio12.txt\"");
	}

	public void endDocument() throws SAXException {
		System.out.println("fichero \"Ejercicio12.txt\" creado con el contenido de \"Contactos.xml\"");
		escribir.close();
	}

	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if (!localName.equals("Contactos") && !localName.equals("Contacto"))
			escribir.print(localName + ": ");
		
		if (attributes.getLength() > 0)
			for (int i = 0; i < attributes.getLength(); i++)
				escribir.print(prefijo + attributes.getQName(i) + ": " + attributes.getValue(i) + ") ");
	}

	public void endElement(String uri, String localName, String name) throws SAXException {
		if (localName.equals("Contacto"))
			escribir.println();
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		contenido = new String (ch, inicio, longitud);
		contenido = contenido.replaceAll("[\t\n ]", "");
		if (!contenido.equals("")){
			contenido += ",";
			escribir.print(contenido);
		} 
	}
}
