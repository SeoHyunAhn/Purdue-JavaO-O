package ClassQork;

import javax.swing.*;

/**
 * Created by student on 9/23/16.
 */
public class Reverse1 {
    public static void main(String[] args) {
        String input, output = "";
        boolean isPalindrome=true;
        char c;
        input= JOptionPane.showInputDialog(null, "Enter the string to be Tested for being a palindrome");
        int leftIndex, rightIndex;
        //TOO check for valid input
        rightIndex=input.length()-1;
        leftIndex=0;
        while (leftIndex<rightIndex){
            if (input.charAt(leftIndex)!=input.charAt(rightIndex)){
                isPalindrome=false;
            }else {
                rightIndex--;
                leftIndex++;
            }

        }
        System.out.println("String is palindrome?"+ isPalindrome);
    }
}
//Infinite loop