
/**
 * Created by student on 11/15/16.
 */
public class CountPrimesExecutor {
    private long lower;
    private long upper;
    private int numThreads;
    private CountPrimes[] countPrimeThreads;

    public CountPrimesExecutor(int numThreads, long lower, long upper){
        this.numThreads=numThreads;
        this.lower=lower;
        this.upper=upper;
        countPrimeThreads=new CountPrimes[numThreads];
        // initalize thread
        long range = (upper-lower)/numThreads +1;
        long start=lower;

        for (int i=0; i<numThreads; i++){
            if (i==numThreads-1)
                countPrimeThreads[i]=new CountPrimes(start, upper);
            else {
                countPrimeThreads[i] = new CountPrimes(start, start + range-1);
                start=start+range;
            }
        }
    }
    public void executeThreads(){
        //Start thread
       for (int i=0; i<numThreads; i++){
           countPrimeThreads[i].start();
       }
        //end thread&& print result
        try{
            for (int i=0; i<numThreads; i++){
                countPrimeThreads[i].join();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
