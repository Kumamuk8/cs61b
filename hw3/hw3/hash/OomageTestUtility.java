package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] temp = new int[M];
        int bucketNum;
        for(Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            temp[bucketNum] += 1;
        }
        for(int i : temp) {
            if(i > oomages.size()/50 && i < oomages.size()/2.5) {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
