
import calculator.SimpleCalculator;

/**
 * This class extends the SimpleCalculator class to implement a simple 
 * shift-reduce calculator without precedence for expressions.
 * This calculator can evaluate expressions of the form NUM [OP NUM]*.
 * @author Mike Bros
 */
public class NoPrecedenceCalculator extends SimpleCalculator {
    
    public NoPrecedenceCalculator(String title) {
        super(title);
    }
    
    public NoPrecedenceCalculator() {
        this("Calculator Without Operator Precedence");
    }
    
    @Override
    public double evaluate() {
	setState(State.START);
        while (true) {
            switch (getState()) {
                case START: 
                    start(); 
                    break;
                case NUMBER1:
                    number1(); 
                    break;
                case OPERATOR:
                    operator(); 
                    break;
                case NUMBER2:
                    number2(); 
                    break;
                case END:
                    end();
                    return (Double)getStack().pop();
                default: 
                    throw new Error("Something is wrong in SimpleCalculator.evaluate"); // shouldn't happen
            }
        } 
    }
    
    private void start() {
        getDispenser().advance();
        if (!getDispenser().tokenIsNumber()) {
            syntaxError(NUM);
        }
        setState(State.NUMBER);
    }
    
}