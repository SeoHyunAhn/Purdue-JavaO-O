/**
 * Created by student on 11/7/16.
 */
public class Arithmetic {
    public int truncatedDivision(int a, int b)throws DenominatorZeroException{
        if (b==0){
            throw new DenominatorZeroException("Denominator was zero in truncatedDivision");
        }
        return a/b;
    }

    public int modulus(int a, int b){
        try {
            return a - b * truncatedDivision(a, b);
        }catch (DenominatorZeroException ex){
            return -1;
        }

    }
}
