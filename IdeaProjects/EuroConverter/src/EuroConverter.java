/**
 * Created by student on 9/6/16.
 */
import java.util.Scanner;
import java.lang.Math;


public class EuroConverter{

    public static void main (String[] args){
        String usd, euroTousd;
        double euro;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount in USD");
        usd=scan.nextLine();
        double c=Double.valueOf((String)usd);

        System.out.println("Enter number of $ in 100 Euro");
        euroTousd=scan.nextLine();
        double d=Double.valueOf((String)euroTousd);

        euro= c * (100 / d);
        System.out.print("Number of euros = " + euro);
    }
}