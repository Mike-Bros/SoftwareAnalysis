package domains.farmer;

import framework.problem.Problem;

public class FarmerProblem extends Problem {
    
        public FarmerProblem() {
            super();
            super.setName("Farmer, Wolf, Goat, and Cabbage");
            super.setIntroduction(INTRO);
            super.setMover(new FarmerMover());
            super.setInitialState(new FarmerState("Here is/an initial/state"));
            super.setCurrentState(super.getInitialState());
            super.setFinalState(new FarmerState("What happens/when/you call/Uber"));
        }

        private static final String INTRO = 
                "A farmer and his wold, goat, and cabbage come to the edge of a river they wish to cross. There is "
              + "a boat at the river's edge that only the farmer can row. The farmer can take at most one other obj"
              + "ect besides himself on a crossing, but if the wold is ever left with the goat, the wolf will eat t"
              + "he goat; similarly, if the goat is left with the cabbage, the goal will eat the cabbage. Device a "
              + "sequence of the crossing of the river so that all four character arrive safely on the other side.";
    }