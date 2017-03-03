/**
 * Created by student on 9/28/16.
 */

public class MyUtils {
    public static boolean isNumeric(String str) {
        int dot=0;
        boolean flag=false;
        do {
            if (str == null || str.length() == 0)
                return false;
            for (int i = 0; i < str.length(); i++) {
//character가 .이면 게속하는데 2번이상은 안되게
                if (Character.isDigit(str.charAt(i)) == false) {
                    if (str.charAt(i) == '.'){
                        dot++;
                        if (dot>1)
                            return false;}
                    else
                        flag = true;

                }
                if (flag == true)
                    return false;
            }
        }while (false);
        return true;
    }
//exception 사용
    public static String formatStr(String str) {
        String firstLetter = str.substring(0,1).toUpperCase();
        String restLetters = str.substring(1).toLowerCase();
        //System.out.println(firstLetter+restLetters);
        return firstLetter + restLetters;
    }
    }

