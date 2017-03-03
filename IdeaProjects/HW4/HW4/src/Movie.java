import java.util.Scanner;
public class Movie {
    private String name;
    private double criticRating;
    private double usersRating;
    private int numUsersRating;

    public Movie(String name, double criticRating, double usersRating, int numUsersRating) {
        if (criticRating < 1 || criticRating > 5) {
            criticRating = 5;
        }
        if (usersRating < 1 || usersRating > 5) {
            usersRating = 5;
        }
        this.name = name;
        this.criticRating = criticRating;
        this.usersRating = usersRating;
        this.numUsersRating = numUsersRating;
    }

    public String getName() {
        return name;
    }

    public double getCriticRating() {
        return criticRating;
    }

    public double getUsersRating() {
        return usersRating;
    }

    public int getNumUsersRating() {
        return numUsersRating;
    }

    public boolean addUserRating(int newRating) {
        double a;
        if (newRating >= 1 && newRating <= 5)
            a = numUsersRating + 1;
        usersRating = (usersRating * numUsersRating + newRating) / a;
        return true;
    }

    public double reviewRang() {
        int reviewRange = 0;
        for (numUsersRating = 0; numUsersRating <= 1000; numUsersRating++)
            return 1;
        for (numUsersRating = 1001; numUsersRating <= 5000; numUsersRating++)
            return 2;
        for (numUsersRating = 5001; numUsersRating <= 10000; numUsersRating++)
            return 3;
        for (numUsersRating = 10001; numUsersRating <= 15000; numUsersRating++)
            return 4;
        for (numUsersRating = 15001; numUsersRating <= 20000; numUsersRating++)
            return 5;
        for (numUsersRating = 20001; numUsersRating <= 25000; numUsersRating++)
            return 6;
        for (numUsersRating = 25001; numUsersRating <= 30000; numUsersRating++)
            return 7;
        for (numUsersRating = 30001; numUsersRating <= 50000; numUsersRating++)
            return 8;
        for (numUsersRating = 50001; numUsersRating <= 100000; numUsersRating++)
            return 9;
        for (numUsersRating = 100001; numUsersRating > 100000; numUsersRating++)
            return 10;
        return reviewRang();
    }

    public static int compareMovies(Movie movie1, Movie movie2) {

        double smartScore1 = 0.5 * movie1.criticRating + 0.3 * movie1.usersRating + 0.1 * movie1.reviewRang();
        double smartScore2 = 0.5 * movie2.criticRating + 0.3 * movie2.usersRating + 0.1 * movie2.reviewRang();

        if (movie1.criticRating > movie2.criticRating || movie2.criticRating > movie1.criticRating) {
            if (movie1.criticRating > movie2.criticRating && movie1.usersRating >= movie2.usersRating)
                return 1;
            if (movie2.criticRating > movie1.criticRating && movie2.usersRating >= movie1.usersRating)
                return 2;
        }

        if (movie1.criticRating > movie2.criticRating && movie1.usersRating < movie2.usersRating) {
            if (smartScore1 > smartScore2)
                return 1;
            if (smartScore1 < smartScore2)
                return 2;
            if (smartScore1 == smartScore2)
                return 0;
        }
        if (movie1.criticRating == movie2.criticRating) {
            if (movie1.usersRating > movie2.usersRating)
                return 1;
            if (movie1.usersRating == movie2.usersRating)
                return 0;
            else
                return 2;
        }
        return 0;
    }
}

public static void main(String[] args){
    Scanner scan=new Scanner(System.in);
    System.out.println("Enter the name of movie1");
    String name1=scan.next();
    System.out.println("Enter the critic rating of "+name1);
        double criticRating1=scan.nextDouble();
    System.out.println("Enter the Users rating of "+name1);
        double usersRating1=scan.nextDouble();
    System.out.println("Enter the Number of users rating of "+name1);
        double numUsersRating1=scan.nextDouble();
    System.out.println("Enter the new users rating of "+name1);
        boolean addUserRating1=scan.nextBoolean();
    Movie movie1 = new Movie(name1, criticRating1, usersRating1,numUsersRating1, addUserRating1);
        //Movie 2
        System.out.println("Enter the name of movie2");
    String name2=scan.next();
    System.out.println("Enter the critic rating of "+name2);
    String criticRating2=scan.next();
    System.out.println("Enter the Users rating of "+name2);
    String usersRating2=scan.next();
    System.out.println("Enter the Number of users rating of "+name2);
    String numUsersRating2=scan.next();
    System.out.println("Enter the new users rating of "+name2);
    String addUserRating2=scan.next();
    Movie movie2= new Movie(name2, criticRating2, usersRating2,numUsersRating2, addUserRating2);
    System.out.println(compareMovies(movie1, movie2));



        }
