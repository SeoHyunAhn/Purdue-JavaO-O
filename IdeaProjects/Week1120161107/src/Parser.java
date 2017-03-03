
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

import static javax.swing.JOptionPane.*;

/**
 * Created by student on 11/6/16.
 */
public class Parser {
    String userName;
    int numQueries;

    public void parse(String filePath) throws
            WrongFileFormatException, WrongNumberOfQueriesException,
            InvalidInputException, MalformedQueryException, IOException {
        Scanner scanner = null;
        int someNumber = 0;
        FileReader in=null;

        try {
            in = new FileReader(filePath);
            scanner = new Scanner(in);
        }catch (IOException e){
            showMessageDialog(null, "The selected file could not be found");
        }


        try {
            String userStart = "C";
            String userEnd = "c";
            String a="";
            while (scanner.hasNextLine()){
                a += scanner.nextLine();
                a +="\n";
            }
//                System.out.println(a);
            String[] parts = a.split("\n");
            if (!parts[0].equals(userStart)){
                throw new WrongFileFormatException("WrongFileFormatException");
            }
            if (!parts[2].equals(userEnd)){
                throw new WrongFileFormatException("WrongFileFormatException");
            }
            if (parts[0].equals(userStart) && parts[2].equals(userEnd)){
                userName =parts[1];
//                System.out.println(userName);
            }

            String numberStart = "N";
            String numberEnd = "n";
            if (!parts[3].equals(numberStart) ){
                throw new WrongFileFormatException("WrongFileFormatException");
            }
            if (!parts[5].equals(numberEnd)){
                throw new WrongFileFormatException("WrongFileFormatException");
            }

            if (parts[3].equals(numberStart) && parts[5].equals(numberEnd)){
                int line4 =Integer.parseInt(parts[4]);
                if (line4<1) {
                    throw new InvalidInputException("InvalidInputException");
//                    System.out.println(someNumber);
                }
                else
                    someNumber = line4;
            }

            String queryStart = "Q";
            String queryEnd = "q";
            int count = 0;
            for (int i=6; i<parts.length; i++){
                if (!parts[6].equals(queryStart)){
                    throw new WrongFileFormatException("WrongFileFormatException");
                }
                if (!parts[parts.length-1].equals(queryEnd)){
                    throw new WrongFileFormatException("WrongFileFormatException");
                }
                if (parts[6].equals(queryStart) &&parts[parts.length-1].equals(queryEnd)){
                    if (parts[i].startsWith("SELECT")||parts[i].startsWith("UPDATE")||parts[i].startsWith("INSERT")||parts[i].startsWith("DELETE")){
//                        System.out.println(parts[i]);
                        count++;
                    }
                }
                else throw new MalformedQueryException("MalformedQueryException");
            }

            numQueries=count;
            if (numQueries!=someNumber)
                throw new WrongNumberOfQueriesException("WrongNumberOfQueriesException");


//        }catch (ArrayIndexOutOfBoundsException exp){
//            showMessageDialog(null, "Array out of bound");
        }finally {
            if (scanner != null)
                scanner.close();
        }
    }

    public String getUserName() {
        return userName;
    }

    public int getNumQueries() {
        return numQueries;
    }

    public static void main(String[] args) throws WrongNumberOfQueriesException, WrongFileFormatException, MalformedQueryException, InvalidInputException, IOException {
        Parser parse = new Parser();
        parse.parse("/Users/student/IdeaProjects/Week1120161107/src/file6.sql");
    }
}