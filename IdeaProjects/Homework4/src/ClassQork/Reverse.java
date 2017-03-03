package ClassQork;

import javax.swing.*;

/**
 * Created by student on 9/23/16.
 */
public class Reverse {
    public static void main(String[] args) {
        String input, output = "";
        char c;
                input= JOptionPane.showInputDialog(null, "Enter the string to be reversed");
        int index;
        //TOO check for valid input
        index=input.length()-1;
        while(index>0){
            c=input.charAt(index);
            output=output+c;

        }
        System.out.println("the reversed string is"+output);
    }
}
