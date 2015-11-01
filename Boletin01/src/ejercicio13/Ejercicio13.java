package ejercicio13;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Ejercicio13 {

	public static void main(String[] args) {
		FileOutputStream html; 
		Source estilos; 
		Source datos;
		Result result;
		Transformer transformer;

		try {
			html = new FileOutputStream("Ejercicio13.html");
			estilos = new StreamSource("Contactos.xsl");
			datos = new StreamSource("Contactos.xml");
			result = new StreamResult(html);
			transformer = TransformerFactory.newInstance().newTransformer(estilos);
			transformer.transform(datos, result);
			html.close();
		} catch (FileNotFoundException e) {
		} catch (TransformerFactoryConfigurationError e) {
		} catch (TransformerConfigurationException e) {
		} catch (TransformerException e){
		} catch (IOException e){
		}
	}

}
