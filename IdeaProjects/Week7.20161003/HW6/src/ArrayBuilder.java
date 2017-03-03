/**
 * Created by student on 10/9/16.
 */
public class ArrayBuilder {
    char[][] letterArray;
    char baseLetter;
    int row, column;
    public ArrayBuilder (char baseLetter, int n, int m) {
        //n= number of row, m=number of column,
        this.baseLetter=baseLetter;
        this.row=n;
        this.column=m;
        char[][] letter = new char[n][m];
        this.letterArray=letter;
    }
    public char[][] getLetterArray(){
        char[][] copy = new char[letterArray.length][letterArray[0].length];
        for (int i = 0; i < letterArray.length; i++) {
            for (int j = 0; j < letterArray[i].length; j++) {
                copy[i][j] = letterArray[i][j];
            }
        }
        return copy;
    }
    public void build() {
        getLetterArray();
        char ch=baseLetter;
        for (int i = 0, k = 1; i < letterArray.length; i++, k += 1) {
            for (int j = 0; j < letterArray[i].length; j++) {
                letterArray[i][j] = ch;
                ch++;
                if (baseLetter==Character.toLowerCase(baseLetter)&& ch>122)
                    ch = (char) (97);
                if (baseLetter==Character.toUpperCase(baseLetter)&&ch>90)
                    ch = (char) (65);
            }
            ch=baseLetter;
            ch += k;
           // System.out.println(ch);
            if (baseLetter==Character.toLowerCase(baseLetter)&& ch>122)
                ch = (char) (ch-26);
            if (baseLetter==Character.toUpperCase(baseLetter)&&ch>90)
                ch = (char) (ch-26);

            }
        }

    public void printLetterArray() {
        for (row = 0; row < letterArray.length; row++) {
            for (column = 0; column < letterArray[0].length; column++)
                System.out.print(letterArray[row][column] + "|");
            System.out.println("\n ");
        }
    }

    public static void main(String[] args) {
        ArrayBuilder testArray = new ArrayBuilder('Y', 4, 4);
        testArray.build();
        testArray.printLetterArray();
        /*System.out.println("Base letter:");
        Scanner scan =new Scanner(System.in);
        String s=scan.nextLine();
        char baseletter = s.charAt(0);
        System.out.println("row:");
        int row=scan.nextInt();
        System.out.println("column:");
        int column=scan.nextInt();

        ArrayBuilder arrayBuilder=new ArrayBuilder(baseletter, row, column);
        */

    }
}