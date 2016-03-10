
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
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
import javafx.scene.input.KeyEvent;
public class TapTapAnimation extends Application {
    private ObservableList<Circle> circles = FXCollections.observableArrayList();
    private ObservableList<Circle> halos = FXCollections.observableArrayList();
    private int[] colPosition = {100, 200, 300, 400};
    private Paint[] color = {Color.rgb(2, 152, 211), Color.rgb(212, 14, 82), Color.rgb(25, 188, 0), Color.rgb(252, 224, 20)};
    final double opacity = 0.05;
    //length of window
    final int winLength = 250;
    private Paint[] haloColor = {Color.rgb(2, 152, 211, opacity), Color.rgb(212, 14, 82, opacity), Color.rgb(25, 188, 0, opacity), Color.rgb(252, 224, 20, opacity)};
    private Circle c1;
    private Circle c2;
    private Circle c3;
    private Circle c4;
    final int initialRadius = 7;
    final int haloRadius = 15;
    private int delayTime = 3000;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, winLength);
        stage.setTitle("Random");
        stage.setScene(scene);
        Timeline tl;
        //initialize halo circles
        createHaloCircles();
        tl = initUI("l.txt");
        for (Circle c : circles) {
            root.getChildren().addAll(c);
        }
        for(Circle c: halos){
            root.getChildren().addAll(c);
        }
        //this is an example of pause timeline when hover the mouse over the close screen button
        //change root to the pause button and we should be able to pause when pause button's clicked
        scene.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                tl.play();
            }
        });
        scene.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                tl.pause();
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(ke.getCode()== KeyCode.D){
                    c1.setRadius(haloRadius);
                }
                if(ke.getCode()== KeyCode.F){
                    c2.setRadius(haloRadius);
                }
                if(ke.getCode()== KeyCode.J){
                    c3.setRadius(haloRadius);
                }
                if(ke.getCode()== KeyCode.K){
                    c4.setRadius(haloRadius);
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(ke.getCode()== KeyCode.D){
                    c1.setRadius(initialRadius);
                }
                if(ke.getCode()== KeyCode.F){
                    c2.setRadius(initialRadius);
                }
                if(ke.getCode()== KeyCode.J){
                    c3.setRadius(initialRadius);
                }
                if(ke.getCode()== KeyCode.K){
                    c4.setRadius(initialRadius);
                }
            }
        });

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
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.replaceAll("\\s+", "");
                beatPair = line.split("\\|");
                timeSt = beatPair[0];
                col = beatPair[1].split(",");
                dict.put(timeSt, col);
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
            col = pair.getValue();
            int timeStamp = Integer.parseInt(timeSt);
            //for each beat, iterate through each column to create musical notes
            for (String nCol : col) {
                Circle c = createCircle(nCol);
                circles.add(c);
                System.out.print(c.toString());
                System.out.print(timeStamp);
                //create a keyframe for each musical note
                keyframes.add(new KeyFrame(new Duration(delayTime + timeStamp), // set start position at 0
                        new KeyValue(c.translateYProperty(), 320, Interpolator.EASE_IN)));
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

        //add all keyframes to timeline
        for (KeyFrame kf : keyframes) {
            tl.getKeyFrames().add(kf);
        }

        return tl;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Circle createCircle(String col) {
        int nColumn = Integer.parseInt(col);
        System.out.println(nColumn);
        Circle c = new Circle(colPosition[nColumn - 1], 0, initialRadius);
        c.setEffect(new Lighting());
        c.setFill(color[nColumn - 1]);
        return c;

    }
    private void createHaloCircles(){
        c1 = new Circle(colPosition[0],winLength-initialRadius*2,initialRadius,color[0]);
        c2 = new Circle(colPosition[1],winLength-initialRadius*2,initialRadius,color[1]);
        c3 = new Circle(colPosition[2],winLength-initialRadius*2,initialRadius,color[2]);
        c4 = new Circle(colPosition[3],winLength-initialRadius*2,initialRadius,color[3]);
        halos.add(c1);
        halos.add(c2);
        halos.add(c3);
        halos.add(c4);
    }
}

