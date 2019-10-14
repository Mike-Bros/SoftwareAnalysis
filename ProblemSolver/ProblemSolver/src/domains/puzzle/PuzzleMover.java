package domains.puzzle;

import domains.dummy.DummyState;
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
            return null;
        }
        
        private State trySlide2(State state) {
            return null;
        }
        
        private State trySlide3(State state) {
            return null;
        }
        
        private State trySlide4(State state) {
            return null;
        }
        
        private State trySlide5(State state) {
            return null;
        }
        
        private State trySlide6(State state) {
            return null;
        }
        
        private State trySlide7(State state) {
            return null;
        }

        private State trySlide8(State state) {
            return null;
        }

        private State illegalMove(State state) {
            return null;
        }
}
