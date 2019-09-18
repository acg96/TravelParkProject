package dat;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class GenerarFichero {
	public static final String RUTA_FICHERO= "files/";
	public static final String EXTENSION_FICHERO= ".dat";
	
	
	public static void generarFicheroReserva(String nombreFichero, String datos){
		try {
			BufferedWriter fichero= new BufferedWriter(new FileWriter(RUTA_FICHERO + nombreFichero + EXTENSION_FICHERO));
			fichero.write(datos);
			fichero.close();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida.");
		}
	}
	
}