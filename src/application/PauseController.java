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

public class PauseController implements Initializable {
	private Stage stage;

	@FXML
	private Button resumeGameButton;

	@FXML
	private Button restartGameButton;

	@Override	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL location, ResourceBundle resources) {
		assert resumeGameButton != null : "fx:id=\"resumeGameButton\" was not injected: check your FXML file 'pause_screen.fxml'.";
		assert restartGameButton != null : "fx:id=\"resumeGameButton\" was not injected: check your FXML file 'pause_screen.fxml'.";

		resumeGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	resumeGame(resumeGameButton);
            }
        });

		restartGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	restartGame(restartGameButton);
            }
        });
	}

	/** Display game screen
	 * @return
	 */
	public void resumeGame(Button button) {
		FXMLLoader resume_loader = new FXMLLoader(Main.class.getResource("/view/play_screen.fxml"));
		Main.switchScreen(resume_loader, button);
	}

	/** Restart game
	 * @return
	 */
	public void restartGame(Button button) {
		FXMLLoader restart_loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
		Main.switchScreen(restart_loader, button);
	}

	/** Returns the main stage
	 * @return
	 */
	public Stage getCurrentStage(Button button) {
		stage = (Stage) button.getScene().getWindow();
		return stage;
	}

}