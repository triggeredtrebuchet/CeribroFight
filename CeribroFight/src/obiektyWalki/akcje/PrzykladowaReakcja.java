package obiektyWalki.akcje;

import obiektyWalki.ChangeLog;
import obiektyWalki.Postac;

public class PrzykladowaReakcja implements Akcja{
	
	private String nazwa = "Reakcja";
	private int kosztAkcje = 0;
	private int kosztReakcje = 1;
	private boolean reakcja = true;
	private boolean reaktywna = false;
	private boolean interaktywna = true;
	
	public void efektNaOdbiorce(Postac p, Postac o, ChangeLog changeLog) {
		System.out.println("Zareagowelem, lecz nic nie moge z tym zrobic");
	}
	
	public String getNazwa() {
		return nazwa;
	}

	public int getKosztAkcje() {
		return kosztAkcje;
	}

	public int getKosztReakcje() {
		return kosztReakcje;
	}

	@Override
	public void efektNaWykonawce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean warunekSpelniony(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean jestReakcja() {
		// TODO Auto-generated method stub
		return this.reakcja;
	}

	@Override
	public boolean jestReaktywna() {
		// TODO Auto-generated method stub
		return this.reaktywna;
	}

	@Override
	public boolean jestInteraktywna() {
		return this.interaktywna;
	}

}
