package application;

public class Kayitlar_islem {
	private int urunID;
	private String urunAd;
	private String urunKategori;
	private double urunFiyat;
	
	Kayitlar_islem(){
		
	}
	
	Kayitlar_islem(int urunID, String urunAd, String urunKategori, double urunFiyat){
		this.urunID=urunID;
		this.urunAd=urunAd;
		this.urunKategori=urunKategori;
		this.urunFiyat=urunFiyat;		
	}

	public int getUrunID() {
		return urunID;
	}

	public void setUrunID(int urunID) {
		this.urunID = urunID;
	}

	public String getUrunAd() {
		return urunAd;
	}

	public void setUrunAd(String urunAd) {
		this.urunAd = urunAd;
	}

	public String getUrunKategori() {
		return urunKategori;
	}

	public void setUrunKategori(String urunKategori) {
		this.urunKategori = urunKategori;
	}

	public double getUrunFiyat() {
		return urunFiyat;
	}

	public void setUrunFiyat(double urunFiyat) {
		this.urunFiyat = urunFiyat;
	}
	
}
