package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.access.Connection;
import bg.tuvarna.sit.usp_cars.data.entities.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class CarRepository implements DAORepository<Car> {
    public static CarRepository getInstance() {
        return CarRepositoryHolder.INSTANCE;
    }

    private static class CarRepositoryHolder {
        public static final CarRepository INSTANCE = new CarRepository();
    }
    @Override
    public void save(Car obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Car obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Car obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Car getById(Integer id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Car car=new Car();
        try {
            String jpql = "SELECT c FROM Car c WHERE id_car =" + id.toString();
            car=session.createQuery(jpql, Car.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return car;
    }

    @Override
    public List<Car> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Car> cars = new LinkedList<>();
        try {
            String jpql = "SELECT c FROM Car c";
            cars.addAll(session.createQuery(jpql, Car.class).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return cars;
    }
}
