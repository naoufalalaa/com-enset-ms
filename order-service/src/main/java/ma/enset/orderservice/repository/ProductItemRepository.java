package ma.enset.orderservice.repository;

import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
