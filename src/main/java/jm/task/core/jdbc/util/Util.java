package jm.task.core.jdbc.util;

import java.sql.DriverManager;
import java.sql.Statement;

public class Util {
    private static final String URl = "jdbc:mysql://localhost:3306/testbase";
    private static final String userNameDB = "root";
    private static final String passwordDB = "suhar_17a17";
    private static final String ConDB = "com.mysql.jdbc.Driver";

    public Util() {

    }

    public Statement getConnection() {
        try {
            Class.forName(ConDB);
            return (DriverManager.getConnection(URl, userNameDB, passwordDB)).createStatement();
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }
}
