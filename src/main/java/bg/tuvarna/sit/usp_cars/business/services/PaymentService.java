package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Owner;
import bg.tuvarna.sit.usp_cars.data.entities.Payment;
import bg.tuvarna.sit.usp_cars.data.repositories.OwnerRepository;
import bg.tuvarna.sit.usp_cars.data.repositories.PaymentRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.OwnerModel;
import bg.tuvarna.sit.usp_cars.presentation.models.PaymentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentService {
    private static final Logger log=Logger.getLogger(PaymentService.class);
    private final PaymentRepository repository=PaymentRepository.getInstance();
    public static PaymentService getInstance() {
        return PaymentService.PaymentServiceHolder.INSTANCE;
    }
    private static class PaymentServiceHolder {
        public static final PaymentService INSTANCE = new PaymentService();
    }

    public ObservableList<PaymentModel> getAllPayments() {
        List<Payment> payments=repository.getAll();

        return FXCollections.observableList(
                payments.stream().map(p -> new PaymentModel(
                        p.getPayment_type()
                )).collect(Collectors.toList()));
    }

    public Payment findPaymentType(PaymentModel paymentModel){
        Payment payment=new Payment(paymentModel.getPayment_type());
        for(Payment p:repository.getAll()){
            if(p.equals(payment))
                return p;
        }
        return null;
    }
    public boolean addPayment(PaymentModel paymentModel){
        if(findPaymentType(paymentModel)!=null){
            log.info("Payment type "+paymentModel.getPayment_type()+" already exists!\n");
            return false;
        }else{
            try{
                Payment payment=new Payment(paymentModel.getPayment_type());
                repository.save(payment);
                log.info("Payment type "+paymentModel.getPayment_type()+" added!\n");
                return true;
            }catch(Exception e){
                log.error("Error adding payment type!\n");
                e.printStackTrace();
                return false;
            }
        }
    }
}
