/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.puzzle;

import domains.farmer.FarmerMover;
import domains.farmer.FarmerState;
import framework.problem.Problem;

/**
 *
 * @author Mike
 */
public class PuzzleProblem extends Problem {

    public PuzzleProblem() {
        super();
        super.setName("Farmer, Wolf, Goat, and Cabbage");
        super.setIntroduction(INTRO);
        super.setMover(new FarmerMover());
        super.setInitialState(new PuzzleState(
                        new int[][]{new int[]{2, 8, 3}, 
                                    new int[]{1, 6, 4}, 
                                    new int[]{7, 0, 5}}));
        super.setCurrentState(super.getInitialState());
        super.setFinalState(new PuzzleState(
                        new int[][]{new int[]{1, 2, 3}, 
                                    new int[]{8, 0, 4}, 
                                    new int[]{7, 6, 5}}));
    }
        private static final String INTRO = 
                "You are given a board in which numbered tiles can slide around. "
              + "There is one blank space that holds no tile.  A legal move "
              + "consists of sliding a tile into the blank space if the tile is "
              + "adjacent to it. The goal is to move tiles around until the board "
              + "looks like the final state below.";
    }
    

