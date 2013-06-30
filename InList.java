public class InList {

    private InListElement[] mArray;

    private int mCurrentIndex;

    public InList() {
        mArray = new InListElement[64];
        mCurrentIndex = 0;
    }

    public void push(String value) {
        if(mCurrentIndex != 0) {
            InListElement element = mArray[mCurrentIndex];
            element.setNextIndex(mCurrentIndex+1);
        }
        mArray[mCurrentIndex+1] = new InListElement(value);
        mCurrentIndex++;
    }

    public void print() {
        System.out.println(mArray[i].getValue());
        if(mArray[i].getNextIndex() != -1) {
            print(mArray[i].getNextIndex());
        }
    }

    private void resetIndex() {
        mCurrentIndex = 0;
    }

}

class InListElement {

    private String mValue;

    private int mNextIndex = -1;

    public InListElement(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

    public int getNextIndex() {
        return mNextIndex;
    }

    public void setNextIndex(int index) {
        mNextIndex = index;
    }

}