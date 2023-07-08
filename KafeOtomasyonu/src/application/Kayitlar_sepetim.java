package application;

public class Kayitlar_sepetim {

	private int id;
	private String ad;
	private String kategori;
	private double fiyat;
	
	public Kayitlar_sepetim() {
		
	}
	
	Kayitlar_sepetim(int id, String ad, String kategori, double fiyat){
		this.id=id;
		this.ad=ad;
		this.kategori=kategori;
		this.fiyat=fiyat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public double getFiyat() {
		return fiyat;
	}

	public void setFiyat(double fiyat) {
		this.fiyat = fiyat;
	}

}
