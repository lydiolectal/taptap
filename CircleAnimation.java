import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import java.util.Random;
import javafx.animation.Animation;




public class CircleAnimation extends Application {
    private ObservableList<Circle> circles = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        Pane root = new Pane();

        for(int i = 0; i<5; i++){
            circles.add(createCircle());
        }

        Timeline tl = new Timeline();

        tl.setCycleCount(3);
        tl.setAutoReverse(true);

        for (int i = 0; i<5; i++){
            tl.getKeyFrames().addAll(
                    new KeyFrame(new Duration(2000+2000*i), // set start position at 0
                            new KeyValue(circles.get(i).translateYProperty(), 300)));

        }

        tl.play();
        for(Circle c: circles){
            root.getChildren().addAll(c);
        }


        Scene scene = new Scene(root, 350, 250);

        stage.setTitle("Timeline");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Circle createCircle(){
        Paint[] color = {Color.RED,Color.CADETBLUE, Color.YELLOW, Color.GREEN};
        Random rand = new Random();
        int col = rand.nextInt(4);
        System.out.println(col);
        Circle c = new Circle((col+1)*40, 20, 20);
        c.setEffect(new Lighting());
        c.setFill(color[col]);
        return c;

    }
}