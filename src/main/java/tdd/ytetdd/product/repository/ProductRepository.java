package tdd.ytetdd.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.ytetdd.product.entity.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIdIn(Collection<Long> collect);

}
