package framework.ui;

import framework.problem.Problem;
import framework.solution.SolvingAssistant;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Mike
 */
public class ProblemGUI extends VBox{
    private final Problem problem;
    private final double width;
    private final double height;
    
    private SolvingAssistant solver;
    
    private Label welcomeMessage;
    private Label introduction;
    private Label status;
    private VBox currentState;
    private VBox moves;
    private VBox finalState;
    private Button reset;
    
    /**
     * Creates a ProblemGUI with given problem, width, and height
     * @param problem problem to make window for
     * @param width the window width
     * @param height the window height
     */
    public ProblemGUI(Problem problem, double width, double height) {
        this.problem = problem;
        this.width = width;
        this.height = height;
        this.setSpacing(15);
        solver = new SolvingAssistant(problem);
        setWindowProperties();
        status = new Label(" ");
        
        updateDisplay();
    }
    
    // Private helper methods follow
    
    private void updateDisplay(){
        super.getChildren().clear();
        setReset();
        setWelcome();
        setIntro();
        
        currentState = setState("Current State:\n",true);
        finalState = setState("  Final State:  \n",false);
        moves = setMoves();
        
        HBox row3 = new HBox();
        row3.getChildren().addAll(currentState,moves,finalState);
        row3.setSpacing(20);
        row3.setAlignment(Pos.CENTER);
        
        super.getChildren().addAll(welcomeMessage,
                                introduction,
                                row3,
                                status,
                                reset);
      
    }
    
    private void setWindowProperties(){
        this.setPrefSize(width, height);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20, 20, 20, 20));
    }


    private void setWelcome() {
        welcomeMessage = new Label("Welcome to the "+this.problem.getName()+" Problem");
        welcomeMessage.setFont(Font.font("Times New Roman",FontWeight.EXTRA_BOLD, 35));
        welcomeMessage.setAlignment(Pos.CENTER);
        welcomeMessage.setWrapText(true);
    }

    private void setIntro() {
        introduction = new Label(this.problem.getIntroduction());
        introduction.setFont(Font.font("Times New Roman",FontWeight.NORMAL, 15));
        introduction.setAlignment(Pos.CENTER);
        introduction.setWrapText(true);
    }
    
    private VBox setState(String header,Boolean curr){
        Label head = new Label(header);
        head.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
        head.setAlignment(Pos.CENTER);
        
        VBox whole = new VBox();
        
        VBox box = setStateHelper(curr);
        
        whole.getChildren().addAll(head,box);
        
        return whole;
    }

    private VBox setStateHelper(Boolean curr) {
        Label state;
        if(curr == true){
        state = new Label(this.problem.getCurrentState().toString());
        }else{
        state = new Label(this.problem.getFinalState().toString());
        }
        state.setFont(Font.font("Courier New",FontWeight.SEMI_BOLD, 20));
        state.setAlignment(Pos.CENTER);
        
        VBox box = new VBox();
        box.getChildren().add(state);
        Color black = Color.BLACK;
        box.setBorder(new Border(new BorderStroke(black,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(3))));
        box.setAlignment(Pos.CENTER);
        
        return box;
    }

    private VBox setMoves() {
        Label head = new Label("Moves (" + solver.getMoveCount() + " so far):\n");
        head.setFont(Font.font("Times New Roman",FontWeight.BOLD, 30));
        head.setAlignment(Pos.CENTER);
        
        VBox moves = new VBox();
        VBox buttons = setButtons();
        
        moves.getChildren().addAll(head,buttons);
        
        return moves;
    }
    
    private VBox setButtons(){
        List<String> moveList = this.problem.getMover().getMoveNames();
        
        VBox buttonsBox = new VBox();
        
        for(int i=0;i<moveList.size();i++){
            Button newButton = new Button(moveList.get(i));
            newButton.setAlignment(Pos.CENTER);
            newButton.setOnAction(e -> {solver.tryMove(newButton.getText())
                    ;doMove();});
            buttonsBox.getChildren().add(newButton);
        }
        
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(8);
        
        return buttonsBox;
    }

    private void doMove() {
        updateDisplay();
        setStatus();
    }

    private void setStatus() {
        if(!solver.isMoveLegal()&&solver.getMoveCount()!=0){
            status = new Label("Illegal move. Try again.");
        }else if(solver.isProblemSolved()){
            status = new Label("You solved the problem. Congratulations.");
        }else{
            status = new Label(" ");
        }
        status.setFont(Font.font("Times New Roman",FontWeight.BOLD, 20));
        status.setTextFill(Color.RED);
        status.setAlignment(Pos.CENTER);
        
    }
    
    private void setReset(){
        reset = new Button("Reset");
        reset.setOnAction(e -> {reset();});
        reset.setAlignment(Pos.CENTER);
    }
    
    private void reset(){
        solver.reset();
        updateDisplay();
    }
}