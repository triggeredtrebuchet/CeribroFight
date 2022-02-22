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
	public boolean zapytanieORuch = false;
	public boolean nieZapytanoOWykonanieKoniecznejReakcji = false;
	public String informacjeZAkcji = "";
	
	public int obrazeniaGardaDlaWykonawcy = 0;
	public int obrazeniaHPDlaWykonawcy = 0;
	public int obrazeniaZKDlaWykonawcy = 0;
	
	public int obrazeniaGardaDlaOdbiorcy = 0;
	public int obrazeniaHPDlaOdbiorcy = 0;
	public int obrazeniaZKDlaOdbiorcy = 0;
	
	
	public void wprowadzZmiany() {
		if(!(this.odbiorca == null)) {
			this.odbiorca.obrGarda(this.obrazeniaGardaDlaOdbiorcy);
			this.odbiorca.obrHP(this.obrazeniaHPDlaOdbiorcy);
			this.odbiorca.obrZimnaKrew(this.obrazeniaZKDlaOdbiorcy);
		}
		
		if(!(this.wykonawca == null)) {
			this.wykonawca.obrGarda(this.obrazeniaGardaDlaWykonawcy);
			this.wykonawca.obrHP(this.obrazeniaHPDlaWykonawcy);
			this.wykonawca.obrZimnaKrew(this.obrazeniaZKDlaWykonawcy);
		}
	}
	
	public void resetDoReakcji() {
		this.zaMaloAkcji = false;
		this.waronekAkcjiNieSpelniony = false;
		this.zapytanieOReakcje = false;
		this.nieZapytanoOWykonanieKoniecznejReakcji = false;
		this.informacjeZAkcji = "";
	}
	
	public String toString() {
		String ret = "\n";
		
		if (this.zaMaloAkcji) {
			ret += "Za malo akcji\n";
		}
		if (this.waronekAkcjiNieSpelniony) {
			ret += "waronekAkcjiNieSpelnion\n";
		}
		if (this.nieZapytanoOWykonanieKoniecznejReakcji) {
			ret += "nieZapytanoOWykonanieKoniecznejReakcji\n";
		}
		if (this.zapytanieOReakcje) {
			ret += this.odbiorca.getNazwa() + " Wykonaj reakcje\n";
		}
		else {
			if (this.wykonawca != null) {
				ret += "Wykonawca: " + this.wykonawca.getNazwa() + "\n";
				if(this.obrazeniaGardaDlaWykonawcy != 0)
					ret += "Obrazenia Gardy dla wykonawcy: " + this.obrazeniaGardaDlaWykonawcy + "\n";
				if(this.obrazeniaHPDlaWykonawcy != 0)
					ret += "Obrazenia HP dla wykonawcy: " + this.obrazeniaHPDlaWykonawcy + "\n";
				if(this.obrazeniaHPDlaWykonawcy != 0)
					ret += "Obrazenia Zimnej Krwi dla wykonawcy: " + this.obrazeniaZKDlaWykonawcy + "\n";
			}
			if (this.odbiorca != null && this.odbiorca != this.wykonawca) {
				ret += "Odbiorca: " + this.odbiorca.getNazwa() + "\n";
				if(this.obrazeniaGardaDlaOdbiorcy != 0)
					ret += "Obrazenia Gardy dla odbiorcy: " + this.obrazeniaGardaDlaOdbiorcy + "\n";
				if(this.obrazeniaHPDlaOdbiorcy != 0)
					ret += "Obrazenia HP dla odbiorcy: " + this.obrazeniaHPDlaOdbiorcy + "\n";
				if(this.obrazeniaZKDlaOdbiorcy != 0)
					ret += "Obrazenia Zimnej Krwi dla odbiorcy: " + this.obrazeniaZKDlaOdbiorcy + "\n";
			}
		}
		
		if (this.informacjeZAkcji != "") {
			ret += this.informacjeZAkcji;
		}
		
		return ret;
	}
	
}
