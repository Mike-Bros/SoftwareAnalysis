/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import framework.problem.Problem;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Mike
 */
public class ProblemGUI extends VBox{
    private final Problem problem;
    private final double width;
    private final double height;
    
    private Label welcomeMessage;
    
    public ProblemGUI(Problem problem, double width, double height) {
        this.problem = problem;
        this.width = width;
        this.height = height;
        
        setWelcomeMessage();
        
        
        
        
        super.getChildren().addAll(welcomeMessage);
    }

    private void setWelcomeMessage() {
        welcomeMessage = new Label("Welcome to the " + this.problem.getName() + "Problem");
    }

    
    
}
