package com.youngzy.stackskills.designpattern.p12builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 照虎画猫，依葫芦画瓢
 *
 * @version 1.0
 */
public class SQLQueryBuilder {
    public enum QueryType {
        SELECT,
        INSERT,
        UPDATE
    }

    private QueryType queryType;
    private String[] columns;
    private List<String> tables = new ArrayList<>();
    private List<String> tableAlias = new ArrayList<>();
    // 不用map，是因为顺序不可控
    private List<String> whereEqualsColumns = new ArrayList<>();
    private List<String> whereEqualsValues = new ArrayList<>();

    private SQLQueryBuilder(){}

    public static SQLQueryBuilder newBuilder() {
        return new SQLQueryBuilder();
    }

    public SQLQueryBuilder queryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    public SQLQueryBuilder selectColumns(String... columns) {
        this.columns = columns;
        return this;
    }

    public SQLQueryBuilder table(String tableName) {
        this.tables.add(tableName);
        return this;
    }

    public SQLQueryBuilder table(String tableName, String alias) {
        this.tables.add(tableName);
        this.tableAlias.add(alias);
        return this;
    }

    public SQLQueryBuilder whereEquals(String column, String value) {
        whereEqualsColumns.add(column);
        whereEqualsValues.add(value);
        return this;
    }

    public String getQuery() {
        StringBuilder sb = new StringBuilder();

        switch (queryType) {
            case INSERT:
                sb = new StringBuilder("INSERT");
                break;
            case SELECT:
                sb = new StringBuilder("SELECT");
                int idx = 0;
                // columns
                for (String column : columns) {
                    sb.append(" " + column);

                    idx ++;

                    if (idx < columns.length) {
                        sb.append(",");
                    }

                }

                sb.append(" FROM ");

                if (tableAlias.isEmpty()) {
                    // 没有别名，只有一个表
                    sb.append(tables.get(0));
                } else {
                    idx = 0;
                    for (String table : tables) {
                        sb.append(table + " ");
                        sb.append(tableAlias.get(idx ++));

                        if (idx < tables.size()) {
                            sb.append(", ");
                        }
                    }
                }

                if (! whereEqualsColumns.isEmpty()) {
                    sb.append(" WHERE ");

                    idx = 0;
                    for (String column : whereEqualsColumns) {
                        sb.append(column + "='");
                        sb.append(whereEqualsValues.get(idx ++) + "'");

                        if (idx < whereEqualsColumns.size()) {
                            sb.append(" AND ");
                        }
                    }
                }

                break;
            case UPDATE:
                sb = new StringBuilder("UPDATE");
                break;
            default:
                throw new RuntimeException("请指定SQL类型！");
        }

        return sb.toString();
    }
}
