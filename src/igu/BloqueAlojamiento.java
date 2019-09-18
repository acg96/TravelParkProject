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

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logica.Alojamiento;
import logica.Alojamiento.Tipo;
import logica.ArticuloAlojamiento;
import logica.ParqueTematico;
import logica.Principal;

import com.toedter.calendar.JDateChooser;

public class BloqueAlojamiento {
	private JPanel pnAlojamiento;
	private JLabel lblPack_1;
	private JPanel pnTituloAlojamiento;
	private JPanel pnAlojamientoNombre;
	private JLabel lblHotelGranVa;
	private JPanel pnAlojamientoAdultos;
	private JLabel lblAdultos;
	private JSpinner spinnerAdultos;
	private JLabel lblCosteDesayuno;
	private JPanel pnAlojamientoFechas;
	private JPanel pnAlojamientoFechaLlegada;
	private JLabel lblAlojamientoLlegada;
	private JDateChooser alojamientocalendarLlegada;
	private JPanel pnDiasAlojamiento;
	private JLabel lblDas;
	private JPanel pnPreciosAlojamiento;
	private JPanel pnPrecioAdultosAlojamiento;
	private JLabel lblPrecioAdultos;
	private JLabel lblEurosAdultosAlojamiento;
	private JPanel pnDesayuno;
	private JPanel pnPrecioTotalAlojamiento;
	private JLabel lblTotalAlojamiento;
	private JLabel lblTotalAlojamientoEuros;
	private JPanel pnFinalAlojamiento;
	private JButton btnMasInformacion;
	private JButton btnAadirALa;
	private JSpinner spinnerDiasAlojamiento;
	private JLabel lblEurosDesayuno;
	private JButton btnEliminarDeLaCesta;
	
	private JPanel pnDesayunoCheck;
	private JLabel lblSiDesayuno;
	private JLabel lblNoDesayuno;
	private JRadioButton rButtonSi;
	private JRadioButton rButtonNo;
	private JLabel lblPreguntaDesayuno;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JPanel pnNAdultos;
	private VentanaPrincipal vp;
	private boolean cesta;
	
	private ArticuloAlojamiento articuloAlojamiento;
	
	public BloqueAlojamiento(Date fechaLlegada, Date fechaSalida, int numPersonas, Alojamiento alojamiento, VentanaPrincipal vp){
		int dif= Principal.diferenciaEnDias(fechaLlegada, fechaSalida);
		if (dif == 0){
			Calendar calendar= Calendar.getInstance();
			calendar.setTime(fechaSalida);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			fechaSalida= calendar.getTime();
		}
		this.articuloAlojamiento= new ArticuloAlojamiento(fechaLlegada, fechaSalida, alojamiento, numPersonas, true);
		this.vp= vp;
	}
	
	public BloqueAlojamiento(ArticuloAlojamiento articuloAlojamiento, VentanaPrincipal vp, boolean cesta){
		this.articuloAlojamiento= articuloAlojamiento;
		this.vp= vp;
		this.cesta= cesta;
	}
	
	private void nuevoArticuloAlojamiento(){
		articuloAlojamiento= new ArticuloAlojamiento(articuloAlojamiento.getFechaInicio(), articuloAlojamiento.getFechaFin(), articuloAlojamiento.getAlojamiento(), articuloAlojamiento.getNumPersonas(), articuloAlojamiento.getDesayuno());
	}
	
	public ArticuloAlojamiento getArticuloAlojamiento(){
		return this.articuloAlojamiento;
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
	
	
	public JPanel getPnAlojamiento() {
		if (pnAlojamiento == null) {
			pnAlojamiento = new JPanel();
			pnAlojamiento.setBorder(new LineBorder(new Color(70, 130, 180), 1, true));
			pnAlojamiento.setBackground(new Color(255, 255, 255));
			pnAlojamiento.setLayout(new GridLayout(4, 0, 0, 5));
			pnAlojamiento.add(getPnTituloAlojamiento());
			pnAlojamiento.add(getPnAlojamientoNombre());
			pnAlojamiento.add(getPnAlojamientoFechas());
			pnAlojamiento.add(getPnFinalAlojamiento());
		}
		return pnAlojamiento;
	}
	private JLabel getLblPack_1() {
		if (lblPack_1 == null) {
			String titulo= Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.alojamiento");
			if (this.articuloAlojamiento.isOferta()){
				titulo+= "  "+Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.descuento")+" -"+(ParqueTematico.DESCUENTO_PARQUE*100)+"%";
			}
			lblPack_1 = new JLabel(titulo);
			lblPack_1.setBackground(new Color(70, 130, 180));
			lblPack_1.setForeground(new Color(255, 255, 255));
			if (this.articuloAlojamiento.isOferta()){
				lblPack_1.setForeground(new Color(0, 240,50));
			}
			lblPack_1.setFont(new Font("Stencil Std", Font.BOLD, 25));
			lblPack_1.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPack_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPack_1;
	}
	private JPanel getPnTituloAlojamiento() {
		if (pnTituloAlojamiento == null) {
			pnTituloAlojamiento = new JPanel();
			pnTituloAlojamiento.setBackground(new Color(70, 130, 180));
			pnTituloAlojamiento.add(getLblPack_1());
		}
		return pnTituloAlojamiento;
	}
	private JPanel getPnAlojamientoNombre() {
		if (pnAlojamientoNombre == null) {
			pnAlojamientoNombre = new JPanel();
			pnAlojamientoNombre.setLayout(new GridLayout(0, 2, 0, 0));
			pnAlojamientoNombre.add(getLblHotelGranVa());
			pnAlojamientoNombre.add(getPnAlojamientoAdultos());
		}
		return pnAlojamientoNombre;
	}
	private JLabel getLblHotelGranVa() {
		if (lblHotelGranVa == null) {
			String tipo= this.articuloAlojamiento.getAlojamiento().getDenominacion();
			//if (this.articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				tipo+= " " + this.articuloAlojamiento.getAlojamiento().getCategoria() + " "+Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.estrellas");
			//}
			tipo+= " ("+Principal.findParque(this.articuloAlojamiento.getAlojamiento().getCodigoParque()).getLocalidad()+")";
			lblHotelGranVa = new JLabel(tipo);
			lblHotelGranVa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHotelGranVa.setHorizontalTextPosition(SwingConstants.CENTER);
			lblHotelGranVa.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHotelGranVa;
	}
	private JPanel getPnAlojamientoAdultos() {
		if (pnAlojamientoAdultos == null) {
			pnAlojamientoAdultos = new JPanel();
			int tamPanel= 1;
			if (articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				tamPanel= 2;
			}
			pnAlojamientoAdultos.setLayout(new GridLayout(0, tamPanel, 20, 0));
			pnAlojamientoAdultos.add(getPnNAdultos());
			if (articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				pnAlojamientoAdultos.add(getPnDesayunoCheck());
			}
		}
		return pnAlojamientoAdultos;
	}
	
	private JPanel getPnDesayunoCheck() {
		if (pnDesayunoCheck == null) {
			pnDesayunoCheck = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnDesayunoCheck.getLayout();
			flowLayout.setAlignment(FlowLayout.CENTER);
			flowLayout.setVgap(30);
			pnDesayunoCheck.add(getLblPreguntaDesayuno());
			pnDesayunoCheck.add(getLblSiDesayuno());
			pnDesayunoCheck.add(getRButtonSi());
			pnDesayunoCheck.add(getLblNoDesayuno());
			pnDesayunoCheck.add(getRButtonNo());
		}
		return pnDesayunoCheck;
	}
	
	private JLabel getLblPreguntaDesayuno() {
		if (lblPreguntaDesayuno == null) {
			lblPreguntaDesayuno = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.desayunoPregunta"));
			lblPreguntaDesayuno.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPreguntaDesayuno.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPreguntaDesayuno.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPreguntaDesayuno;
	}
	
	private JLabel getLblSiDesayuno() {
		if (lblSiDesayuno == null) {
			lblSiDesayuno = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.si"));
			lblSiDesayuno.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSiDesayuno.setHorizontalTextPosition(SwingConstants.CENTER);
			lblSiDesayuno.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSiDesayuno;
	}
	
	private JLabel getLblNoDesayuno() {
		if (lblNoDesayuno == null) {
			lblNoDesayuno = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.no"));
			lblNoDesayuno.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNoDesayuno.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNoDesayuno.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNoDesayuno;
	}
	
	private JRadioButton getRButtonSi() {
		if (rButtonSi == null) {
			rButtonSi = new JRadioButton("");
			rButtonSi.setPreferredSize(new Dimension(31, 31));
			rButtonSi.setHorizontalTextPosition(SwingConstants.CENTER);
			rButtonSi.setHorizontalAlignment(SwingConstants.CENTER);
			rButtonSi.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rButtonSi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rButtonSi.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			rButtonSi.setSelected(true);
			buttonGroup.add(rButtonSi);
			rButtonSi.setBackground(new Color(255, 255, 255));
			rButtonSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!cesta){
						articuloAlojamiento.añadirDesayuno();
					}
					else{
						vp.getReserva().getCarrito().añadirDesayuno(articuloAlojamiento);
					}
					getLblEurosDesayuno().setText(Principal.NF.format(articuloAlojamiento.getPrecioTotalDesayuno()));
					getLblCosteDesayuno().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.desayuno"));
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return rButtonSi;
	}
	
	private JRadioButton getRButtonNo() {
		if (rButtonNo == null) {
			rButtonNo = new JRadioButton("");
			rButtonNo.setPreferredSize(new Dimension(31, 31));
			rButtonNo.setHorizontalTextPosition(SwingConstants.CENTER);
			rButtonNo.setHorizontalAlignment(SwingConstants.CENTER);
			rButtonNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rButtonNo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rButtonNo.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			buttonGroup.add(rButtonNo);
			if (cesta){
				if (!articuloAlojamiento.getDesayuno()){
					rButtonNo.setSelected(true);
				}
			}
			rButtonNo.setBackground(new Color(255, 255, 255));
			rButtonNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!cesta){
						articuloAlojamiento.quitarDesayuno();
					}
					else{
						vp.getReserva().getCarrito().quitarDesayuno(articuloAlojamiento);
					}
					getLblEurosDesayuno().setText("");
					getLblCosteDesayuno().setText("");
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return rButtonNo;
	}
	
	private JLabel getLblAdultos() {
		if (lblAdultos == null) {
			lblAdultos = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.nPersonas"));
			lblAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblAdultos;
	}
	private JSpinner getSpinnerAdultos() {
		if (spinnerAdultos == null) {
			spinnerAdultos = new JSpinner();
			spinnerAdultos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			spinnerAdultos.setBorder(new LineBorder(new Color(70, 130, 180)));
			int fin= 99;
			if (!articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				fin= articuloAlojamiento.getAlojamiento().getNumeroPlazas();
			}
			spinnerAdultos.setModel(new SpinnerNumberModel(1, 1, fin, 1));
			spinnerAdultos.setValue((Integer)this.articuloAlojamiento.getNumPersonas());
			spinnerAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinnerAdultos.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (!cesta){
						articuloAlojamiento.actualizarNumPersonas((Integer)spinnerAdultos.getValue(), 0);
					}
					else{
						vp.getReserva().getCarrito().actualizarNumeroPersonas(articuloAlojamiento, (Integer)spinnerAdultos.getValue(), 0);
					}
					getLblEurosAdultosAlojamiento().setText(Principal.NF.format(articuloAlojamiento.getPrecioTotalPersonas()));
					if (articuloAlojamiento.getDesayuno()){
						getLblEurosDesayuno().setText(Principal.NF.format(articuloAlojamiento.getPrecioTotalDesayuno()));
					}
					else{
						getLblEurosDesayuno().setText("");
					}
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return spinnerAdultos;
	}
	private JLabel getLblCosteDesayuno() {
		if (lblCosteDesayuno == null) {
			lblCosteDesayuno = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.desayuno"));
			if (!articuloAlojamiento.getDesayuno()){
				lblCosteDesayuno.setText("");
			}
			lblCosteDesayuno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblCosteDesayuno;
	}
	
	private JPanel getPnAlojamientoFechas() {
		if (pnAlojamientoFechas == null) {
			pnAlojamientoFechas = new JPanel();
			pnAlojamientoFechas.setLayout(new GridLayout(0, 3, 0, 0));
			pnAlojamientoFechas.add(getPnAlojamientoFechaLlegada());
			pnAlojamientoFechas.add(getPnDiasAlojamiento());
			pnAlojamientoFechas.add(getPnPreciosAlojamiento());
		}
		return pnAlojamientoFechas;
	}
	
	private JPanel getPnAlojamientoFechaLlegada() {
		if (pnAlojamientoFechaLlegada == null) {
			pnAlojamientoFechaLlegada = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnAlojamientoFechaLlegada.getLayout();
			flowLayout.setVgap(23);
			pnAlojamientoFechaLlegada.add(getLblAlojamientoLlegada());
			pnAlojamientoFechaLlegada.add(getAlojamientoCalendarLlegada());
		}
		return pnAlojamientoFechaLlegada;
	}
	
	private JLabel getLblAlojamientoLlegada() {
		if (lblAlojamientoLlegada == null) {
			lblAlojamientoLlegada = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.llegada"));
			lblAlojamientoLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblAlojamientoLlegada;
	}
	
	private JDateChooser getAlojamientoCalendarLlegada(){
		if (alojamientocalendarLlegada == null){
			alojamientocalendarLlegada= new JDateChooser();
			alojamientocalendarLlegada.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			alojamientocalendarLlegada.setBorder(null);
			alojamientocalendarLlegada.getCalendarButton().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_calendario.png")));
			alojamientocalendarLlegada.setIgnoreRepaint(true);
			alojamientocalendarLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
			alojamientocalendarLlegada.setPreferredSize(new Dimension(160, 35));
			alojamientocalendarLlegada.setMinSelectableDate(Principal.fechaSinHoras(new Date()));
			alojamientocalendarLlegada.setDate(this.articuloAlojamiento.getFechaInicio());
			alojamientocalendarLlegada.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					if (getAlojamientoCalendarLlegada()!=null){
						Calendar calendar= Calendar.getInstance();
						calendar.setTime(getAlojamientoCalendarLlegada().getDate());
						calendar.add(Calendar.DAY_OF_YEAR, (Integer)getSpinnerDiasAlojamiento().getValue());
						articuloAlojamiento.setNuevasFechas(getAlojamientoCalendarLlegada().getDate(), calendar.getTime());
					}
					else{
						getAlojamientoCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
					}
				}
			});
			
		}
		return alojamientocalendarLlegada;
	}
	
	private JSpinner getSpinnerDiasAlojamiento() {
		if (spinnerDiasAlojamiento == null) {
			spinnerDiasAlojamiento = new JSpinner();
			spinnerDiasAlojamiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			spinnerDiasAlojamiento.setBorder(new LineBorder(new Color(70, 130, 180)));
			spinnerDiasAlojamiento.setModel(new SpinnerNumberModel(1, 1, 99, 1));
			int restaDias= Principal.diferenciaEnDias(this.articuloAlojamiento.getFechaFin(), this.articuloAlojamiento.getFechaInicio());
			spinnerDiasAlojamiento.setValue(restaDias);
			spinnerDiasAlojamiento.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinnerDiasAlojamiento.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					Calendar calendar= Calendar.getInstance();
					calendar.setTime(articuloAlojamiento.getFechaInicio());
					calendar.add(Calendar.DAY_OF_YEAR, (Integer)getSpinnerDiasAlojamiento().getValue());
					if (!cesta){
						articuloAlojamiento.setNuevasFechas(articuloAlojamiento.getFechaInicio(), calendar.getTime());
					}
					else{
						vp.getReserva().getCarrito().actualizarFechas(articuloAlojamiento, articuloAlojamiento.getFechaInicio(), calendar.getTime());
					}
					getLblEurosAdultosAlojamiento().setText(Principal.NF.format(articuloAlojamiento.getPrecioTotalPersonas()));
					if (articuloAlojamiento.getDesayuno()){
						getLblEurosDesayuno().setText(Principal.NF.format(articuloAlojamiento.getPrecioTotalDesayuno()));
					}
					else{
						getLblEurosDesayuno().setText("");
					}
					setTextPrecioTotalEuros();
					vp.getVentanaCarro().getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.total")+": "+ Principal.NF.format(Principal.formatoDecimales(vp.getReserva().getImporteTotal())));
				}
			});
		}
		return spinnerDiasAlojamiento;
	}
	
	private JPanel getPnDiasAlojamiento() {
		if (pnDiasAlojamiento == null) {
			pnDiasAlojamiento = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnDiasAlojamiento.getLayout();
			flowLayout.setVgap(30);
			pnDiasAlojamiento.add(getLblDas());
			pnDiasAlojamiento.add(getSpinnerDiasAlojamiento());
			
		}
		return pnDiasAlojamiento;
	}
	private JLabel getLblDas() {
		if (lblDas == null) {
			lblDas = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.dias"));
			lblDas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblDas;
	}
	
	private JPanel getPnPreciosAlojamiento() {
		if (pnPreciosAlojamiento == null) {
			pnPreciosAlojamiento = new JPanel();
			pnPreciosAlojamiento.setLayout(new GridLayout(3, 0, 0, 0));
			pnPreciosAlojamiento.add(getPnPrecioAdultosAlojamiento());
			if (this.articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				pnPreciosAlojamiento.add(getPnDesayuno());
			}
			pnPreciosAlojamiento.add(getPnPrecioTotalAlojamiento());
		}
		return pnPreciosAlojamiento;
	}
	private JPanel getPnPrecioAdultosAlojamiento() {
		if (pnPrecioAdultosAlojamiento == null) {
			pnPrecioAdultosAlojamiento = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioAdultosAlojamiento.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioAdultosAlojamiento.add(getLblPrecioAdultos());
			if (this.articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				pnPrecioAdultosAlojamiento.add(getLblEurosAdultosAlojamiento());
			}
		}
		return pnPrecioAdultosAlojamiento;
	}
	private JLabel getLblPrecioAdultos() {
		if (lblPrecioAdultos == null) {
			String label= Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.precioAlojamiento");
			if (!this.articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
				label= "";
			}
			lblPrecioAdultos = new JLabel(label);
			lblPrecioAdultos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblPrecioAdultos;
	}
	private JLabel getLblEurosAdultosAlojamiento() {
		if (lblEurosAdultosAlojamiento == null) {
			lblEurosAdultosAlojamiento = new JLabel(Principal.NF.format(this.articuloAlojamiento.getPrecioTotalPersonas()));
			lblEurosAdultosAlojamiento.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblEurosAdultosAlojamiento;
	}
	private JPanel getPnDesayuno() {
		if (pnDesayuno == null) {
			pnDesayuno = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnDesayuno.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnDesayuno.add(getLblCosteDesayuno());
			pnDesayuno.add(getLblEurosDesayuno()); 
		}
		return pnDesayuno;
	}
	
	private JLabel getLblEurosDesayuno() {
		if (lblEurosDesayuno == null) {
			lblEurosDesayuno = new JLabel(Principal.NF.format(articuloAlojamiento.getPrecioTotalDesayuno()));
			if (!articuloAlojamiento.getDesayuno()){
				lblEurosDesayuno.setText("");
			}
			lblEurosDesayuno.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblEurosDesayuno;
	}

	private JPanel getPnPrecioTotalAlojamiento() {
		if (pnPrecioTotalAlojamiento == null) {
			pnPrecioTotalAlojamiento = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrecioTotalAlojamiento.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnPrecioTotalAlojamiento.add(getLblTotalAlojamiento());
			pnPrecioTotalAlojamiento.add(getLblTotalAlojamientoEuros());
		}
		return pnPrecioTotalAlojamiento;
	}
	private JLabel getLblTotalAlojamiento() {
		if (lblTotalAlojamiento == null) {
			lblTotalAlojamiento = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.totalMayus"));
			lblTotalAlojamiento.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblTotalAlojamiento;
	}
	private void setTextPrecioTotalEuros(){
		String descuento="";
		if (this.articuloAlojamiento.isOferta()){
			descuento=" ("+Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.antes")+" " + Principal.NF.format(Principal.formatoDecimales((this.articuloAlojamiento.getPrecioTotal()/(1-ParqueTematico.DESCUENTO_PARQUE))))+")";
		}
		getLblTotalAlojamientoEuros().setText(Principal.NF.format(this.articuloAlojamiento.getPrecioTotal())+descuento);
	}
	private JLabel getLblTotalAlojamientoEuros() {
		if (lblTotalAlojamientoEuros == null) {
			lblTotalAlojamientoEuros = new JLabel();
			this.setTextPrecioTotalEuros();
			lblTotalAlojamientoEuros.setForeground(new Color(0, 0, 128));
			lblTotalAlojamientoEuros.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblTotalAlojamientoEuros;
	}
	private JPanel getPnFinalAlojamiento() {
		if (pnFinalAlojamiento == null) {
			pnFinalAlojamiento = new JPanel();
			pnFinalAlojamiento.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
			pnFinalAlojamiento.add(getBtnMasInformacion());
			if (!cesta){
				pnFinalAlojamiento.add(getBtnAadirALa());
			}
			else{
				pnFinalAlojamiento.add(getBtnEliminarDeLaCesta());
			}
		}
		return pnFinalAlojamiento;
	}
	private JButton getBtnMasInformacion() {
		if (btnMasInformacion == null) {
			btnMasInformacion = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.masInformacion"));
			btnMasInformacion.setPreferredSize(new Dimension(175, 35));
			btnMasInformacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnMasInformacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnMasInformacion.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnMasInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaMasInformacion ventana= new VentanaMasInformacion(articuloAlojamiento.getAlojamiento().getImagen(), articuloAlojamiento, vp);
					ventana.setVisible(true);
				}
			});
		}
		return btnMasInformacion;
	}
	
	private void mensajeErrorAñadirCarro(){
		Icon icon= new ImageIcon(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		JOptionPane.showMessageDialog(this.getPnAlojamiento(), Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.fechaLlegadaNoValida"), Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.errorSumarPedido"), JOptionPane.ERROR_MESSAGE, icon);
		this.getAlojamientoCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
	}
	
	private JButton getBtnAadirALa() {
		if (btnAadirALa == null) {
			btnAadirALa = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.sumarAlaCesta"));
			btnAadirALa.setPreferredSize(new Dimension(175, 35));
			btnAadirALa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnAadirALa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAadirALa.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnAadirALa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int valorAdultos= (Integer)getSpinnerAdultos().getValue();
					int difDias= 0;
					if (getAlojamientoCalendarLlegada().getDate()!=null){
						difDias= Principal.diferenciaEnDias(getAlojamientoCalendarLlegada().getDate(), Principal.fechaSinHoras(new Date()));
					}
					if (valorAdultos > 0 && getAlojamientoCalendarLlegada().getDate()!=null && difDias >= 0){
						vp.getReserva().getCarrito().añadir(articuloAlojamiento);
						actualizarCantidadLblCarrito();
						nuevoArticuloAlojamiento();
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
			btnEliminarDeLaCesta = new JButton(Principal.LOCALIZACION_TEXTOS.getString("bloqueAloj.eliminarArticulo"));
			btnEliminarDeLaCesta.setPreferredSize(new Dimension(175, 35));
			btnEliminarDeLaCesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnEliminarDeLaCesta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnEliminarDeLaCesta.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnEliminarDeLaCesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					vp.getReserva().getCarrito().eliminar(articuloAlojamiento);
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
