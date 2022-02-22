package obiektyWalki.akcje;

import obiektyWalki.ChangeLog;
import obiektyWalki.Postac;

/*
 * Reakcja ruchu jest uzupelnieniem akcji ruch - kazda postac moze ja wykonac, jednak niewyswietla sie w panelu akcji, gdyz odpala sie ja przez mape
 */
public class ReakcjaRuchu implements Akcja {

	@Override
	public void efektNaWykonawce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		// TODO Auto-generated method stub

	}

	@Override
	public void efektNaOdbiorce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean warunekSpelniony(Postac wykonawca, Postac odbiorca, ChangeLog changeLog) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNazwa() {
		return "Reakcja ruchu";
	}

	@Override
	public int getKosztAkcje() {
		return 0;
	}

	@Override
	public int getKosztReakcje() {
		return 1;
	}

	@Override
	public boolean jestReakcja() {
		return true;
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
