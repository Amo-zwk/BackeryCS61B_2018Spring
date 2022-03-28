package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] buckets = new int[M]; //有M个桶
        int N = oomages.size();
         //遍历一遍oomages
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M; //桶的编号,%M保证了范围0-M-1内
            buckets[bucketNum] += 1; //桶编号的数量
        }
         //如果满足low和high
        int low = N / 50;
        int high = (int) (N / 2.5);
        for (int bucket : buckets) {
            if (bucket < low || bucket > high) {
                return false;
            }
        }
        return true;
    }
}
