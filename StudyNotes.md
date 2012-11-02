# Iterator #
## Summary ##
An iterator provides a number of operations for traversing and accessing data. Iterators can provide a level of abstraction when traversing a data structure. It frees the programmer from being tied to arrays, seperating the logic for selecting data from the code that actually processes it.

You should leave the behavior of calling <code>current()</code> before either <code>first()</code> or <code>last()</code> has been called undefined.

## Interface ##
	public interface Iterator
	{
		public void first();

		public void last();

		public boolean isDone();

		public void next();

		public void previous();

		public Object current() throws IteratorOutOfBoundsException;
	}

## Iterator Idioms ##
### While Loop ###
	Iterator iterator = ...;
	iterator.first();

	while (!iterator.isDone())
	{
		Object object = iterator.current();

		...

		iterator.next();
	}
### For Loop ###
	Iterator iterator = ...;

	for (iterator.first(); !iterator.isDone(); iterator.next())
	{
		Object object = iterator.current();

		...
	}

# Iterable Interface #
## Summary ##
If an object implements the Iterator interface, then the object will provide a generic way to obtain an iterator from it. The Iterable interface enables code that only needs to iterate over the contents of a data structure to treat all those that implement it in the same way, irrespective of the underlying implementation.

## Interface ##
	public interface Iterable
	{
		public Iterator iterator();
	}

# Lists #
## Summary ##
A list is an ordered collection of elements supporting random access to each element, much like an array - you can query a list to get the value contained at any arbitrary element.

## Characteristics ##
*  Preserves insertion order
*  Does not preserve uniqueness of values (list can contain duplicates)
*  Can be resized (unlike arrays which are fixed size)

## Core Operations ##
*  Insert
*  Delete
*  Get
*  Size

## Convenience Operations ##
* 