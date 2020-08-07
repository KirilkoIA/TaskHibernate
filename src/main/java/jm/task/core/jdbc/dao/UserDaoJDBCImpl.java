package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private long idUser = 1;

    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            util.getConnection().execute("CREATE table IF NOT EXISTS users(id int auto_increment primary key,\n" +
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
            util.getConnection().execute("DROP TABLE IF EXISTS users;");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            util.getConnection().execute("INSERT INTO users SET id = '" + idUser++ + "', nameUser = '" + name + "', lastName = '" + lastName + "', age = '" + age + "'");

            System.out.println("User c именем - " + name + " добавлен в базу данных");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            util.getConnection().execute("DELETE FROM users WHERE id = '" + id + "'");
            System.out.println("User delete");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            ResultSet set = util.getConnection().executeQuery("SELECT * FROM users");
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
            util.getConnection().execute("TRUNCATE TABLE users;");
            System.out.println("Table clear");
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
