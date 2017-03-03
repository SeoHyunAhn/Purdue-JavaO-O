package ClassQork;

import javax.swing.*;

/**
 * Created by student on 9/23/16.
 */
public class Average {
    public static void main(String[] args) {
        int count, sum, input;
        count = 0;
        sum = 0;
        boolean doneWithInput = false;
        while (!doneWithInput) {
            input = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a number (0to end input"));
            if (input == 0)
                doneWithInput = true;
            else {
                sum += input;
                count++;
            }
            System.out.printf("The average of %d input is %f", count, ((double) count) / sum);
        }
    }
}
