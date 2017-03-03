/**
 * Created by student on 10/31/16.
 */
public class House extends Residence{
    private int numFloors;
    private boolean hasGarage;

    public House(String address, int numBedrooms, int numBathrooms, int squareFootage, double monthlyRent, int numFloors, boolean hasGarage) {
        super(address, numBedrooms, numBathrooms, squareFootage, monthlyRent);
        this.numFloors = numFloors;
        this.hasGarage = hasGarage;
    }

    public int getNumFloors(){return numFloors;}
    public boolean hasGarage(){return hasGarage;}

    @Override
    public String toString() {
        return "Address: " + super.getAddress()+"Number of Bedrooms: "+super.getNumBedrooms()+"Numer of Bathrooms: "+super.getNumBathrooms()+"Squarefoot: "+super.getSquareFootage()+"Monthyl Rent"+super.getMonthlyRent()+"Number of floors"+getNumFloors()+"Has Garage: "+hasGarage();
    }
}
