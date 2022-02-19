package framework;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import obiektyWalki.Cechy;
import obiektyWalki.Postac;
import obiektyWalki.akcje.AtakStandardowy;
import obiektyWalki.bronie.Bron;

/*Panel postacie to panel widoczny w prawym gornym rogu
 * przechowuje on liste awatarow - ktore sa przyciskami reprezentujacymi postac
 * za pomoca obslugi przyciskow mozna manualnie decydowac o kolejnosci wykonywania ruchu przez postacie
 * TODO dodac obsluge przewijania panelu postaci - na razie nie mozna dotac sie do postaci ktore sa pod dolny progiem panelu
 */

public class PanelPostacie extends JPanel{
	
	Color color1 = new Color(60,20,50);
    Color color2 = new Color(31,40,102);
    ActionListener panelInfo;
    
    public ArrayList<AwatarPostac> awatary = new ArrayList<AwatarPostac>();
	
	
	public PanelPostacie(Color kolor1, Color kolor2, ActionListener panelInfo) {
		this.color1 = kolor1;
		this.color2 = kolor2;
		this.setLayout(null); 
		this.panelInfo = panelInfo;
	}
	
	public void dodajPostac(Postac postac, int n) {
		AwatarPostac button = new AwatarPostac(postac, n);
		button.addActionListener(panelInfo);
		this.add(button);
		this.awatary.add(button);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        GradientPaint gp = new GradientPaint(0, h/2 , this.color1, w, h/2, this.color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}

