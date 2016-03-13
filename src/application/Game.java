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
	private String song;
	private String song_file;
	private HashMap<String, String> song_list = new HashMap<String, String>();

	public Game(String song_title) {
		song = song_title;
		song_file = null;
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
