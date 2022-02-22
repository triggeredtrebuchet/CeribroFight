package obiektyWalki;

import java.util.HashMap;

import javax.swing.JOptionPane;

/*Klase Cechy mozna utozsamiac z blokiem cech z karty postaci Ceribro
 * jest utworzona na bazie stringow
 * suma(String) - zwraca sume
 * rozwin(String) - rozwija o jeden ceche
 * 
 * konstruktor przyjmuje hashMape wg. wzoru:
 * 
 * static HashMap<String, Integer[]> param = new HashMap<String, Integer[]>(){{
		put(String cecha, {int talent, int kosc});
		...
	}};
 *  
 * TODO zastanowic sie czy nie lepsza opcja bedzie zromienie osobnych metod na kazda ceche, zamiast tych switchy i slownikow
 * wtedy wiele klas nie bedzie potrzebowac deklaracji wyrzucania bledu
 */

public class Cechy {
	
	private int[] SW = {3, 20};
	private int[] REF = {3, 20};
	private int[] ZW = {3, 20};
	private int[] BD = {3, 20};
	private int[] INT = {3, 20};
	private int[] DB = {3, 12};
	private int[] CH = {3, 20};
	
	static HashMap<Integer, Integer> doSumy = new HashMap<Integer, Integer>(){{
		put(20, 0);
		put(12, 1);
		put(10, 2);
		put(8, 3);
		put(6, 4);
		put(4, 5);
	}};
	static int[] doRozwin = {20, 12, 10, 8, 6, 4}; 
	
	
	
	public Cechy(HashMap<String, Integer[]> cechy) {
		
		if (cechy.containsKey("SW")) {
			this.SW[0] = cechy.get("SW")[0];
			this.SW[1] = cechy.get("SW")[1];
		}
		
		if (cechy.containsKey("REF")) {
			this.REF[0] = cechy.get("REF")[0];
			this.REF[1] = cechy.get("REF")[1];
		}
		
		if (cechy.containsKey("ZW")) {
			this.ZW[0] = cechy.get("ZW")[0];
			this.ZW[1] = cechy.get("ZW")[1];
		}
		
		if (cechy.containsKey("BD")) {
			this.BD[0] = cechy.get("BD")[0];
			this.BD[1] = cechy.get("BD")[1];
		}
		
		if (cechy.containsKey("INT")) {
			this.INT[0] = cechy.get("INT")[0];
			this.INT[1] = cechy.get("INT")[1];
		}
		
		if (cechy.containsKey("DB")) {
			this.DB[0] = cechy.get("DB")[0];
			this.DB[1] = cechy.get("DB")[1];
		}
		
		if (cechy.containsKey("CH")) {
			this.CH[0] = cechy.get("CH")[0];
			this.CH[1] = cechy.get("CH")[1];
		}
		
	}
	
	public Cechy() {
		
	}
	
	//TODO cechy automatycznie losowane z przeciazeniami na kategorie (nazwy cech) - "SW", "BD", "REF"
	
	public int suma(String cecha) throws Exception {
		switch(cecha) {
		case "SW":
			return this.SW[0] + Cechy.doSumy.get(this.SW[1]);
		case "REF":
			return this.REF[0] + Cechy.doSumy.get(this.REF[1]);
		case "ZW":
			return this.ZW[0] + Cechy.doSumy.get(this.ZW[1]);
		case "BD":
			return this.BD[0] + Cechy.doSumy.get(this.BD[1]);
		case "INT":
			return this.INT[0] + Cechy.doSumy.get(this.INT[1]);
		case "DB":
			return this.DB[0] + Cechy.doSumy.get(this.DB[1]);
		case "CH":
			return this.CH[0] + Cechy.doSumy.get(this.CH[1]);
		default:
			throw new Exception("Nieprawidlowy skrot cechy dla funkcji \"suma\"");
		}
	}
	
	public void rozwin(String cecha) throws Exception{
		switch(cecha) {
		case "SW":
			this.SW[1] = this.doRozwin[this.doSumy.get(this.SW[1]) + 1];
			break;
		case "REF":
			this.REF[1] = this.doRozwin[this.doSumy.get(this.REF[1]) + 1];
			break;
		case "ZW":
			this.ZW[1] = this.doRozwin[this.doSumy.get(this.ZW[1]) + 1];
			break;
		case "BD":
			this.BD[1] = this.doRozwin[this.doSumy.get(this.BD[1]) + 1];
			break;
		case "INT":
			this.INT[1] = this.doRozwin[this.doSumy.get(this.INT[1]) + 1];
			break;
		case "DB":
			this.DB[1] = this.doRozwin[this.doSumy.get(this.DB[1]) + 1];
			break;
		case "CH":
			this.CH[1] = this.doRozwin[this.doSumy.get(this.CH[1]) + 1];
			break;
		default:
			throw new Exception("Nieprawidlowy skrot cechy dla funkcji \"rozwin\"");
		}
	}
	
	public int testSW(int mod, String name) {
		String temp = JOptionPane.showInputDialog("Wynik rzutu kosci testu SW dla " + name);
		int bias;
		if (temp == "")
			bias = (int) Math.floor(Math.random() * this.SW[1] + 1);
		else
			bias = Integer.parseInt(temp);
		return this.SW[0] - bias + mod;
	}
	
	public int testREF(int mod, String name) {
		String temp = JOptionPane.showInputDialog("Wynik rzutu kosci testu REF dla " + name);
		int bias;
		if (temp == "")
			bias = (int) Math.floor(Math.random() * this.REF[1] + 1);
		else
			bias = Integer.parseInt(temp);
		return this.REF[0] - bias + mod;
	}
	
	public int testZW(int mod, String name) {
		String temp = JOptionPane.showInputDialog("Wynik rzutu kosci testu ZW dla " + name);
		int bias;
		if (temp.equals(""))
			bias = (int) Math.floor(Math.random() * this.ZW[1] + 1);
		else
			bias = Integer.parseInt(temp);
		return this.ZW[0] - bias + mod;
	}
	
	public int testBD(int mod, String name) {
		String temp = JOptionPane.showInputDialog("Wynik rzutu kosci testu BD dla " + name);
		int bias;
		if (temp == "")
			bias = (int) Math.floor(Math.random() * this.BD[1] + 1);
		else
			bias = Integer.parseInt(temp);
		return this.BD[0] - bias + mod;
	}
	
	public int testINT(int mod, String name) {
		String temp = JOptionPane.showInputDialog("Wynik rzutu kosci testu INT dla " + name);
		int bias;
		if (temp == "")
			bias = (int) Math.floor(Math.random() * this.INT[1] + 1);
		else
			bias = Integer.parseInt(temp);
		return this.INT[0] - bias + mod;
	}
	
	public int testDB(int mod, String name) {
		String temp = JOptionPane.showInputDialog("Wynik rzutu kosci testu DB dla " + name);
		int bias;
		if (temp == "")
			bias = (int) Math.floor(Math.random() * this.DB[1] + 1);
		else
			bias = Integer.parseInt(temp);
		return this.DB[0] - bias + mod;
	}
	
	public int testCH(int mod, String name) {
		String temp = JOptionPane.showInputDialog("Wynik rzutu kosci testu CH dla " + name);
		int bias;
		if (temp == "")
			bias = (int) Math.floor(Math.random() * this.CH[1] + 1);
		else
			bias = Integer.parseInt(temp);
		return this.CH[0] - bias + mod;
	}
	

	public void setSW(int[] sW) {
		SW = sW;
	}

	public void setREF(int[] rEF) {
		REF = rEF;
	}

	public void setZW(int[] zW) {
		ZW = zW;
	}

	public void setBD(int[] bD) {
		BD = bD;
	}

	public void setINT(int[] iNT) {
		INT = iNT;
	}

	public void setDB(int[] dB) {
		DB = dB;
	}

	public void setCH(int[] cH) {
		CH = cH;
	}
}
