/**
 * EditInfoGUIController
 * @author khf849 
 * Controller for the EditInfoGUI class in order to edit the main information of an event.
 *
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditInfoGUIController implements Initializable {

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
	 * departmentField
	 * Text Field to input the name of the department of the event.
	 */
	private TextField departmentField;
	@FXML
	/**
	 * peopleField
	 * Text Field to input the number of people in the event.
	 */
	private TextField peopleField;
	@FXML
	/**
	 * nameField
	 * Text Field to input the name of the event.
	 */
	private TextField nameField;
	@FXML
	/**
	 * responsibleField
	 * Text Field to input the name of the person responsible of the event.
	 */
	private TextField responsibleField;
	@FXML
	/**
	 * backButton
	 * Button to go back to the dashboard without saving anything.
	 */
	private Button backButton;
	@FXML
	/**
	 * saveButton
	 * Button to go back to the dashboard saving all the information written.
	 */
	private Button saveButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	/**
	 * Method that passes all the info from the CSV to the Text Fields.
	 */
	public void passInfo() {
		nameField.setText(application.Main.infoB.get(0)[1]);
		responsibleField.setText(application.Main.infoB.get(1)[1]);
		departmentField.setText(application.Main.infoB.get(2)[1]);
		peopleField.setText(application.Main.infoB.get(3)[1]);
	}
	/**
	 * Method to Save the information written on the fields and then return to the dashboard.
	 * @param event
	 * Event of clicking the button.
	 * @throws IOException
	 */
	public void Save(ActionEvent event) throws IOException {

		String[] infoArray = application.Main.infoB.get(0);
		infoArray[1] = nameField.getText().replace(",", "/");
		application.Main.infoB.set(0, infoArray);
		infoArray = application.Main.infoB.get(1);
		infoArray[1] = responsibleField.getText().replace(",", "/");
		application.Main.infoB.set(1, infoArray);
		infoArray = application.Main.infoB.get(2);
		infoArray[1] = departmentField.getText().replace(",", "/");
		application.Main.infoB.set(2, infoArray);
		infoArray = application.Main.infoB.get(3);
		infoArray[1] = peopleField.getText().replace(",", "/");
		application.Main.infoB.set(3, infoArray);

		root = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainGUI.fxml"));
		root = loader.load();
		MainController MainController = loader.getController();

		MainController.SaveAll(application.Main.tableSize);
		MainController.LoadTable();

		stage =(Stage)(backButton.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Method to go back to the dashboard without saving anything.
	 * @param event
	 * Event of clicking the button
	 * @throws IOException
	 */
	public void goBack(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainGUI.fxml"));
		root = loader.load();
		MainController MainController = loader.getController();

		MainController.SaveAll(application.Main.tableSize);
		MainController.LoadTable();

		stage =(Stage)(backButton.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
