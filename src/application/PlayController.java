package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import application.Main;
import application.Game;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlayController implements Initializable {
	public static ArrayList<ArrayList<String>> get_keyPressTimeStamps;
	private Stage stage;
	private Scene scene;
	private HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	private Game game;
	private Timeline tl;

	final int initialRadius = 15;
    final int haloRadius = 25;
	private static ArrayList<ArrayList<String>> keyPressTimeStamps;

	@FXML
	private Button quitButton;

	@FXML
	private Text scoreLabel;

	@FXML
	private BorderPane playScreenBorderPane;

	@FXML
	private AnchorPane gameAnchorPane;

	@FXML
	public void handleOnMouseEntered(MouseEvent event) {
      // Display circles on screen

		game.playSong();
		tl.play();
	}

	@FXML
	public void handleOnMouseExited(MouseEvent event) {
      tl.pause();
      game.pauseSong();
	}

	@FXML
	public void handleOnKeyPressed(KeyEvent ke) {
		System.out.println(ke.toString());
		if(ke.getCode()== KeyCode.D){
            game.setHaloRadius(0, haloRadius);
            System.out.println("c1: "+tl.getCurrentTime());
            game.updateKeyPressTimeStamp(0, tl.getCurrentTime());
        }
        if(ke.getCode()== KeyCode.F){
        	game.setHaloRadius(1, haloRadius);
            System.out.println("c2: "+tl.getCurrentTime());
            game.updateKeyPressTimeStamp(1, tl.getCurrentTime());
        }
        if(ke.getCode()== KeyCode.J){
        	game.setHaloRadius(2, haloRadius);
            System.out.println("c3: "+tl.getCurrentTime());
            game.updateKeyPressTimeStamp(2, tl.getCurrentTime());
        }
        if(ke.getCode()== KeyCode.K){
        	game.setHaloRadius(3, haloRadius);
            System.out.println("c4: "+tl.getCurrentTime());
            game.updateKeyPressTimeStamp(3, tl.getCurrentTime());
        }
		//int score = game.scoreGame();
		//scoreLabel.setText(String.valueOf(score));
	}

	@FXML
	public void handleOnKeyReleased(KeyEvent ke) {
		if(ke.getCode()== KeyCode.D){
        	game.setHaloRadius(0, initialRadius);
        }
        if(ke.getCode()== KeyCode.F){
        	game.setHaloRadius(1, initialRadius);
        }
        if(ke.getCode()== KeyCode.J){
        	game.setHaloRadius(2, initialRadius);
        }
        if(ke.getCode()== KeyCode.K){
        	game.setHaloRadius(3, initialRadius);
        }

	}

	@Override	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL location, ResourceBundle resources) {
		assert quitButton != null : "fx:id=\"pauseButton\" was not injected: check your FXML file 'play_screen.fxml'.";

		quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	quitGame(quitButton);
            }
        });

		game = new Game(NewGameController.get_song());
		game.initSongData();
		game.initAudio();
		tl = game.initUI();
		for (Circle c : game.getCircles()) {
			playScreenBorderPane.getChildren().addAll(c);
//			gameAnchorPane.getChildren().addAll(c);
		}
		for(Circle h: game.getHalos()){
			//playScreenBorderPane.getChildren().addAll(h);
			gameAnchorPane.getChildren().addAll(h);
		}

		//int final_score = Game.scoreGame();
		//System.out.println("Final score:" + final_score);
	}

	/** Pause game
	 * @return
	 */
	public void pauseGame() {
        tl.pause();
        game.pauseSong();
	}

	/** Pause game from Pause button
	 * @return
	 */
	public void quitGame(Button pauseButton) {
		FXMLLoader pause_loader = new FXMLLoader(Main.class.getResource("/view/pause_screen.fxml"));
		Main.switchScreen(pause_loader, pauseButton);
	}

	/** Resume game
	 * @return
	 */
	public void playGame() {
		tl.play();
        game.playSong();
	}

	/** Returns the main stage
	 * @return
	 */
	public Stage getCurrentStage(Button button) {
		stage = (Stage) button.getScene().getWindow();
		return stage;
	}

	public static ArrayList<ArrayList<String>> get_keyPressTimeStamps() {
		return keyPressTimeStamps;
	}

}