package it.myevents.myevents.pojos;

public class Data {
	private int giorno;
	private int mese;
	private int anno;

	public Data(int giorno, int mese, int anno) {
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
	}

	public Data(String string) {
		this(Integer.parseInt(string.split("-")[0]), Integer.parseInt(string.split("-")[1]),
				Integer.parseInt(string.split("-")[2]));
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	@Override
	public String toString() {
		return anno + "-" + mese + "-" + giorno;
	}
}