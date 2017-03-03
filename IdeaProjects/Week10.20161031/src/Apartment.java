/**
 * Created by student on 10/31/16.
 */
public class Apartment extends Residence{
    private int floorNumber;


    public Apartment(String address, int numBedrooms, int numBathrooms, int squareFootage, double monthlyRent, int floorNumber) {
        super(address, numBedrooms, numBathrooms, squareFootage, monthlyRent);
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber(){return floorNumber;}

    @Override
    public String toString() {
        return "Address: " + super.getAddress()+"Number of Bedrooms: "+super.getNumBedrooms()+"Numer of Bathrooms: "+super.getNumBathrooms()+"Squarefoot: "+super.getSquareFootage()+"Monthyl Rent"+super.getMonthlyRent()+"Floor Number: "+getFloorNumber();
    }

}
