package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import dat.CargarDatos;

import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import logica.Alojamiento;
import logica.Alojamiento.Tipo;
import logica.Entrada;
import logica.Paquete;
import logica.Principal;
import logica.Reserva;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextArea;


public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String[] CRITERIOS_ORDENACION_SINALOJ= {Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.masBarato"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mayorPrecio")};
	private static String[] CRITERIOS_ORDENACION_CONALOJ= {Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.masBarato"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mayorPrecio"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.masEstrellas"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.menosEstrellas"),Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.hotelesPrimero"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.apartahotelesPrimero"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.apartamentosPrimero")};
	private static String OPCION_PACK= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.packs");
	private static String OPCION_ALOJAMIENTOS= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.alojamientos");
	private static String OPCION_ENTRADAS= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.entradas");
	private static String OPCION_TODAS= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.todas");
	

	private JPanel pnPrincipal;
	private JPanel pnSuperior;
	private JLabel lbLogo;
	private JPanel pnCarrito;
	private JTextField txCarrito;
	private JPanel pnBordeCarrito;
	private JPanelRound pnBusqueda;
	private JDateChooser calendarLlegada;
	private JDateChooser calendarSalida;
	private JPanel pnFechasBusqueda;
	private JLabel lblLlegada;
	private JLabel lblSalida;
	private JPanel pnFechaLlegada;
	private JPanel pnFechaSalida;
	private JPanel pnNpersonas;
	private JLabel lblNPersonas;
	private JSpinner spinnerPersonas;
	private JPanel pnSeleccionar;
	private JPanel pnTodo;
	private JLabel lblPack;
	private JPanel pnAlojamiento;
	private JLabel lblAlojamiento;
	private JPanel pnEntrada;
	private JLabel lblEntradas;
	private JRadioButton rButtonPacks;
	private JRadioButton rButtonAlojamientos;
	private JRadioButton rButtonEntradas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroupMenu = new ButtonGroup();
	private JPanel pnPersonasLocalizacion;
	private JPanel pnLocalizacion;
	private JLabel lbLocalizacion;
	private JButton buttonCarrito;
	
	private DefaultComboBoxModel<String> localidades;
	private DefaultComboBoxModel<String> ordenacion;
	private JComboBox<String> cbLocalizacion;
	private JPanel pnBotones;
	private JButton btBuscar;
	private JButton btVaciar;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenu mnAyuda;
	private JMenuItem mntmContenido;
	private JMenuItem mntmSalir;
	private JMenuItem mntmAcercaDe;
	private JSeparator separator;
	private JMenuItem mntmNuevaReserva;
	private JSeparator separator_1;
	private JPanel pnPantalla2;
	private JPanel pnBusqueda2;
	private JPanel pnBusqueda2Superior;
	private JPanel pnBusqueda2Medio;
	private JPanel pnBusqueda2Final;
	private JButton btnModificarBsqueda;
	private JPanel pnPantalla2Inferior;
	private JPanel pnPantalla2Ordenacion;
	private JComboBox<String> cbOrdenacion;
	private JLabel lblOrdenarPor;
	private JScrollPane scpPantalla2;
	private JPanel pnPantalla2Viajes;
	private JPanel pnSuperiorReserva;
	
	private VentanaCarro ventanaCarro;
	private Reserva reserva;
	private JPanel pnInferiorReserva;
	private JPanel pnDatosReserva;
	private JPanel pnNombreReserva;
	private JLabel lblNombre;
	private JTextField txNombre;
	private JPanel pnApellidos;
	private JLabel lblApellidos;
	private JTextField txApellidos;
	private JPanel pnIdentificacion;
	private JLabel lblDninifnie;
	private JTextField txIdentificacion;
	private JPanel pnBtnConfirmarDatos;
	private JButton btnConfirmarDatos;
	private JScrollPane scrollPaneReserva;
	private JTextPane jTextPaneDescripcion;
	private JPanel pnBotonesReserva;
	private JLabel lblObservaciones;
	private JScrollPane scrollComentarios;
	private JTextArea txAreaComentarios;
	private JPanel pnInferiorBotonesReserva;
	private JButton btnVolverAlPedido;
	private JButton btnConfirmarReserva;
	private JButton btnSalir;
	private JPanel pnInferiorBotonesReservaConfirmada;
	
	private JMenu mnVentana;
	private JRadioButtonMenuItem chckbxmntmEspaol;
	private JRadioButtonMenuItem chckbxmntmIngls;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal.inicializarLocalizacion();
					CargarDatos.parseTodo();
					Principal.randomOfertaParque();
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		reserva= new Reserva();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		setTitle("Travel Park");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 1000);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		ventanaCarro= new VentanaCarro(this);
		getPnPrincipal().add(getPnSuperior(), BorderLayout.NORTH);
		getPnPrincipal().add(getPnBusqueda(), BorderLayout.CENTER);
		cargaAyuda();
	}
	
	private void cargaAyuda(){
	   URL hsURL;
	   HelpSet hs;
	    try{
		    File fichero = new File("help/ayuda.hs");
		    hsURL = fichero.toURI().toURL();
		    hs = new HelpSet(null, hsURL);
		}
	    catch (Exception e){
	      System.out.println("Ayuda no encontrada");
	      return;
	   }
	   HelpBroker hb = hs.createHelpBroker("MainWindow");
	   hb.enableHelpKey(getRootPane(),"introduccion", hs); //Arranca la ayuda pulsando sobre F1
	   hb.enableHelpOnButton(mntmContenido, "introduccion", hs); //Lo vincula al menu ayuda
	   hb.enableHelp(getBtnModificarBsqueda(), "modificar", hs); //Muestra esa parte de la ayuda cuando el foco está sobre ese componente
	   hb.enableHelp(getBtnConfirmarReserva(), "confirmar", hs);
	}
	
	private void cambiarIdioma(){
		buttonCarrito= null;
		pnPrincipal= null;
		pnSuperior= null;
		pnCarrito= null;
		pnBordeCarrito= null;
		pnBusqueda= null;
		calendarLlegada= null;
		calendarSalida= null;
		pnFechasBusqueda= null;
		lblLlegada= null;
		lblSalida= null;
		pnFechaLlegada= null;
		pnFechaSalida= null;
		pnNpersonas= null;
		lblNPersonas= null;
		pnSeleccionar= null;
		pnTodo= null;
		lblPack= null;
		pnAlojamiento= null;
		lblAlojamiento= null;
		pnEntrada= null;
		lblEntradas= null;
		rButtonPacks= null;
		rButtonAlojamientos= null;
		rButtonEntradas= null;
		pnPersonasLocalizacion= null;
		pnLocalizacion= null;
		lbLocalizacion= null;
		localidades= null;
		ordenacion= null;
		cbLocalizacion= null;
		pnBotones= null;
		btBuscar= null;
		btVaciar= null;
		menuBar= null;
		mnArchivo= null;
		mnAyuda= null;
		mntmContenido= null;
		mntmSalir= null;
		mntmAcercaDe= null;
		mntmNuevaReserva= null;
		pnPantalla2= null;
		pnBusqueda2= null;
		pnBusqueda2Superior= null;
		pnBusqueda2Medio= null;
		pnBusqueda2Final= null;
		btnModificarBsqueda= null;
		pnPantalla2Inferior= null;
		pnPantalla2Ordenacion= null;
		cbOrdenacion= null;
		lblOrdenarPor= null;
		scpPantalla2= null;
		pnPantalla2Viajes= null;
		pnSuperiorReserva= null;
		ventanaCarro= null;
		pnInferiorReserva= null;
		pnDatosReserva= null;
		pnNombreReserva= null;
		lblNombre= null;
		pnApellidos= null;
		lblApellidos= null;
		pnIdentificacion= null;
		lblDninifnie= null;
		pnBtnConfirmarDatos= null;
		btnConfirmarDatos= null;
		scrollPaneReserva= null;
		pnBotonesReserva= null;
		lblObservaciones= null;
		scrollComentarios= null;
		pnInferiorBotonesReserva= null;
		btnVolverAlPedido= null;
		btnConfirmarReserva= null;
		btnSalir= null;
		pnInferiorBotonesReservaConfirmada= null;
		mnVentana= null;
		jTextPaneDescripcion= null;
		getChckbxmntmEspaol().setText(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.spanish"));
		getChckbxmntmIngls().setText(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.ingles"));
		setJMenuBar(getMenuBar_1());
		ventanaCarro= new VentanaCarro(this);
		getPnPrincipal().add(getPnSuperior(), BorderLayout.NORTH);
		getPnPrincipal().add(getPnBusqueda(), BorderLayout.CENTER);
		cargaAyuda();
	}
	
	public static void changeStaticLocation(){
		CRITERIOS_ORDENACION_SINALOJ[0]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.masBarato");
		CRITERIOS_ORDENACION_SINALOJ[1]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mayorPrecio");
		CRITERIOS_ORDENACION_CONALOJ[0]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.masBarato");
		CRITERIOS_ORDENACION_CONALOJ[1]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mayorPrecio");
		CRITERIOS_ORDENACION_CONALOJ[2]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.masEstrellas");
		CRITERIOS_ORDENACION_CONALOJ[3]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.menosEstrellas");
		CRITERIOS_ORDENACION_CONALOJ[4]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.hotelesPrimero");
		CRITERIOS_ORDENACION_CONALOJ[5]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.apartahotelesPrimero");
		CRITERIOS_ORDENACION_CONALOJ[6]= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.apartamentosPrimero");
		OPCION_PACK= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.packs");
		OPCION_ALOJAMIENTOS= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.alojamientos");
		OPCION_ENTRADAS= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.entradas");
		OPCION_TODAS= Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.todas");
	}
	
	public Reserva getReserva(){
		return this.reserva;
	}
	
	private JPanel getPnPrincipal(){
		if (pnPrincipal == null){
			pnPrincipal = new JPanel();
			pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(pnPrincipal);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
		}
		return pnPrincipal;
	}
	
	private void desactivarVentana1(){
		this.setVisible(false);
		pnPrincipal= null;
		pnBusqueda= null;
		pnSuperior= null;
		pnInferiorReserva= null;
		getPnPrincipal().add(getPnSuperior(), BorderLayout.NORTH);
		getPnPrincipal().add(getPnPantalla2(), BorderLayout.CENTER);
		getMntmNuevaReserva().setEnabled(true);
		this.setVisible(true);
	}
	
	private void activarVentana1(){
		this.setVisible(false);
		resetVentana();
		reserva.resetReserva();
		getTxCarrito().setText("0");
		pnPrincipal= null;
		pnPantalla2= null;
		pnInferiorReserva= null;
		getPnPrincipal().add(getPnSuperior(), BorderLayout.NORTH);
		getPnPrincipal().add(getPnBusqueda(), BorderLayout.CENTER);
		getMntmNuevaReserva().setEnabled(false);
		getChckbxmntmEspaol().setEnabled(true);
		getChckbxmntmIngls().setEnabled(true);
		this.setVisible(true);
	}
	
	public void activarVentana3(){
		this.setVisible(false);
		pnPrincipal= null;
		pnPantalla2= null;
		getPnPrincipal().add(getPnSuperiorReserva(), BorderLayout.NORTH);
		getPnPrincipal().add(getPnInferiorReserva(), BorderLayout.CENTER);
		getTxApellidos().setEnabled(true);
		getTxNombre().setEnabled(true);
		getTxIdentificacion().setEnabled(true);
		getTxAreaComentarios().setEnabled(true);
		getTxAreaComentarios().setEditable(true);
		getBtnConfirmarDatos().setEnabled(true);
		getMntmNuevaReserva().setEnabled(true);
		if (reserva.getNombreCliente().equals("") || reserva.getApellidosCliente().equals("") || reserva.getIdentificacionCliente().equals("")){
			getBtnConfirmarReserva().setEnabled(false);
		}
		else{
			getBtnConfirmarReserva().setEnabled(true);
			getJTextPaneDescripcion().setText(reserva.verResumenReserva());
			getJTextPaneDescripcion().setCaretPosition(0);
		}
		this.setVisible(true);
	}
	
	public void activarVentana4(){
		this.setVisible(false);
		pnPrincipal= null;
		pnPantalla2= null;
		pnInferiorReserva= null;
		getChckbxmntmEspaol().setEnabled(false);
		getChckbxmntmIngls().setEnabled(false);
		getPnPrincipal().add(getPnSuperiorReserva(), BorderLayout.NORTH);
		getPnPrincipal().add(getPnInferiorReserva(), BorderLayout.CENTER);
		getMntmNuevaReserva().setEnabled(true);
		getBtnConfirmarDatos().setEnabled(false);
		getTxNombre().setEnabled(false);
		getTxApellidos().setEnabled(false);
		getTxIdentificacion().setEnabled(false);
		getTxAreaComentarios().setEnabled(false);
		getTxAreaComentarios().setEditable(false);
		if (reserva.getComentarios().trim().equals("")){
			getTxAreaComentarios().setText(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.noComentarios"));
		}
		getJTextPaneDescripcion().setText(reserva.toString());
		getJTextPaneDescripcion().setCaretPosition(0);
		this.setVisible(true);
	}

	private JDateChooser getCalendarLlegada(){
		if (calendarLlegada == null){
			calendarLlegada= new JDateChooser();
			getLblLlegada().setLabelFor(calendarLlegada.getCalendarButton());
			calendarLlegada.getCalendarButton().setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.llegada").charAt(0));
			calendarLlegada.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			calendarLlegada.setBorder(null);
			calendarLlegada.getCalendarButton().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_calendario.png")));
			calendarLlegada.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					checkFechaLlegada();
				}
			});
			
		
			calendarLlegada.setIgnoreRepaint(true);
			calendarLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
			calendarLlegada.setPreferredSize(new Dimension(160, 35));
			calendarLlegada.setMinSelectableDate(Principal.fechaSinHoras(new Date()));
			calendarLlegada.setDate(Principal.fechaSinHoras(new Date()));
			calendarLlegada.setBackground(Color.WHITE);
			
		}
		return calendarLlegada;
	}
	
	private void checkFechaLlegada(){
		if (getCalendarLlegada() != null){
			int difDias= 0;
			int difDiasActual= 0;
			if (getCalendarLlegada().getDate()!=null && getCalendarSalida().getDate()!=null){
				difDias= Principal.diferenciaEnDias(getCalendarLlegada().getDate(), getCalendarSalida().getDate());
				difDiasActual= Principal.diferenciaEnDias(getCalendarLlegada().getDate(), Principal.fechaSinHoras(new Date()));
			}
			if (getCalendarSalida() != null && difDiasActual >= 0){
				getCalendarSalida().setMinSelectableDate(getCalendarLlegada().getDate());
			}
			else{
				getCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
				getCalendarSalida().setMinSelectableDate(getCalendarLlegada().getDate());
			}
			if (getCalendarSalida() != null && getCalendarLlegada() !=null && getCalendarSalida().getDate()!=null && getCalendarLlegada().getDate()!=null && difDias > 0){
				getCalendarSalida().setDate(getCalendarLlegada().getDate());
			}
			if (getCalendarSalida() != null && getCalendarSalida().getDate() == null){
				getCalendarSalida().setDate(getCalendarLlegada().getDate());
			}
			changeStateRadioButtons();
		}
	}
	
	private void checkFechaSalida(){
		if (getCalendarSalida() != null && getCalendarLlegada() != null){
			if (getCalendarLlegada().getDate() == null){
				getCalendarLlegada().setDate(getCalendarSalida().getMinSelectableDate());
			}
			changeStateRadioButtons();
		}
	}
	
	private void changeStateRadioButtons(){
		int difDias= Principal.diferenciaEnDias(getCalendarSalida().getDate(), getCalendarLlegada().getDate());
		if (difDias == 0){
			this.getRButtonAlojamientos().setEnabled(false);
			this.getRButtonPacks().setEnabled(false);
			this.getRButtonEntradas().setSelected(true);
		}
		else{
			this.getRButtonAlojamientos().setEnabled(true);
			this.getRButtonPacks().setEnabled(true);
		}
	}
	
	private JDateChooser getCalendarSalida(){
		if (calendarSalida == null){
			calendarSalida= new JDateChooser();
			calendarSalida.getCalendarButton().setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.salida").charAt(0));
			getLblSalida().setLabelFor(calendarSalida.getCalendarButton());
			calendarSalida.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			calendarSalida.setBorder(null);
			calendarSalida.getCalendarButton().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo_calendario.png")));
			calendarSalida.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					checkFechaSalida();
				}
			});
			calendarSalida.setIgnoreRepaint(true);
			calendarSalida.setFont(new Font("Tahoma", Font.PLAIN, 20));
			calendarSalida.setPreferredSize(new Dimension(160, 35));
			calendarSalida.setMinSelectableDate(getCalendarLlegada().getDate());
			calendarSalida.setDate(Principal.fechaSinHoras(new Date()));
			calendarSalida.setBackground(Color.WHITE);
		}
		return calendarSalida;
	}
	
	private JPanel getPnSuperior() {
		if (pnSuperior == null) {
			pnSuperior = new JPanel();
			pnSuperior.setLayout(new BoxLayout(pnSuperior, BoxLayout.X_AXIS));
			lbLogo=null;
			pnSuperior.add(getLbLogo());
			pnSuperior.add(getPnCarrito());
		}
		return pnSuperior;
	}
	
	private JPanel getPnSuperiorReserva() {
		if (pnSuperiorReserva == null) {
			pnSuperiorReserva = new JPanel();
			pnSuperiorReserva.setLayout(new BoxLayout(pnSuperiorReserva, BoxLayout.X_AXIS));
			pnSuperiorReserva.add(getLbLogo());
		}
		return pnSuperiorReserva;
	}
	
	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel("");
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logoPrin.png")));
		}
		return lbLogo;
	}
	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JPanel();
			pnCarrito.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			pnCarrito.add(getPnBordeCarrito());
		}
		return pnCarrito;
	}
	
	public VentanaCarro getVentanaCarro(){
		return this.ventanaCarro;
	}
	
	private JButton getButtonCarrito(){
		if (buttonCarrito == null){
			buttonCarrito= new JButton();
			buttonCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ventanaCarro.actualizarListadoCarro();
					setCentradaCarro();
					ventanaCarro.setVisible(true);
				}
			});
			buttonCarrito.setToolTipText(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.pedido"));
			buttonCarrito.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonCarrito.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/carrito4.png")));
			buttonCarrito.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttonCarrito.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.carrito").charAt(0));
		}
		return buttonCarrito;
	}
	
	private void setCentradaCarro(){
		ventanaCarro.setLocationRelativeTo(this);
	}
	
	public JTextField getTxCarrito() {
		if (txCarrito == null) {
			txCarrito = new JTextField();
			txCarrito.setEditable(false);
			txCarrito.setEnabled(false);
			txCarrito.setBorder(null);
			txCarrito.setAutoscrolls(false);
			txCarrito.setMargin(new Insets(0, 0, 0, 0));
			txCarrito.setHorizontalAlignment(SwingConstants.RIGHT);
			txCarrito.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			txCarrito.setFont(new Font("Sylfaen", Font.PLAIN, 55));
			txCarrito.setForeground(new Color(65, 105, 225));
			txCarrito.setDisabledTextColor(new Color(65, 105, 225));
			txCarrito.setText("0");
			txCarrito.setColumns(2);
		}
		return txCarrito;
	}
	private JPanel getPnBordeCarrito() {
		if (pnBordeCarrito == null) {
			pnBordeCarrito = new JPanel();
			pnBordeCarrito.setBorder(new LineBorder(new Color(65, 105, 225), 2, true));
			pnBordeCarrito.add(getButtonCarrito());
			pnBordeCarrito.add(getTxCarrito());
		}
		return pnBordeCarrito;
	}
	private JPanelRound getPnBusqueda() {
		if (pnBusqueda == null) {
			pnBusqueda = new JPanelRound();
			pnBusqueda.setBorder(null);
			pnBusqueda.setBackground(new Color(255, 255, 255));
			Border margin = new EmptyBorder(50, 50, 50, 50);
			pnBusqueda.setBorder(margin);
			pnBusqueda.setLayout(new GridLayout(4, 0, 0, 0));
			pnFechasBusqueda= null;
			pnPersonasLocalizacion= null;
			pnSeleccionar= null;
			pnBotones= null;
			pnBusqueda.add(getPnFechasBusqueda());
			pnBusqueda.add(getPnPersonasLocalizacion());
			pnBusqueda.add(getPnSeleccionar());
			pnBusqueda.add(getPnBotones());
			
		}
		return pnBusqueda;
	}
	
	private JPanel getPnFechasBusqueda() {
		if (pnFechasBusqueda == null) {
			pnFechasBusqueda = new JPanel();
			pnFechasBusqueda.setLayout(new GridLayout(0, 2, 0, 0));
			pnFechasBusqueda.add(getPnFechaLlegada());
			pnFechasBusqueda.add(getPnFechaSalida());
		}
		return pnFechasBusqueda;
	}
	private JLabel getLblLlegada() {
		if (lblLlegada == null) {
			lblLlegada = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.llegada"));
			lblLlegada.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.llegada").charAt(0));
			lblLlegada.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblLlegada;
	}
	
	private JLabel getLblSalida() {
		if (lblSalida == null) {
			lblSalida = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.salida"));
			lblSalida.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.salida").charAt(0));
			lblSalida.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblSalida;
	}
	
	private JPanel getPnFechaLlegada() {
		if (pnFechaLlegada == null) {
			pnFechaLlegada = new JPanel();
			pnFechaLlegada.setBackground(new Color(255, 255, 255));
			FlowLayout flowLayout = (FlowLayout) pnFechaLlegada.getLayout();
			flowLayout.setVgap(23);
			pnFechaLlegada.add(getLblLlegada());
			pnFechaLlegada.add(getCalendarLlegada());
		}
		return pnFechaLlegada;
	}
	private JPanel getPnFechaSalida() {
		if (pnFechaSalida == null) {
			pnFechaSalida = new JPanel();
			pnFechaSalida.setBackground(new Color(255, 255, 255));
			FlowLayout flowLayout = (FlowLayout) pnFechaSalida.getLayout();
			flowLayout.setVgap(23);
			pnFechaSalida.add(getLblSalida());
			pnFechaSalida.add(getCalendarSalida());
		}
		return pnFechaSalida;
	}
	private JPanel getPnNpersonas() {
		if (pnNpersonas == null) {
			pnNpersonas = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNpersonas.getLayout();
			flowLayout.setVgap(25);
			pnNpersonas.setBackground(new Color(255, 255, 255));
			pnNpersonas.add(getLblNPersonas());
			pnNpersonas.add(getSpinnerPersonas());
		}
		return pnNpersonas;
	}
	private JLabel getLblNPersonas() {
		if (lblNPersonas == null) {
			lblNPersonas = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.nPersonas"));
			lblNPersonas.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.nPersonas").charAt(0));
			lblNPersonas.setLabelFor(getSpinnerPersonas());
			lblNPersonas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblNPersonas;
	}
	private JSpinner getSpinnerPersonas() {
		if (spinnerPersonas == null) {
			spinnerPersonas = new JSpinner();
			spinnerPersonas.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
				}
			});
			spinnerPersonas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			spinnerPersonas.setBorder(new LineBorder(new Color(70, 130, 180)));
			spinnerPersonas.setModel(new SpinnerNumberModel(1, 1, 99, 1));
			spinnerPersonas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return spinnerPersonas;
	}
	private JPanel getPnSeleccionar() {
		if (pnSeleccionar == null) {
			pnSeleccionar = new JPanel();
			pnSeleccionar.setBackground(new Color(255, 255, 255));
			pnSeleccionar.setLayout(new GridLayout(0, 3, 0, 0));
			pnSeleccionar.add(getPnTodo());
			pnSeleccionar.add(getPnAlojamiento());
			pnSeleccionar.add(getPnEntrada());
		}
		return pnSeleccionar;
	}
	private JPanel getPnTodo() {
		if (pnTodo == null) {
			pnTodo = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnTodo.getLayout();
			flowLayout.setVgap(25);
			pnTodo.setBackground(new Color(255, 255, 255));
			pnTodo.add(getLblPack());
			pnTodo.add(getRButtonPacks());
		}
		return pnTodo;
	}
	private JLabel getLblPack() {
		if (lblPack == null) {
			lblPack = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.packs"));
			lblPack.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.packs").charAt(0));
			lblPack.setLabelFor(getRButtonPacks());
			lblPack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblPack;
	}
	private JPanel getPnAlojamiento() {
		if (pnAlojamiento == null) {
			pnAlojamiento = new JPanel();
			pnAlojamiento.setBackground(new Color(255, 255, 255));
			FlowLayout flowLayout = (FlowLayout) pnAlojamiento.getLayout();
			flowLayout.setVgap(25);
			pnAlojamiento.add(getLblAlojamiento());
			pnAlojamiento.add(getRButtonAlojamientos());
		}
		return pnAlojamiento;
	}
	private JLabel getLblAlojamiento() {
		if (lblAlojamiento == null) {
			lblAlojamiento = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.alojamientos"));
			lblAlojamiento.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.alojamiento").charAt(0));
			lblAlojamiento.setLabelFor(getRButtonAlojamientos());
			lblAlojamiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblAlojamiento;
	}
	private JPanel getPnEntrada() {
		if (pnEntrada == null) {
			pnEntrada = new JPanel();
			pnEntrada.setBackground(new Color(255, 255, 255));
			FlowLayout flowLayout = (FlowLayout) pnEntrada.getLayout();
			flowLayout.setVgap(25);
			pnEntrada.add(getLblEntradas());
			pnEntrada.add(getRButtonEntradas());
		}
		return pnEntrada;
	}
	private JLabel getLblEntradas() {
		if (lblEntradas == null) {
			lblEntradas = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.entradas"));
			lblEntradas.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.entradas").charAt(0));
			lblEntradas.setLabelFor(getRButtonEntradas());
			lblEntradas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblEntradas;
	}
	private JRadioButton getRButtonPacks() {
		if (rButtonPacks == null) {
			rButtonPacks = new JRadioButton("");
			rButtonPacks.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.packs").charAt(0));
			rButtonPacks.setPreferredSize(new Dimension(31, 31));
			rButtonPacks.setHorizontalTextPosition(SwingConstants.CENTER);
			rButtonPacks.setHorizontalAlignment(SwingConstants.CENTER);
			rButtonPacks.setFont(new Font("Tahoma", Font.PLAIN, 20));
			rButtonPacks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rButtonPacks.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			buttonGroup.add(rButtonPacks);
			rButtonPacks.setBackground(new Color(255, 255, 255));
			rButtonPacks.setSelected(true);
		}
		return rButtonPacks;
	}
	private JRadioButton getRButtonAlojamientos() {
		if (rButtonAlojamientos == null) {
			rButtonAlojamientos = new JRadioButton("");
			rButtonAlojamientos.setPreferredSize(new Dimension(31, 31));
			rButtonAlojamientos.setHorizontalTextPosition(SwingConstants.CENTER);
			rButtonAlojamientos.setHorizontalAlignment(SwingConstants.CENTER);
			rButtonAlojamientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
			rButtonAlojamientos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rButtonAlojamientos.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			buttonGroup.add(rButtonAlojamientos);
			rButtonAlojamientos.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.alojamiento").charAt(0));
			rButtonAlojamientos.setBackground(new Color(255, 255, 255));
		}
		return rButtonAlojamientos;
	}
	private JRadioButton getRButtonEntradas() {
		if (rButtonEntradas == null) {
			rButtonEntradas = new JRadioButton("");
			rButtonEntradas.setPreferredSize(new Dimension(31, 31));
			rButtonEntradas.setHorizontalTextPosition(SwingConstants.CENTER);
			rButtonEntradas.setHorizontalAlignment(SwingConstants.CENTER);
			rButtonEntradas.setFont(new Font("Tahoma", Font.PLAIN, 20));
			rButtonEntradas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			rButtonEntradas.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			buttonGroup.add(rButtonEntradas);
			rButtonEntradas.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.entradas").charAt(0));
			rButtonEntradas.setBackground(new Color(255, 255, 255));
		}
		return rButtonEntradas;
	}
	private JPanel getPnPersonasLocalizacion() {
		if (pnPersonasLocalizacion == null) {
			pnPersonasLocalizacion = new JPanel();
			pnPersonasLocalizacion.setBackground(new Color(255, 255, 255));
			pnPersonasLocalizacion.setLayout(new GridLayout(0, 2, 0, 0));
			pnPersonasLocalizacion.add(getPnNpersonas());
			pnPersonasLocalizacion.add(getPnLocalizacion());
		}
		return pnPersonasLocalizacion;
	}
	private JPanel getPnLocalizacion() {
		if (pnLocalizacion == null) {
			pnLocalizacion = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnLocalizacion.getLayout();
			flowLayout.setVgap(25);
			pnLocalizacion.setBackground(new Color(255, 255, 255));
			pnLocalizacion.add(getLbLocalizacion());
			pnLocalizacion.add(getCbLocalizacion());
		}
		return pnLocalizacion;
	}
	private JLabel getLbLocalizacion() {
		if (lbLocalizacion == null) {
			lbLocalizacion = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.localizacion"));
			lbLocalizacion.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.localizacion").charAt(0));
			lbLocalizacion.setLabelFor(getCbLocalizacion());
			lbLocalizacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lbLocalizacion;
	}
	
	private void cargarLocalidades(){
		if (this.localidades == null) this.localidades= new DefaultComboBoxModel<>();
		this.localidades.addElement(OPCION_TODAS);
		for (String i : Principal.LOCALIDADES){
			this.localidades.addElement(i);
		}
	}
	
	private JComboBox<String> getCbLocalizacion() {
		if (cbLocalizacion == null) {
			cargarLocalidades();
			cbLocalizacion = new JComboBox<>(localidades);
			cbLocalizacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cbLocalizacion.setSelectedIndex(0);
			cbLocalizacion.setMaximumRowCount(6);
			cbLocalizacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			cbLocalizacion.setPreferredSize(new Dimension(150, 30));
			cbLocalizacion.setBorder(null);
		}
		return cbLocalizacion;
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBackground(new Color(255, 255, 255));
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setVgap(25);
			pnBotones.add(getBtBuscar());
			pnBotones.add(getBtVaciar());
		}
		return pnBotones;
	}
	private JButton getBtBuscar() {
		if (btBuscar == null) {
			btBuscar = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.buscar"));
			btBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getBtnModificarBsqueda().doClick();
					desactivarVentana1();
				}
			});
			btBuscar.setBorder(new LineBorder(new Color(70, 130, 180)));
			btBuscar.setPreferredSize(new Dimension(100, 50));
			btBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBuscar.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.buscar").charAt(0));
			btBuscar.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		return btBuscar;
	}
	private JButton getBtVaciar() {
		if (btVaciar == null) {
			btVaciar = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.vaciar"));
			btVaciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					resetVentana();
				}
			});
			btVaciar.setPreferredSize(new Dimension(100, 50));
			btVaciar.setBorder(new LineBorder(new Color(70, 130, 180)));
			btVaciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btVaciar.setFont(new Font("Tahoma", Font.BOLD, 20));
			btVaciar.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.vaciar").charAt(0));
		}
		return btVaciar;
	}
	
	private void resetVentana(){
		this.getCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
		this.getCalendarLlegada().setMinSelectableDate(Principal.fechaSinHoras(new Date()));
		this.getCalendarSalida().setDate(Principal.fechaSinHoras(new Date()));
		this.getCalendarSalida().setMinSelectableDate(Principal.fechaSinHoras(new Date()));
		this.getSpinnerPersonas().setValue(1);
		this.getCbLocalizacion().setSelectedIndex(0);
		this.getRButtonEntradas().setSelected(true);
		this.getRButtonAlojamientos().setEnabled(false);
		this.getRButtonPacks().setEnabled(false);
		this.getTxCarrito().setText("0");
		this.getCbOrdenacion().setSelectedIndex(0);
		this.getTxApellidos().setText("");
		this.getTxNombre().setText("");
		this.getTxIdentificacion().setText("");
		this.jTextPaneDescripcion= null;
		this.getTxAreaComentarios().setText("");
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnArchivo());
			menuBar.add(getMnVentana());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnArchivo() {
		if (mnArchivo == null) {
			mnArchivo = new JMenu(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.viajes"));
			mnArchivo.setFont(new Font("Segoe UI", Font.PLAIN, 21));
			mnArchivo.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuViajes").charAt(0));
			mnArchivo.add(getMntmNuevaReserva());
			mnArchivo.add(getSeparator_1());
			mnArchivo.add(getMntmSalir());
		}
		return mnArchivo;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.ayuda"));
			mnAyuda.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuAyuda").charAt(0));
			mnAyuda.setActionCommand("Ayuda");
			mnAyuda.setFont(new Font("Segoe UI", Font.PLAIN, 21));
			mnAyuda.add(getMntmContenido());
			mnAyuda.add(getSeparator());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmContenido() {
		if (mntmContenido == null) {
			mntmContenido = new JMenuItem(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.contenido"));
			mntmContenido.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuContenido").charAt(0));
			mntmContenido.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			mntmContenido.setActionCommand("Contenido");
		}
		return mntmContenido;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.salir"));
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			mntmSalir.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			mntmSalir.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuSalir").charAt(0));
		}
		return mntmSalir;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.acercaDe"));
			mntmAcercaDe.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			mntmAcercaDe.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuAcerca").charAt(0));
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					aboutTravelPark();
				}
			});
		}
		return mntmAcercaDe;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmNuevaReserva() {
		if (mntmNuevaReserva == null) {
			mntmNuevaReserva = new JMenuItem(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.nuevaReserva"));
			mntmNuevaReserva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			mntmNuevaReserva.setEnabled(false);
			mntmNuevaReserva.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuNueva").charAt(0));
			mntmNuevaReserva.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			mntmNuevaReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (mensajeDeseaReset() == JOptionPane.YES_OPTION){
						activarVentana1();
					}
				}
			});
		}
		return mntmNuevaReserva;
	}
	
	private int mensajeDeseaReset(){
		int value= JOptionPane.showConfirmDialog(this, Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.preguntaReset"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.informacion"), JOptionPane.YES_NO_CANCEL_OPTION);
		return value;
	}
	
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JPanel getPnPantalla2() {
		if (pnPantalla2 == null) {
			pnPantalla2 = new JPanel();
			pnPantalla2.setBorder(null);
			pnPantalla2.setLayout(new BorderLayout(0, 10));
			pnBusqueda2= null;
			pnPantalla2Inferior= null;
			pnPantalla2.add(getPnBusqueda2(), BorderLayout.NORTH);
			pnPantalla2.add(getPnPantalla2Inferior(), BorderLayout.CENTER);
		}
		return pnPantalla2;
	}
	private JPanel getPnBusqueda2() {
		if (pnBusqueda2 == null) {
			pnBusqueda2 = new JPanel();
			pnBusqueda2.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			pnBusqueda2.setLayout(new GridLayout(3, 0, 0, 0));
			pnBusqueda2Superior= null;
			pnBusqueda2Medio= null;
			pnBusqueda2Final= null;
			pnBusqueda2.add(getPnBusqueda2Superior());
			pnBusqueda2.add(getPnBusqueda2Medio());
			pnBusqueda2.add(getPnBusqueda2Final());
		}
		return pnBusqueda2;
	}
	private JPanel getPnBusqueda2Superior() {
		if (pnBusqueda2Superior == null) {
			pnBusqueda2Superior = new JPanel();
			pnBusqueda2Superior.setLayout(new GridLayout(0, 3, 0, 0));
			pnBusqueda2Superior.add(getPnFechaLlegada());
			pnBusqueda2Superior.add(getPnFechaSalida());
			pnBusqueda2Superior.add(getPnLocalizacion());
		}
		return pnBusqueda2Superior;
	}
	private JPanel getPnBusqueda2Medio() {
		if (pnBusqueda2Medio == null) {
			pnBusqueda2Medio = new JPanel();
			pnBusqueda2Medio.setLayout(new GridLayout(0, 4, 0, 0));
			pnBusqueda2Medio.add(getPnNpersonas());
			pnBusqueda2Medio.add(getPnTodo());
			pnBusqueda2Medio.add(getPnAlojamiento());
			pnBusqueda2Medio.add(getPnEntrada());
		}
		return pnBusqueda2Medio;
	}
	private JPanel getPnBusqueda2Final() {
		if (pnBusqueda2Final == null) {
			pnBusqueda2Final = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBusqueda2Final.getLayout();
			flowLayout.setVgap(20);
			pnBusqueda2Final.setBackground(new Color(255, 255, 255));
			pnBusqueda2Final.add(getBtnModificarBsqueda());
		}
		return pnBusqueda2Final;
	}
	private JButton getBtnModificarBsqueda() {
		if (btnModificarBsqueda == null) {
			btnModificarBsqueda = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.modificarBusqueda"));
			btnModificarBsqueda.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.modificar").charAt(0));
			btnModificarBsqueda.setPreferredSize(new Dimension(200, 35));
			btnModificarBsqueda.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnModificarBsqueda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnModificarBsqueda.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnModificarBsqueda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Date fechaLlegada= getCalendarLlegada().getDate();
					Date fechaSalida= getCalendarSalida().getDate();
					if (fechaLlegada == null){
						getCalendarLlegada().setDate(Principal.fechaSinHoras(new Date()));
					}
					if (fechaSalida == null){
						getCalendarSalida().setDate(getCalendarLlegada().getDate());
					}
					if (fechaLlegada == null || fechaSalida == null){
						mensajeErrorModificarBusqueda();
					}
					else{
						int dif= Principal.diferenciaEnDias(fechaSalida, fechaLlegada);
						if (dif >= 0){
							actualizarListaOrdenacion();
							reBuscarBloques();
						}
						else{
							mensajeErrorModificarBusqueda();
							getCalendarSalida().setDate(getCalendarLlegada().getDate());
						}
					}
				}
			});
		}
		return btnModificarBsqueda;
	}
	
	private void mensajeErrorModificarBusqueda(){
		Icon icon= new ImageIcon(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		JOptionPane.showMessageDialog(this, Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.haHabidoProblemas")+"\n"
				+ "\t·"+Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.fechaLlegadaNoValida")+"\n\t·"+Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.fechaSalidaNoValida")
				+ "\n\t·"+Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.fechaSalidaPosterior"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.errorModificar"), JOptionPane.ERROR_MESSAGE, icon);
	}
	
	private JPanel getPnPantalla2Inferior() {
		if (pnPantalla2Inferior == null) {
			pnPantalla2Inferior = new JPanel();
			pnPantalla2Inferior.setLayout(new BorderLayout(0, 0));
			pnPantalla2Inferior.add(getPnPantalla2Ordenacion(), BorderLayout.NORTH);
			pnPantalla2Inferior.add(getScpPantalla2(), BorderLayout.CENTER);
		}
		return pnPantalla2Inferior;
	}
	private JPanel getPnPantalla2Ordenacion() {
		if (pnPantalla2Ordenacion == null) {
			pnPantalla2Ordenacion = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPantalla2Ordenacion.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnPantalla2Ordenacion.add(getLblOrdenarPor());
			pnPantalla2Ordenacion.add(getCbOrdenacion());
		}
		return pnPantalla2Ordenacion;
	}
	
	private void cargarCriteriosOrdenacion(){
		ordenacion= new DefaultComboBoxModel<>();
		if (this.getRButtonAlojamientos().isSelected()){
			for (String i : CRITERIOS_ORDENACION_CONALOJ){
				this.ordenacion.addElement(i);
			}
		}
		else{
			for (String i : CRITERIOS_ORDENACION_SINALOJ){
				this.ordenacion.addElement(i);
			}
		}
	}
	
	private void actualizarListaOrdenacion(){
		cargarCriteriosOrdenacion();
		getCbOrdenacion().setModel(ordenacion);
	}
	
	private JComboBox<String> getCbOrdenacion() {
		if (cbOrdenacion == null) {
			cargarCriteriosOrdenacion();
			cbOrdenacion = new JComboBox<>(ordenacion);
			cbOrdenacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cbOrdenacion.setSelectedIndex(0);
			cbOrdenacion.setMaximumRowCount(6);
			cbOrdenacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			cbOrdenacion.setPreferredSize(new Dimension(400, 30));
			cbOrdenacion.setBorder(null);
			cbOrdenacion.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	            	reBuscarBloques();
	            }
			});
		}
		return cbOrdenacion;
	}
	
	private JLabel getLblOrdenarPor() {
		if (lblOrdenarPor == null) {
			lblOrdenarPor = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.ordenarPor")+" ");
			lblOrdenarPor.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.ordenar").charAt(0));
			lblOrdenarPor.setLabelFor(getCbOrdenacion());
			lblOrdenarPor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblOrdenarPor;
	}
	private JScrollPane getScpPantalla2() {
		if (scpPantalla2 == null) {
			scpPantalla2 = new JScrollPane();
			scpPantalla2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scpPantalla2.setBorder(new LineBorder(new Color(70, 130, 180), 2));
			scpPantalla2.setViewportView(getPnPantalla2Viajes());
		}
		return scpPantalla2;
	}
	
	private void sortListas(){
		if (this.getCbOrdenacion().getSelectedItem().equals(CRITERIOS_ORDENACION_SINALOJ[0])){
			Collections.sort(Principal.ALOJAMIENTOS);
			Collections.sort(Principal.ENTRADAS);
			Collections.sort(Principal.PAQUETES);
		}
		else if (this.getCbOrdenacion().getSelectedItem().equals(CRITERIOS_ORDENACION_SINALOJ[1])){
			Collections.sort(Principal.ALOJAMIENTOS, Collections.reverseOrder());
			Collections.sort(Principal.ENTRADAS, Collections.reverseOrder());
			Collections.sort(Principal.PAQUETES, Collections.reverseOrder());
		}
		else if (this.getCbOrdenacion().getSelectedItem().equals(CRITERIOS_ORDENACION_CONALOJ[4])){
			Principal.colocarHotelesPrimero();
		}
		else if (this.getCbOrdenacion().getSelectedItem().equals(CRITERIOS_ORDENACION_CONALOJ[5])){
			Principal.colocarApartaHotelPrimero();
		}
		else if (this.getCbOrdenacion().getSelectedItem().equals(CRITERIOS_ORDENACION_CONALOJ[6])){
			Principal.colocarApartamentoPrimero();
		}
		else if (this.getCbOrdenacion().getSelectedItem().equals(CRITERIOS_ORDENACION_CONALOJ[2])){
			Collections.sort(Principal.ALOJAMIENTOS, new Comparator<Alojamiento>(){
				@Override
				public int compare(Alojamiento a1, Alojamiento a2) {
					return a2.getCategoria() - a1.getCategoria();
				}
			});
		}
		else if (this.getCbOrdenacion().getSelectedItem().equals(CRITERIOS_ORDENACION_CONALOJ[3])){
			Collections.sort(Principal.ALOJAMIENTOS, new Comparator<Alojamiento>(){
				@Override
				public int compare(Alojamiento a1, Alojamiento a2) {
					return a1.getCategoria() - a2.getCategoria();
				}
			});
		}
	}
	
	private List<Object> obtenerBloquesBusqueda(Date fechaLlegada, Date fechaSalida, String localizacion, int numPersonas, String opcion){
		int dif= Principal.diferenciaEnDias(fechaSalida, fechaLlegada);
		List<Object> listado= new ArrayList<>();
		sortListas();
		if (dif >= 0 && opcion.equals(OPCION_ENTRADAS)){ //Solo entradas
			for (Entrada i : Principal.ENTRADAS){
				if (localizacion.equals(OPCION_TODAS)){
					listado.add(i);
				}
				else{
					if (localizacion.equals(Principal.findParque(i.getCodigoParque()).getLocalidad())){
						listado.add(i);
					}
				}
			}
		}
		else if (dif > 0){
			if (opcion.equals(OPCION_ALOJAMIENTOS)){
				for (Alojamiento i : Principal.ALOJAMIENTOS){
					if (localizacion.equals(OPCION_TODAS)){
						if (checkAlojamientoOcupacion(i, numPersonas)) listado.add(i);
					}
					else{
						if (localizacion.equals(Principal.findParque(i.getCodigoParque()).getLocalidad())){
							if (checkAlojamientoOcupacion(i, numPersonas)) listado.add(i);
						}
					}
				}
			}
			else if (opcion.equals(OPCION_PACK)){
				for (Paquete i : Principal.PAQUETES){
					if (localizacion.equals(OPCION_TODAS)){
						if (i.getDuracion() == dif) listado.add(i);
					}
					else{
						if (localizacion.equals(Principal.findParque(i.getCodigoParque()).getLocalidad())){
							if (i.getDuracion() == dif) listado.add(i);
						}
					}
				}
			}
		}
		return listado;
	}
	
	private boolean checkAlojamientoOcupacion(Alojamiento alojamiento, int numPersonas){
		if (!alojamiento.getTipo().equals(Tipo.HO)){
			if (numPersonas > alojamiento.getNumeroPlazas()) return false;
		}
		return true;
	}
	
	private void cargarBloques(List<Object> listado){
		getPnPantalla2Viajes().removeAll();
		getPnPantalla2Viajes().revalidate();
		getPnPantalla2Viajes().repaint();
		getScpPantalla2().getVerticalScrollBar().setValue(getScpPantalla2().getVerticalScrollBar().getMinimum());
		for (Object i : listado){
			if (i instanceof Alojamiento){
				BloqueAlojamiento ba= new BloqueAlojamiento(this.getCalendarLlegada().getDate(), this.getCalendarSalida().getDate(), (Integer)(getSpinnerPersonas().getValue()), (Alojamiento)i, this);
				getPnPantalla2Viajes().add(ba.getPnAlojamiento());
			}
			else if (i instanceof Paquete){
				BloquePaquete bp= new BloquePaquete(this.getCalendarLlegada().getDate(), (Integer)(getSpinnerPersonas().getValue()), (Paquete)i, this);
				getPnPantalla2Viajes().add(bp.getPnPaquete());
			}
			else if (i instanceof Entrada){
				BloqueEntrada be= new BloqueEntrada(this.getCalendarLlegada().getDate(), this.getCalendarSalida().getDate(), (Integer)(getSpinnerPersonas().getValue()), (Entrada)i, this);
				getPnPantalla2Viajes().add(be.getPnEntrada());
			}
			getPnPantalla2Viajes().revalidate();
			getPnPantalla2Viajes().repaint();
		}
		if (listado.size() == 0){
			JLabel label= new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.noCoincidencias"));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font("Tahoma", Font.PLAIN, 20));
			label.setForeground(Color.RED);
			pnPantalla2Viajes.add(label);
			getPnPantalla2Viajes().revalidate();
			getPnPantalla2Viajes().repaint();
		}
	}
	
	private void reBuscarBloques(){
		String opcion= OPCION_PACK;
		if (getRButtonEntradas().isSelected()){
			opcion= OPCION_ENTRADAS;
		}
		else if (getRButtonAlojamientos().isSelected()){
			opcion= OPCION_ALOJAMIENTOS;
		}
		List<Object> listado= obtenerBloquesBusqueda(getCalendarLlegada().getDate(), getCalendarSalida().getDate(), (String)getCbLocalizacion().getSelectedItem(), (Integer)getSpinnerPersonas().getValue(), opcion);
		int tam= listado.size();
		if (tam == 0){
			tam= 1;
		}
		getPnPantalla2Viajes().setLayout(new GridLayout(tam, 0, 0, 10));
		cargarBloques(listado);
	}
	
	private JPanel getPnPantalla2Viajes() {
		if (pnPantalla2Viajes == null) {
			pnPantalla2Viajes = new JPanel();
			pnPantalla2Viajes.setBackground(new Color(255, 255, 255));

			
			reBuscarBloques();
			
			Border margin = new EmptyBorder(20, 20, 20, 20);
			pnPantalla2Viajes.setBorder(margin);
		}
		return pnPantalla2Viajes;
	}
	private JPanel getPnInferiorReserva() {
		if (pnInferiorReserva == null) {
			pnInferiorReserva = new JPanel();
			pnInferiorReserva.setBorder(new EmptyBorder(10, 0, 0, 0));
			pnInferiorReserva.setLayout(new BorderLayout(0, 15));
			pnDatosReserva= null;
			scrollPaneReserva= null;
			pnBotonesReserva= null;
			pnInferiorReserva.add(getPnDatosReserva(), BorderLayout.NORTH);
			pnInferiorReserva.add(getScrollPaneReserva(), BorderLayout.CENTER);
			pnInferiorReserva.add(getPnBotonesReserva(), BorderLayout.SOUTH);
		}
		return pnInferiorReserva;
	}
	private JPanel getPnDatosReserva() {
		if (pnDatosReserva == null) {
			pnDatosReserva = new JPanel();
			pnDatosReserva.setBackground(new Color(255, 255, 255));
			pnDatosReserva.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			pnDatosReserva.setLayout(new GridLayout(0, 2, 150, 25));
			pnDatosReserva.add(getPnNombreReserva());
			pnDatosReserva.add(getPnApellidos());
			pnDatosReserva.add(getPnIdentificacion());
			pnDatosReserva.add(getPnBtnConfirmarDatos());
		}
		return pnDatosReserva;
	}
	private JPanel getPnNombreReserva() {
		if (pnNombreReserva == null) {
			pnNombreReserva = new JPanel();
			pnNombreReserva.setBackground(new Color(255, 255, 255));
			pnNombreReserva.setBorder(new EmptyBorder(5, 5, 0, 0));
			pnNombreReserva.setLayout(new BoxLayout(pnNombreReserva, BoxLayout.X_AXIS));
			pnNombreReserva.add(getLblNombre());
			pnNombreReserva.add(getTxNombre());
		}
		return pnNombreReserva;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.nombre")+"        ");
			lblNombre.setLabelFor(getTxNombre());
			lblNombre.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.nombre").charAt(0));
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblNombre;
	}
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setColumns(40);
		}
		return txNombre;
	}
	private JPanel getPnApellidos() {
		if (pnApellidos == null) {
			pnApellidos = new JPanel();
			pnApellidos.setBackground(new Color(255, 255, 255));
			pnApellidos.setBorder(new EmptyBorder(5, 0, 0, 5));
			pnApellidos.setLayout(new BoxLayout(pnApellidos, BoxLayout.X_AXIS));
			pnApellidos.add(getLblApellidos());
			pnApellidos.add(getTxApellidos());
		}
		return pnApellidos;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.apellidos")+" ");
			lblApellidos.setLabelFor(getTxApellidos());
			lblApellidos.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.apellidos").charAt(0));
			lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblApellidos;
	}
	private JTextField getTxApellidos() {
		if (txApellidos == null) {
			txApellidos = new JTextField();
			txApellidos.setColumns(20);
		}
		return txApellidos;
	}
	private JPanel getPnIdentificacion() {
		if (pnIdentificacion == null) {
			pnIdentificacion = new JPanel();
			pnIdentificacion.setBackground(new Color(255, 255, 255));
			pnIdentificacion.setBorder(new EmptyBorder(0, 5, 5, 0));
			pnIdentificacion.setLayout(new BoxLayout(pnIdentificacion, BoxLayout.X_AXIS));
			pnIdentificacion.add(getLblDninifnie());
			pnIdentificacion.add(getTxIdentificacion());
		}
		return pnIdentificacion;
	}
	private JLabel getLblDninifnie() {
		if (lblDninifnie == null) {
			lblDninifnie = new JLabel("DNI/NIF/NIE: ");
			lblDninifnie.setLabelFor(getTxIdentificacion());
			lblDninifnie.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.dni").charAt(0));
			lblDninifnie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblDninifnie;
	}
	private JTextField getTxIdentificacion() {
		if (txIdentificacion == null) {
			txIdentificacion = new JTextField();
			txIdentificacion.setColumns(40);
		}
		return txIdentificacion;
	}
	private JPanel getPnBtnConfirmarDatos() {
		if (pnBtnConfirmarDatos == null) {
			pnBtnConfirmarDatos = new JPanel();
			pnBtnConfirmarDatos.setBackground(new Color(255, 255, 255));
			pnBtnConfirmarDatos.setBorder(new EmptyBorder(0, 0, 5, 5));
			pnBtnConfirmarDatos.setLayout(new GridLayout(0, 1, 0, 0));
			btnConfirmarDatos= null;
			pnBtnConfirmarDatos.add(getBtnConfirmarDatos());
		}
		return pnBtnConfirmarDatos;
	}
	private JButton getBtnConfirmarDatos() {
		if (btnConfirmarDatos == null) {
			btnConfirmarDatos = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.confirmarDatos"));
			btnConfirmarDatos.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.confirmarDatos").charAt(0));
			btnConfirmarDatos.setPreferredSize(new Dimension(200, 35));
			btnConfirmarDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnConfirmarDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnConfirmarDatos.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnConfirmarDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (getTxNombre().getText().trim().equals("") || getTxApellidos().getText().trim().equals("") || getTxIdentificacion().getText().trim().equals("")){
						mensajeErrorConfirmarDatos();
					}
					else{
						reserva.setApellidosCliente(getTxApellidos().getText().trim());
						reserva.setNombreCliente(getTxNombre().getText().trim());
						reserva.setIdentificacionCliente(getTxIdentificacion().getText().trim());
						getJTextPaneDescripcion().setText(reserva.verResumenReserva());
						getJTextPaneDescripcion().setCaretPosition(0);
						getBtnConfirmarReserva().setEnabled(true);
					}
				}
			});
		}
		return btnConfirmarDatos;
	}
	
	private void mensajeErrorConfirmarDatos(){
		Icon icon= new ImageIcon(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		JOptionPane.showMessageDialog(this, Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.rellenarCampos")+"\n", Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.error"), JOptionPane.ERROR_MESSAGE, icon);
		if (getTxApellidos().getText().equals("")){
			getTxApellidos().setText(reserva.getApellidosCliente());
		}
		if (getTxNombre().getText().equals("")){
			getTxNombre().setText(reserva.getNombreCliente());
		}
		if (getTxIdentificacion().getText().equals("")){
			getTxIdentificacion().setText(reserva.getIdentificacionCliente());
		}
	}
	
	private JScrollPane getScrollPaneReserva() {
		if (scrollPaneReserva == null) {
			scrollPaneReserva = new JScrollPane();
			scrollPaneReserva.setBackground(new Color(255, 255, 255));
			scrollPaneReserva.setViewportView(getJTextPaneDescripcion());
			getJTextPaneDescripcion().setCaretPosition(0);
		}
		return scrollPaneReserva;
	}
	
	private JTextPane getJTextPaneDescripcion() {
		if (jTextPaneDescripcion == null) {
			jTextPaneDescripcion = new JTextPane();
			jTextPaneDescripcion.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			jTextPaneDescripcion.setBackground(new Color(255, 255, 255));
			jTextPaneDescripcion.setText("");
			jTextPaneDescripcion.setMargin(new Insets(20, 20, 20, 20));
			jTextPaneDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			jTextPaneDescripcion.setEditable(false);
			jTextPaneDescripcion.setEnabled(false);
			jTextPaneDescripcion.setDisabledTextColor(Color.BLACK);
			jTextPaneDescripcion.setText(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.debeConfirmar"));
		}
		return jTextPaneDescripcion;
	}
	private JPanel getPnBotonesReserva() {
		if (pnBotonesReserva == null) {
			pnBotonesReserva = new JPanel();
			pnBotonesReserva.setBorder(new EmptyBorder(0, 0, 0, 0));
			pnBotonesReserva.setLayout(new BorderLayout(0, 5));
			scrollComentarios= null;
			lblObservaciones= null;
			pnInferiorBotonesReserva= null;
			pnInferiorBotonesReservaConfirmada= null;
			pnBotonesReserva.add(getLblObservaciones(), BorderLayout.NORTH);
			pnBotonesReserva.add(getScrollComentarios(), BorderLayout.CENTER);
			if (reserva.isRealizada()){
				pnBotonesReserva.add(getPnInferiorBotonesReservaConfirmada(), BorderLayout.SOUTH);
			}
			else{
				pnBotonesReserva.add(getPnInferiorBotonesReserva(), BorderLayout.SOUTH);
			}
		}
		return pnBotonesReserva;
	}
	private JLabel getLblObservaciones() {
		if (lblObservaciones == null) {
			lblObservaciones = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.observaciones"));
			lblObservaciones.setLabelFor(getTxAreaComentarios());
			lblObservaciones.setDisplayedMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.comentarios").charAt(0));
			lblObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblObservaciones;
	}
	private JScrollPane getScrollComentarios() {
		if (scrollComentarios == null) {
			scrollComentarios = new JScrollPane(getTxAreaComentarios());
			scrollComentarios.setBackground(new Color(255, 255, 255));
			scrollComentarios.setBorder(new LineBorder(new Color(70, 130, 180), 2));
			scrollComentarios.setPreferredSize(new Dimension(4, 90));
			getTxAreaComentarios().setCaretPosition(0);
		}
		return scrollComentarios;
	}
	private JTextArea getTxAreaComentarios() {
		if (txAreaComentarios == null) {
			txAreaComentarios = new JTextArea();
			txAreaComentarios.setLineWrap(true);
			txAreaComentarios.setFont(new Font("Monospaced", Font.PLAIN, 18));
			txAreaComentarios.setText(reserva.getComentarios());
		}
		return txAreaComentarios;
	}
	private JPanel getPnInferiorBotonesReserva() {
		if (pnInferiorBotonesReserva == null) {
			pnInferiorBotonesReserva = new JPanel();
			btnVolverAlPedido= null;
			btnConfirmarReserva= null;
			pnInferiorBotonesReserva.add(getBtnConfirmarReserva());
			pnInferiorBotonesReserva.add(getBtnVolverAlPedido());			
		}
		return pnInferiorBotonesReserva;
	}
	
	private JPanel getPnInferiorBotonesReservaConfirmada() {
		if (pnInferiorBotonesReservaConfirmada == null) {
			pnInferiorBotonesReservaConfirmada = new JPanel();
			btnSalir= null;
			pnInferiorBotonesReservaConfirmada.add(getBtnSalir());
		}
		return pnInferiorBotonesReservaConfirmada;
	}
	
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.nuevaReserva"));
			btnSalir.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.nuevaReserva").charAt(0));
			btnSalir.setPreferredSize(new Dimension(200, 35));
			btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnSalir.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					activarVentana1();
				}
			});
		}
		return btnSalir;
	}
	
	private JButton getBtnVolverAlPedido() {
		if (btnVolverAlPedido == null) {
			btnVolverAlPedido = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.volver"));
			btnVolverAlPedido.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.volver").charAt(0));
			btnVolverAlPedido.setPreferredSize(new Dimension(200, 35));
			btnVolverAlPedido.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnVolverAlPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnVolverAlPedido.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnVolverAlPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					desactivarVentana1();
				}
			});
		}
		return btnVolverAlPedido;
	}
	private JButton getBtnConfirmarReserva() {
		if (btnConfirmarReserva == null) {
			btnConfirmarReserva = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.confirmarReserva"));
			btnConfirmarReserva.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.confirmar").charAt(0));
			btnConfirmarReserva.setPreferredSize(new Dimension(200, 35));
			btnConfirmarReserva.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnConfirmarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnConfirmarReserva.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnConfirmarReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int value= mensajeDeseaConfirmarReserva();
					if (value == JOptionPane.NO_OPTION){
						desactivarVentana1();
					}
					else if (value == JOptionPane.YES_OPTION){
						reserva.añadirComentarios(getTxAreaComentarios().getText().trim());
						reserva.formalizarReserva();
						pnBotonesReserva= null;
						activarVentana4();
					}
				}
			});
		}
		return btnConfirmarReserva;
	}
	
	private int mensajeDeseaConfirmarReserva(){
		int value= JOptionPane.showConfirmDialog(this, Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.preguntaReserva"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.informacion"), JOptionPane.YES_NO_CANCEL_OPTION);
		return value;
	}
	
	private void aboutTravelPark(){
		Icon icon= new ImageIcon(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		JOptionPane.showMessageDialog(this, "TRAVEL PARK\n"
				+ "\t"+Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.version")+": 1.0"
				+ "\n\tCopyright(c) Andrés Casillas García", Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.informacionSobre")+" Travel Park", JOptionPane.ERROR_MESSAGE, icon);
	}
	private JMenu getMnVentana() {
		if (mnVentana == null) {
			mnVentana = new JMenu(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.ventana"));
			mnVentana.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuVentana").charAt(0));
			mnVentana.setFont(new Font("Segoe UI", Font.PLAIN, 21));
			mnVentana.add(getChckbxmntmEspaol());
			mnVentana.add(getChckbxmntmIngls());
		}
		return mnVentana;
	}
	private JRadioButtonMenuItem getChckbxmntmEspaol() {
		if (chckbxmntmEspaol == null) {
			chckbxmntmEspaol = new JRadioButtonMenuItem(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.spanish"));
			chckbxmntmEspaol.setFont(new Font("Segoe UI", Font.PLAIN, 21));
			chckbxmntmEspaol.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuSpanish").charAt(0));
			if (Principal.LOCALIZACION.getLanguage().equals("es")){
				chckbxmntmEspaol.setSelected(true);
			}
			buttonGroupMenu.add(chckbxmntmEspaol);
			
			chckbxmntmEspaol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!Principal.LOCALIZACION.getLanguage().equals("es")){
						if (mensajeDeseaConfirmarIdioma() == JOptionPane.YES_OPTION){
							setVisible(false);
							Principal.nuevaLocalizacion(new Locale("es", Principal.COUNTRY_CODE));
							cambiarIdioma();
							setVisible(true);
						}
						else{
							getChckbxmntmIngls().setSelected(true);
						}
					}
				}
			});
		}
		return chckbxmntmEspaol;
	}
	private JRadioButtonMenuItem getChckbxmntmIngls() {
		if (chckbxmntmIngls == null) {
			chckbxmntmIngls = new JRadioButtonMenuItem(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.ingles"));
			chckbxmntmIngls.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.mnemonics.menuIngles").charAt(0));
			chckbxmntmIngls.setFont(new Font("Segoe UI", Font.PLAIN, 21));
			buttonGroupMenu.add(chckbxmntmIngls);
			if (Principal.LOCALIZACION.getLanguage().equals("en")){
				chckbxmntmIngls.setSelected(true);
			}
			chckbxmntmIngls.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!Principal.LOCALIZACION.getLanguage().equals("en")){
						if (mensajeDeseaConfirmarIdioma() == JOptionPane.YES_OPTION){
							setVisible(false);
							Principal.nuevaLocalizacion(new Locale("en", Principal.COUNTRY_CODE));
							cambiarIdioma();
							setVisible(true);
						}
						else{
							getChckbxmntmEspaol().setSelected(true);
						}
					}
				}
			});
		}
		return chckbxmntmIngls;
	}
	
	private int mensajeDeseaConfirmarIdioma(){
		int value= JOptionPane.showConfirmDialog(this, Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.preguntaIdioma"), Principal.LOCALIZACION_TEXTOS.getString("ventanaPrincipal.informacion"), JOptionPane.YES_NO_CANCEL_OPTION);
		return value;
	}
}
