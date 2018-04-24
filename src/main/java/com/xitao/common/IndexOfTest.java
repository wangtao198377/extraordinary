package com.xitao.common;

//import java.util.Objects;

public class IndexOfTest {

    public static int indexOf(String source, String sub) {
        if( source == null || sub == null) {
            return -1;
        }

        for(int i=0;i<source.length();i++) {
            int m =i;
            for(int j=0;j<sub.length();j++) {
                if(m >= source.length() || j >= sub.length()) {
                    return -1;
                }
                if(source.charAt(m) == sub.charAt(j)) {
                    if(j==sub.length()-1) {
                        return i;
                    }
                    m++;
                } else {
                    break;
                }

            }

        }
        return -1;

    }

    public static void main(String[] args) {
        System.out.println(indexOf("bbba","aca"));
    }
}
