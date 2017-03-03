import java.util.Scanner;

public class Pokemon {
    private String name;
    private int Id;
    private String type = "Fire";
    private int healthPower = 0;
    private double baseAttackPower = 1;
    private static int NUM_POKEMONS = 0;

    // Initialization
    public Pokemon(String name, String type, int healthPower, double baseAttackPower) {
        this.name = name;
        if(setType(type) == true)
            this.type = MyUtils.formatStr(type);
        else
            this.type = "Fire";
        if (healthPower<0)
            healthPower=0;
        this.healthPower = healthPower;
        if (baseAttackPower<0)
            baseAttackPower=1;
        this.baseAttackPower = baseAttackPower;
        Id = NUM_POKEMONS;
        NUM_POKEMONS++;//새로운거 들어올때 하나씩 더하는 것이기에 여기인 컨스트럭터에 들어가야함
    }
    //Accessors
    public String getName(){return name;}
    public int getId(){
        return Id;}
    public String getType(){return type;}
    public int getHealthPower(){return healthPower;}
    public double getBaseAttackPower(){return baseAttackPower;}
    //Mutators
    public boolean setType(String type){

    if (type.equalsIgnoreCase("Grass")|| type.equalsIgnoreCase("Fire")||type.equalsIgnoreCase("Water")||type.equalsIgnoreCase("Electric")){
        this.type = type;
        return true;}
    else
        return false;
    }
    public boolean setHealthPower(int healthPower){
        if (healthPower<0)
    return false;
    else
        this.healthPower=healthPower;
    return true;}

    public boolean setBaseAttackPower(double baseAttackPower){
        if (baseAttackPower<0)
            return false;
        else
            this.baseAttackPower=baseAttackPower;
            return true;}
    //---------------------------------------------------------------------
    @Override
    public String toString(){
        //System.out.println(healthPower);
        //System.out.println(baseAttackPower);
        return "Name: "+name+"\nID: "+Id+"\nType: "+type+"\nHealth Power: "+healthPower+"\nBase Attack Power: "+baseAttackPower;
        //this빼고 써도 괜찮을 듯
        /*this.name+
        Pokemon pokemon=new Pokemon(name, ID, type, healthPower, baseAttackPower);
    return pokemon;*/
    }
    public boolean isDead(){
        if(healthPower>0)
            return false;
        else
            return true;
    }
    public void boostHealthPower(int healthPower){
        this.healthPower +=healthPower;
        //처음꺼는 health power of the object 뒤에꺼는 괄호안에 써있는 것
    }
    public void reduceHealthPower(int healthPower){
        this.healthPower -=healthPower;
        if(this.healthPower<0)
            this.healthPower= 0;
    }
    //Class methods
    public static int battle(Pokemon p1, Pokemon p2){
        do {
            double x=p1.healthPower-p2.baseAttackPower*getAttackMultiplier(p2, p1);
            p1.healthPower=(int)x;
            //if(p1.healthPower <= 0)
                //break;
            double y=p2.healthPower-p1.baseAttackPower*getAttackMultiplier(p1, p2);
            p2.healthPower=(int)y;
            //if (p2.healthPower<=0)
              //  break;
        }while (p1.healthPower>0 && p2.healthPower>0);

        int winner = 1;
        if (p1.healthPower>p2.healthPower)
            winner = 1;//If P1 wins
        else
            winner = 2;
        if(p1.healthPower<0)
            p1.healthPower=0;
        if (p2.healthPower<0)
            p2.healthPower=0;
        return winner;

    }
    public static double getAttackMultiplier(Pokemon attacker, Pokemon defender){
        if(attacker.type.equals("Grass"))
            if(defender.type.equals("Grass")||defender.type.equals("Fire"))
                return 0.5;
            else if (defender.type.equals("Electric"))
                return 1;
            else
                return 2;
        else if(attacker.type.equals("Electric"))
            if(defender.type.equals("Grass")||defender.type.equals("Electric"))
                return 0.5;
            else if (defender.type.equals("Fire"))
                return 1;
            else
                return 2;
        else if(attacker.type.equals("Water"))
            if(defender.type.equals("Grass")||defender.type.equals("Water"))
                return 0.5;
            else if (defender.type.equals("Electric"))
                return 1;
            else
                return 2;
        else
            if(defender.type.equals("Fire")||defender.type.equals("Water"))
                return 0.5;
            else if (defender.type.equals("Electric"))
                return 1;
            else
                return 2;

    }
    public static int battleOracle(Pokemon p1, Pokemon p2){
        Pokemon pokemon1=new Pokemon(p1.getName(), p1.getType(), p1.getHealthPower(), p1.getBaseAttackPower());
        Pokemon pokemon2=new Pokemon(p2.getName(), p2.getType(), p2.getHealthPower(), p2.getBaseAttackPower());
        int x= battle(pokemon1, pokemon2);
        return x;
    }

    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        System.out.println("Enter the first Pokemon's Name: ");
        String name1=scan.next();
        System.out.println("Enter the first Pokemon's Type:");
        String type1=scan.next();
        System.out.println("Enter the first Pokemon's Health Power(HP): ");
        String HPS1=scan.next();
        MyUtils.isNumeric(HPS1);
        int healthPower1=Integer.parseInt(HPS1);

        System.out.println("Enter the first Pokemon's Base Attack's Power:");
        String BAP1=scan.next();
        MyUtils.isNumeric(BAP1);
        int baseHealthAttack1=Integer.parseInt(BAP1);
        Pokemon pokemon1=new Pokemon(name1, type1, healthPower1, baseHealthAttack1);

        System.out.println("Enter the second Pokemon's Name: ");
        String name2=scan.next();
        System.out.println("Enter the second Pokemon's Type:");
        String type2=scan.next();
        System.out.println("Enter the second Pokemon's Health Power(HP): ");
        String HPS2=scan.next();
        MyUtils.isNumeric(HPS2);
        int healthPower2=Integer.parseInt(HPS2);

        System.out.println("Enter the second Pokemon's Base Attack's Power:");
        String BAP2=scan.next();
        MyUtils.isNumeric(BAP2);
        int baseHealthAttack2=Integer.parseInt(BAP2);
        Pokemon pokemon2=new Pokemon(name2, type2, healthPower2, baseHealthAttack2);

        System.out.println("Reducing by "+baseHealthAttack2);
        battle(pokemon1, pokemon2);
        System.out.println("Reducing by" +baseHealthAttack1);

        System.out.println("First Pokemon's Stats after the battle: \n\nName:"+pokemon1.getName()+"\nID:"+pokemon1.getId()+"\nType: "+pokemon1.getType()+"\nHealth Power(HP): "+pokemon1.getHealthPower()+"\nAttack Power: "+pokemon1.getBaseAttackPower()+"\n------------------------");
        System.out.println("Second Pokemon's Stats after the battle: \n\nName:"+pokemon2.getName()+"\nID:"+pokemon2.getId()+"\nType: "+pokemon2.getType()+"\nHealth Power(HP): "+pokemon2.getHealthPower()+"\nAttack Power: "+pokemon2.getBaseAttackPower()+"\n========================");


        if (battleOracle(pokemon1, pokemon2)==2)
            System.out.println("The winner of the battle is "+name2);
        else
            System.out.println("The winner of the battle is "+name1);
    }
}

