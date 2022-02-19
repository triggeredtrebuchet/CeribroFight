package framework;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import obiektyWalki.Postac;


/*AwatarPostac to przycisk reprezentujacy postac
 * Na razie wyswietla imie, hp, garde i ZK
 * dodac wyswietlanie ilosci akcji, broni, [pancerza, jak zostana wprowadzone pancerze
 */

public class AwatarPostac extends JButton{
	
	Postac postac = new Postac();
	JLabel imie = new JLabel();
	JProgressBar iloscHP = new JProgressBar();
	JProgressBar iloscGarda = new JProgressBar();
	JProgressBar iloscZimnaKrew = new JProgressBar();
	JLabel bronPrawa = new JLabel();
	JLabel bronLewa = new JLabel();
	JLabel iloscAkcji = new JLabel();
	
	
	public AwatarPostac(Postac postac, int miejsceWKolejce) {
		super();
		this.postac = postac;
		this.setLayout(null);
		
		this.imie.setText(postac.getNazwa());
		
		String info = Integer.toString(postac.gethP()) + "/" + Integer.toString(postac.getMaxHP());
		this.iloscHP = new JProgressBar(0, postac.getMaxHP());
		this.iloscHP.setValue(postac.gethP());
		this.iloscHP.setString(info);
		
		info = Integer.toString(postac.getGarda()) + "/" + Integer.toString(postac.getMaxGarda());
		this.iloscGarda = new JProgressBar(0, postac.getMaxGarda());
		this.iloscGarda.setValue(postac.getGarda());
		this.iloscGarda.setString(info);
		
		info = Integer.toString(postac.getZimnaKrew()) + "/" + Integer.toString(postac.getMaxZimnaKrew());
		this.iloscZimnaKrew = new JProgressBar(0, postac.getMaxZimnaKrew());	
		this.iloscZimnaKrew.setValue(postac.getZimnaKrew());
		this.iloscZimnaKrew.setString(info);
		
		this.add(this.imie);
		this.add(this.iloscHP);
		this.add(this.iloscGarda);
		this.add(this.iloscZimnaKrew);
		
		this.format(miejsceWKolejce);
		
	}
	
	private void format(int miejsceWKolejce) {
		
		this.imie.setHorizontalAlignment(JLabel.CENTER);
		this.imie.setForeground(Color.white);
		
		this.iloscHP.setForeground(Color.red);
		this.iloscHP.setBackground(new Color(50,10,10));
		this.iloscHP.setStringPainted(true);
		
		this.iloscGarda.setForeground(Color.gray);
		this.iloscGarda.setBackground(Color.DARK_GRAY);
		this.iloscGarda.setStringPainted(true);
		
		this.iloscZimnaKrew.setForeground(new Color(255,153,0));
		this.iloscZimnaKrew.setBackground(new Color(44,25,0));
		this.iloscZimnaKrew.setStringPainted(true);
		
		this.imie.setBounds(5, 5, 380, 20);
		this.iloscHP.setBounds(5, 30, 100, 15);
		this.iloscGarda.setBounds(5, 50, 100, 15);
		this.iloscZimnaKrew.setBounds(5, 70, 100, 15);
		
		
		this.setOpaque(false);				//zmienia na transparentne tlo przycisku
		this.setContentAreaFilled(false); 	//-||-
		this.setBounds(5, 5 + 100 * miejsceWKolejce, 390, 100);
		
	}
	
	public void update() {
		String info = Integer.toString(postac.gethP()) + "/" + Integer.toString(postac.getMaxHP());
		this.iloscHP.setValue(postac.gethP());
		this.iloscHP.setString(info);
		
		info = Integer.toString(postac.getGarda()) + "/" + Integer.toString(postac.getMaxGarda());
		this.iloscGarda.setValue(postac.getGarda());
		this.iloscGarda.setString(info);
		
		info = Integer.toString(postac.getZimnaKrew()) + "/" + Integer.toString(postac.getMaxZimnaKrew());
		this.iloscZimnaKrew.setValue(postac.getZimnaKrew());
		this.iloscZimnaKrew.setString(info);
	}
	
	public String toString() {
		return this.postac.getNazwa() + this.postac.getDostepneAkcje().toString();
	}
	
}
