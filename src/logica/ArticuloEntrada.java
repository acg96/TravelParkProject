package logica;


import java.util.Date;

public class ArticuloEntrada extends ArticuloAbstract{
	private int numAdultos;
	private int numNi�os;
	private Entrada entrada;
	
	public ArticuloEntrada(Date fechaInicio, Date fechaFin, Entrada entrada, int numAdultos, int numNi�os){
		super(fechaInicio, fechaFin);
		this.setNumAdultos(numAdultos);
		this.setNumNi�os(numNi�os);
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

	public int getNumNi�os() {
		return numNi�os;
	}

	private void setNumNi�os(int numNi�os) {
		if (numNi�os >= 0){
			this.numNi�os = numNi�os;
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
			throw new IllegalArgumentException("No es una fecha de fin v�lida.");
		}
	}
	
	@Override
	public double obtenerPrecioSinDescuento(){
		double price= ((this.entrada.getPrecioAdulto() * numAdultos) + (this.entrada.getPrecioNi�o() * numNi�os)) * super.getNumDias();
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
		result+= Principal.LOCALIZACION_TEXTOS.getString("articuloEntrada.nAdultos")+": " + this.getNumAdultos() + " / "+Principal.LOCALIZACION_TEXTOS.getString("articuloEntrada.nCrios")+": " + this.getNumNi�os() +"\n";
		return result;
	}
	
	@Override
	public void actualizarNumPersonas(int numAdultos, int numNi�os){
		this.setNumAdultos(numAdultos);
		this.setNumNi�os(numNi�os);
		this.calcularPrecioTotal();
	}

	
	public double getPrecioTotalAdultos(){
		double price= (this.entrada.getPrecioAdulto() * numAdultos) * super.getNumDias();
		if (this.entrada.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}
	
	public double getPrecioTotalNi�os(){
		double price= (this.entrada.getPrecioNi�o() * numNi�os) * super.getNumDias();
		if (this.entrada.isOferta()){
			price*= 1-ParqueTematico.DESCUENTO_PARQUE;
		}
		return Principal.formatoDecimales(price);
	}

	@Override
	protected void calcularPrecioTotal() {
		double price= ((this.entrada.getPrecioAdulto() * numAdultos) + (this.entrada.getPrecioNi�o() * numNi�os)) * super.getNumDias();
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
		result = prime * result + numNi�os;
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
		if (numNi�os != other.numNi�os)
			return false;
		if (!super.getFechaInicio().equals(other.getFechaInicio()))
			return false;
		if (!super.getFechaFin().equals(other.getFechaFin()))
			return false;
		return true;
	}
	
	

}
