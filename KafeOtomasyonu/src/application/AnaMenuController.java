package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeriTabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;

public class AnaMenuController {

	public AnaMenuController() {
		baglanti = VeriTabaniUtil.Baglan();
	}

	public static int myID;

	public static String kullaniciAdi;
	public static String Sifre;
	public static int Yetki;

	Connection baglanti = null;
	PreparedStatement sorguIfadesi = null;
	ResultSet getirilen = null;
	String sql;

	public static void alertfunc(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("İpek Cafe");
		alert.setHeaderText("Doğrulama Hatası");
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void alertfunc2(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("İpek Cafe");
		alert.setHeaderText("Doğrulama Hatası");
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void alertfunc3(String message, String Header, AlertType typee) {
		Alert alert = new Alert(typee);
		alert.setTitle("İpek Cafe");
		alert.setHeaderText(Header);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
    @FXML
    private Tab tab_yonetici;

	@FXML
	private Button btn_cikis;

	@FXML
	private Button btn_ekle;

	@FXML
	private Button btn_guncelle;

	@FXML
	private Button btn_dogrulama;

	@FXML
	private Button btn_siparisver;

	@FXML
	private Button btn_sil;

	@FXML
	private Button btn_temizle;

	@FXML
	private Button btn_yenile;

	@FXML
	private TableColumn<Kayitlar_islem, String> col_ad;

	@FXML
	private TableColumn<Kayitlar_sepetim, String> col_adsepet;

	@FXML
	private TableColumn<Kayitlar_islem, Double> col_fiyat;

	@FXML
	private TableColumn<Kayitlar_sepetim, Double> col_fiyatsepet;

	@FXML
	private TableColumn<Kayitlar_islem, Integer> col_id;

	@FXML
	private TableColumn<Kayitlar_sepetim, Integer> col_idsepet;

	@FXML
	private TableColumn<Kayitlar_islem, String> col_kategori;

	@FXML
	private TableColumn<Kayitlar_sepetim, String> col_kategorisepet;

	@FXML
	private TableColumn<Kayitlar_login, String> col_kulad;

	@FXML
	private TableColumn<Kayitlar_login, Integer> col_kulid;

	@FXML
	private TableColumn<Kayitlar_login, String> col_kulpass;

	@FXML
	private TableColumn<Kayitlar_islem, String> col_urunad;

	@FXML
	private TableColumn<Kayitlar_islem, Double> col_urunfiyat;

	@FXML
	private TableColumn<Kayitlar_islem, Integer> col_urunid;

	@FXML
	private TableColumn<Kayitlar_islem, String> col_urunkategori;

	@FXML
	private TableColumn<Kayitlar_login, Integer> col_yetki;

	@FXML
	private Button degistir;

	@FXML
	private ImageView fastfood;

	@FXML
	private Label lbl_ad;

	@FXML
	private Label lbl_dort;

	@FXML
	private Label lbl_id;

	@FXML
	private Label lbl_secilenFiyat;

	@FXML
	private Label lbl_secilenKategori;

	@FXML
	private Label lbl_secilenUrun;

	@FXML
	private Label lbl_uc;

	@FXML
	private Label toptutar;

	@FXML
	private Label lbl_dogrulama;

	@FXML
	private ImageView makarna;

	@FXML
	private RadioButton radiob_kullanıcılar;

	@FXML
	private RadioButton radiob_urunler;

	@FXML
	private ImageView salata;

	@FXML
	private Button sepeteEkle;

	@FXML
	private ImageView sicakicecek;

	@FXML
	private ImageView sogukicecek;

	@FXML
	private TableView<Kayitlar_login> tableview_musteriler;

	@FXML
	private TableView<Kayitlar_islem> tableview_urunler;

	@FXML
	private TableView<Kayitlar_islem> tableview_urunlerMenu;

	@FXML
	private TableView<Kayitlar_sepetim> tableview_urunlerSepetim;

	@FXML
	private ImageView tatli;

	@FXML
	private TextField txt_ad;

	@FXML
	private TextField txt_dort;

	@FXML
	private TextField txt_id;

	@FXML
	private TextField txt_idkul;

	@FXML
	private TextField txt_kul;

	@FXML
	private TextField txt_sifre;

	@FXML
	private TextField txt_uc;

	@FXML
	private TextField txt_urunAra;

	@FXML
	private TextField txt_yetki;

	@FXML
	private TextField urunAra;

	@FXML
	private TextField txt_captcha;

	@FXML
	private TextField txt_eskisifre;

	@FXML
	private TextField txt_yenisifre;

	@FXML
	private TextField txt_yenisifretekrar;
	

	// TABLEVIEW DOLDURMA FONKSİYONLARI

	public void DegerleriGetirUrunler() {
		sql = "select * from urunler";
		ObservableList<Kayitlar_islem> kayitlarliste = FXCollections.observableArrayList();

		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				kayitlarliste.add(new Kayitlar_islem(getirilen.getInt("urunID"), getirilen.getString("urunAd"),
						getirilen.getString("urunKategori"), getirilen.getDouble("urunFiyat")));
			}
			col_id.setCellValueFactory(new PropertyValueFactory<>("urunID"));
			col_ad.setCellValueFactory(new PropertyValueFactory<>("urunAd"));
			col_kategori.setCellValueFactory(new PropertyValueFactory<>("urunKategori"));
			col_fiyat.setCellValueFactory(new PropertyValueFactory<>("urunFiyat"));
			tableview_urunlerMenu.setItems(kayitlarliste);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}

	public void DegerleriGetirUrunler2(String sql) {
		ObservableList<Kayitlar_islem> kayitlarliste = FXCollections.observableArrayList();

		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				kayitlarliste.add(new Kayitlar_islem(getirilen.getInt("urunID"), getirilen.getString("urunAd"),
						getirilen.getString("urunKategori"), getirilen.getDouble("urunFiyat")));
			}
			col_id.setCellValueFactory(new PropertyValueFactory<>("urunID"));
			col_ad.setCellValueFactory(new PropertyValueFactory<>("urunAd"));
			col_kategori.setCellValueFactory(new PropertyValueFactory<>("urunKategori"));
			col_fiyat.setCellValueFactory(new PropertyValueFactory<>("urunFiyat"));
			tableview_urunlerMenu.setItems(kayitlarliste);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}

	public void DegerleriGetirKullaniciYonetim(String sql) {
		ObservableList<Kayitlar_login> kayitlarliste = FXCollections.observableArrayList();

		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				kayitlarliste.add(new Kayitlar_login(getirilen.getInt("kID"), getirilen.getString("kul_ad"),
						getirilen.getString("sifre"), getirilen.getInt("yetki")));
			}

			col_kulid.setCellValueFactory(new PropertyValueFactory<Kayitlar_login, Integer>("id"));
			col_kulad.setCellValueFactory(new PropertyValueFactory<Kayitlar_login, String>("sutun_kulad"));
			col_kulpass.setCellValueFactory(new PropertyValueFactory<Kayitlar_login, String>("sifre"));
			col_yetki.setCellValueFactory(new PropertyValueFactory<Kayitlar_login, Integer>("yetki"));

			tableview_musteriler.setItems(kayitlarliste);

		} catch (Exception e) {
			alertfunc3(e.getMessage(), "ERROR", AlertType.ERROR);
		}
	}

	public void DegerleriGetirUrunYonetim(String sql) {
		ObservableList<Kayitlar_islem> kayitlarliste = FXCollections.observableArrayList();

		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				kayitlarliste.add(new Kayitlar_islem(getirilen.getInt("urunID"), getirilen.getString("urunAd"),
						getirilen.getString("urunKategori"), getirilen.getDouble("urunFiyat")));
			}

			col_urunid.setCellValueFactory(new PropertyValueFactory<Kayitlar_islem, Integer>("urunID"));
			col_urunad.setCellValueFactory(new PropertyValueFactory<Kayitlar_islem, String>("urunAd"));
			col_urunkategori.setCellValueFactory(new PropertyValueFactory<Kayitlar_islem, String>("urunKategori"));
			col_urunfiyat.setCellValueFactory(new PropertyValueFactory<Kayitlar_islem, Double>("urunFiyat"));

			tableview_urunler.setItems(kayitlarliste);

		} catch (Exception e) {
			alertfunc3(e.getMessage(), "ERROR", AlertType.ERROR);
		}

	}

	// MENÜ SEKMESİ

	@FXML
	void tableview_menu_Click(MouseEvent event) {
		Kayitlar_islem menu = new Kayitlar_islem();
		menu = (Kayitlar_islem) tableview_urunlerMenu.getItems()
				.get(tableview_urunlerMenu.getSelectionModel().getSelectedIndex());
		lbl_secilenUrun.setText(menu.getUrunAd());
		lbl_secilenKategori.setText(menu.getUrunKategori());
		lbl_secilenFiyat.setText(String.valueOf(menu.getUrunFiyat()));
	}

	@FXML
	void sicakicecek_Click(MouseEvent event) {
		sql = "select * from urunler where urunKategori='Sıcak İçecekler'";
		DegerleriGetirUrunler2(sql);
	}

	@FXML
	void sogukicecek_Click(MouseEvent event) {
		sql = "select * from urunler where urunKategori='Soğuk İçecekler'";
		DegerleriGetirUrunler2(sql);
	}

	@FXML
	void tatli_Click(MouseEvent event) {
		sql = "select * from urunler where urunKategori='Tatlılar'";
		DegerleriGetirUrunler2(sql);
	}

	@FXML
	void fastfood_Click(MouseEvent event) {
		sql = "select * from urunler where urunKategori='Fast Food'";
		DegerleriGetirUrunler2(sql);
	}

	@FXML
	void makarna_Click(MouseEvent event) {
		sql = "select * from urunler where urunKategori='Makarnalar'";
		DegerleriGetirUrunler2(sql);
	}

	@FXML
	void salata_Click(MouseEvent event) {
		sql = "select * from urunler where urunKategori='Salatalar'";
		DegerleriGetirUrunler2(sql);
	}

	@FXML
	void urunAraPress(KeyEvent event) {
		if (urunAra.getText().equals("")) {
			sql = "select * from urunler";
		} else {
			sql = "select * from urunler where urunAd like '%" + urunAra.getText() + "%' or urunKategori like '%"
					+ urunAra.getText() + "%'";
		}
		DegerleriGetirUrunler2(sql);
	}

	@FXML
	void urunAraClick(ActionEvent event) {

	}

	// SEPETİM SEKMESİ

	public void DegerleriGetirSepetim(String sql) {
		ObservableList<Kayitlar_sepetim> kayitlarliste = FXCollections.observableArrayList();

		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				kayitlarliste.add(new Kayitlar_sepetim(getirilen.getInt("id"), getirilen.getString("ad"),
						getirilen.getString("kategori"), getirilen.getDouble("fiyat")));
			}
			col_idsepet.setCellValueFactory(new PropertyValueFactory<Kayitlar_sepetim, Integer>("id"));
			col_adsepet.setCellValueFactory(new PropertyValueFactory<Kayitlar_sepetim, String>("ad"));
			col_kategorisepet.setCellValueFactory(new PropertyValueFactory<Kayitlar_sepetim, String>("kategori"));
			col_fiyatsepet.setCellValueFactory(new PropertyValueFactory<Kayitlar_sepetim, Double>("fiyat"));
			tableview_urunlerSepetim.setItems(kayitlarliste);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void btn_siparisver_Click(ActionEvent event) {
		try {
			sql = "delete from sepetim where kullaniciID=" + String.valueOf(myID);
			sorguIfadesi = baglanti.prepareStatement(sql);
			sorguIfadesi.executeUpdate();
			alertfunc3("Siparişiniz alındı!", "Toplam ödenecek tutar: " + toptutar.getText(), AlertType.CONFIRMATION);
		} catch (Exception e) {
			alertfunc3(e.getMessage(), "ERROR", AlertType.ERROR);
		}
	}

	@FXML
	void sepeteEkle_Click(ActionEvent event) {
		if (tableview_urunlerMenu.getSelectionModel().getSelectedIndex() >= 0) {
			Kayitlar_islem urun = new Kayitlar_islem();
			urun = (Kayitlar_islem) tableview_urunlerMenu.getItems()
					.get(tableview_urunlerMenu.getSelectionModel().getSelectedIndex());
			try {
				sql = "insert into sepetim(ad,kategori,fiyat,kullaniciID) values(?,?,?,?)";
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, urun.getUrunAd());
				sorguIfadesi.setString(2, urun.getUrunKategori());
				sorguIfadesi.setDouble(3, urun.getUrunFiyat());
				sorguIfadesi.setInt(4, myID);
				sorguIfadesi.executeUpdate();
				alertfunc3("Ürün Sepete Başarıyla Eklendi", "Ürün Eklendi", AlertType.CONFIRMATION);
				DegerleriGetirSepetim("select * from sepetim where kullaniciID=" + String.valueOf(myID));
				toptutar.setText(String.valueOf(SepetimToplamTutar()));
			} catch (Exception e) {
				alertfunc3(e.getMessage(), "ERROR", AlertType.ERROR);
			}
		} else {
			alertfunc3("Lütfen Ürün Seçiniz", "Herhangi Bir Ürün Seçilmedi!", AlertType.WARNING);
		}

	}

	public int SepetimToplamTutar() {
		sql = "select * from sepetim";
		int Result = 0;
		try {
			sorguIfadesi = baglanti.prepareStatement(sql);
			ResultSet getirilen = sorguIfadesi.executeQuery();
			while (getirilen.next()) {
				Result += getirilen.getInt("fiyat");
			}
			return Result;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return 0;
	}

	// AYARLAR SEKMESİ

	@FXML
	void btn_degistir_Click(ActionEvent event) {
		if (Sifre.equals(VeriTabaniUtil.MD5Sifrele(txt_eskisifre.getText()))) {
			if (txt_yenisifre.getText().equals(txt_yenisifretekrar.getText())) {

				if (String.valueOf(randomNumber1 + randomNumber2).equals(txt_captcha.getText())) {
					sql = "update login set sifre='" + VeriTabaniUtil.MD5Sifrele(txt_yenisifre.getText())
							+ "' where kID=" + String.valueOf(myID);

					try {
						sorguIfadesi = baglanti.prepareStatement(sql);
						sorguIfadesi.executeUpdate();
						Sifre = VeriTabaniUtil.MD5Sifrele(txt_yenisifre.getText());
						txt_sifre.setText(VeriTabaniUtil.MD5Sifrele(txt_yenisifre.getText()));
						alertfunc3("Şifreniz değiştirildi!", "Güncellendi!", AlertType.CONFIRMATION);

					} catch (Exception e) {
						alertfunc3(e.getMessage(), "ERROR", AlertType.ERROR);
					}

				} else {
					alertfunc3("Captcha'yı doğru giriniz!", "Captcha Doğrulama Hatası", AlertType.WARNING);
				}

			} else {
				alertfunc3("Şifreler uyuşmuyor!", "Şifre Doğrulama Hatası", AlertType.WARNING);
			}
		} else {
			alertfunc3("Eski şifre doğru değil!", "Şifre Doğrulama Hatası", AlertType.WARNING);
		}

	}

	@FXML
	void btn_cikis_Click(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void btn_dogrulama_Click(ActionEvent event) {
		lbl_dogrulama.setText(String.valueOf(randomNumber1) + " + " + String.valueOf(randomNumber2) + " =");
	}

	// YÖNETİCİ SEKMESİ

	@FXML
	void choicebox_musteriler_Click(ActionEvent event) {
		if (!radiob_kullanıcılar.isSelected()) {
			radiob_urunler.setSelected(true);
			tableview_urunler.setVisible(true);
			tableview_musteriler.setVisible(false);
		} else {
			radiob_urunler.setSelected(false);
			tableview_urunler.setVisible(false);
			tableview_musteriler.setVisible(true);
		}
		lbl_id.setText("Kullanıcı ID:");
		lbl_ad.setText("Kullanıcı Adı:");
		lbl_uc.setText("Kullanıcı Şifre:");
		lbl_dort.setText("Yetki:");
	}

	@FXML
	void choicebox_urunler_Click(ActionEvent event) {
		if (!radiob_urunler.isSelected()) {
			radiob_kullanıcılar.setSelected(true);
			tableview_urunler.setVisible(false);
			tableview_musteriler.setVisible(true);
		} else {
			radiob_kullanıcılar.setSelected(false);
			tableview_urunler.setVisible(true);
			tableview_musteriler.setVisible(false);
		}

		lbl_id.setText("Ürün ID:");
		lbl_ad.setText("Ürün Adı:");
		lbl_uc.setText("Ürün Kategori:");
		lbl_dort.setText("Ürün Fiyat:");
	}

	@FXML
	void tableview_musteriler_Click(MouseEvent event) {
		if (radiob_kullanıcılar.isSelected() && tableview_musteriler.getSelectionModel().getSelectedIndex() >= 0) {
			Kayitlar_login kayit = new Kayitlar_login();
			kayit = (Kayitlar_login) tableview_musteriler.getItems()
					.get(tableview_musteriler.getSelectionModel().getSelectedIndex());
			txt_idkul.setText(String.valueOf(kayit.getId()));
			txt_ad.setText(kayit.getSutun_kulad());
			txt_uc.setText(kayit.getSifre());
			txt_dort.setText(String.valueOf(kayit.getYetki()));
		} else {
			alertfunc3("Kullanıcı seçiniz!", "Kullanıcı seçim hatası", AlertType.WARNING);
		}

	}

	@FXML
	void tableview_urunler_Click(MouseEvent event) {
		if (radiob_urunler.isSelected() && tableview_urunler.getSelectionModel().getSelectedIndex() >= 0) {
			Kayitlar_islem kayit = new Kayitlar_islem();
			kayit = (Kayitlar_islem) tableview_urunler.getItems()
					.get(tableview_urunler.getSelectionModel().getSelectedIndex());
			txt_idkul.setText(String.valueOf(kayit.getUrunID()));
			txt_ad.setText(kayit.getUrunAd());
			txt_uc.setText(kayit.getUrunKategori());
			txt_dort.setText(String.valueOf(kayit.getUrunFiyat()));
		} else {
			alertfunc3("Ürün seçiniz!", "Ürün seçim hatası", AlertType.WARNING);
		}
	}

	@FXML
	void btn_ekle_Click(ActionEvent event) {
		if (radiob_kullanıcılar.isSelected()) {
			sql = "insert into login(kul_ad, sifre, yetki) values (?, ?, ?)";
			try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, txt_ad.getText().trim());
				sorguIfadesi.setString(2, VeriTabaniUtil.MD5Sifrele(txt_uc.getText().trim()));
				sorguIfadesi.setString(3, txt_dort.getText().trim());
				sorguIfadesi.executeUpdate();
				alertfunc3("Ekleme işlemi gerçekleştirildi!", "Kullanıcı eklendi!", AlertType.CONFIRMATION);

			} catch (Exception e) {
				alertfunc3("Ekleme işlemi gerçekleştirilemedi!", "Kullanıcı eklenemedi!", AlertType.WARNING);
			}
		} else {
			sql = "insert into urunler(urunAd, urunKategori, urunFiyat) values (?, ?, ?)";
			try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, txt_ad.getText().trim());
				sorguIfadesi.setString(2, txt_uc.getText().trim());
				sorguIfadesi.setDouble(3, Double.valueOf(txt_dort.getText()));
				sorguIfadesi.executeUpdate();
				alertfunc3("Ekleme işlemi gerçekleştirildi!", "Ürün eklendi!", AlertType.CONFIRMATION);
			} catch (Exception e) {
				alertfunc3("Ekleme işlemi gerçekleştirilemedi!", "Ürün eklenemedi!", AlertType.WARNING);
			}
		}

	}

	@FXML
	void btn_guncelle_Click(ActionEvent event) {
		if (radiob_kullanıcılar.isSelected()) {
			sql = "update login set kul_ad=?, sifre=?, yetki=? where kID=? ";
			try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, txt_ad.getText().trim());
				sorguIfadesi.setString(2, VeriTabaniUtil.MD5Sifrele(txt_uc.getText().trim()));
				sorguIfadesi.setString(3, txt_dort.getText().trim());
				sorguIfadesi.setInt(4, Integer.valueOf(txt_idkul.getText()));
				sorguIfadesi.executeUpdate();
				alertfunc3("Güncelleme işlemi gerçekleştirildi!", "Kullanıcı güncellendi!", AlertType.CONFIRMATION);

			} catch (Exception e) {
				alertfunc3("Güncelleme işlemi gerçekleştirilemedi!", "Kullanıcı güncellenemedi!", AlertType.WARNING);
			}
		} else {
			sql = "update urunler set urunAd=?, urunKategori=?, urunFiyat=? where urunID=?";
			try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, txt_ad.getText().trim());
				sorguIfadesi.setString(2, txt_uc.getText().trim());
				sorguIfadesi.setDouble(3, Double.valueOf(txt_dort.getText()));
				sorguIfadesi.setInt(4, Integer.valueOf(txt_idkul.getText()));
				sorguIfadesi.executeUpdate();
				alertfunc3("Güncelleme işlemi gerçekleştirildi!", "Ürün güncellendi!", AlertType.CONFIRMATION);
			} catch (Exception e) {
				alertfunc3("Güncelleme işlemi gerçekleştirilemedi!", "Ürün güncellenemedi!", AlertType.WARNING);
			}
		}

	}

	@FXML
	void btn_sil_Click(ActionEvent event) {
		if (radiob_kullanıcılar.isSelected()) {
			sql = "delete from login where kID=?";
			try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, txt_idkul.getText().trim());
				sorguIfadesi.executeUpdate();
				alertfunc3("Silme işlemi gerçekleştirildi!", "Kullanıcı silindi!", AlertType.CONFIRMATION);

			} catch (Exception e) {
				alertfunc3("Silme işlemi gerçekleştirilemedi!", "Kullanıcı silinemedi!", AlertType.WARNING);
			}
		} else {
			sql = "delete from urunler where urunID=?";
			try {
				sorguIfadesi = baglanti.prepareStatement(sql);
				sorguIfadesi.setString(1, txt_idkul.getText().trim());
				sorguIfadesi.executeUpdate();
				alertfunc3("Silme işlemi gerçekleştirildi!", "Ürün silindi!", AlertType.CONFIRMATION);
			} catch (Exception e) {
				alertfunc3("Silme işlemi gerçekleştirilemedi!", "Ürün silinemedi!", AlertType.WARNING);
			}
		}
	}

	@FXML
	void btn_temizle_Click(ActionEvent event) {
		txt_idkul.setText("");
		txt_ad.setText("");
		txt_uc.setText("");
		txt_dort.setText("");
	}

	@FXML
	void btn_yenile_Click(ActionEvent event) {
		DegerleriGetirKullaniciYonetim("select * from login");
		DegerleriGetirUrunYonetim("select * from urunler");
	}

	Random random = new Random();
	int randomNumber1 = random.nextInt(10, 50);
	int randomNumber2 = random.nextInt(10, 50);

	@FXML
	void initialize() {
		DegerleriGetirUrunler();
		txt_id.setText(String.valueOf(myID));
		txt_kul.setText(kullaniciAdi);
		txt_sifre.setText(Sifre);
		if (Yetki == 0) {
			txt_yetki.setText("Yönetici");
		} else {
			txt_yetki.setText("Müşteri");
			tab_yonetici.setDisable(true);
		}
		radiob_kullanıcılar.setSelected(true);

		lbl_id.setText("Kullanıcı ID:");
		lbl_ad.setText("Kullanıcı Adı:");
		lbl_uc.setText("Kullanıcı Şifre:");
		lbl_dort.setText("Yetki:");

		DegerleriGetirKullaniciYonetim("select * from login");
		DegerleriGetirUrunYonetim("select * from urunler");

		txt_idkul.setDisable(true);
		
		
		
		//CSS
		sepeteEkle.getStyleClass().add("buton");
		btn_ekle.getStyleClass().add("buton");
		btn_guncelle.getStyleClass().add("buton");
		btn_sil.getStyleClass().add("buton");
		btn_siparisver.getStyleClass().add("buton");
		degistir.getStyleClass().add("buton");
		
		txt_dort.getStyleClass().add("text-field");
		
		tableview_urunler.getStyleClass().add("table-view");
	}

}
