package application;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
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
	public static HashMap<String, Scene> screens = new HashMap<String, Scene>();

	@Override
	public void start(Stage primaryStage) {
		try {
			// Load game screen
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

	public static void switchScreen(FXMLLoader loader, Button button) {
		// Grab stage
		Window window = button.getScene().getWindow();
		Stage stage = (Stage) window;

		try {
			// Replace with new AnchorPane scene on current stage
			Pane screen = (Pane) loader.load();
			Scene scene = new Scene(screen);

			System.out.println("BEFORE");
			screens.put("GameScreen", scene);
			Set set = screens.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()) {
				HashMap.Entry mentry = (HashMap.Entry)iterator.next();
				System.out.print("SCENE: "+ mentry.getKey() + " & VALUE is: ");
				System.out.println(mentry.getValue());
			}
			System.out.println("AFTER");

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
