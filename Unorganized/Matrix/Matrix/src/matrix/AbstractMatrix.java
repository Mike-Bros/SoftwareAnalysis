package matrix;

import static matrix.Matrix.create;

/**
 * This abstract class partially implements the Matrix API.
 * Only the number of rows and columns are stored. A subclass
 * should create the data structure used to store matrix elements.
 *
 * @author tcolburn
 */
public abstract class AbstractMatrix implements Matrix {

    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     */
    @Override
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     */
    @Override
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Sets the number of rows in this matrix.
     * @param numRows the number of rows in this matrix
     * @throws MatrixException if numRows is not positive
     */
    @Override
    public void setNumRows(int numRows) {
        if (numRows <= 0) {
            throw new MatrixException(String.format("numRows (%s) must be positive", numRows));
        }
        this.numRows = numRows;
    }

    /**
     * Sets the number of columns in this matrix.
     * @param numColumns the number of columns in this matrix
     * @throws MatrixException if numColumns is not positive
     */
    @Override
    public void setNumColumns(int numColumns) {
        if (numColumns <= 0) {
            throw new MatrixException(String.format("numColumns (%s) must be positive", numColumns));
        }
        this.numColumns = numColumns;
    }

    /**
     * Creates a visual representation of this matrix as a string. This method
     * can be used for debugging. This is a template method; it uses a method
     * (get) that must be implemented by a subclass. This method overrides a
     * method in the Object class.
     *
     * @return the string representation of this matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumColumns(); c++) {
                builder.append(String.format("%6s", get(r, c)));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Tests for equality of this matrix with another. Matrices are equal if
     * they have the same dimensions and all elements are equal by ==. This is a
     * template method; it uses a method (get) that must be implemented by a
     * subclass. This method overrides a method in the Object c=lass, so it must
     * type check and cast its argument to the correct type.
     *
     * @param obj the other matrix to be tested for equality with this matrix
     * @return <b>true</b> if the other matrix is equal to this matrix,
     * <b>false</b> otherwise
     */
   
    public boolean equals(Object obj) {
        if(obj instanceof Matrix){
            Matrix obj2 = (Matrix) obj;
            
            if((obj2.getNumRows() == this.getNumRows()) && (obj2.getNumColumns() == this.getNumColumns())){
                for(int i=0;i<this.getNumColumns();i++){
                    for(int j=0;j<this.getNumRows();j++){
                        if(obj2.get(j,i) != this.get(j,i)){
                            return false;
                        }
                    }
                }
            }else{
                return false;
            }
        }else{
            throw new MatrixException("Equals was pased an object that is not a Matrix");
        }
        return true;
    }

    /**
     * Adds this matrix to another.
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not have
     * the same dimensions
     */
    @Override
    public Matrix add(Matrix other) {
        Matrix newMatrix = create(getNumRows(),getNumColumns());
        
        if ((other.getNumRows() != this.getNumRows()) || (other.getNumColumns() != this.getNumColumns())) {
            throw new MatrixException("Matrices must be same dimensions to add");
        }
        
        for(int i=0;i<this.getNumColumns();i++){
            for(int j=0;j<this.getNumRows();j++){
                newMatrix.set(j, i, other.get(j,i)+this.get(j,i));
            }
        }
        return newMatrix;
    }

    /**
     * Multiplies this matrix by another.
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not
     * match the number of rows in the other
     */
    @Override
    public Matrix multiply(Matrix other) {
        Matrix newMatrix = create(this.getNumRows(),other.getNumColumns());
        int temp=0;
        
        if (this.getNumColumns() != other.getNumRows()) {
            throw new MatrixException(String.format
            ("This matrix has (%s) columns, other has (%s) rows. These two values must be equal", 
            this.getNumColumns(),other.getNumRows()));
        }
        
        for(int i=0;i<this.getNumRows();i++){
            for(int j=0;j<other.getNumColumns();j++){
                newMatrix.set(i, j, dotProduct(i,j,this,other));
            }
        }
        return newMatrix;
    }
    
    /**
     * Dot product of row obj1 and column obj2 other matrix to multiply
     * @return a new matrix that is the product
     * @param row the row of obj1 to use in dot product
     * @param column the column of obj2 to use in dot product
     * @param obj1 the first matrix
     * @param obj2 the second matrix
     * @return the dot product
     */
    private int dotProduct(int row, int column, Matrix obj1, Matrix obj2){
        int product=0;
        for(int i=0;i<obj1.getNumColumns();i++){
            product+= obj1.get(row, i)*obj2.get(i, column);
        }
        return product;
    }
    /**
     * Private instance fields follow
     */

    private int numRows;
    private int numColumns;

}