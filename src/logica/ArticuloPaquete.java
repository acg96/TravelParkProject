package logica;


import java.util.Date;

public class ArticuloPaquete extends ArticuloAbstract{
	private int numAdultos;
	private int numNiños;
	private Paquete paquete;
	
	public ArticuloPaquete(Date fechaInicio, Date fechaFin, Paquete paquete, int numAdultos, int numNiños){
		super(fechaInicio, fechaFin);
		this.setNumAdultos(numAdultos);
		this.setNumNiños(numNiños);
		this.setPaquete(paquete);
		this.calcularPrecioTotal();
	}
	
	
	public int getNumAdultos() {
		return numAdultos;
	}

	private void setNumAdultos(int numAdultos) {
		if (numAdultos >= 0){
			this.numAdultos = numAdultos;
		}
	}

	public int getNumNiños() {
		return numNiños;
	}

	private void setNumNiños(int numNiños) {
		if (numNiños >= 0){
			this.numNiños = numNiños;
		}
	}

	public Paquete getPaquete() {
		return paquete;
	}

	private void setPaquete(Paquete paquete) {
		if (paquete != null){
			if (Principal.existePaquete(paquete.getCodigo())){
				if (super.getNumDias() == paquete.getDuracion()){
					this.paquete = paquete;
				}
				else{
					throw new IllegalArgumentException("La duración del paquete difiere del tiempo seleccionado.");
				}
			}
			else{
				throw new IllegalArgumentException("No es un paquete existente.");
			}
		}
		else{
			throw new IllegalArgumentException("No es un paquete existente.");
		}
	}
	
	@Override
	public void actualizarNumPersonas(int numAdultos, int numNiños){
		this.setNumAdultos(numAdultos);
		this.setNumNiños(numNiños);
		this.calcularPrecioTotal();
	}
	
	@Override
	public boolean isOferta(){
		return this.paquete.isOferta();
	}
	
	@Override
	public double obtenerPrecioSinDescuento(){
		double price= ((this.paquete.getPrecioAdulto() * numAdultos) + (this.paquete.getPrecioNiño() * numNiños));
		return Principal.formatoDecimales(price);
	}

	public double getPrecioTotalAdultos(){
		double price= (this.paquete.getPrecioAdulto() * numAdultos);
		if (this.paquete.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}
	
	public double getPrecioTotalNiños(){
		double price= (this.paquete.getPrecioNiño() * numNiños);
		if (this.paquete.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}
	
	@Override
	protected void calcularPrecioTotal() {
		double price= ((this.paquete.getPrecioAdulto() * numAdultos) + (this.paquete.getPrecioNiño() * numNiños));
		if (this.paquete.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		super.precioTotal= Principal.formatoDecimales(price);
	}
	
	@Override
	public void setNuevasFechas(Date fechaInicio, Date fechaFin){
		super.setNuevasFechas(fechaInicio, fechaFin);
		if (super.getNumDias() != paquete.getDuracion()){
			throw new IllegalArgumentException("La duración del paquete difiere del tiempo seleccionado. Deben ser"
					+ " " + paquete.getDuracion() + " días.");
		}
	}
	
	@Override
	public String toString(){
		String result=Principal.LOCALIZACION_TEXTOS.getString("articuloPaquete.paquete")+": " + this.getPaquete().getCodigo() + " / " + this.getPaquete().getDenominacion() + " / " + Principal.findParque(this.paquete.getCodigoParque()).getDenominacion() + " / " + this.getNumDias() + " "+Principal.LOCALIZACION_TEXTOS.getString("articuloPaquete.dias")+"\n";
		result+= Principal.LOCALIZACION_TEXTOS.getString("articuloPaquete.fechaInicio")+": " + Principal.DF.format(super.getFechaInicio()) + "\n";
		result+= Principal.LOCALIZACION_TEXTOS.getString("articuloPaquete.nAdultos")+": " + this.getNumAdultos() + " / "+Principal.LOCALIZACION_TEXTOS.getString("articuloPaquete.nCrios")+": " + this.getNumNiños() +"\n";
		return result;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numAdultos;
		result = prime * result + numNiños;
		result = prime * result + ((paquete == null) ? 0 : paquete.hashCode());
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
		ArticuloPaquete other = (ArticuloPaquete) obj;
		if (numAdultos != other.numAdultos)
			return false;
		if (numNiños != other.numNiños)
			return false;
		if (paquete == null) {
			if (other.paquete != null)
				return false;
		} else if (!paquete.equals(other.paquete))
			return false;
		if (!super.getFechaInicio().equals(other.getFechaInicio()))
			return false;
		if (!super.getFechaFin().equals(other.getFechaFin()))
			return false;
		return true;
	}
	
	
}
