package framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import obiektyWalki.Postac;
import obiektyWalki.akcje.Akcja;

/* PanelAkcje to panel widoczny w prawym dolnym rogu.
 * w gridzie wyswitla on dostepne akcje dla wskazanej postaci w metodzie wyswitlAkcje
 * TODO ladniejszy widok przyciskow
 */

public class PanelAkcje extends JPanel implements ActionListener{
	
	Color color1 = new Color(60,20,50);
    Color color2 = new Color(31,40,102);
    
    JPanel intro = new JPanel();
    JRadioButton brPrw = new JRadioButton("Prawa Reka");
	JRadioButton brLw = new JRadioButton("Lewa Reka");
	JLabel nazwaPostaci = new JLabel();
    ActionListener panelInfo;
    boolean wyswietlaReakcje = false;
	
	
	public PanelAkcje(Color kolor1, Color kolor2, ActionListener pi) {
		this.color1 = kolor1;
		this.color2 = kolor2;
		this.panelInfo = pi;

		this.setPreferredSize(new Dimension(400,300));
		this.setLayout(new GridLayout(4,4, 5,5));
		
		
		this.intro.setLayout(null);
		this.intro.setOpaque(false);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(this.brLw);
		grp.add(this.brPrw);
		
		this.intro.add(brLw);
		this.brLw.setOpaque(false);
		this.brLw.setForeground(Color.white);
		this.brLw.setBounds(75, 40, 100, 20);
		
		this.intro.add(brPrw);
		this.brPrw.setOpaque(false);
		this.brPrw.setForeground(Color.white);
		this.brPrw.setBounds(225, 40, 100, 20);
		
		this.brLw.addActionListener(this);
		this.brPrw.addActionListener(this);
		
		this.nazwaPostaci.setForeground(Color.WHITE);
		this.nazwaPostaci.setHorizontalAlignment(WIDTH/2);
		
	}
	
	public void wyswietlAkcje(Postac postac) {
		this.removeAll();
		
		this.wyswietlaReakcje = false;
		MainFrame.aktywnaPostac = postac;
		
		this.intro.remove(nazwaPostaci);
		this.nazwaPostaci.setText(postac.getNazwa());
		this.intro.add(nazwaPostaci);
		nazwaPostaci.setBounds(50, -10, 300, 60);
		
		if (MainFrame.aktywnaPostac.prawaRekaAktywna)
			this.brPrw.setSelected(true);
		else
			this.brLw.setSelected(true);
		
		this.add(intro);
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
		MainFrame.aktywnaPostac = postac;
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
	
	public void zmienReke() {
		if (MainFrame.aktywnaPostac.prawaRekaAktywna) {
			MainFrame.aktywnaPostac.prawaRekaAktywna = false;
			this.brLw.setSelected(true);
		}	
		else {
			this.brPrw.setSelected(true);
			MainFrame.aktywnaPostac.prawaRekaAktywna = true;
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.brPrw)
			MainFrame.aktywnaPostac.prawaRekaAktywna = true;
		else if (e.getSource() == this.brLw)
			MainFrame.aktywnaPostac.prawaRekaAktywna = false;
		
	}

}

