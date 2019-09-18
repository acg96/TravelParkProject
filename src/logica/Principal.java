package logica;

import igu.VentanaPrincipal;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import logica.Alojamiento.Tipo;


public class Principal {
	public static List<ParqueTematico> PARQUES_TEMATICOS= new ArrayList<>();
	public static List<String> LOCALIDADES= new ArrayList<>();
	public static List<Alojamiento> ALOJAMIENTOS= new ArrayList<>();
	public static List<Entrada> ENTRADAS= new ArrayList<>();
	public static List<Paquete> PAQUETES= new ArrayList<>();
	
	//******* INTERNACIONALIZACION *********
	public static String COUNTRY_CODE= Locale.getDefault(Locale.Category.FORMAT).getCountry();
	public static Locale LOCALIZACION= Locale.getDefault(Locale.Category.FORMAT);
	public static ResourceBundle LOCALIZACION_TEXTOS= ResourceBundle.getBundle("locale.Textos", LOCALIZACION);
	public static NumberFormat NF= NumberFormat.getCurrencyInstance(LOCALIZACION);
	public static DateFormat DF= DateFormat.getDateInstance(DateFormat.MEDIUM, LOCALIZACION);
	
	public static void inicializarLocalizacion(){
		JOptionPane.setDefaultLocale(LOCALIZACION);
	}
	
	public static void nuevaLocalizacion(Locale localizacion){
		Locale.setDefault(localizacion); //Necesario para jCalendar
		LOCALIZACION= localizacion;
		LOCALIZACION_TEXTOS= ResourceBundle.getBundle("locale.Textos", LOCALIZACION);
		NF= NumberFormat.getCurrencyInstance(LOCALIZACION);
		DF= DateFormat.getDateInstance(DateFormat.MEDIUM, LOCALIZACION);
		JOptionPane.setDefaultLocale(LOCALIZACION);
		VentanaPrincipal.changeStaticLocation();
	}
	//******* INTERNACIONALIZACION *********
	
	public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = Math.round(diferenciaEn_ms / (1000 * 60 * 60 * 24.0));
		return (int) dias;
	}
	
	public static boolean existeParque(String codigo){
		for (ParqueTematico i : PARQUES_TEMATICOS){
			if (i.getCodigo().equals(codigo)) return true;
		}
		return false;
	}
	
	public static void colocarHotelesPrimero(){
		List<Alojamiento> nuevoListado= new ArrayList<>();
		for (Alojamiento i : ALOJAMIENTOS){
			if (i.getTipo().equals(Tipo.HO)){
				nuevoListado.add(i);
			}
		}
		for (Alojamiento i : ALOJAMIENTOS){
			if (!i.getTipo().equals(Tipo.HO)){
				nuevoListado.add(i);
			}
		}
		ALOJAMIENTOS= nuevoListado;
	}
	
	public static void colocarApartaHotelPrimero(){
		List<Alojamiento> nuevoListado= new ArrayList<>();
		for (Alojamiento i : ALOJAMIENTOS){
			if (i.getTipo().equals(Tipo.AH)){
				nuevoListado.add(i);
			}
		}
		for (Alojamiento i : ALOJAMIENTOS){
			if (!i.getTipo().equals(Tipo.AH)){
				nuevoListado.add(i);
			}
		}
		ALOJAMIENTOS= nuevoListado;
	}
	
	public static void colocarApartamentoPrimero(){
		List<Alojamiento> nuevoListado= new ArrayList<>();
		for (Alojamiento i : ALOJAMIENTOS){
			if (i.getTipo().equals(Tipo.AP)){
				nuevoListado.add(i);
			}
		}
		for (Alojamiento i : ALOJAMIENTOS){
			if (!i.getTipo().equals(Tipo.AP)){
				nuevoListado.add(i);
			}
		}
		ALOJAMIENTOS= nuevoListado;
	}
	
	public static boolean existeAlojamiento(String codigo){
		for (Alojamiento i : ALOJAMIENTOS){
			if (i.getCodigo().equals(codigo)) return true;
		}
		return false;
	}
	
	public static Date fechaSinHoras(Date fecha){
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static boolean existeEntrada(String codigo){
		for (Entrada i : ENTRADAS){
			if (i.getCodigo().equals(codigo)) return true;
		}
		return false;
	}

	public static boolean existePaquete(String codigo){
		for (Paquete i : PAQUETES){
			if (i.getCodigo().equals(codigo)) return true;
		}
		return false;
	}
	
	public static Paquete findPaquete(String codigo){
		for (Paquete i : PAQUETES){
			if (i.getCodigo().equals(codigo)) return i;
		}
		return null;
	}
	
	public static Alojamiento findAlojamiento(String codigo){
		for (Alojamiento i : ALOJAMIENTOS){
			if (i.getCodigo().equals(codigo)) return i;
		}
		return null;
	}
	
	public static Entrada findEntrada(String codigo){
		for (Entrada i : ENTRADAS){
			if (i.getCodigo().equals(codigo)) return i;
		}
		return null;
	}
	
	public static ParqueTematico findParque(String codigo){
		for (ParqueTematico i : PARQUES_TEMATICOS){
			if (i.getCodigo().equals(codigo)) return i;
		}
		return null;
	}
	
	public static void randomOfertaParque(){
		if (!isAlreadyOfertaParque()){
			Random rnd= new Random();
			int aleatorio= rnd.nextInt(PARQUES_TEMATICOS.size());
			PARQUES_TEMATICOS.get(aleatorio).aplicarOferta();
		}
		else{
			quitarOfertasParque();
			randomOfertaParque();
		}
	}
	
	private static void quitarOfertasParque(){
		for (ParqueTematico i : PARQUES_TEMATICOS){
			if (i.isOferta()) i.quitarOferta();
		}
	}
	
	private static boolean isAlreadyOfertaParque(){
		for (ParqueTematico i : PARQUES_TEMATICOS){
			if (i.isOferta()) return true;
		}
		return false;
	}
	
	public static double formatoDecimales(double number){
		return Math.round(number*100)/100.0;
	}
}
