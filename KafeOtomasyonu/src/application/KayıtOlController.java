package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeriTabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KayıtOlController {
	
    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
	public KayıtOlController() {		   //kurucu fonksiyon 
		baglanti=VeriTabaniUtil.Baglan();
	}

	public static void alertfunc(String message , String alertHeader , AlertType typee) {
        Alert alert = new Alert(typee); 
        alert.setTitle("İpek Cafe");  
        alert.setHeaderText(alertHeader); 
        alert.setContentText(message); 
        alert.showAndWait(); 
    }
	
	public Boolean verificationfunc() {
        if (SignUpControl() && PasswordVerification()) {
            return true;
        }
        else if(!PasswordVerification()){
        	alertfunc("Şifreler aynı değil!", "Şifreleri yeniden kontrol ediniz!", AlertType.WARNING);
        }
        return false;
    }
    
    public Boolean PasswordVerification() {
        
        if (txt_sifre.getText().equals(txt_sifretekrar.getText())) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public Boolean SignUpControl() {
        
        if(txt_kul.getText().length() < 6) {
            alertfunc("Lütfen En Az 6 Karakterli Bir Kullanıcı Adı Oluşturunuz", "Kullanıcı Adını Düzenleyiniz" , AlertType.WARNING);
            return false;
        }
        if(txt_sifre.getText().length() < 6){
            alertfunc("Lütfen En Az 8 Karakterli Bir Şifre Oluşturunuz", "Şifreyi Düzenleyin", AlertType.WARNING);
            return false;
        }
        
        return true;
    }

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_kayitol;

    @FXML
    private Label lbl_Sonuc;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;
    
    @FXML
    private TextField txt_sifretekrar;

    @FXML
    void btn_kayitol_Click(ActionEvent event) {
    	if(verificationfunc()) {
    		try {
    			sql = "select * from `login` where kul_ad=?";
        		sorguIfadesi = baglanti.prepareStatement(sql);
    			sorguIfadesi.setString(1, txt_kul.getText().trim()); 
    			getirilen = sorguIfadesi.executeQuery();
    			
    			if (getirilen.next()) {
    				alertfunc("Bu Kullanıcıya Ait Hesap Bulunmaktadir", "Kayıt Olunamadı!" , AlertType.WARNING);
    			}
    			else {   				
    				sql = "insert into Login(kul_ad,sifre,yetki) values(?,?,?)";
                    sorguIfadesi = baglanti.prepareStatement(sql);
    				sorguIfadesi.setString(1, txt_kul.getText().trim());
    				sorguIfadesi.setString(2, VeriTabaniUtil.MD5Sifrele(txt_sifre.getText().trim()));
    				sorguIfadesi.setInt(3, 0);
    				sorguIfadesi.executeUpdate();
    				alertfunc("Kayıt olma işlemi başarılı", "Hoş geldiniz..", AlertType.CONFIRMATION);

    				FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    				Parent root = loader.load();
    				Scene scene = new Scene(root);
    				Stage yeniPencere = new Stage();
    				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    				yeniPencere.setScene(scene);
    				yeniPencere.show();
    				Scene scene2 = btn_kayitol.getScene();
    			    Stage stage2 = (Stage) scene2.getWindow();
    			    stage2.close();
    			}
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    			alertfunc(e.getMessage(), "ERROR", AlertType.ERROR);
    		}
    	}

    }

    @FXML
    void initialize() {
		btn_kayitol.getStyleClass().add("buton");
    }

}
