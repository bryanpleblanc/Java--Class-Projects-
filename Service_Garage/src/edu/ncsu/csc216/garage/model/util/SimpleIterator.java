package edu.ncsu.csc216.garage.model.util;

/**
 * Project 3: SimpleIterator
 * 
 * Interface describing behaviors of a generic type of iterator 
 * with two methods: next() and hasNext(). This is identical to 
 * the Java API Iterator<E> type except it does not declare a 
 * remove() method.
 * 
 * @author Bryan LeBlanc 
 *
 * @param <E> generic object 
 */
public interface SimpleIterator<E> {
    
    /**
     * Determine if there is a next element in the list
     * @return True if there is a next element in the list
     */
    public boolean hasNext();
    
    /**
     * Gives the next element and moves the cursor ahead
     * @return E The next element in the list
     */
    public E next();

}
