
/**
JHash2.java

This class implements modular hashing
It does seem to be faster....
*/

public class JHash2 {
	
	private int mArraySize;

	private String[] mArray;

	public JHash2(int minSize) {
		mArraySize = PrimeCentral.getNextPrime(minSize);
		if(mArraySize < 1) {
			mArraySize = 1;
			System.out.println("got bad value for size of array");
		}
		//System.out.println("chose " + mArraySize + " as the prime to use");
		mArray = new String[mArraySize];
	}	

	public void add(String key, String value) {
		mArray[hash(key)] = value;
	}

	public String get(String key) {
		return mArray[hash(key)];
	}
	
	public int hash(String key) {
		return key.hashCode() % mArraySize;
	}

}
