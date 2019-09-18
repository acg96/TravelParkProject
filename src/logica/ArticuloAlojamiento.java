package logica;

import java.util.Date;

import logica.Alojamiento.Tipo;

public class ArticuloAlojamiento extends ArticuloAbstract{
	public static final double INCREMENTO_DESAYUNO= 0.10; //Incremento desayuno
	
	private int numPersonas;
	private Alojamiento alojamiento;
	private boolean desayuno;
	
	public ArticuloAlojamiento(Date fechaInicio, Date fechaFin, Alojamiento alojamiento, int numPersonas, boolean desayuno){
		super(fechaInicio, fechaFin);
		this.setAlojamiento(alojamiento);
		this.setNumPersonas(numPersonas);		
		this.setDesayuno(desayuno);
		this.calcularPrecioTotal();
	}
	
	public boolean getDesayuno(){
		return this.desayuno;
	}
	
	public void añadirDesayuno(){
		this.setDesayuno(true);
		this.calcularPrecioTotal();
	}
	
	public void quitarDesayuno(){
		this.setDesayuno(false);
		this.calcularPrecioTotal();
	}
	
	private void setDesayuno(boolean desayuno){
		boolean result= false;
		if (this.alojamiento.getTipo().equals(Tipo.HO)){
			result= desayuno;
		}
		this.desayuno= result;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	private void setNumPersonas(int numPersonas) {
		if (numPersonas >= 0){
		  if(this.alojamiento.getTipo().equals(Tipo.HO)){
			  this.numPersonas= numPersonas;
		  }
		  else{ 
			  if (numPersonas <= this.alojamiento.getNumeroPlazas()){
				this.numPersonas= numPersonas;
			  }
			  else{
					throw new IllegalArgumentException("El número de personas no es válido.");
			  }
		  }
		}
		else{
			throw new IllegalArgumentException("El número de personas no es válido.");
		}
	}
	
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	private void setAlojamiento(Alojamiento alojamiento) {
		if (alojamiento != null){
			if (Principal.existeAlojamiento(alojamiento.getCodigo())){
				this.alojamiento = alojamiento;
			}
			else{
				throw new IllegalArgumentException("No es un alojamiento existente.");
			}
		}
		else{
			throw new IllegalArgumentException("No es un alojamiento existente.");
		}
	}
	
	private void actualizarNumPersonas(int numPersonas){
		this.setNumPersonas(numPersonas);
		this.calcularPrecioTotal();
	}
	
	@Override
	public void actualizarNumPersonas(int numAdultos, int numNiños){
		this.actualizarNumPersonas(numNiños + numAdultos);
	}
	
	@Override
	public boolean isOferta(){
		return this.alojamiento.isOferta();
	}
	
	public double getPrecioTotalPersonas(){
		double price= 0;
		if ((this.alojamiento.getTipo().equals(Tipo.HO))){
			price= this.alojamiento.getPrecio() * numPersonas * super.getNumDias();
		}
		else{
			price= this.alojamiento.getPrecio() * super.getNumDias();
		}
		if (this.alojamiento.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}
	
	@Override
	public double obtenerPrecioSinDescuento(){
		double price= 0;
		if ((this.alojamiento.getTipo().equals(Tipo.HO))){
			if (this.desayuno){
				price= this.alojamiento.getPrecio() * numPersonas * (1+INCREMENTO_DESAYUNO) * super.getNumDias();
			}
			else{
				price= this.alojamiento.getPrecio() * numPersonas * super.getNumDias();
			}
		}
		else{
			price= this.alojamiento.getPrecio() * super.getNumDias();
		}
		return Principal.formatoDecimales(price);
	}
	
	public double getPrecioTotalDesayuno(){
		double price= 0.0;
		if (this.desayuno && (this.alojamiento.getTipo().equals(Tipo.HO))){
			price= this.alojamiento.getPrecio() * numPersonas * INCREMENTO_DESAYUNO * super.getNumDias();
		}
		if (this.alojamiento.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}
	
	@Override
	protected void calcularPrecioTotal() {
		double price= 0;
		if ((this.alojamiento.getTipo().equals(Tipo.HO))){
			if (this.desayuno){
				price= this.alojamiento.getPrecio() * numPersonas * (1+INCREMENTO_DESAYUNO) * super.getNumDias();
			}
			else{
				price= this.alojamiento.getPrecio() * numPersonas * super.getNumDias();
			}
		}
		else{
			price= this.alojamiento.getPrecio() * super.getNumDias();
		}
		if (this.alojamiento.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		super.precioTotal= Principal.formatoDecimales(price);
	}
	
	@Override
	public String toString(){
		String desayuno= Principal.LOCALIZACION_TEXTOS.getString("articuloAlojamiento.desayunoNo");
		if (this.getDesayuno()){
			desayuno= Principal.LOCALIZACION_TEXTOS.getString("articuloAlojamiento.desayunoSi");
		}
		String result=Principal.LOCALIZACION_TEXTOS.getString("articuloAlojamiento.alojamiento")+": " + this.getAlojamiento().getCodigo() + " / " + this.getAlojamiento().getTipo().toString() + " / " + this.getAlojamiento().getCategoria() + " "+Principal.LOCALIZACION_TEXTOS.getString("articuloAlojamiento.estrellas")+" / " +this.getAlojamiento().getDenominacion() + " / " + Principal.findParque(this.alojamiento.getCodigoParque()).getDenominacion() + " / " + desayuno + "\n";
		result+= Principal.LOCALIZACION_TEXTOS.getString("articuloAlojamiento.fechaInicio")+": " + Principal.DF.format(super.getFechaInicio()) + " / "+Principal.LOCALIZACION_TEXTOS.getString("articuloAlojamiento.numeroNoches")+": " + this.getNumDias() + "\n";
		result+= Principal.LOCALIZACION_TEXTOS.getString("articuloAlojamiento.nPersonas")+": " + this.getNumPersonas() +"\n";
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alojamiento == null) ? 0 : alojamiento.hashCode());
		result = prime * result + numPersonas;
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
		ArticuloAlojamiento other = (ArticuloAlojamiento) obj;
		if (alojamiento == null) {
			if (other.alojamiento != null)
				return false;
		} else if (!alojamiento.equals(other.alojamiento))
			return false;
		if (numPersonas != other.numPersonas)
			return false;
		if (!super.getFechaInicio().equals(other.getFechaInicio()))
			return false;
		if (!super.getFechaFin().equals(other.getFechaFin()))
			return false;
		return true;
	}

	
}
