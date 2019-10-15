package circles;

import java.util.stream.Stream;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public static final int ROWS_MIN = 1;
    public static final int ROWS_MAX = 5;
    
    public static final int COLS = 5;
    public static final int COLS_MIN = 1;
    public static final int COLS_MAX = 5;
    
    public static final int CELL_SIZE = 100;
    public static final int CELL_SIZE_MIN = 50;
    public static final int CELL_SIZE_MAX = 150;
    
    private int row = 0;
    private int col = 0;
    
    private VBox root;
    private HBox controlGroup, labelGroup;
    private Pane canvas;
    private Button starter;
    private Spinner rowSpinner,columnSpinner,xScale,yScale;
    private Slider cellSize;
    private Label rowSpinnerL,columnSpinnerL,cellSizeL,cellSizeNum,xScaleL,yScaleL;
    
    @Override
    public void start(Stage primaryStage) {
        root = new VBox(5);
        controlGroup = new HBox(10);
        labelGroup = new HBox(60);
        canvas = new Pane();
        starter = new Button();
        
        rowSpinner = new Spinner(ROWS_MIN,ROWS_MAX,ROWS);
        rowSpinner.setPrefWidth(CELL_SIZE_MAX*COLS_MAX/12);
        rowSpinnerL = new Label("Rows");
        columnSpinner = new Spinner(COLS_MIN,COLS_MAX,COLS);
        columnSpinner.setPrefWidth(CELL_SIZE_MAX*COLS_MAX/12);
        columnSpinnerL = new Label("Columns");
        
        cellSize = new Slider(CELL_SIZE_MIN,CELL_SIZE_MAX,CELL_SIZE);
        cellSizeL = new Label("Cell Size");
        cellSizeNum = new Label(String.format("%.0f",cellSize.getValue()));
                
        xScale = new Spinner(-3,3,0);
        xScale.setPrefWidth(CELL_SIZE_MAX*COLS_MAX/12);
        xScaleL = new Label("X Scale");
        yScale = new Spinner(-3,3,0);
        yScale.setPrefWidth(CELL_SIZE_MAX*COLS_MAX/12);
        yScaleL = new Label("Y Scale");
        
        root.setAlignment(Pos.CENTER);
        labelGroup.setAlignment(Pos.CENTER);
        controlGroup.setAlignment(Pos.CENTER);
        canvas.setPrefSize(COLS_MAX * CELL_SIZE_MAX, ROWS_MAX * CELL_SIZE_MAX);
        
        addButtonHandler();
        
        
        
        labelGroup.getChildren().addAll(rowSpinnerL,columnSpinnerL,cellSizeL,xScaleL,yScaleL);
        controlGroup.getChildren().addAll(rowSpinner,columnSpinner,cellSize,cellSizeNum,xScale,yScale);
        
        root.getChildren().addAll(canvas, labelGroup, controlGroup);
       
        
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
        int col_value = (int) columnSpinner.getValue();
        return Stream.generate(() -> makeCircle()).limit(col_value);
        
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
        int cell_size = (int) cellSize.getValue();
        int row_value = (int) rowSpinner.getValue();
        int col_value = (int) columnSpinner.getValue();
        double toX = cell_size/2 + row*cell_size;
        double toY = cell_size/2 + col*cell_size;
        double fromX = cell_size/2 + (row_value-1)*cell_size;
        double fromY = cell_size/2 + (col_value-1)*cell_size;
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
        int cell_size = (int) cellSize.getValue();
        circle.setRadius(cell_size/4);
        return circle;
    }
    

    /**
     * @return stream which contains a stream of circles for each row
     */
    private Stream<Stream<Circle>> makeAllRows(){
        int x = (int) rowSpinner.getValue();
        return Stream.generate(() -> makeRow()).limit(x);
    
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


