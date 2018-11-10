package com.xitao;

public enum EnumSingleton {
    RED("red","red clor"),COLOR("color","color2");
    private String field1;
    private String field2;
    //此处只能为private
    private  EnumSingleton(String value1,String value2) {
        field1 = value1;
        field2 =value2;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
