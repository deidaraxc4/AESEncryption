

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

public class SceneController implements Initializable{
	@FXML
	private TextField secKeyLabel;
	@FXML
	private Button encryptBtn;
	@FXML
	private Button decryptBtn;
	@FXML
	private TextArea plainField;
	@FXML
	private TextArea cipherField;
	@FXML
	private Button keyGenBtn;
	@FXML
	private Label keyGenLabel;
	@FXML
	private Label statusLabel;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
