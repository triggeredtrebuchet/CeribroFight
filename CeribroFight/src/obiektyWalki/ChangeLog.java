package obiektyWalki;


/*
 * ChangeLog zawiera inforamcje o zmianie stanu pewnej postaci p - wskaznik na nia
 *  
 */
public class ChangeLog {

	public Postac kolejnaDomyslniePostac = null;
	
	public Postac wykonawca = null;
	public Postac odbiorca = null;
	public boolean zaMaloAkcji = false;
	public boolean waronekAkcjiNieSpelniony = false;
	public boolean zapytanieOReakcje = false;
	public boolean nieZapytanoOWykonanieKoniecznejReakcji = false;
	
	public int obrazeniaGardaDlaWykonawcy = 0;
	public int obrazeniaHPDlaWykonawcy = 0;
	public int obrazeniaZKDlaWykonawcy = 0;
	
	public int obrazeniaGardaDlaOdbiorcy = 0;
	public int obrazeniaHPDlaOdbiorcy = 0;
	public int obrazeniaZKDlaOdbiorcy = 0;
	
	
	public void wprowadzZmiany() {
		if(!this.odbiorca.equals(null)) {
			this.odbiorca.obrGarda(this.obrazeniaGardaDlaOdbiorcy);
			this.odbiorca.obrHP(this.obrazeniaHPDlaOdbiorcy);
			this.odbiorca.obrZimnaKrew(this.obrazeniaZKDlaOdbiorcy);
		}
		
		if(!this.wykonawca.equals(null)) {
			this.wykonawca.obrGarda(this.obrazeniaGardaDlaWykonawcy);
			this.wykonawca.obrHP(this.obrazeniaHPDlaWykonawcy);
			this.wykonawca.obrZimnaKrew(this.obrazeniaZKDlaWykonawcy);
		}
	}
	
}
