package logica;

public class Entrada implements Comparable<Entrada>{
	private String codigo;
	private String codigoParque;
	private double precioAdulto;
	private double precioNiño;
	
	public Entrada(String codigo, String codigoParque, double precioAdulto, double precioNiño){
		this.setCodigo(codigo);
		this.setCodigoParque(codigoParque);
		this.setPrecioAdulto(precioAdulto);
		this.setPrecioNiño(precioNiño);
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
				if (checkCodigo.equals("EN")){
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
	
	public double getPrecioAdulto() {
		return precioAdulto;
	}
	
	private void setPrecioAdulto(double precioAdulto) {
		if (precioAdulto >= 0){
			this.precioAdulto= precioAdulto;
		}
		else{
			throw new IllegalArgumentException("El precio de adulto no es válido.");
		}
	}
	
	public double getPrecioNiño() {
		return precioNiño;
	}
	
	private void setPrecioNiño(double precioNiño) {
		if (precioNiño >= 0){
			this.precioNiño= precioNiño;
		}
		else{
			throw new IllegalArgumentException("El precio de niño no es válido.");
		}
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
		Entrada other = (Entrada) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Entrada otra) {
		int estePrecio= (int)((this.getPrecioAdulto() + this.getPrecioNiño())*100);
		int otroPrecio= (int)((otra.getPrecioAdulto() + otra.getPrecioNiño())*100);
		if (this.isOferta()){
			estePrecio*= 1 - ParqueTematico.DESCUENTO_PARQUE;
		}
		if (otra.isOferta()){
			otroPrecio*= 1 - ParqueTematico.DESCUENTO_PARQUE;
		}
		return estePrecio - otroPrecio;
	}
	
	
	
}
