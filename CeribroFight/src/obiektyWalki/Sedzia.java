package obiektyWalki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import obiektyWalki.*;
import obiektyWalki.akcje.Akcja;

/*
 * Klasa Sedzia pelni role mistrza gry
 * w hierarchi jest nad wszystkimi obiektami obiektow Walki, ale pod frame'em
 * 
 * Przechowuje informacje o kolejce i trzyma wszystkie postacie na polu walki w liscie
 * 
 */

public class Sedzia {

	public static ArrayList<Postac> dostepnePostacie = new ArrayList<Postac>();
	private static Queue<Postac> kolejka = new LinkedList<>();
	private static ChangeLog zaleglyChangeLog; // w razie czekania na akcje
	private static int[][] inicjatywa;
	
	
	public Sedzia(ArrayList<Postac> dp) {
		dostepnePostacie = dp;
	}
	
	public Sedzia() {

	}
	
	public static void dodajPostac(Postac p) {
		dostepnePostacie.add(p);
		kolejka.add(p);
	}
	
	public static ChangeLog przelosuj() {
		kolejka.clear();
		int[][] wartlos = new int [dostepnePostacie.size()][2]; //[index] [wartosc losowania]
		
		for(int i = 0; i < wartlos.length; i ++) {
			wartlos[i][0] = i;
			wartlos[i][1] = dostepnePostacie.get(i).getCechy().testZW(0, dostepnePostacie.get(i).getNazwa());
		}
		
		Arrays.sort(wartlos, new Comparator<int[]>() {
	          @Override              
	          // porownaj po drugiej kolumnie - kolumnie wartosci
	          public int compare(final int[] entry1, 
	                             final int[] entry2) {

	            if (entry1[1] < entry2[1])
	                return 1;
	            else
	                return -1;
	          }
	        });
		
		inicjatywa = wartlos;		
		return resetKolejki();
	}
	
	private static ChangeLog resetKolejki() {
		kolejka.clear();
		for(int i = 0; i < inicjatywa.length; i++) {
			kolejka.add(dostepnePostacie.get(inicjatywa[i][0]));
		}
		ChangeLog ch = new ChangeLog();
		ch.kolejnaDomyslniePostac = kolejka.peek();
		
		return ch;
	}
	
	public static ChangeLog kolejnaRunda() {
		for(Postac p: dostepnePostacie) {
			p.resetAkcji();
		}
		
		return resetKolejki();
	}
	
	public static ChangeLog zapytanieOWykonanieAkcji(Postac wykonawca, Akcja akcja) {
		
		ChangeLog changeLog = new ChangeLog();
		Postac odbiorca = wykonawca; // domyslnie
		/*
		 * zapytanie o Wykonbanie Akcji bedzie najczesciej wykorzystywana meteda przez framework
		 * 
		 */
		if (!(zaleglyChangeLog == null)) {
			if (!akcja.jestReakcja() || wykonawca != zaleglyChangeLog.odbiorca) {
				changeLog.nieZapytanoOWykonanieKoniecznejReakcji = true;
				return changeLog;
			}
			else {
				return zapytanieOWykonanieReakcji(akcja);
			}
		}
		else if (akcja.jestReakcja()) {
			return changeLog;
		}
		

		if(!wykonawca.czyStacNaAkcje(akcja)) {
			changeLog.zaMaloAkcji = true;
			return changeLog;
		}
		
		if(!akcja.warunekSpelniony(wykonawca, odbiorca, changeLog))
			return changeLog;
		
		
		else {
			if(akcja.jestInteraktywna()) {
				odbiorca = pierwszyCel(wykonawca);
				changeLog.odbiorca = odbiorca;
			}
			changeLog.wykonawca = wykonawca;
		}
		akcja.efektNaOdbiorce(wykonawca, odbiorca, changeLog);
		akcja.efektNaWykonawce(wykonawca, odbiorca, changeLog);

		wykonawca.zaplacZaAkcje(akcja);
		if (wykonawca.getIloscAkcjiL() + wykonawca.getIloscAkcjiR() == 0)
			kolejka.remove(wykonawca);
		if (kolejka.isEmpty())
			kolejnaRunda();
		
		
		if(akcja.jestReaktywna() && odbiorca.getIloscReakcji() != 0) {			
			changeLog.zapytanieOReakcje = true;
			zaleglyChangeLog = changeLog;
			changeLog.kolejnaDomyslniePostac = odbiorca;
		}
		else if(changeLog.zapytanieORuch)
			changeLog.kolejnaDomyslniePostac = wykonawca;
		else {
			changeLog.wprowadzZmiany();
			changeLog.kolejnaDomyslniePostac = kolejka.peek();
		}
		
		return changeLog;
	}
	
	private static ChangeLog zapytanieOWykonanieReakcji(Akcja reakcja) {
		ChangeLog changeLog = zaleglyChangeLog;
		zaleglyChangeLog = null;
		changeLog.resetDoReakcji();
		
		if(!changeLog.odbiorca.czyStacNaAkcje(reakcja)) {
			changeLog.zaMaloAkcji = true;
			changeLog.wprowadzZmiany();
			return changeLog;
		}
		
		if(!reakcja.warunekSpelniony(changeLog.odbiorca, changeLog.wykonawca, changeLog)) {
			changeLog.wprowadzZmiany();
			return changeLog;
		}
		

		changeLog.odbiorca.zaplacZaAkcje(reakcja);
		reakcja.efektNaOdbiorce(changeLog.odbiorca, changeLog.wykonawca, changeLog);
		reakcja.efektNaWykonawce(changeLog.odbiorca, changeLog.wykonawca, changeLog);
		
		if(reakcja.jestReaktywna()) {
			changeLog.zapytanieOReakcje = true;
			zaleglyChangeLog = changeLog;
			changeLog.kolejnaDomyslniePostac = changeLog.wykonawca;
		}
		else {
			changeLog.wprowadzZmiany();
			changeLog.kolejnaDomyslniePostac = kolejka.peek();
		}
		
		return changeLog;
	}
	
	private static Postac pierwszyCel(Postac p) { // nieprzetestowane
		//metoda ta sluzy do selekcji celu do ataku
		//bierze pod uwage odchylenie od kierunku wskazywanego przez postac jak i odleglosc od niej
		
		if(p.pozAlpha > Math.PI) {
			double r = (p.pozAlpha - Math.PI)/(2* Math.PI); // ilosc nadmiarowych obrotow w lewo
			p.pozAlpha -= Math.ceil(r) * 2 * Math.PI; // cofnij sie o pelne katy
		}
		else if (p.pozAlpha < -Math.PI) {
			double r = (- Math.PI - p.pozAlpha)/(2* Math.PI); // ilosc nadmiarowych obrotow w prawo
			p.pozAlpha += Math.ceil(r) * 2 * Math.PI; // cofnij sie o pelne katy
		}
		
		Postac cel = null;
		double deltaCel = -1; // blad kwadratowy
		double alpha = p.pozAlpha;
		
		for(Postac c : dostepnePostacie) {
			if (c == p) continue;
			/*
			 *  if (alpha >= 0 && alpha < Math.PI/2 && c.pozX < p.pozX
			 * && c.pozY < p.pozY) //ten blok kodu sprawdza czy c nie jest za plecami p
			 * continue; else if (alpha >= Math.PI/2 && alpha < Math.PI && c.pozX > p.pozX
			 * && c.pozY < p.pozY) continue; else if (alpha >= Math.PI && alpha < Math.PI *
			 * 3/2 && c.pozX > p.pozX && c.pozY > p.pozY) continue; else if (c.pozX < p.pozX
			 * && c.pozY > p.pozY) continue;
			 */
			//blad (delta) licze tak: (roznica konta)^2 * (odleglosc miedzy postaciami)^2 | w ten sposob nie trafi sie w jakas bardzo daleko postac, ktora przez przypadek stanela w lini ognia
			double delta = Math.pow(Math.atan2((c.pozY - p.pozY),(c.pozX - p.pozX)) + p.pozAlpha, 2) * Math.pow(Math.hypot(c.pozX - p.pozX, c.pozY - p.pozY),2);  
			
			if (deltaCel == -1 || deltaCel > delta) {
				cel = c;
				deltaCel = delta;
			}
		}		
		
		System.out.println(deltaCel);
		return cel;
	}

	
}
