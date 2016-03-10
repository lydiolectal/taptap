package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import application.Main;

/**
 * Created by lydiading on 3/8/16.
 */
public class TutorialControllerTwo implements Initializable {

    /* Page 2 Tutorial buttons */
    @FXML
    private Button backButton;

    @FXML
    private Button newGameButton;

    @Override	// This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {

    	// All @FXML variables will have been injected
    	assert backButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'simple.fxml'.";
        assert newGameButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'simple.fxml'.";

        // Display previous tutorial page
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadPrevTutorial(backButton);
            }
        });

        // Begin new game screen
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	loadNewGame(newGameButton);
            }
        });
    }

    protected void loadPrevTutorial(Button button) {
        FXMLLoader prev_tut_loader = new FXMLLoader(Main.class.getResource("/view/tutorial_first_screen.fxml"));
        Main.switchScreen(prev_tut_loader, button);
    }

    protected void loadNewGame(Button button) {
        FXMLLoader new_game_loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
        Main.switchScreen(new_game_loader, button);
    }
}
