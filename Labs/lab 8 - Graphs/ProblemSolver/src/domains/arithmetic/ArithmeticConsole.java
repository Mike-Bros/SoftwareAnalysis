package domains.arithmetic;

import framework.ui.ProblemConsole;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mike Bros
 */
public class ArithmeticConsole extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new ProblemConsole(new ArithmeticProblem(), 450, 550));
        primaryStage.setTitle("Testing Arithmetic Console");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}