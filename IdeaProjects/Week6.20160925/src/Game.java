import java.util.Random;
import java.util.Scanner;

/**
 * Created by student on 9/25/16.
 */
public class Game {
    private final static int ROCK = 1;
    private final static int PAPER = 2;
    private final static int SCISSORS = 3;

    public void runGame() {
        Scanner scan = new Scanner(System.in);
        int move1;
        do {
            System.out.println("Welcome \nPlease enter an options: \n1.Rock\n2.Paper\n3.Scissors\n4.Exit");
            move1 = scan.nextInt();

            if (move1 == ROCK)
                System.out.println("You played rock!");
            else if (move1 == PAPER)
                System.out.println("You played Paper!");
            else if (move1 == SCISSORS)
                System.out.println("You played Scissor!");
            else {
                System.out.println("Thanks for playing!");
                return;
            }
            int a=this.simulateComputerMove();
            if (a == ROCK)
                System.out.println("The computer plays rock!");
            else if (a == PAPER)
                System.out.println("The computer plays Paper!");
            else
                System.out.println("The computer plays Scissor!");

            if (this.checkWinner(move1, a) == 1)
                System.out.println("You Win!");
            else if (this.checkWinner(move1, a) == 2)
                System.out.println("You lost!");
            else
                System.out.println("Draw!");
        } while (move1 >0 || move1 <4);
    }

    private int checkWinner(int move1, int move2) {
         if (move1-move2==2 || move1-move2==-1)
            return 2;
        else if(move1-move2==1 ||move1-move2==-2)
            return 1;
        else
             return 0;}
    private int simulateComputerMove() {
        Random random = new Random();
        return random.nextInt(3);
    }

    public static void main(String[] args) {
        Game game1=new Game();
        game1.runGame();
    }
}

/*
public class Game {
    private final static int ROCK = 1;
    private final static int PAPER = 2;
    private final static int SCISSORS = 3;

    public void runGame() {
        Scanner scan = new Scanner(System.in);
        int move1;
        do {
            System.out.println("Welcome \nPlease enter an options: \n1.Rock\n2.Paper\n3.Scissors\n4.Exit");
            move1 = scan.nextInt();

            if (move1 == ROCK)
                System.out.println("You played rock!");
            else if (move1 == PAPER)
                System.out.println("You played Paper!");
            else if (move1 == SCISSORS)
                System.out.println("You played Scissor!");
            else {
                System.out.println("Thanks for playing!");
                return;
            }


            {System.out.print("The computer plays ");
                if (this.simulateComputerMove() == ROCK)
                    System.out.println("rock!");
                else if (this.simulateComputerMove() == PAPER)
                    System.out.println("Paper!");
                else if (this.simulateComputerMove() == SCISSORS)
                    System.out.println("Scissor!");}

            if (this.checkWinner(move1, this.simulateComputerMove()) == 1)
                System.out.println("You Win!");
            else if (this.checkWinner(move1, this.simulateComputerMove()) == 2)
                System.out.println("You lost!");
            else if (this.checkWinner(move1, this.simulateComputerMove()) ==0)
                System.out.println("Draw!");
        } while (move1 >0 || move1 <4);
    }

    private int checkWinner(int move1, int move2) {
        switch (move2) {
            case ROCK:
                if (move1 == SCISSORS)
                    return 1;
                else if (move1 == PAPER)
                    return 2;
                else
                    return 0;
            case PAPER:
                if (move2 == ROCK)
                    return 1;
                else if (move2 == SCISSORS)
                    return 2;
                else
                    return 0;
            case SCISSORS:
                if (move2 == PAPER)
                    return 1;
                else if (move2 == ROCK)
                    return 2;
                else
                    return 0;
        }
        return 0;
    }

    private int simulateComputerMove() {
        Random random = new Random();
        int rand = random.nextInt(3) + 1;
        return rand;
    }

    public static void main(String[] args) {
        Game game1=new Game();
        game1.runGame();
    }
}

*/