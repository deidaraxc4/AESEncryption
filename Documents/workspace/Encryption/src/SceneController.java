import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private Label statusLabel, firstStep, secondStep;
	@FXML
	private ChoiceBox<String> choiceBox;
	@FXML
	private TextField fileField;
	@FXML
	private TextField directoryField;
	@FXML
	private TextField passField;
	@FXML
	private TextField repassField;
	@FXML
	private Button doBtn, dirBrowse, fileBrowse;
	
	FileChooser fileChooser = new FileChooser();
	DirectoryChooser directoryChooser = new DirectoryChooser();
	String modeSelection = "Encrypt";
	File inputFile, outputFile, selectedDirectory;
	Boolean isEqual = false;
	
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
			if(AES.decrypt(encryptedText, secret)==null) {
				statusLabel.setText("Error when decrypting");
			}
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
	
	@FXML
	public void actionFile(ActionEvent event) throws Exception {
		if(modeSelection.equals("Encrypt")) {
			inputFile = new File(fileField.getText());
			selectedDirectory = new File(directoryField.getText());
			outputFile = new File(selectedDirectory+"/"+inputFile.getName()+".encrypted");
			//Check if password match
			if(passField.getText().equals(repassField.getText())) {
				isEqual = true;
				AES.encrypt(passField.getText(), inputFile, outputFile);
				statusLabel.setText("Encrypted succesfully");
			} else if(!passField.getText().equals(repassField.getText())) {
				statusLabel.setText("Passwords do not match!");
			}
		} else if(modeSelection.equals("Decrypt")) {
			inputFile = new File(fileField.getText());
			selectedDirectory = new File(directoryField.getText());
			String [] parts = inputFile.getName().split("\\.encrypte");
			String decrypted = parts [0];
			outputFile = new File(selectedDirectory+"/"+decrypted);
			//Check if password match
			if(passField.getText().equals(repassField.getText())) {
				isEqual = true;
				AES.decrypt(passField.getText(), inputFile, outputFile);
				statusLabel.setText("Decrypted succesfully");
			} else if(!passField.getText().equals(repassField.getText())) {
				statusLabel.setText("Passwords do not match!");
			}
		}
	}
	
	@FXML
	public void directoryOpen(ActionEvent event) throws Exception {
		Node source = (Node) event.getSource();
	    Window theStage = source.getScene().getWindow();
	    directoryChooser.setTitle("Choose location to "+modeSelection);
	    selectedDirectory = directoryChooser.showDialog(theStage);
	    directoryField.setText(selectedDirectory.getAbsolutePath());
	}
	
	@FXML
	public void fileOpen(ActionEvent event) throws Exception {
		Node source = (Node) event.getSource();
	    Window theStage = source.getScene().getWindow();
	    fileChooser.setTitle("Choose file to "+modeSelection);
	    inputFile = fileChooser.showOpenDialog(theStage);
	    fileField.setText(inputFile.getAbsolutePath());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		keyGenField.setEditable(false);
		ObservableList obList = FXCollections.observableArrayList("Encrypt","Decrypt");
		choiceBox.setItems(obList);
		choiceBox.getSelectionModel().selectFirst();
		
		choiceBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				modeSelection = choiceBox.getValue();
				firstStep.setText("Select file to "+modeSelection);
				secondStep.setText("Select directory for "+modeSelection+"ed file");
				doBtn.setText(modeSelection);
			}
			
		});
		
		
	}
}
