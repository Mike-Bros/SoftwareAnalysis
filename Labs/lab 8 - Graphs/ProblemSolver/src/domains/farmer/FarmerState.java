package domains.farmer;

import framework.problem.State;
import java.util.Arrays;

/**
 *
 * @author Mike
 */
public class FarmerState implements State{
    
    FarmerState(String farmer, String wolf, String goat, String cabbage) {
        String[] objectSides = {farmer,wolf,goat,cabbage};
        
        for(int i=0;i<objects.length;i++){
            setSide(objectSides[i],i);
        }
    }
    

    @Override
    public boolean equals(Object other) {
        
        if(other instanceof FarmerState){
            FarmerState otherState = (FarmerState) other;
            return Arrays.equals(otherState.contents, this.contents);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   |  |   \n");
        for(int i=0;i<objects.length;i++){
            if(contents[i]==true){
                sb.append(objects[i]+"|  |   ");
            }else{
                sb.append("   |  |"+ objects[i]);
            }
            sb.append("\n");
        }
        
        sb.append("   |  |   ");
        return sb.toString();
    }
    
    
    private void setSide(String side, int index){
    
        if(side=="West"){
            contents[index]=true;
        }else if(side=="East"){
            contents[index]=false;
        }else{
            throw new Error("Problem initializing state");
        }
    }
    
    final Boolean[] contents = {false,false,false,false};
    private final String[] objects = {" F "," W "," G "," C "};
}
