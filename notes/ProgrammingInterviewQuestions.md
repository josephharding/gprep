# Question 1 #
You have a set of integers. The function needs to return one inteteger such that it does not already exist in the set of numbers and is within the bounds of 0 and the highest number in the set. It does not necessarily need to be in any order relative to the set. If such a number does not exist, return the next highest possible number. The numbers in the set are guaranteed to be unique.

## Examples ##
{3, 4, 0, 1} would return 2
{0, 3, 2} would return 1
{1, 2} would return 0
{2, 4, 6} should return 0, 1, 3, or 5

# Question 2 #
Given a string, and an array of characters, find the shortest substring that contains every character in the array.

Not sure if the characters in the array are sorted, but it would be nice if they were.

## Examples ##
String: "catdog" Array:['o', 'a']
Answer: "atdo"
