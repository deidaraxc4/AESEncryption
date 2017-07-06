import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

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
	private TextField keyGenField;
	@FXML
	private Label statusLabel;

	// Event Listener on Button[#encryptBtn].onAction
	@FXML
	public void encryptText(ActionEvent event) {
		if(!secKeyLabel.getText().equals("")) {
			String plainText = plainField.getText(); //text to encrypt
			String secret = secKeyLabel.getText(); //AES key
			String encryptedText = AES.encrypt(plainText, secret);
			cipherField.setText(encryptedText);
			statusLabel.setText("Succesfully encrypted text");
		} else {
			statusLabel.setText("No Key to encrypt text with");
		}
	}
	// Event Listener on Button[#decryptBtn].onAction
	@FXML
	public void decryptText(ActionEvent event) {
		if(!secKeyLabel.getText().equals("")) {
			String encryptedText = cipherField.getText();
			String secret = secKeyLabel.getText();
			String decryptedText = AES.decrypt(encryptedText, secret);
			plainField.setText(decryptedText);
			statusLabel.setText("Succesfully decrypted text");
		} else {
			statusLabel.setText("No Key to decrypt text with");
		}
	}
	// Event Listener on Button[#keyGenBtn].onAction
	@FXML
	public void genKey(ActionEvent event) throws Exception{
		keyGenField.setText(AES.keyToString(AES.createKey()));
		statusLabel.setText("Successfully generated AES Key");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		keyGenField.setEditable(false);
	}
}
