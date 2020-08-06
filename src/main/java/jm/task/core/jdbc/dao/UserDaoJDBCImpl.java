package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String URl = "jdbc:mysql://localhost:3306/testbase";
    private static final String userNameDB = "root";
    private static final String passwordDB = "suhar_17a17";
    private static final String ConDB = "com.mysql.jdbc.Driver";
    private long idUser = 1;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Class.forName(ConDB);
            ((DriverManager.getConnection(URl, userNameDB, passwordDB))
                    .createStatement()).execute("CREATE table IF NOT EXISTS users(id int auto_increment primary key,\n" +
                    "nameUser varchar(30),\n" +
                    "lastName varchar(30),\n" +
                    "age int(120));");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Class.forName(ConDB);
            ((DriverManager.getConnection(URl, userNameDB, passwordDB))
                    .createStatement()).execute("DROP TABLE IF EXISTS users;");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Class.forName(ConDB);
            ((DriverManager.getConnection(URl, userNameDB, passwordDB))
                    .createStatement()).execute("INSERT INTO users SET id = '" + idUser++ + "', nameUser = '" + name + "', lastName = '" + lastName + "', age = '" + age + "'");

            System.out.println("User c именем - " + name + " добавлен в базу данных");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            Class.forName(ConDB);
            ((DriverManager.getConnection(URl, userNameDB, passwordDB))
                    .createStatement()).execute("DELETE FROM users WHERE id = '" + id + "'");
            System.out.println("User delete");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            Class.forName(ConDB);
            ResultSet set = ((DriverManager.getConnection(URl, userNameDB, passwordDB))
                    .createStatement()).executeQuery("SELECT * FROM users");
            while (set.next()) {
                User user = new User(set.getString(2), set.getString(3), set.getByte(4));
                user.setId(set.getLong(1));
                list.add(user);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        try {
            Class.forName(ConDB);
            ((DriverManager.getConnection(URl, userNameDB, passwordDB))
                    .createStatement()).execute("TRUNCATE TABLE users;");
            System.out.println("Table clear");
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
