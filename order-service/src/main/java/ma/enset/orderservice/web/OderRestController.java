package ma.enset.orderservice.web;

import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.model.Customer;
import ma.enset.orderservice.model.Product;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.repository.ProductItemRepository;
import ma.enset.orderservice.services.CustomerRestClientService;
import ma.enset.orderservice.services.InventoryRestClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/allOrders")
    @PreAuthorize("hasAuthority('USER')")
    public Iterable<Order> getAllOrders(){
        List<Order> orders = (List<Order>) orderRepository.findAll();
        orders.forEach(order -> {
            Customer customer = customerRestClientService.customerById(order.getCustomerId());
            order.setCustomer(customer);
            order.getProductItems().forEach(productItem -> {
                Product product = inventoryRestClientService.productById(productItem.getProductId());
                productItem.setProduct(product);
            });
        });
        return orders;
    }

    @GetMapping("/fullOrder/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }

    @PostMapping("/addOrder")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    @DeleteMapping("/deleteOrder/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
    }
}
