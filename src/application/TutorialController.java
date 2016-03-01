/*
* Lydia Ding, Sarah Leong-Fern, Lucy Lu, Nayely Martinez
* This is the controller for tutorials
*/
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

public class TutorialController implements Initializable {
	private Stage stage;

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
            public void handle(ActionEvent event) {
                loadTutorial2();
            }
        });

     // Switch to settings scene (via SettingsController) if skip button pressed
        skipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/settings_screen.fxml"));
            	try {
					Parent root = (Parent)fxmlLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
            	SettingsController controller = fxmlLoader.<SettingsController>getController();
            	controller.loadSettings(skipButton);
            }
        });
	}

	/** Returns the main stage
	 * @return
	 */
	public Stage getCurrentStage(Button button) {
		stage = (Stage) button.getScene().getWindow();
		return stage;
	}

	/** Returns the second tutorial scene
	 * @return
	 */
	protected void loadTutorial2() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/tutorial_second_screen.fxml"));
			AnchorPane tutorialScreen = (AnchorPane) loader.load();
			Stage currStage = getCurrentStage(nextButton);

			if (!tutorialScreen.isVisible()) {
				tutorialScreen.setVisible(true);
			}
			// Replace with new scene
			Scene scene = new Scene(tutorialScreen);
			currStage.setScene(scene);
			currStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
