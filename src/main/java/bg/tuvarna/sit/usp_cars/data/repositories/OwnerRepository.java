package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.access.Connection;
import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class OwnerRepository implements DAORepository<Owner>{
    public static OwnerRepository getInstance() {
        return OwnerRepository.OwnerRepositoryHolder.INSTANCE;
    }

    private static class OwnerRepositoryHolder {
        public static final  OwnerRepository INSTANCE = new OwnerRepository();
    }
    @Override
    public void save(Owner obj) {
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
    public void update(Owner obj) {
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
    public void delete(Owner obj) {
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
    public Owner getById(Integer id) {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        Owner owner=new Owner();
        try{
            String jpql="SELECT o FROM Owner o WHERE id_owner ="+id.toString();
            owner=session.createQuery(jpql,Owner.class).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return owner;
    }

    @Override
    public List<Owner> getAll() {
        Session session= Connection.openSession();
        Transaction transaction= session.beginTransaction();
        List<Owner> owner = new LinkedList<>();
        try{
            String jpql="SELECT o FROM Owner o";
            owner.addAll(session.createQuery(jpql, Owner.class).getResultList());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return owner;
    }
}
