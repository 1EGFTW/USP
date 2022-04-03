package bg.tuvarna.sit.usp_cars.presentation.models;

import java.util.Objects;

public class PaymentModel {
    private String payment_type;

    public PaymentModel() {
    }

    public PaymentModel(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentModel that = (PaymentModel) o;
        return Objects.equals(payment_type, that.payment_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment_type);
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "payment_type='" + payment_type + '\'' +
                '}';
    }
}
