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
import javafx.stage.Stage;
import javafx.stage.Window;
import application.Main;

public class NewGameController implements Initializable {
	private Stage stage;

	@FXML
	private Button readTutorialButton;

    @FXML
    private Button startGameButton;

	@Override	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL location, ResourceBundle resources) {
		assert readTutorialButton != null : "fx:id=\"readTutorialButton\" was not injected: check your FXML file 'new_game_screen.fxml'.";

		readTutorialButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	TutorialController.loadTutorial(readTutorialButton);
            }
        });
	}

	public void loadTutorial(Button readTutorialButton) {
		Window window = readTutorialButton.getScene().getWindow();
		Stage stage = (Stage) window;

		try {
			// Replace with new AnchorPane scene on current stage
			FXMLLoader tut_first_loader = new FXMLLoader(Main.class.getResource("/view/tutorial_second_screen.fxml"));
			AnchorPane screen = (AnchorPane) tut_first_loader.load();
			Scene scene = new Scene(screen);
			stage.setScene(scene);
			stage.setTitle("Tap Tap Revolution");
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}

		//Main.switchScreen(loader, readTutorialButton);
	}

	/** Returns the main stage
	 * @return
	 */
	public Stage getCurrentStage(Button button) {
		stage = (Stage) button.getScene().getWindow();
		return stage;
	}

	// Switch to settings scene and display on stage
	public void loadPlayScreen(Button button) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
		Main.switchScreen(loader, button);

			/***first way to do it***/
//			Game game = new Game(int difficulty_level, Str song_title);
//			main.getGame(game);
			/***second way to do it***/
//			main.getSongInfo(int difficulty_level, Str song_title);
//			AnchorPane settingsScreen = (AnchorPane) loader.load();
//
//			if (!settingsScreen.isVisible()) {
//				settingsScreen.setVisible(true);
//			}
//			// Replace with new scene
//			Scene scene = new Scene(settingsScreen);
//			Stage currStage = getCurrentStage(button);
//			currStage.setScene(scene);
//			currStage.show();



		/* OLD CODE FOR LOADING CONTROLLER */
		// Listen for button clicks
//		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'new_game_screen.fxml'.";

//        // Switch to tutorial scene (via TutorialControllerOne)
//        backButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//            	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/tutorial2.fxml"));
//            	TutorialControllerOne controller = fxmlLoader.<TutorialControllerOne>getController();
//            	controller.loadTutorial2();
//            }
//        });
	}

}
