import java.util.Scanner;

/**
 * Created by student on 9/30/16.
 */
public class StringManipulation {
    public boolean haveSameChars(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        for(int i=0; i<s1.length(); i++){
            char c=s1.charAt(i);
            for (int j=0; j<s2.length(); j++){
                char c2=s2.charAt(j);
                if (c==c2) {
                    break;
                }
                if (j==s2.length()-1)
                    return false;
                }

            }
        return true;}

    }


    /*
    public boolean haveSameChars(String s1, String s2){
        if (s1.equals(s2))
            return true;
        else if (s1==null || s2==null)
            return false;
        else
            return false;
    }
}
*/