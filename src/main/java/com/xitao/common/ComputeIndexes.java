package com.xitao.common;

import java.util.Arrays;

public class ComputeIndexes {

    int[] computeStr(String str) {
        int len = str.length();
        int[] fails = new int[len];
        fails[0] = 0;

        for (int j = 1; j < len; j++) {
            int m = j - 1;
            for (; m >= 0; m--) {
                int index = 0;
                while (str.charAt(index) == str.charAt(index + j - m) && index < m) {
                    index++;
                }
                if (index == m) {
                    fails[j] = m;
                    break;
                }
            }
        }
        return fails;
    }

    int[] computeImprove(String str) {
        int len = str.length();
        int[] fails = new int[len];
        fails[0] = 0;

        for (int j = 1; j < len; j++) {
            int m = j - 1;
            int pre = fails[m];

            while (str.charAt(pre) != str.charAt(j - 1) && m > 0 && pre > 0) {
                pre = fails[m--];
            }
            if (str.charAt(pre) == str.charAt(j - 1) && pre != j - 1) {
                fails[j] = pre + 1;
                continue;
            }
            fails[j] = 0;
        }
        return fails;

    }

    public int indexOf(String source, String target) {
        int[] result2 = computeImprove(target);
        int i = 0;
        int j = 0;
        while (i < source.length() && j < target.length()) {
            if (source.charAt(i) != target.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = result2[j];
                }
            } else {
                i++;
                j++;
            }
        }
        if (j == target.length()) {
            return i-target.length();
        }
        return -1;
    }


    public static void main(String[] args) throws Exception {
        String target = "ASDADSDADS";
        ComputeIndexes computeIndexes = new ComputeIndexes();

        int[] result = computeIndexes.computeStr(target);
        int[] result2 = computeIndexes.computeImprove(target);

        System.out.println(Arrays.asList(result));
        System.out.println(Arrays.asList(result2));

        System.out.println(computeIndexes.indexOf("aadadwASDADSDADSddd","DSDADS"));
    }
}
