import java.util.Scanner;
import java.lang.Math;

public class Pythagoras{

    public static void main (String[] args){
        double a, b, c;
        double root1, root2;

        Scanner scan=new Scanner(System.in);

        System.out.println("Enter a");
        a=scan.nextInt();
        System.out.println("Enter b");
        b=scan.nextInt();

        root1=Math.pow(a, 2);
        root2=Math.pow(b, 2);
        c=Math.sqrt(root1+root2);
        System.out.println("Hypothenuse = " + c);
    }
}
