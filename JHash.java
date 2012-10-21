
/*
JHash.java

This class implements my own variant on hash tables
I call it the dual array combo attack


*/

public class JHash {

	private String[] mValueArray;

	private String[] mKeyArray;

	private int mIndex = 0;
	
	private int mMaxIndex = 0;	

	public JHash(int max) {
		mMaxIndex = max;
		mValueArray = new String[mMaxIndex];
		mKeyArray = new String[mMaxIndex];	
	}

	public void add(String key, String value) {
		if(mIndex <= mMaxIndex) {
			mValueArray[mIndex] = value;
			mKeyArray[mIndex] = key;
			mIndex++;
		}
	}

	public String get(String key) {
		int index = 0;
		for(int i = 0; i < mMaxIndex; i++) {
			if(key.equals(mKeyArray[i])) {
				index = i;
				break;
			}
		}
		return mValueArray[index];
	}

}
