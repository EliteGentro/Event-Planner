/**
 * RenameController
 * @author khf849 
 * Controller for the RenameGUI class to change a file's name
 *
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RenameController implements Initializable {

	/**
	 * stage
	 */
	private Stage stage;
	/**
	 * scene
	 */
	private Scene scene;
	/**
	 * root
	 */
	private Parent root;
	@FXML
	/**
	 * renameButton
	 * Button to rename the file to whatever is in the nameField
	 */
	private Button renameButton;
	@FXML
	/**
	 * nameField
	 * Text Field that contains the name of the file to be modified. 
	 */
	private TextField nameField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	/**
	 * Method to change the selected file's name to whatever is in nameField.
	 * @param event
	 * Event of clicking the renameButton
	 * @throws IOException
	 */
	public void RenameFile(ActionEvent event) throws IOException {
		File original = new File("src/Files/"+application.Main.groupName + "/"+ application.Main.csvFile);
		File renamed = new File("src/Files/"+application.Main.groupName + "/"+ nameField.getText()+".csv");
		boolean verify = original.renameTo(renamed);
		if(verify) {
			JOptionPane.showMessageDialog ( null , "El archivo ha sido renombrado" );
		} else {
			JOptionPane.showMessageDialog ( null , "Hubo un Error renombrando el archivo, intente de nuevo y evite caracteres especiales" );
		}
		root = FXMLLoader.load(getClass().getResource("/application/SecondGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SecondGUI.fxml"));
		root = loader.load();
		SecondController secondController = loader.getController();
		secondController.fillingNameTable();
		stage =(Stage)(renameButton.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * When the scene is initialized, the nameField will be already filled with the current file's name.
	 * @param fileName
	 * The current name of the file.
	 */
	public void FillField(String fileName) {
		String newName = "";
		String[] split = fileName.split("");
		for(int i = 0; i<split.length-4;i++) {
			newName += split[i];
		}
		nameField.setText(newName);
	}
}
