/**
 * Created by student on 11/6/16.
 */
public class Person implements Ageable, Weighable {
    private int age;
    private double weight;
    public Person(int age, double weight){
        this.age=age;
        this.weight=weight;
    }

    @Override // flag for java doc, running by inherited method && make connection with others
    public int getAge() {
        return this.age;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
/*
empty array = length =0
null --> ==null
ArrayboundException
missing rows, nuable to access array --> access every array and take the length of that
    if ragged: all row would have a different array count
inheritace: super constructor
    super --> initalize the color(변수 --> initalize constructor
    initalize variable

    override
        use same method
        what is the instance of the variable
throwing and catching
    throw --> who ever using it needs to handle it
        if, a or b happen throw new exception
    catch -->  try & catch

*/