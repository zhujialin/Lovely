package esam;

/**
 * Created by zhaobofan on 16/9/5.
 */
public class Math6 {
    public static void main(String args[]) {
        // 求最大连续数组的和
        int a[] = {5, -4, 8, 9};
        sum(a);
    }

    private static void sum(int[] a) {
        int max = 0;
        int cur = 0;
        for (int i = 0; i < a.length; i++) {
            cur += a[i];
            if (cur < 0) {
                cur = 0;
            }
            if (cur > max) {
                max = cur;
            }

        }
        System.out.println(max);
    }
}
