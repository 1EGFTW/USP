package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.access.Connection;
import bg.tuvarna.sit.usp_cars.data.entities.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class ServiceRepository implements DAORepository<Service>{
    public static ServiceRepository getInstance() {
        return ServiceRepository.ServiceRepositoryHolder.INSTANCE;
    }

    private static class ServiceRepositoryHolder {
        public static final  ServiceRepository INSTANCE = new ServiceRepository();
    }
    @Override
    public void save(Service obj) {
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
    public void update(Service obj) {
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
    public void delete(Service obj) {
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
    public Service getById(Integer id) {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        Service service=new Service();
        try{
            String jpql="SELECT s FROM Service s WHERE id_service ="+id.toString();
            service=session.createQuery(jpql,Service.class).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return service;
    }

    @Override
    public List<Service> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Service> services = new LinkedList<>();
        try {
            String jpql = "SELECT s FROM Service s";
            services.addAll(session.createQuery(jpql, Service.class).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return services;
    }
}
