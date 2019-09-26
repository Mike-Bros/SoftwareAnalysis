/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.arithmetic;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Mike
 */
public class ArithmeticMover extends Mover{
        
        public static final String ADD = "Add 3";
        public static final String SUBTRACT = "Subtract 5";
        public static final String DIVIDE = "Divide by 2";
        public static final String MULTIPLY = "Multiply by 2";
        
    ArithmeticMover() {
        super.addMove(ADD, s -> tryAdd(s));
        super.addMove(SUBTRACT, s -> trySubtract(s));
        super.addMove(DIVIDE, s -> tryDivide(s));
        super.addMove(MULTIPLY, s -> tryMultiply(s));
    }
    
    private State tryAdd(State state) {
        ArithmeticState otherState = (ArithmeticState) state;
        return new ArithmeticState(otherState.contents+3);
    }
    
    private State trySubtract(State state) {
        ArithmeticState otherState = (ArithmeticState) state;
        return new ArithmeticState(otherState.contents-5);
    }
    
    private State tryDivide(State state) {
        ArithmeticState otherState = (ArithmeticState) state;
        return new ArithmeticState(otherState.contents/2);
    }
    
    private State tryMultiply(State state) {
        ArithmeticState otherState = (ArithmeticState) state;
        return new ArithmeticState(otherState.contents*2);
    }
    
}
