package logica;


import java.util.Date;

public class ArticuloEntrada extends ArticuloAbstract{
	private int numAdultos;
	private int numNiños;
	private Entrada entrada;
	
	public ArticuloEntrada(Date fechaInicio, Date fechaFin, Entrada entrada, int numAdultos, int numNiños){
		super(fechaInicio, fechaFin);
		this.setNumAdultos(numAdultos);
		this.setNumNiños(numNiños);
		this.setEntrada(entrada);
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

	public Entrada getEntrada() {
		return entrada;
	}

	private void setEntrada(Entrada entrada) {
		if (entrada != null){
			if (Principal.existeEntrada(entrada.getCodigo())){
				this.entrada = entrada;
			}
			else{
				throw new IllegalArgumentException("No es una entrada existente.");
			}
		}
		else{
			throw new IllegalArgumentException("No es una entrada existente.");
		}
	}
	
	@Override
	protected void calcularDias(){
		this.numDias= Principal.diferenciaEnDias(this.getFechaFin(), this.getFechaInicio());
		++this.numDias;
	}
	
	@Override
	protected void setFechaFin(Date fechaFin){
		if (fechaFin != null){
			int difDias= Principal.diferenciaEnDias(fechaFin, super.getFechaInicio());
			if (difDias >= 0){
				this.fechaFin = fechaFin;
			}
			else{
				throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio.");
			}
		}
		else{
			throw new IllegalArgumentException("No es una fecha de fin válida.");
		}
	}
	
	@Override
	public double obtenerPrecioSinDescuento(){
		double price= ((this.entrada.getPrecioAdulto() * numAdultos) + (this.entrada.getPrecioNiño() * numNiños)) * super.getNumDias();
		return Principal.formatoDecimales(price);
	}
	
	@Override
	public boolean isOferta(){
		return this.entrada.isOferta();
	}
	
	@Override
	public String toString(){
		String result=Principal.LOCALIZACION_TEXTOS.getString("articuloEntrada.entrada")+": " + this.getEntrada().getCodigo() + " / " +  Principal.findParque(this.entrada.getCodigoParque()).getDenominacion() + "\n";
		result+= Principal.LOCALIZACION_TEXTOS.getString("articuloEntrada.fechaInicio")+": " + Principal.DF.format(super.getFechaInicio()) + " / "+Principal.LOCALIZACION_TEXTOS.getString("articuloEntrada.numeroDias")+": " + this.getNumDias() + "\n";
		result+= Principal.LOCALIZACION_TEXTOS.getString("articuloEntrada.nAdultos")+": " + this.getNumAdultos() + " / "+Principal.LOCALIZACION_TEXTOS.getString("articuloEntrada.nCrios")+": " + this.getNumNiños() +"\n";
		return result;
	}
	
	@Override
	public void actualizarNumPersonas(int numAdultos, int numNiños){
		this.setNumAdultos(numAdultos);
		this.setNumNiños(numNiños);
		this.calcularPrecioTotal();
	}

	
	public double getPrecioTotalAdultos(){
		double price= (this.entrada.getPrecioAdulto() * numAdultos) * super.getNumDias();
		if (this.entrada.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}
	
	public double getPrecioTotalNiños(){
		double price= (this.entrada.getPrecioNiño() * numNiños) * super.getNumDias();
		if (this.entrada.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}

	@Override
	protected void calcularPrecioTotal() {
		double price= ((this.entrada.getPrecioAdulto() * numAdultos) + (this.entrada.getPrecioNiño() * numNiños)) * super.getNumDias();
		if (this.entrada.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		super.precioTotal= Principal.formatoDecimales(price);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entrada == null) ? 0 : entrada.hashCode());
		result = prime * result + numAdultos;
		result = prime * result + numNiños;
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
		ArticuloEntrada other = (ArticuloEntrada) obj;
		if (entrada == null) {
			if (other.entrada != null)
				return false;
		} else if (!entrada.equals(other.entrada))
			return false;
		if (numAdultos != other.numAdultos)
			return false;
		if (numNiños != other.numNiños)
			return false;
		if (!super.getFechaInicio().equals(other.getFechaInicio()))
			return false;
		if (!super.getFechaFin().equals(other.getFechaFin()))
			return false;
		return true;
	}
	
	

}
