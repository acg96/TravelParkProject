package igu;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.util.List;

import logica.ArticuloAbstract;
import logica.ArticuloAlojamiento;
import logica.ArticuloEntrada;
import logica.ArticuloPaquete;
import logica.Principal;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCarro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnPrincipal;
	private JLabel lblTituloCarro;
	private JScrollPane spListado;
	private JPanel pnListaCarro;
	private JPanel pnInferior;
	private JButton btnVolver;
	private JButton btnConfirmarPedido;
	private JButton btnEliminar;
	
	private VentanaPrincipal vp;

	
	public VentanaCarro(VentanaPrincipal vp) {
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.vp= vp;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icon.png")));
		setTitle("Travel Park");
		setBounds(100, 100, 1200, 750);
		setLocationRelativeTo(vp);
		pnPrincipal = new JPanel();
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnPrincipal);
		addWindowListener( new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e ) { 
				closeWindow();
			} 
		} );
		pnPrincipal.setLayout(new BorderLayout(0, 0));
		pnPrincipal.add(getLblTituloCarro(), BorderLayout.NORTH);
		pnPrincipal.add(getSpListado(), BorderLayout.CENTER);
		pnPrincipal.add(getPnInferior(), BorderLayout.SOUTH);
	}

	public JLabel getLblTituloCarro() {
		if (lblTituloCarro == null) {
			lblTituloCarro = new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.pedido"));
			lblTituloCarro.setForeground(new Color(70, 130, 180));
			lblTituloCarro.setBorder(new EmptyBorder(20, 0, 0, 0));
			lblTituloCarro.setHorizontalAlignment(SwingConstants.CENTER);
			lblTituloCarro.setFont(new Font("Sylfaen", Font.BOLD, 40));
		}
		return lblTituloCarro;
	}
	private JScrollPane getSpListado() {
		if (spListado == null) {
			spListado = new JScrollPane();
			spListado.setViewportView(getPnListaCarro());
		}
		return spListado;
	}
	
	public void actualizarListadoCarro(){
		reBuscarBloques();
	}
	
	private JPanel getPnListaCarro() {
		if (pnListaCarro == null) {
			pnListaCarro = new JPanel();
			pnListaCarro.setBackground(new Color(255, 255, 255));

			
			reBuscarBloques();
			
			Border margin = new EmptyBorder(20, 20, 20, 20);
			pnListaCarro.setBorder(margin);
		}
		return pnListaCarro;
	}
	
	private void cargarBloques(List<ArticuloAbstract> listado){
		getPnListaCarro().removeAll();
		getPnListaCarro().revalidate();
		getPnListaCarro().repaint();
		getSpListado().getVerticalScrollBar().setValue(getSpListado().getVerticalScrollBar().getMinimum());
		for (ArticuloAbstract i : listado){
			if (i instanceof ArticuloAlojamiento){
				BloqueAlojamiento ba= new BloqueAlojamiento((ArticuloAlojamiento)i, this.vp, true);
				getPnListaCarro().add(ba.getPnAlojamiento());
			}
			else if (i instanceof ArticuloPaquete){
				BloquePaquete bp= new BloquePaquete((ArticuloPaquete)i, this.vp, true);
				getPnListaCarro().add(bp.getPnPaquete());
			}
			else if (i instanceof ArticuloEntrada){
				BloqueEntrada be= new BloqueEntrada((ArticuloEntrada)i, this.vp, true);
				getPnListaCarro().add(be.getPnEntrada());
			}
			getPnListaCarro().revalidate();
			getPnListaCarro().repaint();
		}
		if (listado.size() == 0){
			JLabel label= new JLabel(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.pedidoVacio"));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font("Tahoma", Font.PLAIN, 20));
			label.setForeground(Color.RED);
			getPnListaCarro().add(label);
			getBtnConfirmarPedido().setEnabled(false);
			getBtnEliminarTodo().setEnabled(false);
			getPnListaCarro().revalidate();
			getPnListaCarro().repaint();
		}
		else{
			getBtnConfirmarPedido().setEnabled(true);
			getBtnEliminarTodo().setEnabled(true);
		}
		getLblTituloCarro().setText(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.pedido")+" | "+Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.total")+": "+ Principal.NF.format(Principal.formatoDecimales(this.vp.getReserva().getImporteTotal())));
	}
	
	private List<ArticuloAbstract> getListado(){
		return this.vp.getReserva().getCarrito().getListado();
	}
	
	private void reBuscarBloques(){
		int tam= getListado().size();
		if (tam == 0){
			tam= 1;
		}
		getPnListaCarro().setLayout(new GridLayout(tam, 0, 0, 10));
		cargarBloques(getListado());
	}
	private JPanel getPnInferior() {
		if (pnInferior == null) {
			pnInferior = new JPanel();
			pnInferior.add(getBtnConfirmarPedido());
			pnInferior.add(getBtnEliminarTodo());
			pnInferior.add(getBtnVolver());
		}
		return pnInferior;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.volver"));
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					closeWindow();
				}
			});
			btnVolver.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.mnemonics.volver").charAt(0));
			btnVolver.setPreferredSize(new Dimension(200, 35));
			btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnVolver.setBorder(new LineBorder(new Color(70, 130, 180)));
		}
		return btnVolver;
	}
	
	private JButton getBtnEliminarTodo() {
		if (btnEliminar == null) {
			btnEliminar = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.eliminarTodo"));
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int value= mensajeDeseaConfirmarBorrado();
					if (value == JOptionPane.YES_OPTION){
						vp.getReserva().getCarrito().vaciarCarrito();
						actualizarListadoCarro();
						vp.getTxCarrito().setText("0");
					}
					
				}
			});
			btnEliminar.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.mnemonics.eliminar").charAt(0));
			btnEliminar.setPreferredSize(new Dimension(200, 35));
			btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnEliminar.setBorder(new LineBorder(new Color(70, 130, 180)));
		}
		return btnEliminar;
	}
	
	private void closeWindow(){
		this.setVisible(false);
	}
	
	private JButton getBtnConfirmarPedido() {
		if (btnConfirmarPedido == null) {
			btnConfirmarPedido = new JButton(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.confirmarPedido"));
			btnConfirmarPedido.setMnemonic(Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.mnemonics.confirmar").charAt(0));
			btnConfirmarPedido.setPreferredSize(new Dimension(200, 35));
			btnConfirmarPedido.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnConfirmarPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnConfirmarPedido.setBorder(new LineBorder(new Color(70, 130, 180)));
			btnConfirmarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					vp.activarVentana3();
				}
			});
		}
		return btnConfirmarPedido;
	}
	
	private int mensajeDeseaConfirmarBorrado(){
		int value= JOptionPane.showConfirmDialog(this, Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.mensajeEliminar"), Principal.LOCALIZACION_TEXTOS.getString("ventanaCarro.informacion"), JOptionPane.YES_NO_CANCEL_OPTION);
		return value;
	}
}
