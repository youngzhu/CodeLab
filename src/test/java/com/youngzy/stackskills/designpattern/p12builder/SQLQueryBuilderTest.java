package com.youngzy.stackskills.designpattern.p12builder;

import org.junit.Test;

import static org.junit.Assert.*;

public class SQLQueryBuilderTest {

    @Test
    public void selectOneTable() {
        String selectSQL = SQLQueryBuilder.newBuilder()
                .queryType(SQLQueryBuilder.QueryType.SELECT)
                .table("Person")
                .selectColumns("name", "age", "gender")
                .whereEquals("name", "Adam")
                .whereEquals("gender", "m")
                .getQuery();

        String expected = "SELECT name, age, gender FROM Person WHERE name='Adam' AND gender='m'";
        assertEquals(expected, selectSQL);
    }

    @Test
    public void selectTwoTables() {
        String selectSQL = SQLQueryBuilder.newBuilder()
                .queryType(SQLQueryBuilder.QueryType.SELECT)
                .table("Person", "p")
                .table("Nation", "n")
                .selectColumns("name", "age", "gender")
                .whereEquals("p.name", "Adam")
                .whereEquals("p.gender", "m")
                .getQuery();

        String expected = "SELECT name, age, gender FROM Person p, Nation n WHERE p.name='Adam' AND p.gender='m'";
        assertEquals(expected, selectSQL);
    }

    @Test
    public void selectNoWhere() {
        String selectSQL = SQLQueryBuilder.newBuilder()
                .queryType(SQLQueryBuilder.QueryType.SELECT)
                .table("Person")
                .selectColumns("name", "age", "gender")
                .getQuery();

        String expected = "SELECT name, age, gender FROM Person";
        assertEquals(expected, selectSQL);
    }

    @Test
    public void exception() {
        String selectSQL = SQLQueryBuilder.newBuilder()
                .table("Person")
                .selectColumns("name", "age", "gender")
                .getQuery();

        String expected = "SELECT name, age, gender FROM Person";
        assertEquals(expected, selectSQL);
    }
}