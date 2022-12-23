package ma.enset.inventoryservice.repo;

import ma.enset.inventoryservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    Product save(Product product);

    @GetMapping("/")
    @PreAuthorize("hasAuthority('USER')")
    Page<Product> findAll(Pageable pageable);
}
