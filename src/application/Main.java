/**
 * Main
 * @authorkhf849 
 * Contains the main variables that will be efficient to pass through classes constantly to read and write CSVs
 *
 */
package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	/**
	 * root
	 */
	private Parent root;
	/**
	 * scene
	 */
	private Scene scene;
	/*
	 * allGroups
	 * Matrix with all groups with passwords
	 */
	public static ArrayList<String[]> allGroups = new ArrayList <>();
	/*
	 * group
	 * Array with group name and password
	 */
	public static String[] group = new String[2];
	/*
	 * groupNames
	 * Array where to add all the names of the groups
	 */
	public static ArrayList<String> groupNames = new ArrayList <String>();
	/*
	 * categories
	 * array where to add all the activity planner categories
	 */
	public static String[] categories = new String[8];
	/*
	 * contents
	 * Matrix where all the categories and information are added
	 */
	public static ArrayList<String[]> contents = new ArrayList <String[]>();
	/*
	 * infoB
	 * Matrix where all the information of an event is stored
	 */
	public static ArrayList<String[]> infoB = new ArrayList <String[]>();
	/*
	 * infoA
	 * Array with the label and information of each information row.
	 */
	public static String[] infoA = new String[2];
	/*
	 * csvFile
	 * The name of a desired CSV file
	 */
	public static String csvFile = null;
	/*
	 * formatter
	 * Way to format the dates returned by the dates selectors in the program
	 */
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	/*
	 * rowNumber
	 * the number of the row selected
	 */
	public static int rowNumber;
	/*
	 * tableSize
	 * the size of the table in the dashboard.
	 */
	public static int tableSize;
	/*
	 * groupName
	 * the number of the row selected
	 */
	public static String groupName;
	/**
	 * Starts the application
	 * @param stage
	 * the main stage of the application
	 */
	@Override
	public void start(Stage stage) {
		try {
			Text();
			root = FXMLLoader.load(getClass().getResource("/application/LoginGUI.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Reads the csv file and gets it to the allQuestions Array list.
	 * @throws IOException
	 * Exception for input/output exception
	 */
	public static void Text() throws IOException {
		File dir = new File("src/Files");
		dir.mkdirs();
		File leaderboard = new File (dir, "StudentGroups.txt");
		BufferedReader readFile = new BufferedReader (new FileReader(leaderboard));
		String line = readFile.readLine();
		StringTokenizer tokenLine;
		int j = 0;
		while(line != null)
		{
			tokenLine = new StringTokenizer(line, ":");
			int i = 0;
			while(tokenLine.hasMoreTokens()) {
				group[i] = (tokenLine.nextToken());
				i++;
			}
			groupNames.add(group[0]);
			allGroups.add(j, group);
			line = readFile.readLine();
			j++;
		}
		readFile.close();
	}
	/**
	 * Reads the csv file and gets it to the allQuestions Array list.
	 * @param csvName
	 * Name of the CSV File
	 * @throws IOException
	 * Exception for input/output exception
	 */
	public static void CSVRead(String csvName) throws IOException {
		infoB.clear();
		contents.clear();
		String line = "";
		String csvSplitBy = ",";
		BufferedReader br = new BufferedReader(new FileReader(csvName));
		int lineCount = 0;
		while((line = br.readLine())!=null) {	// Checks for lines
			if(lineCount<7) {
				infoA = line.split(csvSplitBy);
				infoB.add(infoA);
				lineCount++;
			} else {
				categories = line.split(csvSplitBy);
				contents.add(categories);
			}
		}
		br.close();
	}
}
