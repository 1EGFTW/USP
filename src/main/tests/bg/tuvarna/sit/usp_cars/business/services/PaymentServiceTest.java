package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.Payment;
import bg.tuvarna.sit.usp_cars.data.repositories.PaymentRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.PaymentModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private Payment payment;
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;
    private PaymentModel paymentModel;

    @BeforeEach
    void setUp() {
        paymentService=PaymentService.getInstance();
        paymentRepository=PaymentRepository.getInstance();
        payment=new Payment("1");
        paymentModel=new PaymentModel("1");
    }

    @Test
    void getAllPayments() {
        ObservableList<PaymentModel> before=paymentService.getAllPayments();
        paymentRepository.save(payment);
        ObservableList<PaymentModel> after=paymentService.getAllPayments();
        assertNotEquals(before,after);
        paymentRepository.delete(payment);
    }

    @Test
    void findPaymentType() {
        assertNull(paymentService.findPaymentType(paymentModel));
    }

    @Test
    void addPayment() {
        assertTrue(paymentService.addPayment(paymentModel));
        payment=paymentService.findPaymentType(paymentModel);
        paymentRepository.delete(payment);
    }

    @Test
    void updatePayment() {
        assertFalse(paymentService.updatePayment(paymentModel));
    }

    @Test
    void deletePayment() {
        assertFalse(paymentService.deletePayment(paymentModel));
    }
}