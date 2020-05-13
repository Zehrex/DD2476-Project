2
https://raw.githubusercontent.com/AndreaRiboni/Garden/master/src/flower_field/Flower_Field.java
package flower_field;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class
 * @author Andrea Riboni
 */
public class Flower_Field extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Window.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Garden Simulation");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
