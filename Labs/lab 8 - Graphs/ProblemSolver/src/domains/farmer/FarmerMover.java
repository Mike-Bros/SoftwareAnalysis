package domains.farmer;

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
            setSides(otherState);
           swapSides(0);
           if(!wolfCanEat()&&!goatCanEat()){
               sideToString();
               return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
           }else{
               swapSides(0);
               return illegalMove(otherState);
           }
           
        }
        
         private State tryWolf(State state) {
           FarmerState otherState = (FarmerState) state;
           setSides(otherState);
           if(onSameSide(0,1)){
               swapSides(1);
               swapSides(0);
               if(!wolfCanEat()&&!goatCanEat()){
                   sideToString();
                   return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
               }else{
                   swapSides(1);
                   swapSides(0);
                   return illegalMove(otherState);
               }
           }else{
               return illegalMove(otherState);
           }
        }
         
          private State tryGoat(State state) {
           FarmerState otherState = (FarmerState) state;
           setSides(otherState);
           if(onSameSide(0,2)){
               swapSides(2);
               swapSides(0);
               if(!wolfCanEat()&&!goatCanEat()){
                   sideToString();
                   return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
               }else{
                   swapSides(2);
                   swapSides(0);
                   return illegalMove(otherState);
               }
           }else{
               return illegalMove(otherState);
           }
        }
          
           private State tryCab(State state) {
           FarmerState otherState = (FarmerState) state;
           setSides(otherState);
           if(onSameSide(0,3)){
               swapSides(3);
               swapSides(0);
               if(!wolfCanEat()&&!goatCanEat()){
                   sideToString();
                   return new FarmerState(objectSides[0],objectSides[1],objectSides[2],objectSides[3]);
               }else{
                   swapSides(3);
                   swapSides(0);
                   return illegalMove(otherState);
               }
           }else{
               return illegalMove(otherState);
           }
        }

        private State illegalMove(State state) {
            return null;
        }
        
        //helper method that sets private string array to that of the states
        private void setSides(State state){
            FarmerState otherState = (FarmerState) state;
            this.contents=otherState.contents;
        }
        
        private void sideToString(){
            for(int i=0;i<objectSides.length;i++){
                //if east
                if(contents[i]==false){
                    objectSides[i]="East";
                }else{
                    objectSides[i]="West";
                }
            }
        }
        
        private Boolean isWest(int placement){
            return contents[placement];
        }
        
       private void swapSides(int object){
           if(isWest(object)){
               //set to east (false)
               contents[object]=false;
           }else{
               //set to west (true)
               contents[object]=true;
           }
       }
       
       private Boolean wolfCanEat(){
           if(onSameSide(0,1)){
               return false;
           }else if(onSameSide(1,2)){
               return true;
           }else{
               return false;
           }
       }
       
       private Boolean goatCanEat(){
           if(onSameSide(0,2)){
               return false;
           }else if(onSameSide(2,3)){
               return true;
           }else{
               return false;
           }
       }
        
       private Boolean onSameSide(int object1,int object2){
           if(isWest(object1)&&isWest(object2)){
               return true;
           }else if(!isWest(object1)&&!isWest(object2)){
               return true;
           }else{
               return false;
           }
       }
       
        private Boolean[] contents;
        private final String[] objectSides = {"","","",""};
}
