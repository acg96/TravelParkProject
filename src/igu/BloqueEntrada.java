package igu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;









import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logica.ArticuloEntrada;
import logica.Entrada;
import logica.ParqueTematico;
import logica.Principal;

import com.toedter.calendar.JDateChooser;

public class BloqueEntrada {
	private JPanel pnEntrada;
	private JLabel lblPack_1;
	private JPanel pnTituloEntrada;
	private JPanel pnEntradaNombre;
	private JLabel lblHotelGranVa;
	private JPanel pnEntradaAdultos;
	private JLabel lblAdultos;
	private JSpinner spinnerAdultos;
	private JLabel lblNios;
	private JSpinner spinnerNiños;
	private JPanel pnEntradaFechas;
	private JPanel pnEntradaFechaLlegada;
	private JLabel lblEntradaLlegada;
	private JDateChooser entradacalendarLlegada;
	private JPanel pnDiasEntrada;
	private JLabel lblDas;
	private JSpinner spinnerDiasEntrada;
	private JPanel pnPreciosEntrada;
	private JPanel pnPrecioAdultosEntrada;
	private JLabel lblPrecioAdultos;
	private JLabel lblEurosAdultosEntrada;
	private JPanel pnPrecioNiñosEntrada;
	private JLabel lblPrecioNios;
	private JLabel lblEurosNiñosEntrada;
	private JPanel pnPrecioTotalEntrada;
	private JLabel lblTotalEntrada;
	private JLabel lblTotalEntradaEuros;
	private JPanel pnFinalEntrada;
	private JButton btnMasInformacion;
	private JButton btnAadirALa;
	private JButton btnEliminarDeLaCesta;
	
	private JPanel pnNAdultos;
	private JPanel pnNNiños;
	private VentanaPrincipal vp;
	private boolean cesta;
	
	private ArticuloEntrada articuloEntrada;
	
	public BloqueEntrada(Date fechaLlegada, Date fechaSalida, int numPersonas, Entrada entrada, VentanaPrincipal vp){
		this.articuloEntrada= new ArticuloEntrada(fechaLlegada, fechaSalida, entrada, numPersonas, 0);
		this.vp= vp;
	}
	
	public BloqueEntrada(ArticuloEntrada articuloEntrada, VentanaPrincipal vp, boolean cesta){
		this.articuloEntrada= articuloEntrada;
		this.vp= vp;
		this.cesta= cesta;
	}
	
	private void nuevoArticuloEntrada(){
		articuloEntrada= new ArticuloEntrada(articuloEntrada.getFechaInicio(), articuloEntrada.getFechaFin(), articuloEntrada.getEntrada(), articuloEntrada.getNumAdultos(), articuloEntrada.getNumNiños());
	}
	
	private JPanel getPnNAdultos() {
		if (pnNAdultos == null) {
			pnNAdultos = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNAdultos.getLayout();
			flowLayout.setAlignment(FlowLayout.CENTER);
			flowLayout.setVgap(30);
			pnNAdultos.add(getLblAdultos());
			pnNAdultos.add(getSpinnerAdultos());
		}
		return pnNAdultos;
	}
	
	public ArticuloEntrada getArticuloEntrada(){
		return this.articuloEntrada;
	}
	
	private JPanel getPnNNiños() {
		if (pnNNiños == null) {
			pnNNiños = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNNiños.getLayout();
			flowLayout.setAlignment(FlowLayout.CENTER);
			flowLayout.setVgap(30);
			pnNNiños.add(getLblNios());
			pnNNiños.add(getSpinner_1());
		}
		return pnNNiños;
	}
	
	public JPanel getPnEntrada() {
		if (pnEntrada == null) {
			pnEntrada = new JPanel();
			pnEntrada.setBorder(new LineBorder(new Color(70, 130, 180), 1, true));
			pnEntrada.setBackground(new Color(255, 255, 255));
			pnEntrada.setLayout(new GridLayout(4, 0, 0, 5));
			pnEntrada.add(getPnTituloEntrada());
			pnEntrada.add(getPnEntradaNombre());
			pnEntrada.add(getPnEntradaFechas());
			pnEntrada.add(getPnFinalEntrada());
		}
		return pnEntrada;
	}
	private JLabel getLblPack_1() {
		if (lblPack_1 == null) {
			String titulo= Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.entrada");
			if (this.articuloEntrada.isOferta()){
				titulo+= "  "+Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.descuento")+" -"+(ParqueTematico.DESCUENTO_PARQUE*100)+"%";
			}
			lblPack_1 = new JLabel(titulo);
			lblPack_1.setBackground(new Color(70, 130, 180));
			lblPack_1.setForeground(new Color(255, 255, 255));
			if (this.articuloEntrada.isOferta()){
				lblPack_1.setForeground(new Color(0, 240,50));
			}
			lblPack_1.setFont(new Font("Stencil Std", Font.BOLD, 25));
			lblPack_1.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPack_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPack_1;
	}
	private JPanel getPnTituloEntrada() {
		if (pnTituloEntrada == null) {
			pnTituloEntrada = new JPanel();
			pnTituloEntrada.setBackground(new Color(70, 130, 180));
			pnTituloEntrada.add(getLblPack_1());
		}
		return pnTituloEntrada;
	}
	private JPanel getPnEntradaNombre() {
		if (pnEntradaNombre == null) {
			pnEntradaNombre = new JPanel();
			pnEntradaNombre.setLayout(new GridLayout(0, 2, 0, 0));
			pnEntradaNombre.add(getLblHotelGranVa());
			pnEntradaNombre.add(getPnEntradaAdultos());
		}
		return pnEntradaNombre;
	}
	private JLabel getLblHotelGranVa() {
		if (lblHotelGranVa == null) {
			lblHotelGranVa = new JLabel(Principal.findParque(this.articuloEntrada.getEntrada().getCodigoParque()).getDenominacion()+" ("+Principal.findParque(this.articuloEntrada.getEntrada().getCodigoParque()).getLocalidad()+")");
			lblHotelGranVa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHotelGranVa.setHorizontalTextPosition(SwingConstants.CENTER);
			lblHotelGranVa.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHotelGranVa;
	}
	private JPanel getPnEntradaAdultos() {
		if (pnEntradaAdultos == null) {
			pnEntradaAdultos = new JPanel();
			pnEntradaAdultos.setLayout(new GridLayout(0, 2, 20, 0));
			pnEntradaAdultos.add(getPnNAdultos());
			pnEntradaAdultos.add(getPnNNiños());
		}
		return pnEntradaAdultos;
	}
	private JLabel getLblAdultos() {
		if (lblAdultos == null) {
			lblAdultos = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.adultos"));
			lblAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblAdultos;
	}
	private JSpinner getSpinnerAdultos() {
		if (spinnerAdultos == null) {
			spinnerAdultos = new JSpinner();
			spinnerAdultos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			spinnerAdultos.setBorder(new LineBorder(new Color(70, 130, 180)));
			spinnerAdultos.setModel(new SpinnerNumberModel(1, 1, 99, 1));
			spinnerAdultos.setValue((Integer)this.articuloEntrada.getNumAdultos());
			spinnerAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinnerAdultos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (!cesta){
						articuloEntrada.actualizarNumPersonas((Integer)spinnerAdultos.getValue(), articuloEntrada.getNumNiños());
					}
					else{
						vp.getReserva().getCarrito().actualizarNumeroPersonas(articuloEntrada, (Integer)spinnerAdultos.getValue(), articuloEntrada.getNumNiños());
					}
					getLblEurosAdultosEntrada().setText(Principal.NF.format(articuloEntrada.getPrecioTotalAdultos()));
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return spinnerAdultos;
	}
	private JLabel getLblNios() {
		if (lblNios == null) {
			lblNios = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.crios"));
			lblNios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblNios;
	}
	private JSpinner getSpinner_1() {
		if (spinnerNiños == null) {
			spinnerNiños = new JSpinner();
			spinnerNiños.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			spinnerNiños.setBorder(new LineBorder(new Color(70, 130, 180)));
			spinnerNiños.setModel(new SpinnerNumberModel(0, 0, 99, 1));
			spinnerNiños.setValue((Integer)this.articuloEntrada.getNumNiños());
			spinnerNiños.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinnerNiños.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (!cesta){
						articuloEntrada.actualizarNumPersonas(articuloEntrada.getNumAdultos(), (Integer)spinnerNiños.getValue());
					}
					else{
						vp.getReserva().getCarrito().actualizarNumeroPersonas(articuloEntrada, articuloEntrada.getNumAdultos(), (Integer)spinnerNiños.getValue());
					}
					getLblEurosNiñosEntrada().setText(Principal.NF.format(articuloEntrada.getPrecioTotalNiños()));
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return spinnerNiños;
	}
	private JPanel getPnEntradaFechas() {
		if (pnEntradaFechas == null) {
			pnEntradaFechas = new JPanel();
			pnEntradaFechas.setLayout(new GridLayout(0, 3, 0, 0));
			pnEntradaFechas.add(getPnEntradaFechaLlegada());
			pnEntradaFechas.add(getPnDiasEntrada());
			pnEntradaFechas.add(getPnPreciosEntrada());
		}
		return pnEntradaFechas;
	}
	
	private JPanel getPnEntradaFechaLlegada() {
		if (pnEntradaFechaLlegada == null) {
			pnEntradaFechaLlegada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnEntradaFechaLlegada.getLayout();
			flowLayout.setVgap(23);
			pnEntradaFechaLlegada.add(getLblEntradaLlegada());
			pnEntradaFechaLlegada.add(getEntradaCalendarLlegada());
		}
		return pnEntradaFechaLlegada;
	}
	
	private JLabel getLblEntradaLlegada() {
		if (lblEntradaLlegada == null) {
			lblEntradaLlegada = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.llegada"));
			lblEntradaLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblEntradaLlegada;
	}
	
	private JDateChooser getEntradaCalendarLlegada(){
		if (entradacalendarLlegada == null){
			entradacalendarLlegada= new JDateChooser();
			entradacalendarLlegada.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			entradacalendarLlegada.setBorder(null);
			entradacalendarLlegada.getCalendarButton().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_calendario.png")));
			entradacalendarLlegada.setIgnoreRepaint(true);
			entradacalendarLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
			entradacalendarLlegada.setPreferredSize(new Dimension(160, 35));
			entradacalendarLlegada.setMinSelectableDate(Principal.fechaSinHoras(new Date()));
			entradacalendarLlegada.setDate(this.articuloEntrada.getFechaInicio());
			entradacalendarLlegada.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (getEntradaCalendarLlegada()!=null){
						Calendar calendar= Calendar.getInstance();
						calendar.setTime(getEntradaCalendarLlegada().getDate());
						calendar.add(Calendar.DAY_OF_YEAR, (Integer)getSpinnerDiasEntrada().getValue()-1);
						if (!cesta){
							articuloEntrada.setNuevasFechas(getEntradaCalendarLlegada().getDate(), calendar.getTime());
						}
						else{
							vp.getReserva().getCarrito().actualizarFechas(articuloEntrada, getEntradaCalendarLlegada().getDate(), calendar.getTime());
						}
						vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
					}
					else{
						getEntradaCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
					}
				}
			});
			
		}
		return entradacalendarLlegada;
	}
	private JPanel getPnDiasEntrada() {
		if (pnDiasEntrada == null) {
			pnDiasEntrada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnDiasEntrada.getLayout();
			flowLayout.setVgap(30);
			pnDiasEntrada.add(getLblDas());
			pnDiasEntrada.add(getSpinnerDiasEntrada());
		}
		return pnDiasEntrada;
	}
	private JLabel getLblDas() {
		if (lblDas == null) {
			lblDas = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.dias"));
			lblDas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblDas;
	}
	private JSpinner getSpinnerDiasEntrada() {
		if (spinnerDiasEntrada == null) {
			spinnerDiasEntrada = new JSpinner();
			spinnerDiasEntrada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			spinnerDiasEntrada.setBorder(new LineBorder(new Color(70, 130, 180)));
			spinnerDiasEntrada.setModel(new SpinnerNumberModel(1, 1, 99, 1));
			int restaDias= Principal.diferenciaEnDias(this.articuloEntrada.getFechaFin(), this.articuloEntrada.getFechaInicio());
			++restaDias;
			spinnerDiasEntrada.setValue(restaDias);
			spinnerDiasEntrada.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinnerDiasEntrada.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					Calendar calendar= Calendar.getInstance();
					calendar.setTime(articuloEntrada.getFechaInicio());
					calendar.add(Calendar.DAY_OF_YEAR, (Integer)getSpinnerDiasEntrada().getValue()-1);
					if (!cesta){
						articuloEntrada.setNuevasFechas(articuloEntrada.getFechaInicio(), calendar.getTime());
					}
					else{
						vp.getReserva().getCarrito().actualizarFechas(articuloEntrada, articuloEntrada.getFechaInicio(), calendar.getTime());
					}
					getLblEurosAdultosEntrada().setText(Principal.NF.format(articuloEntrada.getPrecioTotalAdultos()));
					getLblEurosNiñosEntrada().setText(Principal.NF.format(articuloEntrada.getPrecioTotalNiños()));
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return spinnerDiasEntrada;
	}
	private JPanel getPnPreciosEntrada() {
		if (pnPreciosEntrada == null) {
			pnPreciosEntrada = new JPanel();
			pnPreciosEntrada.setLayout(new GridLayout(3, 0, 0, 0));
			pnPreciosEntrada.add(getPnPrecioAdultosEntrada());
			pnPreciosEntrada.add(getPnPrecioNiñosEntrada());
			pnPreciosEntrada.add(getPnPrecioTotalEntrada());
		}
		return pnPreciosEntrada;
	}
	private JPanel getPnPrecioAdultosEntrada() {
		if (pnPrecioAdultosEntrada == null) {
			pnPrecioAdultosEntrada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioAdultosEntrada.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioAdultosEntrada.add(getLblPrecioAdultos());
			pnPrecioAdultosEntrada.add(getLblEurosAdultosEntrada());
		}
		return pnPrecioAdultosEntrada;
	}
	private JLabel getLblPrecioAdultos() {
		if (lblPrecioAdultos == null) {
			lblPrecioAdultos = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.precioAdultos"));
			lblPrecioAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblPrecioAdultos;
	}
	private JLabel getLblEurosAdultosEntrada() {
		if (lblEurosAdultosEntrada == null) {
			lblEurosAdultosEntrada = new JLabel(Principal.NF.format(this.articuloEntrada.getPrecioTotalAdultos()));
			lblEurosAdultosEntrada.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblEurosAdultosEntrada;
	}
	private JPanel getPnPrecioNiñosEntrada() {
		if (pnPrecioNiñosEntrada == null) {
			pnPrecioNiñosEntrada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioNiñosEntrada.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioNiñosEntrada.add(getLblPrecioNios());
			pnPrecioNiñosEntrada.add(getLblEurosNiñosEntrada());
		}
		return pnPrecioNiñosEntrada;
	}
	private JLabel getLblPrecioNios() {
		if (lblPrecioNios == null) {
			lblPrecioNios = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.precioCrios"));
			lblPrecioNios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblPrecioNios;
	}
	private JLabel getLblEurosNiñosEntrada() {
		if (lblEurosNiñosEntrada == null) {
			lblEurosNiñosEntrada = new JLabel(Principal.NF.format(this.articuloEntrada.getPrecioTotalNiños()));
			lblEurosNiñosEntrada.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblEurosNiñosEntrada;
	}
	private JPanel getPnPrecioTotalEntrada() {
		if (pnPrecioTotalEntrada == null) {
			pnPrecioTotalEntrada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioTotalEntrada.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioTotalEntrada.add(getLblTotalEntrada());
			pnPrecioTotalEntrada.add(getLblTotalEntradaEuros());
		}
		return pnPrecioTotalEntrada;
	}
	private JLabel getLblTotalEntrada() {
		if (lblTotalEntrada == null) {
			lblTotalEntrada = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.totalMayus"));
			lblTotalEntrada.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblTotalEntrada;
	}
	
	private void setTextPrecioTotalEuros(){
		String descuento="";
		if (this.articuloEntrada.isOferta()){
			descuento=" ("+Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.antes")+" " + Principal.NF.format(Principal.formatoDecimales((this.articuloEntrada.getPrecioTotal()/(1-ParqueTematico.DESCUENTO_PARQUE))))+")";
		}
		getLblTotalEntradaEuros().setText(Principal.NF.format(this.articuloEntrada.getPrecioTotal())+descuento);
	}
	
	private JLabel getLblTotalEntradaEuros() {
		if (lblTotalEntradaEuros == null) {
			lblTotalEntradaEuros = new JLabel();
			this.setTextPrecioTotalEuros();
			lblTotalEntradaEuros.setForeground(new Color(0, 0, 128));
			lblTotalEntradaEuros.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblTotalEntradaEuros;
	}
	private JPanel getPnFinalEntrada() {
		if (pnFinalEntrada == null) {
			pnFinalEntrada = new JPanel();
			pnFinalEntrada.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
			pnFinalEntrada.add(getBtnMasInformacion());
			if (!cesta){
				pnFinalEntrada.add(getBtnAadirALa());
			}
			else{
				pnFinalEntrada.add(getBtnEliminarDeLaCesta());
			}
		}
		return pnFinalEntrada;
	}
	private JButton getBtnMasInformacion() {
		if (btnMasInformacion == null) {
			btnMasInformacion = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.masInformacion"));
			btnMasInformacion.setPreferredSize(new Dimension(175, 35));
			btnMasInformacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnMasInformacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnMasInformacion.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnMasInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaMasInformacion ventana= new VentanaMasInformacion(Principal.findParque(articuloEntrada.getEntrada().getCodigoParque()).getImagen(), articuloEntrada, vp);
					ventana.setVisible(true);
				}
			});
		}
		return btnMasInformacion;
	}
	
	
	private void mensajeErrorAñadirCarro(){
		Icon icon= new ImageIcon(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		JOptionPane.showMessageDialog(this.getPnEntrada(), Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.fechaLlegadaNoValida"), Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.errorSumarPedido"), JOptionPane.ERROR_MESSAGE, icon);
		this.getEntradaCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
	}
	
	private JButton getBtnAadirALa() {
		if (btnAadirALa == null) {
			btnAadirALa = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.sumarAlaCesta"));
			btnAadirALa.setPreferredSize(new Dimension(175, 35));
			btnAadirALa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnAadirALa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAadirALa.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnAadirALa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int valorAdultos= (Integer)getSpinnerAdultos().getValue();
					int valorNiños= (Integer)getSpinner_1().getValue();
					int difDias= 0;
					if (getEntradaCalendarLlegada().getDate()!=null){
						difDias= Principal.diferenciaEnDias(getEntradaCalendarLlegada().getDate(), Principal.fechaSinHoras(new Date()));
					}
					if (valorAdultos > 0 && valorNiños >= 0 && getEntradaCalendarLlegada().getDate()!=null && difDias >= 0){
						vp.getReserva().getCarrito().añadir(articuloEntrada);
						actualizarCantidadLblCarrito();
						nuevoArticuloEntrada();
					}
					else{
						mensajeErrorAñadirCarro();
					}
				}
			});
		}
		return btnAadirALa;
	}
	
	private JButton getBtnEliminarDeLaCesta() {
		if (btnEliminarDeLaCesta == null) {
			btnEliminarDeLaCesta = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloqueEntr.eliminarArticulo"));
			btnEliminarDeLaCesta.setPreferredSize(new Dimension(175, 35));
			btnEliminarDeLaCesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnEliminarDeLaCesta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnEliminarDeLaCesta.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnEliminarDeLaCesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					vp.getReserva().getCarrito().eliminar(articuloEntrada);
					vp.getVentanaCarro().actualizarListadoCarro();
					vp.getTxCarrito().setText(String.valueOf(vp.getReserva().getCarrito().getTotalArticulos()));
				}
			});
		}
		return btnEliminarDeLaCesta;
	}
	
	private void actualizarCantidadLblCarrito(){
		vp.getTxCarrito().setText(String.valueOf(vp.getReserva().getCarrito().getTotalArticulos()));
	}
}
