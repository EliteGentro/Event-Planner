/**
 * EditController
 * @author khf849 
 * Controller for the EditGUI class in order to edit a specific row of activity of an event.
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

public class EditController implements Initializable {

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
	 * hourField
	 * Text Field to input the hour of the activity
	 */
	private TextField hourField;
	@FXML
	/**
	 * placeField
	 * Text Field to input the place of the activity
	 */
	private TextField placeField;
	@FXML
	/**
	 * activityField
	 * Text Field to input the dynamic of the activity
	 */
	private TextField activityField;
	@FXML
	/**
	 * physicalField
	 * Text Field to input the physical plant needs of the activity
	 */
	private TextField physicalField;
	@FXML
	/**
	 * classroomsField
	 * Text Field to input the classroom needed for the activity
	 */
	private TextField classroomsField;
	@FXML
	/**
	 * tiField
	 * Text Field to input the TI department needs for the activity
	 */
	private TextField tiField;
	@FXML
	/**
	 * securityField
	 * Text Field to input the security needs for the activity
	 */
	private TextField securityField;
	@FXML
	/**
	 * commentaryField
	 * Text Field to input extra commentaries about the activity
	 */
	private TextField commentaryField;
	@FXML
	/**
	 * backButton
	 * Button to go back to the dashboard without saving anything.
	 */
	private Button backButton;
	@FXML
	/**
	 * saveButton
	 * Button to save all the information entered in the Scene's fields.
	 */
	private Button saveButton;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	/**
	 * Method that passes all the info from the CSV to the Text Fields.
	 */
	public void passInfo() {
		hourField.setText(application.Main.contents.get(application.Main.rowNumber)[0]);
		placeField.setText(application.Main.contents.get(application.Main.rowNumber)[1]);
		activityField.setText(application.Main.contents.get(application.Main.rowNumber)[2]);
		physicalField.setText(application.Main.contents.get(application.Main.rowNumber)[3]);
		classroomsField.setText(application.Main.contents.get(application.Main.rowNumber)[4]);
		tiField.setText(application.Main.contents.get(application.Main.rowNumber)[5]);
		securityField.setText(application.Main.contents.get(application.Main.rowNumber)[6]);
		commentaryField.setText(application.Main.contents.get(application.Main.rowNumber)[7]);
	}
	/**
	 * Method to save the information written in the fields to the CSV
	 * @param event
	 * Event of clicking the button
	 * @throws IOException
	 */
	public void Save(ActionEvent event) throws IOException {
		String[] rowArray = new String[8];
		rowArray[0]=hourField.getText().replace(",", "/");
		rowArray[1]=placeField.getText().replace(",", "/");
		rowArray[2]=activityField.getText().replace(",", "/");
		rowArray[3]=physicalField.getText().replace(",", "/");
		rowArray[4]=classroomsField.getText().replace(",", "/");
		rowArray[5]=tiField.getText().replace(",", "/");
		rowArray[6]=securityField.getText().replace(",", "/");
		rowArray[7]=commentaryField.getText().replace(",", "/");

		application.Main.contents.set(application.Main.rowNumber, rowArray);

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
	 * Method that goes back to the dashboard without saving anything.
	 * @param event
	 * Event of clicking the button.
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
