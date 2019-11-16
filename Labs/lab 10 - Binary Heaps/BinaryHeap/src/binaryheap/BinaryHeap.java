package binaryheap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;

/**
 * This class implements a binary heap data structure by extending the
 * ArrayList class.
 * It also implements the java.util.Queue interface so that it can be
 * used with the framework problem solver.
 * @author tcolburn
 * @param <E> the type of element stored on this binary heap
 */
public class BinaryHeap<E> extends ArrayList<E> implements Queue<E> {

    /**
     * Creates an empty binary heap with a given capacity and comparator.
     * @param capacity The initial size of the underlying ArrayList object.
     * @param comp A comparator object for comparing keys of binary heap elements.
     */
    public BinaryHeap(int capacity, Comparator<E> comp) {
        super(capacity+1);
        init();
        this.comp = comp;
    }

    /**
     * Getter for the comparator for this binary heap.
     * @return the comparator for this binary heap
     */
    public Comparator<E> getComp() {
        return comp;
    }

    /**
     * Initializes the underlying ArrayList object for use as a binary heap.
     * A null object is added to location 0, which is not used by the heap.
     */
    private void init() {
        add(0, null);
    }

    /**
     * Clears this binary heap by clearing and initializing the underlying
     * ArrayList object.
     */
    @Override
    public void clear() {
        super.clear();
        init();
    }

    /**
     * Returns the current size of this binary heap.  Since the first location 
     * (index 0) of the underlying ArrayList object is not used, the size of the 
     * binary heap is one less than the size of the ArrayList object.
     * @return The binary heap's current size. 
     */
    @Override
    public int size() {
        return super.size()-1;
    }

    /**
     * Returns true if this binary heap is empty, that is, its size is zero.
     * @return Whether this binary heap is empty.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a new element to this binary heap.  At the end of the add,
     * the heap has one more element and the heap property is maintained.
     * @param element The element to add
     * @return true.  In accordance with the Collection interface, returns
     * true since duplicate elements are allowed.
     */
    @Override
    public boolean add(E element) {
        //Increase size of array by 1
        this.ensureCapacity(size()+1);
        
        
        //create index variable at end of the array
        //used to keep track of where element is trying to be placed
        int indexE = size();
        Boolean repeat = true;
        
        //repeat until element has been added to the ArrayList
        while(repeat){
            //canAdd will return true if adding element at indexE
            //will keep the tree balanced
            if(canAdd(element,indexE)){
                //set the element to indexE and break out of the loop
                set(indexE,element);
                repeat = false;
                //increase modCount
                modCount++;
            }else{
                //swap the parent E into the indexE position
                set(indexE,get(indexE/2));
                //increase modCount
                modCount++;
                //Change index to the parent node to try canAdd() again
                indexE= indexE/2;
            }
            
        }
        
        return true;
    }
        

    /**
     * Removes an element from the root of this binary heap.  After removal,
     * the heap has one less element and the heap property is restored.
     * This method does not override any method in the ArrayList class 
     * (note absence of an index parameter).
     * However, it implements a method in the Queue interface.
     * @return The removed element
     */
    @Override
    public E remove() {
        E removed = this.remove();
        Boolean repeat = true;
        int index = 0;
       //set as last right-most node
       set(index,get(size()));
       
       while(repeat){
           //if index node has smaller children
           if(hasSmaller(index)){
                //find smallest child of index node
                //and swap index with smaller child node
                smallestChild(index);
                
           }//else end loop
           else{
               repeat = false;
           }
       }
	
        return removed;    
    }

    /**
     * A Comparator object used to compare binary heap elements during its
     * add and remove operations.
     */
    private final Comparator<E> comp;

    /*
    The following are required to complete the implementation of the Queue<E> 
    interface. They are not used in the test.
    */
    
    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean canAdd(E element,int indexE) {
        //if empty, E can be added
        //compare() returns...
        // -1 if element is less than parent
        // 0 if elemebet is equal to parent
        // 1 if element is greater than parent
        // -1 return false
        // 0 return true
        // 1 return true
        
        if(this.isEmpty()){
            return true;
        }else if(0 >= this.comp.compare(element, get(indexE/2))){
            return true;
        }else{
            return false;
        }
    }

    private boolean hasSmaller(int index) {
        //if index has no children return false
        if(index>=size()){
            return false;
        }
        //if left or right child is smaller return true
        if((-1 == this.comp.compare(get(index*2), get(index))) || -1 == this.comp.compare(get(index*2+1), get(index))){
            return true;
        }else{
            return false;
        }
    }

    private void smallestChild(int index) {
        E temp;
        //if left child is smaller swap with index
        if(0 >= this.comp.compare(get(index*2), get(index*2+1))){
            temp = get(index);
            set(index,get(index*2));
            set(index*2,temp);
            modCount++;
        }//else if right child is smaller swap with index
        else if(0 >= this.comp.compare(get(index*2+1), get(index*2))){
            temp = get(index);
            set(index,get(index*2+1));
            set(index*2+1,temp);
            modCount++;
        }
    }

   
}
