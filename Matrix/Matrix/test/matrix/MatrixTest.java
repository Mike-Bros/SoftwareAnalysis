package matrix;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Matrix API.
 * @author tcolburn
 */
public class MatrixTest {
    
    public MatrixTest() {
        m3x2 = Matrix.create(3, 2);
        m3x2_ = Matrix.create(3, 2);
        m2x3 = Matrix.create(2, 3);
        m2x3_ = Matrix.create(2, 3);
        m3x3 = Matrix.create(3, 3);
        m3x3_ = Matrix.create(3,8);
    }

    @Test
    public void testDimensions() {
        assertTrue(m3x2.getNumRows() == 3);
        assertTrue(m3x2.getNumColumns() == 2);
    }

    @Test
    public void testBounds() {
        // these checks should succeed
        for (int r = 0; r < m3x2.getNumRows(); r++) {
            for (int c = 0; c < m3x2.getNumColumns(); c++) {
                m3x2.checkBounds(r, c);
            }
        }
        // Test for exceptions
        tryBadIndex(-1, 2); // bad row
        tryBadIndex(2, -1); // bad column
        tryBadIndex(3, 1);  // bad row
        tryBadIndex(2, 2);  // bad column
    }
    
    private void tryBadIndex(int row, int col) { // row or column should be
        try {                                    // out of bounds
            m3x2.checkBounds(row, col);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) { }
    }
    
    @Test
    public void testGet() {  // m should have all zeroes
        for (int r = 0; r <  m3x2.getNumRows(); r++) 
            for (int c = 0; c < m3x2.getNumColumns(); c++) 
                assertTrue(m3x2.get(r, c) == 0);
        System.out.println(m3x2);
    }
    
    @Test
    public void testSetAndGet() {
        m3x2.set(0, 0, 1);   m3x2.set(0, 1, 4);  // 1 4
        m3x2.set(1, 0, 2);   m3x2.set(1, 1, 5);  // 2 5
        m3x2.set(2, 0, 3);   m3x2.set(2, 1, 6);  // 3 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 4);
        assertTrue(m3x2.get(1, 0) == 2);  assertTrue(m3x2.get(1, 1) == 5);
        assertTrue(m3x2.get(2, 0) == 3);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testClear() {
        m3x2.set(0, 0, 1);   m3x2.set(0, 1, 4);  // 1 4
        m3x2.set(1, 0, 2);   m3x2.set(1, 1, 5);  // 2 5
        m3x2.set(2, 0, 3);   m3x2.set(2, 1, 6);  // 3 6
        
        m3x2.clear();
        
        assertTrue(m3x2.get(0, 0) == 0);  assertTrue(m3x2.get(0, 1) == 0);
        assertTrue(m3x2.get(1, 0) == 0);  assertTrue(m3x2.get(1, 1) == 0);
        assertTrue(m3x2.get(2, 0) == 0);  assertTrue(m3x2.get(2, 1) == 0);
    }
    
    @Test
    public void testFillRowWise() {
        m3x2.fillRowWise();           // 1 2
                                      // 3 4
                                      // 5 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 2);
        assertTrue(m3x2.get(1, 0) == 3);  assertTrue(m3x2.get(1, 1) == 4);
        assertTrue(m3x2.get(2, 0) == 5);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testFillColumnWise() {
        m3x2.fillColumnWise();        // 1 4
                                      // 2 5
                                      // 3 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 4);
        assertTrue(m3x2.get(1, 0) == 2);  assertTrue(m3x2.get(1, 1) == 5);
        assertTrue(m3x2.get(2, 0) == 3);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testTranspose() {
        m3x2.fillColumnWise();          // 1 4
                                        // 2 5
                                        // 3 6
                                        
        m2x3 = m3x2.transpose();        // 1 2 3
                                        // 4 5 6
        assertTrue(m2x3.get(0, 0) == 1 && m2x3.get(0, 1) == 2 && m2x3.get(0, 2) == 3);
        assertTrue(m2x3.get(1, 0) == 4 && m2x3.get(1, 1) == 5 && m2x3.get(1, 2) == 6);
    }
    
    @Test
    public void testMakeIdentity() {
        try {                                    
            m3x2.makeIdentity();
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) { }
        
        m3x3.makeIdentity();    // 1 0 0
                                // 0 1 0
                                // 0 0 1
        assertTrue(m3x3.get(0, 0) == 1 && m3x3.get(0, 1) == 0 && m3x3.get(0, 2) == 0);
        assertTrue(m3x3.get(1, 0) == 0 && m3x3.get(1, 1) == 1 && m3x3.get(1, 2) == 0);
        assertTrue(m3x3.get(2, 0) == 0 && m3x3.get(2, 1) == 0 && m3x3.get(2, 2) == 1);
    }
    
    /**
     * This test should test the equality of:
     *   1. A matrix with itself (should be true)
     *   2. Several pairs of matrices of differing dimensions 
     *      (should be false)
     *   3. Two empty matrices of the same dimensions (should be true)
     *   4. Two nonempty matrices of the same dimensions with the same 
     *      values for elements (should be true)
     *   5. Two nonempty matrices of the same dimensions with the same 
     *      values except for one element (should be false)
     *   6. A nonempty matrix with the transpose of the transpose of
     *      itself (should be true)
     */
    @Test
    public void testEquals() {
        //1.
        m3x3.fillRowWise();
        m2x3.fillRowWise();
        m3x2.fillRowWise();
        assertTrue(m3x3.equals(m3x3));
        assertTrue(m3x2.equals(m3x2));
        assertTrue(m2x3.equals(m2x3));
        //2.
        assertTrue(!m3x3.equals(m3x2));
        assertTrue(!m3x3.equals(m2x3));
        assertTrue(!m2x3.equals(m3x3));
        assertTrue(!m2x3.equals(m3x2));
        assertTrue(!m3x2.equals(m2x3));
        assertTrue(!m3x2.equals(m3x2));
        //3.
        m3x3.clear();
        m3x3_.clear();
        m3x2.clear();
        m3x2_.clear();
        m2x3.clear();
        m2x3_.clear();
        assertTrue(m3x3.equals(m3x3_));
        assertTrue(m3x3_.equals(m3x3));
        assertTrue(m3x2.equals(m3x2_));
        assertTrue(m3x2_.equals(m3x2));
        assertTrue(m2x3.equals(m2x3_));
        assertTrue(m2x3_.equals(m2x3));
        //4.
        m3x3.fillRowWise();
        m3x3_.fillRowWise();
        m2x3.fillRowWise();
        m2x3_.fillRowWise();
        m3x2.fillRowWise();
        m3x2_.fillRowWise();
        assertTrue(m3x3.equals(m3x3_));
        assertTrue(m3x3_.equals(m3x3));
        assertTrue(m3x2.equals(m3x2_));
        assertTrue(m3x2_.equals(m3x2));
        assertTrue(m2x3.equals(m2x3_));
        assertTrue(m2x3_.equals(m2x3));
        //5.
        m3x3.fillRowWise();
        m3x3.set(1,1,5);
        m3x3_.fillRowWise();
        m2x3.fillRowWise();
        m2x3.set(1,1,5);
        m2x3_.fillRowWise();
        assertTrue(!m3x3.equals(m3x3_));
        assertTrue(!m3x3_.equals(m3x3));
        assertTrue(!m3x2.equals(m3x2_));
        assertTrue(!m3x2_.equals(m3x2));
        //6. 
        m3x3.fillRowWise();
        m2x3.fillRowWise();
        m3x2.fillRowWise();
        assertTrue(m3x3.equals(m3x3.transpose().transpose()));
        assertTrue(m3x2.equals(m3x2.transpose().transpose()));
        assertTrue(m2x3.equals(m2x3.transpose().transpose()));
    }
    
    /**
     * This test should:
     *   1. Try to add two matrices of different dimensions and catch the
     *      thrown exception
     *   2. Add two empty matrices of the same dimensions and confirm
     *      the result with assertions
     *   3. Add two nonempty matrices of the same dimensions and confirm
     *      the result with assertions
     */
    @Test
    public void testAdd() {
	// You must provide
    }
    
    /**
     * This test should:
     *   1. Try to multiply several pairs of incompatible matrices and catch the
     *      thrown exceptions
     *   2. Multiply two nonempty compatible matrices and confirm
     *      the result with assertions
     *   3. Multiply a nonempty square matrix by the identity matrix of the same
     *      dimensions and confirm that the result is the original matrix
     */
    @Test
    public void testMultiply() {
	// You must provide
    }
    
    private Matrix m3x2;
    private Matrix m3x2_;
    private Matrix m2x3;
    private Matrix m2x3_;
    private Matrix m3x3;
    private Matrix m3x3_;
}