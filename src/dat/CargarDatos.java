package dat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import logica.Alojamiento;
import logica.Entrada;
import logica.Paquete;
import logica.ParqueTematico;
import logica.Principal;

public class CargarDatos {
	public static final String FICHERO_PAQUETES= "files/paquetes.dat";
	public static final String FICHERO_ENTRADAS= "files/entradas.dat";
	public static final String FICHERO_ALOJAMIENTOS= "files/alojamientos.dat";
	public static final String FICHERO_PARQUES= "files/tematicos.dat";
	public static final int VALOR_PAQUETE= 0;
	public static final int VALOR_ENTRADA= 1;
	public static final int VALOR_ALOJAMIENTO= 2;
	public static final int VALOR_PARQUE= 3;
	
	
	public static void parseTodo(){
		parseFichero(VALOR_PARQUE);
		parseFichero(VALOR_ENTRADA);
		parseFichero(VALOR_ALOJAMIENTO);
		parseFichero(VALOR_PAQUETE);
	}
	
	/**
	 * Carga un fichero indicado por parámetro
	 * @param tipo int, con valor VALOR_PAQUETE para paquetes, VALOR_ENTRADA para entradas, VALOR_ALOJAMIENTO para alojamientos o VALOR_PARQUE para parques
	 */
	private static void parseFichero(int tipo){
		String nombreFichero= FICHERO_PARQUES;
		if (tipo == VALOR_PAQUETE){
			nombreFichero= FICHERO_PAQUETES;
		}
		else if (tipo == VALOR_ENTRADA){
			nombreFichero= FICHERO_ENTRADAS;
		}
		else if (tipo == VALOR_ALOJAMIENTO){
			nombreFichero= FICHERO_ALOJAMIENTOS;
		}
		String linea= "";
	    String[] datosLinea= null;
	    try {
	      BufferedReader fichero= new BufferedReader(new FileReader(nombreFichero));
	      while (fichero.ready()) {
	        linea = fichero.readLine();
	        datosLinea = linea.split("@");
	        if (tipo == VALOR_PAQUETE){
				parsePaquete(datosLinea, linea);
			}
			else if (tipo == VALOR_ENTRADA){
				parseEntrada(datosLinea, linea);
			}
			else if (tipo == VALOR_ALOJAMIENTO){
				parseAlojamiento(datosLinea, linea);
			}
			else{
				parseParque(datosLinea, linea);
			}
	      }
	      fichero.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("El archivo no se ha encontrado.");
	    }
	    catch (IOException ioe) {
	    	System.out.println("Error de entrada/salida.");
	    }
	}
	
	private static boolean existLocalidad(String localidad){
		for (String i : Principal.LOCALIDADES){
			if (i.toLowerCase().equals(localidad.trim().toLowerCase())) return true;
		}
		return false;
	}
	
	private static void parseParque(String[] datosLinea, String linea){
		try{
			ParqueTematico parque= new ParqueTematico(datosLinea[0].trim(), datosLinea[1].trim(), datosLinea[2].trim(), datosLinea[3].trim(), datosLinea[4].trim());
			if (!Principal.existeParque(parque.getCodigo())){
				Principal.PARQUES_TEMATICOS.add(parque);
				if (!existLocalidad(parque.getLocalidad())){
					Principal.LOCALIDADES.add(parque.getLocalidad().trim());
				}
			}
			else{
				System.out.println("***Error en línea de fichero " + FICHERO_PARQUES + ":***\n" + linea + "\n");
			}
		}catch(IllegalArgumentException ex){
			System.out.println("***Error en línea de fichero " + FICHERO_PARQUES + ":***\n" + linea + "\n");
		}
	}
	
	private static void parseAlojamiento(String[] datosLinea, String linea){
		try{
			Alojamiento alojamiento= new Alojamiento(datosLinea[0].trim(), datosLinea[1].trim(), Integer.valueOf(datosLinea[2].trim()), datosLinea[3].trim(), datosLinea[4].trim(), Integer.valueOf(datosLinea[5].trim()), Principal.formatoDecimales(Double.valueOf(datosLinea[6].trim())));
			if (!Principal.existeAlojamiento(alojamiento.getCodigo())){
				Principal.ALOJAMIENTOS.add(alojamiento);
			}
			else{
				System.out.println("***Error en línea de fichero " + FICHERO_ALOJAMIENTOS + ":***\n" + linea + "\n");
			}
		}catch(Exception ex){
			System.out.println("***Error en línea de fichero " + FICHERO_ALOJAMIENTOS + ":***\n" + linea + "\n");
		}
	}
	
	private static void parsePaquete(String[] datosLinea, String linea){
		try{
			Paquete paquete= new Paquete(datosLinea[0].trim(), datosLinea[1].trim(), datosLinea[2].trim(), datosLinea[3].trim(), Integer.valueOf(datosLinea[4].trim()), Principal.formatoDecimales(Double.valueOf(datosLinea[5].trim())), Principal.formatoDecimales(Double.valueOf(datosLinea[6].trim())));
			if (!Principal.existePaquete(paquete.getCodigo())){
				Principal.PAQUETES.add(paquete);
			}
			else{
				System.out.println("***Error en línea de fichero " + FICHERO_PAQUETES + ":***\n" + linea + "\n");
			}
		}catch(Exception ex){
			System.out.println("***Error en línea de fichero " + FICHERO_PAQUETES + ":***\n" + linea + "\n");
		}
	}
	
	private static void parseEntrada(String[] datosLinea, String linea){
		try{
			Entrada entrada= new Entrada(datosLinea[0].trim(), datosLinea[1].trim(), Principal.formatoDecimales(Double.valueOf(datosLinea[2].trim())), Principal.formatoDecimales(Double.valueOf(datosLinea[3].trim())));
			if (!Principal.existeEntrada(entrada.getCodigo())){
				Principal.ENTRADAS.add(entrada);
			}
			else{
				System.out.println("***Error en línea de fichero " + FICHERO_ENTRADAS + ":***\n" + linea + "\n");
			}
		}catch(Exception ex){
			System.out.println("***Error en línea de fichero " + FICHERO_ENTRADAS + ":***\n" + linea + "\n");
		}
	}
}
