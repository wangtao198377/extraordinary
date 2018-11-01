package com.xitao.mybatis;

import org.junit.Test;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DriverManagerTest {

    @Test
    public void test() throws Exception{
        Class cls = Class.forName("com.mysql.jdbc.Driver");
        Connection  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis");
        String sql ="select 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        resultSet.getInt(1);
    }

    @Test
    public void test1() throws Exception{
        TestReflect.test();
    }

    private static class TestReflect {

            @CallerSensitive
            public static  void test() {
                System.out.println(Reflection.getCallerClass().getName());
            }
    }
}
