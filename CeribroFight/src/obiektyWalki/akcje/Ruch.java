package obiektyWalki.akcje;

import obiektyWalki.ChangeLog;
import obiektyWalki.Postac;


public class Ruch implements Akcja{

	@Override
	public void efektNaWykonawce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		//wykonawca.setIloscReakcji(wykonawca.getIloscReakcji() + 1);
		changeLog.zapytanieORuch = true;
		
	}

	@Override
	public void efektNaOdbiorce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean warunekSpelniony(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		
		changeLog.informacjeZAkcji = "Wykonaj ruch\n";
		return true;
	}

	@Override
	public String getNazwa() {
		return "Ruch";
	}

	@Override
	public int getKosztAkcje() {
		return 1;
	}

	@Override
	public int getKosztReakcje() {
		return 0;
	}

	@Override
	public boolean jestReakcja() {
		return false;
	}

	@Override
	public boolean jestReaktywna() {
		return false;
	}

	@Override
	public boolean jestInteraktywna() {
		return false;
	}

}
