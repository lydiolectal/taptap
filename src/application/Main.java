package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class Main extends Application {

	private static Stage primaryStage;
	private StackPane rootLayout;

	@FXML
	private Button nextButton;

	@FXML
	private Button nextButton2;

	@FXML
	private Button backButton2;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
			//switchScreen(loader);
			AnchorPane screen = (AnchorPane) loader.load();

			// Replace with new scene
			Scene scene = new Scene(screen);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tap Tap Revolution");
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** Returns the main stage
	 * @return
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void switchScreen(FXMLLoader loader, Button button) {
		try {
			Window window = button.getScene().getWindow();
			AnchorPane screen = (AnchorPane) loader.load();
			Stage stage = (Stage) window;

			// Replace with new scene
			Scene scene = new Scene(screen);
			stage.setScene(scene);
			stage.setTitle("Tap Tap Revolution");
			stage.show();

		} catch (IOException e) {
			try {
				BorderPane screen = (BorderPane) loader.load();

				// Replace with new scene
				Scene scene = new Scene(screen);
				Stage stage = getPrimaryStage();
				stage.setScene(scene);
				stage.setTitle("Tap Tap Revolution");
				stage.show();

			} catch (IOException e2) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
