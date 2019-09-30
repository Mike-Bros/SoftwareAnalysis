package calculator;

/**
 * @author Mike Bros
 */
public class ParenthesisCalculator extends PrecedenceCalculator {
    
    public ParenthesisCalculator(String title) {
        super(title);
    }
    
    public ParenthesisCalculator() {
        this("Calculator With Operator Precedence and Parentheses");
    }
    
    @Override
    public double evaluate() {
	setState(State.START);
        while (true) {
            switch (getState()) {
                case START: 
                    start(); 
                    break;
                case NUMBER:
                    number(); 
                    break;
                case OPERATOR:
                    operator(); 
                    break;
                case LEFT_PAREN:
                    left_paren();
                    break;
                case RIGHT_PAREN:
                    right_paren();
                    break;
                case END:
                    end();
                    return (Double)getStack().pop();
                default: 
                    throw new Error("Something is wrong in SimpleCalculator.evaluate"); // shouldn't happen
            }
        } 
    }

    @Override
    public void reduce() {
        if(numOpNumOnStack()){
            while(numOpNumOnStack()){
                if(getDispenser().tokenIsEOF()){
                    reduceNumOpNum();
                }else if(getDispenser().tokenIsOperator()){
                    if(getDispenser().getChar()== '/' || getDispenser().getChar()== '*'){
                        break;
                    }else{
                        reduceNumOpNum();
                    }
                }
            }
        }else{
            //do nothing?
        }
    }
    
    /**
     * Performs the processing in the state named START.
     * A token is parsed, and if it is not a number an error is signaled.
     * Otherwise, the state is changed to NUMBER.
     */
    private void start() {
        getDispenser().advance();
        if (!getDispenser().tokenIsNumber()) {
            syntaxError(NUM);
        }
        setState(State.NUMBER);
    }
    
    /**
     * Performs the processing in the state named NUMBER.
     * The number is shifted onto the stack and the next token is parsed.
     * If the token is EOF the state is changed to END.
     * If the token is an operator the state is changed to OPERATOR.
     * Otherwise an error is signaled.
     */
    private void number() {
        shift();
        getDispenser().advance();
        if (getDispenser().tokenIsEOF()) {
            setState(State.END);
        } else if (getDispenser().tokenIsOperator()) {
            setState(State.OPERATOR);
        } else if (getDispenser().tokenIsLeftParen()) {
            setState(State.LEFT_PAREN);
        } else if (getDispenser().tokenIsRightParen()) {
            setState(State.RIGHT_PAREN);
        } else {
            syntaxError(OP_OR_END);
        }
    }
    
     /**
     * Performs the processing in the state named OPERATOR.
     * The operator is shifted onto the stack and the next token is parsed.
     * If the next token is not a number an error is signaled.
     * Otherwise the state is changed to NUMBER.
     */
    private void operator() {
        reduce();
        shift();
        getDispenser().advance();
        if (!getDispenser().tokenIsNumber()) {
            syntaxError(NUM);
        }else if (getDispenser().tokenIsLeftParen()) {
            setState(State.LEFT_PAREN);
        } else if (getDispenser().tokenIsRightParen()) {
            setState(State.RIGHT_PAREN);
        }else{
            setState(State.NUMBER);
        }
    }
    
    private void left_paren(){
        getDispenser().advance();
        if(getDispenser().tokenIsLeftParen()){
            setState(State.LEFT_PAREN);
        }else if(getDispenser().tokenIsRightParen()){
            setState(State.RIGHT_PAREN);
        }else if(getDispenser().tokenIsNumber()){
            setState(State.NUMBER);
        }else if(getDispenser().tokenIsOperator()){
            setState(State.OPERATOR);
        }else if(getDispenser().tokenIsEOF()){
            syntaxError("Error -- operator expected. Found: (");
        }
    }
    
    private void right_paren(){
        getDispenser().advance();
        reduce();
        if(getDispenser().tokenIsEOF()){
            setState(State.END);
        }else if(getDispenser().tokenIsLeftParen()){
            setState(State.LEFT_PAREN);
        }else if(getDispenser().tokenIsNumber()){
            setState(State.NUMBER);
        }else if(getDispenser().tokenIsOperator()){
            setState(State.OPERATOR);
        }
    }
}