import java.util.Scanner;

/**
 * Created by student on 9/21/16.
 */
public class lab05 {
    public class Restaurants {
        // On campus
        public static final String ON_CAMPUS_VEGAN = "Purdue Dining Courts\nFlatbreads";
        public static final String ON_CAMPUS_VEGETARIAN = ON_CAMPUS_VEGAN + "\nOasis Cafe\nAh*Z\nFreshens";
        public static final String ON_CAMPUS_GLUTEN_FREE = "Purdue Dining Courts\nFlatbreads\nOasis Cafe\nPappy's " +
                "Sweet Shop";
        public static final String ON_CAMPUS_BURGERS = "Pappy's Sweet Shop\nCary Knight Spot";
        public static final String ON_CAMPUS_SANDWICHES = "Flatbreads\nOasis Cafe\nErbert & Gerbert's";
        public static final String ON_CAMPUS_OTHERS = "Purdue Dining Courts\nAh*Z\nFreshens\nLemongrass";
        public static final String ON_CAMPUS_ALL = ON_CAMPUS_BURGERS + "\n" + ON_CAMPUS_SANDWICHES + "\n" +
                ON_CAMPUS_OTHERS;

        // Off campus
        public static final String OFF_CAMPUS_VEGAN = "Chipotle\nQdoba\nNine Irish Brothers\nFive Guys\n Puccini's " +
                "Smiling Teeth\nPanera Bread";
        public static final String OFF_CAMPUS_VEGETARIAN = OFF_CAMPUS_VEGAN + "\nWendy's\nBruno's Pizza\nJimmy " +
                "John's\nPotbelly Sandwich Shop\nBasil Thai\nIndia Mahal";
        public static final String OFF_CAMPUS_GLUTEN_FREE = "Chipotle\nQdoba\nNine Irish Brothers\nPuccini's Smiling " +
                "Teeth\nWendy's\nScotty's Brewhouse\nPanera Bread\nBasil Thai";
        public static final String OFF_CAMPUS_BURGERS = "Five Guys\nWendy's\nTriple XXX\nScotty's Brewhouse";
        public static final String OFF_CAMPUS_SANDWICHES ="Panera Bread\nJimmy John's\nPotbelly Sandwich Shop";
        public static final String OFF_CAMPUS_PIZZAS = "Puccini's Smiling Teeth\nMad Mushroom Pizza\nBruno's Pizza\n";
        public static final String OFF_CAMPUS_OTHERS = "Chipotle\nQdoba\nNine Irish Brothers\nFamous Frank's\n Von's " +
                "Dough Shack\nBuffalo Wild Wings\nBasil Thai\nMaru Sushi\nIndia Mahal\nHappy China\nYori";
        public static final String offCampusAll = OFF_CAMPUS_BURGERS + "\n" + OFF_CAMPUS_SANDWICHES + "\n" +
                OFF_CAMPUS_PIZZAS + OFF_CAMPUS_OTHERS;
    }
    public static boolean getYesNoKey() {
        int key;

        java.util.Scanner oScanner = new java.util.Scanner(System.in);

        do { key = oScanner.findInLine(".").charAt(0);
            if (key == 'y' || key == 'Y') return true;
            if (key == 'n' || key == 'N') return false;
        } while (key != 0);

        return false; // 이 줄은 실행되지 않음
    }



public static void main(String[] args) {
    System.out.println("Enter 1 if you want to eat on campus. If not, enter 2");
    Scanner scan = new Scanner(System.in);
    int a = scan.nextInt();
    if (a == 1) {
        System.out.println("Do you have dietary restrictions? (Y/N)");
        if (getYesNoKey()) {
            System.out.println("1. Vegan, 2. Vegetarian, 3.Gluten free ");
            int c = scan.nextInt();
            if (c == 1)
                System.out.print(Restaurants.ON_CAMPUS_VEGAN);
            else if (c == 2)
                System.out.print(Restaurants.ON_CAMPUS_VEGETARIAN);
            else
                System.out.print(Restaurants.ON_CAMPUS_GLUTEN_FREE);
        } else {
            System.out.print("Food Preference (Y/N)");
            if (getYesNoKey()) {
                System.out.print("1. Burgers, 2. Sandwiches, 3. Others");
                int e = scan.nextInt();
                if (e == 1)
                    System.out.print(Restaurants.ON_CAMPUS_BURGERS);
                else if (e == 2)
                    System.out.print(Restaurants.ON_CAMPUS_SANDWICHES);
                else
                    System.out.print(Restaurants.ON_CAMPUS_OTHERS);
            } else
                System.out.print(Restaurants.ON_CAMPUS_ALL);
        }
    if (a == 2) {
            System.out.println("Do you have dietary restrictions? (Y/N)");
            if (getYesNoKey()) {
                System.out.println("1. Vegan, 2. Vegetarian, 3.Gluten free ");
                int g = scan.nextInt();
                if (g == 1)
                    System.out.print(Restaurants.OFF_CAMPUS_VEGAN);
                else if (g == 2)
                    System.out.print(Restaurants.OFF_CAMPUS_VEGETARIAN);
                else
                    System.out.print(Restaurants.OFF_CAMPUS_GLUTEN_FREE);
            } else {
                System.out.print("Food Preference (Y/N)");
                if (getYesNoKey()) {
                    System.out.print("1. Burgers, 2. Sandwiches, 3. Others");
                    int i = scan.nextInt();
                    if (i == 1)
                        System.out.print(Restaurants.OFF_CAMPUS_BURGERS);
                    else if (i == 2)
                        System.out.print(Restaurants.OFF_CAMPUS_SANDWICHES);
                    else
                        System.out.print(Restaurants.OFF_CAMPUS_OTHERS);
                } else
                    System.out.print(Restaurants.offCampusAll);
            }
        }
    }
}
}




