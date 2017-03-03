/**
 * Created by student on 11/6/16.
 */
public class Stats {
    public static int mode(int[] list) {
        int modeValue, modeCount;
        //most frequent value so far, count of the mosr frequent vlaue so far
        int currentValue, currentCount;
        // value that has just been procedded, the count of the run of those values
        modeValue = list[0]; //assume have at least one
        modeCount = itemFrequency(list, 0);
        for (int i = 0; i < list.length; i++) {
            if (modeCount < itemFrequency(list, i)) {
                modeValue = list[i];
                modeCount = itemFrequency(list, i);
            }
        }
        return modeValue;
    }
    private static int itemFrequency(int[] array, int startIndex){
        int count=1;
        for (int i=startIndex+1; i<array.length; i++){
            if (array[i] == array[startIndex])
                count++;
        }
        return count;
    }
}
/*
이거를 엶에따라 다른 한쪽이 닫히게되는 원리
        modeCount = 1;
        modeValue = list[0];
        currentCount=1;
        currentValue=list[0];

        if (list == null || list.length < 1)
            return -1;
        if (list.length==1)
            return list[0];

        for (int i=1; i<list.length; i++) {
            if (list[i] == currentValue) {
                currentCount++;

                if (currentCount > currentValue) {
                    modeCount = currentCount;
                    modeValue = modeCount;
                }
            }else
            currentCount=1;
        currentValue=list[0];
            // if this value is same as privious value
        }
        return modeValue;*/






/*


//
//
//    int count = 0, max=2;
//        if (list.length==1){
//        return list[0];
//    }
//        for (int i = 0; i < list.length; i++) {
//        for (int j = 0; j < list.length; j++) {
//            if (list[i] == list[j])
//                count++;
//        }
////            if (list.length<5) {
//        if (count > max) {
//            max = count;
//            max = list[i];
//            return max;
//        }
////            }
////            if (list.length>4)
//    }
//        return max;
//}
if (list==null || list.length==0){
            return -1;
        }

        int currentNumber=list[0], currentCount=1, mode=currentNumber, modeCount=currentCount;
        for (int i=1; i<list.length; i++){
            if (i == list.length - 1) {
                currentCount++;
                if (currentCount > modeCount) {
                    modeCount = currentCount;
                    mode = currentNumber;
                }
            }

            if (list[i]!=currentNumber) {
                if (currentCount > modeCount) {
                    modeCount = currentCount;
                    mode = currentNumber;
                }
                currentCount = 1;
                currentNumber = list[i];
            }else{
                    currentCount++;
                }
            }
        return mode;
        }


*/