import java.util.Random;
/**
 * Created by student on 9/18/16.
 */
public class Team {
    private String name, location;
    private int nWon, nLosses;
    private double offSense, defense;
    //Constructor
    public Team(String name, String location){
        this.name=name;
        this.location=location;
    }
    //Prototype of Accessor
    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }
    public int getN_Wins(){
        return nWon;
    }
    public int getN_Lossess(){
        return nLosses;
    }
    public double getOffense(){
        return offSense;
    }
    public double getDefense(){
        return defense;
    }
    //Luck Class Method
    public static double luck(){
        Random rand = new Random();
        int  n = rand.nextInt(1);
        return n;
    }
    //Play Class Method
    public static Team play(Team team1, boolean isHome, Team team2)
        if (Team team1=boolean isHome)
            homeScore=(team1.getOffense);


}
