package logica;

import javax.swing.ImageIcon;

public class Alojamiento implements Comparable<Alojamiento>{
	private String codigo;
	private Tipo tipo;
	private int categoria;
	private String denominacion;
	private String codigoParque;
	private int numeroPlazas;
	private double precio;
	private ImageIcon imagen;
	
	public enum Tipo{
		AP, HO, AH;
	}
	
	public Alojamiento(String codigo, String tipo, int categoria, String denominacion, String codigoParque, int numeroPlazas, double precio){
		this.setCodigo(codigo);
		this.setTipo(tipo);
		this.setCategoria(categoria);
		this.setDenominacion(denominacion);
		this.setCodigoParque(codigoParque);
		this.setNumeroPlazas(numeroPlazas);
		this.setPrecio(precio);
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
				if (checkCodigo.equals("AL")){
					this.codigo= codigo.trim();
				}
				else{
					throw new IllegalArgumentException("El codigo no es válido.");
				}
			}
			else{
				throw new IllegalArgumentException("El codigo no es válido.");
			}
		}
		else{
			throw new IllegalArgumentException("El codigo no es válido.");
		}
	}

	public Tipo getTipo() {
		return tipo;
	}

	private void setTipo(String tipo) {
		if (tipo != null){
			if (tipo.equals(Tipo.AP.name()) || tipo.equals(Tipo.HO.name()) || tipo.equals(Tipo.AH.name())){
				this.tipo= Tipo.valueOf(tipo);
			}
			else{
				throw new IllegalArgumentException("El tipo no es válido.");
			}
		}
		else{
			throw new IllegalArgumentException("El tipo no es válido.");
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
					throw new IllegalArgumentException("El código del parque no es válido.");
				}
			}
			else{
				throw new IllegalArgumentException("El código del parque no es válido.");
			}
		}
		else{
			throw new IllegalArgumentException("El código del parque no es válido.");
		}
	}

	public int getCategoria() {
		return categoria;
	}
	
	private void setCategoria(int categoria){
		if (categoria > 0){
			this.categoria= categoria;
		}
		else{
			throw new IllegalArgumentException("La categoría no es válida.");
		}
	}

	public String getDenominacion() {
		return denominacion;
	}
	
	private void setDenominacion(String denominacion){
		if (denominacion != null){
			if (!denominacion.trim().equals("")){
				this.denominacion= denominacion.trim();
			}
			else{
				throw new IllegalArgumentException("La denominación no es válida.");
			}
		}
		else{
			throw new IllegalArgumentException("La denominación no es válida.");
		}
	}

	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	
	private void setNumeroPlazas(int numeroPlazas){
		if (numeroPlazas > 0){
			this.numeroPlazas= numeroPlazas;
		}
		else{
			throw new IllegalArgumentException("El número de plazas no es válido.");
		}
	}

	public double getPrecio() {
		return precio;
	}
	
	private void setPrecio(double precio){
		if (precio >= 0){
			this.precio= precio;
		}
		else{
			throw new IllegalArgumentException("El precio no es válido.");
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
		Alojamiento other = (Alojamiento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Alojamiento otro) {
		int estePrecio= (int)(this.getPrecio()*100);
		int otroPrecio= (int) (otro.getPrecio()*100);
		if (this.isOferta()){
			estePrecio*= 1 - ParqueTematico.DESCUENTO_PARQUE;
		}
		if (otro.isOferta()){
			otroPrecio*= 1 - ParqueTematico.DESCUENTO_PARQUE;
		}
		return estePrecio - otroPrecio;
	}
	
	
}
