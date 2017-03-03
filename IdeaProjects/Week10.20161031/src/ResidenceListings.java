
/**
 * Created by student on 10/31/16.
 */
public class ResidenceListings {
    private int numResidences;
    private int maxResidences;
    private Residence[] residences;

    public ResidenceListings(){
        maxResidences=10;
        residences=new Residence[10];
    }
    public void addResidence(Residence residence){
        if (residences.length==numResidences) {
            int arraySize = residences.length * 2;
            Residence[] tempArray = new Residence[arraySize];
            for (int i = 0; i < residences.length; i++) {
                tempArray[i] = residences[i];
            }
            residences = tempArray;
            maxResidences=residences.length;
        }
        for (int i=0; i<residences.length; i++){
            if (residences[i]==null) {
                residences[i]=residence;
                numResidences++;
                return;
            }
        }
    }
    private int findResidence(Residence residence) {
        // if found , return the index
        // otherwise, return -1;

        for (int i=0; i<numResidences; i++){
            if (residences[i].equals(residence)){
                return i;
         }
        }
        return -1;
    }
    public void removeResidence(Residence residence) throws NoSuchResidenceException {
        // calculate size, do deletion
        if (findResidence(residence) == -1) {
            throw new NoSuchResidenceException("");
        }
        for (int i = findResidence(residence); i < numResidences-1; i++) {
            residences[i] = residences[i + 1];
        }
        residences[numResidences-1] = null;
        numResidences-=1;
            // 만약에 비었는데 지우려고 하면 throw exception
    }
    //            int arraySize=0;
//            if (residences[i] != null) {
//                arraySize++;}
    public Residence findResidenceByAddress(String address){
        for (int i=0; i<residences.length; i++){
            if (residences[i].getAddress().equals(address))
                return residences[i];
        }
        return null;
    }
    public int getNumResidences(){return numResidences;}
    public int getMaxResidences(){return maxResidences;}
    public Residence[] getResidences(){return residences;}

}
