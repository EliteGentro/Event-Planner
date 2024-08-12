/**
 * SecondController
 * @author khf849 
 * Class that controlls everything to do with the secondGUI, which is where the user can select an event to edit.
 *
 */
package controllers;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
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

public class SecondController implements Initializable{

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
	 * openButton
	 * Button to open a file in the dashboard.
	 */
	private Button openButton;
	@FXML
	/*
	 * nameField
	 * Field were to type the name of the event.
	 */
	private TextField nameField;
	@FXML
	/*
	 * createButton
	 * Button to create a new file/event.
	 */
	private Button createButton;
	@FXML
	/*
	 * renameButton
	 * Button that gets the user to the Rename Tab.
	 */
	private Button renameButton;
	@FXML
	/*
	 * exportButton
	 * Button that exports an event as a file to a desired location.
	 */
	private Button exportButton;
	@FXML
	/*
	 * searchButton
	 * Button to search for a specific set of characters contained in an event name.
	 */
	private Button searchButton;
	@FXML
	/*
	 * importButton
	 * Button to import a file as an event in the program.
	 */
	private Button importButton;
	@FXML
	/*
	 * deleteButton
	 * Button to delete an event.
	 */
	private Button deleteButton;
	@FXML
	/*
	 * datesButton
	 * Button to go into the Date Management tab
	 */
	private Button datesButton;
	@FXML
	/*
	 * searchField
	 * TextField to look for a specific set of characters contained in an event name.
	 */
	private TextField searchField;
	@FXML
	/*
	 * name
	 * Table that contains all the file names to be selected.
	 */
	private TableView <Events> name;
	@FXML
	/*
	 * eventsColumn
	 * Each column of the table "name".
	 */
	private TableColumn<Events, String> eventsColumn;
	@FXML
	/*
	 * data
	 * Observable list that the "name" table will contain
	 */
	private ObservableList<Events> data = FXCollections.observableArrayList();

	/*
	 * dir
	 * Directory of the files specific to the group name the user is logged into.
	 */
	File dir = new File("src/Files/"+application.Main.groupName);



	@Override

	public void initialize(URL location, ResourceBundle resources) {
	}
	/**
	 * Goes to the dashboard scene of the selected event.
	 * @param event
	 * the event of clicking the edit button.
	 */
	public void goEditar(ActionEvent event) throws IOException {
		if (name.getSelectionModel().getSelectedIndex() >= 0) {
			application.Main.csvFile = name.getSelectionModel().getSelectedItems().get(0).getName().toString();
			root = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainGUI.fxml"));
			root = loader.load();
			MainController MainController = loader.getController();
			MainController.LoadTable();

			stage =(Stage)(openButton.getScene().getWindow());
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	/**
	 * Opens file explorer for the user to find a location to export a file.
	 * @param event
	 * the event of clicking the export button.
	 */
	public void ExportFile(ActionEvent event) {
		if(name.getSelectionModel().getSelectedItems().get(0).getName().toString()!= null) {
			String selectedFolder = chooseFolder();
			if (selectedFolder != null) {
				String original = ("src/Files/"+application.Main.groupName + "/"+  name.getSelectionModel().getSelectedItems().get(0).getName().toString());
				String export = (selectedFolder + "/"+ name.getSelectionModel().getSelectedItems().get(0).getName().toString());
				copyFile(original,export);
			}
		} else {
			JOptionPane.showMessageDialog ( null , "Porfavor escoge un archivo." );
		}
	}
	/**
	 * Opens file explorer for the user to find a location from where to import a file from.
	 * @param event
	 * the event of clicking the import button.
	 */
	public void ImportFile(ActionEvent event) {
		try {
			String selectedFile = chooseFile();
			if (selectedFile != null) {
				ArrayList<String> divisions = new ArrayList<String>();
				StringTokenizer divide = new StringTokenizer(selectedFile, File.separator);
				while (divide.hasMoreTokens()) {
					divisions.add(divide.nextToken());
				}
				String export = ("src/Files/"+application.Main.groupName + "/"+  divisions.get(divisions.size()-1));
				String imported = (selectedFile);
				copyFile(imported,export);
				clearingNameTable();
				fillingNameTable();
				try {
					application.Main.csvFile = divisions.get(divisions.size()-1);
					root = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainGUI.fxml"));
					root = loader.load();
					MainController MainController = loader.getController();
					MainController.LoadTable();
				}catch(Exception e) {
					File toDelete = new File ("src/Files/"+application.Main.groupName + "/"+  divisions.get(divisions.size()-1));
					if(toDelete.delete()) {
						clearingNameTable();
						fillingNameTable();
					}
					JOptionPane.showMessageDialog ( null , "Revise que el documento sea del tipo: csv y siga el formato correcto" );
				}
			}
			else {
				JOptionPane.showMessageDialog ( null , "Algo salió mal, porfavor intente de nuevo" );
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog ( null , "Algo salió mal, porfavor intente de nuevo" );
		}
	}
	/**
	 * Deletes a selected file of the student group's folder
	 * @param event
	 * the event of clicking the delete button.
	 */
	public void DeleteFile(ActionEvent event) {
		try {
			if(name.getSelectionModel().getSelectedItems().get(0).getName().toString()!= null) {
				File toDelete = new File ("src/Files/"+application.Main.groupName + "/"+  name.getSelectionModel().getSelectedItems().get(0).getName().toString());
				if(toDelete.delete()) {
					clearingNameTable();
					fillingNameTable();
				}else {
					JOptionPane.showMessageDialog ( null , "Algo salió mal, porfavor intente de nuevo" );
				}
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog ( null , "Algo salió mal, porfavor intente de nuevo" );
		}
	}
	/**
	 * Searches for a specific set of characters and filters the table to fit the found characters.
	 * @param event
	 * the event of clicking the search button.
	 */
	public void Search(ActionEvent event) {
		File[] fileNames = dir.listFiles();
		data.clear();
		name.setItems(data);
		eventsColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("name"));
		name.setEditable(true);
		for(int i=0; i < fileNames.length; i++) {
			data.add(new Events(fileNames[i].getName()));
		}
		name.setItems(searchList(searchField.getText(),data));
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
	public FilteredList<Events> searchList(String wordSearch, ObservableList<Events> list){ 									//got it from here https://www.youtube.com/watch?v=VUVqamT8Npc
		List<String> filteredWords = Arrays.asList(wordSearch.trim().split(" "));
		FilteredList<Events> filteredList = new FilteredList<>(list);

		filteredList.setPredicate(event -> filteredWords.stream()
				.allMatch(word -> event.getName().toLowerCase().contains(word.toLowerCase())));
		return filteredList;

	}
	/**
	 * Creates a file with the desired name
	 * @param event
	 * the event of clicking the create file button.
	 */
	public void CreateFile(ActionEvent event) throws IOException {
		File CSVdir = new File("src/Files/"+application.Main.groupName);
		String CSVname = (nameField.getText()+".csv");
		File newCSV = new File(CSVdir, CSVname);
		if(newCSV.createNewFile()) {
			//debug
		}else {
			JOptionPane.showMessageDialog ( null , "El nombre de este archivo ya existe o no es correcto" );
		}
		fillingCSV(newCSV);
		application.Main.CSVRead("src/Files/"+application.Main.groupName + "/" + CSVname);  
		application.Main.csvFile = CSVname;

		root = FXMLLoader.load(getClass().getResource("/application/MainGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainGUI.fxml"));
		root = loader.load();
		MainController MainController = loader.getController();
		MainController.LoadTable();

		stage =(Stage)(openButton.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Takes the user to the rename scene to rename the document
	 * @param event
	 * the event of clicking the rename button.
	 */
	public void goRename(ActionEvent event) throws IOException {
		application.Main.csvFile = name.getSelectionModel().getSelectedItems().get(0).getName().toString();

		root = FXMLLoader.load(getClass().getResource("/application/RenameGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/RenameGUI.fxml"));
		root = loader.load();
		RenameController renameController = loader.getController();
		renameController.FillField(name.getSelectionModel().getSelectedItems().get(0).getName().toString());

		stage =(Stage)(openButton.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Takes the user to the Event management page to sort dates and events.
	 * @param event
	 * the event of clicking the dates button.
	 */
	public void goDates(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/application/DatesGUI.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/DatesGUI.fxml"));
		root = loader.load();
		DatesController datesController = loader.getController();
		datesController.fillTable();

		stage =(Stage)(datesButton.getScene().getWindow());
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Puts everything needed to fill out a table in a single method.
	 */
	public void fillingNameTable() {
		File[] fileNames = dir.listFiles();
		eventsColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("name"));
		name.setEditable(true);
		for(int i=0; i < fileNames.length; i++) {
			data.add(new Events(fileNames[i].getName()));
		}
		name.setItems(data);
	}
	/**
	 * The method needed to fill a CSV from the start with the proper format.
	 * @param fileName
	 * the name of the CSV file to be created
	 */
	public void fillingCSV(File fileName) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		//Filling CSV
		writer.append("Nombre del Evento:,Nombre"+"\n");
		writer.append("Responsable:,Responsable"+"\n");
		writer.append("Departamento:,Departamento"+"\n");
		writer.append("Cantidad de Personas:,0"+"\n");
		writer.append("Fecha de Inicio:,01/01/2023"+"\n");
		writer.append("Fecha de Clausura:,01/01/2023"+"\n");
		writer.append("Fecha de Montaje:,01/01/2023"+"\n");
		writer.append("Hora,Lugar,Actividad,Planta Física,Salones,TI,Seguridad,Comentarios"+"\n");
		for(int a=0;a<5;a++) {
			for(int b=0;b<8;b++) {
				writer.append("-,");		
			}
			writer.append("\n");
		}
		writer.flush();
		writer.close();
	}
	/**
	 * prompts the file explorer to choose a folder
	 * @return file.getAbsolutePath
	 * either the path for a folder selected or null
	 */
	public static String chooseFolder() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			return file.getAbsolutePath();
		} else {
			return null;
		}
	}
	/**
	 * prompts the file explorer to choose a file
	 * @return file.getAbsolutePath
	 * either the path for a file selected or null
	 */
	public static String chooseFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			return file.getAbsolutePath();
		} else {
			return null;
		}
	}
	/**
	 * prompts the file explorer to choose a folder
	 * @param source
	 * The original file path that will be copied into the group folder
	 * @param destination
	 * The destination path where the imported file will go to.
	 */
	public static void copyFile(String source, String destination) {
		Path sourcePath = Paths.get(source);
		Path destinationPath = Paths.get(destination);
		try {
			Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to clear all the information in the table for refresh or update.
	 */
	public void clearingNameTable() {
		data.clear();
		name.setItems(data);
	}
}
