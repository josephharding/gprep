/**
 * Iterator.java
 * 
 * @version: 1.0
 * Purpose:  Interface for Iterators to use.
 * Usage:    Have Iterator classes implement the interface.
 *
 * Taken from Beginning Algorithms by Harris and Ross
 *********************************************************/

public interface Iterator
{
    public void first();

    public void last();

    public boolean isDone();

    public void next();

    public void previous();

    public Object current() throws IteratorOutOfBoundsException;
}