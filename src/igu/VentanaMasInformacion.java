package igu;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;

import logica.ArticuloAbstract;
import logica.ArticuloAlojamiento;
import logica.ArticuloEntrada;
import logica.ArticuloPaquete;
import logica.Principal;
import logica.Alojamiento.Tipo;

public class VentanaMasInformacion extends JDialog {
	private static String IMAGEN_DEFAULT= "/img/no_disponible.jpg";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnPrincipal;
	private JLabel lbTitulo;
	private JPanel pnMedio;
	private JLabel lblImagen;
	private JScrollPane sPaneDescripcion;
	private JTextPane jTextPaneDescripcion;
	private JPanel pnFinal;
	private JPanel pnDescripcion;
	private JButton btnVolver;
	private JPanel pnImagen;
	private ImageIcon imagen;
	private ArticuloAbstract articuloAbstract;
	private ArticuloEntrada articuloEntrada;
	private ArticuloPaquete articuloPaquete;
	private ArticuloAlojamiento articuloAlojamiento;
	private JPanel pnInferiorImagen;
	private JButton buttonAntes;
	private JButton buttonDespues;
	private boolean medio;
	
	public VentanaMasInformacion(ImageIcon imagen, ArticuloAbstract articulo, VentanaPrincipal vp) {
		if (imagen == null){
			try{
				this.imagen= new ImageIcon(getClass().getResource(IMAGEN_DEFAULT));
			}catch(Exception ex){
			}
		}
		else{
			this.imagen= imagen;
		}
		this.setModal(true);
		this.articuloAbstract= articulo;
		checkTipoArticulo();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener( new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e ) { 
				closeWindow();
			} 
		} );
		setBounds(100, 100, 825, 525);
		setLocationRelativeTo(vp);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		setTitle("Travel Park");
		pnPrincipal = new JPanel();
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pnPrincipal);
		pnPrincipal.add(getLbTitulo(), BorderLayout.NORTH);
		pnPrincipal.add(getPnMedio(), BorderLayout.CENTER);
		pnPrincipal.add(getPnFinal(), BorderLayout.SOUTH);
	}
	
	private void checkTipoArticulo(){
		if (articuloAbstract instanceof ArticuloEntrada){
			articuloEntrada= (ArticuloEntrada) articuloAbstract;
		}
		else if (articuloAbstract instanceof ArticuloPaquete){
			articuloPaquete= (ArticuloPaquete) articuloAbstract;
		}
		else if (articuloAbstract instanceof ArticuloAlojamiento){
			articuloAlojamiento= (ArticuloAlojamiento) articuloAbstract;
		}
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			String label= "";
			if (articuloPaquete != null){
				label= articuloPaquete.getPaquete().getDenominacion();
			}
			else if (articuloAlojamiento != null){
				label= articuloAlojamiento.getAlojamiento().getDenominacion();
				if (this.articuloAlojamiento.getAlojamiento().getTipo().equals(Tipo.HO)){
					label+= " " + this.articuloAlojamiento.getAlojamiento().getCategoria() + " "+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.estrellas");
				}
			}
			else if (articuloEntrada != null){
				label= Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.entrada")+" " + Principal.findParque(articuloEntrada.getEntrada().getCodigoParque()).getDenominacion();
			}
			lbTitulo = new JLabel(label);
			lbTitulo.setFont(new Font("Sylfaen", Font.BOLD, 40));
			lbTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setBorder(new EmptyBorder(25, 0, 0, 0));
		}
		return lbTitulo;
	}
	private JPanel getPnMedio() {
		if (pnMedio == null) {
			pnMedio = new JPanel();
			pnMedio.setLayout(new GridLayout(0, 2, 0, 0));
			pnMedio.add(getPnImagen());
			pnMedio.add(getPnDescripcion());
		}
		return pnMedio;
	}
	
	private JPanel getPnDescripcion() {
		if (pnDescripcion == null) {
			pnDescripcion = new JPanel();
			pnDescripcion.setLayout(new GridLayout(0, 1, 0, 0));
			pnDescripcion.add(getSPaneDescripcion());
		}
		return pnDescripcion;
	}
	
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
			lblImagen.setBorder(new LineBorder(new Color(70, 130, 180), 2, true));
			lblImagen.setHorizontalTextPosition(SwingConstants.CENTER);
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
			if (imagen!=null){
				lblImagen.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						adaptarImagenLabel();
					}
				});
			}
		}
		return lblImagen;
	}
	
	
	private JScrollPane getSPaneDescripcion() {
		if (sPaneDescripcion == null) {
			sPaneDescripcion = new JScrollPane();
			sPaneDescripcion.setViewportView(getJTextPaneDescripcion());
			getJTextPaneDescripcion().setCaretPosition(0);
		}
		return sPaneDescripcion;
	}
	private JTextPane getJTextPaneDescripcion() {
		if (jTextPaneDescripcion == null) {
			jTextPaneDescripcion = new JTextPane();
			jTextPaneDescripcion.setBackground(new Color(255, 255, 255));
			jTextPaneDescripcion.setText(getTextoDescripcion());
			jTextPaneDescripcion.setMargin(new Insets(20, 20, 20, 20));
			jTextPaneDescripcion.setFont(new Font("Monotype Corsiva", Font.PLAIN, 30));
			jTextPaneDescripcion.setEditable(false);
			jTextPaneDescripcion.setEnabled(false);
			jTextPaneDescripcion.setDisabledTextColor(Color.BLACK);
			StyledDocument doc = jTextPaneDescripcion.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
		}
		return jTextPaneDescripcion;
	}
	
	
	private String getTextoDescripcion(){
		String texto= getLbTitulo().getText();
		if (articuloAlojamiento != null){
			texto+= " "+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.situadoen")+" " + Principal.findParque(articuloAlojamiento.getAlojamiento().getCodigoParque()).getLocalidad()+".";
			texto+="\n"+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.alLadoParque")+" "+ Principal.findParque(articuloAlojamiento.getAlojamiento().getCodigoParque()).getDenominacion()+". "+Principal.findParque(articuloAlojamiento.getAlojamiento().getCodigoParque()).getDescripcion()+".";
		}
		else if (articuloPaquete != null){
			texto+= " "+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.aDisfrutar")+" " + Principal.findParque(articuloPaquete.getPaquete().getCodigoParque()).getLocalidad()+".";
			texto+="\n"+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.alLadoParque")+" "+ Principal.findParque(articuloPaquete.getPaquete().getCodigoParque()).getDescripcion();
			texto+="\n"+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.alojateEn")+" "+ Principal.findAlojamiento(articuloPaquete.getPaquete().getCodigoAlojamiento()).getDenominacion();
			if (Principal.findAlojamiento(articuloPaquete.getPaquete().getCodigoAlojamiento()).getTipo().equals(Tipo.HO)){
				texto+= " "+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.de")+" " + Principal.findAlojamiento(articuloPaquete.getPaquete().getCodigoAlojamiento()).getCategoria() + " "+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.estrellas")+".";
			}
		}
		else if (articuloEntrada != null){
			texto+= " "+Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.situadoen")+" " + Principal.findParque(articuloEntrada.getEntrada().getCodigoParque()).getLocalidad()+". ";
			texto+= Principal.findParque(articuloEntrada.getEntrada().getCodigoParque()).getDescripcion()+".";
		}
		return texto;
	}
	
	private JPanel getPnFinal() {
		if (pnFinal == null) {
			pnFinal = new JPanel();
			pnFinal.add(getBtnVolver());
		}
		return pnFinal;
	}
	
	private void adaptarImagenLabel(){
		  Image imgOriginal = imagen.getImage();
		  Image imgEscalada = imgOriginal.getScaledInstance((int)(getLblImagen().getWidth()),(int)(getLblImagen().getHeight()), Image.SCALE_FAST);
		  getLblImagen().setIcon(new ImageIcon(imgEscalada));
	}
	
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.volver"));
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					closeWindow();
				}
			});
			btnVolver.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.mnemonics.volver").charAt(0));
			btnVolver.setPreferredSize(new Dimension(200, 35));
			btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnVolver.setBorder(new LineBorder(new Color(70, 130, 180)));
		}
		return btnVolver;
	}
	
	private void closeWindow(){
		setVisible(false);
	}
	
	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getLblImagen(), BorderLayout.CENTER);
			if (this.articuloPaquete != null || this.articuloAlojamiento != null){
				pnImagen.add(getPnInferiorImagen(), BorderLayout.SOUTH);
			}
		}
		return pnImagen;
	}
	
	private JPanel getPnInferiorImagen(){
		if (pnInferiorImagen == null){
			pnInferiorImagen= new JPanel();
			pnInferiorImagen.setLayout(new FlowLayout());
			pnInferiorImagen.add(getButtonAntes());
			pnInferiorImagen.add(getButtonDespues());
		}
		return this.pnInferiorImagen;
	}
	private JButton getButtonAntes() {
		if (buttonAntes == null) {
			buttonAntes = new JButton("<--");
			buttonAntes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getButtonAntes().setEnabled(false);
					getButtonDespues().setEnabled(true);
					if (articuloPaquete != null){
						if (!medio){
							medio= true;
							getButtonAntes().setEnabled(true);
							imagenPaqueteAlojamiento();
						}
						else{
							imagenOriginalPaquete();
							medio= false;
						}
					}
					else if (articuloAlojamiento != null){
						imagenOriginalAlojamiento();
					}
				}
			});
			buttonAntes.setEnabled(false);
			buttonAntes.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.mnemonics.antes").charAt(0));
			buttonAntes.setToolTipText(Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.imagenAntes"));
			buttonAntes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonAntes.setPreferredSize(new Dimension(100, 35));
			buttonAntes.setFont(new Font("Tahoma", Font.PLAIN, 20));
			buttonAntes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttonAntes.setBorder(new LineBorder(new Color(70, 130, 180)));
		}
		return buttonAntes;
	}
	
	private JButton getButtonDespues() {
		if (buttonDespues == null) {
			buttonDespues = new JButton("-->");
			buttonDespues.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getButtonDespues().setEnabled(false);
					getButtonAntes().setEnabled(true);
					if (articuloPaquete != null){
						if (!medio){
							imagenPaqueteAlojamiento();
							getButtonDespues().setEnabled(true);
							medio= true;
						}
						else{
							imagenPaqueteParque();
							medio= false;
						}
					}
					else if (articuloAlojamiento != null){
						imagenParqueAlojamiento();
					}
				}
			});
			buttonDespues.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.mnemonics.despues").charAt(0));
			buttonDespues.setToolTipText(Principal.LOCALIZACION_TEXTOS.getString("ventanaMasInfo.imagenSiguiente"));
			buttonDespues.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			buttonDespues.setPreferredSize(new Dimension(100, 35));
			buttonDespues.setFont(new Font("Tahoma", Font.PLAIN, 20));
			buttonDespues.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			buttonDespues.setBorder(new LineBorder(new Color(70, 130, 180)));
		}
		return buttonDespues;
	}
	
	private void imagenOriginalPaquete(){
		imagen= articuloPaquete.getPaquete().getImagen();
		if (imagen == null){
			imagen= new ImageIcon(getClass().getResource(IMAGEN_DEFAULT));
		}
		adaptarImagenLabel();
	}
	
	private void imagenOriginalAlojamiento(){
		imagen= articuloAlojamiento.getAlojamiento().getImagen();
		if (imagen == null){
			imagen= new ImageIcon(getClass().getResource(IMAGEN_DEFAULT));
		}
		adaptarImagenLabel();
	}
	
	private void imagenPaqueteParque(){
		if (this.articuloPaquete != null){
			this.imagen= Principal.findParque(articuloPaquete.getPaquete().getCodigoParque()).getImagen();
			if (this.imagen == null){
				this.imagen= new ImageIcon(getClass().getResource(IMAGEN_DEFAULT));
			}
			adaptarImagenLabel();
		}
	}
	
	private void imagenParqueAlojamiento(){
		if (this.articuloAlojamiento != null){
			this.imagen= Principal.findParque(articuloAlojamiento.getAlojamiento().getCodigoParque()).getImagen();
			if (this.imagen == null){
				this.imagen= new ImageIcon(getClass().getResource(IMAGEN_DEFAULT));
			}
			adaptarImagenLabel();
		}
	}
	
	private void imagenPaqueteAlojamiento(){
		if (this.articuloPaquete != null){
			this.imagen= Principal.findAlojamiento(articuloPaquete.getPaquete().getCodigoAlojamiento()).getImagen();
			if (this.imagen == null){
				this.imagen= new ImageIcon(getClass().getResource(IMAGEN_DEFAULT));
			}
			adaptarImagenLabel();
		}
	}
}
