package com.xitao.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class MybatisInit {
    public static void main(String args[]) throws Exception  {

        //initByCode();
        initFromXml();

    }

    public static void initByCode() {

        Properties properties = new Properties();
        properties.put("driver","com.mysql.jdbc.Driver");
        properties.put("username","root");
        properties.put("password","cainiao123");
        properties.put("url","jdbc:mysql://localhost:3306/mybatis");


        PooledDataSourceFactory pooledDataSourceFactory =new PooledDataSourceFactory();
        pooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = pooledDataSourceFactory.getDataSource();

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("developement",transactionFactory,dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory =new  SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Integer count = sqlSession.selectOne("com.xitao.mybatis.BlogMapper.selectBlog");
        System.out.println("count=="+count);

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        System.out.println(blogMapper.selectBlog());
        //sqlSessionFactory.getConfiguration().addMapper();


    }

    public static void initFromXml() throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Integer count = sqlSession.selectOne("blogMapper.selectBlog", null);
            System.out.println("count::" + count);

            Test1DO test1DO = sqlSession.selectOne("blogMapper.selectBlogById", 1);

            System.out.println("test1DO::" + test1DO);
        } finally {
            sqlSession.close();
        }

    }
}
