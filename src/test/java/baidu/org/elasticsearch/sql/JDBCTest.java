/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package org.elasticsearch.sql;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import junit.framework.TestCase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest extends TestCase {
    private static String CONNECT_URL = "jdbc:mysql://localhost:8306";
    private static String SUPERUSER_NAME = "superuser";
    private static String SUPERUSER_PASS = "superuser";
    private static String USER_NAME = "gaopan";
    private static String USER_PASS = "gaopan123";

    private Connection conn;
    private Statement stmt;

    @Override
    protected void setUp() throws Exception {
        connnect(SUPERUSER_NAME, SUPERUSER_PASS);
    }

    @Override
    protected void tearDown() throws Exception {
        stmt.close();
        conn.close();
    }

    private void connnect(String username, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(CONNECT_URL, username, password);
        stmt = conn.createStatement();
    }

    private void clean() {
        try {
            String sql1 = " DROP TABLE db1.mytable1";
            stmt.executeUpdate(sql1);
        } catch (Exception e) {
            // Do noting
        }
        try {
            String sql2 = " DROP TABLE db2.mytable2";
            stmt.executeUpdate(sql2);
        } catch (Exception e) {
            // Do noting
        }
        try {
            String sql3 = " DROP USER gaopan";
            stmt.executeUpdate(sql3);
        } catch (Exception e) {
            // Do noting
        }
    }

    public void testAll() throws Exception {
        clean();
        createTable();
        createUser();
        grant();
        Thread.sleep(10000);
        insert_no_permission();
        revoke();
        insert();
        Thread.sleep(1000);
        search();
        update_array();
        update_object();
        createTable_no_permission();
        insert_duplicateKey();
        whiteList();
        dropUser();
        dropTable();
        httpPut();
        httpHead();
        httpPost();
        httpGet();
        httpDelete();
    }

    public void createTable() throws Exception {
        String sql = " create table db1.mytable1(\n" +
                " id integer primary key,\n" +
                " title string,\n" +
                " tags array(string),\n" +
                " author object as(\n" +
                " name string,\n" +
                " age integer)\n" +
                " )";
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);
    }

    public void createUser() throws Exception {
        String sql = " CREATE USER " + USER_NAME + " IDENTIFIED BY " + USER_PASS;
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);
    }

    public void grant() throws Exception {
        String sql = " GRANT read_only on db1.mytable1 to " + USER_NAME;
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);
    }

    public void insert_no_permission() throws Exception {
        tearDown();
        connnect(USER_NAME, USER_PASS);

        try {
            String sql = " INSERT INTO db1.mytable1 (id,title,tags,author) VALUES \n" +
                    "(1,'baidu',['aaa','bbb'],{name='gaopan',age=26})";
            int affectRows = stmt.executeUpdate(sql);
        } catch (Exception e) {
            assertTrue(e instanceof MySQLSyntaxErrorException);
        }
    }

    public void revoke() throws Exception {
        tearDown();
        connnect(SUPERUSER_NAME, SUPERUSER_PASS);

        String sql = " REVOKE read_only on db1.mytable1 FROM " + USER_NAME;
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);
    }

    public void insert() throws Exception {
        String grantSQL = " GRANT read_write on db1.mytable1 to " + USER_NAME;
        int grantAffectRows = stmt.executeUpdate(grantSQL);
        assertEquals(1, grantAffectRows);

        tearDown();
        connnect(USER_NAME, USER_PASS);

        String insertSQL = " insert into db1.mytable1 (id,title,tags,author) values\n" +
                "(1,'baidu',['aaa','bbb'],{name='gaopan',age=26})";
        int insertAffectRows = stmt.executeUpdate(insertSQL);
        assertEquals(1, insertAffectRows);
    }

    public void search() throws Exception {
        String searchSQL = " select * from db1.mytable1";
        ResultSet rs = stmt.executeQuery(searchSQL);
        int id = 0;
        String title = null;
        String tages = null;
        String author = null;
        while (rs.next()) {
            id = rs.getInt("id");
            title = rs.getString("title");
            tages = rs.getString("tags");
            author = rs.getString("author");
        }
        assertEquals(1, id);
        assertEquals("baidu", title);
        assertEquals("[\"aaa\",\"bbb\"]", tages);
        assertEquals("{\"age\":26,\"name\":\"gaopan\"}", author);
    }

    public void update_array() throws Exception {
        String sql = " update db1.mytable1 set tags=['ccc','ddd'] WHERE id = 1";
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);

        String searchSQL = " select tags from db1.mytable1 WHERE id = 1";
        ResultSet rs = stmt.executeQuery(searchSQL);
        String tages = null;
        while (rs.next()) {
            tages = rs.getString("tags");
        }
        assertEquals("[\"ccc\",\"ddd\"]", tages);
    }

    public void update_object() throws Exception {
        String sql = " update db1.mytable1 set author['age'] = 24 where id = 1";
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);

        String searchSQL = " select author['age'] from db1.mytable1 WHERE id = 1";
        ResultSet rs = stmt.executeQuery(searchSQL);
        int age = 0;
        while (rs.next()) {
            age = rs.getInt("author['age']");
        }
        assertEquals(24, age);
    }

    public void createTable_no_permission() throws Exception {
        tearDown();
        connnect(USER_NAME, USER_PASS);
        try {
            String sql = " create table db1.mytable2(id integer primary key)";
            int affectRows = stmt.executeUpdate(sql);
        } catch (Exception e) {
            assertTrue(e instanceof MySQLSyntaxErrorException);
        }
    }

    public void whiteList() throws Exception {
        tearDown();
        connnect(SUPERUSER_NAME, SUPERUSER_PASS);

        String sql = " ALTER USER gaopan WHITELIST \"127.0.0.1\"";
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);
        tearDown();
        connnect(USER_NAME, USER_PASS);
    }

    // test insert duplicateKey
    public void insert_duplicateKey() throws Exception {
        try {
            String insertSQL = " insert into db1.mytable1 (id,title) values (1,'newbaidu')";
            int insertAffectRows = stmt.executeUpdate(insertSQL);
            assertEquals(1, insertAffectRows);
        } catch (Exception e) {
            // Do nothing
        }

        String searchSQL = " select title from db1.mytable1 WHERE id = 1";
        ResultSet rs = stmt.executeQuery(searchSQL);
        String title = null;
        while (rs.next()) {
            title = rs.getString("title");
        }
        assertEquals("baidu", title);

        Thread.sleep(200);
        // search twice, primary and replic
        String searchSQL2 = " select title from db1.mytable1 WHERE id = 1";
        ResultSet rs2 = stmt.executeQuery(searchSQL);
        String title2 = null;
        while (rs2.next()) {
            title2 = rs2.getString("title");
        }
        assertEquals("baidu", title2);
    }

    public void dropUser() throws Exception {
        tearDown();
        connnect(SUPERUSER_NAME, SUPERUSER_PASS);

        String sql = " DROP USER " + USER_NAME;
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);
    }

    public void dropTable() throws Exception {
        tearDown();
        connnect(SUPERUSER_NAME, SUPERUSER_PASS);

        String sql = " DROP TABLE db1.mytable1";
        int affectRows = stmt.executeUpdate(sql);
        assertEquals(1, affectRows);
    }

    public void httpPut() throws Exception {
        String sql = " HTTP PUT /db2.mytable2";
        ResultSet rs = stmt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result = rs.getString("PUT RESULT");
        }
        assertEquals("{\"acknowledged\":true}", result);
    }

    public void httpHead() throws Exception {
        String sql = " HTTP HEAD /db2.mytable2";
        ResultSet rs = stmt.executeQuery(sql);
        int result = 0;
        while (rs.next()) {
            result = rs.getInt("HEAD RESULT");
        }
        assertEquals(200, result);
    }

    public void httpPost() throws Exception {
        String sql = " HTTP POST /db2.mytable2/all/1 {\"name\":\"gaopan\"}";
        ResultSet rs = stmt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result = rs.getString("POST RESULT");
        }
        assertEquals("{\"_index\":\"db2.mytable2\",\"_type\":\"all\",\"_id\":\"1\"," +
                "\"_version\":1,\"_shards\":{\"total\":2,\"successful\":1,\"failed\":0},\"created\":true}", result);
    }

    public void httpGet() throws Exception {
        String sql = " HTTP GET /db2.mytable2/all/1";
        ResultSet rs = stmt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result = rs.getString("GET RESULT");
        }
        assertEquals("{\"_index\":\"db2.mytable2\",\"_type\":\"all\",\"_id\":\"1\"," +
                "\"_version\":1,\"found\":true,\"_source\":{\"name\":\"gaopan\"}}", result);
    }
    
    public void httpDelete() throws Exception {
        String sql = " HTTP DELETE /db2.mytable2";
        ResultSet rs = stmt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result = rs.getString("DELETE RESULT");
        }
        assertEquals("{\"acknowledged\":true}", result);
    }
}
