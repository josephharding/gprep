# Answer(s) to Question 1 #
This solution is reliant on the fact that you know the index of the element that you are accessing. Whether it be a counter that you make, or the list supplies the index, you should be able to compare that index against the value you retrieve.

If the list can be randomly accessed by index, then the commented out optimization can be used to save time. Since the list is sorted, if the last element is equal to the length, then there are no free numbers in the set and so the next highest possible number needs to be returned. If you have to traverse the whole list to access the last element, then it's not too much of an optimization and is practically nonexistent.

	if (list.size() == 0)
		return 0;

	sortAscending(list);

	/* Optimization
		if (list.size() == list.last())
			return list.last() + 1;
	*/

	int element = list.first();

	while (list.hasNext())
	{
		element = list.next();

		if (indexOf(element) != element)
			return indexOf(element);
	}

	return indexOf(element) + 1;