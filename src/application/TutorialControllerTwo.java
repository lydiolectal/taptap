package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import application.Main;

public class TutorialControllerTwo implements Initializable {

    /* Page 2 Tutorial buttons */
    @FXML
    private Button backButton;

    @FXML
    private Button newGameButton;

    /** This method is called by the FXMLLoader on startup
     * and sets the actions of the two buttons in the second tutorial controller
     * @return
     */
    @Override
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

    /** Switches from the second tutorial screen to the first
     * @return
     */
    protected void loadPrevTutorial(Button button) {
        FXMLLoader prev_tut_loader = new FXMLLoader(Main.class.getResource("/view/tutorial_first_screen.fxml"));
        Main.switchScreen(prev_tut_loader, button);
    }

    /** Switches from the second tutorial screen to the new game screen
     * @return
     */
    protected void loadNewGame(Button button) {
        FXMLLoader new_game_loader = new FXMLLoader(Main.class.getResource("/view/new_game_screen.fxml"));
        Main.switchScreen(new_game_loader, button);
    }
}
