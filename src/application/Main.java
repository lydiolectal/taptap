package application;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	private static Stage primaryStage;
	public static HashMap<String, Scene> screens = new HashMap<String, Scene>();

	@Override
	public void start(Stage primaryStage) {
		try {
			// Load startup screen
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
			AnchorPane screen = (AnchorPane) loader.load();

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

	/** Switches between scenes by loading FXML files on the main stage
	 * @return
	 */
	public static void switchScreen(FXMLLoader loader, Button button) {
		// Grab stage
		Window window = button.getScene().getWindow();
		Stage stage = (Stage) window;

		try {
			// Replace with new Pane scene on current stage
			Pane screen = (Pane) loader.load();
			Scene scene = new Scene(screen);
			stage.setScene(scene);
			stage.setTitle("Tap Tap Revolution");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
