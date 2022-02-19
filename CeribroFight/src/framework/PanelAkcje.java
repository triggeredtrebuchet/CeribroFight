package framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import obiektyWalki.Postac;
import obiektyWalki.akcje.Akcja;

/* PanelAkcje to panel widoczny w prawym dolnym rogu.
 * w gridzie wyswitla on dostepne akcje dla wskazanej postaci w metodzie wyswitlAkcje
 * TODO ladniejszy widok przyciskow
 */

public class PanelAkcje extends JPanel{
	
	Color color1 = new Color(60,20,50);
    Color color2 = new Color(31,40,102);
    Postac aktywnaPostac;
    ActionListener panelInfo;
    boolean wyswietlaReakcje = false;
	
	
	public PanelAkcje(Color kolor1, Color kolor2, ActionListener pi) {
		this.color1 = kolor1;
		this.color2 = kolor2;
		this.panelInfo = pi;

		this.setPreferredSize(new Dimension(400,300));
		this.setLayout(new GridLayout(4,4, 5,5));
		
	}
	
	public void wyswietlAkcje(Postac postac) {
		this.removeAll();
		
		this.wyswietlaReakcje = false;
		this.aktywnaPostac = postac;
		JLabel nazwaPostaci = new JLabel();
		nazwaPostaci.setText(postac.getNazwa());
		nazwaPostaci.setForeground(Color.WHITE);
		nazwaPostaci.setHorizontalAlignment(WIDTH/2);
		this.add(nazwaPostaci);
		for(Akcja akcja: postac.getDostepneAkcje()) {
				JButton button = new JButton();
				button.addActionListener(panelInfo);
				button.setText(akcja.getNazwa());
				this.add(button);
		}
		// przyciski sa w tej samej kolejnosci co akcje w obiekcie postacie
		// oznacza to, ze dostep do inforamcji ktora akcja zostala wybrana otrzymujemy przez panel akcje
		// if (e.getSource == panelAkcje.getComponent(i+1))
		// 		this.aktywnapostac.getDostepneAkcje().get(i) == akcja wykonana przez nacisniecie itego przycisku od gory
		//  ( i+1, poniewaz pierwszym komponentem jest labelka nazwaPostaci
		this.repaint();		
	}
	public void wyswietlReakcje(Postac postac) {
		this.removeAll();
		
		this.wyswietlaReakcje = true;
		this.aktywnaPostac = postac;
		JLabel nazwaPostaci = new JLabel();
		nazwaPostaci.setText(postac.getNazwa());
		nazwaPostaci.setForeground(Color.WHITE);
		nazwaPostaci.setHorizontalAlignment(WIDTH/2);
		this.add(nazwaPostaci);
		for(Akcja reakcja: postac.getDostepneReakcje()) {
				JButton button = new JButton();
				button.addActionListener(panelInfo);
				button.setText(reakcja.getNazwa());
				this.add(button);
		}
		// przyciski sa w tej samej kolejnosci co reakcje w obiekcie postacie
		// oznacza to, ze dostep do inforamcji ktora akcja zostala wybrana otrzymujemy przez panel akcje
		// if (e.getSource == panelAkcje.getComponent(i+1))
		// 		this.aktywnapostac.getDostepneAkcje().get(i) == akcja wykonana przez nacisniecie itego przycisku od gory
		//  ( i+1, poniewaz pierwszym komponentem jest labelka nazwaPostaci
		this.repaint();		
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

