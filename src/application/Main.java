package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class Main extends Application {

	private Stage primaryStage;
	private StackPane rootLayout;
	private Button skipButton;

	@FXML
	private Button nextButton;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/base_screen.fxml"));
			rootLayout = (StackPane) loader.load();
			Scene baseScene = new Scene(rootLayout);

			//mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(baseScene);
			primaryStage.setTitle("Tap Tap Revolution");
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

		beginTutorial();
	}

	/** Returns the main stage
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	// Display first tutorial overlay scene
	private void beginTutorial() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/tutorial_first_screen.fxml"));
			AnchorPane mainScreen = (AnchorPane) loader.load();
			rootLayout.getChildren().setAll(mainScreen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
