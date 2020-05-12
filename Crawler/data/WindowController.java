2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_field/WindowController.java
package flower_field;

import flower_logic.Breeder;
import flower_logic.Field;
import flower_logic.Flower;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Andrea Riboni
 */
public class WindowController implements Initializable {

    @FXML
    private Canvas field;
    @FXML
    private TextArea days;
    private Field flowers;
    final int FLOWERS_START = 3;
    private int DAY_TIME = 100; //milliseconds of a day

    /**
     * Initializes the controller class.
     *
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Field initialization
        flowers = new Field();
        for (int i = 0; i < FLOWERS_START; i++) {
            System.out.println(flowers.plantRandom());
        }

        //Window initialization
        addMouseListener(field); //not yet used

        //GameLoop initialization
        loop();
    }

    //game-loop logic
    private void loop() {
        GraphicsContext gc = field.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();
        //Game-Loop start
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                //background
                gc.clearRect(0, 0, field.getWidth(), field.getHeight());
                //flowers
                flowers.getFlowers().forEach((f) -> {
                    Drawer2D.drawFlower(gc, f);
                });
            }
        }.start();

        //Days passing
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    Breeder.breed(flowers);
                    //increment the textarea days
                    int days_count = Integer.parseInt(days.getText());
                    days.setText(++days_count + "");
                }
        }, 0, DAY_TIME);
    }

    private void addMouseListener(Canvas c) {
        c.setOnMouseClicked((MouseEvent event) -> {
            System.out.println(event.getScreenX());
            System.out.println(event.getScreenY());
        });
    }

    //add a random flower
    @FXML
    private void plantRandom(ActionEvent event) {
        flowers.plantRandom();
    }
}
