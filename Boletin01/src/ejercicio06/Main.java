package ejercicio06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		File fCopiar = new File("./fCopiar.txt"), f1 = new File("./f1.txt"), f2 = new File("./f2.txt"), f3 = new File("./f3.txt"), reconstruido = new File("reconstruido.txt");
		FileWriter wriF1, wriF2, wriF3, wriRec; 
		FileReader lectorFCop, lectorF1, lectorF2, lectorF3;
		char[] caracteres = new char[15], recuperarF1 = new char[5], recuperarF2 = new char[5] ,recuperarF3 = new char[5];
		int i = 0 , j = 0, k = 0;
		try {
			wriF1 = new FileWriter(f1);
			wriF2 = new FileWriter(f2);
			wriF3 = new FileWriter(f3);
			wriRec = new FileWriter(reconstruido);
			try {
				lectorFCop = new FileReader(fCopiar);
				while (lectorFCop.read(caracteres) != -1){
					wriF1.write(caracteres,0,5);
					wriF2.write(caracteres,5,5);
					wriF3.write(caracteres,10,5);
					Arrays.fill(caracteres, ' ');
				}
				lectorF1 = new FileReader(f1);
				lectorF2 = new FileReader(f2);
				lectorF3 = new FileReader(f3);
				wriF1.close(); wriF2.close(); wriF3.close();
				while(i != -1 || j != -1 || k != -1){
					if (i != -1){
						if (i != 0)
							wriRec.write(recuperarF1);
						i = lectorF1.read(recuperarF1);
					}
					if (j != -1){
						if (j != 0)
							wriRec.write(recuperarF2);
						j = lectorF2.read(recuperarF2);
					}
					if (k != -1){
						if (k != 0)
							wriRec.write(recuperarF3);
						k = lectorF3.read(recuperarF3);
					}
				}
				lectorFCop.close(); lectorF1.close(); lectorF2.close(); lectorF3.close(); wriRec.close();
			} catch (FileNotFoundException e) {}
		} catch (IOException e1){}
	}
}
