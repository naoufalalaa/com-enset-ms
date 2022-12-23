package ma.enset.orderservice.entities;

import ma.enset.orderservice.enums.OrderStatus;
import ma.enset.orderservice.model.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
@Projection(name="fullOrder",types = Order.class)
public interface OrderProjection {
    Long getId();
    Date getCreatedAt();
    Long getCustomerId();
    Customer getCustomer();
    OrderStatus getStatus();
}
