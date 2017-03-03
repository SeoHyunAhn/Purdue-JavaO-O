/**
 * Created by student on 9/6/16.
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.lang.Math;

public class CompoundInterest{

    public static void main (String[] args){
        double principle, IR, a, years;
        int interest;

        Scanner scan =new Scanner(System.in);

        System.out.println("Enter principle:");
        principle=scan.nextDouble();
        System.out.println("Enter Interest rate:");
        IR=scan.nextDouble();
        System.out.println("Enter years:");
        years=scan.nextDouble();
        a=principle*(Math.pow(1+IR/100.0, years));
        interest=(int)(a-principle);
        System.out.print("Interest: " + interest);
    }
}
