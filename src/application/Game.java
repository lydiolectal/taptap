package application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game {
	private String song;
	private String song_file;
	private HashMap<String, String> song_list = new HashMap<String, String>();
	private HashMap<String, String> song_file_list = new HashMap<String, String>();

	// From TapTapAnimation vars
	private static  ArrayList<ArrayList<String>> keyPressTimeStamps = new ArrayList<>();
    private static  ArrayList<ArrayList<String>> trackDict = new ArrayList<>(4);

    // Change beatFile, should be coming from NewGameController
    private String beatFile;

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

	/* Creates an arrayList of beats for each of the four different keys by
	 * loading and parsing through a song textfile.
	 *
	 * @return ArrayList<ArrayList<String>>
	 */
	private ArrayList<ArrayList<String>> createTrackList() {
		set_beat_file();
        //initialize empty 2d arraylist
        for (int i = 0; i < 4; i++) {
            ArrayList<String> dictElement = new ArrayList<>();
            trackDict.add(dictElement);
        }

        String line;
        String[] beatPair;
        String timeSt;
        String[] col;
        //avoid file not found exception
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
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return trackDict;
    }

	private void set_beat_file() {
		String[] addr = song_file.split("\\.");
		beatFile = addr[0]+".txt";
	}

	private void playSong(String song_name) {
		String song_file = song_list.get(song_name);
		Media sound = new Media(new File(song_file).toURI().toString());
		//Media sound = new Media(song_file);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	private static int scoreGame() {
        ArrayList<String> user_beats, orig_beats;
        int score = 0;
        int smallest_lst_size = 0;
        int orig_curr_beat, orig_next_beat, user_curr_beat, user_next_beat;
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
                user_curr_beat = Math.round(Float.parseFloat(user_beats.get(j)));
                // Check to make sure user keypress within 400ms of actual correct beat
                if (Math.abs(user_curr_beat - orig_curr_beat) <= 400) {
                    score += 10;
                }
            }
        }
        return score;
    }
}
