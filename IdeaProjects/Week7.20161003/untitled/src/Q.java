class Q {
    public static void main(String args[]) {
        char[][] array = new char[6][10];   //2차원 배열로써 2차원에 4개 1차원에 10개짜리 방을 잡습니다.
        char ch='A';   // char형 변수 ch에 초기값으로 A을 넣습니다.

        for(int i =0, k = 2; i<array.length; i++, k+=4) {
            // array.length를 하면 2차원배열의 크기(길이)를 뽑아냅니다.
            // 동시에 k변수를 만들고 for문이 돌 때마다 2씩 증가시킵니다.
            for(int j=0; j<array[i].length; j++) {   // array[i].length는 1차원배열의 방 크기를 뽑아냅니다.
                array[i][j] = ch;
                //차례차례 [i][j]번 째 방에 ch값을 넣습니다. 당연히 i,j값은 for문이 돌 때마다 바뀝니다.
                ch++;   // 'A'를 문자지만 char형 특성상 변수에 담아 ++를 시키면 하나씩 증가가 됩니다.
                System.out.print(array[i][j]);   //for j문이 한 번씩 끝날 때 마다 출력합니다.
            }
            System.out.println(""); // 그리고 for i문이 끝날 때 마다 줄바꿈을 합니다.
            ch = 'A';   // ch를 통하여 i문이 끝나면 다시 A로 초기화를 시킵니다.
            ch= (char) ( (k-25) +'A');// 그리고 k가 2씩 증가하므로 C 그다음은 E 그다음은 G가 나옵니다.

        }
    }
}
/*class Alphabet {
public static void main(String args[]) {

    // Create an array that will hold the grid
    char alphGrid[][] = genArray();

    // Two for loops to print the grid on the screen
    for(int i=0; i<26; i++) {

        for(int j=0; j<26; j++) {
            System.out.print(alphGrid[i][j]);
        }
        System.out.println();
    }

} // end of main

// Create a function to generate the grid
public static char[][] genArray(){
    char[][] arr = new char[26][26];

    // Two for loops to generate the grid
    for(int i = 0; i < 26; i++) {
        for(int j = 0; j < 26; j++) {

            // Creates an int that will later be cast to a char
            int let = i + j;

            // Keeps the int from getting too big
            if(let >= 26)
                let = let - 26;

            // Add 65 to the int so that the char will return letters and not ASCII symbols
            let = let + 65;

            // Cast the int to a char
            char letter = (char)let;

            // Put the char into its respective place in the array
            arr[i][j] = letter;

        }
    }

    // Returns the grid
    return arr;
}
}
*/