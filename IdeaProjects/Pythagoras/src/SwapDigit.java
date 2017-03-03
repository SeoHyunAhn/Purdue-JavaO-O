import java.util.Scanner;
import java.lang.Math;
/**
 * Created by student on 9/6/16.
 */
public class SwapDigit {

    public static void main (String[] args){
        int a;
        Scanner scan =new Scanner(System.in);

        System.out.println("Enter number:");
        a=scan.nextInt();
        int b=a%10;
        int c=a/10;
        System.out.println("Swapped " +b+c);


    }

}


