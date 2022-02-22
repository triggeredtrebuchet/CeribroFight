package obiektyWalki;

import java.awt.Color;
import java.util.ArrayList;

import obiektyWalki.akcje.*;
import obiektyWalki.bronie.Bron;


/*
 * Klasa postac to wlasciwie karta postaci z ceribro przechowujaca informacje o walce
 */
public class Postac {
	
	private String nazwa = "Nieznajomy";
	public Color kolor; // reprezentacja na mapie
	
	private int iloscAkcjiR = 0;//ilosc akcji na prawej rece
	private int iloscAkcjiL = 0;//ilosc akcji na lewej rece
	private int maxIloscAkcjiR = 2;
	private int maxIloscAkcjiL = 1;
	private int iloscReakcji = 0;
	private int maxIloscReakcji = 1;
	
	private int hP = 0;
	private int garda = 0;
	private int zimnaKrew = 0;
	
	private int maxHP = 0;
	private int maxGarda = 0;
	private int maxZimnaKrew = 0;
	
	private int zakresRuchu = 5;
	
	private Cechy cechy = new Cechy(); //zawiera talenty i ich rozwiniecia
	public Bron prawareka = new Bron();
	public Bron lewareka = new Bron();
	public boolean prawaRekaAktywna = true; // czy nastepna akcja, o ile to mozliwe, zostanie wykonana z prawej reki - w przeciwnym raze - z lewej
	private ArrayList<Bron> ekwipunek = new ArrayList<Bron>(); // TODO akcje zmianiania broni - niski priorytet
	
	private ArrayList<Akcja> dostepneAkcje = new ArrayList<Akcja>() {{
		add(new AtakStandardowy());
		add(new Ruch());
	}};
	
	private ArrayList<Akcja> dostepneReakcje = new ArrayList<Akcja>() {{
		add(new PrzykladowaReakcja());
	}};
	


	public int pozX = 200; //pozycja: X - poziom; y - pion (pixele); Alpha - kat od poziomu (Radiany) | jak w matematyce 
	public int pozY = 200;
	public double pozAlpha = 0.0;
	
	
	public Postac(String nazwa,Color kolor, int maxHP, int maxGarda, int maxZimnaKrew, Cechy cechy, Bron prawas, Bron lewas) {
		this.setNazwa(nazwa);
		this.kolor = kolor;
		this.maxHP = maxHP;
		this.maxGarda = maxGarda;
		this.maxZimnaKrew = maxZimnaKrew;
		
		this.hP = this.maxHP;
		this.garda = this.maxGarda;
		this.zimnaKrew = this.maxZimnaKrew;
		
		try {
			this.zakresRuchu = cechy.suma("ZW") + 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.cechy = cechy;
		this.prawareka = prawas;
		this.lewareka = lewas;
	}
	
	public Postac() {		
	}


	public String getNazwa() {
		return nazwa;
	}


	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}


	public int getIloscAkcjiR() {
		return iloscAkcjiR;
	}


	public int getIloscAkcjiL() {
		return iloscAkcjiL;
	}


	public int getIloscReakcji() {
		return iloscReakcji;
	}


	public void setIloscReakcji(int iloscReakcji) {
		this.iloscReakcji = iloscReakcji;
	}


	public int gethP() {
		return hP;
	}


	public boolean obrHP(int hP) {//zwraca prawde, jesli postac zmarla od obrazen
		this.hP -= hP;
		if (this.hP < 0) {
			this.hP = 0;
			return true;
		}
		else
			return false;
	}


	public int getGarda() {
		return garda;
	}


	public void obrGarda(int garda) {
		this.garda -= garda;
		if (this.garda < 0)
			this.garda = 0;
	}


	public int getZimnaKrew() {
		return zimnaKrew;
	}


	public void obrZimnaKrew(int zimnaKrew) {
		this.zimnaKrew -= zimnaKrew;
		if (this.zimnaKrew <= 0) {
			this.zimnaKrew = 0;
			this.panika();
		}
			
	}
	
	public void panika() {
		System.out.println(this.nazwa + " panikuje!");
	}

	public void setMaxIloscAkcjiR(int maxIloscAkcjiR) {
		this.maxIloscAkcjiR = maxIloscAkcjiR;
	}


	public void setMaxIloscAkcjiL(int maxIloscAkcjiL) {
		this.maxIloscAkcjiL = maxIloscAkcjiL;
	}


	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}


	public void setMaxGarda(int maxGarda) {
		this.maxGarda = maxGarda;
	}


	public void setMaxZimnaKrew(int maxZimnaKrew) {
		this.maxZimnaKrew = maxZimnaKrew;
	}


	
	public Cechy getCechy() {
		return cechy;
	}


	public Bron getPrawareka() {
		return prawareka;
	}


	public void setPrawareka(Bron prawareka) {
		this.prawareka = prawareka;
	}


	public Bron getLewareka() {
		return lewareka;
	}


	public void setLewareka(Bron lewareka) {
		this.lewareka = lewareka;
	}


	public ArrayList<Bron> getEkwipunek() {
		return ekwipunek;
	}


	public void setEkwipunek(ArrayList<Bron> ekwipunek) {
		this.ekwipunek = ekwipunek;
	}


	public ArrayList<Akcja> getDostepneAkcje() {
		return dostepneAkcje;
	}


	public void dodajAkcje(Akcja akcja) {
		this.dostepneAkcje.add(akcja);
	}


	public int getMaxIloscAkcjiR() {
		return maxIloscAkcjiR;
	}


	public int getMaxIloscAkcjiL() {
		return maxIloscAkcjiL;
	}


	public int getMaxHP() {
		return maxHP;
	}


	public int getMaxGarda() {
		return maxGarda;
	}


	public int getMaxZimnaKrew() {
		return maxZimnaKrew;
	}


	public void setCechy(Cechy cechy) {
		this.cechy = cechy;
	}
	
	
	
	public ArrayList<Akcja> getDostepneReakcje() {
		return dostepneReakcje;
	}
	
	public int getZakresRuchu() {
		return zakresRuchu;
	}

	public void setZakresRuchu(int zakresRuchu) {
		this.zakresRuchu = zakresRuchu;
	}

	public void dodajReakcje(Akcja reakcja) {
		this.dostepneReakcje.add(reakcja);
	}
	
	public boolean czyStacNaAkcje(Akcja akcja) { // zwraca true jesli postac moze wykonac akcje ( jesli chodzi o to, czy starczy jej na to akcji)
		
		
		if (this.iloscReakcji < akcja.getKosztReakcje()) {
			return false;
		}
		
		
		if (this.prawaRekaAktywna) {
			if (this.iloscAkcjiR < akcja.getKosztAkcje())
				return false;
		}
		else {
			if (this.iloscAkcjiL < akcja.getKosztAkcje())
				return false;
		}
		
		return true;
	}
	
	public void zaplacZaAkcje(Akcja akcja) {
		this.iloscReakcji -= akcja.getKosztReakcje();
		if (this.prawaRekaAktywna) 
			this.iloscAkcjiR -= akcja.getKosztAkcje();
		else 
			this.iloscAkcjiL -= akcja.getKosztAkcje();
	}
	
	public void resetAkcji() {
		this.iloscAkcjiL = this.maxIloscAkcjiL;
		this.iloscAkcjiR = this.maxIloscAkcjiR;
		this.iloscReakcji = this.maxIloscReakcji;
	}
	
	
	
}
