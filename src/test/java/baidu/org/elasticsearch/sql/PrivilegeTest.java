package org.elasticsearch.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import junit.framework.TestCase;

public class PrivilegeTest extends TestCase {
    private static String CONNECT_URL = "jdbc:mysql://127.0.0.1:8368";
    private static String SUPERUSER_NAME = "superuser";
    private static String SUPERUSER_PASS = "superuser";
    private static String ROOT_NAME = "root";
    private static String ROOT_PASS = "root";

    private Connection conn;
    private Statement stmt;

    private void disconnect() throws Exception {
        stmt.close();
        conn.close();
    }

    private void connnect(String username, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(CONNECT_URL, username, password);
        stmt = conn.createStatement();
    }
    
    @Test
    public void testRootAndSuperuser() throws Exception {
        
        // test reset superuser password
        connnect(ROOT_NAME, ROOT_PASS);
        stmt.execute("alter user superuser identified by yiguolei");
        disconnect();
        boolean connectFailed = false;
        try {
            connnect(SUPERUSER_NAME, SUPERUSER_PASS);
        } catch (Exception e) {
            connectFailed = true;
        }
        assertEquals(true, connectFailed);

        connnect(ROOT_NAME, ROOT_PASS);
        stmt.execute("alter user superuser identified by " + SUPERUSER_PASS);
        disconnect();
        connectFailed = false;
        try {
            connnect(SUPERUSER_NAME, SUPERUSER_PASS);
        } catch (Exception e) {
            connectFailed = true;
        }
        assertEquals(false, connectFailed);
        boolean resetPasswdFailed = false;
        try {
            stmt.execute("alter user root identified by " + SUPERUSER_PASS);
        } catch (Exception e) {
            resetPasswdFailed = true;
        }
        assertEquals(true, resetPasswdFailed);
        disconnect();
        
        // could not grant or revoke on superuser and root
        connnect(ROOT_NAME, ROOT_PASS);
        boolean grantFailed = false;
        try {
            stmt.execute("grant read_only on db1 to superuser");
        } catch (Exception e) {
            grantFailed = true;
        }
        assertEquals(true, grantFailed);
        
        grantFailed = false;
        try {
            stmt.execute("grant read_only on db1 to root");
        } catch (Exception e) {
            grantFailed = true;
        }
        assertEquals(true, grantFailed);
        disconnect();
    }

    @Test
    public void testCreateUserAndDropNormalUser() throws Exception {
        connnect(ROOT_NAME, ROOT_PASS);
        boolean userIsExists = false;
        // drop the user if it already exists
        ResultSet resultSet = stmt.executeQuery("show grants");
        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            if (userName.equals("yiguolei")) {
                userIsExists = true;
            }
        }
        if (userIsExists) {
            stmt.execute("drop user yiguolei");
        }
        // create the user and check if it exists
        stmt.execute("create user yiguolei identified by yiguolei");
        resultSet = stmt.executeQuery("show grants");
        boolean userIsCreated = false;
        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            if (userName.equals("yiguolei")) {
                userIsCreated = true;
            }
        }
        assertEquals(true, userIsCreated);
        
        // grant privilege to the user and check
        stmt.execute("grant read_only on db1 to yiguolei");
        resultSet = stmt.executeQuery("show grants for yiguolei");
        boolean privilegeFound = false;
        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            if (userName.equals("yiguolei")) {
                String dbPrivileges = resultSet.getString("db_privileges");
                if (dbPrivileges.contains("db1: (READ_ONLY);")) {
                    privilegeFound = true;
                }
            }
        }
        assertEquals(true, privilegeFound);
        
        // revoke db privilege from user
        stmt.execute("revoke read_only on db1 from yiguolei");
        resultSet = stmt.executeQuery("show grants for yiguolei");
        privilegeFound = false;
        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            if (userName.equals("yiguolei")) {
                String dbPrivileges = resultSet.getString("db_privileges");
                if (dbPrivileges.contains("db1: (READ_ONLY);")) {
                    privilegeFound = true;
                }
            }
        }
        assertEquals(false, privilegeFound);
        
        // grant table privilege to the user
        stmt.execute("grant read_write on db1.table1 to yiguolei");
        resultSet = stmt.executeQuery("show grants for yiguolei");
        privilegeFound = false;
        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            if (userName.equals("yiguolei")) {
                String dbPrivileges = resultSet.getString("table_privileges");
                if (dbPrivileges.contains("db1.table1: (READ_WRITE);")) {
                    privilegeFound = true;
                }
            }
        }
        assertEquals(true, privilegeFound);
        
        // drop the user
        stmt.execute("drop user yiguolei");
        userIsExists = false;
        resultSet = stmt.executeQuery("show grants");
        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            if (userName.equals("yiguolei")) {
                userIsExists = true;
            }
        }
        assertEquals(false, userIsExists);
        disconnect();
    }
}
