package calculator;

/**
 * @author Mike Bros
 */
public class PrecedenceCalculator extends NoPrecedenceCalculator {
    
    public PrecedenceCalculator(String title) {
        super(title);
    }
    
    public PrecedenceCalculator() {
        this("Calculator With Operator Precedence");
    }
    
    
    /**
     * Reduces the stack from either NUM or NUM OP NUM to NUM.
     * Takes into account precedence of operators
     */
    @Override
    public void reduce() {
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
    }

}