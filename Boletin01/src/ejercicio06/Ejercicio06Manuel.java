package ejercicio06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio06Manuel {

	public static void main(String[] args) {
	        File origen = new File("PR006\\fichero.txt");
	        File unido = new File("PR006\\unido.txt");
	        BufferedReader reader, readerF1, readerF2, readerF3;
	        BufferedWriter writerF1, writerF2, writerF3, writerUnido;
	        char[] array = new char[15];
	        int num;

	        try {
	            reader = new BufferedReader(new FileReader(origen));
	            writerUnido = new BufferedWriter(new FileWriter(unido));

	            writerF1 = new BufferedWriter(new FileWriter("PR006\\f1.txt"));
	            writerF2 = new BufferedWriter(new FileWriter("PR006\\f2.txt"));
	            writerF3 = new BufferedWriter(new FileWriter("PR006\\f3.txt"));

	            while ((num=reader.read(array)) != -1) {
	                if(num>10){
	                    writerF1.write(array, 0, 5);
	                    writerF2.write(array, 5, 5);
	                    writerF3.write(array, 10, num-10);
	                }else if(num>5) {
	                    writerF1.write(array, 0, 5);
	                    writerF2.write(array, 5, num-5);
	                }else{
	                    writerF1.write(array, 0, num);
	                }

	            }
	            writerF1.close();
	            writerF2.close();
	            writerF3.close();
	            reader.close();

	            readerF1 = new BufferedReader(new FileReader("PR006\\f1.txt"));
	            readerF2 = new BufferedReader(new FileReader("PR006\\f2.txt"));
	            readerF3 = new BufferedReader(new FileReader("PR006\\f3.txt"));

	            while ((num=readerF1.read(array,0,5)) != -1) {
	                writerUnido.write(array, 0, num);

	                if((num=readerF2.read(array, 5, 5))!=-1){
	                    writerUnido.write(array,5,num);
	                    if((num=readerF3.read(array, 10, 5))!=-1){
	                        writerUnido.write(array,10,num);
	                    }
	                }


	            }
	            writerUnido.close();
	            readerF1.close();
	            readerF2.close();
	            readerF3.close();


	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.out.println("Error en el E/S.");
	        }
	}
}
