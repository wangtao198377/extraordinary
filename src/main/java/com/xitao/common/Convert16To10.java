package com.xitao.common;

public class Convert16To10 {

    public static void main(String[] args) throws Exception{
        int aa=0XF234;
        String dd="1234";

        int bb= Integer.valueOf(dd,16);
        System.out.println(bb);
        System.out.println(aa);
        System.out.println(convert("0x12"));

    }

    public static int convert(String str16)  throws Exception {
        if(str16.charAt(0) != '0' || (str16.charAt(1) != 'x' && str16.charAt(1) != 'X'))
            return -1;
        if(str16.length() <3) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        for(int i=2;i<str16.length();i++) {
            int temp = charToi(str16.charAt(i));
            if(temp == -1) {
                throw new IllegalArgumentException();
            }
            result = result*16+temp;
        }
        return result;
    }



    public static int charToi(char ch) {
        if(ch >='0' || ch <='9') {
            return ch -'0';
        } else if (ch >='A' || ch <'F') {
            return ch-'A';
        } else if (ch >'a' || ch <'f') {
            return ch -'a';
        }
        return -1;
    }
}
