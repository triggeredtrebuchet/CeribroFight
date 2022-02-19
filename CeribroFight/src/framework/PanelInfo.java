package framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import obiektyWalki.Postac;
import obiektyWalki.akcje.Akcja;

/*
 * Panel info jest panel po prawej stronie ekranu - sam wlasciwie nie ma ciala, bo sa nim panel postacie i panel akcje
 * na razie obsluguje przycisk awatarPostaci z panelu Postacie ustawiajac postac aktualnie wykonujaca ruch
 */

public class PanelInfo extends JPanel implements ActionListener{
	
	PanelPostacie panelPostacie;
	PanelAkcje panelAkcje;
	int iloscPostaci = 0;
    ArrayList<Postac> postacie = new ArrayList<Postac>();
    MainFrame mainFrame;
	
	
	public PanelInfo(MainFrame mainFrame, Color ks, Color kp, Color ka) {
		super();
		this.mainFrame = mainFrame;
		this.setPreferredSize(new Dimension(400,300));
		this.setLayout(new GridLayout(4,4, 5,5));
		this.setLayout(new BorderLayout(5,5));
		
		this.panelAkcje = new PanelAkcje(ks,ka, this);
		this.panelPostacie = new PanelPostacie(ks, kp, this);
		
		this.add(this.panelAkcje, BorderLayout.SOUTH);
		this.add(this.panelPostacie);
	}
	
	public void dodajPostac(Postac postac) {
		this.postacie.add(postac);
		this.panelPostacie.dodajPostac(postac, this.iloscPostaci);
		this.iloscPostaci++;
	}
	
	public void wyswietlAkcje(Postac postac) {
		this.panelAkcje.wyswietlAkcje(postac);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < this.iloscPostaci; i++) {
			if ( e.getSource().equals(this.panelPostacie.awatary.get(i))) {
				if(this.panelAkcje.wyswietlaReakcje)
					this.panelAkcje.wyswietlAkcje(this.postacie.get(i));
				else
					this.panelAkcje.wyswietlReakcje(this.postacie.get(i));
				break;
				}
		}
		if(!this.panelAkcje.wyswietlaReakcje) {
		for(int i = 1; i< this.panelAkcje.getComponentCount(); i++) {
			if(e.getSource().equals(this.panelAkcje.getComponent(i)))
				this.mainFrame.wyslijZapytanie(this.panelAkcje.aktywnaPostac, this.panelAkcje.aktywnaPostac.getDostepneAkcje().get(i-1));
		}}
		else {
			for(int i = 1; i< this.panelAkcje.getComponentCount(); i++) {
				if(e.getSource().equals(this.panelAkcje.getComponent(i)))
					this.mainFrame.wyslijZapytanie(this.panelAkcje.aktywnaPostac, this.panelAkcje.aktywnaPostac.getDostepneReakcje().get(i-1));
		}}
		
		
		this.mainFrame.setVisible(true);
	}
	

}
