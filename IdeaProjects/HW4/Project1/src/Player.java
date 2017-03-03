import java.text.DecimalFormat;
import java.util.Scanner;

public class Player {
    private String name;
    private double positionX;
    private double positionY;

    //Constructor prototype
    public Player(String name){
        this.name=name;
    }
    public Player(String name, double positionX, double positionY){
        this.name=name;
        this.positionX=positionX;
        this.positionY=positionY;
    }

//Player class accessors
    public String getName() {
        return name;
    }
    public double getPositionX() {
        return positionX;
    }
    public double getPositionY(){
        return positionY;
    }
    //Player class mutator
    public void setName(String playerName){
        name = playerName;}

    //Method prototypes
    public void moveX(double offsetX){
        positionX = positionX + offsetX;
    }
    public void moveY(double offsetY){

    positionY= positionY+ offsetY;
    }
    public void moveInDirection (double theta, double distance) {
        positionX = positionX + distance*Math.cos(theta*Math.PI/180);
        positionY=positionY + distance*Math.sin(theta*Math.PI/180);
    }
    public boolean hasSamePositionAs(Player player) {
        return distanceFrom(player) < 0.001;
    }
    public double distanceFrom(Player player) {
        //double dis = player1.distanceFrom(player2);
        double distance =Math.sqrt(Math.pow((positionX-player.positionX), 2)+Math.pow(positionY-player.positionY, 2));
        return distance;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter player1 name: ");
        String name1 =scan.next();

        System.out.println("enter the starting xPosition of " + name1 + ":");
        double positionX1=scan.nextDouble();

        System.out.println("enter the starting yPosition of " + name1 + ":");
        double positionY1=scan.nextDouble();
        Player player1 = new Player(name1, positionX1, positionY1);
        //System.out.println(player1.name+"("+player1.getPositionX()+","+player1.getPositionY()+")");

        //-------------------P2//
        System.out.println("Enter player2 name:");
        String name2 =scan.next();
        System.out.println("enter the starting xPosition of " + name2 + ":");
        double positionX2=scan.nextDouble();

        System.out.println("enter the starting yPosition of " + name2 + ":");
        double positionY2=scan.nextDouble();
        Player player2 = new Player(name2, positionX2, positionY2);
        //System.out.println(player2.name+"("+player2.getPositionX()+","+player2.getPositionY()+")");
//--------------movement of P1//

        System.out.println("Enter " + name1 + "'s horizontal move offset");
        double offsetX1=scan.nextDouble();
        player1.moveX(offsetX1);
        //System.out.println(player1.name+"("+player1.getPositionX()+","+player1.getPositionY()+")");

        System.out.println("Enter " + name1 + "'s vertical move offset");
        double offsetY1=scan.nextDouble();
        player1.moveY(offsetY1);
        //System.out.println(player1.name+"("+player1.getPositionX()+","+player1.getPositionY()+")");
        System.out.println("Enter " + name1 +"'s diangonal move angle degrees");
        double theta1=scan.nextDouble();
        System.out.println("Enter " + name1 +"'s diangonal move offset");
        double distance1=scan.nextDouble();
        player1.moveInDirection(theta1, distance1);
        //System.out.println(player1.name+"("+player1.getPositionX()+","+player1.getPositionY()+")");

//---------------movement of P2//
        System.out.println("Enter " + name2 + "'s horizontal move offset");
        double offsetX2=scan.nextDouble();
        player2.moveX(offsetX2);
        //System.out.println(player2.name+"("+player2.getPositionX()+","+player2.getPositionY()+")");

        System.out.println("Enter " + name2 + "'s vertical move offset");
        double offsetY2=scan.nextDouble();
        player2.moveY(offsetY2);
        //System.out.println(player2.name+"("+player2.getPositionX()+","+player2.getPositionY()+")");

        System.out.println("Enter " + name2 +"'s diangonal move angle degrees");
        double theta2=scan.nextDouble();
        System.out.println("Enter " + name2 +"'s diangonal move offset");
        double distance2=scan.nextDouble();
        player2.moveInDirection(theta2, distance2);
        //System.out.println(player2.name+"("+player2.getPositionX()+","+player2.getPositionY()+")");
//-------rounding//


        DecimalFormat f=new DecimalFormat(("#.00000"));
        System.out.println(name1+"'s Position: (" + f.format(player1.positionX)+") ("+f.format(player1.positionY)+")");
        System.out.println(name2+"'s Position: (" + f.format(player2.positionX)+") ("+f.format(player2.positionY)+")");
        System.out.println("Distance between player: "+f.format(player1.distanceFrom(player2)));
        System.out.println("Same Position: "+player1.hasSamePositionAs(player2));

    }
}
