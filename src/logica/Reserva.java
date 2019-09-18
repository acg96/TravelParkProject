package logica;


import java.util.Date;

import dat.GenerarFichero;

public class Reserva {
	public static final String NOMBRE_EMPRESA= "TRAVEL PARK";
	
	private Carrito carrito;
	private String comentarios;
	private String nombreCliente;
	private String apellidosCliente;
	private String identificacionCliente;
	private boolean realizada;
	private Date fechaReserva;
	
	public Reserva(){
		this.carrito= new Carrito();
		inicializarAtributos();
	}
	
	private void inicializarAtributos(){
		this.comentarios= "";
		this.nombreCliente= "";
		this.apellidosCliente= "";
		this.identificacionCliente= "";
		this.realizada= false;
		this.fechaReserva= null;
	}
	
	public double getImporteTotal(){
		return this.carrito.getPrecioTotal();
	}
	
	public Date getFechaReserva(){
		return this.fechaReserva;
	}
	
	public void formalizarReserva(){
		if (!this.realizada){
			if (this.carrito.getListado().size() > 0 && !nombreCliente.equals("") && !apellidosCliente.equals("") && !identificacionCliente.equals("")){
				this.realizada= true;
				this.fechaReserva= Principal.fechaSinHoras(new Date());
				String nombreFich= this.identificacionCliente + "-" + Principal.DF.format(this.fechaReserva);
				GenerarFichero.generarFicheroReserva(nombreFich, this.toString());
			}
			else{
				throw new RuntimeException("La reserva no se puede formalizar porque faltan datos.");
			}
		}
	}
	
	public boolean isRealizada(){
		return this.realizada;
	}
	
	public void resetReserva(){
		inicializarAtributos();
		this.carrito.vaciarCarrito();
	}

	public String getComentarios() {
		return comentarios;
	}

	private void setComentarios(String comentarios) {
		if (comentarios == null){
			comentarios= "";
		}
		this.comentarios= comentarios.trim();
	}
	
	public void añadirComentarios(String comentarios){
		if (!isRealizada()){
			if (comentarios == null){
				comentarios= "";
			}
			this.setComentarios(comentarios);
		}
	}
	
	public void vaciarComentarios(){
		if (!isRealizada()){
			this.setComentarios("");
		}
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		if (!isRealizada()){
			if (nombreCliente != null){
				if (!nombreCliente.trim().equals("")){
					this.nombreCliente = nombreCliente.trim();
				}
				else{
					throw new IllegalArgumentException("El nombre es inválido.");
				}
			}
			else{
				throw new IllegalArgumentException("El nombre es inválido.");
			}
		}
	}

	public String getApellidosCliente() {
		return apellidosCliente;
	}

	public void setApellidosCliente(String apellidosCliente) {
		if (!isRealizada()){
			if (apellidosCliente != null){
				if (!apellidosCliente.trim().equals("")){
					this.apellidosCliente = apellidosCliente.trim();
				}
				else{
					throw new IllegalArgumentException("Los apellidos son inválidos.");
				}
			}
			else{
				throw new IllegalArgumentException("Los apellidos son inválidos.");
			}
		}
	}

	public String getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(String identificacionCliente) {
		if (!isRealizada()){
			if (identificacionCliente != null){
				if (!identificacionCliente.trim().equals("")){
					this.identificacionCliente = identificacionCliente.trim();
				}
				else{
					throw new IllegalArgumentException("La identificación no es válida.");
				}
			}
			else{
				throw new IllegalArgumentException("La identificación no es válida.");
			}
		}
	}

	public Carrito getCarrito() {
		return carrito;
	}
	
	private String getStringPaquetes(){
		String result="** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.paquetes")+" **\n";
		for (ArticuloAbstract i : this.carrito.getListado()){
			if (i instanceof ArticuloPaquete){
				result+= ((ArticuloPaquete) i).toString();
				result+="\n";
			}
		}
		if (result.equals("** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.paquetes")+" **\n")){
			result="";
		}
		else{
			result+="\n";
		}
		return result;
	}
	
	private String getStringAlojamientos(){
		String result="** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.alojamientos")+" **\n";
		for (ArticuloAbstract i : this.carrito.getListado()){
			if (i instanceof ArticuloAlojamiento){
				result+= ((ArticuloAlojamiento) i).toString();
				result+="\n";
			}
		}
		if (result.equals("** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.alojamientos")+" **\n")){
			result="";
		}
		else{
			result+="\n";
		}
		return result;
	}
	
	private String getStringEntradas(){
		String result="** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.entradas")+" **\n";
		for (ArticuloAbstract i : this.carrito.getListado()){
			if (i instanceof ArticuloEntrada){
				result+= ((ArticuloEntrada) i).toString();
				result+="\n";
			}
		}
		if (result.equals("** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.entradas")+" **\n")){
			result="";
		}
		else{
			result+="\n";
		}
		return result;
	}
	
	private double getTotalPaquetes(){
		double result= 0.0;
		for (ArticuloAbstract i : this.carrito.getListado()){
			if (i instanceof ArticuloPaquete){
				result+= ((ArticuloPaquete) i).getPrecioTotal();
			}
		}
		result= Principal.formatoDecimales(result);
		return result;
	}
	
	private double getTotalAlojamientos(){
		double result= 0.0;
		for (ArticuloAbstract i : this.carrito.getListado()){
			if (i instanceof ArticuloAlojamiento){
				result+= ((ArticuloAlojamiento) i).getPrecioTotal();
			}
		}
		result= Principal.formatoDecimales(result);
		return result;
	}
	
	private double getTotalEntradas(){
		double result= 0.0;
		for (ArticuloAbstract i : this.carrito.getListado()){
			if (i instanceof ArticuloEntrada){
				result+= ((ArticuloEntrada) i).getPrecioTotal();
			}
		}
		result= Principal.formatoDecimales(result);
		return result;
	}
	
	private double getDescuento(){
		double result= 0.0;
		for (ArticuloAbstract i : this.carrito.getListado()){
			if (i.isOferta()){
				double diferencia= i.obtenerPrecioSinDescuento() - i.getPrecioTotal();
				diferencia= Principal.formatoDecimales(diferencia);
				result+= diferencia;
			}
		}
		result= Principal.formatoDecimales(result);
		return result;
	}
	
	public String verResumenReserva(){
		String result;
		if (!isRealizada()){
			result= Principal.LOCALIZACION_TEXTOS.getString("reserva.viajes")+" " + NOMBRE_EMPRESA + "\n\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.resumen")+" " + "\n-------------------------------------------------------------\n";
			result+= this.getIdentificacionCliente() + " - " + this.getNombreCliente() + " " + this.getApellidosCliente() +"\n\n**** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.datos")+" ****\n\n";
			result+= this.getStringPaquetes() + this.getStringAlojamientos() + this.getStringEntradas();
			result+= "**** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.coste")+" ****\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.paquetesMinus")+":\t\t\t\t" + Principal.NF.format(this.getTotalPaquetes());
			result+= "\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.alojamientosMinus")+":        \t\t\t\t" + Principal.NF.format(this.getTotalAlojamientos()); 
			result+= "\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.entradasMinus")+":             \t\t\t\t" + Principal.NF.format(this.getTotalEntradas()); 
			double descuento= getDescuento();
			if (descuento > 0.0){
				result+= "\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.oferta")+":\t\t\t\t" + Principal.NF.format(descuento) + "\n\n"; 
			}
			else{
				result+="\n\n";
			}
			result+= Principal.LOCALIZACION_TEXTOS.getString("reserva.importe")+":       \t\t\t\t" + Principal.NF.format(this.getImporteTotal());
		}
		else{
			result=this.toString();
		}
		return result;
	}
	
	@Override
	public String toString(){
		String result;
		if (isRealizada()){
			result= Principal.LOCALIZACION_TEXTOS.getString("reserva.viajes")+" " + NOMBRE_EMPRESA + "\n\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.justificante")+" - " + Principal.DF.format(this.fechaReserva) + "\n-------------------------------------------------------------\n";
			result+= this.getIdentificacionCliente() + " - " + this.getNombreCliente() + " " + this.getApellidosCliente() +"\n\n**** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.datos")+" ****\n\n";
			result+= this.getStringPaquetes() + this.getStringAlojamientos() + this.getStringEntradas();
			result+= "**** "+Principal.LOCALIZACION_TEXTOS.getString("reserva.pagado")+" ****\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.paquetesMinus")+":\t\t\t\t" + Principal.NF.format(this.getTotalPaquetes());
			result+= "\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.alojamientosMinus")+":        \t\t\t\t" + Principal.NF.format(this.getTotalAlojamientos()); 
			result+= "\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.entradasMinus")+":             \t\t\t\t" + Principal.NF.format(this.getTotalEntradas());
			double descuento= getDescuento();
			if (descuento > 0.0){
				result+= "\n"+Principal.LOCALIZACION_TEXTOS.getString("reserva.oferta")+":\t\t\t\t" + Principal.NF.format(descuento) + "\n\n";
			}
			else{
				result+="\n\n";
			}
			result+= Principal.LOCALIZACION_TEXTOS.getString("reserva.importe")+":       \t\t\t\t" + Principal.NF.format(this.getImporteTotal());
		}
		else{
			result=Principal.LOCALIZACION_TEXTOS.getString("reserva.noRealizada");
		}
		return result;
	}
}
