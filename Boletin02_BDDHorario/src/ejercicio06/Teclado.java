package ejercicio06;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teclado {
	static Scanner teclado = new Scanner(System.in);
	
	public enum TipoComparar {
		MAYOR_IGUAL, MENOR_IGUAL, MAYOR, MENOR
	}
	
	public enum TipoEntre {
		AMBOS_INC, AMBOS_EXC, MIN_INC_MAX_EXC, MIN_EXC_MAX_INC
	}
	
	// Leer caracter.
	public static char leerChar(){
		char caracter = ' ';
		String cadena;
		do {
			cadena = teclado.next();
			if (cadena.length()>1)
				System.out.print("- Introduzca s�lo un caracter: ");
			else
				caracter = cadena.charAt(0);
		} while(cadena.length()>1);
		
		return caracter;
	}
	
	// leer caracter con patr�n.
	public static char leerCharPatron(String patron){
		char caracter=' ';
		String cadena;
		boolean repetir = false;
		do{
			cadena = teclado.next();
			
			if (cadena.length()>1)
				System.out.print("Introduzca s�lo un caracter: ");
			else
				if (cadena.matches(patron)){
					caracter = cadena.charAt(0);
					repetir = false;
				}else{
					System.out.print("El caracter no cumple el patr�n: ");
					repetir = true;
				}
		} while (cadena.length()>1 || repetir);
		
		return caracter;
	}
	
	// Leer linea.
	public static String leerLinea(){
		String cadena;

		cadena = teclado.nextLine();
		return cadena;
	}
	// Leer l�nea con patr�n.
	public static String leerLineaPatron(String patron){
		String cadena;
		do {
			cadena = teclado.nextLine();
			
			if (!cadena.matches(patron))
				System.out.print("La cadena no cumple el patr�n: ");
			
		} while(!cadena.matches(patron));
		
		return cadena;
	}
	
	
	// Leer String sin espacios.
	public static String leerPalabra(){
		String cadena;

		cadena = teclado.next();
		return cadena;
	}
	// Leer String sin espacios.
	public static String leerPalabraPatron(String patron){
		String cadena;
		do {
			cadena = teclado.next();
			
			if (!cadena.matches(patron))
				System.out.print("- La cadena no cumple el patr�n: ");
			
		} while(!cadena.matches(patron));
		
		return cadena;
	}

	public static int leerInt(){
		int numero = 0;
		boolean error;
		do{
			try{
				numero = teclado.nextInt();
				error = false;
			}catch (InputMismatchException e){
				System.out.print("-  Debe introducir un n�mero: ");
				error = true;
			}finally{
				teclado.nextLine();
			}
		}while(error);
		
		return numero;
	}
	
	public static int leerIntComparar(int numComparar, TipoComparar tipo){
		int numero;
		boolean repetir = false;
		do {
			numero = leerInt();
			
			switch (tipo){
			case MAYOR_IGUAL:
				if (numero < numComparar){
					System.out.printf("- El numero debe ser >= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR_IGUAL:
				if (numero > numComparar){
					System.out.printf("- El numero debe ser <= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MAYOR:
				if (numero <= numComparar){
					System.out.printf("- El numero debe ser > %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR:
				if (numero >= numComparar){
					System.out.printf("- El numero debe ser < %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static int leerIntEntre(int min, int max, TipoEntre tipo){
		int numero;
		boolean repetir = false;
		do {
			numero = leerInt();
			
			switch (tipo){
			case AMBOS_INC:
				if (numero >= min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
				
			case AMBOS_EXC:
				if (numero > min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_INC_MAX_EXC:
				if (numero >= min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_EXC_MAX_INC:
				if (numero > min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
			}
		} while (repetir);
	
		return numero;
	}
	public static byte leerByte(){
		byte numero = 0;
		boolean error;	
		do{
			try{
				numero = teclado.nextByte();
				error = false;
			}catch (InputMismatchException e){
				System.out.print("- Debe introducir un n�mero: ");
				
				error = true;
			}finally{
				teclado.nextLine();
			}
		}while(error);
		
		return numero;
	}
	
	public static byte leerByteComparar(byte numComparar, TipoComparar tipo){
		byte numero;
		boolean repetir = false;
		do {
			numero = leerByte();
			
			switch (tipo){
			case MAYOR_IGUAL:
				if (numero < numComparar){
					System.out.printf("- El numero debe ser >= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR_IGUAL:
				if (numero > numComparar){
					System.out.printf("- El numero debe ser <= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MAYOR:
				if (numero <= numComparar){
					System.out.printf("- El numero debe ser > %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR:
				if (numero >= numComparar){
					System.out.printf("- El numero debe ser < %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static byte leerByteEntre(byte min, byte max, TipoEntre tipo){
		byte numero;
		boolean repetir = false;
		do {
			numero = leerByte();
			
			switch (tipo){
			case AMBOS_INC:
				if (numero >= min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
				
			case AMBOS_EXC:
				if (numero > min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_INC_MAX_EXC:
				if (numero >= min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_EXC_MAX_INC:
				if (numero > min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static short leerShort(){
		short numero = 0;
		boolean error;
		do{
			try{
				numero = teclado.nextShort();
				error = false;
			}catch (InputMismatchException e){
				System.out.println("- Debe introducir un n�mero: ");

				error = true;
			}finally{
				teclado.nextLine();
			}
		}while(error);
		
		return numero;
	}
	
	public static short leerShortComparar(short numComparar, TipoComparar tipo){
		short numero;
		boolean repetir = false;
		do {
			numero = leerShort();
			
			switch (tipo){
			case MAYOR_IGUAL:
				if (numero < numComparar){
					System.out.printf("- El numero debe ser >= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR_IGUAL:
				if (numero > numComparar){
					System.out.printf("- El numero debe ser <= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MAYOR:
				if (numero <= numComparar){
					System.out.printf("- El numero debe ser > %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR:
				if (numero >= numComparar){
					System.out.printf("- El numero debe ser < %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static short leerShortEntre(short min, short max, TipoEntre tipo){
		short numero;
		boolean repetir = false;
		do {
			numero = leerShort();
			
			switch (tipo){
			case AMBOS_INC:
				if (numero >= min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
				
			case AMBOS_EXC:
				if (numero > min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && < %d:",min, max);
					repetir = true;
				}
				break;
				
			case MIN_INC_MAX_EXC:
				if (numero >= min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_EXC_MAX_INC:
				if (numero > min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static long leerLong(){
		long numero = 0;
		boolean error;
		do{
			try{
				numero = teclado.nextLong();
				error = false;
			}catch (InputMismatchException e){
				System.out.print("- Debe introducir un n�mero: ");

				error = true;
			}finally{
				teclado.nextLine();
			}
		}while(error);
		
		return numero;
	}
	
	public static long leerLongComparar(long numComparar, TipoComparar tipo){
		long numero;
		boolean repetir = false;
		do {
			numero = leerLong();
			
			switch (tipo){
			case MAYOR_IGUAL:
				if (numero < numComparar){
					System.out.printf("- El numero debe ser >= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR_IGUAL:
				if (numero > numComparar){
					System.out.printf("- El numero debe ser <= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MAYOR:
				if (numero <= numComparar){
					System.out.printf("- El numero debe ser > %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR:
				if (numero >= numComparar){
					System.out.printf("- El numero debe ser < %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static long leerLongEntre(long min, long max, TipoEntre tipo){
		long numero;
		boolean repetir = false;
		do {
			numero = leerLong();
			
			switch (tipo){
			case AMBOS_INC:
				if (numero >= min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
				
			case AMBOS_EXC:
				if (numero > min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_INC_MAX_EXC:
				if (numero >= min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_EXC_MAX_INC:
				if (numero > min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static float leerFloat(){
		float numero = 0;
		boolean error;
		do{
			try{
				numero = teclado.nextFloat();
				error = false;
			}catch (InputMismatchException e){
				System.out.println("- Debe introducir un n�mero: ");

				error = true;
			}finally{
				teclado.nextLine();
			}
		}while(error);
		
		return numero;
	}
	
	public static float leerFloatComparar(float numComparar, TipoComparar tipo){
		float numero;
		boolean repetir = false;
		do {
			numero = leerFloat();
			
			switch (tipo){
			case MAYOR_IGUAL:
				if (numero < numComparar){
					System.out.printf("- El numero debe ser >= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR_IGUAL:
				if (numero > numComparar){
					System.out.printf("- El numero debe ser <= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MAYOR:
				if (numero <= numComparar){
					System.out.printf("- El numero debe ser > %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR:
				if (numero >= numComparar){
					System.out.printf("- El numero debe ser < %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static float leerFloatEntre(float min, float max, TipoEntre tipo){
		float numero;
		boolean repetir = false;
		do {
			numero = leerFloat();
			
			switch (tipo){
			case AMBOS_INC:
				if (numero >= min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
				
			case AMBOS_EXC:
				if (numero > min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_INC_MAX_EXC:
				if (numero >= min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_EXC_MAX_INC:
				if (numero > min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
			}
		} while (repetir);
	
		return numero;
	}
	public static double leerDouble(){
		double numero = 0;
		boolean error;
		do{
			try{
				numero = teclado.nextDouble();
				error = false;
			}catch (InputMismatchException e){
				System.out.print("- Debe introducir un n�mero: ");

				error = true;
			}finally{
				teclado.nextLine();
			}
		}while(error);
		
		return numero;
	}
	
	public static double leerDoubleComparar(double numComparar, TipoComparar tipo){
		double numero;
		boolean repetir = false;
		do {
			numero = leerDouble();
			
			switch (tipo){
			case MAYOR_IGUAL:
				if (numero < numComparar){
					System.out.printf("- El numero debe ser >= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR_IGUAL:
				if (numero > numComparar){
					System.out.printf("- El numero debe ser <= %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MAYOR:
				if (numero <= numComparar){
					System.out.printf("- El numero debe ser > %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
				
			case MENOR:
				if (numero >= numComparar){
					System.out.printf("- El numero debe ser < %d: ",numComparar);
					repetir = true;
				}else
					repetir = false;
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static double leerDoubleEntre(double min, double max, TipoEntre tipo){
		double numero;
		boolean repetir = false;
		do {
			numero = leerDouble();
			
			switch (tipo){
			case AMBOS_INC:
				if (numero >= min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
				
			case AMBOS_EXC:
				if (numero > min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_INC_MAX_EXC:
				if (numero >= min && numero < max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser >= %d && < %d: ",min, max);
					repetir = true;
				}
				break;
				
			case MIN_EXC_MAX_INC:
				if (numero > min && numero <= max)
					repetir = false;
				else{
					System.out.printf("- El numero debe ser > %d && <= %d: ",min, max);
					repetir = true;
				}
				break;
			}
		} while (repetir);
	
		return numero;
	}
	
	public static boolean leerBoolean(String pregunta, String opcion1, String opcion2){
		boolean respuesta; 
		int numero;
		
		System.out.printf("- %s\n", pregunta);
		System.out.printf("1. %s\n", opcion1);
		System.out.printf("2. %s\n", opcion2);
		System.out.print("- Respuesta: ");
		numero = leerIntEntre(1, 2, TipoEntre.AMBOS_INC);
		
		if (numero == 1)
			respuesta = true;
		else
			respuesta = false;
		return respuesta;
	}
	
	public static void close(){
		teclado.close();
	}
	
	public static void limpiarBuffer(){
		teclado.nextLine();
	}
}
