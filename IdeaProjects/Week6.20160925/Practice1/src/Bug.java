import java.util.Scanner;

/**
 * Created by student on 9/30/16.
 */
public class Bug {
    private int direction; //+1 for right, -1 for left
    private int position;
    public Bug(int position){
       this.position=position;
        //it always starts at right
        this.direction=1;
    }
    public int getDirection(){return this.direction;}
    public int getPosition(){return this.position;}
    public void setDirection(int newDirection){this.direction=newDirection;}
    public void turn(){this.direction*=-1;}
    public void move(){this.position+=this.direction;}
}

/*int initialPosition = 0;
    int position;
    String direction="right";

    public Bug(int initialPosition) {
        this.initialPosition = initialPosition;
    }

    public void turn() {

        if (direction.equals("right"))
            direction = "left";
        else
            direction="right";
    }

    public void move() {
        if (direction.equals("left"))
            position = initialPosition - 1;
        else
            position = initialPosition + 1;
    }

    public int getPosition() {
        return position;
    }

    public String getDirection() {
        return direction;
    }
}*/