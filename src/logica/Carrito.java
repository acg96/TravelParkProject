package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logica.Alojamiento.Tipo;

public class Carrito {
	private List<ArticuloAbstract> listado;
	private int totalArticulos;
	private double precioTotal;
	
	public Carrito(){
		listado= new ArrayList<>();
		totalArticulos= 0;
		precioTotal= 0.0;
	}
	
	public void añadir(ArticuloAbstract articulo){
		if (articulo != null){
			this.listado.add(articulo);
			precioTotal+= articulo.getPrecioTotal();
			++totalArticulos;
		}
	}
	
	public void añadirDesayuno(ArticuloAbstract articulo){
		if (articulo instanceof ArticuloAlojamiento){
			ArticuloAlojamiento articuloAlojamiento= (ArticuloAlojamiento) articulo;
			if (articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				for (int i= 0; i < listado.size(); ++i){
					if (listado.get(i) == articulo){ //Por referencia
						precioTotal-= listado.get(i).getPrecioTotal();
						((ArticuloAlojamiento)listado.get(i)).añadirDesayuno();
						precioTotal+= listado.get(i).getPrecioTotal();
						break;
					}
				}
			}
		}
	}
	
	public void quitarDesayuno(ArticuloAbstract articulo){
		if (articulo instanceof ArticuloAlojamiento){
			ArticuloAlojamiento articuloAlojamiento= (ArticuloAlojamiento) articulo;
			if (articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				for (int i= 0; i < listado.size(); ++i){
					if (listado.get(i) == articulo){ //Por referencia
						precioTotal-= listado.get(i).getPrecioTotal();
						((ArticuloAlojamiento)listado.get(i)).quitarDesayuno();
						precioTotal+= listado.get(i).getPrecioTotal();
						break;
					}
				}
			}
		}
	}
	
	//Con un valor negativo deja el anterior y lanza excepción, además de si supera aforo AH y AP
	public void actualizarNumeroPersonas(ArticuloAbstract articulo, int numAdultos, int numNiños){
		if (numAdultos < 0 || numNiños < 0){
			throw new IllegalArgumentException("El número de personas debe ser mayor que 0.");
		}
		if (articulo instanceof ArticuloAlojamiento){
			ArticuloAlojamiento obj= (ArticuloAlojamiento) articulo;
			if (!obj.getAlojamiento().getTipo().equals(Tipo.HO)){
				if (numAdultos+numNiños > obj.getAlojamiento().getNumeroPlazas()){
					throw new IllegalArgumentException("El número de personas supera el aforo máximo permitido.");
				}
			}
		}
		for (int i= 0; i < listado.size(); ++i){
			if (listado.get(i) == articulo){ //Por referencia
				precioTotal-= listado.get(i).getPrecioTotal();
				listado.get(i).actualizarNumPersonas(numAdultos, numNiños);
				precioTotal+= listado.get(i).getPrecioTotal();
				break;
			}
		}
	}
	
	//Puede lanzar excepción y dejaría las fechas como estaban inicialmente
	public void actualizarFechas(ArticuloAbstract articulo, Date fechaInicio, Date fechaFin){
		if (fechaInicio== null || fechaFin == null){
			throw new IllegalArgumentException("Las fechas indicadas no son válidas.");
		}
		if (fechaInicio.compareTo(fechaFin) > 0){
			throw new IllegalArgumentException("La fecha de fin no puede ser superior a la de inicio.");
		}
		
		for (int i= 0; i < listado.size(); ++i){
			if (listado.get(i) == articulo){ //Por referencia
				precioTotal-= listado.get(i).getPrecioTotal();
				listado.get(i).setNuevasFechas(fechaInicio, fechaFin);
				precioTotal+= listado.get(i).getPrecioTotal();
				break;
			}
		}
	}
	
	public void eliminar(ArticuloAbstract articulo){
		for (int i= 0; i < listado.size(); ++i){
			if (listado.get(i) == articulo){ //Por referencia
				--totalArticulos;
				precioTotal-= listado.get(i).getPrecioTotal();
				listado.remove(i);
				break;
			}
		}
	}
	
	public int getTotalArticulos(){
		return this.totalArticulos;
	}
	
	public List<ArticuloAbstract> getListado(){
		return this.listado;
	}
	
	public double getPrecioTotal(){
		return this.precioTotal;
	}
	
	public void vaciarCarrito(){
		this.totalArticulos= 0;
		this.precioTotal= 0.0;
		for (int i= listado.size()-1; i >= 0; --i){
			listado.remove(i);
		}
	}
}
