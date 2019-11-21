/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.arithmetic;

import framework.problem.State;

/**
 *
 * @author Mike Bros
 */
public class ArithmeticState implements State{

    public ArithmeticState(int i) {
        this.contents = i;
    }
    

    @Override
    public boolean equals(Object other) {
        if(other instanceof ArithmeticState){
            ArithmeticState otherState = (ArithmeticState) other;
            return otherState.contents == this.contents;
        }else{
            return false;
        }
        
    }

    @Override
    public String toString() {
        return "The value is: " + contents;
    }
    
    final int contents;
}
