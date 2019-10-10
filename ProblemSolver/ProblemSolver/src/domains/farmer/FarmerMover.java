package domains.farmer;

import domains.arithmetic.ArithmeticState;
import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Mike
 */
public class FarmerMover extends Mover{
        public static final String FARM = "Farmer Goes Alone";
        public static final String WOLF = "Farmer Takes Wolf";
        public static final String GOAT = "Farmer Takes Goat";
        public static final String CAB = "Farmer Take Cabbage";
    
        public FarmerMover() {
            super.addMove(FARM, s -> tryFarm(s));
            super.addMove(WOLF, s -> tryWolf(s));
            super.addMove(GOAT, s -> tryGoat(s));
            super.addMove(CAB, s -> tryCab(s));
        }

        private State tryFarm(State state) {
            FarmerState otherState = (FarmerState) state;
            if(otherState.west[0]&& (otherState.west[1]||otherState.west[2]||otherState.west[3])){
                //return a state with farmer on the east side and everything 
                //else stays the same
                setSides(otherState);
                this.west[0]=false;
                this.east[0]=true;
                sideToString();
                return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
            }else{
                //return a state with farmer on the west side and everything 
                //else stays the same
                setSides(otherState);
                this.west[0]=true;
                this.east[0]=false;
                sideToString();
                return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
            }
        }
        
         private State tryWolf(State state) {
            FarmerState otherState = (FarmerState) state;
            if(otherState.west[0]&&otherState.west[1]){
                setSides(otherState);
                tryFarm(otherState);
                this.west[1]=false;
                this.east[1]=true;
                sideToString();
                return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
            }else if(otherState.east[0]&&otherState.east[1]){
                setSides(otherState);
                tryFarm(otherState);
                this.west[1]=true;
                this.east[1]=false;
                sideToString();
                return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
            }else{
                throw new Error("Cannot move the wolf");
            }
        }
         
          private State tryGoat(State state) {
            return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
        }
          
           private State tryCab(State state) {
            return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
        }

        private State illegalMove(State state) {
            return null;
        }
        
        private void setSides(State state){
            FarmerState otherState = (FarmerState) state;
            this.west=otherState.west;
            this.east=otherState.east;
        }
        
        private void sideToString(){
            for(int i=0;i<objectSides.length;i++){
                if(east[i]==true){
                    objectSides[i]="East";
                }else{
                    objectSides[i]="West";
                }
            }
        }
        
        private Boolean[] east;
        private Boolean[] west;
        private final String[] objectSides = {"","","",""};
}
