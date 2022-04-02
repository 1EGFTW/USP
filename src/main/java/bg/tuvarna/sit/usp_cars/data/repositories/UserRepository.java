package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.access.Connection;
import bg.tuvarna.sit.usp_cars.data.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class UserRepository implements DAORepository<User>{
    public static  UserRepository getInstance() {
        return UserRepository.UserRepositoryHolder.INSTANCE;
    }

    private static class UserRepositoryHolder {
        public static final  UserRepository INSTANCE = new UserRepository();
    }

    @Override
    public void save(User obj) {
        Session session= Connection.openSession();
        Transaction transaction= session.beginTransaction();
        try{
            session.save(obj);
        }catch(Exception e){
            e.printStackTrace();
        }finally {

            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(User obj) {
        Session session=Connection.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.update(obj);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(User obj) {
        Session session=Connection.openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.delete(obj);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public User getById(Integer id) {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        User user=new User();
        try{
            String jpql="SELECT u FROM User u WHERE id_user ="+id.toString();
            user=session.createQuery(jpql,User.class).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        List<User> users = new LinkedList<>();
        try{
            String jpql="SELECT u FROM User u";
            users.addAll(session.createQuery(jpql, User.class).getResultList());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return users;
    }
}
