// class for ding string manipulations

import java.util.Random;

public class StringMan {

	public String reverseString(String subject) {
		char[] charArray = subject.toCharArray();
		int currentPosition = 0;
		int oppositePosition = charArray.length - 1;
		boolean reversed = false;
		while(!reversed) {
			// flip values
			char temp = charArray[currentPosition];	
			charArray[currentPosition] = charArray[oppositePosition];
			charArray[oppositePosition] = temp;

			currentPosition++;
			oppositePosition--;
			// check progress towards reversal
			if (currentPosition >= oppositePosition) {
				reversed = true;
			}
		}
		return new String(charArray);
	}

	public String scramble(String subject) {
		Random rand = new Random();
		char[] charArray = subject.toCharArray();
		int len = charArray.length - 1;		
		// brute force, roll random on len, the result = target index, flip target index with index len-1, roll random on
		// len-1, repeat
	
		boolean done = false;
		while(!done) {
			int rIndex = rand.nextInt(len);
			char temp = charArray[rIndex];
			charArray[rIndex] = charArray[len];
			charArray[len] = temp;
			len--;
			if(len == 0) {
				done = true;
			}	
		}

		// I'd like to find a solution that uses a single random roll
		return new String(charArray);
	}

}
