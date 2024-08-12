/**
 * LoginController
 * @author khf849 
 * Contains the main variables that will be efficient to pass through classes constantly to read and write CSVs
 *
 */
package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

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
	/*
	 * contraseñaLabel
	 * Label that says "contraseña"
	 */
	private Label contraseñaLabel;
	@FXML
	/*
	 * equipoLabel
	 * Label where the that says "Nombre del Equipo"
	 */
	private Label equipoLabel;
	@FXML
	/*
	 * enterButton
	 * Button for the user to Login.
	 */
	private Button enterButton;
	@FXML
	/*
	 * equiposBox
	 * ChoiceBox to select the user's student group to login with.
	 */
	private ChoiceBox<String> equiposBox;
	@FXML
	/*
	 * contraseñaField
	 * Field to enter the user's password.
	 */
	private PasswordField contraseñaField;
	/*
	 * equiposList
	 * List that goes into equiposBox containing the student group names
	 */
	ObservableList<String> equiposList = FXCollections.observableArrayList(application.Main.groupNames);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		equiposBox.setItems(equiposList);
	}
	/**
	 * Logs the user in if the correct password is typed
	 * @param event
	 * the event of clicking the login button.
	 */
	public void iniciar(ActionEvent event) throws IOException {
		for(int i=0;i<application.Main.groupNames.size();i++) {
			if ((application.Main.groupNames.get(i) == equiposBox.getValue())) {
				if(application.Main.allGroups.get(i)[1].equals(contraseñaField.getText())){
					application.Main.groupName = application.Main.groupNames.get(i);
					root = FXMLLoader.load(getClass().getResource("/application/SecondGUI.fxml"));
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SecondGUI.fxml"));
					root = loader.load();
					SecondController secondController = loader.getController();
					secondController.fillingNameTable();
					stage =(Stage)(enterButton.getScene().getWindow());
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					break;
				}else{
					JOptionPane.showMessageDialog ( null , "La Contraseña es Incorrecta" );
				}
			}
		} 
	}
}
