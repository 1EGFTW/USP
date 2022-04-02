package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.access.Connection;
import bg.tuvarna.sit.usp_cars.data.entities.CarService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class CarServiceRepository implements DAORepository<CarService> {
    public static CarServiceRepository getInstance() {
        return CarServiceRepository.CarServiceRepositoryHolder.INSTANCE;
    }

    private static class CarServiceRepositoryHolder {
        public static final  CarServiceRepository INSTANCE = new CarServiceRepository();
    }
    @Override
    public void save(CarService obj) {
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
    public void update(CarService obj) {
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
    public void delete(CarService obj) {
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
    public CarService getById(Integer id) {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        CarService carService=new CarService();
        try{
            String jpql="SELECT cs FROM CarService cs WHERE id_car_service ="+id.toString();
            carService=session.createQuery(jpql,CarService.class).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return carService;
    }

    @Override
    public List<CarService> getAll() {
        Session session= Connection.openSession();
        Transaction transaction= session.beginTransaction();
        List<CarService> carservice = new LinkedList<>();
        try{
            String jpql="SELECT cs FROM CarService cs";
            carservice.addAll(session.createQuery(jpql,CarService.class).getResultList());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return carservice;
    }
}
