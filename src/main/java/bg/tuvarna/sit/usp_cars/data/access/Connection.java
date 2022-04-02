package bg.tuvarna.sit.usp_cars.data.access;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {
    private static final Logger log=Logger.getLogger(Connection.class);
    private static SessionFactory sessionFactory;
    static{
        try{
            sessionFactory=new Configuration().configure().buildSessionFactory();
        }catch (Throwable e)
        {
            log.error("Initial SessionFactory error"+e);
            e.printStackTrace();
        }
    }
    public static Session openSession(){
        return sessionFactory.openSession();
    }
    public static void closeSession(){
        sessionFactory.close();
    }
}