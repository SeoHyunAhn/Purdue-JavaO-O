import java.util.Scanner;
public class MovieCopy {
    private String name;
    private double criticRating;
    private double usersRating;
    private int numUsersRatings;

    public MovieCopy(String name, double criticRating, double usersRating, int numUsersRatings) {
        if (criticRating < 1 || criticRating > 5) {
            criticRating = 5;
        }
        if (usersRating < 1 || usersRating > 5) {
            usersRating = 5;
        }
        this.name = name;
        this.criticRating = criticRating;
        this.usersRating = usersRating;
        this.numUsersRatings = numUsersRatings;
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

    public int getNumUsersRatings() {
        return numUsersRatings;
    }

    public boolean addUserRating(int newRating) {
        double c=1;
        if (newRating < 1 && newRating > 5)
            return false;
        c = numUsersRatings + 1;
        usersRating = (usersRating * numUsersRatings + newRating) / c;
        return true;
    }

    public double reviewRang() {
        int reviewRange = 0;
        if (numUsersRatings>=0 && numUsersRatings<=1000)
            return 1;
        else if (numUsersRatings >=1001 && numUsersRatings<=5000)
            return 2;
        else if (numUsersRatings >=5001 && numUsersRatings<=10000)
            return 3;
        else if (numUsersRatings >=10001 && numUsersRatings<=15000)
            return 4;
        else if (numUsersRatings >=15001 && numUsersRatings<=20000)
            return 5;
        else if (numUsersRatings >=20001 && numUsersRatings<=25000)
            return 6;
        else if (numUsersRatings >=25001 && numUsersRatings<=30000)
            return 7;
        else if (numUsersRatings >=30001 && numUsersRatings<=50000)
            return 8;
        else if (numUsersRatings >=50001 && numUsersRatings<=100000)
            return 9;
        else if (numUsersRatings >=100001)
            return 10;
        return reviewRang();
    }

    public static int compareMovies(MovieCopy movie1, MovieCopy movie2) {

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
            else if (smartScore1 < smartScore2)
                return 2;
            else
                return 0;
        }
        if (movie1.criticRating - movie2.criticRating<0.001) {
            if (movie1.usersRating > movie2.usersRating)
                return 1;
            else if (movie1.usersRating == movie2.usersRating)
                return 0;
            else
                return 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of movie1");
        String name1 = scan.next();
        System.out.println("Enter the critic rating of " + name1);
        double criticRating1 = scan.nextDouble();
        System.out.println("Enter the Users rating of " + name1);
        double usersRating1 = scan.nextDouble();
        System.out.println("Enter the Number of users rating of " + name1);
        int numUsersRatings1 = scan.nextInt();
        MovieCopy movie1 = new MovieCopy(name1, criticRating1, usersRating1, numUsersRatings1);
        //Movie 2
        System.out.println("Enter the name of movie2");
        String name2 = scan.next();
        System.out.println("Enter the critic rating of " + name2);
        double criticRating2 = scan.nextDouble();
        System.out.println("Enter the Users rating of " + name2);
        double usersRating2 = scan.nextDouble();
        System.out.println("Enter the Number of users rating of " + name2);
        int numUsersRatings2 = scan.nextInt();
        MovieCopy movie2 = new MovieCopy(name2, criticRating2, usersRating2, numUsersRatings2);

        System.out.println("Enter the new users rating of " + name1);
        int newRating1 = scan.nextInt();
        movie1.addUserRating(newRating1);
        System.out.println("Enter the new users rating of " + name2);
        int newRating2 = scan.nextInt();
        movie1.addUserRating(newRating2);

        System.out.println(compareMovies(movie1, movie2));

    }
}
