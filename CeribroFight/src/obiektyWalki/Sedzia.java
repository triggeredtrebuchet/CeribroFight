package obiektyWalki;

import java.util.ArrayList;
import java.util.Queue;

import obiektyWalki.akcje.Akcja;

/*
 * Klasa Sedzia pelni role mistrza gry
 * w hierarchi jest nad wszystkimi obiektami obiektow Walki, ale pod frame'em
 * 
 * Przechowuje informacje o kolejce i trzyma wszystkie postacie na polu walki w liscie
 * 
 */

public class Sedzia {

	private ArrayList<Postac> dostepnePostacie = new ArrayList<Postac>();
	private Queue<Postac> kolejka;
	private ChangeLog zaleglyChangeLog; // w razie czekania na akcje
	
	
	public Sedzia(ArrayList<Postac> dp) {
		this.dostepnePostacie = dp;
	}
	
	public Sedzia() {

	}
	
	public void dodajPostac(Postac p) {
		this.dostepnePostacie.add(p);
		this.kolejka.add(p);
	}
	
	public ChangeLog zapytanieOWykonanieAkcji(Postac wykonawca, Akcja akcja) {
		
		ChangeLog changeLog = new ChangeLog();
		/*
		 * zapytanie o Wykonbanie Akcji bedzie najczesciej wykorzystywana meteda przez framework
		 */
		if (!this.zaleglyChangeLog.equals(null)) {
			changeLog.nieZapytanoOWykonanieKoniecznejReakcji = true;
			return changeLog;
		}
		
		if(!wykonawca.czyStacNaAkcje(akcja)) {
			changeLog.zaMaloAkcji = true;
			return changeLog;
		}
		
		Postac odbiorca = wykonawca; // domyslnie
		if(akcja.jestInteraktywna()) {
			odbiorca = this.pierwszyCel(wykonawca);
			changeLog.odbiorca = odbiorca;
		}
		changeLog.wykonawca = wykonawca;
		
		
		if(!akcja.warunekSpelniony(wykonawca, odbiorca, changeLog))
			return changeLog;
		
		akcja.efektNaOdbiorce(wykonawca, odbiorca, changeLog);
		akcja.efektNaWykonawce(wykonawca, odbiorca, changeLog);
		
		if(akcja.jestReaktywna()) {
			changeLog.zapytanieOReakcje = true;
			this.zaleglyChangeLog = changeLog;
		}
		else
			changeLog.wprowadzZmiany();
		
		return changeLog;
	}
	
	public ChangeLog zapytanieOWykonanieReakcji(Akcja reakcja) {
		ChangeLog changeLog = this.zaleglyChangeLog;
		this.zaleglyChangeLog = null;
		return this.zapytanieOWykonanieAkcji(changeLog.odbiorca, reakcja);
	}
	
	private Postac pierwszyCel(Postac p) { // nieprzetestowane
		//metoda ta sluzy do selekcji celu do ataku
		//bierze pod uwage odchylenie od kierunku wskazywanego przez postac jak i odleglosc od niej
		
		
		Postac cel = null;
		double deltaCel = -1; // blad kwadratowy
		double alpha = p.pozAlpha;
		
		for(Postac c : dostepnePostacie) {
			if (c == p)
				continue;
			if (alpha >= 0 && alpha < Math.PI/2 && c.pozX < p.pozX && c.pozY < p.pozY)                    //ten blok kodu sprawdza czy c nie jest za plecami p
				continue;
			else if (alpha >= Math.PI/2 && alpha < Math.PI && c.pozX > p.pozX && c.pozY < p.pozY)
				continue;
			else if (alpha >= Math.PI && alpha < Math.PI * 3/2 && c.pozX > p.pozX && c.pozY > p.pozY)
				continue;
			else if (c.pozX < p.pozX && c.pozY > p.pozY)
				continue;
			
			//blad (delta) licze tak: (roznica konta)^2 * (odleglosc miedzy postaciami)^2 | w ten sposob nie trafi sie w jakas bardzo daleko postac, ktora przez przypadek stanela w lini ognia
			double delta = Math.pow(Math.atan((c.pozY - p.pozY)/(c.pozX - p.pozX)), 2) * Math.pow(Math.hypot(c.pozX - p.pozX, c.pozY - p.pozY),2);  
			
			if (deltaCel == -1 || deltaCel > delta) {
				cel = c;
				deltaCel = delta;
			}
		}		
		return cel;
	}

	
}
