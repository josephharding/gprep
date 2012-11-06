/**
 * ArrayIterator.java
 * 
 * @version    1.0
 * Purpose     Class used to iterate over an array of Objects with a layer of abstraction.
 * Usage:      Construct an iterator with an array as a parameter
 *             and call functions as needed to traverse it.
 *
 * Taken from Beginning Algorithms by Harris and Ross
 *********************************************************/

public class ArrayIterator implements Iterator
{
    private final Object[] array;
    private final int first;
    private final int last;
    private int current = -1;

    /**
     * creates an ArrayIterator object using the supplied array
     *
     * @param array the array to be iterated
     */
    public ArrayIterator(Object[] array)
    {
        assert array != null : "array can't be null";
        this.array = array;
        first = 0;
        last = array.length - 1;
    }

    /**
     * creates an ArrayIterator object using the supplied array, starting
     * at the supplied index and extending for the supplied length
     *
     * @param array  the array to be iterated
     * @param start  the array index where the iterator should start
     * @param length the number of elements the iterator should cover
     */
    public ArrayIterator(Object[] array, int start, int length)
    {
        assert array != null : "array can't be null";
        assert start >= 0 : "start can't be < 0";
        assert start < array.length : "start can't be > array.length";
        assert length >= 0 : "length can't be < 0";

        this.array = array;
        this.first = start;
        this.last = start + length - 1;

        assert this.last < array.length : "start + length can't be > array.length";
    }

    /**
     * moves the internal pointer to the first element in the ArrayIterator
     */
    public void first()
    {
        current = first;
    }

    /**
    * moves the internal pointer to the last element in the ArrayIterator
    */
    public void last()
    {
        current = last;
    }

    /**
    * moves the internal pointer to the next element in the ArrayIterator
    */
    public void next()
    {
        ++current;
    }

    /**
    * moves the internal pointer to the previous element in the ArrayIterator
    */
    public void previous()
    {
        --current;
    }

    /**
    * checks to see if the internal pointer no longer points at an element
    * (pointer is outside of the bounds)
    *
    * @return Whether or not the internal pointer no longer points at an element
    */
    public boolean isDone()
    {
        return current < first || current > last;
    }

    /**
    * returns the object the internal pointer is pointing at. If not pointing
    * at anything, an IteratorOutOfBoundsException will be thrown
    *
    * @return                              the object the internal pointer is pointing at
    * @throws IteratorOutOfBoundsException If trying to access the current element
    *                                      while the internal pointer is not within bounds.
    */
    public Object current() throws IteratorOutOfBoundsException
    {
        if (isDone())
            throw new IteratorOutOfBoundsException();

        return array[current];
    }
}