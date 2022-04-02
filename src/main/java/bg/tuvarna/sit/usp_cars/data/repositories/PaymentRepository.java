package bg.tuvarna.sit.usp_cars.data.repositories;

import bg.tuvarna.sit.usp_cars.data.access.Connection;
import bg.tuvarna.sit.usp_cars.data.entities.Mechanic;
import bg.tuvarna.sit.usp_cars.data.entities.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class PaymentRepository implements DAORepository<Payment>{
    public static PaymentRepository getInstance() {
        return PaymentRepository.PaymentRepositoryHolder.INSTANCE;
    }

    private static class PaymentRepositoryHolder {
        public static final  PaymentRepository INSTANCE = new PaymentRepository();
    }
    @Override
    public void save(Payment obj) {
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
    public void update(Payment obj) {
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
    public void delete(Payment obj) {
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
    public Payment getById(Integer id) {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        Payment payment=new Payment();
        try{
            String jpql="SELECT p FROM Payment p WHERE id_payment ="+id.toString();
            payment=session.createQuery(jpql,Payment.class).getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return payment;
    }

    @Override
    public List<Payment> getAll() {
        Session session=Connection.openSession();
        Transaction transaction= session.beginTransaction();
        List<Payment>  payments = new LinkedList<>();
        try{
            String jpql="SELECT p FROM Payment p";
            payments.addAll(session.createQuery(jpql, Payment.class).getResultList());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        return payments;
    }
}
