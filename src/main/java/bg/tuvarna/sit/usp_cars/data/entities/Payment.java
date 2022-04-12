package bg.tuvarna.sit.usp_cars.data.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_payment",nullable = false)
    private int id_payment;

    @Column(name = "payment_type")
    private String payment_type;

    @OneToMany(mappedBy = "payment")
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private Set<Car> payments=new HashSet<>();

    public Payment() {
    }

    public Payment(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getId_payment() {
        return id_payment;
    }

    public void setId_payment(int id_payment) {
        this.id_payment = id_payment;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public Set<Car> getPayments() {
        return payments;
    }

    public void setPayments(Set<Car> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(payment_type, payment.payment_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment_type);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_type='" + payment_type + '\'' +
                '}';
    }
}
