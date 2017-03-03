import jdk.nashorn.internal.scripts.JO;
import javax.swing.*;
import java.util.Random;

/**
 * Created by student on 9/18/16.
 */
public class Team {
    private String name, location;
    private int nWins, nLosses;
    private double offense, defense;
    //Constructor
    public Team(String name, String location){
        this.name=name;
        this.location=location;
        nWins=0;
        nLosses=0;
        offense=luck();
        defense=luck();
    }
    //Prototype of Accessor
    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }
    public int getN_Wins(){
        return nWins;
    }
    public int getN_Lossess(){
        return nLosses;
    }
    public double getOffense(){
        return offense;
    }
    public double getDefense(){
        return defense;
    }

    //Luck Class Method
    public static double luck(){
        Random random = new Random();
        double rand = random.nextDouble();
        double scaled = rand * 1.00;
        int c =(int) (scaled*100);
        return c/100.0;
    }

    //Play Class Method
    public static Team play(Team team1, boolean isHome, Team team2) {
        if (isHome = true) {
            double homeScore = (team1.getOffense() + team1.getDefense() + 0.2) * luck();
            double awayScore = (team2.getOffense() + team2.getDefense()) * luck();

            if (homeScore > awayScore) {
                team1.nWins++;
                team2.nLosses++;
                return team1;
            } else {
                team2.nWins++;
                team1.nLosses++;
                return team2;
            }
        }
        else {
            return play(team2, true, team1);
        }


    }


    //Team Object Methods
    public int calcTotalGames(){
        return nWins+nLosses;
    }
    public double calcWinRate(){
        return (nWins +0.0)/calcTotalGames();
    }//*100 --> main
    public double calcLossRate(){
        return (nLosses+0.0)/calcTotalGames();
    }
    public int calcDifference(){
        return nWins-nLosses;
    }
    //Statistics report
    public String generateStats(){

        //return generateStats();
        String Message="";
        Message += this.name + " (" +this.location+")\n";
        Message += "Total games: "+this.calcTotalGames()+"\n";
        Message += "No.Wins: "+this.nWins+" ("+calcWinRate()*100+"%)\n";
        Message += "No.Losses: "+this.nLosses+" ("+calcLossRate()*100+"%)\n";
        Message += "Difference: "+calcDifference();
        return Message;
    }

    public static void main(String[] args){
        String a= JOptionPane.showInputDialog("Enter name of the location for home team separated by a comma (,) ");
        String b= JOptionPane.showInputDialog( "Enter name of the location for away team separated by a comma (,) ");
        String[] parts =a.split(",");
        String homeP1=parts[0];
            String name1_all=homeP1.toLowerCase();
            String name1_First = homeP1.substring(0,1);
            String name1_upFirst = name1_First.toUpperCase();
            String name1= name1_upFirst+name1_all.substring(1, homeP1.length());

        String homeP2=parts[1];
            String location1_all=homeP2.toLowerCase();
            String location1_First = homeP2.substring(0,1);
            String location1_upFirst = location1_First.toUpperCase();
            String location1= location1_upFirst+location1_all.substring(1, homeP2.length());
            Team team1=new Team(name1, location1);

        String[] part =b.split(",");
        String awayP1=part[0];
            String name2_all=awayP1.toLowerCase();
            String name2_First = awayP1.substring(0,1);
            String name2_upFirst = name2_First.toUpperCase();
            String name2= name2_upFirst+name2_all.substring(1, awayP1.length());

        String awayP2=part[1];
            String location2_all=awayP2.toLowerCase();
            String location2_First = awayP2.substring(0,1);
            String location2_upFirst = location2_First.toUpperCase();
            String location2= location2_upFirst+location2_all.substring(1, awayP2.length());
            Team team2=new Team(name2, location2);

        JOptionPane.showMessageDialog(null, "Home team is: "+name1+" from "+location1+" rated "+team1.getOffense()+" (Offense) " +team1.getDefense()+" (Defense) ");
        JOptionPane.showMessageDialog(null, "Away team is: "+name2+" from "+location2+" rated "+team2.getOffense()+" (Offense) " +team2.getDefense()+" (Defense) ");
        Team Winner1 =play(team1, true, team2);
        JOptionPane.showMessageDialog(null, "Round 1 \nWinner is: "+Winner1.getName()+" from "+Winner1.getLocation()+Winner1.getOffense()+" (Offense) " +Winner1.getDefense()+" (Defense) ");
        Team Winner2 =play(team1, true, team2);
        JOptionPane.showMessageDialog(null, "Round 2 \nWinner is: "+Winner2.getName()+" from "+Winner2.getLocation()+Winner2.getOffense()+" (Offense) " +Winner2.getDefense()+" (Defense) ");
        Team Winner3 =play(team1, true, team2);
        JOptionPane.showMessageDialog(null, "Round 3 \nWinner is: "+Winner3.getName()+" from "+Winner3.getLocation()+Winner3.getOffense()+" (Offense) " +Winner3.getDefense()+" (Defense) ");
        JOptionPane.showMessageDialog(null, team1.generateStats());
        JOptionPane.showMessageDialog(null, team2.generateStats());

    }
}
