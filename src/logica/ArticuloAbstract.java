package logica;

import java.util.Date;

public abstract class ArticuloAbstract{
	private Date fechaInicio;
	protected Date fechaFin;
	protected int numDias;
	protected double precioTotal;
	
	public ArticuloAbstract(Date fechaInicio, Date fechaFin){
		this.setFechaInicio(fechaInicio);
		this.setFechaFin(fechaFin);
		this.calcularDias();
	}

	public void setNuevasFechas(Date fechaInicio, Date fechaFin){
		this.setFechaInicio(fechaInicio);
		this.setFechaFin(fechaFin);
		this.calcularDias();
		this.calcularPrecioTotal();
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	private void setFechaInicio(Date fechaInicio) {
		if (fechaInicio != null){
			this.fechaInicio = fechaInicio;
		}
		else{
			throw new IllegalArgumentException("No es una fecha de inicio válida.");
		}
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	protected void setFechaFin(Date fechaFin) {
		if (fechaFin != null){
			int difDias= Principal.diferenciaEnDias(fechaFin, fechaInicio);
			if (difDias > 0){
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
	
	protected void calcularDias(){
		this.numDias= Principal.diferenciaEnDias(this.getFechaFin(), this.getFechaInicio());
	}
	
	public int getNumDias(){
		return this.numDias;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}
	
	public abstract boolean isOferta();
	
	public abstract double obtenerPrecioSinDescuento();

	protected abstract void calcularPrecioTotal();
	
	public abstract boolean equals(Object obj);
	
	public abstract int hashCode();
	
	public abstract void actualizarNumPersonas(int numAdultos, int numNiños);
}
