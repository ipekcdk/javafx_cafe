package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.isteMySQL.Util.VeriTabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
	
	public LoginController() {		//kurucu fonksiyon 
		baglanti=VeriTabaniUtil.Baglan();
	}

	public static void alertfunc(String message) {
        Alert alert = new Alert(AlertType.WARNING); 
        alert.setTitle("İpek Cafe");  
        alert.setHeaderText("Doğrulama Hatası"); 
        alert.setContentText(message); 
        alert.showAndWait(); 
    }
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lbl_Sonuc;
       
    @FXML
    private Button btn_kayitol;
    
    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;

    Connection baglanti=null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen=null;
    String sql;
    
    @FXML
    void btn_login_Click(ActionEvent event) {
    	sql="select * from login where kul_ad=? and sifre=?";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txt_kul.getText().trim());
    		sorguIfadesi.setString(2, VeriTabaniUtil.MD5Sifrele(txt_sifre.getText().trim())); 
    		
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		
    		if(!getirilen.next()) {
    			alertfunc("Kullanıcı adı veya Şifre hatalı!");
    		}
    		else {
    			AnaMenuController.myID=getirilen.getInt("kID");
    			AnaMenuController.kullaniciAdi=getirilen.getString("kul_ad");
    			AnaMenuController.Sifre=getirilen.getString("sifre");
    			AnaMenuController.Yetki=getirilen.getInt("yetki");

    			FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaMenu.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage yeniPencere = new Stage();
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                yeniPencere.setScene(scene);
                yeniPencere.show();
                Scene scene2 = btn_login.getScene();
                Stage stage2 = (Stage) scene2.getWindow();
                stage2.hide();
    		}
    	} catch(Exception e) {
    		lbl_Sonuc.setText(e.getMessage().toString());
    	}

    }
    @FXML
    void btn_kayitol_Click(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KayıtOl.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage yeniPencere = new Stage();
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            yeniPencere.setScene(scene);
            yeniPencere.show();
            Scene scene2 = btn_login.getScene();
            Stage stage2 = (Stage) scene2.getWindow();
            stage2.hide();
                    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    	
    }

    @FXML
    void initialize() {
        lbl_Sonuc.setText("");
        
		btn_login.getStyleClass().add("buton");
		btn_kayitol.getStyleClass().add("buton");

    }

}
