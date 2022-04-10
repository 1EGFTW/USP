package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.access.Connection;
import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class MechanicRepository implements DAORepository<Mechanic>{
    public static MechanicRepository getInstance() {
        return MechanicRepository.MechanicRepositoryHolder.INSTANCE;
    }

    private static class MechanicRepositoryHolder {
        public static final MechanicRepository INSTANCE = new MechanicRepository();
    }
    @Override
    public void save(Mechanic obj) {
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
    public void update(Mechanic obj) {
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
    public void delete(Mechanic obj) {
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
    public Mechanic getById(Integer id) {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        Mechanic mechanic=new Mechanic();
        try{
            String jpql="SELECT m FROM Mechanic m WHERE id_mechanic ="+id.toString();
            mechanic=session.createQuery(jpql,Mechanic.class).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return mechanic;
    }

    @Override
    public List<Mechanic> getAll() {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        List< Mechanic> mechanic = new LinkedList<>();
        try{
            String jpql="SELECT m FROM Mechanic m";
            mechanic.addAll(session.createQuery(jpql,  Mechanic.class).getResultList());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return mechanic;
    }
}
