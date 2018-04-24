package com.xitao.mybatis;
import org.apache.ibatis.jdbc.SQL;

public class SqlBuilderTest {

    // Anonymous inner class
    public String deletePersonSql() {
        return new SQL() {{
            DELETE_FROM("PERSON");
            WHERE("ID = #{id}");
        }}.toString();
    }

    // Builder / Fluent style
    public String insertPersonSql() {
        String sql = new SQL()
                .INSERT_INTO("PERSON")
                .VALUES("ID, FIRST_NAME", "#{id}, #{firstName}")
                .VALUES("LAST_NAME", "#{lastName}")
                .toString();
        return sql;
    }

    // With conditionals (note the final parameters, required for the anonymous inner class to access them)
    public String selectPersonLike(final String id, final String firstName, final String lastName) {
        return new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME");
            FROM("PERSON P");
            if (id != null) {
                WHERE("P.ID like #{id}");
            }
            if (firstName != null) {
                WHERE("P.FIRST_NAME like #{firstName}");
            }
            if (lastName != null) {
                WHERE("P.LAST_NAME like #{lastName}");
            }
            ORDER_BY("P.LAST_NAME");
        }}.toString();
    }
    public static void main(String args[]) {

    }
}
