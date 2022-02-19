package obiektyWalki.akcje;

import obiektyWalki.ChangeLog;
import obiektyWalki.Postac;

/* Akcje to wlaciwie obiekty/klasy statyczne 
 * zawiera informacje, ktore mozna znalezc w podreczniku do ceribro
 * 
 * efekty akcji reaktywnych maja byc zapisane do changeloga, a tych nieraktywnych moga byc aplikowane do postaci od razu - moga tez byc aplikowane do changeloga 
 *  
 */

public interface Akcja {
	
	String nazwa = "Akcja";
	
	/*
	 * Pola do zaimplementowania
	 * int kosztakcje
	 * int kosztreakcje
	 * boolean jestReakcja; -- jezeli akcja jest reakcja - true
	 * boolean interaktywba; -- jezeli ma ona wykonawce i odbiorce - true; jezeli dziala ona jedynie na wykonawce false
	 * boolean jestReaktywna -- czy na ta akcje moze byc reakcja?
	 */
	
	
	public void efektNaWykonawce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog);// w przypadku kiedy nie ma odbiorcy - dac w miejsce odbiorcy - wykonawce
	public void efektNaOdbiorce(Postac wykonawca, Postac odbiorca, ChangeLog changeLog);// w przypadku kiedy nie ma odbiorcy - dac w miejsce odbiorcy - wykonawce
	public boolean warunekSpelniony(Postac wykonawca, Postac odbiorca, ChangeLog changeLog);// w przypadku kiedy nie ma odbiorcy - dac w miejsce odbiorcy - wykonawce
	public String getNazwa();
	public int getKosztAkcje();
	public int getKosztReakcje();
	public boolean jestReakcja();
	public boolean jestReaktywna();
	public boolean jestInteraktywna();
	
}
