package obiektyWalki.bronie;

import obiektyWalki.Cechy;

public class Bron {

	private String nazwa = "Bron jednoreczna";
	private int minObrazenia = 0;
	private int maxObrazenia = 20;
	private int rozrzut = 0; //kosc: 20 - k20 itd.
	private int zasieg = 80;  //1 * 40 - krotki; 2 * 40 - normalny; 3 * 40 - dlugi | 40pxl - jedna jednostka
	private int defIloscAkcji = 2;
	private String cechaBazowa = "BD";
	
	public Bron(String nazwa, int minO, int maxO, int rozrzut, int zasieg, String cechaB) {
		this.setNazwa(nazwa);
		this.minObrazenia = minO;
		this.maxObrazenia = maxO;
		this.rozrzut = rozrzut;
		this.setZasieg(zasieg);
		this.cechaBazowa = cechaB;
	}
	
	public Bron(int minO, int maxO, int rozrzut) {
		this.minObrazenia = minO;
		this.maxObrazenia = maxO;
		this.rozrzut = rozrzut;
	}
	
	public Bron(){};
	
	public Bron BronLewaZajeta() {
		return new Bron(0,0,0);
	}
	
	public int obrazeniaGarda(Cechy cechy) throws Exception {
		
		if (cechy.suma(this.cechaBazowa) < this.minObrazenia)
			return Math.round(cechy.suma(this.cechaBazowa)/2);
		
		return Math.min(cechy.suma(this.cechaBazowa), this.maxObrazenia);
	}
	
	public int obrazeniaCialo(Cechy cechy) throws Exception {
		int baza = this.obrazeniaGarda(cechy) - Math.round(rozrzut/2);
		baza += Math.round(Math.random() * this.rozrzut);
		return baza;
	}

	
	public int getZasieg() {
		return zasieg;
	}

	public void setZasieg(int zasieg) {
		this.zasieg = zasieg;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	
}
