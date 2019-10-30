package domains.puzzle;

import domains.dummy.DummyState;
import domains.puzzle.PuzzleState.Location;
import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author Mike
 */
public class PuzzleMover extends Mover{
        public static final String SLIDE1 = "SLIDE TILE 1";
        public static final String SLIDE2 = "SLIDE TILE 2";
        public static final String SLIDE3 = "SLIDE TILE 3";
        public static final String SLIDE4 = "SLIDE TILE 4";
        public static final String SLIDE5 = "SLIDE TILE 5";
        public static final String SLIDE6 = "SLIDE TILE 6";
        public static final String SLIDE7 = "SLIDE TILE 7";
        public static final String SLIDE8 = "SLIDE TILE 8";
        
        

        public PuzzleMover() {
            super.addMove(SLIDE1, s -> trySlide1(s));
            super.addMove(SLIDE2, s -> trySlide2(s));
            super.addMove(SLIDE3, s -> trySlide3(s));
            super.addMove(SLIDE4, s -> trySlide4(s));
            super.addMove(SLIDE5, s -> trySlide5(s));
            super.addMove(SLIDE6, s -> trySlide6(s));
            super.addMove(SLIDE7, s -> trySlide7(s));
            super.addMove(SLIDE8, s -> trySlide8(s));
            
            
        }

        private State trySlide1(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,1);
        }
        
        private State trySlide2(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,2);
        }
        
        private State trySlide3(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,3);
        }
        
        private State trySlide4(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,4);
        }
        
        private State trySlide5(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,5);
        }
        
        private State trySlide6(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,6);
        }
        
        private State trySlide7(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,7);
        }

        private State trySlide8(State state) {
            PuzzleState otherState = (PuzzleState) state;
            return slideGeneric(otherState,8);
        }

        private State illegalMove(State state) {
            return null;
        }
        
        private State slideGeneric(State state,int tile){
            PuzzleState otherState = (PuzzleState) state;
            Location tileLoc = otherState.getLocation(tile);
            Location tile0Loc = otherState.getLocation(0);
            if(isAdjecent(tileLoc,tile0Loc)){
                return new PuzzleState(otherState,tileLoc,tile0Loc);
            }else{
                return illegalMove(otherState);
            }
        }
        
        private Boolean isAdjecent(Location loc,Location loc0){
            if(loc.getColumn()==loc0.getColumn()){
                if(loc.getRow()==1){
                    return true;
                }else if(loc0.getRow()==1){
                    return true;
                }else{
                    return false;
                }
            }else if(loc.getRow()==loc0.getRow()){
                if(loc.getColumn()==1){
                    return true;
                }else if(loc0.getColumn()==1){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
}
