/**
 * Created by student on 11/15/16.
 */
public class Divider implements Runnable{
    private Counter counter;
    int start=1;
    int end=3000;
    public Divider(){

    }
    public void run(){
        int range=(end-start+1)/3;
        Thread thread=new Thread();
        int least=start;
        for (int i=0; i<3; i++){
            if (i==2){

            }
        }

    }

    public static void main(String[] args) {
        //create three thread, wait for thread
    }
}
/*
       // initalize thread
        long range = (upper-lower+1)/numThreads;
        long reminder = (upper-lower+1)%numThreads;
        int count = 0;
        countPrimesThreads=new CountPrimes[numThreads];
        long start=lower;

        for (int i=0; i<numThreads; i++){
            if (i==numThreads-1)
                countPrimesThreads[i]=new CountPrimes(start, upper);
            else {
                countPrimesThreads[i] = new CountPrimes(start, start + range);
                start=start+range+1;
            }
        }
        //Start thread
       for (int i=0; i<numThreads; i++){
           countPrimesThreads[i].start();
       }
        //end thread&& print result
        try{
            for (int i=0; i<numThreads; i++){
                countPrimesThreads[i].join();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
 */