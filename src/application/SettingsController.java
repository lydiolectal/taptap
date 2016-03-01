package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
	private Stage stage;

    @FXML
    private Button backButton;

    @FXML
    private Button beginGame;

	@Override	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL location, ResourceBundle resources) {
	}

	/** Returns the main stage
	 * @return
	 */
	public Stage getCurrentStage(Button button) {
		stage = (Stage) button.getScene().getWindow();
		return stage;
	}

	// Switch to settings scene and display on stage
	public void loadSettings(Button button) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/settings_screen.fxml"));
			AnchorPane settingsScreen = (AnchorPane) loader.load();

			if (!settingsScreen.isVisible()) {
				settingsScreen.setVisible(true);
			}
			// Replace with new scene
			Scene scene = new Scene(settingsScreen);
			Stage currStage = getCurrentStage(button);
			currStage.setScene(scene);
			currStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Listen for button clicks
		assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'settings_screen.fxml'.";

        // Switch to tutorial scene (via TutorialController)
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/tutorial_second_screen.fxml"));
            	TutorialController controller = fxmlLoader.<TutorialController>getController();
            	controller.loadTutorial2();
            }
        });
	}


}
