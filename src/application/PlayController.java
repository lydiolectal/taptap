package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import application.Main;
import application.Game;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlayController implements Initializable {
	public static ArrayList<ArrayList<String>> get_keyPressTimeStamps;
	private Game game;
	private Timeline tl;

	final int initialRadius = 15;
    final int haloRadius = 25;

	/* Inject Play FXML buttons */
	@FXML
	private Button quitButton;

	@FXML
	private Text scoreLabel;

	@FXML
	private BorderPane playScreenBorderPane;

	@FXML
	private AnchorPane gameAnchorPane;

	/* Inject action events from FXML panes */
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
		int score = game.scoreGame();
		scoreLabel.setText("Score: " + String.valueOf(score) + " points");
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

	/** This method is called by the FXMLLoader on startup
	 * and sets the actions of the pause button in the play screen controller
	 * @return
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert quitButton != null : "fx:id=\"pauseButton\" was not injected: check your FXML file 'play_screen.fxml'.";

		quitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	quitGame(quitButton);
            }
        });

		// Begin game
		game = new Game(NewGameController.get_song());
		game.initSongData();
		game.initAudio();
		tl = game.initUI();

		// Add circle animations to scene pane
		for(Circle c : game.getCircles()) {
			playScreenBorderPane.getChildren().addAll(c);
		}
		for(Circle h: game.getHalos()){
			gameAnchorPane.getChildren().addAll(h);
		}

		tl.setOnFinished(event -> quitGame(quitButton));
	}

	/** Pause game when mouse moves outside game window
	 * @return
	 */
	public void pauseGame() {
        tl.pause();
        game.pauseSong();
	}

	/** Quit game
	 * @return
	 */
	public void quitGame(Button quitButton) {
		FXMLLoader pause_loader = new FXMLLoader(Main.class.getResource("/view/pause_screen.fxml"));
		Main.switchScreen(pause_loader, quitButton);
	}

	/** Resume game when mouse moves inside game window
	 * @return
	 */
	public void playGame() {
		tl.play();
        game.playSong();
	}
}