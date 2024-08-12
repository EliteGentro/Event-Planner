/**
 * DatesController
 * @author khf849 
 * Controller for DatesGUI in which the user can manage event by sorting them by name and date.
 *
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.Events;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DatesController implements Initializable {
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
	 * searchField
	 * Field to input text to be searched in the file names
	 */
	private TextField searchField;
	@FXML
	/*
	 * searchButton
	 * Button to run the search method
	 */
	private Button searchButton;
	@FXML
	/*
	 * eventsColumn
	 * 1 of 2 columns in the event management scene to show the events' names
	 */
	private TableColumn<Events, String> eventsColumn;
	@FXML
	/*
	 * name
	 * Table containing the events and their start date.
	 */
	private TableView<Events> name;
	@FXML
	/*
	 * backButton
	 * Button to go back to the event selector.
	 */
	private Button backButton;
	@FXML
	/*
	 * dateColumn
	 * Table containing the events and their start date.
	 */
	private TableColumn<Events, String> dateColumn;
	/*
	 * data
	 * Table list that the table "name" will use to be filled.
	 */
	private ObservableList<Events> data = FXCollections.observableArrayList();
	/*
	 * dir
	 * Directory of the files specific to the group name the user is logged into.
	 */
	File dir = new File("src/Files/"+application.Main.groupName);
	/*
	 * tableFill
	 * Matrix to fill the table with.
	 */
	public static ArrayList<String[]> tableFill = new ArrayList <String[]>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	/**
	 * Puts everything needed to fill out a the name table.
	 */
	public void fillTable() throws IOException{
		tableFill.clear();
		File[] fileNames = dir.listFiles();
		eventsColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("name"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("startDate"));
		for(int i=0; i<fileNames.length;i++) {
			String[] nameAndDate = new String[2];
			application.Main.CSVRead("src/Files/"+application.Main.groupName + "/" + fileNames[i].getName());
			nameAndDate[0]= fileNames[i].getName();
			nameAndDate[1]= application.Main.infoB.get(4)[1];
			tableFill.add(nameAndDate);
		}
		for(int i=0; i<fileNames.length;i++) {
			data.add(new Events(tableFill.get(i)[0], tableFill.get(i)[1]));
		}
		name.setItems(data);
	}
	/**
	 * Goes to the dashboard scene of the selected event.
	 * @param event
	 * the event of clicking the search button.
	 */
	public void Search(ActionEvent event) throws IOException {
		File[] fileNames = dir.listFiles();
		data.clear();
		name.setItems(data);
		eventsColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("name"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("startDate"));
		name.setEditable(true);
		for(int i=0; i < fileNames.length; i++) {
			String[] nameAndDate = new String[2];
			application.Main.CSVRead("src/Files/"+application.Main.groupName + "/" + fileNames[i].getName());
			nameAndDate[0]= fileNames[i].getName();
			nameAndDate[1]= application.Main.infoB.get(4)[1];
			tableFill.add(nameAndDate);
			data.add(new Events(tableFill.get(i)[0], tableFill.get(i)[1]));
		}
		name.setItems(searchList(searchField.getText(), data));
	}
	/**
	 * Returns the filtered list filtered by the method "search".
	 * @param wordSearch
	 * the characters that are searched.
	 * @param list
	 * the list that contains the complete list of events.
	 * @return filteredList
	 * the modified list to be displayed in the table
	 */
	public FilteredList<Events> searchList(String wordSearch, ObservableList<Events> list){ 
		List<String> filteredWords = Arrays.asList(wordSearch.trim().split(" "));
		FilteredList<Events> filteredList = new FilteredList<>(list);

		filteredList.setPredicate(event -> filteredWords.stream()
				.allMatch(word -> event.getName().toLowerCase().contains(word.toLowerCase()) ||
						event.getStartDate().toLowerCase().contains(word.toLowerCase())));
		return filteredList;

	}
	/**
	 * Goes back to the file selector scene.
	 * @param event
	 * the event of clicking the back button.
	 */
	public void GoBack(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/SecondGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/SecondGUI.fxml"));
		root = loader.load();
		SecondController secondController = loader.getController();
		secondController.fillingNameTable();
		stage =(Stage)(backButton.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
