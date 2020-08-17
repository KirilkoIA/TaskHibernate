package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = util.getFactory().openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = util.getFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE user").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = util.getFactory().openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
        System.out.println("User c именем - " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        user.setId(id);
        Session session = util.getFactory().openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "From " + User.class.getSimpleName();
        Session session = util.getFactory().openSession();
        session.beginTransaction();
        List<User> list = session.createQuery(sql).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = util.getFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
