package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import application.Main;

public class PauseController implements Initializable {

	/* Inject FXML buttons */
	@FXML
	private Button quitAndReadTutorialButton;
	@FXML
	private Button restartGameButton;

	/** This method is called by the FXMLLoader on scene startup
	 * and sets the actions of the two buttons in the pause screen controller
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert quitAndReadTutorialButton != null : "fx:id=\"resumeGameButton\" was not injected: check your FXML file 'pause_screen.fxml'.";
		assert restartGameButton != null : "fx:id=\"resumeGameButton\" was not injected: check your FXML file 'pause_screen.fxml'.";

		quitAndReadTutorialButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	loadTutorial(quitAndReadTutorialButton);
            }
        });

		restartGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	restartGame(restartGameButton);
            }
        });
	}

	/** Switches from new game screen to the first tutorial screen
	 * @return
	 */
	public void loadTutorial(Button quitAndReadTutorialButton) {
		FXMLLoader tut_first_loader2 = new FXMLLoader(Main.class.getResource("/view/tutorial_first_screen.fxml"));
		Main.switchScreen(tut_first_loader2,quitAndReadTutorialButton);
	}

	/** Switches from pause screen to new game screen
	 * @return
	 */
	public void restartGame(Button button) {
		FXMLLoader restart_loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
		Main.switchScreen(restart_loader, button);
	}
}