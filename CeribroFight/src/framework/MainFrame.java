package framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import obiektyWalki.Cechy;
import obiektyWalki.ChangeLog;
import obiektyWalki.Postac;
import obiektyWalki.akcje.Akcja;
import obiektyWalki.akcje.AtakStandardowy;
import obiektyWalki.bronie.Bron;
/*
 * MainFramework zwiera wszystkie inne panele
 * Jest ciasno zwiazany ze swoimi panelami - to niby nie jest dobra metoda,
 *  ale jedynym innym wyjsciem byloby zeby wszystko znajdowalo sie w tym pliku i to bylby chyba wiekszy chaos
 *  
 *  TODO zrobic zapytanie i update
 */
public class MainFrame extends JFrame{

	Color kolorPaneluMapa = new Color(60,10,20);		//kolorki gradientu w tle
	Color kolorSrodka = new Color(60,20,50);
	Color kolorPaneluPostacie = new Color(31,40,102);
	Color kolorPaneluAkcje = new Color(60,10,20);
	
	PanelMapa panelMapa = new PanelMapa(this.kolorPaneluMapa, this.kolorSrodka);
	PanelInfo panelInfo = new PanelInfo(this, this.kolorSrodka, this.kolorPaneluPostacie, this.kolorPaneluAkcje);
	
	public static Postac aktywnaPostac; // niebezpieczne, ale za to jakie skuteczne

	public MainFrame(){
		
		ImageIcon image = new ImageIcon("logowalka.png");
		this.setIconImage(image.getImage());
		this.setTitle("Ceribro Fight");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,600);
		this.setLayout(new BorderLayout(5,5));
		this.add(this.panelMapa);
		this.add(this.panelInfo, BorderLayout.EAST);
		
		//---------------dodanie postaci do panelu panelPostacie
		Postac kocor = new Postac("Kocor wrednyj",Color.black, 10, 10, 5, new Cechy(), new Bron(), new Bron());
		kocor.pozX += 100;
		kocor.pozY += 100;
		kocor.pozAlpha = Math.PI * 3/4;
		Postac krasnolod = new Postac("krasnolud Brzydkij",Color.BLUE, 10, 10, 5, new Cechy(), new Bron(), new Bron());
		krasnolod.pozAlpha = -Math.PI * 1/4;
		kocor.dodajAkcje(new AtakStandardowy());
		try {
			krasnolod.getCechy().rozwin("BD");
			krasnolod.getCechy().rozwin("BD");
			krasnolod.getCechy().rozwin("BD");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.panelInfo.dodajPostac(krasnolod);
		this.panelInfo.dodajPostac(kocor);
		
		this.panelInfo.wyswietlAkcje(krasnolod);
		this.panelInfo.wyswietlAkcje(kocor);
		this.panelMapa.dodajPostac(krasnolod);
		this.panelMapa.dodajPostac(kocor);
		//---------------- blok powyzej to jedynie test - ma to byc obslugiwane przez sedzie, changelogi i zapytania
		
		
		this.setVisible(true); // to ma byc zawsze na koncu
	}

	public void update(ChangeLog changelog) {
		//TODO
	}
	public void wyslijZapytanie(Postac p, Akcja a) {
		System.out.println(p);
		System.out.println(a);
	}
	
}
