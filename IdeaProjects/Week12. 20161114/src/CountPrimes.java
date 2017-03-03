import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by student on 11/15/16.
 */
public class CountPrimes extends Thread{
    private long lower;
    private long upper;
    private static AtomicInteger numPrimes=new AtomicInteger(0);

    public CountPrimes(long lower, long upper){
        this.lower=lower;
        this.upper=upper;
    }
    public void run() {
        for (long i = lower; i <= upper; i++) {
            if(isPrime(i)==true)
                numPrimes.incrementAndGet();
            }

    }

    public static int getNumPrimes(){return numPrimes.get();}
    public static void resetNumPrimes(){numPrimes.set(0);}
    public boolean isPrime(long num) {
        if (num<=1)
            return false;
        else if (num==2)
            return true;
        for (long j = 2; j < num; j++) {
            if (num % j == 0)
                return false;
        }
        return true;
    }
}
