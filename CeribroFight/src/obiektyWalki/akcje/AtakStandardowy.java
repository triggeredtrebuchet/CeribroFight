package obiektyWalki.akcje;

import obiektyWalki.ChangeLog;
import obiektyWalki.Postac;
import obiektyWalki.bronie.Bron;

public class AtakStandardowy implements Akcja{

	protected final String nazwa = "Atak standardowy";
	protected final int kosztAkcje = 1;
	protected final int kosztReakcje = 0;
	private Bron bronAtk;
	protected boolean reakcja = false;
	protected boolean reaktywna = true;
	protected boolean interaktywna = true;
	
	@Override
	public void efektNaOdbiorce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog){
		//------zadawanie obrazen----------------
		
		try {
			if (odbiorca.getGarda() > 0)
				changeLog.obrazeniaGardaDlaOdbiorcy = bronAtk.obrazeniaGarda(wykonawca.getCechy());
			else
				changeLog.obrazeniaGardaDlaOdbiorcy = bronAtk.obrazeniaCialo(wykonawca.getCechy()); // po co ja dawalem wyjatek do sumy??? tyle z tym klopotu
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean warunekSpelniony(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		//----Sprawdzenie czy jest w zasiegu--------
		this.bronAtk = wykonawca.prawareka;
		
		if (!wykonawca.prawaRekaAktywna)
			this.bronAtk = wykonawca.lewareka;
		
		if (Math.hypot(odbiorca.pozX - wykonawca.pozX, odbiorca.pozY - wykonawca.pozY) > bronAtk.getZasieg())
			return false;
		else
			return true;
	}

	@Override
	public String getNazwa() {
		return nazwa;
	}

	@Override
	public int getKosztAkcje() {
		return kosztAkcje;
	}

	@Override
	public int getKosztReakcje() {
		return kosztReakcje;
	}

	@Override
	public void efektNaWykonawce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean jestReakcja() {
		return this.reakcja;
	}

	@Override
	public boolean jestReaktywna() {
		return this.reaktywna;
	}

	@Override
	public boolean jestInteraktywna() {
		
		return this.interaktywna;
	}


}
