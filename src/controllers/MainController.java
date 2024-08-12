/**
 * MainController
 * @author khf849 
 * Controller of the MainGUI in which the user can edit CSV of school events directly from the program's interface.
 *
 */
package controllers;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import application.Events;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController implements Initializable {

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
	/**
	 * stage1
	 */
	public Stage stage1 = new Stage();

	@FXML
	/**
	 * nameLabel
	 * Label to mark where the name of the project is listed.
	 */
	private Label nameLabel;
	@FXML
	/**
	 * responsibleLabel
	 * Label to mark where the responsible of the project is listed.
	 */
	private Label responsibleLabel;
	@FXML
	/**
	 * departmentLabel
	 * Label to mark where the department of the project is listed.
	 */
	private Label departmentLabel;
	@FXML
	/**
	 * peopleLabel
	 * Label to mark where the number of people involved in the project is listed.
	 */
	private Label peopleLabel;
	@FXML
	/**
	 * tabla
	 * Table that will contain the event information.
	 */
	private TableView<Events> tabla;
	@FXML
	/**
	 * hourCol
	 * Table Column containing the hour of each division of the event.
	 */
	private TableColumn<Events, String> hourCol;
	@FXML
	/**
	 * placeCol
	 * Table Column containing the place of each division of the event.
	 */
	private TableColumn<Events, String> placeCol;
	@FXML
	/**
	 * activityCol
	 * Table Column containing description of each activity.
	 */
	private TableColumn<Events, String> activityCol;
	@FXML
	/**
	 * physicalCol
	 * Table Column containing needs from the physical department.
	 */
	private TableColumn<Events, String> physicalCol;
	@FXML
	/**
	 * classroomsCol
	 * Table Column containing the needed classrooms for each activity.
	 */
	private TableColumn<Events, String> classroomsCol;
	@FXML
	/**
	 * tiCol
	 * Table Column containing needs from the TI department.
	 */
	private TableColumn<Events, String> tiCol;
	@FXML
	/**
	 * tiCol
	 * Table Column containing needs from the security department.
	 */
	private TableColumn<Events, String> securityCol;
	@FXML
	/**
	 * commentaryCol
	 * Table Column containing additional commentaries for each activity.
	 */
	private TableColumn<Events, String> commentaryCol;
	@FXML
	/**
	 * quitarFilaButton
	 * Button to remove a Row / Activity
	 */
	private MenuItem quitarFilaButton;
	@FXML
	/**
	 * tableroButton
	 *  Button to go back to the dashboard / SecondGUI
	 */
	private MenuItem tableroButton;
	@FXML
	/**
	 * cerrarButton
	 * Button to go back to the Login page.
	 */
	private MenuItem cerrarButton;
	@FXML
	/**
	 * mainMenuBar
	 * Bar containing different buttons.
	 */
	private MenuBar mainMenuBar;
	@FXML
	/**
	 * addButton
	 * Button to add a new row/activity above the selected one.
	 */
	private MenuItem addButton;
	@FXML
	/**
	 * addButtonDown
	 * Button to add a new row/activity below the selected one.
	 */
	private MenuItem addButtonDown;
	@FXML
	/**
	 * editarFilaButton
	 * Button to edit the selected row.
	 */
	private MenuItem editarFilaButton;
	@FXML
	/**
	 * editInfoButton
	 * button to edit the event's main info.
	 */
	private MenuItem editInfoButton;
	@FXML
	/**
	 * frequentButton
	 * button to open FAQ.
	 */
	private MenuItem frequentButton;
	@FXML
	/**
	 * videoButton
	 * button to open video tutorials.
	 */
	private MenuItem videoButton;
	@FXML
	/**
	 * eventStartDatePicker
	 * Date Picker to pick the event's start date.
	 */
	private DatePicker eventStartDatePicker;
	@FXML
	/**
	 * eventEndDatePicker
	 * Date Picker to pick the event's end date.
	 */
	private DatePicker eventEndDatePicker;
	@FXML
	/**
	 * setUpDatePicker
	 * Date Picker to pick the event's set up date.
	 */
	private DatePicker setUpDatePicker;
	@FXML
	/**
	 * data
	 * List that will contain the information added to the table.
	 */
	private ObservableList<Events> data = FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	/**
	 * Method to go back to the login page
	 * @param event
	 * Event of button being clicked
	 * @throws IOException
	 */
	public void goLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/LoginGUI.fxml"));
		stage =(Stage)(mainMenuBar.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Method to go back to the dashboard page
	 * @param event
	 * Event of button being clicked
	 * @throws IOException
	 */
	public void goTablero(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/SecondGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SecondGUI.fxml"));
		root = loader.load();
		SecondController secondController = loader.getController();
		secondController.fillingNameTable();
		stage =(Stage)(mainMenuBar.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Method to edit the main info of the event
	 * @param event
	 * Event of button being clicked
	 * @throws IOException
	 */
	public void EditInfo(ActionEvent event) throws IOException {
		application.Main.tableSize = tabla.getItems().size();

		root = FXMLLoader.load(getClass().getResource("/application/EditInfoGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditInfoGUI.fxml"));
		root = loader.load();
		EditInfoGUIController editInfoGUIController = loader.getController();
		editInfoGUIController.passInfo();

		stage =(Stage)(mainMenuBar.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Method to save the start date upon its modification
	 * @param event
	 * @throws IOException
	 * event of date being changed
	 */
	public void SaveStartEventDate(ActionEvent event) throws IOException {
		SaveDate(eventStartDatePicker, application.Main.infoB,4,1);
		SaveAll(tabla.getItems().size());
	}
	/**
	 * Method to save the end date upon its modification
	 * @param event
	 * @throws IOException
	 * event of date being changed
	 */
	public void SaveEndEventDate(ActionEvent event) throws IOException {
		SaveDate(eventEndDatePicker, application.Main.infoB,5,1);
		SaveAll(tabla.getItems().size());
	}
	/**
	 * Method to save the setup date upon its modification
	 * @param event
	 * @throws IOException
	 * event of date being changed
	 */
	public void SaveSetUpDate(ActionEvent event) throws IOException {
		SaveDate(setUpDatePicker, application.Main.infoB,6,1);
		SaveAll(tabla.getItems().size());
	}
	/**
	 * Method to edit the information of a specific row in the table
	 * @param event
	 * event of the button being clicked
	 * @throws IOException
	 */
	public void goEdit(ActionEvent event) throws IOException {
		if (tabla.getSelectionModel().getSelectedIndex() >= 0) {
			application.Main.tableSize = tabla.getItems().size();
			application.Main.rowNumber = tabla.getSelectionModel().getSelectedIndex()+1;

			root = FXMLLoader.load(getClass().getResource("/application/EditGUI.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditGUI.fxml"));
			root = loader.load();
			EditController editController = loader.getController();
			editController.passInfo();

			stage =(Stage)(mainMenuBar.getScene().getWindow());
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			JOptionPane.showMessageDialog ( null , "Seleccione una fila para editar." );
		}
	}
	/**
	 * Method to add a row above the row selected
	 * @param event
	 * Event of the button being clicked
	 * @throws IOException
	 */
	public void addRow(ActionEvent event) throws IOException {
		if (tabla.getSelectionModel().getSelectedIndex() >= 0) {
			String[] rowFill = new String[8];
			for(int i=0; i<8;i++) {
				rowFill[i]="-";
			}
			application.Main.contents.add(tabla.getSelectionModel().getSelectedIndex()+2, rowFill);
			SaveAll(tabla.getItems().size()+1);
			LoadTable();
		}
	}
	/**
	 * Method to add a row below the row selected
	 * @param event
	 * Event of the button being clicked
	 * @throws IOException
	 */
	public void addRowDown(ActionEvent event) throws IOException {
		if (tabla.getSelectionModel().getSelectedIndex() >= 0) {
			String[] rowFill = new String[8];
			for(int i=0; i<8;i++) {
				rowFill[i]="-";
			}
			application.Main.contents.add(tabla.getSelectionModel().getSelectedIndex()+1, rowFill);
			SaveAll(tabla.getItems().size()+1);
			LoadTable();
		}
	}
	/**
	 * Method to delete the selected row
	 * @param event
	 * Event of the button being clicked
	 * @throws IOException
	 */
	public void deleteRow(ActionEvent event) throws IOException {
		if (tabla.getSelectionModel().getSelectedIndex() >= 0) {
			application.Main.contents.remove(tabla.getSelectionModel().getSelectedIndex()+1);
			SaveAll(tabla.getItems().size()-1);
			LoadTable();
		}
	}
	/**
	 * Method to put everything needed to load the table's information into a single line.
	 */
	public void LoadTable() {
		try {
			clearingEventTable();
			application.Main.CSVRead("src/Files/"+application.Main.groupName + "/" + application.Main.csvFile); //Cambiar cuando se separe por folders
			fillingEventTable();
			fillingInfo();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to fill the table into a single line.
	 */
	public void fillingEventTable() {

		hourCol.setText(application.Main.contents.get(0)[0]);
		placeCol.setText(application.Main.contents.get(0)[1]);
		activityCol.setText(application.Main.contents.get(0)[2]);
		physicalCol.setText(application.Main.contents.get(0)[3]);
		classroomsCol.setText(application.Main.contents.get(0)[4]);
		tiCol.setText(application.Main.contents.get(0)[5]);
		securityCol.setText(application.Main.contents.get(0)[6]);
		commentaryCol.setText(application.Main.contents.get(0)[7]);

		hourCol.setCellValueFactory(new PropertyValueFactory<Events, String>("hour"));
		placeCol.setCellValueFactory(new PropertyValueFactory<Events, String>("place"));
		activityCol.setCellValueFactory(new PropertyValueFactory<Events, String>("activity"));
		physicalCol.setCellValueFactory(new PropertyValueFactory<Events, String>("physical"));
		classroomsCol.setCellValueFactory(new PropertyValueFactory<Events, String>("classrooms"));
		tiCol.setCellValueFactory(new PropertyValueFactory<Events, String>("ti"));
		securityCol.setCellValueFactory(new PropertyValueFactory<Events, String>("security"));
		commentaryCol.setCellValueFactory(new PropertyValueFactory<Events, String>("commentary"));
		tabla.setEditable(true);
		for(int i=1; i < application.Main.contents.size(); i++) {
			data.add(new Events(attribute(i,0),attribute(i,1),attribute(i,2),attribute(i,3),attribute(i,4),attribute(i,5),attribute(i,6),attribute(i,7))); 
		}
		tabla.setItems(data);
	}
	/**
	 * Method to read and pass all the event's information into a single line.
	 */
	public void fillingInfo() {
		nameLabel.setText(application.Main.infoB.get(0)[1]);
		responsibleLabel.setText(application.Main.infoB.get(1)[1]);
		departmentLabel.setText(application.Main.infoB.get(2)[1]);
		peopleLabel.setText(application.Main.infoB.get(3)[1]);
		LocalDate startDate = LocalDate.parse(application.Main.infoB.get(4)[1], application.Main.formatter); //falta Start Date
		LocalDate endDate = LocalDate.parse(application.Main.infoB.get(5)[1], application.Main.formatter);
		LocalDate setUpDate = LocalDate.parse(application.Main.infoB.get(6)[1], application.Main.formatter);
		eventStartDatePicker.setValue(startDate);
		eventEndDatePicker.setValue(endDate);
		setUpDatePicker.setValue(setUpDate);
	}
	/**
	 * Method to clear the table into a single line.
	 */
	public void clearingEventTable() {
		data.clear();
		tabla.setItems(data);
	}
	/**
	 * Method to put everything needed to save a date into a single line.
	 * @param picker
	 * Date Picker with the value of the date
	 * @param list
	 * the list to save the information needed
	 * @param number1
	 * Containing the coordinates of where the date will be saved.
	 * @param number2
	 * Containing the coordinates of where the date will be saved
	 */
	public void SaveDate(DatePicker picker, ArrayList <String[]> list, int number1, int number2) {
		String[] date = application.Main.infoB.get(number1);
		date[number2]= picker.getValue().format(application.Main.formatter);
		application.Main.infoB.set(number1, date);
	}
	/**
	 * Method to save everything to the CSV into a single line.
	 * @param tableSize
	 * the current size of the tables or rows it contains.
	 * @throws IOException
	 */
	public void SaveAll(int tableSize) throws IOException {
		FileWriter writer = new FileWriter("src/Files/"+application.Main.groupName + "/" + application.Main.csvFile);
		for(int a=0;a<7;a++) {
			for(int b=0;b<2;b++) {
				writer.append(application.Main.infoB.get(a)[b] + ",");
			}
			writer.append("\n");
		}
		for(int a=0;a<=tableSize;a++) {
			for(int b=0;b<8;b++) {
				writer.append(attribute(a,b) + ",");		
			}
			writer.append("\n");
		}
		writer.flush();
		writer.close();
	}
	
	public void Questions(ActionEvent event) throws IOException {
		Parent root1= FXMLLoader.load(getClass().getResource("/application/QuestionsGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage1.setScene(scene1);
		stage1.show();
	}
	public void Tutorials(ActionEvent event) throws IOException {
		Parent root1= FXMLLoader.load(getClass().getResource("/application/VideosGUI.fxml"));
		Scene scene1 = new Scene(root1);
		stage1.setScene(scene1);
		stage1.show();
	}
	
	/**
	 * Puts the method needed to return an attribute from the contents matrix easier and shorter.
	 * @param row
	 * number of row meant to be obtained
	 * @param column
	 * number of column meant to be obtained
	 * @return
	 * Returns the contents of the contents matrix at a certain coordinate.
	 */
	public String attribute(int row, int column) {
		return application.Main.contents.get(row)[column];
	}
	
}

