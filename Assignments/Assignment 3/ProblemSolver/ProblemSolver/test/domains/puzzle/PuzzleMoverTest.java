package domains.puzzle;

import domains.puzzle.PuzzleState.Location;
import framework.problem.Mover;
import framework.problem.State;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Your name here
 */
public class PuzzleMoverTest {
        
    /**
     * Declare private instance fields here. For example:
     */
    
    private State start;
    private final Mover mover;
    private final String slide1, slide2, slide3, slide4,  // move names
	                 slide5, slide6, slide7, slide8;
    
    public PuzzleMoverTest() {
        mover = new PuzzleMover();
        List<String> moveNames = mover.getMoveNames();
        
        slide1 = moveNames.get(0);
        slide2 = moveNames.get(1);
        slide3 = moveNames.get(2);
        slide4 = moveNames.get(3);
        slide5 = moveNames.get(4);
        slide6 = moveNames.get(5);
        slide7 = moveNames.get(6);
        slide8 = moveNames.get(7);
    }
    
    /**
     * Test all moves in the methods below by replacing the calls to fail.
     */

    @Test
    public void testSlide1() {
        test(slide1,1);
    }

    @Test
    public void testSlide2() {
        test(slide2,2);        
    }

    @Test
    public void testSlide3() {
        test(slide3,3);      
    }

    @Test
    public void testSlide4() {
        test(slide4,4);       
    }

    @Test
    public void testSlide5() {
        test(slide5,5);      
    }

    @Test
    public void testSlide6() {
        test(slide6,6);        
    }

    @Test
    public void testSlide7() {
        test(slide7,7);       
    }

    @Test
    public void testSlide8() {
        test(slide8,8);       
    }
    
    private void test(String move,int tileNum){
            //test if tileNum is in middle can swap to any adjecent tile
            Location num = new Location(1,1);
            Location zero = new Location(0,1);
            assertTrue(testSlide(num,zero,tileNum,move));
            zero = new Location(2,1);
            assertTrue(testSlide(num,zero,tileNum,move));
            zero = new Location(1,0);
            assertTrue(testSlide(num,zero,tileNum,move));
            zero = new Location(1,2);
            assertTrue(testSlide(num,zero,tileNum,move));

            //test if tileNum is middle and can't swap to nonadjecent tiles
            zero = new Location(0,0);
            assertTrue(testSlide(num,zero,tileNum,move));
            zero = new Location(0,2);
            assertTrue(testSlide(num,zero,tileNum,move));
            zero = new Location(2,0);
            assertTrue(testSlide(num,zero,tileNum,move));
            zero = new Location(2,2);
            assertTrue(testSlide(num,zero,tileNum,move));
    }
    
    private Boolean testSlide(Location num,Location zero,int tileNum,String move){
        int[][] newState={new int[]{9, 9, 9}, 
                          new int[]{9, 9, 9}, 
                          new int[]{9, 9, 9}};
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i==num.getRow() && j==num.getColumn()){
                    newState[i][j]=tileNum;
                }else if(i==zero.getRow() && j==zero.getColumn()){
                    newState[i][j]=0;
                }
            }
        }
        
        start = new PuzzleState(newState);
        
        if(!start.equals(mover.doMove(move, start))){
            return true;
        }else if(start.equals(mover.doMove(move, start))){
            return true;
        }else{
            return false;
        }
    }
}