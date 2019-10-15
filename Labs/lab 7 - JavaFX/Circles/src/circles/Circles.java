package circles;

import java.util.stream.Stream;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A lab exercise to introduce Java 8 lambdas and streams.
 * @author Michael Bros
 */
public class Circles extends Application {
    
    public static final int ROWS = 4;
    public static final int COLS = 5;
    public static final int CELL_SIZE = 100;
    
    private int row = 0;
    private int col = 0;
    
    private HBox root;
    private Pane canvas;
    private Button starter;
    private Spinner rowSpinner,columnSpinner,xScale,yScale;
    private Slider cellSize;
    
    @Override
    public void start(Stage primaryStage) {
        root = new HBox();
        canvas = new Pane();
        starter = new Button("Circles");
        
        rowSpinner = new Spinner(1,5,4);
        columnSpinner = new Spinner(1,5,5);
        
        
        
        xScale = new Spinner(-3,3,0);
        yScale = new Spinner(-3,3,0);
        
        
        root.setAlignment(Pos.CENTER);
        canvas.setPrefSize(COLS * CELL_SIZE, ROWS * CELL_SIZE);
        
        addButtonHandler();
        
        root.getChildren().addAll(canvas, rowSpinner,columnSpinner,xScale,yScale);
       
        
        primaryStage.setTitle("Java 8 Lab Exercise");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        //test makeRow
        //makeRow().forEach(x -> System.out.println(x));
        
        //test makeAllRows
        //makeAllRows().forEach(r -> r.forEach(x -> System.out.println(x)));
    }
    
    /**
     * This method adds the handler to the button that gives
     * this application its behavior.
     */
    private void addButtonHandler() {
        starter.setOnAction(e -> { canvas.getChildren().clear(); 
           addAllRowsToCanvas(makeAllRows()); });
    }
    
    /**
     * @return stream which contains a row of circles
     */
    private Stream<Circle> makeRow(){
        return Stream.generate(() -> makeCircle()).limit(COLS);
        
    }
    
     /**
     * @param stream that contains all the circles in a row
     */
    private void addRowToCanvas(Stream<Circle> stream){
        col = 0;
        stream.forEach(c -> {addToCanvas(c); col++;});
    }
    
    /**
     * @param cirlce to add to the canvas
     */
    private void addToCanvas(Circle circle){
        double toX = CELL_SIZE/2 + row*CELL_SIZE;
        double toY = CELL_SIZE/2 + col*CELL_SIZE;
        double fromX = CELL_SIZE/2 + (ROWS-1)*CELL_SIZE;
        double fromY = CELL_SIZE/2 + (COLS-1)*CELL_SIZE;
        Color color = new Color(Math.random(),Math.random(),Math.random(),1);
        Circle newCircle = (Circle) circle;
        
        newCircle.setCenterX(fromY);
        newCircle.setCenterY(fromX);
        newCircle.setFill(color);
        canvas.getChildren().add(newCircle);
        
        TranslateTransition tt = new TranslateTransition(Duration.millis(500));
        tt.setNode(circle);
        tt.setByX(toY-fromY);
        tt.setByY(toX-fromX);
        tt.play();
        
        ScaleTransition st= new ScaleTransition(Duration.millis(300+Math.random()*800));
        st.setNode(circle);
        st.setByX(1.5);
        st.setByY(1.5);
        st.setCycleCount(Animation.INDEFINITE);
        st.setAutoReverse(true);
        st.play();
    }
    
    private Circle makeCircle(){
        Circle circle = new Circle();
        circle.setRadius(CELL_SIZE/4);
        return circle;
    }
    

    /**
     * @return stream which contains a stream of circles for each row
     */
    private Stream<Stream<Circle>> makeAllRows(){
        return Stream.generate(() -> makeRow()).limit(ROWS);
    
    }
    
    private void addAllRowsToCanvas(Stream<Stream<Circle>> stream){
        row = 0;
        stream.forEach( r -> {addRowToCanvas(r); row++;});
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}


