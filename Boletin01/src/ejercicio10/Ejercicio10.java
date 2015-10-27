package ejercicio10;

import java.io.*;

import com.thoughtworks.xstream.XStream;

import ejercicio08.Contacto;

public class Ejercicio10 {

    public static void main(String[] args) {

        File fichero = new File("binario.dat");
        ListaContactos lista = new ListaContactos();
        FileInputStream fileIn;
        DataInputStream lectorContacto;
        Contacto contacto;
        XStream xstream = new XStream();

        try {
            fileIn = new FileInputStream(fichero);
            lectorContacto = new DataInputStream(fileIn);

            try {
                while (true) {
                    contacto = new Contacto(lectorContacto.readUTF(), lectorContacto.readUTF(), lectorContacto.readUTF(), lectorContacto.readInt(), lectorContacto.readBoolean(), lectorContacto.readFloat());
                    lista.agregarContacto(contacto);
                }


            } catch (EOFException e) {

            }
            lectorContacto.close();

            xstream.alias("Contactos", ListaContactos.class);
            xstream.alias("Contacto", Contacto.class);
            xstream.addImplicitCollection(ListaContactos.class, "lista");
            xstream.toXML(lista, new FileOutputStream("Contactos.xml"));

            fileIn.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

}
