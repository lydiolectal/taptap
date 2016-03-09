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

import application.Main;

/**
 * Created by lydiding on 3/8/16.
 */
public class TutorialControllerTwo implements Initializable {

    /* Page 2 Tutorial buttons */
    @FXML
    private Button nextButton2;

    @FXML
    private Button backButton2;

    private Main main;

    @Override	// This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        assert nextButton2 != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'simple.fxml'.";

        // All @FXML variables will have been injected
        nextButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadNewGame(nextButton2);
            }
        });

        // Switch to settings scene (via NewGameController) if skip button pressed
        backButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/tutorial1.fxml"));
        		Main.switchScreen(loader, backButton2);
            }

        });
    }

    public Stage getCurrentStage(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        return stage;
    }

    private void loadNewGame(Button button) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
        Main.switchScreen(loader, button);
    }
}
