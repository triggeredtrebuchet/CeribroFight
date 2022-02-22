
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import obiektyWalki.Cechy;
import obiektyWalki.Postac;
import obiektyWalki.Sedzia;
import obiektyWalki.akcje.AtakStandardowy;
import obiektyWalki.bronie.Bron;
import framework.*;

public class main{	
	public static void main(String[] args) throws IOException {
		
		Sedzia sedzia = new Sedzia();
		Postac kocor = new Postac("Kocor wrednyj",Color.black, 10, 10, 5, new Cechy(), new Bron(), new Bron());
		kocor.pozX += 30;
		kocor.pozY += 30;
		kocor.pozAlpha = Math.PI * 3/4;
		Postac krasnolod = new Postac("krasnolud Brzydkij",Color.BLUE, 10, 10, 5, new Cechy(), new Bron(), new Bron());
		krasnolod.pozAlpha = -Math.PI * 1/4;
		kocor.dodajAkcje(new AtakStandardowy());
		try {
			krasnolod.getCechy().rozwin("BD");
			krasnolod.getCechy().rozwin("BD");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Postac thull = new Postac("Thull Obslizgla morda",Color.BLUE, 10, 10, 5, new Cechy(), new Bron(), new Bron());
		thull.pozY += 50;
		
		sedzia.dodajPostac(krasnolod);
		sedzia.dodajPostac(kocor);
		sedzia.dodajPostac(thull);
		
		MainFrame mf = new MainFrame();
		Sedzia.przelosuj();
		mf.update(Sedzia.kolejnaRunda());
		
	}
}
