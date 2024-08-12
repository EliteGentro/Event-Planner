package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class VideosController implements Initializable {

	@FXML
	/**
	 * video1
	 * First Video (top to bottom)
	 */
	private Button video1;
	@FXML
	/**
	 * video2
	 * Second Video (top to bottom)
	 */
	private Button video2;
	@FXML
	/**
	 * video3
	 * Third Video (top to bottom)
	 */
	private Button video3;
	@FXML
	/**
	 * video4
	 * Fourth Video (top to bottom)
	 */
	private Button video4;
	@FXML
	/**
	 * video5
	 * Fifth Video (top to bottom)
	 */
	private Button video5;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	/**
	 * Method used to show videos for each button, it needs to be separated into one method per each button once the videos are made.
	 * @param event
	 * the event of the button being clicked.
	 * @throws IOException
	 * @throws URISyntaxException
	 * Si tienes una duda específica, porfavor contacta a Jorge Castañeda Castaneda@tec.mx 
	 */
	public void Video(ActionEvent event) throws IOException, URISyntaxException {
		Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("https://www.youtube.com/watch?v=K4TOrB7at0Y"));
	}

}
