import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;
import java.util.*;


public class CircleAnimation extends Application {
    private ObservableList<Circle> circles = FXCollections.observableArrayList();
    private int[] colPosition = {80,160,240,320};
    private Paint[] color = {Color.RED,Color.CADETBLUE, Color.YELLOW, Color.GREEN};
    private int delayTime = 3000;
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 350, 250);
        stage.setTitle("Random");
        stage.setScene(scene);
        Timeline tl;
        tl = initUI("src/l.txt");
        for(Circle c: circles){
            root.getChildren().addAll(c);
        }
        //this is an example of pause timeline when hover the mouse over the close screen button
        //change root to the pause button and we should be able to pause when pause button's clicked
        root.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                tl.play();
            }
        });
        root.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                tl.pause();
            }
        });
//        tl.play();
        stage.show();
    }

    private Map createBeatDictionary(String filename) {
        Map<String, String[]> dict = new HashMap<>();
        String line;
        String[] beatPair;
        String timeSt;
        String[] col;
        //avoid file not found exception
        try {
            Scanner sc = new Scanner(new FileReader(filename));
            while (sc.hasNextLine()){
                line = sc.nextLine();
                line = line.replaceAll("\\s+","");
                beatPair = line.split("\\|");
                timeSt = beatPair[0];
                col = beatPair[1].split(",");
                dict.put(timeSt,col);
            }
            System.out.println(dict.size());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return dict;
    }
    private Timeline initUI(String songFile) {
        Map<String, String[]> dict;
        String timeSt;
        String[] col;
//        Timeline tl = new Timeline();
      Timeline tl = new Timeline(new KeyFrame(Duration.ZERO));
        ObservableList<KeyFrame> keyframes = FXCollections.observableArrayList();
        //create beat data dictionary based on filename
        dict = createBeatDictionary(songFile);
        //iterate though dictionary for beat time and column numbers
        Iterator<Map.Entry<String, String[]>> it = dict.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> pair = it.next();
            timeSt = pair.getKey();
            col =pair.getValue();
            int timeStamp = Integer.parseInt(timeSt);
            //for each beat, iterate through each column to create musical notes
            for(String nCol:col){
                Circle c = createCircle(nCol);
                circles.add(c);
                System.out.print(c.toString());
                System.out.print(timeStamp);
                //create a keyframe for each musical note
                keyframes.add(new KeyFrame(new Duration(delayTime+timeStamp), // set start position at 0
                        new KeyValue(c.translateYProperty(), 320, Interpolator.EASE_IN)));
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

        //add all keyframes to timeline
        for (KeyFrame kf: keyframes){
            tl.getKeyFrames().add(kf);
        }

        return tl;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Circle createCircle(String col){
        int nColumn = Integer.parseInt(col);
        System.out.println(nColumn);
        Circle c = new Circle(colPosition[nColumn-1], 0, 7);
        c.setEffect(new Lighting());
        c.setFill(color[nColumn-1]);
        return c;

    }
}