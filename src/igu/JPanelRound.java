package igu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class JPanelRound extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorPrimario = new Color(0xffffff);
    private Color colorSecundario = new Color(0xffffff);
    private Color colorContorno = new Color(0x0000ff);
    private int arcw=200;
    private int arch=200;
   
    public JPanelRound() {
        super();
        setOpaque(false);
    }
   
   
     @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
       
       
         Paint oldPaint = g2.getPaint();
          RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(
                    0,0,getWidth(),getHeight()-1,getArcw(),getArch());
            g2.clip(r2d);
            g2.setPaint(new GradientPaint(0.0f, 0.0f,getColorPrimario(),
                    0.0f, getHeight(),getColorSecundario()));
            g2.fillRect(0,0,getWidth(),getHeight());
           
            g2.setPaint(new GradientPaint(0.0f, 0.0f,getColorContorno(),
                    0.0f, getHeight(), getColorContorno()));
            g2.drawRoundRect(0, 0, getWidth()-2 , getHeight() -2, 203, 203);
       
        g2.setPaint(oldPaint);
        super.paintComponent(g);
    }

     public Color getColorPrimario() {
         return colorPrimario;
     }

     public void setColorPrimario(Color colorPrimario) {
         this.colorPrimario = colorPrimario;
     }

     public Color getColorSecundario() {
         return colorSecundario;
     }

     public void setColorSecundario(Color colorSecundario) {
         this.colorSecundario = colorSecundario;
     }

     public Color getColorContorno() {
         return colorContorno;
     }

     public void setColorContorno(Color colorContorno) {
         this.colorContorno = colorContorno;
     }

    public int getArcw() {
        return arcw;
    }

    public void setArcw(int arcw) {
        this.arcw = arcw;
    }

    public int getArch() {
        return arch;
    }

    public void setArch(int arch) {
        this.arch = arch;
    }

}
