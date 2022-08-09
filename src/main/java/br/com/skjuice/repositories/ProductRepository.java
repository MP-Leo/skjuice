package br.com.skjuice.repositories;

import br.com.skjuice.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByFlavor(String flavor);

}
