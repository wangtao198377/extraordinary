package com.xitao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class GetSql2 {


    private static final String[] sqlTemplate = {
            "alter table %s add oldId varchar(64) DEFAULT NULL COMMENT 'sqlserver旧id';"
    };


    private static final String[] prefixArr={"orderbank_"};


    public static void main(String[] args) throws Exception {
        File fs= new File("/Users/wangtao68/dev/sql.txt");
        System.setOut(new PrintStream(new FileOutputStream(fs,false)));
        for(int i=0;i<sqlTemplate.length;i++) {
            for(int j=0;j<200;j++) {
                String num = String.format("%04d",j);
                String prefix = prefixArr[i]+num;
                String sql = String.format(sqlTemplate[i],prefix);
                System.out.println();
                System.out.println(sql);
            }
        }


    }
}
