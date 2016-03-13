package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import application.Main;

public class PlayController implements Initializable {
	private Stage stage;

	@FXML
	private Button pauseButton;
	
	@FXML
	private ProgressBar progressBar;

	@Override	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL location, ResourceBundle resources) {
		assert pauseButton != null : "fx:id=\"pauseButton\" was not injected: check your FXML file 'play_screen.fxml'.";

		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	pauseGame(pauseButton);
            }
        });
	}

	/** Display tutorial
	 * @return
	 */
	public void pauseGame(Button readTutorialButton) {
		FXMLLoader pause_loader = new FXMLLoader(Main.class.getResource("/view/pause_screen.fxml"));
		Main.switchScreen(pause_loader,readTutorialButton);
	}

	/** Returns the main stage
	 * @return
	 */
	public Stage getCurrentStage(Button button) {
		stage = (Stage) button.getScene().getWindow();
		return stage;
	}	
	
	public void updateProgressBar(long progress){
		progressBar.setProgress(progress);
	}
	
		public void updateProgressBar(long progress){
		progressBar.setProgress(progress);
	}

}