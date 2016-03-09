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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TutorialController implements Initializable {
	private static Stage stage;

	/* Page 1 Tutorial buttons */
    @FXML
    private Button nextButton;

    @FXML
    private Button skipButton;

    /* Page 2 Tutorial buttons */
    @FXML
    private Button nextButton2;

    @FXML
    private Button backButton2;

	@Override	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL location, ResourceBundle resources) {
		assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'simple.fxml'.";

        // All @FXML variables will have been injected
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { loadTutorial2(); }
        });

     // Switch to settings scene (via SettingsController) if skip button pressed
        skipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	loadSettings();
            }

        });
	}

	/** Returns the main stage
	 * @return
	 */
	public static Stage getCurrentStage(Button button) {
		stage = (Stage) button.getScene().getWindow();
		return stage;
	}

	/** Returns the second tutorial scene
	 * @return
	 */
	protected static void loadTutorial(Button button) {
		try {
			//FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/tutorial_first_screen.fxml"));
			Stage currStage = getCurrentStage(button);
			//AnchorPane tutorialScreen2 = (AnchorPane) loader.load();
			Pane tutorialScreen2 = (Pane) FXMLLoader.load(Main.class.getResource("/view/tutorial_second_screen.fxml"));

			if (!tutorialScreen2.isVisible()) {
				tutorialScreen2.setVisible(true);
			}
			// Replace with new scene
			Scene scene = new Scene(tutorialScreen2);
			currStage.setScene(scene);
			currStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Returns the second tutorial scene
	 * @return
	 */
	protected void loadTutorial2() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/tutorial_second_screen.fxml"));
			AnchorPane tutorialScreen2 = (AnchorPane) loader.load();
			Stage currStage = getCurrentStage(nextButton);

			if (!tutorialScreen2.isVisible()) {
				tutorialScreen2.setVisible(true);
			}
			// Replace with new scene
			Scene scene = new Scene(tutorialScreen2);
			currStage.setScene(scene);
			currStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadSettings() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/main_screen.fxml"));
			BorderPane settingsScreen = (BorderPane) loader.load();
			Stage currStage = getCurrentStage(skipButton);

			if (!settingsScreen.isVisible()) {
				settingsScreen.setVisible(true);
			}
			// Replace with new scene
			Scene scene = new Scene(settingsScreen);
			currStage.setScene(scene);
			currStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
