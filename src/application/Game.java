package application;

import javafx.animation.*;
import javafx.scene.effect.Lighting;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.*;

public class Game {
	private String song;
	private String song_file;

	// From TapTapAnimation vars
	private static  ArrayList<ArrayList<String>> keyPressTimeStamps = new ArrayList<>();
    private static  ArrayList<ArrayList<String>> trackDict = new ArrayList<>(4);
    private String beatFile;

    // Window settings
    final int winLength = 400;
    private int[] colPosition = {199, 281, 361, 442};

    // Circle settings
    private Circle c1;
    private Circle c2;
    private Circle c3;
    private Circle c4;
    private ObservableList<Circle> circles = FXCollections.observableArrayList();
    private ObservableList<Circle> halos = FXCollections.observableArrayList();
    final double opacity = 0.18;
    final int initialRadius = 15;
    private Paint[] color = {Color.rgb(2, 152, 211), Color.rgb(212, 14, 82), Color.rgb(25, 188, 0), Color.rgb(252, 224, 20)};
    private Paint[] haloColor = {Color.rgb(2, 152, 211, opacity), Color.rgb(212, 14, 82, opacity), Color.rgb(25, 188, 0, opacity), Color.rgb(252, 224, 20, opacity)};

    // Animation settings
    private int delayTime = 0;

    // Media settings
    protected MediaPlayer mediaPlayer;


	public Game(String song_title) {
		song = song_title;
		song_file = NewGameController.get_song_file();
	}

	protected void initSongData() {
		// Initialize empty user_keypress file
        for (int i = 0; i < 4; i++) {
            ArrayList<String> keyPressElement = new ArrayList<>();
            keyPressTimeStamps.add(keyPressElement);
        }

        // Load song beat array
        trackDict = createTrackList();
        for (int i = 0; i < 4; i++) {
            System.out.println(i + " is " + trackDict.get(i));
        }
	}

	protected Timeline initUI() {
		createHaloCircles();
        ArrayList<ArrayList<String>> dict = new ArrayList<>();
        Timeline tl = new Timeline(new KeyFrame(Duration.ZERO));
        ObservableList<KeyFrame> keyframes = FXCollections.observableArrayList();

        // Create beat data arrayList based on filename
        dict = createTrackList();

        //iterate though dictionary for beat time and column numbers
        for (int i = 0; i < 4; i++) {
            for(String ts: dict.get(i)){
                int timeStamp = Integer.parseInt(ts);
                Circle c = createCircle(i);
                circles.add(c);
                System.out.println("timeStamp:" + (timeStamp - 900 + delayTime));
                // Create keyFrame for each musical note
                //TODO: currently hard code the travelTime for each note. travelTime should be smaller that the first timestamp.
                keyframes.add(new KeyFrame(new Duration(timeStamp-900+delayTime),
                        new KeyValue(c.translateYProperty(), 0, Interpolator.EASE_IN)));

                keyframes.add(new KeyFrame(new Duration(delayTime + timeStamp),
                        new KeyValue(c.translateYProperty(), winLength-initialRadius*2, Interpolator.EASE_IN)));

                keyframes.add(new KeyFrame(new Duration(timeStamp+150), new KeyValue(c.translateYProperty(),winLength+initialRadius,Interpolator.LINEAR)));
                //TODO:figure out how to start the circles at different time so that the speed of the circles are constant.

            }
        }

        // Add all keyFrames to timeline
        for (KeyFrame kf : keyframes) {
            tl.getKeyFrames().add(kf);
        }

        return tl;
    }

	/* Creates an arrayList of beats for each of the four different keys by
	 * loading and parsing through a song text file.
	 *
	 * @return ArrayList<ArrayList<String>>
	 */
	private ArrayList<ArrayList<String>> createTrackList() {
		set_beat_file();
        // Initialize empty 2d arrayList
        for (int i = 0; i < 4; i++) {
            ArrayList<String> dictElement = new ArrayList<>();
            trackDict.add(dictElement);
        }

        String line;
        String[] beatPair;
        String timeSt;
        String[] col;

        // Avoid file not found exception
        try {
            Scanner sc = new Scanner(new FileReader(beatFile));
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.replaceAll("\\s+", "");
                beatPair = line.split("\\|");
                timeSt = beatPair[0];
                col = beatPair[1].split(",");

                for(String nCol : col){
                    trackDict.get(Integer.parseInt(nCol)-1).add(timeSt);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return trackDict;
    }

	private void set_beat_file() {
		String[] addr = song_file.split("\\.");
		beatFile = addr[0]+".txt";
		System.out.println("beatFile=" + beatFile);
	}

	private Circle createCircle(int col) {
        Circle c = new Circle(colPosition[col], 0, initialRadius);
        c.setEffect(new Lighting());
        c.setFill(color[col]);
        return c;
    }

	protected ObservableList<Circle> getCircles() {
	    return circles;
	}

	protected ObservableList<Circle> getHalos() {
	    return halos;
	}

	private void createHaloCircles(){
        c1 = new Circle(colPosition[0],winLength-initialRadius*5.7,initialRadius,haloColor[0]);
        c2 = new Circle(colPosition[1],winLength-initialRadius*5.7,initialRadius,haloColor[1]);
        c3 = new Circle(colPosition[2],winLength-initialRadius*5.7,initialRadius,haloColor[2]);
        c4 = new Circle(colPosition[3],winLength-initialRadius*5.7,initialRadius,haloColor[3]);
        halos.add(c1);
        halos.add(c2);
        halos.add(c3);
        halos.add(c4);
    }

	protected void setHaloRadius(int index, int radius) {
		switch(index) {
		case(0):
			c1.setRadius(radius);
            break;
		case(1):
			c2.setRadius(radius);
            break;
		case(2):
			c3.setRadius(radius);
            break;
		case(3):
			c4.setRadius(radius);
            break;
		}
	}

	protected void initAudio() {
		Media sound = new Media(new File(song_file).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
	}

	protected void playSong() {
		mediaPlayer.play();
	}

	protected void pauseSong() {
		mediaPlayer.pause();
	}

	protected static int scoreGame() {
        ArrayList<String> user_beats, orig_beats;
        int score = 0;
        int smallest_lst_size = 0;
        int orig_curr_beat, user_curr_beat;

        // Grab timeStamp list for each of the four keys
        for (int i = 0; i < 4; i++) {

            System.out.println("Size of trackDict[" + i + "]:" + trackDict.get(i).size());
            System.out.println("Size of keyPress[" + i + "]:" + keyPressTimeStamps.get(i).size());

            user_beats = keyPressTimeStamps.get(i);
            orig_beats = trackDict.get(i);
            // If user pressed the key LESS than actual key beats
            if (orig_beats.size() >= user_beats.size()) {
                smallest_lst_size = user_beats.size();
            } else {
                smallest_lst_size = orig_beats.size();
            }

            for (int j = 0; j <= smallest_lst_size - 1; j++) {
                orig_curr_beat = Math.round(Float.parseFloat(orig_beats.get(j)));
                user_curr_beat = Math.round(Float.parseFloat(user_beats.get(j).replace(" ms", "")));
                // Check to make sure user keypress within 400ms of actual correct beat
                if (Math.abs(user_curr_beat - orig_curr_beat) <= 400) {
                    score += 10;
                }
            }
        }
        return score;
    }

	public void updateKeyPressTimeStamp(int i, Duration currentTime) {
		keyPressTimeStamps.get(i).add(currentTime.toString());
	}
}
