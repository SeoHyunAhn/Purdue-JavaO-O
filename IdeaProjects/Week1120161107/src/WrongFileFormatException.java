/**
 * Created by student on 11/6/16.
 */
public class WrongFileFormatException extends Exception {
    public WrongFileFormatException(){
        super();
    }
    public WrongFileFormatException(String str){
        super("str");
    }
}
