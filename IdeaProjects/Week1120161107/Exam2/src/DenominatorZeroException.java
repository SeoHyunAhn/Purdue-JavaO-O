/**
 * Created by student on 11/7/16.
 */
public class DenominatorZeroException extends Exception {
        public DenominatorZeroException(){
            super("Denominator is Zero");
        }
    public DenominatorZeroException(String str){
        super(str);
    }
}
