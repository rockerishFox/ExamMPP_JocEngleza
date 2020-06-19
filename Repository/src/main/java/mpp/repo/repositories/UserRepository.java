package mpp.repo.repositories;

import mpp.domain.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository implements CRUDRepository<User>
{
    // partea de Hibernate
    private static SessionFactory sessionFactory;
    private static Session session;

    private static void initialiseSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }

    public UserRepository() {
        initialiseSessionFactory();
        session = sessionFactory.openSession();
    }


    // partea de CRUD
    @Override
    public void save(User o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User findOne(int id) throws Exception {
        session = sessionFactory.openSession();

        String command = "FROM User E WHERE E.id = :entity_id";
        Query query = session.createQuery(command);
        query.setParameter("entity_id", id);
        List result = query.list();

        session.close();

        if (result.isEmpty()) {
            throw new Exception("User not found");
        }

        return (User) result.get(0);
    }

    @Override
    public User findByUsername(String username) throws Exception {
        session = sessionFactory.openSession();

        String command = "FROM User E WHERE E.username = :user_name";
        Query query = session.createQuery(command);
        query.setParameter("user_name", username);
        List result = query.list();

        session.close();

        if (result.isEmpty()) {
            throw new Exception("User not found");
        }

        return (User) result.get(0);
    }

    @Override
    public User findPlayer(String username, String password) throws Exception {
        session = sessionFactory.openSession();

        String command = "FROM User E WHERE E.username = :user_name AND E.password=:password";
        Query query = session.createQuery(command);
        query.setParameter("user_name", username);
        query.setParameter("password", password);
        List result = query.list();


        session.close();

        if (result.isEmpty()) {
            throw new Exception("User not found");
        }

        return (User) result.get(0);
    }

    @Override
    public void delete(int id) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("delete User where id = :ID");
        query.setParameter("ID", id);

        int result = query.executeUpdate(); // returns rows affected

        session.getTransaction().commit();
        session.close();

//         SAU
//        session = sessionFactory.openSession();
//        User user = new User();
//        user.setId(id);
//        session.delete(user);
//        session.close();

    }

    @Override
    public void update(int id, User newUser) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "UPDATE User U set U.password = :pass, U.username=:user_name WHERE U.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("pass", newUser.getPassword());
        query.setParameter("user_name", newUser.getUsername());
        query.setParameter("id", id);
        int result = query.executeUpdate(); // returns rows affected

        session.close();
    }

    @Override
    public Iterable<User> getAll() {
        session = sessionFactory.openSession();

        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List results = query.list();

        session.close();

        return (List<User>) results;
    }
}
