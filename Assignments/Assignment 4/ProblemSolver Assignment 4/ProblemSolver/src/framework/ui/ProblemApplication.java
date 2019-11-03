package framework.ui;

import domains.arithmetic.ArithmeticProblem;
import domains.dummy.DummyProblem;
import domains.farmer.FarmerProblem;
import domains.puzzle.PuzzleProblem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;



/**
 * This class presents problem solving domains in a tabbed pane.
 */
public class ProblemApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        
	/* Add tabs using the following */

	Tab tab = new Tab();
        tab.setText("Dummy");
        tab.setContent(new ProblemGUI(new DummyProblem(), 900, 750));
        tabPane.getTabs().add(tab);
        
        Tab tab2 = new Tab();
        tab2.setText("Arithmetic");
        tab2.setContent(new ProblemGUI(new ArithmeticProblem(), 900, 750));
        tabPane.getTabs().add(tab2);
        
        Tab tab3 = new Tab();
        tab3.setText("Farmer, Wolf, Goat, and Cabbage");
        tab3.setContent(new ProblemGUI(new FarmerProblem(), 900, 750));
        tabPane.getTabs().add(tab3);
        
        Tab tab4 = new Tab();
        tab4.setText("8-Puzzle");
        tab4.setContent(new ProblemGUI(new PuzzleProblem(), 900, 750));
        tabPane.getTabs().add(tab4);
        
        Scene scene = new Scene(tabPane);
        primaryStage.setTitle("Problem Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}