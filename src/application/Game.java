package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game {
	private String difficulty;
	private String song;
	private String song_file;
	private HashMap<String, String> song_list = new HashMap<String, String>();

	public Game(String difficulty_lvl, String song_title) {
		difficulty = difficulty_lvl;
		song = song_title;
		song_file = null;
	}

	/**
	 * Creates a hashmap of song files as values
	 * @return
	 */
	protected void createSongMap() {
		song_list.put("Seven Nation Army - The White Stripes", "seven_nation_army.mp3");
		song_list.put("It's Time - Imagine Dragons", "its_time.mp3");
		getSongMap();
		//playSong(song_list.get("Seven Nation Army - The White Stripes"));
	}

	/**
	 * Prints hashmap of song files as values
	 * @return
	 */
	private void getSongMap() {
		Set set = song_list.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			HashMap.Entry mentry = (HashMap.Entry)iterator.next();
			System.out.print("Key is: "+ mentry.getKey() + " & Value is: ");
			System.out.println(mentry.getValue());
		}
	}

	private void playSong(String song_name) {
		String song_file = song_list.get(song_name);
		Media sound = new Media(new File(song_file).toURI().toString());
		//Media sound = new Media(song_file);
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	private void scoreGame(ArrayList<ArrayList<String>> trackBeats, ArrayList<ArrayList<String>> userPerformance) {
		Set set = ((Map<String, String>) trackBeats).entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			HashMap.Entry mentry = (HashMap.Entry)iterator.next();
			System.out.print("Key is: "+ mentry.getKey() + " & Value is: ");
			System.out.println(mentry.getValue());
		}
	}
}
