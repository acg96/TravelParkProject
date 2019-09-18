package logica;

import javax.swing.ImageIcon;

public class ParqueTematico {
	public static final double DESCUENTO_PARQUE= 0.20;
	
	private String codigo;
	private String denominacion;
	private String pais;
	private String localidad;
	private String descripcion;
	private ImageIcon imagen;
	private boolean oferta;
	
	public ParqueTematico(String codigo, String denominacion, String pais, String localidad, String descripcion){
		this.setCodigo(codigo);
		this.setDenominacion(denominacion);
		this.setPais(pais);
		this.setLocalidad(localidad);
		try{
			this.descripcion= descripcion.trim();
		}catch(Exception ex){
			this.descripcion= "";
		}
		try{
			this.imagen= new ImageIcon(getClass().getResource("/img/"+ codigo + ".jpg"));
		}catch(Exception ex){
			System.out.println("No se encuentra la imagen: "+ "/img/"+ codigo + ".jpg");
		}
	}
	
	private void setOferta(boolean oferta){
		this.oferta= oferta;
	}
	
	public boolean isOferta(){
		return this.oferta;
	}
	
	public void aplicarOferta(){
		this.setOferta(true);
	}
	
	public void quitarOferta(){
		this.setOferta(false);
	}
	
	public String getCodigo() {
		return codigo;
	}
	private void setCodigo(String codigo) {
		if (codigo != null){
			if (!codigo.trim().equals("")){
				String checkCodigo=	codigo.trim().substring(0, 2);
				if (checkCodigo.equals("PT")){
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
	
	public String getDenominacion() {
		return denominacion;
	}
	
	private void setDenominacion(String denominacion) {
		if (denominacion != null){
			if (!denominacion.trim().equals("")){
				this.denominacion= denominacion.trim();
			}
			else{
				throw new IllegalArgumentException("La denominacion no es válida.");
			}
		}
		else{
			throw new IllegalArgumentException("La denominacion no es válida.");
		}
	}
	
	public String getPais() {
		return pais;
	}
	
	private void setPais(String pais) {
		if (pais != null){
			if (!pais.trim().equals("")){
				this.pais= pais.trim();
			}
			else{
				throw new IllegalArgumentException("El país no es válido.");
			}
		}
		else{
			throw new IllegalArgumentException("El país no es válido.");
		}
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	private void setLocalidad(String localidad) {
		if (localidad != null){
			if (!localidad.trim().equals("")){
				this.localidad= localidad.trim();
			}
			else{
				throw new IllegalArgumentException("La localidad no es válida.");
			}
		}
		else{
			throw new IllegalArgumentException("La localidad no es válida.");
		}
	}
	
	public String getDescripcion() {
		return descripcion;
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
		ParqueTematico other = (ParqueTematico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
