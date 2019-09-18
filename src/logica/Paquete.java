package logica;

import javax.swing.ImageIcon;

public class Paquete implements Comparable<Paquete>{
	private String codigo;
	private String denominacion;
	private String codigoParque;
	private String codigoAlojamiento;
	private int duracion;
	private double precioAdulto;
	private double precioNi�o;
	private ImageIcon imagen;
	
	
	public Paquete(String codigo, String denominacion, String codigoParque, String codigoAlojamiento, int duracion, double precioAdulto, double precioNi�o){
		this.setCodigo(codigo);
		this.setDenominacion(denominacion);
		this.setCodigoParque(codigoParque);
		this.setCodigoAlojamiento(codigoAlojamiento);
		this.setDuracion(duracion);
		this.setPrecioAdulto(precioAdulto);
		this.setPrecioNi�o(precioNi�o);
		try{
			this.imagen= new ImageIcon(getClass().getResource("/img/"+ codigo + ".jpg"));
		}catch(Exception ex){
			System.out.println("No se encuentra la imagen: "+ "/img/"+ codigo + ".jpg");
		}
	}
	
	public boolean isOferta(){
		if (Principal.findParque(this.getCodigoParque()).isOferta()){
			return true;
		}
		return false;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	private void setCodigo(String codigo) {
		if (codigo != null){
			if (!codigo.trim().equals("")){
				String checkCodigo=	codigo.trim().substring(0, 2);
				if (checkCodigo.equals("PV")){
					this.codigo= codigo.trim();
				}
				else{
					throw new IllegalArgumentException("El codigo no es v�lido.");
				}
			}
			else{
				throw new IllegalArgumentException("El codigo no es v�lido.");
			}
		}
		else{
			throw new IllegalArgumentException("El codigo no es v�lido.");
		}
	}
	
	public String getDenominacion() {
		return denominacion;
	}
	
	private void setDenominacion(String denominacion) {
		if (denominacion != null){
			if (!denominacion.trim().equals("")){
				this.denominacion= denominacion.trim();
			}
			else{
				throw new IllegalArgumentException("La denominaci�n no es v�lida.");
			}
		}
		else{
			throw new IllegalArgumentException("La denominaci�n no es v�lida.");
		}
	}
	
	public String getCodigoParque() {
		return codigoParque;
	}
	
	private void setCodigoParque(String codigoParque) {
		if (codigoParque!=null){
			if (!codigoParque.trim().equals("")){
				if (Principal.existeParque(codigoParque.trim())){
					this.codigoParque= codigoParque.trim();
				}
				else{
					throw new IllegalArgumentException("El c�digo del parque no es v�lido.");
				}
			}
			else{
				throw new IllegalArgumentException("El c�digo del parque no es v�lido.");
			}
		}
		else{
			throw new IllegalArgumentException("El c�digo del parque no es v�lido.");
		}
	}
	
	public String getCodigoAlojamiento() {
		return codigoAlojamiento;
	}
	
	private void setCodigoAlojamiento(String codigoAlojamiento) {
		if (codigoAlojamiento!=null){
			if (!codigoAlojamiento.trim().equals("")){
				if (Principal.existeAlojamiento(codigoAlojamiento.trim())){
					this.codigoAlojamiento= codigoAlojamiento.trim();
				}
				else{
					throw new IllegalArgumentException("El c�digo del alojamiento no es v�lido.");
				}
			}
			else{
				throw new IllegalArgumentException("El c�digo del alojamiento no es v�lido.");
			}
		}
		else{
			throw new IllegalArgumentException("El c�digo del alojamiento no es v�lido.");
		}
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	private void setDuracion(int duracion) {
		if (duracion > 0){
			this.duracion= duracion;
		}
		else{
			throw new IllegalArgumentException("La duraci�n no es v�lida.");
		}
	}
	
	public double getPrecioAdulto() {
		return precioAdulto;
	}
	
	private void setPrecioAdulto(double precioAdulto) {
		if (precioAdulto >= 0){
			this.precioAdulto= precioAdulto;
		}
		else{
			throw new IllegalArgumentException("El precio de adulto no es v�lido.");
		}
	}
	
	public double getPrecioNi�o() {
		return precioNi�o;
	}
	
	private void setPrecioNi�o(double precioNi�o) {
		if (precioNi�o >= 0){
			this.precioNi�o= precioNi�o;
		}
		else{
			throw new IllegalArgumentException("El precio de ni�o no es v�lido.");
		}
	}
	
	public ImageIcon getImagen() {
		return imagen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paquete other = (Paquete) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Paquete otro) {
		int estePrecio= (int)((this.getPrecioAdulto() + this.getPrecioNi�o())*100);
		int otroPrecio= (int)((otro.getPrecioAdulto() + otro.getPrecioNi�o())*100);
		if (this.isOferta()){
			estePrecio*= 1 - ParqueTematico.DESCUENTO_PARQUE;
		}
		if (otro.isOferta()){
			otroPrecio*= 1 - ParqueTematico.DESCUENTO_PARQUE;
		}
		return estePrecio - otroPrecio;
	}
	
}
