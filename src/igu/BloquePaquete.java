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

import logica.ArticuloPaquete;
import logica.Paquete;
import logica.ParqueTematico;
import logica.Principal;

import com.toedter.calendar.JDateChooser;

public class BloquePaquete {
	private JPanel pnPaquete;
	private JLabel lblPack_1;
	private JPanel pnTituloPaquete;
	private JPanel pnPaqueteNombre;
	private JLabel lblHotelGranVa;
	private JPanel pnPaqueteAdultos;
	private JLabel lblAdultos;
	private JSpinner spinnerAdultos;
	private JLabel lblNios;
	private JSpinner spinnerNi�os;
	private JPanel pnPaqueteFechas;
	private JPanel pnPaqueteFechaLlegada;
	private JLabel lblPaqueteLlegada;
	private JDateChooser paquetecalendarLlegada;
	private JPanel pnDiasPaquete;
	private JLabel lblDas;
	private JPanel pnPreciosPaquete;
	private JPanel pnPrecioAdultosPaquete;
	private JLabel lblPrecioAdultos;
	private JLabel lblEurosAdultosPaquete;
	private JPanel pnPrecioNi�osPaquete;
	private JLabel lblPrecioNios;
	private JLabel lblEurosNi�osPaquete;
	private JPanel pnPrecioTotalPaquete;
	private JLabel lblTotalPaquete;
	private JLabel lblTotalPaqueteEuros;
	private JPanel pnFinalPaquete;
	private JButton btnMasInformacion;
	private JButton btnAadirALa;
	private JButton btnEliminarDeLaCesta;
	
	private JPanel pnNAdultos;
	private JPanel pnNNi�os;
	private VentanaPrincipal vp;
	private boolean cesta;
	
	private ArticuloPaquete articuloPaquete;
	
	public BloquePaquete(Date fechaLlegada, int numPersonas, Paquete paquete, VentanaPrincipal vp){
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(fechaLlegada);
		calendar.add(Calendar.DAY_OF_YEAR, paquete.getDuracion());
		Date fechaSalida= calendar.getTime();
		this.articuloPaquete= new ArticuloPaquete(fechaLlegada, fechaSalida, paquete, numPersonas, 0);
		this.vp= vp;
	}
	
	public BloquePaquete(ArticuloPaquete articuloPaquete, VentanaPrincipal vp, boolean cesta){
		this.articuloPaquete= articuloPaquete;
		this.vp= vp;
		this.cesta= cesta;
	}
	
	private void nuevoArticuloPaquete(){
		articuloPaquete= new ArticuloPaquete(articuloPaquete.getFechaInicio(), articuloPaquete.getFechaFin(), articuloPaquete.getPaquete(), articuloPaquete.getNumAdultos(), articuloPaquete.getNumNi�os());
	}
	
	public ArticuloPaquete getArticuloPaquete(){
		return this.articuloPaquete;
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
	
	private JPanel getPnNNi�os() {
		if (pnNNi�os == null) {
			pnNNi�os = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNNi�os.getLayout();
			flowLayout.setAlignment(FlowLayout.CENTER);
			flowLayout.setVgap(30);
			pnNNi�os.add(getLblNios());
			pnNNi�os.add(getSpinner_1());
		}
		return pnNNi�os;
	}
	
	public JPanel getPnPaquete() {
		if (pnPaquete == null) {
			pnPaquete = new JPanel();
			pnPaquete.setBorder(new LineBorder(new Color(70, 130, 180), 1, true));
			pnPaquete.setBackground(new Color(255, 255, 255));
			pnPaquete.setLayout(new GridLayout(4, 0, 0, 5));
			pnPaquete.add(getPnTituloPaquete());
			pnPaquete.add(getPnPaqueteNombre());
			pnPaquete.add(getPnPaqueteFechas());
			pnPaquete.add(getPnFinalPaquete());
		}
		return pnPaquete;
	}
	private JLabel getLblPack_1() {
		if (lblPack_1 == null) {
			String titulo= Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.pack");
			if (this.articuloPaquete.isOferta()){
				titulo+= "  "+Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.descuento")+" -"+(ParqueTematico.DESCUENTO_PARQUE*100)+"%";
			}
			lblPack_1 = new JLabel(titulo);
			lblPack_1.setBackground(new Color(70, 130, 180));
			lblPack_1.setForeground(new Color(255, 255, 255));
			if (this.articuloPaquete.isOferta()){
				lblPack_1.setForeground(new Color(0, 240,50));
			}
			lblPack_1.setFont(new Font("Stencil Std", Font.BOLD, 25));
			lblPack_1.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPack_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPack_1;
	}
	private JPanel getPnTituloPaquete() {
		if (pnTituloPaquete == null) {
			pnTituloPaquete = new JPanel();
			pnTituloPaquete.setBackground(new Color(70, 130, 180));
			pnTituloPaquete.add(getLblPack_1());
		}
		return pnTituloPaquete;
	}
	private JPanel getPnPaqueteNombre() {
		if (pnPaqueteNombre == null) {
			pnPaqueteNombre = new JPanel();
			pnPaqueteNombre.setLayout(new GridLayout(0, 2, 0, 0));
			pnPaqueteNombre.add(getLblHotelGranVa());
			pnPaqueteNombre.add(getPnPaqueteAdultos());
		}
		return pnPaqueteNombre;
	}
	private JLabel getLblHotelGranVa() {
		if (lblHotelGranVa == null) {
			lblHotelGranVa = new JLabel(this.articuloPaquete.getPaquete().getDenominacion() +" ("+Principal.findParque(this.articuloPaquete.getPaquete().getCodigoParque()).getLocalidad()+")");
			lblHotelGranVa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHotelGranVa.setHorizontalTextPosition(SwingConstants.CENTER);
			lblHotelGranVa.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHotelGranVa;
	}
	private JPanel getPnPaqueteAdultos() {
		if (pnPaqueteAdultos == null) {
			pnPaqueteAdultos = new JPanel();
			pnPaqueteAdultos.setLayout(new GridLayout(0, 2, 20, 0));
			pnPaqueteAdultos.add(getPnNAdultos());
			pnPaqueteAdultos.add(getPnNNi�os());
		}
		return pnPaqueteAdultos;
	}
	private JLabel getLblAdultos() {
		if (lblAdultos == null) {
			lblAdultos = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.adultos"));
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
			spinnerAdultos.setValue((Integer)this.articuloPaquete.getNumAdultos());
			spinnerAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinnerAdultos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (!cesta){
						articuloPaquete.actualizarNumPersonas((Integer)spinnerAdultos.getValue(), articuloPaquete.getNumNi�os());
					}
					else{
						vp.getReserva().getCarrito().actualizarNumeroPersonas(articuloPaquete, (Integer)spinnerAdultos.getValue(), articuloPaquete.getNumNi�os());
					}
					getLblEurosAdultosPaquete().setText(Principal.NF.format(articuloPaquete.getPrecioTotalAdultos()));
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return spinnerAdultos;
	}
	private JLabel getLblNios() {
		if (lblNios == null) {
			lblNios = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.crios"));
			lblNios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblNios;
	}
	private JSpinner getSpinner_1() {
		if (spinnerNi�os == null) {
			spinnerNi�os = new JSpinner();
			spinnerNi�os.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			spinnerNi�os.setBorder(new LineBorder(new Color(70, 130, 180)));
			spinnerNi�os.setModel(new SpinnerNumberModel(0, 0, 99, 1));
			spinnerNi�os.setValue((Integer)this.articuloPaquete.getNumNi�os());
			spinnerNi�os.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinnerNi�os.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (!cesta){
						articuloPaquete.actualizarNumPersonas(articuloPaquete.getNumAdultos(), (Integer)spinnerNi�os.getValue());
					}
					else{
						vp.getReserva().getCarrito().actualizarNumeroPersonas(articuloPaquete, articuloPaquete.getNumAdultos(), (Integer)spinnerNi�os.getValue());
					}
					getLblEurosNi�osPaquete().setText(Principal.NF.format(articuloPaquete.getPrecioTotalNi�os()));
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return spinnerNi�os;
	}
	private JPanel getPnPaqueteFechas() {
		if (pnPaqueteFechas == null) {
			pnPaqueteFechas = new JPanel();
			pnPaqueteFechas.setLayout(new GridLayout(0, 3, 0, 0));
			pnPaqueteFechas.add(getPnPaqueteFechaLlegada());
			pnPaqueteFechas.add(getPnDiasPaquete());
			pnPaqueteFechas.add(getPnPreciosPaquete());
		}
		return pnPaqueteFechas;
	}
	
	private JPanel getPnPaqueteFechaLlegada() {
		if (pnPaqueteFechaLlegada == null) {
			pnPaqueteFechaLlegada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPaqueteFechaLlegada.getLayout();
			flowLayout.setVgap(23);
			pnPaqueteFechaLlegada.add(getLblPaqueteLlegada());
			pnPaqueteFechaLlegada.add(getpaqueteCalendarLlegada());
		}
		return pnPaqueteFechaLlegada;
	}
	
	private JLabel getLblPaqueteLlegada() {
		if (lblPaqueteLlegada == null) {
			lblPaqueteLlegada = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.llegada"));
			lblPaqueteLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblPaqueteLlegada;
	}
	
	private JDateChooser getpaqueteCalendarLlegada(){
		if (paquetecalendarLlegada == null){
			paquetecalendarLlegada= new JDateChooser();
			paquetecalendarLlegada.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			paquetecalendarLlegada.setBorder(null);
			paquetecalendarLlegada.getCalendarButton().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_calendario.png")));
			paquetecalendarLlegada.setIgnoreRepaint(true);
			paquetecalendarLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
			paquetecalendarLlegada.setPreferredSize(new Dimension(160, 35));
			paquetecalendarLlegada.setMinSelectableDate(Principal.fechaSinHoras(new Date()));
			paquetecalendarLlegada.setDate(this.articuloPaquete.getFechaInicio());
			paquetecalendarLlegada.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (getpaqueteCalendarLlegada()!=null){
						Calendar calendar= Calendar.getInstance();
						calendar.setTime(getpaqueteCalendarLlegada().getDate());
						calendar.add(Calendar.DAY_OF_YEAR, articuloPaquete.getPaquete().getDuracion());
						articuloPaquete.setNuevasFechas(getpaqueteCalendarLlegada().getDate(), calendar.getTime());
					}
					else{
						getpaqueteCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
					}
				}
			});
			
		}
		return paquetecalendarLlegada;
	}
	private JPanel getPnDiasPaquete() {
		if (pnDiasPaquete == null) {
			pnDiasPaquete = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnDiasPaquete.getLayout();
			flowLayout.setVgap(30);
			pnDiasPaquete.add(getLblDas());
		}
		return pnDiasPaquete;
	}
	private JLabel getLblDas() {
		if (lblDas == null) {
			lblDas = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.duracion")+" " + this.articuloPaquete.getPaquete().getDuracion() + " "+Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.dias"));
			lblDas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblDas;
	}
	
	private JPanel getPnPreciosPaquete() {
		if (pnPreciosPaquete == null) {
			pnPreciosPaquete = new JPanel();
			pnPreciosPaquete.setLayout(new GridLayout(3, 0, 0, 0));
			pnPreciosPaquete.add(getPnPrecioAdultosPaquete());
			pnPreciosPaquete.add(getPnPrecioNi�osPaquete());
			pnPreciosPaquete.add(getPnPrecioTotalPaquete());
		}
		return pnPreciosPaquete;
	}
	private JPanel getPnPrecioAdultosPaquete() {
		if (pnPrecioAdultosPaquete == null) {
			pnPrecioAdultosPaquete = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioAdultosPaquete.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioAdultosPaquete.add(getLblPrecioAdultos());
			pnPrecioAdultosPaquete.add(getLblEurosAdultosPaquete());
		}
		return pnPrecioAdultosPaquete;
	}
	private JLabel getLblPrecioAdultos() {
		if (lblPrecioAdultos == null) {
			lblPrecioAdultos = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.precioAdultos"));
			lblPrecioAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblPrecioAdultos;
	}
	private JLabel getLblEurosAdultosPaquete() {
		if (lblEurosAdultosPaquete == null) {
			lblEurosAdultosPaquete = new JLabel(Principal.NF.format(this.articuloPaquete.getPrecioTotalAdultos()));
			lblEurosAdultosPaquete.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblEurosAdultosPaquete;
	}
	private JPanel getPnPrecioNi�osPaquete() {
		if (pnPrecioNi�osPaquete == null) {
			pnPrecioNi�osPaquete = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioNi�osPaquete.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioNi�osPaquete.add(getLblPrecioNios());
			pnPrecioNi�osPaquete.add(getLblEurosNi�osPaquete());
		}
		return pnPrecioNi�osPaquete;
	}
	private JLabel getLblPrecioNios() {
		if (lblPrecioNios == null) {
			lblPrecioNios = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.precioCrios"));
			lblPrecioNios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblPrecioNios;
	}
	private JLabel getLblEurosNi�osPaquete() {
		if (lblEurosNi�osPaquete == null) {
			lblEurosNi�osPaquete = new JLabel(Principal.NF.format(this.articuloPaquete.getPrecioTotalNi�os()));
			lblEurosNi�osPaquete.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblEurosNi�osPaquete;
	}
	private JPanel getPnPrecioTotalPaquete() {
		if (pnPrecioTotalPaquete == null) {
			pnPrecioTotalPaquete = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioTotalPaquete.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioTotalPaquete.add(getLblTotalPaquete());
			pnPrecioTotalPaquete.add(getLblTotalPaqueteEuros());
		}
		return pnPrecioTotalPaquete;
	}
	private JLabel getLblTotalPaquete() {
		if (lblTotalPaquete == null) {
			lblTotalPaquete = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.totalMayus"));
			lblTotalPaquete.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblTotalPaquete;
	}
	private void setTextPrecioTotalEuros(){
		String descuento="";
		if (this.articuloPaquete.isOferta()){
			descuento=" ("+Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.antes")+" " + Principal.NF.format(Principal.formatoDecimales((this.articuloPaquete.getPrecioTotal()/(1-ParqueTematico.DESCUENTO_PARQUE))))+")";
		}
		getLblTotalPaqueteEuros().setText(Principal.NF.format(this.articuloPaquete.getPrecioTotal())+descuento);
	}
	private JLabel getLblTotalPaqueteEuros() {
		if (lblTotalPaqueteEuros == null) {
			lblTotalPaqueteEuros = new JLabel();
			this.setTextPrecioTotalEuros();
			lblTotalPaqueteEuros.setForeground(new Color(0, 0, 128));
			lblTotalPaqueteEuros.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblTotalPaqueteEuros;
	}
	private JPanel getPnFinalPaquete() {
		if (pnFinalPaquete == null) {
			pnFinalPaquete = new JPanel();
			pnFinalPaquete.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
			pnFinalPaquete.add(getBtnMasInformacion());
			if (!cesta){
				pnFinalPaquete.add(getBtnAadirALa());
			}
			else{
				pnFinalPaquete.add(getBtnEliminarDeLaCesta());
			}
		}
		return pnFinalPaquete;
	}
	private JButton getBtnMasInformacion() {
		if (btnMasInformacion == null) {
			btnMasInformacion = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.masInformacion"));
			btnMasInformacion.setPreferredSize(new Dimension(175, 35));
			btnMasInformacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnMasInformacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnMasInformacion.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnMasInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaMasInformacion ventana= new VentanaMasInformacion(articuloPaquete.getPaquete().getImagen(), articuloPaquete, vp);
					ventana.setVisible(true);
				}
			});
		}
		return btnMasInformacion;
	}
	
	private void mensajeErrorA�adirCarro(){
		Icon icon= new ImageIcon(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		JOptionPane.showMessageDialog(this.getPnPaquete(), Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.fechaLlegadaNoValida"), Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.errorSumarPedido"), JOptionPane.ERROR_MESSAGE, icon);
		this.getpaqueteCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
	}
	
	private JButton getBtnAadirALa() {
		if (btnAadirALa == null) {
			btnAadirALa = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.sumarAlaCesta"));
			btnAadirALa.setPreferredSize(new Dimension(175, 35));
			btnAadirALa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnAadirALa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAadirALa.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnAadirALa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int valorAdultos= (Integer)getSpinnerAdultos().getValue();
					int valorNi�os= (Integer)getSpinner_1().getValue();
					int difDias= 0;
					if (getpaqueteCalendarLlegada().getDate()!=null){
						difDias= Principal.diferenciaEnDias(getpaqueteCalendarLlegada().getDate(), Principal.fechaSinHoras(new Date()));
					}
					if (valorAdultos > 0 && valorNi�os >= 0 && getpaqueteCalendarLlegada().getDate()!=null && difDias >= 0){
						vp.getReserva().getCarrito().a�adir(articuloPaquete);
						actualizarCantidadLblCarrito();
						nuevoArticuloPaquete();
					}
					else{
						mensajeErrorA�adirCarro();
					}
				}
			});
		}
		return btnAadirALa;
	}
	
	private JButton getBtnEliminarDeLaCesta() {
		if (btnEliminarDeLaCesta == null) {
			btnEliminarDeLaCesta = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloquePaq.eliminarArticulo"));
			btnEliminarDeLaCesta.setPreferredSize(new Dimension(175, 35));
			btnEliminarDeLaCesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnEliminarDeLaCesta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnEliminarDeLaCesta.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnEliminarDeLaCesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					vp.getReserva().getCarrito().eliminar(articuloPaquete);
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
