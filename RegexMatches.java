import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

    public static void main( String args[] ) {

        // String to be scanned to find the pattern.
        String line = "hello world";
        String pattern = "(.+)(r.+)(.+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find()) {
            // group 0 always exists and is the entire line
            System.out.println("Found value: " + m.group(0) );

            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
        } else {
            System.out.println("NO MATCH");
        }
    }
}