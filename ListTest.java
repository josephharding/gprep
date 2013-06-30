
import java.lang.String;
import java.util.LinkedList;

public class ListTest {

    private LinkedList<String> mNameMap;

    public ListTest() {

        mNameMap = new LinkedList<String>();

        mNameMap.add("Joe");
        mNameMap.add("Char");
        mNameMap.add("Sue");

        System.out.println("name: " + mNameMap.getFirst());

        mNameMap.iterator();
    }

}